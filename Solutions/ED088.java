import java.util.Scanner;


//classe do jogo da vida (tem um dado estado, uma delimitacao de janela (row e col))
class Game{
	private int row;
	private int col;
	private char[][] state;
	
	
	//inicializar um estado do jogo
	Game (int row, int col) {
		this.row = row;
		this.col = col;
		state = new char[row+2][col+2];		//Acrescentar 2 posicoes com NULL, uma no inicio e outra no fim, para nao haver problemas de indexOutOfBounds
	}


	//le o input do novo jogo life
	void read(Scanner in) {
		String buf = new String("");
		for (int i=0; i<row; i++) {
			buf = in.next();
			for (int j=0; j<col; j++) {
				state[i+1][j+1] = buf.charAt(j);
			}
		}
	}


	//output retornado por uma classe Game
	public String toString() {
		String out = "";
		for (int i=0; i<row; i++) {
			for (int j=0; j<col; j++) {
				out += "" + (state[i+1][j+1]);
			}
			out += "\n";
		}
		return out;
	}


	///Retorna o numero de celulas vivas vizinhas da celula na pos (x,y)
	int lifeCount(int x, int y) {
		int count = 0;
		for (int i=0; i<3 && count<4; i++){			//a celula da pos (x,y) esta na pos [x+1][y+1] (para evitar erros de out of bound)
			if (state[x-1+i][y+1] == 'O') {count+=1;}
		}
		for (int i=0; i<2 && count<4; i++){			//a celula da pos (x,y) esta na pos [x+1][y+1] (para evitar erros de out of bound)
			if (state[x+1][y-i] == 'O') {count+=1;}
		}
		for (int i=0; i<2 && count<4; i++){			//a celula da pos (x,y) esta na pos [x+1][y+1] (para evitar erros de out of bound)
			if (state[x-i][y-1] == 'O') {count+=1;}
		}
		if (state[x-1][y] == 'O') {count+=1;}

		return count;
	}
	
	//Retorna a geracao seguinte dum dado estado
	Game nextGen() {
		//System.out.println("Length[] = " + state[0].length + " Length = " + state.length);
		int count = 0;
		Game lifeNew = new Game(row, col);
		for (int i=0; i<row; i++) {
			for (int j=0; j<col; j++) {
				//System.out.println("i = "+ i + " || j = " + j);
				count = lifeCount(i+1,j+1);
				//System.out.println("Counta espaco (" + i + ", " + j + ") = "+ count);
				if (state[i+1][j+1] == 'O') {
					if (count <= 1 || count >= 4) {lifeNew.state[i+1][j+1] = '.';}
					else {lifeNew.state[i+1][j+1] = 'O';}
				}

				else {
					if (count == 3) {lifeNew.state[i+1][j+1] = 'O';}
					else {lifeNew.state[i+1][j+1] = '.';} 
				}
			}
		}
		return lifeNew;
	}
}



class ED088 {

	public static void main(String[] args) {
		Scanner inp = new Scanner(System.in);
		
		int L = inp.nextInt();
		int C = inp.nextInt();
		int I = inp.nextInt();
		Game life = new Game(L, C);
		life.read(inp);

		for (int i=0; i<I; i++) {
			life = life.nextGen();
			//System.out.println("Geracao " + i + ":\n");
			//System.out.println(life);
		}
		System.out.print(life);	
	}
}



