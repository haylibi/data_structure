class MyIntSet implements IntSet {
   // Deverá colocar aqui os atributos e métodos
   boolean[] isElem;
   int size;

   MyIntSet(){
       isElem = new boolean[1001];
       size = 0;
   }

   MyIntSet(int maxNumber) {
       isElem = new boolean[maxNumber+1];
       size = 0;
   }

   public boolean contains(int x){
       return isElem[x];
   }

   public boolean add(int x){
       if (isElem[x] == false){
           isElem[x] = true;
           size++;
           return true;
       }
       return false;
   }

   public boolean remove(int x){
       if (isElem[x]){
           isElem[x] = false;
           size--;
           return true;
       }
       return false;
   }
   public int size(){
       return size;
   }

   public void clear(){
       boolean[] next = new boolean[isElem.length];
       isElem = next;
       size = 0;
   }

   public String toString(){
       String out = new String("");
       out += "{";
       for(int i=0; i<isElem.length; i++){
           if (isElem[i] && i!=isElem.length-1) {
               out+= i + ",";
           }
       }
       if (isElem[isElem.length-1]) out+=isElem.length-1;
       out+="}";
       return out;
   }
}