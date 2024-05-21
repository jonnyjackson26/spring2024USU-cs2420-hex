public class UnionFindTests {
    public static void main(String[] args) {
        int n = 10;
        UnionFind uf = new UnionFind(n);
        System.out.println("Initial state:");
        System.out.println(uf);

        uf.union(0, 1);
        uf.union(2, 3);
        uf.union(4, 5);
        uf.union(6, 7);
        uf.union(8, 9);
        uf.union(1, 9);

        System.out.println("\nState after unions:");
        System.out.println(uf);

        System.out.println("\nFinding groups:");
        System.out.println("Connected(0, 2): " + uf.connected(0, 2));
        System.out.println("Connected(1, 9): " + uf.connected(1, 9));
        System.out.println("Connected(2, 8): " + uf.connected(2, 8));
        System.out.println("Connected(3, 5): " + uf.connected(3, 5));

        System.out.println("\nState after finding:");
        System.out.println(uf);
    }
}