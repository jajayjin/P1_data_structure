import java.security.InvalidParameterException;

public class Arraylist<T> {
    public Object[] array;
    private int size=0;
    public static int cap = 2;

    public Arraylist(){
        array = new Object[cap];
        size=0;
    }
    public void add(Object item){
        if(size==cap){
        array = resizearray();
        }
        array[size] = item;
        size++;

    }
    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        } else {
            System.arraycopy(array, index + 1, array, index, size - index - 1);
            array[size - 1] = null;
            System.out.println("remove already");
            size--;
        }
    }
    public void remove(Object item){
        int index=0;
        int j=0;
        for(int i = 0;i<array.length;i++) {
            if(array[i]==(item)) {
                j+=1;
                index=i;
                remove(index);
                System.out.println("remove already");
            }
        }
        if(j==0){
            throw new InvalidParameterException("Cannot find this value in this list.");
        }

    }
    public boolean contains(Object item){
        int j=0;
        if(size()==0){
            return false;
        }
        for(int i = 0;i<size();i++) {
            if(array[i]==(item)) {
                return true;
            }
        }
        return false;
    }
    public T get(int index){
        if(index < 0 || index> cap || index>size){
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        return (T) array[index];

    }
    public void set(int index, T Items){
        if(index < 0 || index> cap || index>size){
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }else {
            array[index] = Items;
        }
    }
    public void getArray() {
        for(Object items: array) {
            System.out.println(items);
        }

    }
    public int size() {
        return size;
    }
    //for resize array
    public Object[] resizearray() {
            cap*=2;
            Object[] newarray = new Object[cap];
            System.arraycopy(array,0,newarray,0,size);
        return newarray;
    }
    public boolean isEmpty(){
        if(size()==0){
            return true;
        }else{
            return false;
        }
    }
}
