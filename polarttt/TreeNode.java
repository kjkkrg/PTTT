package polarttt;

public class TreeNode {

	GameState gameState;
	boolean maxNode;
	// note: these refer to whose turn it is in the game
	// not whose move generated the current ply
	int currentPlayer;
	int nextPlayer;
	// used to generate children - 1:1 relationship
	LinkedList<Node> potentialMoves;
	int numChildren;
	ArrayList<TreeNode> children = new ArrayList<TreeNode>();
	// doubly linked
	TreeNode parent;
	// comes from heuristic
	double value;

	/** For root */
	public TreeNode(Node[][] currentState, int currentPlayer, int nextPlayer,
			TreeNode parent) {
		gameState = new GameState(currentState);
		this.currentPlayer = currentPlayer;
		this.nextPlayer = nextPlayer;
		potentialMoves = new LinkedList<Node>();

		// root is always a max node
		maxNode = true;

	}

	/** For rest of game nodes - takes a game state rather than an array */
	public TreeNode(GameState parentState, int currentPlayer, int nextPlayer,
			boolean maxNode, TreeNode parent) {
		gameState = parentState;
		this.currentPlayer = currentPlayer;
		this.nextPlayer = nextPlayer;
		potentialMoves = new LinkedList<Node>();
		this.maxNode = maxNode;

	}

	/**
	 * Counts the number of child nodes this node will have, and adds the
	 * potential moves to represent to a Linked List
	 */
	private void countChildren() {
		// iterates through each node in the game
		for (int circles = 0; circles < gameState.getNumX(); circles++) {
			for (int lines = 0; lines < gameState.getNumY(); lines++) {
				// if a node is played by either player...
				if (gameState.getNodes()[circles][lines].getPlayer() != 0) {
					// it iterates through each of its nodes neighbors...
					for (Node i : gameState.getNodes()[circles][lines]
							.getNeighbors()) {
						// and adds them to the list of potential moves
						// if they are unplayed and not already present
						if ((!potentialMoves.contains(i))
								&& (i.getPlayer() == 0)) {
							potentialMoves.add(i);
							// increments the number of child nodes
							numChildren++;
						}
					}
				}
			}
		}
	}

	/** Calls countChildren() and createAllChildren() */
	protected void createNextBranch() {
		countChildren();
		createAllChildren();
		// TODO: testing, remove
		// System.out.println("\nNumber of children for this branch: " +
		// children.size());
	}

	/** Loops through all potential moves, calls child creation for each */
	private void createAllChildren() {
		for (Node i : potentialMoves) {
			children.add(createChildNode(i));
		}
	}

	/**
	 * Creates a child node with the hypotheticalMove node played by the current
	 * player - also ensures that the player on the child node is the other
	 * player
	 */
	private TreeNode createChildNode(Node hypotheticalMove) {
		// chooses player for next ply - if this is a max node, the next
		// ply will be generated using the opponent's moves and vice versa
		int player;
		if (maxNode) {
			player = nextPlayer;
		} else {
			player = currentPlayer;
		}

		// creates a child node with game state identical to the current game
		// state
		// except with one move made
		GameState childState = new GameState(gameState.getNodes());
		childState.getNodes()[hypotheticalMove.getX()][hypotheticalMove.getY()]
				.setPlayer(player);
		TreeNode childNode = new TreeNode(childState, currentPlayer,
				nextPlayer, !maxNode, this);

		// TODO: testing, remove
		String max = "";
		if (childNode.isMaxNode()) {
			max = "MAX";
		} else {
			max = "MIN";
		}
		System.out
				.println("\nChild State: "
						+ max
						+ " "
						+ childState.getNodes()[hypotheticalMove.getX()][hypotheticalMove
								.getY()].toString());

		return childNode;
	}

	// TODO: make a method that will score a single child node w/heuristic
	// call on an entire ply in GameTree

	public Node[][] getGameState() {
		return gameState.getNodes();
	}

	public int getNumChildren() {
		return numChildren;
	}

	public LinkedList<Node> getPotentialMoves() {
		return potentialMoves;
	}

	public int getCurrentPlayer() {
		return currentPlayer;
	}

	public ArrayList<TreeNode> getChildren() {
		return children;
	}

	public TreeNode getParent() {
		return parent;
	}

	public boolean isMaxNode() {
		return maxNode;
	}

}
