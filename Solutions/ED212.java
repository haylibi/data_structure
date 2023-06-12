import java.util.Scanner;

public class ED212 {
    public static int[] sumLevels(BTree<Integer> t) {
        int altura = t.depth();
        int[] array = new int[altura+1];
        int curDepth = 0;
        return sumLevels(t.getRoot(), array, curDepth, altura);
    }

    public static int[] sumLevels(BTNode<Integer> t, int[] array, int curDepth, int maxDepth) {
        if (curDepth > maxDepth || t == null) return array;
        array[curDepth] += t.getValue();
        sumLevels(t.getLeft(), array, curDepth+1, maxDepth);
        sumLevels(t.getRight(), array, curDepth+1, maxDepth);
        return array;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        BTree<Integer> t = LibBTree.readIntTree(in);
        int[] sumLevel = sumLevels(t);
        System.out.print("[" + sumLevel[0]);
        for (int i=1; i<sumLevel.length; i++)
            System.out.print(", " + sumLevel[i]);
        System.out.println("]");
    }

}