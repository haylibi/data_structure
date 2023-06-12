
public class ED197 {
    public static MyQueue<Integer> merge(MyQueue<Integer> a, MyQueue<Integer> b) {
        MyQueue<Integer> out = new LinkedListQueue<>();
        while(!(a.isEmpty() || b.isEmpty())) {
            if (a.first()<b.first()) {
                out.enqueue(a.dequeue());
            }
            else out.enqueue(b.dequeue());
        }
        if (a.isEmpty()) {
            while (!(b.isEmpty())) out.enqueue(b.dequeue());
            return out;
        }
        while (!(a.isEmpty())) out.enqueue(a.dequeue());
        return out;

    }
/*     public static void main(String[] args) {
        MyQueue<Integer> a = new LinkedListQueue<>();
        MyQueue<Integer> b = new LinkedListQueue<>();
        a.enqueue(1);
        a.enqueue(2);
        a.enqueue(4);
        a.enqueue(5);
        b.enqueue(2);
        b.enqueue(3);
        b.enqueue(5);
        b.enqueue(6);
        b.enqueue(8);
        System.out.println("a = " + a + "\nb = " + b);
        System.out.println("merge = " + merge(a, b));
    } */
}