import java.util.Scanner;

public class ED217 {
    public static void main(String[] args) {
	Scanner out = new Scanner(System.in);

	int P = out.nextInt();
	Point[] pontos = new Point[P];

	int x,y;
	for (int i=0; i<P; i++) {
		x = out.nextInt();
		y = out.nextInt();
		pontos[i] = new Point(x,y);
	}


	int R = out.nextInt();
	Rectangle[] ret = new Rectangle[R];
	int x1,y1,x2,y2;
	Point[] R1 = new Point[2*R];
	for (int i=0; i<R; i++) {
		x1 = out.nextInt();
		y1 = out.nextInt();
		x2 = out.nextInt();
		y2 = out.nextInt();
		R1[2*i] = new Point(x1, y1);
		R1[2*i+1] = new Point(x2, y2);
		ret[i] = new Rectangle(R1[2*i], R1[2*i+1]);
	}
	int p_out = 0;
	int p_count;
	int[] rect = new int[R];
	for (int i=0; i<P; i++) {
		p_count = 0;
		for (int j=0; j<R; j++) {
			//System.out.println("i = " + i +" j = " + j);
			if (ret[j].pointInside(pontos[i])) {
				rect[j] = 1;
				p_count = 1;
			}
		}
		if (p_count == 0)
			p_out += 1;
	}
	int sum = 0;
	for (int i=0; i<R; i++) {
		sum += rect[i];
		//System.out.println(rect[i]);
	}
	sum = rect.length-sum;
	System.out.println(p_out + " " + sum);
    }
}
