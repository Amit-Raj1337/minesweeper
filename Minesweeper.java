import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Minesweeper {

	
	JFrame frame=new JFrame("Minesweeper");
	JPanel masterPanel=new JPanel();
	JPanel panel1=new JPanel(new GridLayout(0,9));
	JPanel panel2=new JPanel();
	
	
	JButton resetButton=new JButton("Reset");
	int rowlength,columnlength;
	
	JButton [][] grid= new JButton[9][9];
		
	public Minesweeper() {
	
		 rowlength=9;
		 columnlength=9;
		
	} 	
	
	public void GridInitializer()
	{
			
		for(int i=0;i<rowlength;i++)
		{
			for( int j=0;j<columnlength;j++)
			{
				grid[i][j]=new JButton();
				panel1.add(grid[i][j]);
				grid[i][j].setBackground(Color.BLACK);
				
			}
			
		}// end of nested loop
		
		panel2.add(resetButton);
		
		panel1.setPreferredSize(new Dimension(500, 500));
		
		panel1.setBackground(Color.GREEN);
	
		
		masterPanel.add(panel1,BorderLayout.CENTER);
		masterPanel.add(panel2,BorderLayout.PAGE_END);
		
		
		frame.setContentPane(masterPanel);
	
		frame.pack();
		frame.setVisible(true);
	
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
						
	} // end of GridInitializer.
	
}//end of class
