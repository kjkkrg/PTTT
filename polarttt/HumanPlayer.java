package polarttt;

import java.util.LinkedList;

public class HumanPlayer implements Player {

	int playerNum;
	LinkedList<Edge> edges;

	public HumanPlayer(int playerNum){
		this.playerNum = playerNum;
		edges = new LinkedList<Edge>();
	}
	

	@Override
	public void chooseMove() {
		// TODO This should mostly be graphics-driven, I'd imagine

	}

	@Override
	public void reportMove() {
		// TODO Just displaying what move was made
		
	}

	
	@Override
	public int getPlayerNum() {
		return playerNum;
	}


	@Override
	public LinkedList<Edge> getEdges() {
		return edges;
	}


	@Override
	public void addEdge(Edge newEdge) {
		edges.add(newEdge);
		
	}


	@Override
	public boolean hasEdge(Edge seeking) {
		if(edges.isEmpty()){
			return false;
		} else if (edges.contains(seeking)){
			return true;
		}
		return false;
	}
}
