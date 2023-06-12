import java.util.Scanner;
class Cliente {
    private String name;    //nome do cliente
    private int arrival, products;       //tempo de chegada & numero de produtos do cliente
    Cliente(String nome, int tempo, int produtos) {
        name = nome;
        arrival = tempo;
        products = produtos;
    }
    public String getName() {return name;}
    public int getArrival() {return arrival;}
    public int getProducts() {return products;}
    public String toString(){return getName();}
}

class Caixa{
    private MyQueue<Cliente> fila;
    private int processTime, currentTime, totalProducts, nclientes, timeLeft;
    private Cliente last;
    
    Caixa(int time) {
        fila = new LinkedListQueue<Cliente>();
        currentTime = 0;
        processTime = time;
        totalProducts = 0;
        nclientes = 0;
        timeLeft = 0;
    }
    public void addClient(Cliente C) {
        if (fila.size() == 0) {
            timeLeft = currentTime + 10 + C.getProducts()*processTime;
        }
        //System.out.println("Adicionei!!");
        nclientes += 1;
        fila.enqueue(C);
        last = C;
        totalProducts += C.getProducts();
    }

    public void skip() {
        Cliente first = fila.first();
        if (first.getArrival()>currentTime) {currentTime = first.getArrival();}
        currentTime += 10 + fila.first().getProducts()*processTime;
        fila.dequeue();
    }
    public void next(){
        currentTime++;
        if (fila.isEmpty()) {
            return;
        }
        if (currentTime == timeLeft+1) {
            //System.out.println("Tirei!!");
            fila.dequeue();
            if (!fila.isEmpty()) timeLeft += 10 + fila.first().getProducts()*processTime;
        }
    }

    public String toString(){
        String out = "";
        out += "Fila: ";
        out += fila.toString();
        return out;
    }

    public int getCurrentSize() {return fila.size();}
    public int getLastProducts() {return last.getProducts();}
    public int getTime() {return currentTime;}
    public Cliente getFirst(){return fila.first();}
    public int getTotalSize() {return nclientes;}
    public int getTotalProducts() {return totalProducts;}
}

class Caixas{
    private int currentTime,size;
    private Caixa[] store;

    Caixas(int N) {
        store = new Caixa[N];
        currentTime = 0;
        size = 0;
    }
    private int selectCaixa() {
        int pos = 0;
        int filaSize = store[0].getCurrentSize();
        int storeSize;
        for (int i=0; i<size; i++) {
            storeSize = store[i].getCurrentSize();
            if (storeSize == 0) {return i;}
            if (storeSize<filaSize) {
                pos = i;
                filaSize = store[i].getCurrentSize();
            }
            if (storeSize == filaSize) {
                if (store[pos].getLastProducts() > store[i].getLastProducts()){
                    pos = i;
                    filaSize = store[i].getCurrentSize();
                }
            }
        }
        return pos;
    }

    public int getTime() {return currentTime;}
    public void addClient(Cliente C) {store[selectCaixa()].addClient(C);}
    public void addCaixa(int k){
        store[size] = new Caixa(k);
        size++;
    }


    public void nextTick() {
        for (int i=0; i<store.length; i++){
            while (currentTime>store[i].getTime()){
                store[i].next();
            }
        }
        currentTime += 1;
    }

    public String toString() {
        String out = "";
        for (int i=0; i<size; i++){
            out += "Caixa #" + (int) (i + 1) + ": ";
            out += store[i].getTotalSize() + " " + store[i].getTotalProducts() + "\n";
        }
        return out;
    }
    /* public String toString() {
        String out = "";
        for (int i=0; i<size; i++){
            out += "Caixa #" + (int) (i + 1) + " -> ";
            out += store[i].toString() + "\n";
        }
        return out;
    } */
}


public class ED115 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int flag = in.nextInt();        //flag (1 ou 2)
        int N = in.nextInt();           //numero de caixas
        int k;                          //tempos de processamento das caixas

        if (flag == 1) {
            k = in.nextInt();
            Caixa cashier = new Caixa(k);
            int C = in.nextInt();       //numero de clientes
            in.nextLine();
            String nome;
            int tempo, prod;
            Cliente client;
            for (int i=0; i<C-1; i++) {
                nome = in.next();
                tempo = in.nextInt();
                prod = in.nextInt();
                client = new Cliente(nome, tempo, prod);
                cashier.addClient(client);
                in.nextLine();
            }
            nome = in.next();
            tempo = in.nextInt();
            prod = in.nextInt();
            client = new Cliente(nome, tempo, prod);
            cashier.addClient(client);
            String out = "";
            Cliente first;
            for (int i=0; i<C; i++) {
                first = cashier.getFirst();
                out += first.getName() + " " + first.getArrival() + " ";
                cashier.skip();
                out += cashier.getTime() + "\n";
            }
            System.out.print(out);
        }
        else {
            Caixas store = new Caixas(N);   //criar as N caixas
            for (int i=0; i<N; i++) {
                k = in.nextInt();
                store.addCaixa(k);          //criar a loja com as N caixas com os tempos K_i
            }
            int C = in.nextInt();           //Numero de clientes
            in.nextLine();
            String nome;
            int tempo, prod;
            Cliente client;
            for (int i=0; i<C; i++) {
                nome = in.next();
                tempo = in.nextInt();
                prod = in.nextInt();
                while (tempo > store.getTime()) {
                    //System.out.println("Tick = " + store.getTime() + "\n" + store);
                    store.nextTick();
                }
                client = new Cliente(nome, tempo, prod);
                store.addClient(client);
                //System.out.println("Tick = " + store.getTime() + "\n" + store);
                store.nextTick();
            }
            System.out.print(store);
        }
    }
}
