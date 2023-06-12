import java.util.Scanner;

public class ED232{
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int size;
        for (int i=0; i<N; i++) {
            String tipo = in.next();
            in.nextLine();
            if (tipo.equals("char")){
                SinglyLinkedList<Character> testChar = new SinglyLinkedList<>();
                size = in.nextInt();
                for (int j=0; j<size; j++)  testChar.addLast(in.next().charAt(0));

                SinglyLinkedList<Character> toRemoveChar = new SinglyLinkedList<>();
                size = in.nextInt();
                for (int j=0; j<size; j++)  toRemoveChar.addLast(in.next().charAt(0));
                
                System.out.println("no inicio list = " + testChar + " | size = " + testChar.size());
                System.out.println("chamada a list.remove(" + toRemoveChar + ")");
                testChar.remove(toRemoveChar);
                System.out.println("no final list = " + testChar + " | size = " + testChar.size());
            }

            if (tipo.equals("int")){
                SinglyLinkedList<Integer> testInt = new SinglyLinkedList<>();
                size = in.nextInt();
                for (int j=0; j<size; j++)  testInt.addLast(in.nextInt());

                SinglyLinkedList<Integer> toRemoveInt = new SinglyLinkedList<>();
                size = in.nextInt();
                for (int j=0; j<size; j++)  toRemoveInt.addLast(in.nextInt());

                System.out.println("no inicio list = " + testInt + " | size = " + testInt.size());
                System.out.println("chamada a list.remove(" + toRemoveInt + ")");
                testInt.remove(toRemoveInt);
                System.out.println("no final list = " + testInt + " | size = " + testInt.size());
            }

            if (tipo.equals("string")) {
                SinglyLinkedList<String> testString = new SinglyLinkedList<>();
                size = in.nextInt();
                for (int j=0; j<size; j++)  testString.addLast(in.next());

                SinglyLinkedList<String> toRemoveString = new SinglyLinkedList<>();
                size = in.nextInt();
                for (int j=0; j<size; j++)  toRemoveString.addLast(in.next());
                
                System.out.println("no inicio list = " + testString + " | size = " + testString.size());
                System.out.println("chamada a list.remove(" + toRemoveString + ")");
                testString.remove(toRemoveString);
                System.out.println("no final list = " + testString + " | size = " + testString.size());
            }

            System.out.println("--------------------------------");
        }

    }
}