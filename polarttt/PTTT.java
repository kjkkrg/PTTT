package polarttt;

import java.util.Scanner;

import javax.swing.JFrame;

public class PTTT {
	Game game;
	int circles;
	int lines;
	Player pa;
	Player pb;
	static PTTT thisGame;

	public static void main(String[] args) {
		
		thisGame = new PTTT();


//		JFrame frame = new JFrame("Polar Tic-Tac-Toe");
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.getContentPane().add(new PolarTTTPanel());
//
//		// shows panel
//		frame.pack();
//		frame.setVisible(true);
	}
	
	public PTTT(){
		Scanner commandLineScanner = new Scanner(System.in);

		// step 1: get desired number of lines and circles
		// TODO: we will need to do better idiot-proofing or exception throwing
		// on the scanner stuff
                //**********hard code values to make testing easier******************
//		System.out
//				.println("Please input the desired dimensions of the board in the form");
//		System.out.println("a,b");
//		System.out
//				.println("Where a is the desired number of circles and b is the desired number of radial lines");
//		System.out
//				.println("Or, please input d if you would like the default dimensions of 4 x 12");
//
//		String input1 = commandLineScanner.next();
                String input1 = "d";
		System.out.println(input1);
		if (input1.contains((CharSequence) ",")) {
			String[] dimensions = input1.split(",");
			circles = Integer.parseInt(dimensions[0]);
			lines = Integer.parseInt(dimensions[1]);
		} else {
			circles = 4;
			lines = 12;
		}

		// step 2: get types of players
		int input2 = 3;
//		while ((input2 > 2) || (input2 < 0)) {
//			System.out.println("Please enter 0 if both players are humans,");
//			System.out
//					.println("1 if one player is human and the other is an AI,");
//			System.out.println("or 2 if both players are AIs.");
//			input2 = commandLineScanner.nextInt();
//		}
//		
		// randomizes player numbers
		double playerNum = Math.random();
		if (playerNum > .5) playerNum = 1;
		else playerNum = 0;
		//input2=3;
		// creates players
		if (input2 == 0){
			pa = new HumanPlayer((int) playerNum);
			pb = new HumanPlayer((int) (1-playerNum));
		} else if (input2 == 1){
			pa = new HumanPlayer((int) playerNum);
			pb = new AIPlayer((int) (1-playerNum));
		} else {
			pa = new AIPlayer((int) playerNum);
			pb = new AIPlayer((int) (1-playerNum));
		}
		
		game = new Game(circles, lines, pa, pb,800);
		
		commandLineScanner.close();
	JFrame frame = new JFrame("Polar Tic-Tac-Toe");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new PolarTTTPanel());

		// shows panel
		frame.pack();
		frame.setVisible(true);
        }

}