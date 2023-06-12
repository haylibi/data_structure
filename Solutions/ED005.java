import java.util.Scanner;

public class ED005{
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        MyStack<Integer> s = new LinkedListStack<>();
        String tmp = "";
        String[] tmp2;
        int a,b;
        int N = in.nextInt();
        in.nextLine();
/*         for (int j=0; j<N; j++) {
            tmp = in.nextLine();
            s = new LinkedListStack<>();
            for (int i=0; i<tmp.length(); i++) {
                System.out.println(s);
                if (tmp.charAt(i) == ' ') continue;
                if ('0'<= tmp.charAt(i) && tmp.charAt(i) <= '9') {
                    s.push(tmp.charAt(i) - '0');
                }
                else {
                    if (s.size() < 2) {
                        s = new LinkedListStack<>();
                        break;
                    }
                    a = s.pop();
                    b = s.pop();
                    if (tmp.charAt(i) == '+') s.push(b + a);
                    if (tmp.charAt(i) == '-') s.push(b - a);
                    if (tmp.charAt(i) == '*') s.push(b * a);
                    if (tmp.charAt(i) == '/') s.push(b / a);
                }
            } */
            for (int j=0; j<N; j++) {
                tmp = in.nextLine();
                tmp2 = tmp.split(" ", 0);
                s = new LinkedListStack<>();
                for (int i=0; i<tmp2.length; i++) {
                    if ('0'<= tmp2[i].charAt(0) && tmp2[i].charAt(0) <= '9') {
                        s.push(Integer.parseInt(tmp2[i]));
                    }
                    else {
                        if (s.size() < 2) {
                            s = new LinkedListStack<>();
                            break;
                        }
                        a = s.pop();
                        b = s.pop();
                        if (tmp2[i].equals("+")) s.push(b + a);
                        if (tmp2[i].equals("-")) s.push(b - a);
                        if (tmp2[i].equals("*")) s.push(b * a);
                        if (tmp2[i].equals("/")) s.push(b / a);
                    }
                }
            if (s.size() != 1) System.out.println("Expressao Incorrecta");
            else System.out.println(s.pop());
        }
        
    }
}