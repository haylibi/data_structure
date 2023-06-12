import java.util.Scanner;

public class ED195 {
    public static boolean balanced(String s) {
        MyStack<Character> abertura = new LinkedListStack<>();
        MyStack<Character> fecho = new LinkedListStack<>();
        int size = s.length();
        if (size%2 != 0) {return false;}
        for (int i=0; i<size; i++) {
            if (s.charAt(i) == ')') {
                if (abertura.pop() != '(') {
                    return false;
                }
            }
            if (s.charAt(i) == ']') {
                if (abertura.pop() != '[') {
                    return false;
                }
            }
            if (s.charAt(i) == '(' || s.charAt(i) == '[') {
                abertura.push(s.charAt(i));
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        in.nextLine();
        String s = "";
        for (int i=0; i<N; i++) {
            s = in.next();
            System.out.println(balanced(s));
        }
        
    }
}