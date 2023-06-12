import java.util.Scanner;

public class ED006 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        in.nextLine();
        CircularLinkedList<String> pessoas = new CircularLinkedList<>();
        int count = 0;
        String tmp = "";
        int nPessoas = 0;
        for (int i=0; i<N; i++){
            count = in.nextLine().split(" ",0).length;              // guarda o numero de palavras em cada frase (unica coisa importante da frase)
            //System.out.println("count = " + count);
            nPessoas = in.nextInt();
            for (int k=0; k<nPessoas; k++){
                tmp = in.next();
                pessoas.addLast(tmp);
            }
            //System.out.println(pessoas);
            while (pessoas.size() > 1) {
                for (int j=1; j<count; j++) {
                    pessoas.rotate();
                }
                pessoas.removeFirst();
                //System.out.println(pessoas);
            }
            if (pessoas.getFirst().equals("Carlos")) {
                System.out.println("O Carlos nao se livrou");
            }
            else {
                System.out.println("O Carlos livrou-se (coitado do " + pessoas.getFirst() + "!)");
            }
            pessoas.removeFirst();
            if (in.hasNextLine()) in.nextLine();
        }
    }
}