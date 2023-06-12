import java.util.Scanner;

public class ED238 {

    static int max(int[] zeros, int k) {
        int sizeZeros = zeros[0];
        int curMax = 0;
        for (int i=1; i<=sizeZeros - k; i++) {
            int diff = zeros[i+1+k] - zeros[i]  ;
            if (diff-1 > curMax)
                curMax = diff-1;
        }
        return curMax;
    }

    static int[] getZeros(String num) {
        int size = num.length();
        int[] out = new int[size+1];
        out[0] = size;
        int cur = 1;
        for (int i=0; i<size; i++) {
            if (num.charAt(i) == '0') {
                out[cur] = i;
                cur ++;
            }
        }
        out[0] = cur;
        return out;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        in.nextLine();
        String num = in.nextLine();
        num = "0" + num + "0";
        int[] zeros = getZeros(num);
        int Q = in.nextInt();
        /* for (int i=1; i<zeros[0]; i++) {
            System.out.print(zeros[i] + ",");
        }
        System.out.println(); */
        for (int i=0; i<Q; i++) {
            int K = in.nextInt();
            System.out.println(max(zeros,K));
        }
    }   
}