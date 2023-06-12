import java.util.Scanner;



public class ED222 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int K = in.nextInt();
        int T = in.nextInt();
        int[] P = new int[N];
        int[] valido = new int[N-K+1];      // em valido[i] diz-nos quantas localizacoes tem prof>=T de i ate i+k
        valido[0] = 0;
        int count = 0;
        for (int i=0; i<N; i++)
            P[i] = in.nextInt();
        for (int i=0; i<K; i++)
            if (P[i]>=T) valido[0]++;
        if (valido[0]>=(K+1)/2) 
            count++;
        for (int i=1; i<=N-K; i++) {
            if (P[i-1] >= T) valido[i] = valido[i-1] - 1;
            else valido[i] = valido[i-1];
            if (P[i+K-1] >= T) valido[i]++;
            if (valido[i]>=(K+1)/2) 
                count++;
        }
        System.out.println(count);
        /* for (int i=0; i<N-K+1; i++) 
            System.out.print(valido[i] + ", ");
        System.out.println(); */
    }
}