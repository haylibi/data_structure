/* -----------------------------------
  Estruturas de Dados 2019/2020
  Triagem de Manchester [ED098]  
----------------------------------- */

import java.util.Scanner;
import java.util.LinkedList;

// Classe para representar um Doente
class Doente {
    public String nome;     // Nome
    public int chegada;     // Tempo de chegada
    public int atendimento; // Tempo que demora a ser atendido
    public int entrada;     // Tempo em que comecou a ser atendido

    // Construtor: inicializa os atributos
    Doente(String n, int c, int a) {
        nome        = n;
        chegada     = c;
        atendimento = a;
        entrada     = -1;
    }
}

// Classe para representar uma fila de atendimento de uma cor (necessaria criar array)
// (nota: Java nao permite criacao direta de array de genericos)
class FilaAtendimento {
    public MyQueue<Doente> fila;

    FilaAtendimento() {
	    fila = new LinkedListQueue<Doente>();
    }
    
    public int size(){return fila.size();}
    public void enqueue(Doente d) {fila.enqueue(d);}
    public Doente dequeue(){return fila.dequeue();}
    public Doente first(){return fila.first();}
    public boolean isEmpty(){return fila.isEmpty();}
}

// Classe para representar uma equipa da urgencia
class Equipa {
    int numDoentes;       // Numero de doentes que atenderam
    int totalAtendimento; // Total de tempo passado a atender
    int livre;            // Tempo em que ficam livres para poder atender novo doente

    Equipa() {
	numDoentes       = 0;
	totalAtendimento = 0;
	livre            = 0;
    }

    // Novo doente d comecou a ser atendido num dado tempo t nesta equipa
    void novoDoente(Doente d, int t) {
        // a completar ...
        livre = t + d.atendimento;
        totalAtendimento += d.atendimento;
        this.numDoentes += 1;
    }
}

// Classe principal que contem o metodo main
class ED098 {
    // Constantes que nao mudam durante o programa: numero de cores e seus nomes
    private static final int NUM_CORES = 5;
    private static final String[] CORES = {"Vermelho","Laranja","Amarelo","Verde","Azul"};

    private static int numEquipas;               // Numero de equipas
    private static int numDoentes;               // Numero total de doentes
    private static FilaAtendimento emEspera[];   // Array com uma fila para cada cor
    private static LinkedList<Doente> atendidos; // Lista de doentes ja atendidos
    private static Equipa equipas[];             // Equipas da urgencia

    // Metodo para devolver indice de uma cor representada pela string s
    private static int indiceCor(String s) {
	for (int i=0; i<NUM_CORES; i++) {
        if (CORES[i].equals(s)) return i;
    }
	return -1;
    }

    // Ler os doentes a partir do input e coloca-los nas respetivas filas
    private static void lerDoentes(Scanner in) {	
        while (in.hasNext()) {
            String nome     = in.next();
            String cor      = in.next();
            int chegada     = in.nextInt();
            int atendimento = in.nextInt();
            //System.out.printf("Li [%s,%s,%d,%d]%n", nome, cor, chegada, atendimento);
            

            //Adicionar pessoa a fila correta de espera
            int color = indiceCor(cor);
            Doente Pessoa = new Doente(nome, chegada, atendimento);
            emEspera[color].enqueue(Pessoa);
            numDoentes++;

        }
    }

    // Mostrar numero de doentes em cada cor (necessario para flag==0)
    private static void mostrarCores() {
        System.out.println("------------");
        System.out.println("Cores     ND");
        System.out.println("------------");
        
        int soma = 0;
        for (int i=0; i<NUM_CORES; i++) {
            System.out.printf("%8s %3d\n" , CORES[i], emEspera[i].size());
            soma += emEspera[i].size();
        }
        System.out.println("------------");
        System.out.println("Numero doentes atendidos: " + soma);
    }
    

    // Mostrar estatisticas dos doentes atendidos (necessario para flag==1 e flag==2)
    private static void mostrarAtendidos() {
        System.out.println("---------------------------");
        System.out.println("Lista dos doentes atendidos");
        System.out.println("---------------------------");
        // itera sobre todos os doentes ja atendidos (instrucao for-each)
        double tEspera = 0;
        for (Doente d : atendidos) { 
            System.out.print(d.nome + " ");
            // a completar ...
            tEspera+=d.entrada-d.chegada;
            System.out.println(d.chegada + " " + (int) (d.entrada-d.chegada) + " " + (int) (d.entrada+d.atendimento));
        }
        System.out.println("---------------------------");
        tEspera = tEspera/(double)atendidos.size();
        System.out.printf("Tempo medio de espera: %.1f\n" , tEspera);
    }

    // Mostrar estatisticos das equipas (necessario para flag==2)
    private static void mostrarEquipas() {
        System.out.println("-----------------------");
        System.out.println("Equipa NDoentes MediaTA");
        System.out.println("-----------------------");

        int tempo = 0;
        while (atendidos.size() < numDoentes) {
            int E = proximaEquipaLivre();
            tempo = equipas[E].livre;
            while(proximoDoente(tempo) == -1) tempo++;
            int next = proximoDoente(tempo);
            Doente D = emEspera[next].dequeue();
            equipas[E].novoDoente(D, tempo);
            D.entrada = tempo;
            atendidos.addLast(D);
        }
        for (int i=0; i<numEquipas; i++) {
            Equipa E = equipas[i];
            System.out.printf("%6d %8d %7.1f\n", i,E.numDoentes,(double) E.totalAtendimento/(double) E.numDoentes);
        }
    }

    // Qual a cor da proxima equipa a ficar livre?
    private static int proximaEquipaLivre() {
        int tmp = equipas[0].livre;
        int out = 0;
        for (int i=0; i<numEquipas; i++) {
            if (equipas[i].livre < tmp) {
                out = i;
                tmp = equipas[i].livre;
            }
        }
        return out;
    }

    // Qual a cor mais prioritaria com doente ainda por ser atendido no tempo atual?
    private static int proximoDoente(int tempo) {
        // a completar ...
        for (int i=0; i<NUM_CORES; i++) {
            if (!emEspera[i].isEmpty()) {
                if (emEspera[i].first().chegada<=tempo) return i;
            }
        }
        return -1;
    }

    // Simular processo de atendimento pelas varias equipas medicas
    private static void simular() {
        // a completar ...
        int tempo = 0;
        while (atendidos.size() < numDoentes) {
            while (proximoDoente(tempo) == -1) tempo++;
            Doente D = emEspera[proximoDoente(tempo)].dequeue();
            D.entrada = tempo;
            tempo += D.atendimento;
            atendidos.addLast(D);
        }
    }

    // Inicializar variaveis
    private static void inicializar() {
        numDoentes = 0;

        emEspera = new FilaAtendimento[NUM_CORES];	
        for (int i=0; i<NUM_CORES;i++)
            emEspera[i] = new FilaAtendimento();

        atendidos = new LinkedList<Doente>();

        equipas = new Equipa[numEquipas];
        for (int i=0; i<numEquipas; i++)
            equipas[i] = new Equipa();
    }

    // ----------------------------------------------------------------

    // Funcao principal chamada no inicio do codigo
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int flag   = in.nextInt();
        numEquipas = in.nextInt();
        
        inicializar();
        lerDoentes(in);

        if (flag == 0) {
            mostrarCores();
        } else {
            if (flag == 1) simular();
            if (flag == 2) mostrarEquipas(); 
            mostrarAtendidos();
        }
    }
}