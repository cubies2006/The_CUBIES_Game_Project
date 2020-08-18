import javax.microedition.lcdui.*;
import java.io.IOException;

public class CubiesInstructions extends Canvas
{
    private CubiesMain midlet;
	private Image instructionBackground, ArrowUp, ArrowDown;
	private int InstructionStatus;
	private String instruction1, instruction2, instruction3, instruction4;
	private String instruction5, instruction6, instruction7, instruction8;
	private String instruction9, instruction10, instruction11, instruction12;
	private String instruction13, instruction14, instruction15, instruction16;
	private String instruction17, instruction18, instruction19, instruction20;
	private String instruction21;
	
	public CubiesInstructions(CubiesMain midlet) 
	{	
		this.midlet = midlet;
		try
		{
			instructionBackground = Image.createImage("/Game Screens/CubiesInstructions.png");
			ArrowDown = Image.createImage("/Game Directions/ArrowDown.png");
			ArrowUp = Image.createImage("/Game Directions/ArrowUp.png");
			instruction1 = ("   Given a randomized cube pattern and a set");
			instruction2 = ("of cubes to be eliminated, your objective is to");
			instruction3 = ("eliminate a number of similar cube patterns.");
			
			instruction4 = ("   If you  accidentally  or  intentionally hit your");
			instruction5 = ("cube pattern in a different cube pattern, then");
			instruction6 = ("your  cube  pattern  will  become  part of  the");
			instruction7 = ("cubes to be eliminated.");
			
			instruction8 = ("   You have  the  freedom  to  hit your cube in");
			instruction9 = ("the Sky Platform in order to eliminate consec-");
			instruction10 = ("utive similar cubes arranged in a column.");
			
			instruction11 = ("   You also have  the option  to  use  a  Super");
			instruction12 = ("Cube to eliminate a  number  of  different  cu-");
			instruction13 = ("bes arranged in a row or in a column.");
			
			instruction14 = ("   In  order for  you  to  progress through  the");
			instruction15 = ("game, you must successfully eliminate all cu-");
			instruction16 = ("bes before the  time  runs  out.  Otherwise, it");
			instruction17 = ("will mean Game Over."); 

			instruction18 = ("   The final score you earn in a level is deter-");
			instruction19 = ("mined by 3 factors namely: the current score");
			instruction20 = ("you earn,  the  number  of  Super  Cubes left,");
			instruction21 = ("and the remaining time.");
		}
		catch(IOException ioe) {}
    }
	
	protected void paint(Graphics g)
	{
		g.drawImage(instructionBackground, this.getWidth() / 245, this.getHeight() / 300, Graphics.TOP|Graphics.LEFT);
		if (InstructionStatus == 0)
		{
			g.setColor( 255, 255, 255);
			g.setFont(Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_PLAIN, Font.SIZE_MEDIUM));
			g.drawString(instruction1, 11, 140, Graphics.TOP|Graphics.LEFT);
			g.drawString(instruction2, 11, 150, Graphics.TOP|Graphics.LEFT);
			g.drawString(instruction3, 11, 160, Graphics.TOP|Graphics.LEFT);
				
			g.drawString(instruction4, 11, 180, Graphics.TOP|Graphics.LEFT);
			g.drawString(instruction5, 11, 190, Graphics.TOP|Graphics.LEFT);
			g.drawString(instruction6, 11, 200, Graphics.TOP|Graphics.LEFT);
			g.drawString(instruction7, 11, 210, Graphics.TOP|Graphics.LEFT);
				
			g.drawString(instruction8, 11, 230, Graphics.TOP|Graphics.LEFT);
			g.drawString(instruction9, 11, 240, Graphics.TOP|Graphics.LEFT);
			g.drawString(instruction10, 11, 250, Graphics.TOP|Graphics.LEFT);
			g.drawImage(ArrowDown, 112, 270, Graphics.TOP|Graphics.LEFT);
		}
		else if (InstructionStatus == 1)
		{
			g.setColor( 255, 255, 255);
			g.drawImage(ArrowUp, 112, 125, Graphics.TOP|Graphics.LEFT);
			g.drawString(instruction11, 11, 140, Graphics.TOP|Graphics.LEFT);
			g.drawString(instruction12, 11, 150, Graphics.TOP|Graphics.LEFT);
			g.drawString(instruction13, 11, 160, Graphics.TOP|Graphics.LEFT);
			
			g.drawString(instruction14, 11, 180, Graphics.TOP|Graphics.LEFT);
			g.drawString(instruction15, 11, 190, Graphics.TOP|Graphics.LEFT);
			g.drawString(instruction16, 11, 200, Graphics.TOP|Graphics.LEFT);
			g.drawString(instruction17, 11, 210, Graphics.TOP|Graphics.LEFT);
				
			g.drawString(instruction18, 11, 230, Graphics.TOP|Graphics.LEFT);
			g.drawString(instruction19, 11, 240, Graphics.TOP|Graphics.LEFT);
			g.drawString(instruction20, 11, 250, Graphics.TOP|Graphics.LEFT);
			g.drawString(instruction21, 11, 260, Graphics.TOP|Graphics.LEFT);
		}
		setFullScreenMode(true);		
	}
	
	protected void keyPressed(int keyCode)
	{
		if (keyCode == -7)
		{
			midlet.setCurrent("MainMenu");
			InstructionStatus = 0;
		}
		else
		{
			int gameAction = getGameAction(keyCode);
			if (gameAction == UP)
			{
				InstructionStatus = 0;
			}
			if (gameAction == DOWN)
			{
				InstructionStatus = 1;
			}
			repaint();
		}
	}
}