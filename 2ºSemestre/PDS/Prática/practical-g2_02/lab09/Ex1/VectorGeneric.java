package lab09.Ex1;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class VectorGeneric<T> {
    private T[] vec;
    private int nElem;
    private final int ALLOC = 50;
    private int dimVec;

    @SuppressWarnings("unchecked")
    public VectorGeneric(int numElem) {
        vec = (T[]) new Object[numElem];
        nElem = 0;
    }

    public boolean addElem(T elem) {
        if (elem == null)
            return false;

        ensureSpace();
        vec[nElem++] = elem;
        return true;
    }

    private void ensureSpace() {
        if(nElem > dimVec) {
            dimVec += ALLOC;
            @SuppressWarnings("unchecked")
            T[] newArray = (T[]) new Object[dimVec];
            System.arraycopy(vec, 0, newArray, 0, nElem);
            vec = newArray;
        }
    }

    public boolean removeElem(T elem) {
        for (int i = 0; i < nElem; i++) {
            if(vec[i].equals(elem)) {
                if(nElem - i - 1 > 0)  // not the last element
                    System.arraycopy(vec, i + 1, vec, i, nElem - i - 1);
                vec[--nElem] = null;
                return true;
            }
        }
        return false;
    }

    public int totalElem() {
        return nElem;
    }

    public T getElem(int i) {
        return (T) vec[i];
    }

    // ----------------------------------- Iterator -----------------------------------
    public Iterator<T> iterator() {
        return (this).new VectorIterator<T>();
    }

    private class VectorIterator<K> implements Iterator<K> {

        private int index;

        public VectorIterator() {
            index = 0;
        }

        @Override
        public boolean hasNext() {
            if (index < nElem)
                return true;
            else
                return false;
        }

        @SuppressWarnings("unchecked")
        public K next() {
            if(hasNext()) 
                return (K) VectorGeneric.this.getElem(index++);
            
            throw new NoSuchElementException(String.format("There are only %d elements!", nElem));
        }

        public void remove() {
            throw new UnsupportedOperationException("Invalid operation for this iterator! It'a an optional operation so it's not implemented.");
        }

    }

    // ----------------------------------- ListIterator -----------------------------------
    public ListIterator<T> listIterator(int index) {
        return (this).new VectorListIterator<T>(index);
    }

    public ListIterator<T> listIterator() {
        return (this).new VectorListIterator<T>();
    }

    private class VectorListIterator<K> implements ListIterator<K> {

        private int index;

        public VectorListIterator() {
            index = 0;
        }

        public VectorListIterator(int index) {
            if(index < 0 || index > nElem)
                throw new IndexOutOfBoundsException(String.format("Index %d is out of bounds!", index));
            this.index = index;
        }

        @Override
        public boolean hasNext() {
            if (index < nElem)
                return true;
            else
                return false;
        }

        @SuppressWarnings("unchecked")
        public K next() {
            if(hasNext()) 
                return (K) VectorGeneric.this.getElem(index++);
            
            throw new NoSuchElementException(String.format("There are only %d elements!", nElem));
        }

        @Override
        public boolean hasPrevious() {
            if (index > 0)
                return true;
            else
                return false;
        }

        @SuppressWarnings("unchecked")
        public K previous() {
            if(hasPrevious()) 
                return (K) VectorGeneric.this.getElem(--index);
            
            throw new NoSuchElementException(String.format("There are only %d elements!", nElem));
        }

        @Override
        public int nextIndex() {
            if(hasNext())
                return index + 1;
            else
                return nElem;
        }

        @Override
        public int previousIndex() {
            if(hasPrevious())
                return index - 1;
            else
                return - 1;
        }

        public void remove() {
            throw new UnsupportedOperationException("Invalid operation for this iterator! It'a an optional operation so it's not implemented.");
        }

        public void set(K e) {
            throw new UnsupportedOperationException("Invalid operation for this iterator! It'a an optional operation so it's not implemented.");
        }

        public void add(K e) {
            throw new UnsupportedOperationException("Invalid operation for this iterator! It'a an optional operation so it's not implemented.");
        }

    }

}