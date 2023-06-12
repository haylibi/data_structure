import java.util.Scanner;

public class ED194 {
    
    public static void reverse(MyStack<Integer> s, int n) {
        int[] tmp = new int[n];
        for (int i=0; i<n; i++) {
            tmp[i] = s.pop();
        }
        for (int i=0; i<n; i++) {
            s.push(tmp[i]);
        }
    }
}