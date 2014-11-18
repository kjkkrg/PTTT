package polarttt;

import java.util.LinkedList;

public class Diagonal {
	LinkedList<Node> nodesOnDiagonal;
	int lineOfOrigin;
	boolean clockwise;
	
	public Diagonal(int lineOfOrigin, boolean clockwise){
		this.lineOfOrigin = lineOfOrigin;
		this.clockwise = clockwise;
		nodesOnDiagonal = new LinkedList<Node>();	
	}
	
	
	/** Adds Node n to the diagonal */
	public void add(Node n){
		if (!nodesOnDiagonal.contains(n)){
			nodesOnDiagonal.add(n);
		}
	}
	
	/** Gets the number of nodes on the diagonal belonging to the given Player */
	public int getNumberBelongingToPlayer(Player player){
		int count = 0;
		
		for (Node i : nodesOnDiagonal){
			if (i.getPlayer() == player.getPlayerNum()){
				count++;
			}
		}
		
		return count;
	}
	
	/** Returns the number of nodes in this diagonal, marked and unmarked */
	public int length(){
		return nodesOnDiagonal.size();
	}
	
	public String toString(){
		String nodes = "";
		for (Node i : nodesOnDiagonal){
			nodes += i.toString();
		}
		return nodes;
	}


	public boolean isClockwise(){
		return clockwise;
	}


	/** Returns the end of the linked list of nodes */
	protected Node getEnd(){
		return nodesOnDiagonal.getLast();
	}
	
}
