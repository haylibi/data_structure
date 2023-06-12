/*
Nome: Luis Duarte Carneiro Pinto
Número mecanográfico: 201704025
Sobre ajudas: ----
Explicação da solucao: 
      flag 1 -> Criar uma arvore do tipo "String" onde vou inserindo novos elementos. Caso ja exista um elemento, nao faz nada, se ja existir, adiciono outro.
               No final, e so verificar o numero de elementos desta arvore.

      flag 2 -> Criar uma BSTMap do tipo <String,Integer> onde cada par nos indica o nome do filme e o numero de avaliacoes respetivo.
                No final, basta percorrer a arvore 1 vez e retirar o filme com mais avaliacoes.

      flag 3 -> Criar uma BSTMap do tipo <String,Pair<Integer,Double>> onde um dado par indica-nos o nome do filme e o numero de avaliacoes que teve e a media atual, respetivamente.
                No final, basta-nos percorrer a arvore uma vez, e escolher os filmes com avaliacao >= 5.0

Complexidade:
      flag 1 -> O(n^2) (sendo n o numero de filmes diferentes. Pois, para inserir n strings numa arvore ordenada, no pior caso, as strings estao por ordem (crescente ou
                    decrescente) e isso faz com que seja perciso percorrer todas strings ja inseridas, logo 1+2+3+4+5+...+n, ou seja, O(n^2))

      flag 2 -> O(n^2) (por causa da mesma situacao descrita em flag 1) Fora a insercao de elementos, so ha complexidade O(n), uma vez que temos de percorrer todos os 
                nos da 

      flag 3 -> O(n^2) (por causa da mesma situacao descrita em flag 1) Fora a insercao, fica como na complexidade de flag 2
*/



import java.util.Scanner;
class Pair<T1,T2> {
    T1 x;
    T2 y;

    Pair(T1 xx, T2 yy) { x=xx; y=yy;}
}
public class ED234 {
    static Pair<String,Integer> maxRev(BSTMapNode<String,Integer> n, Pair<String,Integer> curMax) {
        if (n == null) return curMax;
        if (n.getValue() > curMax.y) {
            curMax.x = n.getKey();
            curMax.y = n.getValue();
        }
        curMax = maxRev(n.getLeft(),curMax);
        curMax = maxRev(n.getRight(), curMax);
        return curMax;
    }

    static void posAverage(BSTMapNode<String,Pair<Integer,Double>> n) {
        if (n==null) return;
        posAverage(n.getLeft());
        if (n.getValue().y >= 5.0) System.out.println(n.getKey());
        posAverage(n.getRight());
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int flag = in.nextInt();
        int N = in.nextInt();
        if (flag==1) {
            BSTree<String> f1Movies = new BSTree<>();
            for (int i=0; i<N; i++) {
                f1Movies.insert(in.next());
                in.nextLine();
            }
            System.out.println(f1Movies.numberNodes());
            return;
        }

        else if (flag==2) {
            BSTMap<String,Integer> f2Movies = new BSTMap<>();
            for (int i=0; i<N; i++) {
                String name = in.next();
                Integer k = f2Movies.get(name);
                if (k==null) f2Movies.put(name,1);
                else f2Movies.put(name, k+1);
                in.nextLine();
            }
            Pair<String,Integer> out = maxRev(f2Movies.getRoot(),new Pair<String,Integer>("",0));
            System.out.println(out.x + " " + out.y);
        }
        else {
            BSTMap<String,Pair<Integer,Double>> f3Movies = new BSTMap<>();
            for (int i=0; i<N; i++) {
                String name = in.next();
                int rev = in.nextInt();
                Pair<Integer,Double> movie = f3Movies.get(name);
                if (movie == null) { f3Movies.put(name, new Pair<Integer,Double>(1,(double) rev)); }
                else {
                    movie.y = movie.y*(movie.x) + rev;
                    movie.x += 1;
                    movie.y = movie.y/movie.x;
                }
            }
            posAverage(f3Movies.getRoot());
        }
    }
}