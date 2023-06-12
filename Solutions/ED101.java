import java.util.Scanner;

class Turtle {
   char[][] table;
   int[] dir,colFilled,linFilled;
   int x,y,pen;

   Turtle(int x, int y, int LINS, int COLS) {
	dir = new int[2];
	dir[0] = 1;	//direcao Este = [1,0] (avanca 1 para a direita, 0 na direcao vertical)
	dir[1] = 0;
	table = new char[LINS][COLS];
	colFilled = new int[COLS];
	linFilled = new int[LINS];
	this.x = x;
	this.y = y;
	pen = 0;	//pen=1 significa caneta embaixo
	for (int i=0; i<LINS; i++) {
		for (int j=0; j<COLS; j++) {
			table[i][j] = '.';
		}
	}
   }

   Turtle update(int LINS, int COLS) {
	Turtle nova = new Turtle(0, 0, LINS, COLS);
	return nova;
   }

   //mover a tartaruga n possicoes na direcao em que esta
   void move(int n){
	//table[y][x] = '.';
	int[] dim = new int[2];
	dim[0] = table[0].length;
	dim[1] = table.length;
	if (pen == 1) {table[y][x] = '*';}
	for (int i=0; i<n; i++) {
		if (x+dir[0] >= dim[0] || x+dir[0] < 0 || y+dir[1] >= dim[1] || y+dir[1] < 0){
			break;
		}
		else {		
			x+=dir[0];
			y+=dir[1];
		}
		if (pen == 1) {
			table[y][x] = '*';
			colFilled[x] = 1;
			linFilled[y] = 1;		
		}
	}
	//table[y][x] = 'I';
   }

   void read(String S) {
	int tmp = dir[0];
	if (S.equals("U")) {
		pen = 0;
	}
	if (S.equals("D")) {
		pen = 1;
		table[y][x] = '*';
		linFilled[y] = 1;
		colFilled[x] = 1;
	}

	if (S.equals("R")) {
		dir[0] = -dir[1];
		dir[1] = tmp;	
	}
	if (S.equals("L")) {
		dir[0] = dir[1];
		dir[1] = -tmp;		
	}
   }

   int outPut(int flag, Scanner in) {
	if (flag == 0) {
		System.out.print(toString());
	}
	
	if (flag == 1) {
		int out = 0;
		int lines = table.length;
		int cols = table[0].length;
		for (int i=0; i<table.length; i++) {
			lines -= linFilled[i];
			for (int j=0; j<table[0].length; j++) {
				if (i==0) {cols -= colFilled[j];}
				if (table[i][j] == '*') {out+=1;}
			}
		}
		out = 100*out/(table.length*table[0].length);
		System.out.println(out + " " + lines + " " + cols);
		return 1;
	}

	if (flag == 2) {
		int col,lin;
		lin = in.nextInt();
		col = in.nextInt();
		char[][] tableInp = new char[lin][col];
		String buf = new String("");
		buf = in.nextLine();
		for (int i=0; i<lin; i++) {
			buf = in.nextLine();
			for (int j=0; j<col; j++) {
				buf = buf.replaceAll("\\s+","");
				tableInp[i][j] = buf.charAt(j);
				//System.out.println(tableInp[i][j] + " | " + buf.charAt(j));
			}
		}
		int count = 1;
		for (int i=0; i<table.length-lin+1; i++) {
			for (int j=0; j<table.length-col+1; j++) {
				count = 1;
				outerloop:
				for (int k=0; k<lin; k++) {
					for (int h=0; h<col; h++) {
						//System.out.println("Aqui i = " + i + " | j = " + j + " | table[i+k][j+h] = " + table[i+k][j+h]);
						//System.out.println("Aqui k = " + k + " | h = " + h + " | tableInp[k][h] = " + tableInp[k][h]);
						if (table[i+k][j+h] != tableInp[k][h]) {
							//System.out.println("Fodeu\n");
							count = 0;							
							break outerloop;						
						}
					}
					if (k == lin-1) {
						System.out.println("Sim");
						return 1;
					}	
				}
			}
		}
		System.out.println("Nao");
		return 1;
	}
	return 0;
   }

   
   public String toString(){
	String out = new String("");
	for (int i=0; i<table.length; i++) {
		for (int j=0; j<table[0].length-1; j++) {
			out+=table[i][j] + " ";
		}
		out+=table[i][table[0].length-1] + "\n";
	}
	return out;
   }
}



class ED101 {
   public static void main(String[] args) {
	Scanner in = new Scanner(System.in);
	int flag = in.nextInt();
	int LINS = in.nextInt();
	int COLS = in.nextInt();
	int forward;
	Turtle myTurtle = new Turtle(0, 0, LINS, COLS);
	String steps = in.next();
	while (steps.equals("end") == false) {
		if (steps.equals("F")) {
			forward = in.nextInt();
			myTurtle.move(forward);
		}
		else {myTurtle.read(steps);}
		//System.out.println("Turtle in pos (" + myTurtle.x + "," + myTurtle.y +") directed to (" + myTurtle.dir[0] + "," + myTurtle.dir[1] + ")\n" + myTurtle);
		steps = in.next();
	}
	myTurtle.outPut(flag, in);

   }
}
