
public class Tree234<T extends Comparable<? super T>> implements Tree<T> {

    private class DataItem {
        public T data;

        public DataItem(T data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return String.format("/ %s", this.data.toString());
        }
    }

    private class Node {
        private static final int ORDER = 4;
        private int numberOfItems;
        private Node parent;
        private Node childArray[];
        private DataItem itemArray[];

        public Node getChild(int index) {
            return this.childArray[index];
        }

        public Node getParent() {
            return this.parent;
        }

        public int getNumberOfItems() {
            return this.numberOfItems;
        }

        public DataItem getItem (int index) {
            return this.itemArray[index];
        }

        public boolean isFull() {
            return this.numberOfItems == ORDER - 1;
        }

        public boolean isLeaf() {
            return this.childArray[0] == null;
        }

        //You must explain in detail why this is a good idea
        @SuppressWarnings("unchecked")
        public Node() {
            this.numberOfItems = 0;
            this.parent = null;
            this.childArray = (Node[]) new Object[ORDER];
            this.itemArray = (DataItem[]) new Object[ORDER -1];
        }

        public int findItem(T key) {
            int index = 0;
            while (index < ORDER -1) {
                if (this.itemArray[index] == null) {
                    return - 1;
                }
                else if (this.itemArray[index].data.compareTo(key) == 0) {
                    return index;
                }//else not found doNothing();
                index++;
            }
            return -1; //no match found, return negative index
        }

        //removes highest value item (value at the end)
        public DataItem removeItem() {
            DataItem temp = this.itemArray[this.numberOfItems - 1];
            this.itemArray[this.numberOfItems - 1] = null;
            this.numberOfItems--;
            return temp;
        }

        //assumes that the node is not full!
        public int insertItem(DataItem newItem) {
            T newKey = newItem.data;
            for(int index = ORDER - 2; index >= 0; index--) {
                if (this.itemArray[index] != null) {
                    T currentKey = this.itemArray[index].data;
                    if (newKey.compareTo(currentKey) < 0) {  //currentKey larger than newKey
                        this.itemArray[index - 1] = this.itemArray[index];
                    }
                    else { //currentKey is smaller that newKey
                        this.itemArray[index + 1] = this.itemArray[index];
                        return index + 1;
                    }
                }
            }
            this.itemArray[0] = newItem;
            return 0;
        }
    }

    @Override
    public boolean add (T element){
        return false;
    }

    @Override
    public boolean contains (T element){
        return false;
    }

    @Override
    public boolean remove(T element) {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }
}