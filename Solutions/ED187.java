import java.util.Scanner;

class Matrix {
   int data[][]; // os elementos da matriz em si
   int rows;     // numero de linhas
   int cols;     // numero de colunas

   // construtor padrao de matriz
   Matrix(int r, int c) {
      data = new int[r][c];
      rows = r;
      cols = c;
   }

   // Ler os rows x cols elementos da matriz
   public void read(Scanner in) {
      for (int i=0; i<rows; i++)
         for (int j=0; j<cols; j++)
            data[i][j] = in.nextInt();
   }

   // Representacao em String da matriz
   public String toString() {
      String ans = "";
      for (int i=0; i<rows; i++) {
         for (int j=0; j<cols; j++)
            ans += data[i][j] + " ";
         ans += "\n";
      }
      return ans;
   }

   public static Matrix identity(int n) {
	Matrix M = new Matrix(n, n);
	for (int i=0; i<n; i++) {M.data[i][i] = 1;}
	return M;
   }  

   public Matrix transpose() {
	Matrix temp = new Matrix(cols, rows);
	for (int i=0; i<cols; i++)
           for (int j=0; j<rows; j++)
              temp.data[i][j] = data[j][i];
	return temp;
   }


   public Matrix sum(Matrix m) { 
	Matrix temp = new Matrix(rows, cols);
	for (int i=0; i<rows; i++)
	   for (int j=0; j<cols; j++)
		temp.data[i][j] = data[i][j] + m.data[i][j];
	return temp;
   }

   public Matrix multiply(Matrix m) { 
	Matrix temp = new Matrix(rows, m.cols);
	int sum;
	for (int i=0; i<rows; i++)
	   for (int j=0; j<m.cols; j++) {
		for (int k=0; k<cols; k++)
			temp.data[i][j] += data[i][k]*m.data[k][j];
	   }
	return temp;
   }
}
