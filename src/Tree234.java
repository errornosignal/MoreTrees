import java.util.ArrayList;
import java.util.List;

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
        private List<Node> childArray;
        private List<DataItem> itemArray;

        public Node getChild(int index) {
            return this.childArray.get(index);
        }

        public Node getParent() {
            return this.parent;
        }

        public int getNumberOfItems() {
            return this.numberOfItems;
        }

        public DataItem getItem (int index) {
            return this.itemArray.get(index);
        }

        public boolean isFull() {
            return this.numberOfItems == ORDER - 1;
        }

        public boolean isLeaf() {
            return this.childArray.get(0) == null;
        }

        //You must explain in detail why this is a good idea
        @SuppressWarnings("unchecked")
        public Node() {
            this.numberOfItems = 0;
            this.parent = null;
            this.childArray = new ArrayList<>(ORDER);
            this.itemArray = new ArrayList<>(ORDER - 1);

            for (int index = 0; index < ORDER; index++);
        }

        public int findItem(T key) {
            int index = 0;
            while (index < ORDER -1) {
                if (this.itemArray.get(index) == null) {
                    return - 1;
                }
                else if (this.itemArray.get(index).data.compareTo(key) == 0) {
                    return index;
                }//else not found doNothing();
                index++;
            }
            return -1; //no match found, return negative index
        }

        //removes highest value item (value at the end)
        public DataItem removeItem() {
            DataItem temp = this.itemArray.get(this.numberOfItems - 1);
            this.itemArray.set(this.numberOfItems - 1, null);
            this.numberOfItems--;
            return temp;
        }

        //assumes that the node is not full!
        public int insertItem(DataItem newItem) {
            T newKey = newItem.data;
            for(int index = ORDER - 2; index >= 0; index--) {
                if (this.itemArray.get(index) != null) {
                    T currentKey = this.itemArray.get(index).data;
                    if (newKey.compareTo(currentKey) < 0) {  //currentKey larger than newKey
                        this.itemArray.set(index + 1, this.itemArray.get(index));
                    }
                    else { //currentKey is smaller that newKey
                        this.itemArray.set(index + 1, this.itemArray.get(index));
                        return index + 1;
                    }
                }
            }
            this.itemArray.set(0, newItem);
            return 0;
        }
    }

    private Node root = new Node();

    @Override
    public boolean add (T element){
        Node current = this.root;
        DataItem tempItem = new DataItem(element);
        boolean hasHitLeaf = false;
        while(!hasHitLeaf) {
            if (current.isFull()) {
                this.split(current);
                //back it up and search again
                current = current.getParent();
                current = this.getNextChild(current, element);
            }
            else if (current.isLeaf()) {
                hasHitLeaf = true;
            }
            else {
                current = this.getNextChild(current, element);
            }
        }
        current.insertItem(tempItem);
        return true;
    }

    @Override
    public boolean contains (T element){
        Node current = this.root;
        int childNumber = 0;
        while(true) {
            if ((childNumber = current.findItem(element)) >= 0) {
                    return true;
            }
            else if (current.isLeaf()) {
                return false;
            }
            else {
                current = this.getNextChild(current, element);
            }
        }
    }

    private Node getNextChild(Node current, T element) {
        int numberOfItems = current.getNumberOfItems();
        int index = 0;
        while (index < numberOfItems) {
            // is element (key) less than the value of the data at the current index?
            if (element.compareTo(current.getItem(index).data) < 0) {
                return current.getChild(index);
            } // else element is greater than data, doNothing();
            index++;

        return current.getChild(index);
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