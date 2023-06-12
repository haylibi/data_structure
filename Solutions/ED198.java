import java.util.Scanner;


class ED198 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] best = new int[n];
        best[0] = in.nextInt();
        for (int i=1; i<n; i++) {
            int next = in.nextInt();
            if (best[i-1] < 0) best[i] = next;
            else best[i] = best[i-1] + next;
        }
        int maxSoFar = best[0];
        for (int i=1; i<n; i++)
            if (best[i]>maxSoFar) maxSoFar = best[i];
        System.out.println(maxSoFar);
    }
}