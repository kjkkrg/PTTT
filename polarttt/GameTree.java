package polarttt;

public class GameTree {
	
	TreeNode root;
	int depthReached;
	int nodesEvaluated;
	// TODO: maybe heuristic should be passed in here,
	// and evaluation should happen in this class instead of TreeNode?
	// also, will need a separate method to iterate back up the tree
	// to assign values
	
	public GameTree(Node[][] currentGameState, int firstPlayer, int secondPlayer){
		
		// root has a null parent
		root = new TreeNode(currentGameState, firstPlayer, secondPlayer, null);
		depthReached = 0;
		nodesEvaluated = 0;
		
		root.createNextBranch();
		// TODO: maxDepth should be some sort of command line parameter, I think
		createPlies(1, 4, root);
		
	}
	
	/** Creates all the plies in a tree, up until the specified maximum depth 
	 * NOTE: currently, the root is NOT considered its own ply. 
	 * TODO: This ^ may need to change!*/	
	protected void createPlies(int currentDepth, int maxDepth, TreeNode current){
		//TODO: testing, remove
		System.out.println("\n\n\n************************ Branch In Ply " + currentDepth + " ************************\n\n");
		
		// base case
		if (currentDepth == maxDepth){
			System.out.println("We're at the maximum depth");
			return;
		} else {
			// creates the branches for all of the current node's children
			for (TreeNode i : current.getChildren()){
				System.out.println("\n-----NEW BRANCH-----");
				i.createNextBranch();
			}
			currentDepth++;
			// TODO: Is this method call even the right thing to do here?
			for (TreeNode i : current.getChildren()){
				createPlies(currentDepth, maxDepth, i);
			}
		}
	}
	
	public TreeNode getRoot() {
		return root;
	}

	public int getDepthReached() {
		return depthReached;
	}

	public int getNodesEvaluated() {
		return nodesEvaluated;
	}
	
}
