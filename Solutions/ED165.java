import java.util.Scanner;

public class ED165 {    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        BSTree<Integer> sumsSet = new BSTree<>();
        int N = in.nextInt();
        int[] intSet = new int[N];
        for (int i=0; i<N; i++) {
            intSet[i] = in.nextInt();
        }

        for (int i=0; i<N; i++) 
            for (int j=0; j<N; j++)
                sumsSet.insert(intSet[i] + intSet[j]);
        int P = in.nextInt();
        for (int i=0; i<P; i++) {
            int p = in.nextInt();
            boolean val = sumsSet.contains(p);
            if (val) System.out.println(p + ": sim");
            else System.out.println(p + ": nao");
        }
    }
}