import java.util.Scanner;
class Pair<T1,T2> {
    private T1 key;
    private T2 value;

    Pair(T1 key, T2 value) {
        this.key = key;
        this.value = value;
    }

    public T1 getKey() {
        return key;
    }

    public T2 getValue() {
        return value;
    }
    
}

public class ED007 {
    public static Pair<Boolean, Integer> balanced(String s) {
        MyStack<Character> abertura = new LinkedListStack<>();
        int size = s.length();
        for (int i=0; i<size; i++) {
            if (s.charAt(i) == ')') {
                if (abertura.size() == 0) {
                    Pair<Boolean, Integer> output = new Pair<>(false,i);
                    return output;
                }
                if (abertura.pop() != '(') {
                    Pair<Boolean, Integer> output = new Pair<>(false,i);
                    return output;
                }
            }
            if (s.charAt(i) == ']') {
                if (abertura.size() == 0) {
                    Pair<Boolean, Integer> output = new Pair<>(false,i);
                    return output;
                }
                if (abertura.pop() != '[') {
                    Pair<Boolean, Integer> output = new Pair<>(false,i);
                    return output;
                }
            }
            if (s.charAt(i) == '(' || s.charAt(i) == '[') {
                abertura.push(s.charAt(i));
            }
        }
        Pair<Boolean, Integer> output = new Pair<>(true,0);
        if (abertura.size()!=0) output = new Pair<>(false, -1);
        return output;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        in.nextLine();
        String s = "";
        Pair<Boolean, Integer> tmp;
        for (int i=0; i<N; i++) {
            s = in.nextLine();
            tmp = balanced(s);
            if (!tmp.getKey()) {
                if (tmp.getValue() == -1) System.out.println("Ficam parenteses por fechar");
                else System.out.println("Erro na posicao " + tmp.getValue());
            }
            else System.out.println("Expressao bem formada");
        }
        
    }
}