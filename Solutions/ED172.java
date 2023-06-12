import java.util.Scanner;

public class ED172 {
    public static void main(String[] args) {
        BSTMap<String,Integer> dic = new BSTMap<>();
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            String word = in.nextLine();
            for (String i : word.split(" ")) {
                if (i.equals("\n")) continue;
                Integer val = dic.get(i);
                if (val != null) dic.put(i, val + 1);
                else dic.put(i, 1);
            }
        }

        for (String i : dic.keys()) {
            System.out.println(i + ": " + dic.get(i));
        }
    }
}