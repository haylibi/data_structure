import java.util.Scanner;
import java.util.TreeMap;

public class ED242 {
    static int cols,rows;
    static int numberOfLakes;

    static TreeMap<Integer,Integer> nLakes(char[][] lake) {
        boolean[][] visited = new boolean[rows][cols];
        TreeMap<Integer,Integer> lakes = new TreeMap<>();
        numberOfLakes = 0;
        for (int i=0; i<rows; i++) {
            for (int j=0; j<cols; j++) {
                if (lake[i][j] == '.' && !visited[i][j]) {
                    int k = count(lake,i,j,visited, 0);
                    if (k > 0) {
                        Integer get = lakes.get(k);
                        if (get == null) lakes.put(k,1);
                        else lakes.put(k, get+1);
                        numberOfLakes++;
                    }
                }
            }
        }
        return lakes;
    }

    static int count(char[][] lake, int i, int j, boolean[][] visited, int curCount) {
        if (lake[i][j]=='.' && (i >= rows-1 || j >= cols-1 || i<=0 || j<=0)) return -1;
        if (visited[i][j] || lake[i][j] == '#') return 0;
        visited[i][j] = true;
        int up =    count(lake, i  , j+1, visited, curCount);
        int down =  count(lake, i  , j-1, visited, curCount);
        int left =  count(lake, i-1, j  , visited, curCount);
        int right = count(lake, i+1, j  , visited, curCount);
        if (up<0 || down <0 || left <0 || right<0) return -1;
        return 1 + up + down + left + right;
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int K = in.nextInt();
        char[][] lake = new char[100][100];
        int count = 0;
        in.nextLine();
        while (in.hasNextLine()) {
            lake[count] = in.nextLine().toCharArray();
            count++;
        }
        cols = lake[0].length;
        rows = count;
        TreeMap<Integer,Integer> lakes = nLakes(lake);
        //System.out.println(lakes);
        count = 0;
        while(numberOfLakes > K) {
            int next = lakes.firstKey();
            int nl = lakes.get(next);
            if (nl==1) lakes.remove(next);
            else lakes.put(next, nl - 1);
            count += next;
            numberOfLakes--;
        }
        //System.out.println(lakes);
        System.out.println(count);
    }
}