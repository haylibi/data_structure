import java.util.Scanner;

public class ED121 {
    public static String palin(String text){
        text = text.toLowerCase();
        text = text.replaceAll("\\W","");
        int len = text.length();
        for (int i = 0; i < len; i++){
            if (text.charAt(i) != text.charAt(len - i - 1)){
                return "nao";
            }
        }
        return "sim";
        }
    public static void main(String[] args){
        Scanner obj = new Scanner(System.in);
        int lines = obj.nextInt();
        String[] text = new String[lines+1];
        for (int i = 0; i<=lines; i++){         //tive de criar um ciclo de tamanho lines+1, porque o primeiro obj.nextLine() e todo bugado...
            text[i] = obj.nextLine();
        }
        String[] out = new String[lines+1];
        int k = 1;
        while (k<=lines){
            out[k] = palin(text[k]);
            k += 1;
        }

        System.out.println(lines);
        for (int j = 1; j <= lines; j++){
            System.out.println(out[j]);
        }
    }
}
