import java.util.Scanner;

public class ED239 {
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        BTree<Integer> t = LibBTree.readIntTree(in);
        System.out.println(t.level(5));
    }
}