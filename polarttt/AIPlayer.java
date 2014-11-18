package polarttt;
import java.util.LinkedList;

public class AIPlayer implements Player {
	
	int maxSearchDepth;
	//TODO:the next two should be parameters reported by reportMove() and its delegates (minimax, etc)?
	int numNodesEvaluated;
	int timeToSelect;
	
	int playerNum;
	LinkedList<Edge> edges;
	
	public AIPlayer(int playerNum){
		this.playerNum = playerNum;
		edges = new LinkedList<Edge>();
	}


	//@Override
	public void chooseMove() {
		// TODO Auto-generated method stub

	}

	//@Override
	public void reportMove() {
		reportNumEvaluated();
		reportTimetoSelect();
		
	}

	private int reportTimetoSelect() {
		// TODO stub
		return -1;
		
	}

	private int reportNumEvaluated() {
		// TODO stub
		return -1;
		
	}
	
	//@Override
	public int getPlayerNum() {
		return playerNum;
	}

	//@Override
	public LinkedList<Edge> getEdges() {
		return edges;
	}


	//@Override
	public void addEdge(Edge newEdge) {
		edges.add(newEdge);
		
	}

//
	//@Override
	public boolean hasEdge(Edge seeking) {
		if(edges.isEmpty()){
			return false;
		} else if (edges.contains(seeking)){
			return true;
		}
		return false;
	}
}
