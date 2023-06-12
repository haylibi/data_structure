import java.util.Scanner;
import java.util.Arrays;

public class ED183{
    public static void med(String str, int len){
        String[] array = str.split(" ",0);
        int[] intArray = Arrays.stream(array).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(intArray); 
        int sum = 0;
        for (int i = 0; i < len; i++){
            sum += Integer.parseInt(array[i]);
        }
        System.out.println(String.format("%.2f",(float) sum / (float) len));
        System.out.println(intArray[len-1]-intArray[0]);
    }

    public static void main(String[] args){
        Scanner obj = new Scanner(System.in);
        int k = obj.nextInt();
        String empty = obj.nextLine();      //tem de haver um .nextLine() antes do input em string, por algum motivo
        String n = obj.nextLine();
        med(n,k);
    }




}