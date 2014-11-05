package backgammon.gui;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import backgammon.game.Point;


public class BoardPanel extends JPanel{
	
	Point[] allPoints = null;
	int redBore, blackBore;
	Image imgBG;
	Graphics2D g2;

	/**`
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	
	public BoardPanel(){
		
		setSize(1169,637);
		
	}
	
	
	public void paintComponent(Graphics g){
		
		
		super.paintComponent(g);
		this.setBackground(Color.WHITE);
		g2 = (Graphics2D) g;

	    //setting font for the drawn strings on the game
		g2.setFont(new Font("default", Font.BOLD, 16));
		
		//Drawing background
		imgBG = Toolkit.getDefaultToolkit().getImage("images/Backgammon blank board2.png");
	    g2.drawImage(imgBG, 0, 0, this);
	    

	    //Drawing checkers on board
	    //loop through all points
	    for(int i =0; i<allPoints.length;i++){
	    	//check if its blank, end if it is
		    if(allPoints[i].getBlackCount() != 0 || allPoints[i].getRedCount() != 0){
		    	//check colour
		    	if(allPoints[i].getBlackCount() != 0){
	    			g2.setColor(Color.BLACK);
		    	}else{
	    			g2.setColor(Color.RED);
		    	}
		    	
		    	//print 0 point checkers
		    	if(i==0 || i==25){
		    				    		
		    		//deciding on the side
		    		if(i==0){
		    			
			    		g2.fillOval(510, 150, 50, 50);
			    		g2.setColor(Color.WHITE);
			    		g2.drawOval(510, 150, 50, 50);
			    		g2.drawString(allPoints[i].numEither()+"", 532, 180);
			    		
		    		}else{
		    			
			    		g2.fillOval(510, 300, 50, 50);
			    		g2.setColor(Color.WHITE);
			    		g2.drawOval(510, 300, 50, 50);
			    		g2.drawString(allPoints[i].numEither()+"", 532, 330);
			    		
		    		}
		    		

		    	//print top right half checkers
		    	}else if(i>0 && i<7){
		    	
		    		g2.fillOval(990-(i*70), 80, 50, 50);
		    		g2.setColor(Color.WHITE);
		    		g2.drawOval(990-(i*70), 80, 50, 50);
		    		g2.drawString(allPoints[i].numEither()+"", 1010-(i*70), 110);
		    		
		    	//print top left half checkers
		    	}else if(i>6 && i<13){
		    		
		    		g2.fillOval(500-((i-6)*70), 80, 50, 50);
		    		g2.setColor(Color.WHITE);
		    		g2.drawOval(500-((i-6)*70), 80, 50, 50);
		    		g2.drawString(allPoints[i].numEither()+"", 520-((i-6)*70), 110);
		    		
		    		
		    	//print bottom left half checkers
		    	}else if(i>12 && i<19){
		    		
		    		g2.fillOval(20+((i-12)*70), 430, 50, 50);
		    		g2.setColor(Color.WHITE);
		    		g2.drawOval(20+((i-12)*70), 430, 50, 50);
		    		g2.drawString(allPoints[i].numEither()+"", 40+((i-12)*70), 460);

		    	//print bottom right half checkers
		    	}else if(i>18 && i<25){
		    		
		    		g2.fillOval(500+((i-18)*70), 430, 50, 50);
		    		g2.setColor(Color.WHITE);
		    		g2.drawOval(500+((i-18)*70), 430, 50, 50);
		    		g2.drawString(allPoints[i].numEither()+"", (520+(i-18)*70), 460);
		    	}
		    }
	    }
	    
	    //Drawing checkers that have been bored
	    if(redBore != 0){
	    	
	    	g2.setColor(Color.RED);
	    	g2.fillOval(1060, 150, 50, 50);
    		g2.setColor(Color.WHITE);
    		g2.drawOval(1060, 150, 50, 50);
    		g2.drawString(redBore+"", 1080, 180);
	    }
	    if(blackBore != 0){
	    	g2.setColor(Color.BLACK);
	    	g2.fillOval(1060, 390, 50, 50);
    		g2.setColor(Color.WHITE);
    		g2.drawOval(1060, 390, 50, 50);
    		g2.drawString(redBore+"", 1080, 420);
	    }
	    
	    

	    
	}
	
	public void printCheckers(Point[] c, int rb, int bb){
		allPoints = c;
		redBore = rb;
		blackBore = bb;
		repaint();
	}
	
	
}