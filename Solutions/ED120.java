import java.util.Scanner;

class ED120{


    public static void line(int n, String C){		//Desenha uma linha de tamanho n com o caracter "C"
    for(int i=1;i<=n;i++)
        System.out.print(C);
    }

	public static void losango(int n, String C){
		if (n%2 == 0)
			System.out.println("\n'"+n+"' isn't odd. Please use an odd integer");		//So funciona com numeros impares
		else{
			int i=1;
			while (i<n){
				line((n-i)/2,".");		//desenhar os '.'s antes dos '#' 
                line(i,C);				//desenhar os caracteres no tamanho respetivo da linha
                line((n-i)/2,".");      //desenhar os '.'s depois dos '#' 
				System.out.println("");
				i+=2;
			}
			while (i>=1){
				line((n-i)/2,".");		//desenhar os '.'s antes dos '#' 
                line(i,C);				//desenhar os caracteres no tamanho respetivo da linha
                line((n-i)/2,".");      //desenhar os '.'s depois dos '#' 
				System.out.println("");
				i-=2;
            }
		}
    }
    
    public static void main(String[] args){
        Scanner myObj = new Scanner(System.in);
        int n = myObj.nextInt();
        losango(n,"#");   
    }





}