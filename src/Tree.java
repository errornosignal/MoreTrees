
public interface Tree<T extends Comparable<? super T>> {

    boolean add(T element);
    boolean contains(T element);
    boolean remove(T element);
    int size();
}
