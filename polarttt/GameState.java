package polarttt;

public class GameState {
	// nodes in the current game state
	Node[][] nodes;
	// diagonals for this game
	Diagonal[][] diagonals;

	int numX;
	int numY;

	/** Default constructor for game */
	public GameState(int circles, int lines) {
		this.numX = circles;
		this.numY = lines;

		nodes = new Node[numX][numY];

		createNodesAndNeighbors();
		createDiagonals();

	}

	/** Constructor to be used with an existing game state */
	public GameState(Node[][] currentState) {
		numX = currentState.length;
		numY = currentState[0].length;

		nodes = new Node[numX][numY];

		createDiagonals();

		// clones the existing state
		for (int circles = 0; circles < numX; circles++) {
			for (int lines = 0; lines < numY; lines++) {
				Node entry = new Node(currentState[circles][lines].getX(),
						currentState[circles][lines].getY());
				entry.setPlayer(currentState[circles][lines].getPlayer());
				nodes[circles][lines] = entry;
			}
		}

		// sets all neighbors
		for (int i = 0; i < numX; i++) {
			for (int j = 0; j < numY; j++) {
				setNeighbors(nodes[i][j]);
			}
		}
	}

	/**
	 * Creates empty diagonals for this game, then calls method to populate
	 * diagonals with the correct Nodes
	 */
	private void createDiagonals() {
		// There are two diagonals originating from each line: one clockwise,
		// one counterclockwise
		// Diagonal [0][x] refers to the counterclockwise diagonal from line x
		diagonals = new Diagonal[2][numY];
		// create counterclockwise diagonals
		for (int j = 0; j < numY; j++) {
			diagonals[0][j] = new Diagonal(j, false);
		}
		// create clockwise diagonals
		for (int j = 0; j < numY; j++) {
			diagonals[1][j] = new Diagonal(j, true);
		}

		// adds all nodes on the "zeroth" circle
		// and sets each of their diagonals as they're created
		for (int i = 0; i < numY; i++){
			diagonals[0][i].add(nodes[0][i]);
			setDiagonal(diagonals[0][i]);
			diagonals[1][i].add(nodes[0][i]);
			setDiagonal(diagonals[1][i]);
		}
		
	}

	/** Adds nodes to specified diagonal */
	private void setDiagonal(Diagonal a) {
		// base case
		if (a.length() == numX){
			return;
		}
		// step 1: get node on the end of the linked list
		Node endOfList = a.getEnd();
		
		// step 2: find the coordinates of the correct neighbor, depending on type
		int circle;
		int line;

		// always increment X
		circle = endOfList.getX() + 1;
		
		if (a.isClockwise()){
			if (endOfList.getY() == (numY - 1)){
				// wrap around
				line = 0;
			} else {
				// increment y
				line = endOfList.getY() + 1;
			}
		} else {
			if (endOfList.getY() == 0){
				//wrap around
				line = numY - 1;
			} else {
				// decrement y
				line = endOfList.getY() - 1;
			}
		}
		
		// step 3: add the neighbor
		a.add(nodes[circle][line]);
		
		// step 4: recurse
		setDiagonal(a);
	}

	/** Creates Nodes for the game, all initialized to empty */
	private void createNodesAndNeighbors() {
		// create edge nodes
		for (int y = 0; y < numY; y++) {
			nodes[numX - 1][y] = new Node(numX - 1, y);
			nodes[0][y] = new Node(0, y);
		}
		// create inner nodes
		for (int y = 0; y < numY; y++) {
			for (int x = 1; x < numX - 1; x++) {
				nodes[x][y] = new Node(x, y);
			}
		}

		// set all neighbors
		for (int i = 0; i < numX; i++) {
			for (int j = 0; j < numY; j++) {
				setNeighbors(nodes[i][j]);
			}
		}
	}

	/** Sets neighbors for a node in the graph */
	private void setNeighbors(Node a) {
		int counterclockwise = a.getY() - 1;
		int clockwise = a.getY() + 1;

		// special case: on "nth" radial line of grid
		if (a.getY() == (numY - 1)) {
			clockwise = 0;
		}

		// special case: on "zeroth" radial line of grid
		if (a.getY() == 0) {
			counterclockwise = numY - 1;
		}

		// for all
		a.addNeighbor(nodes[a.getX()][clockwise]);
		a.addNeighbor(nodes[a.getX()][counterclockwise]);
		// for those not on outermost arc
		if (a.getX() != (numX - 1)) {
			a.addNeighbor(nodes[a.getX() + 1][a.getY()]);
			a.addNeighbor(nodes[a.getX() + 1][counterclockwise]);
			a.addNeighbor(nodes[a.getX() + 1][clockwise]);
		}
		// for those not on innermost arc
		if (a.getX() != 0) {
			a.addNeighbor(nodes[a.getX() - 1][a.getY()]);
			a.addNeighbor(nodes[a.getX() - 1][counterclockwise]);
			a.addNeighbor(nodes[a.getX() - 1][clockwise]);
		}

	}

	public Diagonal[][] getDiagonals() {
		return diagonals;
	}

	public Node[][] getNodes() {
		return nodes;
	}

	public int getNumX() {
		return numX;
	}

	public int getNumY() {
		return numY;
	}

}
