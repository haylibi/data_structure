import java.util.Scanner;

class Pair<T1,T2> {
    T1 x;
    T2 y;
    Pair(T1 xx, T2 yy) {
        x = xx;
        y = yy;
    }
    T1 getX() { return x; }
    T2 getY() { return y; }
    void setX(T1 xx) { x = xx; }
    void setY(T2 yy) { y = yy; }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}

public class ED213 {
    public static String maxSum(BTree<Integer> t) {
        Pair<String, Integer> cur = new Pair<>("",0), max = new Pair<>("",0);
        Pair<String, Integer> out = maxSumAux(t.getRoot(), cur, max);
        //return out.getX() + " " + out.getY();
        return out.getX();
    }
    public static Pair<String, Integer> maxSumAux(BTNode<Integer> t, Pair<String, Integer> cur, Pair<String, Integer> max) {
        cur.setY(cur.getY() + t.getValue());
        Pair<String, Integer>  left = new Pair<>(cur.getX(), cur.getY());
        Pair<String, Integer> right = new Pair<>(cur.getX(), cur.getY());
        if (t.getLeft() != null)
            left = maxSumAux(t.getLeft(), new Pair<>(cur.getX() + "E", cur.getY()), max);
        if (t.getRight() != null)
            right = maxSumAux(t.getRight(), new Pair<>(cur.getX() + "D", cur.getY()), max);
        if (left.getY() > max.getY()) max = left;
        if (right.getY() > max.getY()) max = right;
        return max;
    }
/* 
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        BTree<Integer> t = LibBTree.readIntTree(in);
        System.out.println(maxSum(t));
    } 
*/
}