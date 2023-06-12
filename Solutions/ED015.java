import java.util.Scanner;

class Sopa {
	private int rows,cols;
	char[][] game;
	private char[][] solution;
	String[] words;
	
	//Construtor
	Sopa (int LINS, int COL) {
		rows = LINS;
		cols = COL;
		game = new char[LINS][COL];
		solution = new char[LINS][COL];
	}
	
	//cria um jogo especifico com um input dado
	public void read(Scanner in) {
		String buf = new String("");
		for (int i=0; i<rows; i++) {
			buf = in.next();
			for (int j=0; j<cols; j++) {
				game[i][j] = buf.charAt(j);
				solution[i][j] = '.';
			}
		}
		
		int nwords = in.nextInt();
		
		words = new String[nwords];
		for (int i=0; i<nwords; i++) {
			words[i] = in.next();
		}
	}

	//verifica se Word esta na sopa de letras numa posicao (x,y) com o movimento "mov" = iniciais de up/down/left/right
	public boolean fits(int x, int y, String word, char mov) {
		int len = word.length();		
		if (word.charAt(0) != game[x][y]) {return false;}
		char[][] temp = new char[rows][cols];		//var que vai substituir a solution (depois de verificado se a palavra cabe e tal)
		
		for (int i=0; i<rows; i++)
			for (int j=0; j<cols; j++)
				temp[i][j] = solution[i][j];

		temp[x][y] = word.charAt(0);
		if (mov == 'u' && x+1>=len) {
			for (int i=1; i<len; i++) {
				temp[x-i][y] = game[x-i][y];
				if (word.charAt(i)!=game[x-i][y]) {return false;}
			}
			solution = temp;
			return true;
		}	
		if (mov == 'd' && rows-x>=len) {
			for (int i=1; i<len; i++) {
				temp[x+i][y] = game[x+i][y];
				if (word.charAt(i) != game[x+i][y]) {return false;}
			}
			solution = temp;
			return true;
		}
		if (mov == 'l' && y+1>=len) {
			for (int i=1; i<len; i++) {
				temp[x][y-i] = game[x][y-i];
				if (word.charAt(i)!=game[x][y-i]) {return false;}
			}
			solution = temp;
			return true;

		}
		if (mov == 'r' && cols-y>=len) {
			for (int i=1; i<len; i++) {
				temp[x][y+i] = game[x][y+i];
				if (word.charAt(i)!=game[x][y+i]) {return false;}
			}
			solution = temp;
			return true;
		}
		else {return false;}
	}

	public void solve() {
		char[] movements = {'u','d','l','r'};
		for (int i=0; i<rows; i++) {
			for (int j=0; j<cols; j++) {
				for (int k=0; k<words.length; k++){
					for (int h=0; h<movements.length; h++)
						if (fits(i,j,words[k],movements[h]))
							break;
				}
			}
		}
	}

	public void showSol() {
		for (int i=0; i<rows; i++) {
			for (int j=0; j<cols; j++)
				System.out.print(solution[i][j]);
			System.out.println("");
		}
	}
}

class ED015 {

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		int LINS = in.nextInt();
		int COL = in.nextInt();	
		int contador = 1;
		while (LINS != 0 && COL != 0) {
			Sopa jogo = new Sopa(LINS, COL);
			jogo.read(in);
			
			jogo.solve();
			System.out.println("Input #" + contador);
			jogo.showSol();
			LINS = in.nextInt();
			COL = in.nextInt();
			contador += 1;
		}
				
	}
}
