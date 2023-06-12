import java.util.Scanner;
import java.util.TreeSet;

class Pair<K,V> {
    K x;
    V y;

    Pair(K a, V b) {
        x = a;
        y = b;
    }

    K getKey() {return x; }
    V getValue() { return y;}
    void setKey(K xx) { x=xx;}
    void setValue(V yy) { y=yy;}
}

public class ED241 {
    public static void maxSub(BSTMapNode<String,Integer> n, Pair<String,Integer> curMax) {
        if (n==null) return;
        if (n.getValue()>curMax.getValue()) {
            curMax.setKey(n.getKey());
            curMax.setValue(n.getValue());
        }
        maxSub(n.getLeft(), curMax);
        maxSub(n.getRight(), curMax);
    }

    public static void printPos(BSTMapNode<String,Pair<Integer,Integer>> n) {
        if (n == null) return;
        printPos(n.getLeft());
        if (2*n.getValue().getKey() >= n.getValue().getValue()) System.out.println(n.getKey());
        printPos(n.getRight());

    }

    public static void doneAll(BSTMapNode<String,TreeSet<String>> n, int number) {
        if (n == null) return;
        doneAll(n.getLeft(), number);
        if (n.getValue().size() == number) System.out.println(n.getKey());
        doneAll(n.getRight(), number);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int F = in.nextInt();
        int N = in.nextInt();
        if (F == 1) {
            BSTree<String> problems = new BSTree<>();
            for (int i=0; i<N; i++) {
                in.next();
                String name = in.next();
                in.nextLine();
                problems.insert(name);
            }
            System.out.println(problems.numberNodes());
        }
        else if (F == 2) {
            BSTMap<String,Integer> students = new BSTMap<>();
            for (int i=0; i<N; i++) {
                String name = in.next();
                in.nextLine();
                Integer v = students.get(name);
                if (v != null) students.put(name, v + 1);
                else students.put(name, 1);
            }
            BSTMapNode<String, Integer> cur = students.getRoot();
            Pair<String,Integer> win = new Pair<>("",0);
            maxSub(cur, win);
            System.out.println(win.getKey() + " " + win.getValue());
        }

        else if (F == 3) {
            BSTMap<String,Pair<Integer,Integer>> students = new BSTMap<>();
            for (int i=0; i<N; i++) {
                String name = in.next();
                Pair<Integer,Integer> v = students.get(name);
                if (v != null) {
                    v.setValue(v.getValue() + 1);
                    in.next();
                    String accepted = in.next();
                    if (accepted.charAt(0) == 'A') v.setKey(v.getKey() + 1);
                }
                else {
                    in.next();
                    String accepted = in.next();
                    if (accepted.charAt(0) == 'A') 
                        students.put(name, new Pair<Integer,Integer>(1,1));
                    else
                        students.put(name, new Pair<Integer,Integer>(0,1));
                }
            }
            System.out.println(students.size());
            printPos(students.getRoot());
        }

        else {
            BSTMap<String, TreeSet<String>> problems = new BSTMap<>();
            BSTree<String> students = new BSTree<>();
            for (int i=0; i<N; i++) {
                String name = in.next();
                students.insert(name);
                String problem = in.next();
                String accepted = in.next();
                if (accepted.charAt(0) != 'A') continue;
                TreeSet<String> v = problems.get(problem);
                if (v != null) {
                    //System.out.println(problem);
                    v.add(name);
                }
                else {
                    TreeSet<String> next = new TreeSet<String>();
                    next.add(name);
                    problems.put(problem, next);
                }
            }
            int nStudents = students.numberNodes();
            doneAll(problems.getRoot(), nStudents);
        }
    }
}