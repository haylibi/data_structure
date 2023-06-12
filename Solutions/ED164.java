import java.util.Scanner;

public class ED164 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        BSTree<String> nomes = new BSTree<>();
        int N = in.nextInt();
        for (int i=0; i<N; i++) {
            nomes.insert(in.next());
        }
        System.out.println(nomes.numberNodes());
    }
}