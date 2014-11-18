package polarttt;
import java.util.LinkedList;

public interface Player {
	
	// needed for both to use win-checker
	// todo: should be linked list?
	LinkedList<Edge> getEdges();
	// needed for both to set color of Nodes played, etc
	int getPlayerNum();
	// places Node
	void chooseMove();
	void reportMove();
	void addEdge(Edge newEdge);
	boolean hasEdge(Edge seeking);

}
