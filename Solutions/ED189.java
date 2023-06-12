// -----------------------------------------------------------
// Estruturas de Dados 2019/2020 (CC1007) - DCC/FCUP
// http://www.dcc.fc.up.pt/~pribeiro/aulas/edados1920/
// -----------------------------------------------------------
// Lista ligada simples
// Ultima alteracao: 03/04/2018
// -----------------------------------------------------------

public class SinglyLinkedList<T> {
    private Node<T> first;    // Primeiro no da lista
    private int size;         // Tamanho da lista
 
    // Construtor (cria lista vazia)
    SinglyLinkedList() {
       first = null;
       size = 0;
    }
 
    // Retorna o tamanho da lista
    public int size() {
       return size;
    }
 
    // Devolve true se a lista estiver vazia ou falso caso contrario
    public boolean isEmpty() {
       return (size == 0);
    }
    
    // Adiciona v ao inicio da lista
    public void addFirst(T v) {
       Node<T> newNode = new Node<T>(v, first); 
       first = newNode;
       size++;
    }
 
    // Adiciona v ao final da lista
    public void addLast(T v) {
       Node<T> newNode = new Node<T>(v, null); 
       if (isEmpty()) {
          first = newNode;
       } else {
          Node<T> cur = first;
          while (cur.getNext() != null)
             cur = cur.getNext();
          cur.setNext(newNode);         
       }
       size++;
    }
 
    // Retorna o primeiro valor da lista (ou null se a lista for vazia)
    public T getFirst() {
       if (isEmpty()) return null;
       return first.getValue();
    }
 
    // Retorna o ultimo valor da lista (ou null se a lista for vazia)
    public T getLast() {
       if (isEmpty()) return null;
       Node<T> cur = first;
       while (cur.getNext() != null)
          cur = cur.getNext();
       return cur.getValue();      
    }

    public T get(int pos) {
       if (pos<0) { return null; }
       Node<T> cur = first;
       for (int i=0; i<pos && cur != null; i++) {
          cur = cur.getNext();
       }
       if (cur == null) { return null; }
       return cur.getValue();
    }
 
    // Remove o primeiro elemento da lista (se for vazia nao faz nada)
    public void removeFirst() {
       if (isEmpty()) return;
       first = first.getNext();
       size--;
    }
 
    // Remove o ultimo elemento da lista (se for vazia nao faz nada)
    public void removeLast() {
       if (isEmpty()) return;
       if (size == 1) {
          first = null;
       } else {
          // Ciclo com for e uso de de size para mostrar alternativa ao while
          Node<T> cur = first;
          for (int i=0; i<size-2; i++)
             cur = cur.getNext();
          cur.setNext(cur.getNext().getNext());
       }
       size--;
    }

    public T remove(int pos) {
      if (pos<0 || pos >= size) { return null; }
      if (pos == size-1) { 
         T last = getLast();
         removeLast();
         return last;
      }
      Node<T> cur = first;
      for (int i=0; i<pos; i++) {
         cur = cur.getNext();
      }
      T val = cur.getValue();
      cur.setValue(cur.getNext().getValue());
      cur.setNext(cur.getNext().getNext());
      size--;
      return val;   
   }
    
    // Converte a lista para uma String
    public String toString() {
       String str = "{";      
       Node<T> cur = first;
       while (cur != null) {
          str += cur.getValue();
          cur = cur.getNext();
          if (cur != null) str += ",";                     
       }      
       str += "}";
       return str;
    }
 }