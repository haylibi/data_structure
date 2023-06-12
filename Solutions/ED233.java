import java.util.Scanner;
 
public class ED233 {
   public static BTree<Integer> readIntTree(Scanner in) {
      BTree<Integer> t = new BTree<Integer>();
      t.setRoot(readIntNode(in));
      return t;
   }
   private static BTNode<Integer> readIntNode(Scanner in) {
      String s = in.next();
      if (s.equals("N")) return null;
      Integer value = Integer.parseInt(s);
      BTNode<Integer> left = readIntNode(in);
      BTNode<Integer> right = readIntNode(in);
      return new BTNode<Integer>(value, left, right);
   }

   public static void main(String[] args) {
      Scanner in = new Scanner(System.in);
      int N = in.nextInt();
      int flag = in.nextInt();
      if (flag == 0) {
         for (int i=0; i<N; i++) {
            BTree<Integer> t = readIntTree(in);
            System.out.println("t.internal() = " + t.internal());
         }
      }
      if (flag == 1) {
         for (int i=0; i<N; i++) {
            BTree<Integer> t = readIntTree(in);
            int cut = in.nextInt();
            System.out.print("t.cut(" + cut + ") = ");
            t.cut(cut);
            t.printPreOrder();
         }
      }
      if (flag == 2) {
         for (int i=0; i<N; i++) {
            BTree<Integer> t = readIntTree(in);
            int[] maxLevels = t.maxLevel();
            System.out.println("[" + maxLevels[0] + ", " + maxLevels[1] + "]");
         }
      }
   }
} 


/*
Nome: Luis Duarte Carneiro Pinto
Número mecanográfico: 201704025
Sobre ajudas: ----
Explicação da solucao: 
      internal() ->  Se um no nao e null entao retornar 1 + os internal das subarvores desse no, se um no e null retornar 0.

      cut(int d) ->  Se d<=0 ou o no e null, retornar logo vazio. Se d==1, entao tirar os nos filhos desse no fazendo setleft(null), setright(null). Se d>1, fazer cut das subarvores esq e dir com profundidade d-1.

      maxLevel() ->  Criar um array int[] levels, de tamanho depth()+1 que vai guardar o numero de nos em cada profundidade. para guardar o numero de nos
                     em cada profundidade fazer: funcao com parametros (int[] levels, int curDepth, Node curNode) que atualiza o levels, atualizando o valor de
                     levels[curDepth] caso curNode != null. Chamar a mesma funcao recursivamente para os filhos de curNode. 

Complexidade:
      internal() -> O(n), temos de visitar todos os nos da arvore. (sendo n o numero de nos).

      cut(int d) ->  O(d), so depende da profundidade... Percorrer a arvore ate chegar a profundidade desejada. (isto caso d>0, nao vou abordar quando d<=0)
      
      maxLevel() -> O(n), uma chamada a funcao depth() (O(n)), passar uma vez pela arvore toda (com maxLevelsAux), logo O(n), 
                     e um ciclo de de 0 ate log(n), logo log(n)... Entao a complexidade e O(n+n+log(n)) = O(n)
*/

class BTree<T> {   
   private BTNode<T> root; // raiz da arvore

   // Construtor
   BTree() {
      root = null;
   }
   // Getter e Setter para a raiz
   public BTNode<T> getRoot() {return root;}
   public void setRoot(BTNode<T> r) {root = r;}

   // Verificar se arvore esta vazia
   public boolean isEmpty() {
      return root == null;
   }

   // --------------------------------------------------------

   // Numero de nos da arvore   
   public int numberNodes() {
      return numberNodes(root);
   }

   private int numberNodes(BTNode<T> n) {
      if (n == null) return 0;
      return 1 + numberNodes(n.getLeft()) + numberNodes(n.getRight());
   }

   // --------------------------------------------------------

   // Altura da arvore
   public int depth() {
      return depth(root);
   }

   private int depth(BTNode<T> n) {
      if (n == null) return -1;
      return 1 + Math.max(depth(n.getLeft()), depth(n.getRight()));
   }

   // --------------------------------------------------------
   
   // O elemento value esta contido na arvore?
   public boolean contains(T value) {
      return contains(root, value);
   }

   private boolean contains(BTNode<T> n, T value) {
      if (n==null) return false;
      if (n.getValue().equals(value)) return true;
      return contains(n.getLeft(), value) || contains(n.getRight(), value);
   }

   // --------------------------------------------------------

   // Imprimir arvore em PreOrder
   public void printPreOrder() {
      System.out.print("PreOrder:");
      printPreOrder(root);
      System.out.println();
   }

   private void printPreOrder(BTNode<T> n) {
      if (n==null) return;
      System.out.print(" " + n.getValue() );
      printPreOrder(n.getLeft());
      printPreOrder(n.getRight());
   }

   // --------------------------------------------------------
   
   // Imprimir arvore em InOrder
   public void printInOrder() {
      System.out.print("InOrder:");
      printInOrder(root);
      System.out.println();
   }

   private void printInOrder(BTNode<T> n) {
      if (n==null) return;
      printInOrder(n.getLeft());
      System.out.print(" " + n.getValue());
      printInOrder(n.getRight());
   }

   // --------------------------------------------------------

   // Imprimir arvore em PostOrder
   public void printPostOrder() {
      System.out.print("PostOrder:");
      printPostOrder(root);
      System.out.println();
   }

   private void printPostOrder(BTNode<T> n) {
      if (n==null) return;
      printPostOrder(n.getLeft());
      printPostOrder(n.getRight());
      System.out.print(" " + n.getValue());
   }

   // --------------------------------------------------------

   // Imprimir arvore numa visita em largura (usando TAD Fila)
   public void printBFS() {
      System.out.print("BFS:");
      
      MyQueue<BTNode<T>> q = new LinkedListQueue<BTNode<T>>();
      q.enqueue(root);
      while (!q.isEmpty()) {
         BTNode<T> cur = q.dequeue();
         if (cur != null) {
            System.out.print(" " + cur.getValue());
            q.enqueue(cur.getLeft());
            q.enqueue(cur.getRight());
         }
      }
      System.out.println();
   }

   // --------------------------------------------------------
   
   // Imprimir arvore numa visita em profundidade (usando TAD Pilha)
   public void printDFS() {
      System.out.print("DFS:");
      
      MyStack<BTNode<T>> q = new LinkedListStack<BTNode<T>>();
      q.push(root);
      while (!q.isEmpty()) {
         BTNode<T> cur = q.pop();
         if (cur != null) {
            System.out.print(" " + cur.getValue());
            q.push(cur.getLeft());
            q.push(cur.getRight());
         }
      }
      System.out.println();
   }

   // ----------------------------------------------------------------------------

   //                                   ED233

   // ----------------------------------------------------------------------------

   public int internal() { return internal(root); }
   public int internal(BTNode<T> n) {
        if (n == null) return 0;
        if (n.getLeft() == null && n.getRight() == null) return 0;
        return 1 + internal(n.getLeft()) + internal(n.getRight());
    }
    


   public void cut(int d) {
      if (d<=0) {
         root = null;
      }
      cut(d, root); 
   }
   public void cut (int d, BTNode<T> n) {
      if (n==null) return;
      if (d == 1) {
         n.setLeft(null);
         n.setRight(null);
         return;
      }
      cut(d-1, n.getLeft());
      cut(d-1, n.getRight());
   }


   public int[] maxLevel() { 
      int[] levels = new int[depth() + 1];
      maxLevelAux(root, levels, 0); 
      int a=0, b=0;
      for (int i=0; i<levels.length; i++) {
         if (levels[i] > a) {
            a = levels[i];
            b = 1;
            continue;
         }
         if (levels[i] == a) { b++; }
      }
      int[] out = {a,b};
      return out;
   }
   public void maxLevelAux(BTNode<T> n, int[] d, int curDepth) {
      if (n == null) return;
      d[curDepth] ++;
      maxLevelAux(n.getLeft() , d, curDepth+1);
      maxLevelAux(n.getRight(), d, curDepth+1);
      return;
    }
}
