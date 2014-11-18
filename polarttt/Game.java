package polarttt;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
public class Game extends JPanel {
  private final int NODESIZE = 12, ADJ = NODESIZE / 2;
    private int FRAMESIZE = 800;
    private final int x0 = FRAMESIZE / 2;
    private final int y0 = FRAMESIZE / 2 - 50;
    private final int SCALE = FRAMESIZE / 6;  //distance between circles
    private final double ANGLE = 2 * Math.PI / 12;  // the ANGLE of rotation in degrees
    private Vector<Double> xs = new Vector<Double>();  //x coord
    private Vector<Double> ys = new Vector<Double>();  //y coord
    private Vector<Integer> playerOne = new Vector();
    private Vector<Integer> playerTwo = new Vector();
    private Vector<Integer> allMoves = new Vector();
    private int moveCount = 0;
	// circles, lines gotten through command-line input
	// number of circles
	private int numX;
	// number of radial lines
	private int numY;
	// players; types of which gotten through command line
	Player pX;
	Player pO;
	boolean firstMove;
	GameState nodes;

	public Game(int numX, int numY, Player pX, Player pO,int size) {
		this.numX = numX;
		this.numY = numY;
		this.pX = pX;
		this.pO = pO;
                FRAMESIZE=size;
		firstMove = true;
		nodes = new GameState(numX, numY);

	}public Game(int numX, int numY, Player pX, Player pO) {
		this.numX = numX;
		this.numY = numY;
		this.pX = pX;
		this.pO = pO;
                FRAMESIZE=800;
		firstMove = true;
		nodes = new GameState(numX, numY);

                    }
                
            
        
        

  

//    Game(int i, int i0, Player player1, Player player2) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

	/**
	 * Checks whether or not a move is legal (i.e. next to a previously played
	 * node) call this before move() using user/AI input
	 */
	public boolean isValidMove(Node chosen) {
		if (firstMove == true) {
			System.out.println("It's the first move");
			return true;
		}

		for (Node i : chosen.getNeighbors()) {
			if (i.getPlayer() != 0) {
				// returns true if the node has a neighbor that is played
				return true;
			}
		}
		// all neighbors are blank/unplayed
		return false;
	}

	/**
	 * Modifies the board state with a specific move, which MUST be evaluated to
	 * be valid before being passed in as an argument
	 */
	public void move(Node changed, Player currentPlayer) {
		if (firstMove == true) {
			firstMove = false;
		}
		// sets the Node to belong to the current player
		changed.setPlayer(currentPlayer.getPlayerNum());

		// create and add any new edges as applicable
		for (Node i : changed.getNeighbors()) {
			if (i.getPlayer() == currentPlayer.getPlayerNum()) {
				Edge possibleNewEdge = new Edge(changed, i, changed.getPlayer());
				if (!currentPlayer.hasEdge(possibleNewEdge)) {
					currentPlayer.addEdge(possibleNewEdge);
				}
			}
		}

	}

	/** Reports output of win-checker. Should be called after each successful move */
	public boolean checkIfWin() {
		// TODO: calls winChecker object (yet to be implemented)
		return false;
	}

	public void draw() {
		// does nothing I'm involved with
	}

	/**
	 * 
	 * 
	 * 
	 * 
	 * Below here be getters and setters
	 * 
	 * 
	 * 
	 * 
	 * */

	public int getNumX() {
		return numX;
	}

	public void setNumX(int numX) {
		this.numX = numX;
	}

	public int getNumY() {
		return numY;
	}

	public void setNumY(int numY) {
		this.numY = numY;
	}

	public Node[][] getGameState() {
		return nodes.getNodes();
	}

	public boolean isFirstMove() {
		return firstMove;
	}

	public Diagonal[][] getDiagonals() {
		return nodes.getDiagonals();
	}
        

}
