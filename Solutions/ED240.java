import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class ED240 {
    static void getMin(BTNode<Integer> n, int[] curMin) {
        if (n==null) return;
        if (n.getValue() < curMin[0]) {
            curMin[0] = n.getValue();
            curMin[1] = 1;
        }
        else if(n.getValue() == curMin[0]) 
            curMin[1]++;
        getMin(n.getLeft(), curMin);
        getMin(n.getRight(), curMin);
    }
    public static String[] paths(BTree<Integer> t) {
        BTNode<Integer> cur = t.getRoot();
        String[] output = new String[0];
        if (cur == null) return output;
        int[] min = {cur.getValue(), 0};
        getMin(cur, min);   // min[0] -> minimo da arvore, min[1] -> quantas vezes ha na arvore
        //System.out.println(min[0] + " " + min[1]);
        output = new String[min[1]];
        int nextPath = 0;
        Set<String> visited = new TreeSet<>();
        if (cur.getValue() == min[0]) {
            output[0] = "R";
            visited.add("R");
            visited.add("");
            nextPath = 1;
        }
        pathsAux(t, cur, min[0], output, visited, nextPath);
        return output;
    }

    static boolean pathsAux(BTree<Integer> t, BTNode<Integer> n, int min, String[] output, Set<String> visited, int curPath) {
        if (n == null || curPath >= output.length) { return false; }
        if (output[curPath] == null) output[curPath] = "";
        if (n.getValue() == min && !visited.contains(output[curPath])) {
            visited.add(output[curPath]);
            pathsAux(t, t.getRoot(), min, output, visited, curPath+1);
            return true; 
        }
        String path = output[curPath];
        output[curPath] = path + "E";
        if (pathsAux(t, n.getLeft(), min, output, visited, curPath)) return true;
        output[curPath] = path + "D";
        if (pathsAux(t, n.getRight(), min, output, visited, curPath)) return true;
        return false;
    }

    

  /*   public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        BTree<Integer> t = LibBTree.readIntTree(in);
        String[] paths = paths(t);
        for (String i: paths)
            System.out.print(i + ",");
        System.out.println();
    } */
}