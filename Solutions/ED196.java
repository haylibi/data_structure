import java.util.Scanner;

public class ED196 {
    public static void process(MyQueue<String> q, MyQueue<String> a, MyQueue<String> b) {
        String name,line;
        while (!q.isEmpty()){
            name = q.dequeue();
            line = q.dequeue();
            //System.out.println("name = " + name + " | line = " + line);
            if (line.equals("A")) a.enqueue(name);
            if (line.equals("B")) b.enqueue(name);
            if (line.equals("X")) {
                if (a.size() < b.size()) a.enqueue(name);
                if (b.size() < a.size()) b.enqueue(name);
            }
        }
    }

 /*    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        in.nextLine();
        MyQueue<String> q = new LinkedListQueue<>();
        MyQueue<String> a = new LinkedListQueue<>();
        MyQueue<String> b = new LinkedListQueue<>();
        String inp = "";
        for (int i=0; i<2*N; i++) {
            inp = in.next();
            q.enqueue(inp);
        }
        System.out.println(q);
        System.out.println(a);
        System.out.println(b);
        process(q,a,b);
        System.out.println("q = " + q + "\na = " + a + "\nb = " + b);
    } */
}