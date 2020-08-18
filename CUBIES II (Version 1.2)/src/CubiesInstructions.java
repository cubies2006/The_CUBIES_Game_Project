import javax.microedition.lcdui.*;
import java.io.IOException;

public class CubiesInstructions extends Canvas
{
    private CubiesMain midlet;
	private Image instructionBackground, ArrowUp, ArrowDown;
	private int InstructionStatus;
	private String instruction1, instruction2, instruction3, instruction4, instruction5;
	private String instruction6, instruction7, instruction8, instruction9, instruction10;
	private String instruction11, instruction12, instruction13, instruction14, instruction15;
	
	public CubiesInstructions(CubiesMain midlet) 
	{	
		this.midlet = midlet;
		setFullScreenMode(true);			
		try
		{
			instructionBackground = Image.createImage("/Game Screens/CubiesInstructions.png");
			ArrowDown = Image.createImage("/Game Directions/ArrowDown.png");
			ArrowUp = Image.createImage("/Game Directions/ArrowUp.png");
			instruction1 = ("   Given a randomized cube pattern,");
			instruction2 = ("you have to eliminate all the cubes");
			instruction3 = ("in a limited period of time.");

			instruction4 = ("   If you accidentally or intentionally");
			instruction5 = ("hit your cube in a different cube, th-");
			instruction6 = ("en your cube will become part of the");
			instruction7 = ("cubes to be eliminated.");
			
			instruction8 = ("   Eliminate all cubes before the time");
			instruction9 = ("runs out.  Otherwise, it will mean Ga-");
			instruction10 = ("me Over."); 

			instruction11 = ("   The final score you earn in a level");
			instruction12 = ("is determined by  3 factors namely:");
			instruction13 = ("current score, number of Super Cub-");
			instruction14 = ("es left, and the remaining time.");
		}
		catch(IOException ioe) {}
    }
	
	protected void paint(Graphics g)
	{
		g.drawImage(instructionBackground, this.getWidth() / 176, this.getHeight() / 208, Graphics.TOP|Graphics.LEFT);
		g.setFont(Font.getFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL));
		if (InstructionStatus == 0)
		{
			g.setColor(255, 255, 255);
			g.drawString(instruction1, 13, 100, Graphics.TOP|Graphics.LEFT);
			g.drawString(instruction2, 13, 110, Graphics.TOP|Graphics.LEFT);
			g.drawString(instruction3, 13, 120, Graphics.TOP|Graphics.LEFT);
			
			g.drawString(instruction4, 13, 135, Graphics.TOP|Graphics.LEFT);
			g.drawString(instruction5, 13, 145, Graphics.TOP|Graphics.LEFT);
			g.drawString(instruction6, 13, 155, Graphics.TOP|Graphics.LEFT);
			g.drawString(instruction7, 13, 165, Graphics.TOP|Graphics.LEFT);
			
			g.drawImage(ArrowDown, 84, 180, Graphics.TOP|Graphics.LEFT);
		}
		else if (InstructionStatus == 1)
		{
			g.setColor(255, 255, 255);
			g.drawImage(ArrowUp, 84, 85, Graphics.TOP|Graphics.LEFT);			
			g.drawString(instruction8, 13, 100, Graphics.TOP|Graphics.LEFT);
			g.drawString(instruction9, 13, 110, Graphics.TOP|Graphics.LEFT);
			g.drawString(instruction10, 13, 120, Graphics.TOP|Graphics.LEFT);
			
			g.drawString(instruction11, 13, 135, Graphics.TOP|Graphics.LEFT);
			g.drawString(instruction12, 13, 145, Graphics.TOP|Graphics.LEFT);
			g.drawString(instruction13, 13, 155, Graphics.TOP|Graphics.LEFT);
			g.drawString(instruction14, 13, 165, Graphics.TOP|Graphics.LEFT);
		}	
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