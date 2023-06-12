import java.util.Scanner;

public class ED211 {
    public static int countEven(BTree<Integer> t) {
        return countEven(t.getRoot());
    }

    public static int countEven(BTNode<Integer> t) {
        if (t == null) return 0;
        if (t.getValue() % 2 == 0) return 1 + countEven(t.getLeft()) + countEven(t.getRight());
        return countEven(t.getLeft()) + countEven(t.getRight());
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        BTree<Integer> t = LibBTree.readIntTree(in);
        System.out.println(countEven(t));
    }
}