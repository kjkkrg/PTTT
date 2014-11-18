package polarttt;

/*Kevin Jakub
 * 5/3/2013
 * Draw a square jelly roll recursively
 */
import java.awt.BorderLayout;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.awt.Dimension;
import java.awt.Graphics;
import java.lang.Math;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JRadioButton;
import javax.swing.JPanel;
import javax.swing.ButtonGroup;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.ItemListener;
import javax.swing.JRadioButton;
import java.awt.event.*;

public class PolarTTTPanel extends JPanel {

	private final int VERTICES = 12, ROWS = 4;
	private final int NODESIZE = 12, ADJ = NODESIZE / 2;
	private final int FRAMESIZE = 800;
	private final int x0 = FRAMESIZE / 2;
	private final int y0 = FRAMESIZE / 2 - 50;
	private final int SCALE = FRAMESIZE / 6; // distance between circles
	private final double ANGLE = 2 * Math.PI / 12; // the ANGLE of rotation in
													// degrees
	private Vector<Double> xs = new Vector<Double>(); // x coord
	private Vector<Double> ys = new Vector<Double>(); // y coord
	private Vector<Integer> playerOneNodes = new Vector();
	private Vector<Integer> playerTwoNodes = new Vector();
	private Vector<Integer> allMovesNodes = new Vector();
	private int moveCount = 0;
	private String vscomp = "Play Computer";
	private String vsfriend = "Play a Friend";
	private String compvscomp = "Watch Computer";
	private JLabel output = new JLabel(
			"WELCOME TO POLAR TIC-TACTOE!  Click one of the game options above to start!");

	Node[][] nodesArr;

	public PolarTTTPanel() {
		Player pa;
		Player pb;
		int input2 = 3;
		// while ((input2 > 2) || (input2 < 0)) {
		// System.out.println("Please enter 0 if both players are humans,");
		// System.out
		// .println("1 if one player is human and the other is an AI,");
		// System.out.println("or 2 if both players are AIs.");
		// input2 = commandLineScanner.nextInt();
		// }
		//
		// randomizes player numbers
		double playerNum = Math.random();
		if (playerNum > .5)
			playerNum = 1;
		else
			playerNum = 0;
		// input2=3;
		// creates players
		if (input2 == 0) {
			pa = new HumanPlayer((int) playerNum);
			pb = new HumanPlayer((int) (1 - playerNum));
		} else if (input2 == 1) {
			pa = new HumanPlayer((int) playerNum);
			pb = new AIPlayer((int) (1 - playerNum));
		} else {
			pa = new AIPlayer((int) playerNum);
			pb = new AIPlayer((int) (1 - playerNum));
		}
		Game current = new Game(4, 12, pa, pb, FRAMESIZE);
		nodesArr = new Node[ROWS][VERTICES];
		setLayout(new BorderLayout());
		// add(new r)
		// JButton newGame = new JButton("NEW GAME AGain")
		setPreferredSize(new Dimension(FRAMESIZE, FRAMESIZE));
		setBackground(Color.black);

		JPanel buttons = new JPanel();
		JPanel out = new JPanel();
		// Create the buttons.

		JRadioButton firstButton = new JRadioButton(vscomp);
		// firstButton.setKeyAccelerator('1');
		firstButton.setActionCommand(vscomp);
		// firstButton.setSelected(true);

		JRadioButton secondButton = new JRadioButton(vsfriend);
		// secondButton.setKeyAccelerator('2');
		secondButton.setActionCommand(vsfriend);

		JRadioButton thirdButton = new JRadioButton(compvscomp);
		// secondButton.setKeyAccelerator('2');
		thirdButton.setActionCommand(compvscomp);

		// Group the radio buttons.
		ButtonGroup group = new ButtonGroup();
		group.add(firstButton);
		group.add(secondButton);
		group.add(thirdButton);
		// buttons.getHeight();
		// Register a listener for the radio buttons.
		RadioListener myListener = new RadioListener();
		firstButton.addActionListener(myListener);
		firstButton.addChangeListener(myListener);
		firstButton.addItemListener(myListener);
		firstButton.addItemListener(myListener);
		secondButton.addActionListener(myListener);
		secondButton.addChangeListener(myListener);
		secondButton.addItemListener(myListener);
		thirdButton.addActionListener(myListener);
		thirdButton.addChangeListener(myListener);
		thirdButton.addItemListener(myListener);
		buttons.add(firstButton);
		buttons.add(secondButton);
		buttons.add(thirdButton);
		add(buttons, BorderLayout.NORTH);
		add(out, BorderLayout.SOUTH);

		out.add(output);

		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				// event = "click";
				int x1 = arg0.getX();
				int y1 = arg0.getY();
				System.out.println("\nCoor: " + x1 + ", " + y1);
				for (int i = 0; i < xs.size(); i++) {
					// see if click is on a vertex
					if ((x1 <= (xs.elementAt(i) + NODESIZE) && x1 >= (xs
							.elementAt(i)))
							&& (y1 <= ys.elementAt(i) + NODESIZE && y1 >= ys
									.elementAt(i))) {
						// make sure vertex is unoccupied
						if (!allMovesNodes.contains(i)) {
							moveCount++;
							allMovesNodes.add(i);
							System.out.println("Node " + (i + 1) + " Coor: "
									+ xs.elementAt(i) + ", " + ys.elementAt(i));
							  String op = "Node " + (i + 1) + " Coor: " + xs.elementAt(i).intValue() + ", " + ys.elementAt(i).intValue() + " selected";
							// JOptionPane.showMessageDialog(null, "Node " + (i
							// + 1));
							// add node to player node vector
							if (moveCount % 2 != 0) {
								playerOneNodes.add(i);
								output.setText(op);
								JOptionPane.showMessageDialog(null, 
										"\t  Player " + 1 + "   Node "
												+ (i + 1));
							} else {
								playerTwoNodes.add(i);
								output.setText(op);
								JOptionPane.showMessageDialog(null,
										"\t  Player " + 2 + "   Node "
												+ (i + 1));
							}
						}// endif
						else {
							JOptionPane.showMessageDialog(null, "Node "
									+ (i + 1) + " is occupied!");
						}
						// redraw with new move
						paintChildren(PolarTTTPanel.this
								.getComponentGraphics(getGraphics()));
						break;
					}
				}
			}
		});
	}

	protected void paintChildren(Graphics page) {
		super.paintChildren(page);
		// draw main game if no move has been made
		// if (moveCount == 0) {
		// draw four large circles
		page.setColor(Color.blue);
		page.drawOval(x0 - SCALE / 2, y0 - SCALE / 2, SCALE, SCALE);
		page.drawOval(x0 - SCALE, y0 - SCALE, SCALE * 2, SCALE * 2);
		page.drawOval(x0 - 3 * SCALE / 2, y0 - 3 * SCALE / 2, SCALE * 3,
				SCALE * 3);
		page.drawOval(x0 - 2 * SCALE, y0 - 2 * SCALE, SCALE * 4, SCALE * 4);
		System.out.println("" + (x0 - SCALE * ADJ));
		// draw lines as well as dots on vertices. add values to vector for
		// later use
		for (int i = 1; i <= 12; i++) {
			page.drawLine(x0, y0, x0 + (int) (2 * SCALE * Math.cos(ANGLE * i)),
					y0 + (int) (2 * SCALE * Math.sin(ANGLE * i)));
			page.fillOval(x0 - ADJ + (int) (2 * SCALE * Math.cos(ANGLE * i)),
					y0 - ADJ + (int) (2 * SCALE * Math.sin(ANGLE * i)),
					NODESIZE, NODESIZE);
			xs.add(x0 - ADJ + (2 * SCALE * Math.cos(ANGLE * i)));
			ys.add(y0 - ADJ + (2 * SCALE * Math.sin(ANGLE * i)));
			page.fillOval(
					(x0 - ADJ + (int) ((2 * SCALE * Math.cos(ANGLE * i)) * .75)),
					y0 - ADJ + (int) ((2 * SCALE * Math.sin(ANGLE * i) * .75)),
					NODESIZE, NODESIZE);
			xs.add(x0 - ADJ + ((2 * SCALE * Math.cos(ANGLE * i)) * .75));
			ys.add(y0 - ADJ + ((2 * SCALE * Math.sin(ANGLE * i) * .75)));
			page.fillOval(
					(x0 - ADJ + (int) ((2 * SCALE * Math.cos(ANGLE * i)) * .50)),
					y0 - ADJ + (int) ((2 * SCALE * Math.sin(ANGLE * i) * .50)),
					NODESIZE, NODESIZE);
			xs.add(x0 - ADJ + ((2 * SCALE * Math.cos(ANGLE * i)) * .50));
			ys.add(y0 - ADJ + ((2 * SCALE * Math.sin(ANGLE * i) * .50)));
			page.fillOval(
					(x0 - ADJ + (int) ((2 * SCALE * Math.cos(ANGLE * i)) * .25)),
					y0 - ADJ + (int) ((2 * SCALE * Math.sin(ANGLE * i) * .25)),
					NODESIZE, NODESIZE);
			xs.add(x0 - ADJ + ((2 * SCALE * Math.cos(ANGLE * i)) * .25));
			ys.add(y0 - ADJ + ((2 * SCALE * Math.sin(ANGLE * i) * .25)));
		}
		// }
		// draw player 1
		page.setColor(Color.red);
		for (Integer i : playerOneNodes) {
			page.fillOval(xs.elementAt(i).intValue(), ys.elementAt(i)
					.intValue(), NODESIZE + 2, NODESIZE + 2);
		}
		// draw player two
		page.setColor(Color.green);
		for (Integer i : playerTwoNodes) {
			page.fillOval(xs.elementAt(i).intValue(), ys.elementAt(i)
					.intValue(), NODESIZE + 2, NODESIZE + 2);
		}
		page.setColor(Color.black);

	}// end painchildren

	private class RadioListener implements ActionListener, // only one event
															// type needed
			ChangeListener, // for curiosity only
			ItemListener { // for curiosity only

		public void actionPerformed(ActionEvent e) {
			String factoryName = null;
			System.out.print("ActionEvent received: ");
			// xs = new Vector<Double>(); //x coord
			// ys = new Vector<Double>(); //y coord
			// playerOne = new Vector();
			//
			// playerTwo = new Vector();
			// allMoves = new Vector();
			if (e.getActionCommand() == vscomp) {
				restart();
				System.out.println(vscomp + " pressed.");
				// reset
				// paintChildren(PolarTTTPanel.this.getComponentGraphics(getGraphics()));
			} else if (e.getActionCommand() == vsfriend) {
				restart();
				System.out.println(vsfriend + " pressed.");
				// paintChildren(PolarTTTPanel.this.getComponentGraphics(getGraphics()));
				// add(new PolarTTTPanel());
			} else {
				restart();
				System.out.println(compvscomp + " pressed.");
				// paintChildren(PolarTTTPanel.this.getComponentGraphics(getGraphics()));
			} 
		}
public void restart() {
	playerOneNodes = new Vector();
	 playerTwoNodes = new Vector();
	 allMovesNodes = new Vector();
	 moveCount = 0;
	 repaint();
	add(new PolarTTTPanel());
}
		public void itemStateChanged(ItemEvent e) {
			System.out.println("ItemEvent received: "
					+ e.getItem()
					+ " is now "
					+ ((e.getStateChange() == ItemEvent.SELECTED) ? "selected."
							: "unselected"));
		}

		public void stateChanged(ChangeEvent e) {
			System.out.println("ChangeEvent received from: " + e.getSource());
		}
	}
}
