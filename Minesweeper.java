import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Minesweeper {

	
	JFrame frame=new JFrame("Minesweeper");
	JPanel masterPanel=new JPanel();
	JPanel panel1=new JPanel(new GridLayout(0,9));
	JPanel panel2=new JPanel();
	
	
	char tempgrid[][]=new char[9][9];
	char original[][]=new char[9][9];
	char flaggrid[][]=new char[9][9];
	
	
	JButton resetButton=new JButton("Reset");
	int rowlength,columnlength;
	
	JButton [][] grid= new JButton[9][9];
	int i,j;
		
	public Minesweeper() {
	
		 rowlength=9;
		 columnlength=9;
		 
		 
		 for(i=0;i<rowlength;i++){
			 for(j=0;j<columnlength;j++){
				 tempgrid[i][j]='o';
				 original[i][j]='o';
				 flaggrid[i][j]=0;
				 
			 }
		 }
		
	} 	
	
	public void GridInitializer()
	{
			
		for(int i=0;i<rowlength;i++)
		{
			for( int j=0;j<columnlength;j++)
			{
				grid[i][j]=new JButton();
				panel1.add(grid[i][j]);
			//	grid[i][j].setBackground(Color.BLACK);
				
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
		
		
		gridButtonListeners();
		ResetButtonListeners();
		
						
	} // end of GridInitializer.
	
	
	int [][] getRandomVect	(int minX, int minY,int maxX, int maxY)
	{
	      //Vectors can be translated, multipled or scaled later
	      int[][] result=new int[1][2];
	      result[0][0]=(int) (Math.random()*(maxX-minX)+minX);
	      result[0][1]=(int) (Math.random()*(maxY-minY)+minY);
	      return result;
	}

	 
	
	public void ResetButtonListeners()
	{
		
		resetButton.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				
				resetGame();
				selectBombs();
				
			}
		});
		
	}// end of resetButtonListeners method
	
	
	public void gridButtonListeners()
	{
		
		for( i=0;i<rowlength;i++)
		{
			for (  j=0;j<columnlength;j++)
			{
				
				grid[i][j].addActionListener(new ActionListener() {
					
					
					public void actionPerformed(ActionEvent e) {
					
						
						JButton temp=(JButton) e.getSource();
						
						for(int a=0;a<rowlength;a++){
							
							for(int b=0;b<columnlength;b++)
							{
								
								if(grid[a][b]==e.getSource())
								{
									
									System.out.println(a+"  "+b);
								//grid[a][b].setText("ffff");
									grid[a][b].setEnabled(false);
								nextInstanceOfMinesweeper(a, b);
								
									//grid[a][b].setBackground(Color.CYAN);
								//grid[i][j].setEnabled(false);
								}								
							}
						}						 
						
						
						//temp.setText("S");
						
						
						
						
					//	grid[i][j].setBackground(Color.CYAN);
						
						
					}
				});
			}
		}// end of loops
		
	}// end of gridbuttonlisteners  method
	
	 
	 
	 public void selectBombs()
	 {
		 int [][] randomVector;
		 int a,b;
		 
		 for(int i=0;i<10;i++)
		 {
		 
		 randomVector= getRandomVect(0,0,rowlength,columnlength);
		 		 
		 
		 a=randomVector[0][0];
		 b=randomVector[0][1];
		 
		 original[a][b]='b';
		 
		 
			ImageIcon myIcon2=new ImageIcon("D:/Amit/MineSweeper/src/resources/bomb.jpg");
			
						    
		    Image img = myIcon2.getImage();
			Image newimg = img.getScaledInstance(53, 53,  java.awt.Image.SCALE_SMOOTH);
			ImageIcon newIcon = new ImageIcon(newimg);
			 
			 grid[a][b].setIcon(newIcon);
 
			
		 }// end of for loop
		 
		 
	 }// end selectBombs method
	 
	 
	 
	 public void resetGame()
	 {
		 
		 for(int i=0;i<rowlength;i++)
			{
				for( int j=0;j<columnlength;j++)
				{
					
					grid[i][j].setBackground(Color.BLACK);
					grid[i][j].setText("");
					
				}
				
			}// end of nested loop
		 
		 
		 
	 }// end resetGame method
	 
	 
	 
	 public void nextInstanceOfMinesweeper(int xCoordinate,int yCoordinate)
		{
		 
		 
		 String k=" ";
		int counter=0;
		int i=xCoordinate;
		int j=yCoordinate;
		
		//grid[i][j].setBackground(Color.MAGENTA);
				if((i==0)||(i==(rowlength-1))||(j==0)||(j==(columnlength-1)))//if element is present in either edges (rows or columns)
				{	
					if((i==0)||(i==(rowlength-1))&&(j==0)||(j==(columnlength-1))) //if it's one of the four corner values
					{
						
						if(i==0&&j==0)//top left corner
						{
													
								if(original[i][j+1]=='b')
								counter++;
								if(original[i+1][j]=='b')
									counter++;
								if(original[i+1][j+1]=='b')
									counter++;
								
								flaggrid[i][j]=1;
								grid[i][j].setEnabled(false);
								
								if(counter!=0)
										{
									 k=k+counter;
									grid[i][j].setText(k);
									
									
									//grid[i][j].setBackground(Color.GRAY);
									tempgrid[i][j]='o'; 
									    } 
								else
								{
									if(flaggrid[i][j+1]==0)
									nextInstanceOfMinesweeper(i,(j+1));
									
									if(flaggrid[i+1][j]==0)
									nextInstanceOfMinesweeper((i+1), j);
									
									if(flaggrid[i+1][j+1]==0)
									nextInstanceOfMinesweeper((i+1),(j+1));
									
									
								}
							
						}
						
						if(i==0&&j==(columnlength-1))//top right corner
						{
										
								if(original[i][j-1]=='b')
								counter++;
								if(original[i+1][j-1]=='b')
									counter++;
								if(original[i+1][j]=='b')
									counter++;
																
								flaggrid[i][j]=1;
								grid[i][j].setEnabled(false);
								
								
								if(counter!=0)
								{
							 k=k+counter;
							grid[i][j].setText(k);
							//grid[i][j].setEnabled(false);
							//grid[i][j].setBackground(Color.GRAY);
							tempgrid[i][j]='o'; 
								} 
								else
								{
									
									if(flaggrid[i][j-1]==0)
							nextInstanceOfMinesweeper(i,(j-1));

									if(flaggrid[i+1][j-1]==0)
									nextInstanceOfMinesweeper((i+1), (j-1));
							
									
									if(flaggrid[i+1][j]==0)
									nextInstanceOfMinesweeper((i+1),j);
								
								}
								
								
							
						}// end of top right corner
						
						
						if(i==(rowlength-1)&&j==0)//bottom left corner
						{
							
							
								if(original[i][j+1]=='b')
								counter++;
								if(original[i-1][j]=='b')
									counter++;
								if(original[i-1][j+1]=='b')
									counter++;
								

								flaggrid[i][j]=1;
								grid[i][j].setEnabled(false);
								
								if(counter!=0)
								{
							 k=k+counter;
							grid[i][j].setText(k);
							//grid[i][j].setEnabled(false);
							//grid[i][j].setBackground(Color.GRAY);
							tempgrid[i][j]='o'; 
							    } 
								else
								{ 
									

									if(flaggrid[i][j+1]==0)
							nextInstanceOfMinesweeper(i,(j+1));
									

									if(flaggrid[i-1][j]==0)
							nextInstanceOfMinesweeper((i-1), j);
										

									if(flaggrid[i-1][j+1]==0)
							nextInstanceOfMinesweeper((i-1),(j+1));
							
							
								}
								
							
						}// end of bottom left corner
						
						
						if(i==(rowlength-1)&&j==(columnlength-1))//bottom right corner
						{
							
							
							if(original[i][j-1]=='b')
								counter++;
								if(original[i-1][j-1]=='b')
									counter++;
								if(original[i-1][j]=='b')
									counter++;
								
															
								flaggrid[i][j]=1;
								grid[i][j].setEnabled(false);
								
								if(counter!=0)
								{
							 k=k+counter;
							grid[i][j].setText(k);
						//	grid[i][j].setEnabled(false);
							tempgrid[i][j]='o'; 
							    } 
								else
								{
									

									if(flaggrid[i][j-1]==0)
							nextInstanceOfMinesweeper(i,(j-1));
									

									if(flaggrid[i-1][j-1]==0)
							nextInstanceOfMinesweeper((i-1), (j-1));
									

									if(flaggrid[i-1][j]==0)
							nextInstanceOfMinesweeper((i-1),(j));
							
							
								}
									
								
							
						} //end of bottom right corner
						
						
												
						
					}//end of corner elements logic
					
					else
					{
						
						if(j==0)//left column elements that are not in the corner
						{
							counter=0;
							
								if(original[i-1][j]=='b') //counting the generic no. of live cells
								counter++;
								if(original[i-1][j+1]=='b')
									counter++;
								if(original[i][j+1]=='b')
									counter++;
								if(original[i+1][j]=='b')
									counter++;
								if(original[i+1][j+1]=='b')
									counter++;								
															
								flaggrid[i][j]=1;
								grid[i][j].setEnabled(false);
								
								if(counter!=0)
								{k="";
							 k=k+counter;
							grid[i][j].setText(k);
						//	grid[i][j].setEnabled(false);
							//grid[i][j].setBackground(Color.GRAY);
							tempgrid[i][j]='o'; 
							    } 
								else
								{
									
									if(flaggrid[i-1][j]==0)
									nextInstanceOfMinesweeper((i-1), (j));
									
									if(flaggrid[i-1][j+1]==0)
									nextInstanceOfMinesweeper((i-1), (j+1));
									
									if(flaggrid[i][j+1]==0)
									nextInstanceOfMinesweeper((i), (j+1));
									
									if(flaggrid[i+1][j]==0)
									nextInstanceOfMinesweeper((i+1), (j));
									
									if(flaggrid[i+1][j+1]==0)
									nextInstanceOfMinesweeper((i+1), (j+1));
							
							
								}
								
									
									
								
								
						}// end of left column
						
						if(j==(columnlength-1))//right column elements that are not in the corner
						{
							counter=0;
							
								if(original[i-1][j]=='b') //counting the generic no. of live cells
								counter++;
								if(original[i-1][j-1]=='b')
									counter++;
								if(original[i][j-1]=='b')
									counter++;
								if(original[i+1][j]=='b')
									counter++;
								if(original[i+1][j-1]=='b')
									counter++;								
															
								
								
								flaggrid[i][j]=1;
								grid[i][j].setEnabled(false);
								
								
								if(counter!=0)
								{k="";
							 k=k+counter;
							grid[i][j].setText(k);
						//	grid[i][j].setEnabled(false);
							//grid[i][j].setBackground(Color.GRAY);
							tempgrid[i][j]='o'; 
							    } 
								else
								{
									
									if(flaggrid[i-1][j]==0)
									nextInstanceOfMinesweeper((i-1), (j));
									
									if(flaggrid[i-1][j-1]==0)
									nextInstanceOfMinesweeper((i-1), (j-1));
									
									if(flaggrid[i][j-1]==0)
									nextInstanceOfMinesweeper((i), (j-1));
									
									if(flaggrid[i+1][j]==0)
									nextInstanceOfMinesweeper((i+1), (j));
									
									if(flaggrid[i+1][j-1]==0)
									nextInstanceOfMinesweeper((i+1), (j-1));
							
							
								}
								
								
						}// end of right column
						
						if(i==0)//top row elements that are not in the corner
						{
							counter=0;
							
								if(original[i][j-1]=='b') //counting the generic no. of live cells
								counter++;
								if(original[i+1][j-1]=='b')
									counter++;
								if(original[i+1][j]=='b')
									counter++;
								if(original[i+1][j+1]=='b')
									counter++;
								if(original[i+1][j]=='b')
									counter++;								
													
								flaggrid[i][j]=1;
								grid[i][j].setEnabled(false);
								
								if(counter!=0)
								{k="";
							 k=k+counter;
							grid[i][j].setText(k);
						//	grid[i][j].setEnabled(false);	
							
							//grid[i][j].setBackground(Color.GRAY);
							tempgrid[i][j]='o'; 
							    } 
								else
								{
									
									if(flaggrid[i][j-1]==0)
									nextInstanceOfMinesweeper((i), (j-1));
									
									if(flaggrid[i+1][j-1]==0)
									nextInstanceOfMinesweeper((i+1), (j-1));
									
									if(flaggrid[i+1][j]==0)
									nextInstanceOfMinesweeper((i+1), (j));
									
									if(flaggrid[i+1][j+1]==0)
									nextInstanceOfMinesweeper((i+1), (j+1));
									
									if(flaggrid[i+1][j]==0)
									nextInstanceOfMinesweeper((i+1), (j));
							
							
								}
								
								
						}// end of top row
						
						if(i==(rowlength-1))//bottom row elements that are not in the corner
						{
							counter=0;
							
								if(original[i][j-1]=='b') //counting the generic no. of live cells
								counter++;
								if(original[i-1][j-1]=='b')
									counter++;
								if(original[i-1][j]=='b')
									counter++;
								if(original[i-1][j+1]=='b')
									counter++;
								if(original[i-1][j]=='b')
									counter++;								
												
								
								flaggrid[i][j]=1;
								grid[i][j].setEnabled(false);
								
								if(counter!=0)
								{k="";
							 k=k+counter;
							grid[i][j].setText(k);
							//grid[i][j].setEnabled(false);
							//grid[i][j].setBackground(Color.GRAY);
							tempgrid[i][j]='o'; 
							    } 
								else
								{
									
									if(flaggrid[i][j-1]==0)
									nextInstanceOfMinesweeper((i), (j-1));
									
									if(flaggrid[i-1][j-1]==0)
									nextInstanceOfMinesweeper((i-1), (j-1));
									
									if(flaggrid[i-1][j]==0)
									nextInstanceOfMinesweeper((i-1), (j));
									
									if(flaggrid[i-1][j+1]==0)
									nextInstanceOfMinesweeper((i-1), (j+1));
									
									if(flaggrid[i-1][j]==0)
									nextInstanceOfMinesweeper((i-1), (j));
							
							
								}
								
								
								
						}// end of bottom row
						
						
					}// end of else 
					
				} //end of edge logic
				
				else//logic for remaining elements
				{
					
					counter=0;
					
					if(original[i-1][j-1]=='b') //counting the generic no. of live cells
					counter++;
					if(original[i-1][j]=='b')
						counter++;
					if(original[i-1][j+1]=='b')
						counter++;
					if(original[i][j+1]=='b')
						counter++;
					if(original[i+1][j+1]=='b')
						counter++;								
					if(original[i+1][j]=='b')
						counter++;
					if(original[i+1][j-1]=='b')
						counter++;
					if(original[i][j-1]=='b')
						counter++;
				
					flaggrid[i][j]=1;
					grid[i][j].setEnabled(false);
					
					if(counter!=0)
					{k="";
				 k=k+counter;
				grid[i][j].setText(k);
			//	grid[i][j].setEnabled(false);
				//tempgrid[i][j]='o'; 
				    } 
					else
					{
						
						if(flaggrid[i-1][j-1]==0)
						nextInstanceOfMinesweeper((i-1), (j-1));
						
						if(flaggrid[i-1][j]==0)
						nextInstanceOfMinesweeper((i-1), (j));
						
						if(flaggrid[i-1][j+1]==0)
						nextInstanceOfMinesweeper((i-1), (j+1));
						
						if(flaggrid[i][j+1]==0)
						nextInstanceOfMinesweeper((i), (j+1));
						
						if(flaggrid[i+1][j+1]==0)
						nextInstanceOfMinesweeper((i+1), (j+1));
						
						if(flaggrid[i+1][j]==0)
						nextInstanceOfMinesweeper((i+1), (j));
						
						if(flaggrid[i+1][j-1]==0)
						nextInstanceOfMinesweeper((i+1), (j-1));
						
						if(flaggrid[i][j-1]==0)
						nextInstanceOfMinesweeper((i), (j-1));
				
				
					}
					
					
					
				}
			
			
		
		}//end of nextInstanceofMinesweeper
	 
	 
	
	
}//end of class
