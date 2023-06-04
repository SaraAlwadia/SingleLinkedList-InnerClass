package sll_lec;
import java.util.StringJoiner;
public class SingleLinkedList<E> {
    // Nested Class
    /*<listing chapter="2" number="1">*/
    /**
     * A Node is the building block for the SingleLinkedList
     */
    private static class Node<E> {
        // Data Fields

        /**
         * The reference to the data.
         */
        private E data;
        /**
         * The reference to the next node.
         */
        private Node<E> next = null;

        // Constructors
        /**
         * Creates a new node with a null next field
         *
         * @param dataItem The data stored
         */
        public Node(E dataItem) {
            data = dataItem;
            next = null;
        }

        /**
         * Construct a node with the given data value and link
         *
         * @param dataItem The data stored
         * @param nodeRef The node referenced by new node
         */
        public Node(E dataItem, Node<E> nodeRef) {
            data = dataItem; // 5
            next = nodeRef; 
        }

    }
    /*</listing>*/
    // Data fields
    /**
     * A reference to the head of the list
     */
    private Node<E> head = null;
    /**
     * The size of the list
     */
    private int size = 0;

    // Helper Methods
    /**
     * Insert an item as the first item of the list.
     *
     * @param item The item to be inserted
     */
    private void addFirst(E item) {
        head = new Node<>(item, head);
        size++;
    }

    /**
     * Add a node after a given node
     *
     * @param node The node which the new item is inserted after
     * @param item The item to insert
     */
    private void addAfter(Node<E> node, E item) {
        node.next = new Node<>(item, node.next);
        size++;
    }

    /**
     * Remove the first node from the list
     *
     * @returns The removed node's data or null if the list is empty
     */
    private E removeFirst() {
        Node<E> temp = head;
        if (head != null) {
            head = head.next;
        }
        if (temp != null) {
            size--;
            return temp.data;
        } else {
            return null;
        }
    }

    /**
     * Remove the node after a given node
     *
     * @param node The node before the one to be removed
     * @returns The data from the removed node, or null if there is no node to
     * remove
     */
    private E removeAfter(Node<E> node) {
        Node<E> temp = node.next;
        if (temp != null) {
            node.next = temp.next;
            size--;
            return temp.data;
        } else {
            return null;
        }
    }

    /**
     * Find the node at a specified index
     *
     * @param index The index of the node sought
     * @returns The node at index or null if it does not exist
     */
    private Node<E> getNode(int index) {
        Node<E> node = head;
        for (int i = 0; i < index && node != null; i++) {
            node = node.next;
        }
        return node;
    }

    // Public Methods
    /**
     * Get the data value at index
     *
     * @param index The index of the element to return
     * @return The data at index
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(Integer.toString(index));
        }
        Node<E> node = getNode(index);
        return node.data;
    }

    /**
     * Set the data value at index
     *
     * @param index The index of the item to change
     * @param newValue The new value
     * @return The data value previously at index
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public E set(int index, E newValue) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(Integer.toString(index));
        }
        Node<E> node = getNode(index);
        E result = node.data;
        node.data = newValue;
        return result;
    }

    /**
     * Insert a new item before the one at position index, starting at 0 for the
     * list head. The new item is inserted between the one at position index-1
     * and the one formerly at position index.
     *
     * @param index The index where the new item is to be inserted
     * @param item The item to be inserted
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public void add(int index, E item) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(Integer.toString(index));
        }
        if (index == 0) {
            addFirst(item);
        } else {
            Node<E> node = getNode(index - 1);
            addAfter(node, item);
        }
    }

    /**
     * Append the specified item to the end of the list
     *
     * @param item The item to be appended
     * @return true (as specified by the Collection interface)
     */
    public boolean add(E item) {
        add(size, item);
        return true;
    }

    /*<exercise chapter="2" section="5" type="programming" number="4">*/
    /**
     * Remove the item at the specified position in the list. Shifts any
     * subsequent items to the left (subtracts one from their indices). Returns
     * the item that was removed.
     *
     * @param index The index of the item to be removed
     * @return The item that was at the specified position
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(Integer.toString(index));
        }
        Node<E> removedNode = null;
        if (index == 0) {
            return removeFirst();
        } else {
            Node<E> node = getNode(index - 1);
            return removeAfter(node);
        }
    }
    /*</exercise>*/
    
    /*<exercise chapter="2" section="5" type="programming" number="5">*/
    /** Remove the first occurrence of element item.
     * @param item The item to be removed
     * @return true if item is found and removed; otherwise, return false.
     */
    public boolean remove(E item) {
        if (head == null) {
            return false;
        }
        if (head.data.equals(item)) {
            removeFirst();
            return true;
        }
        Node<E> p = head;
        while (p.next != null) {
            if (p.next.data.equals(item)) {
                removeAfter(p);
                return true;
            } else {
                p = p.next;
            }
        }
        return false;
    }
    /*</exercise>*/
    

    /*<exercise chapter="2" section="5" type="programming" number="2">*/
    /**
     * Query the size of the list
     *
     * @return The number of objects in the list
     */
    int size() {
        return size;
    }
    /*</exercise>*/
    
    /*<exercise chapter="2" section="5" type="programming" number="3">*/
    /**
     * Returns the first occurrence of the specified element in this list,
     * or -1 if this list does not contain the element.
     * @param o element to search for
     * @return  the index of the first occurrence of the specified element
     */
    public int indexOf(Object o) {
        Node<E> p = head;
        int index = 0;
        while (p != null) {
            if (p.data.equals(o)) {
                return index;
            }
            p = p.next;
            index++;
        }
        return -1;
    }
    /*</exercise>*/

    /**
     * Obtain a string representation of the list
     *
     * @return A String representation of the list
     */
    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner(" --> ", "[", "]");
        Node<E> p = head;
        while (p != null) {
            sj.add(p.data.toString());
            p = p.next;
        }
        return sj.toString();
    }


}
/*</listing>*/

