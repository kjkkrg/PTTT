package polarttt;

import java.util.LinkedList;

public class Node {

	LinkedList<Node> neighbors;
	int player;
	int x;
	int y;

	public Node(int x, int y) {
		// node initialized to "open/no player"
		player = 0;
		this.x = x;
		this.y = y;
		// starts out with empty list of neighbors
		neighbors = new LinkedList<Node>();

	}

	/** Adds Node n to this node's list of neighbors */
	public void addNeighbor(Node n) {
		neighbors.add(n);
	}

	/** Returns all neighbors for this node */
	public LinkedList<Node> getNeighbors() {
		return neighbors;
	}

	/** Sets the color of a Node */
	public void setPlayer(int player) {
		this.player = player;
	}

	@Override
	public String toString() {
		return ("(" + x + "," + y + "; " + player + ")");
	}

	public boolean equals(Object o) {
		if (o == null) {
			return false;
		} else if (this.getClass() != o.getClass()) {
			return false;
		} else if ((this.x != ((Node) o).getX())
				|| (this.y != ((Node) o).getY())
				|| (this.player != ((Node) o).getPlayer())) {
			return false;
		}

		return true;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getPlayer() {
		return player;
	}
}
