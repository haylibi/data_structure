import java.util.Scanner;

class Person implements Comparable<Person> {
    String name;
    private int price;

    Person(String n, int p) { name = n; price = p;}

    public int getPrice() { return price; }

    public int compareTo(Person p) {
        return p.getPrice() - price;
    }

    public String toString() {
        return price + " " + name;
    }
}
public class ED215 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        MinHeap<Person> offers = new MinHeap<>(10000);
        int N = in.nextInt();
        for (int i=0; i<N; i++) {
            if (in.next().equals("OFERTA")) {
                offers.insert(new Person(in.next(), in.nextInt()));
                in.nextLine();
            }
            else {
                System.out.println(offers.removeMin());
            }
        }
    }
}