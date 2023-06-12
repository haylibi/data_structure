import java.util.Scanner;

public class ED236 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        SinglyLinkedList<Integer> test1 = new SinglyLinkedList<>();
        test1.addFirst(10);
        test1.addFirst(8);
        test1.addFirst(6);
        test1.addFirst(4);
        test1.addFirst(2);
        SinglyLinkedList<Character> test2 = new SinglyLinkedList<>();
        test2.addFirst('e');
        test2.addFirst('d');
        test2.addFirst('c');
        test2.addFirst('b');
        test2.addFirst('a');

        System.out.println(test1.cut(2,3));
        test2.shift(0);
        System.out.println(test2);
    }   
}