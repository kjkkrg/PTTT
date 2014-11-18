/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package polarttt;

import javax.swing.JFrame;
import polarttt.*;

/**
 *
 * @author Other
 */
public class PolarTTT {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       // TODO Auto-generated method stub

		JFrame frame = new JFrame("Polar Tic-Tac-Toe");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new PolarTTTPanel());
		frame.pack();
		frame.setVisible(true);
    }
    
}
