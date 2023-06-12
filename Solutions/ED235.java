import java.util.Scanner;

public class ED235 {

    static void line(int k, char c) {
        for (int i=0; i<k; i++)
            System.out.print(c);
    }
    
    static void drawTriangle(int n) {
        for (int i=1; i<=n; i++) {
            line(i,'#');
            line(n-i,'.');
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        for (int i=0; i<N; i++)
            drawTriangle(in.nextInt());
    }  
}