import java.util.Scanner;

class Matriz {
	int n,m;
	char[][] data;
	
	Matriz(int a, int b) {
		n = a;
		m = b;
		data = new char[n][m];
	}

	void read(Scanner in) {
		String buf = new String("");
		for (int i=0; i<n; i++) {
			buf = in.next();
			for (int j=0; j<m; j++) {
				data[i][j] = buf.charAt(j);
			}
		}
	}

	int countCardinal (int n, int m, int incrx, int incry) {
		if (data[n][m] != '#') return 0;
		else {
			int count = 0;
			for (int x=n, y=m; x<this.n && y<this.m && y>=0 && x>=0 ; x += incrx, y += incry) {
				if (data[x][y] != '#') {return count;}
				else {count += 1;}
			}
			return count;
		}
	}


	int[] maxCardinal() {		//int[1] e o max, int[0] e o numero de ocorrencias
		int[] out = new int[2];
		out[0] = 0;		//valor output do numero de vezes que o maximo cardinal ocorre, ate la sera usado como variavel temp
		out[1] = 0;		//valor output do maximo ocorrencia de cardinais
		int values[] = new int[n*m];
		for(int i=0; i<n; i++)
			for (int j=0; j<m; j++) {
				values[out[0]] = ED216.max(ED216.max(countCardinal(i,j,0,1),countCardinal(i,j,1,0)),ED216.max(countCardinal(i,j,1,1),countCardinal(i,j,1,-1)));
				
				//System.out.print("data["+ i + "]["+j+"]" + " | " + "max = " + values[out[0]]);
				out[0] += 1;
			}

		for (int j=0; j<values.length; j++) {
			if (values[j] > out[1]) {
				out[1] = values[j];
			}
		}
		out[0] = 0;
		for (int j=0; j<values.length; j++){
			if (values[j] == out[1]) {
				out[0]+=1;
			}
		}
		return out;
	}


	/*public String toString(){
		String output = "";
		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) {
				output += "" + data[i][j];
			}
			output += "\n";
		}
		return output;
	}*/
}


public class ED216 {
   public static int max(int a, int d) {
	if (a<d) return d;
	else return a;
   }


   public static void main(String[] args) {
	Scanner in = new Scanner(System.in);

	int n = in.nextInt();
	int m = in.nextInt();
	Matriz nova = new Matriz(n, m);

	nova.read(in);
	int[] last = nova.maxCardinal();
	System.out.println(last[1] + " " + last[0]);
   }
}
