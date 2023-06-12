public class BigNumber{
    int[] number;
    int size;

    BigNumber(){
        size = 0;
        number = new int[1001];
    }

    BigNumber(String n){
        size = n.length();
        number = new int[1001];
        for (int i=0; i<size; i++){
            number[i] = n.charAt(size-i-1) - '0';
        }
    }

    public boolean equals(BigNumber n){
        if (n.size!=size) return false;
        for(int i=0; i<size; i++){
            if (number[i] != n.number[i]) return false;
        }
        return true;
    }

    public String toString(){
        String out = new String("");
        for (int i=size-1; i>=0; i--) out+=number[i];
        return out;
    }


    public BigNumber add(BigNumber n){
        BigNumber out = new BigNumber("0");
        out.size = 0;
        int aux = 0;
        int newSize;
        if (size>n.size) newSize = size;
        else newSize = n.size;
        for (int i=0; i<newSize; i++){
            out.number[i] = (number[i] + n.number[i] + aux)%10;
            out.size++;
            //System.out.println(number[i] + " + " + n.number[i] + " = " + out.number[i]);
            aux = (int) (number[i] + n.number[i] + aux)/10;
        }
        if (aux != 0){
            out.size++;
            out.number[out.size-1] = aux;
        }
        return out;
    }

    public BigNumber multiply(int n, int aux) {
    BigNumber temp = new BigNumber("0");
    BigNumber out = new BigNumber("0");
    for (int i=0; i < n; i++) temp = temp.add(this);
    for (int i=aux; i<=aux+temp.size; i++) out.number[i] = temp.number[i-aux];
    out.size = temp.size+aux;
    return out;
    }

    public BigNumber multiply(BigNumber n){
        BigNumber out = new BigNumber();
        for (int i=0; i<n.size; i++) {
            out = out.add(this.multiply(n.number[i], i));            
        }  
        return out;
    }
}