import java.util.Scanner;



public class ED199 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int B = in.nextInt();
        int I = in.nextInt();
        in.nextLine();
        String Tesouros = in.nextLine();
        
        char D;
        int maxDir = B;
        int maxEsq = B;
        //System.out.println("B -> " + B);
        for (int i=0; i<I; i++) {
            D = in.next().charAt(0);
            int aux = in.nextInt();
            
            if (D == 'D'){
                B = B + aux;
                if (maxDir < B) maxDir = B + 0;}
            else {
                B = B - aux;
                if (maxEsq > B) maxEsq = B;}
            //System.out.println(D + " " + aux + " | B -> " + B);
        }
        int count = 0;
        //System.out.println(maxEsq + " " + maxDir + " " + B);
        for (int i=maxEsq-1; i<maxDir; i++)
            if (Tesouros.charAt(i) == 'T') count++;
            System.out.println(count);
    }
}