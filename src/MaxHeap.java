
/**
 * Class to implement MaxHeap data structure used in Dijkstra algo with heap to store fringes
 */
public class MaxHeap {

    private int capacity;
    private int currentSize;
    private int[] H; //Heap node names
    private HeapNode[] D; //Heap node values
    private int[] P; //Heap node pos

    public MaxHeap(int capacity) {
        this.capacity = capacity;
        H = new int[capacity];
        D = new HeapNode[capacity];
        P = new int[capacity];
        this.currentSize = -1;
    }

    //maximum
    public int extractMaxIndex() {
        if (currentSize != -1) {
            //swap weights
            HeapNode max = D[0];
            D[0] = D[currentSize];
            D[currentSize] = null;
            //swap names
            int temp = H[0];
            H[0] = H[currentSize];
            H[currentSize] = temp;
            //swap pos
            P[H[0]] = 0;
            P[H[currentSize]] = -1;
            currentSize--;
            heapify(D, 0);
            return max.getName();
        }
        return -1;
    }

    //insert
    public void insert(int bw, int w) {
        if (currentSize == capacity) {
            System.out.println("Max Heap is full");
            return;
        }
        currentSize++;
        int idx = currentSize;
        D[idx] = new HeapNode(bw, w);
        P[w] = idx;
        H[idx] = w;
        heapifyUp(idx);
    }

    public void heapifyUp(int pos) {
        int parentIdx = (pos - 1) / 2;
        int currentIdx = pos;
        while (currentIdx > 0 && D[parentIdx].getValue() < D[currentIdx].getValue()) {
            swap(currentIdx, parentIdx);
            swapNames(currentIdx, parentIdx);
            swapPositions(currentIdx, parentIdx);
            currentIdx = parentIdx;
            parentIdx = (parentIdx - 1) / 2;
        }
    }

    public void swap(int a, int b) {
        HeapNode temp = D[a];
        D[a] = D[b];
        D[b] = temp;
    }

    public void swapNames(int a, int b) {
        int temp = H[a];
        H[a] = H[b];
        H[b] = temp;
    }

    public void swapPositions(int a, int b) {
        P[H[a]] = a;
        P[H[b]] = b;
    }

    //delete
    public void delete(int pos) {
        if (pos != -1 && currentSize != -1) {
            //swap weights
            D[pos] = D[currentSize];
            D[currentSize] = null;
            //swap names
            int temp = H[pos];
            H[pos] = H[currentSize];
            H[currentSize] = temp;
            //swap pos
            P[H[pos]] = pos;
            P[H[currentSize]] = -1;
            heapify(D, pos);
            currentSize--;
        }
    }


    public void heapify(HeapNode[] D, int k) {
        int largest = k;
        int l = 2 * k + 1;
        int r = 2 * k + 2;

        // If left child is larger than root
        if (l < currentSize && D[l].getValue() > D[largest].getValue())
            largest = l;

        // If right child is larger than largest so far
        if (r < currentSize && D[r].getValue() > D[largest].getValue())
            largest = r;

        // If largest is not root
        if (largest != k) {
            swap(k, largest);
            swapNames(k, largest);
            swapPositions(k, largest);
            // Recursively call heapify for the affected sub-tree
            heapify(D, largest);
        }
    }

    public int getPosByHeapNodeName(int x) {
        return P[x];
    }
}

    class HeapNode {
        private int value;
        private int name;

        public HeapNode(int value, int name) {
            this.value = value;
            this.name = name;
        }

        public int getValue() {
            return value;
        }

        public int getName() {
            return name;
        }
    }
