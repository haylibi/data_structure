import java.util.Scanner;

class Process {
    String name;
    int time;
    Process(String name, int time) {
        this.name = name;
        this.time = time;
    }

    int getTime() { return time; }
    String getName() { return name; }
    int updateTime(int t) {
        time = time - t;
        return time;
    }
}
public class ED237 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        int N = in.nextInt();
        in.nextLine();
        MyQueue<Process> roundRobin = new LinkedListQueue<>();
        for (int i=0; i<N; i++) {
            roundRobin.enqueue(new Process(in.next(), in.nextInt()));
        }
        int curTime = 0;
        int count = 0;
        while (!roundRobin.isEmpty()) {
            Process next = roundRobin.dequeue();
            if (next.updateTime(T) > 0) {
                roundRobin.enqueue(next);
                curTime += T;
            }
            else {
                curTime += T + next.getTime();
                System.out.println(next.getName() + " " + curTime);
            }
            count++;
        }
        System.out.println(count);
    }   
}