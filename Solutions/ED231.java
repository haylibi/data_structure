/*
Nome: Luis Duarte Carneiro Pinto
Número mecanográfico: 201704025
Sobre ajudas: ----

Caso flag == 1
    Explicação da solucao:  Enquanto os dados sao introduzidos (depois dos 2 primeireos), comparar a diferenca entre o valor introduzido e o valor introduzido antes desse com as variaveis max e min
    Complexidade aproximada: linear, visto que apenas e realizar um ciclo para ler os dados de cada dia e, dentro desse ciclo, so se realizam aproximadamente 8 operacoes (igualdade, comparacao, subtracao e atribuicao de valores)

Caso flag == 2
    Explicação da solucao: Com uma variavel auxiliar, verificar se o dia anterior foi um dia de baixa propagacao. 
        Caso tenha sido, verificar se este dia e de baixa propagacao (aumentar o contador de numero de dias seguidos de baixa propagacao e comparalo com o maximo ate entao) ou nao (resetar o contador e atualizar a variavel auxiliar).
        Caso contrario, aumentar o contador de periodos de baixa propagacao e colocar o contador de numero de dias seguidos de baixa propagacao para 1 e atualizar a variavel auxiliar (para 1, neste caso)
    Complexidade aproximada: complexidade flag == 1 + (1 ciclo de 0 até N-1 onde se realiza um numero fixo de operacoes)
                             = linear
Caso flag == 3
    Explicação da solucao: Descobrir a altura do grafico (pegar no ultimo valor do array com casos confirmados).
        Para cada "nivel" do grafico (de altura-1 ate 0), realizar um ciclo de 0 ate N para descobrir se, nesse dia, o numero de casos/100*currentAltura e superior a 0 ou nao, caso seja colocar um "#", caso contrario, colocar um "."
    Complexidade aproximada: complexidade flag == 1 + (ultimo inteiro/100 * N) 
                            = (como os casos confirmados <= 10^5) O(linear + n*10^5) = O(n) = linear
*/

import java.util.Scanner;

public class ED231 {
    static int N,flag;       // numero de dias consecutivos dado no input e a flag que representa o output desejado
    static int[] C;     // array com numero de casos confirmados em cada um dos dias (de 0 ate N-1)



    // caso em que flag == 2, output da quantidade de baixa propagacao e maior tamanho de um periodo de baixa propagacao
    public static void flag2() {
        int curCount = 0, maxCount = 0;     // vai contar os casos seguidos de baixa propagacao
        int last = 0;                       // verifica se o dia anterior foi de baixa ou alta propagacao
        int periodCount = 0;                // vai contar o numero de periodos de baixa propagacao
        for (int i=0; i<N-1; i++) {
            // caso em que o crescimento e menor que 5% ( baixa propagacao )
            if ((double) (C[i+1]-C[i]) / (double) C[i] <= 0.05) {
                // caso em que o dia anterior foi de alta propagacao
                if (last == 0) {
                    last = 1;       // atualizar o valor do ultimo dia
                    periodCount++;  // atualizar o count dos periodos de baixa propagacao
                    curCount ++;
                }
                // caso em que o dia anterior foi de baixa propagacao
                else {
                    curCount ++;
                }
                if (curCount > maxCount) maxCount = curCount;
            }
            // caso em que o crescimento e maior que 5% ( alta propagacao )
            else {
                curCount = 0;   // reset dos valores
                last = 0;       // alterar a variavel do ultimo dia para "alta propagacao" <=> 0
            }
        }
        System.out.println(periodCount + " " + maxCount);
    }


    // caso em que flag == 3, output de um grafico de barras dos casos totais confirmados por dia
    public static void flag3() {
        int altura = C[N-1]/100;
        for (int i=altura-1; i>=0; i--) {
            for (int j=0; j<N; j++) {
                if (C[j]/(100*(i+1)) > 0) System.out.print("#");
                else System.out.print(".");
            }
            System.out.println();
        }
        
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        N = in.nextInt();
        C = new int[N];
        C[0] = in.nextInt();
        C[1] = in.nextInt();
        int max = C[1]-C[0], min = C[1]-C[0];

        for (int i=2; i<N; i++) {
            C[i] = in.nextInt();
            if (C[i]-C[i-1] > max) max = C[i] - C[i-1];
            if (C[i]-C[i-1] < min) min = C[i] - C[i-1];
        }
        flag = in.nextInt();
        if (flag == 1) System.out.println(min + " " + max);
        if (flag == 2) flag2();
        if (flag == 3) flag3();
    }
}