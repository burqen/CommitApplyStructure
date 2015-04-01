
public class CommitApplyStructure {
	private Node currentLeader;
	private Node lastCommitted;

	public CommitApplyStructure() {
		currentLeader = new Node(-1);
		lastCommitted = currentLeader;
	}

	public void commit() {
		Node tmp = new Node(lastCommitted.id + 1);
		lastCommitted.next = tmp;
		if (lastCommitted == currentLeader)
			tmp.nextToLead = true;
		lastCommitted = tmp;	
	}

	public void apply(int id) {
		if (id <= currentLeader.id || id > lastCommitted.id)
			return;

		Node tmp = currentLeader;
		while (tmp.id != id) {
			tmp = tmp.next;
		}

		tmp.applied = true;

		if (tmp.nextToLead) {
			while (tmp.next != null && tmp.next.applied)
				tmp = tmp.next;
			currentLeader = tmp;
			if (tmp.next != null)
				tmp.next.nextToLead = true;
		}
	}

	public int lead() {
		return currentLeader.id;
	}

	private class Node {
		private int id;
		private Node next;
		private boolean nextToLead;
		private boolean applied;

		Node(int id) {
			this.id = id;
			nextToLead = false;
			applied = false;
			next = null;
		}
	}
}
