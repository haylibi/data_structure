import java.util.Scanner;
class Aviao {
    String nome;
    int tempoPrevisto;

    Aviao(String name, int time) {
        nome = name;
        tempoPrevisto = time;
    }
    public int getTime(){return tempoPrevisto;}
    public String getName(){return nome;}
}

class Pista {
    MyQueue<Aviao> levantar;
    MyQueue<Aviao> aterrar;
    MyQueue<Aviao> levantado;
    MyQueue<Aviao> aterrado;
    int currentTime;

    Pista() {
        levantar = new LinkedListQueue<>();
        aterrar = new LinkedListQueue<>();
        levantado = new LinkedListQueue<>();
        aterrado = new LinkedListQueue<>();
        currentTime = 0;
    }

    // Acrescentar avioes a pista
    public void addLevantar(Aviao a) {levantar.enqueue(a);}
    public void addAterrar(Aviao a) {aterrar.enqueue(a);}

    public Aviao next(){
        currentTime++;
        Aviao nextLevantar = levantar.first();
        Aviao nextAterrar = aterrar.first();
        int esperarAterrar, esperarLevantar;
        if (nextLevantar != null && nextAterrar != null) {
            if (nextLevantar.getTime() >= nextAterrar.getTime()) {
                esperarAterrar = currentTime - nextAterrar.getTime();
                if (esperarAterrar < 0) {
                    currentTime = nextAterrar.getTime();
                    esperarAterrar = 0;
                }
                aterrado.enqueue(new Aviao(aterrar.dequeue().getName(), esperarAterrar));
                return nextAterrar;
            }
            esperarLevantar = currentTime - nextLevantar.getTime();
            if (esperarLevantar < 0) {
                currentTime = nextLevantar.getTime();
                esperarLevantar = 0;
            }
            levantado.enqueue(new Aviao(levantar.dequeue().getName(), esperarLevantar));
            return nextLevantar;
        }
        if (nextLevantar != null) {
            esperarLevantar = currentTime - nextLevantar.getTime();
            if (esperarLevantar < 0) {
                currentTime = nextLevantar.getTime();
                esperarLevantar = 0;
            }
            levantado.enqueue(new Aviao(levantar.dequeue().getName(), esperarLevantar));
            return nextLevantar;
        }
        if (nextAterrar != null) {
            esperarAterrar = currentTime - nextAterrar.getTime();
            if (esperarAterrar < 0) {
                currentTime = nextAterrar.getTime();
                esperarAterrar = 0;
            }
            aterrado.enqueue(new Aviao(aterrar.dequeue().getName(), esperarAterrar));
            return nextAterrar;
        }
        return null;
    }

}

public class ED029 {

    public static void output(Pista P){
        MyQueue<Aviao> aterrado = P.aterrado;
        MyQueue<Aviao> levantado = P.levantado;
        Aviao next = levantado.dequeue();
        while (levantado.size() != 0) {
            System.out.println(next.getName() + " " + next.getTime());
            next = levantado.dequeue();
        }
        System.out.println(next.getName() + " " + next.getTime());
        next = aterrado.dequeue();
        while (aterrado.size() != 0) {
            System.out.println(next.getName() + " " + next.getTime());
            next = aterrado.dequeue();
        }
        System.out.println(next.getName() + " " + next.getTime());
    }


    public static void main(String[] agrs) {
        Scanner in = new Scanner(System.in);

        int C = in.nextInt();
        in.nextLine();
        
        for (int i=0; i<C; i++) {
            int L = in.nextInt();
            int A = in.nextInt();
            Pista P = new Pista();
            in.nextLine();
            for (int j=0; j<L; j++) {
                String nome = in.next();
                int desejado = in.nextInt();
                Aviao temp = new Aviao(nome, desejado);
                P.addLevantar(temp);
                in.nextLine();
            }
            for (int j=0; j<A; j++) {
                String nome = in.next();
                int desejado = in.nextInt();
                Aviao temp = new Aviao(nome, desejado);
                P.addAterrar(temp);
                if (j != A-1) in.nextLine();
            }
            while (P.next() != null) {}
            System.out.println(L + " " + A);
            output(P);
        } 
    }
}