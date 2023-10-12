import java.security.InvalidParameterException;
import java.util.NoSuchElementException;

public class Linkedlist<T> {

    public static class Node<T>{ //construct data in class
        public T value;
        public Node next;
        Node(){
            this.next =null;
        }
        Node(T value){
            this.value = value;
            this.next =null;
        }
        Node(T value,Node next){
            this.value = value;
            this.next =next;
        }
        public T getItem(){
            return value;
        }
        public Node getNext(){
            return next;
        }
        public void setItem(T value){
            this.value = value;
        }
        public void setNext(Node next){
            this.next = next;
        }
    }
    private int size;
    private Node head;
    private Node last;

    public Linkedlist(){
        head=null;
        size=0;
    }
    public void addFirst(T value) {
        Node newNode = new Node(value);
        if(head==null){
            head=newNode;
        }else{
            newNode.next = head;
            head.next =newNode;
        }
        size++;
    }
    public void addLast(T value) {
        Node newNode = new Node(value);
        if(head==null){
            addFirst(value);
            last = newNode;
        }else{
            Node p = head;
            while(p.next!=null){
                p=p.next;
            }
            p.next = newNode;
            last = newNode;
            size++;
        }
    }
    public boolean contains(T value){
        Node p =head;
        while(p.next!=null && p.value != value){
            p = p.next;
        }
        return p!=null;
    }
    public Node nodeAt(int index){
        if(index < 0  || index>size){
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        Node p =head;
        int i=0;
        while(p.next!=null && i != index){
            p = p.next;
            i++;
        }
        return p;
    }
    public boolean isEmpty(){
        if(size == 0){
            return true;
        }
        return false;
    }
    public void removeAt(int index){
        if(size==0){
            throw new NoSuchElementException("List is empty");
        }else if(index > size || index<0){
            throw new IndexOutOfBoundsException("Index out of bounds: "+index);
        }

        if(index==0){
            head = head.next;
        }
        else{
            Node p = nodeAt(index-1);
            p.next = p.next.next;
        }
        size--;
    }
    public void remove(T value){
        if(size==0){
            throw new NoSuchElementException("List is empty");
        }
        if(contains(value)){
            Node p =head;
            while(p.next!=null && p.next.value != value){
                p = p.next;
            }
            p.next = p.next.next;
            size--;
        }else{
            System.out.println("There is no that items in this list");
        }
    }
    public void add(T value){
        Node p = new Node(value);
        if(size()==0){
            head = p;
        }else{
            Node test = head;
            if(last == null){
                while(test.next!= null ){
                    test = test.next;
                }
                test.next = p;
            }else{
                while(test.next!= last ){
                    test = test.next;
                }
                test.next = p;
                test.next.next = last;
            }
        }
        size++;
    }
    public void set(int index, T target){
        nodeAt(index).setItem(target);
    }
    public Node getNext(Node n){
        return n.getNext();
    }
    public int size(){
        return size;
    }
    public void clear(){
        Node p=head;
         while(p!=null){
             p=p.next;
             size--;
         }
    }

}
