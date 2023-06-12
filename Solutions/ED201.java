import java.util.Scanner;
import java.util.LinkedList;
public class ED201 {
    static int M[]; // duracoes de cada musica

    static int sums(int v[], int D) {
        // array de booleanos para representar o conjunto
        boolean used[] = new boolean[v.length];
        return goSets(0, 0, v, used, D); // chamar funcao recursiva
     }
     // Gera todos os subconjuntos a partir da posicao 'cur'
     static int goSets(int curMax, int cur, int v[], boolean used[], int limite) {
        if (curMax == limite) return curMax;
        if (cur == v.length) {  // Caso base: terminamos o conjunto
           int soma = 0;
           for (int i=0; i<cur; i++) 
                if (used[i]) soma += v[i];
           if (soma <= limite) curMax = Math.max(soma, curMax);
           // if (used[4] && used[7] && used[8]) System.out.println(soma);
        } else {                        // Se nao terminamos, continuar a gerar
           used[cur] = true;            // Subconjuntos que incluem o elemento actual
           curMax = goSets(curMax, cur+1, v, used, limite);   // Chamada recursiva
           used[cur] = false;           // Subconjuntos que nao incluem o el. actual
           curMax = goSets(curMax, cur+1, v, used, limite);   // Chamada recursiva
        }
        return curMax;
     }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        
        int D = in.nextInt();   // duracao da viagem
        int N = in.nextInt();   // numero musicas disponiveis

        M = new int[N];
        for (int i=0; i<N; i++) 
            M[i] = in.nextInt();
        System.out.println(sums(M,D));

    }
}