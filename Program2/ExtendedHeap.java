import java.util.ArrayList;

public class ExtendedHeap {
	private ArrayList<Contestant> heap = new ArrayList<Contestant>();
	private int heapSizeLim;
	private ArrayList<Integer> handle = new ArrayList<Integer>();//contains places in heap and ID has

	public int getHeapSizeLim() {
		return heapSizeLim;
	}
	
	public ExtendedHeap(int size) {
		this.heap.add(null);
		this.handle.add(null);
		heapSizeLim = size;
	}
	
	public int getHeapIndex(int ID) {
		if (handle.size() > ID) {
			return handle.get(ID);
		}
		return 0;
	}
	
	public void findContestant(int ID) {
		int index = getHeapIndex(ID);
		if (index > 0) {
			System.out.println("Contestant <" + ID + "> is in the extended heap with score <" + heap.get(index).getTotalPoints() + ">.");
		} else {
			System.out.println("Contestant <" + ID + "> is not in the extended heap.");
		}
	}
	
	void heapSwap(int ind1, int ind2) {
		Contestant temp = heap.get(ind1);
		handle.set(heap.get(ind2).getID(), ind1);
		handle.set(heap.get(ind1).getID(), ind2);
		heap.set(ind1, heap.get(ind2));
		heap.set(ind2, temp);
	}
	
	void siftUp(int index) {
		if (index != 1) {
			int parentTotal = heap.get(index/2).getTotalPoints();
			int total = heap.get(index).getTotalPoints();
			while ((index != 1) && (parentTotal > total)) {
				heapSwap(index, index/2);
				index /= 2;
				if (index == 1) {
					break;
				}
				parentTotal = heap.get(index/2).getTotalPoints();
				total = heap.get(index).getTotalPoints();
			}
		}
	}
	
	public void insertContestant(int ID, int score) {
		if (heap.size() > heapSizeLim + 1) {
			System.out.println("Contestant <" + ID + "> could not be inserted because the extended heap is full.");
		} else if (getHeapIndex(ID) > 0) { //!=0 if contestant should not be reinserted after elimination
			System.out.println("Contestant <" + ID + "> is already in the extended heap: cannot insert.");
		} else {
			Contestant newContestant = new Contestant(ID, score);
			heap.add(newContestant);
			for (int i = handle.size() - 1; i < ID; i++) {//grow handle array if ID is bigger than handle array
				handle.add(-1);
			}
			handle.set(ID, heap.size()-1);//need to set index no matter handle size
			siftUp(heap.size()-1);
			System.out.println("Contestant <" + ID + "> inserted with initial score <" + score + ">.");
		}
	}
	
	public void minHeapify(int root_index) {
		int lowest_index = root_index;
		int left_index = 2 * root_index;
		int right_index = 2 * root_index + 1;
		if ((left_index < heap.size()) && (heap.get(left_index).getTotalPoints() < heap.get(lowest_index).getTotalPoints())) {
			lowest_index = left_index;
		}
		if ((right_index < heap.size()) && (heap.get(right_index).getTotalPoints() < heap.get(lowest_index).getTotalPoints())) {
			lowest_index = right_index;
		}
		if (lowest_index != root_index) {
			heapSwap(lowest_index, root_index);
			minHeapify(lowest_index);
		}
	}
	
	public Contestant extractMin() {
		if (heap.size() < 2) {//no elements
			return null;
		}
		if (heap.size() == 2) {//1 element
			handle.set(heap.get(1).getID(), -1);
			Contestant eliminated = heap.get(1);
			heap.remove(1);
			return eliminated;
		}
		Contestant top = heap.get(1);//2 or more elements
		handle.set(heap.get(1).getID(), -1);//set current head's heap index to -1 in handle
		heap.set(1, heap.get(heap.size() - 1));//make the last element in heap the new head
		handle.set(heap.get(1).getID(), 1);//update its index
		heap.remove(heap.size() - 1);//remove last element since there are now two
		minHeapify(1);
		return top;
	}
	
	public void eliminateWeakest() {
		Contestant eliminated = extractMin();
		if (eliminated != null) {
			System.out.println("Contestant <" + eliminated.getID() + "> with current lowest score <" + eliminated.getTotalPoints() + "> eliminated.");
		} else {
			System.out.println("No contestant can be eliminated since the extended heap is empty.");
		}
	}
	
	public void earnPoints(int ID, int points) {
		int index = getHeapIndex(ID);
		if (index > 0) {
			Contestant update = heap.get(index);
			update.addPoints(points);
			System.out.println("Contestant <" + ID + ">'s score increased by <" + points +"> points to <" + update.getTotalPoints() + ">.");
			heap.set(index, update);
			minHeapify(index);
		} else {
			System.out.println("Contestant <" + ID + "> is not in the extended heap.");
		}
	}
	
	public void losePoints(int ID, int points) {
		int index = getHeapIndex(ID);
		if (index > 0) {
			Contestant update = heap.get(index);
			update.subtractPoints(points);
			System.out.println("Contestant <" + ID + ">'s score decreased by <" + points +"> points to <" + update.getTotalPoints() + ">.");
			heap.set(index, update);
			siftUp(index);
		} else {
			System.out.println("Contestant <" + ID + "> is not in the extended heap.");
		}
	}
	
	public void crownWinner() {
		Contestant winner = null;
		while (heap.size() > 1) {
			winner = extractMin();
		}
		System.out.println("Contestant <" + winner.getID() + "> wins with score <" + winner.getTotalPoints() + ">!");
	}
	
	public void showContestants() {
		for (int i = 1; i < heap.size(); i++) {
			System.out.println("Contestant <" + heap.get(i).getID() + "> in extended heap location <" + i + "> with score <" + heap.get(i).getTotalPoints() + ">.");
		}
	}
	
	public void showHandles() {
		for (int i = 1; i <= heapSizeLim; i++) {
			int index = getHeapIndex(i);
			if (index > 0) {
				System.out.println("Contestant <" + i + "> stored in extended heap location <" + index + ">.");
			} else {
				System.out.println("There is no Contestant <" + i + "> in the extended heap: handle[<"  + i + ">] = -1.");
			}
		}
	}
	
	public void showLocation(int ID) {
		int index = getHeapIndex(ID);
		if (index > 0) {
			System.out.println("Contestant <" + ID + "> stored in extended heap location <" + index + ">.");
		} else {
			System.out.println("There is no Contestant <" + ID + "> in the extended heap: handle[<"  + ID + ">] = -1.");
		}
	}
	
	public void printArrayLists() {
		for (int i = 1; i < heap.size(); i++) {
			System.out.print("ID: " + heap.get(i).getID());
			System.out.print("; score: " + heap.get(i).getTotalPoints());
			System.out.println("; index in heap: " + handle.get(heap.get(i).getID()));
		}
	}
}
