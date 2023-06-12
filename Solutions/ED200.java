import java.util.Scanner;


public class ED200 {
    static int rows,cols;
    static char m[][];
    static boolean[][] visited;

    static int t(int y, int x) {
        if (y<0 || y>=rows || x<0 || x>=cols) return 0; // Caso base: fora dos limites
        if (visited[y][x]) return 0;  // Caso base: celula ja visitada
        if (m[y][x] == '.') return 0; // Caso base: celula vazia
        int count = 1;        // celula nao vazia
        visited[y][x] = true; // marcar como visitada
        count += t(y-1, x);   // Adicionando celulas nao vizinhas
        count += t(y+1, x);
        count += t(y, x+1);
        count += t(y, x-1);
        count += t(y+1, x+1);
        count += t(y+1, x-1);
        count += t(y-1, x-1);
        count += t(y-1, x+1);
        return count;
     }

     public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();   //numero de casos a considerar
        
        for (int p=0; p<N; p++) {
            // Leitura de uma matriz de caracteres
            rows = in.nextInt();
            cols = in.nextInt();
            m = new char[rows][cols];
            visited = new boolean[rows][cols];
            for (int i=0; i<rows; i++)
                m[i] = in.next().toCharArray();

            // Teste do metodo t(y,x)
            int max = 0;
            for (int y=0; y<rows; y++) 
                for (int x=0; x<cols; x++) {
                    max = Math.max(max, t(y,x));
                }    
            System.out.println(max);
            }
        }
}