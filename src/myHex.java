import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class myHex {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BLUE = "\u001B[34m";




    public static void main(String[] args) {
        System.out.println("my hex");
        UnionFind uf = new UnionFind(124+4); //the plus 4 is for the top bottom left and right
        //red = 123, blue = 122               //the board is responsible for the first 121 indexes and then the next 2 are responsible for red and blue
        int turn=122;

        //add extra elements for TOP (124), BOTTOM (125), LEFT(126), AND RIGHT (127)
        //top and bottom are red(123), and left and right are blue (122)
        uf.union(124,123);
        uf.union(125,123);
        uf.union(126,122);
        uf.union(127,122);


        //fill the union find set
        try {
            File txtFile = new File("txtFiles/moves.txt");
            Scanner myReader = new Scanner(txtFile);
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                System.out.println(line);
                int index=Integer.parseInt(line)-1; //i subtract 1 because we're labeling the board starting at 1, not 0.
                //TODO make sure moves are legal
                uf.union(index,turn);

                //switch turn
                if(turn==122) {
                    turn=123; //make it reds turn
                } else {
                    turn=122; //make it blues turn
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        printBoard(uf);
        System.out.println("winner is "+winner(uf));

    }

    public static void printBoard(UnionFind uf) {

        for(int i=0; i<11; i++) {
            StringBuilder line = new StringBuilder();
            //spacing
            for(int spacingVar=0; spacingVar<i; spacingVar++) {
                line.append(" ");
            }
            for(int j=0; j<11; j++) {
                int index=i*11+j;

                if(uf.connected(122,index)) { //blue
                    line.append(ANSI_BLUE + "B " + ANSI_RESET);
                } else if(uf.connected(123,index)) { //red
                    line.append(ANSI_RED + "R " + ANSI_RESET);
                } else {
                    line.append("X ");
                }
            }
            System.out.println(line);
        }
    }

    /*
    returns -1 if there is no winner
    returns 122 if blue won
    returns 123 if red won
     */
    public static int winner(UnionFind uf) {
        System.out.println(uf.toString());
        if(uf.find(126)==uf.find(127) && uf.find(126)==uf.find(122)) { //blue won
            return 123;
        } else if(uf.find(124)==uf.find(125) && uf.find(124)==uf.find(123)) { //red
            return 122;
        }
        return -1;
    }



    public static int[] getNeighborsIndexes(int index, UnionFind uf) {
        ArrayList<Integer> neighbors = new ArrayList<>();

        int row = (index - 1) / 11; // rows start at 0
        int col = (index - 1) % 11; // Columns start ay 0

        // left neighbor
        if (col > 0) {
            neighbors.add(index - 1);
        }
        // right neighbor
        if (col < 10) {
            neighbors.add(index + 1);
        }
        // upper left neighbor
        if (row > 0 && col >= 0) {
            neighbors.add(index - 11);
        }
        // upper right neighbor
        if (row > 0 && col < 10) {
            neighbors.add(index - 10);
        }
        // lower left neighbor
        if (row < 10 && col > 0) {
            neighbors.add(index + 10);
        }
        // lower right neighbor
        if (row < 10 && col < 11) {
            neighbors.add(index + 11);
        }

        int[] n = new int[neighbors.size()];
        for (int i = 0; i < neighbors.size(); i++) {
            n[i] = neighbors.get(i);
        }
        return n;
    }



}
