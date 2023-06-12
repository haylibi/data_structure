import java.util.Scanner;

class Evento{
    private int id;
    private int startTime;
    private int endTime;
    private Bombeiro[] bombeiros;

    Evento(int identificador, int time, Bombeiro[] bomb){
        id = identificador;
        startTime = time;
        bombeiros = bomb;
        endTime = -1;
    }

    public void end(int time) {endTime = time;};
    public Bombeiro[] getBombeiros(){return bombeiros;}
    public int getId(){return id;}
    public int getStart(){return startTime;}
    public int getEnd(){return endTime;}
    public int duration(){
        if (endTime == -1) return -1;
        return endTime-startTime;
    }

    public String toString() {
        String out = "";
        out += "----------------------\n";
        out += "Event ID: " + id;
        out += "\nDuration: From " + startTime + " to " + endTime + " = " + duration();
        out += "\n----------------------\n";
        return out;
    }
}

class Bombeiro {
    private String nome;
    private int numberEvents;
    public int actionTime;
    
    Bombeiro(String name) {
        nome = name;
        numberEvents = 0;
        actionTime = 0;
    }

    public int getActionTime(){return actionTime;}
    public void addEvent(){numberEvents+=1;}
    public int getEvent(){return numberEvents;}
    public String getName(){return nome;}
    public String toString() {return nome + " " + numberEvents + " " + actionTime;}
}



class ED095{
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int flag = in.nextInt();        // flag variable (flag = 1, 2 or 3)
        int N = in.nextInt();           // N -> number of firefighters
        
        
        Evento[] eventos = new Evento[501];                           // Array com todos os eventos
        MyQueue<Bombeiro> bombeiros = new LinkedListQueue<>();        // Fila com os bombeiros livres


        int numberEvents = 0;           // Int com o numero de eventos que ocorreram
        int notEnoughMembers = 0;       // Int que conta o numero de eventos que nao teve membros suficientes


        // Input dos N bombeiros
        for (int i=0; i<N; i++) {  
            String name = in.next();
            Bombeiro F = new Bombeiro(name);
            bombeiros.enqueue(F);
        }
        
        

        // Ciclo para verificar os eventos (ate ser diferente de "FIM")
        String tmp = in.next();
        while (!tmp.equals("FIM")){
            if (tmp.equals("PARTIDA")){

                // INPUT
                int id = in.nextInt();              // id do evento
                int nBombeiros = in.nextInt();      // numero de bombeiros necessario
                int startTime = in.nextInt();       // quando comeca o evento
                in.nextLine();

                // Se o numero necessario de bombeiros for superior ao numero de bomb disponiveis
                if (bombeiros.size()<nBombeiros){  
                    Bombeiro[] bomb = new Bombeiro[bombeiros.size()];
                    for (int i=0; i<bomb.length; i++) {
                        bomb[i] = bombeiros.dequeue();      //colocar os bombeiros na frente da fila em atividade no evento[id]
                        bomb[i].addEvent();
                        notEnoughMembers++;
                    }
                    eventos[id] = new Evento(id, startTime, bomb);
                }
                // Se o numero necessario de bombeiros for inferior ao numero de bomb disponiveis
                else {
                    Bombeiro[] bomb = new Bombeiro[nBombeiros];
                    for (int i=0; i<nBombeiros; i++){
                        bomb[i] = bombeiros.dequeue();
                        bomb[i].addEvent();
                    }
                    eventos[id] = new Evento(id, startTime, bomb);
                }
                
                tmp = in.next();
                numberEvents ++;
            }
            else { 
                int id = in.nextInt();
                int endTime = in.nextInt();
                in.nextLine();
                Bombeiro[] bChegada = eventos[id].getBombeiros();
                eventos[id].end(endTime); 
                for (int i=0; i<bChegada.length; i++){
                    bChegada[i].actionTime += eventos[id].duration();
                    bombeiros.enqueue(bChegada[i]);
                }
                tmp = in.next();
            }
        }
        
        if (flag == 1) {System.out.println("Ocorreram " + numberEvents + " eventos");}
        if (flag == 2) {
            System.out.println("Bombeiros Destacados");
            for (int i=0; i<eventos.length; i++) {
                if (eventos[i] == null) continue;
                System.out.println("EVENTO " + eventos[i].getId());
                Bombeiro[] bomb = eventos[i].getBombeiros();
                if (bomb.length == 0) System.out.println("Nenhum");
                for (int j=0; j<bomb.length; j++) {
                    System.out.println(bomb[j].getName());
                }
            }
        }
        if (flag == 3) {
            System.out.println("Listagem de Bombeiros");
            for (int i=0; i<N; i++) {
                System.out.println(bombeiros.dequeue());
            }
        }
    }
}