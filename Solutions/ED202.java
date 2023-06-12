import java.util.Scanner;


public class ED202 {
    // Escrever todos as permutacoes do array v[]
    static float permutations(int v[], float[][] values) {
        boolean used[] = new boolean[v.length]; // $i$ esta na permutacao?
        int perm[] = new int[v.length];         // permutacao actual
        float best = 0;
        for (int i=0; i<values[0].length; i++) {
            best+=values[i][(i+1)%values[0].length];
        }
        return goPerm(best, 0, v, used, perm, values); // chamar funcao recursiva
        }
        // Gera todos os subconjuntos a partir da posicao 'cur'
    static float goPerm(float best, int cur, int v[], boolean used[], int perm[], float values[][]) {
    if (cur == v.length) {  // Caso base: terminamos a permutacao
        float soma = 0;
        for (int i=0; i<v.length-1; i++) // Escrever a permutacao
            soma += values[v[perm[i]]][v[perm[i+1]]]; 
        soma += values[v[perm[v.length-1]]][v[perm[0]]];
        if (soma < best) best = soma;
    } else { // Se nao terminamos, continuar a gerar
        for (int i=0; i<v.length; i++) // Tentar todos os elementos
        if (!used[i]) { 
            used[i] = true; perm[cur] = i;
            best = goPerm(best, cur+1, v, used, perm, values);
            used[i] = false;
        }
      }
      return best;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        in.nextLine();
        int[] K = new int[N];
        for (int i=0; i<N; i++) 
            K[i] = i;
        in.nextLine();
        float values[][] = new float[N][];
        for (int i=0; i<N; i++) {
            values[i] = new float[N];
            for (int j=0; j<N; j++)
                values[i][j] = in.nextFloat();
        }
        //System.out.println(K[3] + " -> " + K[2] + " : " + values[2][3]);
        System.out.println(Math.round(permutations(K, values)*100)/100.0);
    }

    
}