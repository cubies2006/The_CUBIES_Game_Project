import javax.microedition.lcdui.*;
import javax.microedition.media.*;
import java.io.*;

public class CubiesTitle extends Canvas
{
	private CubiesMain midlet;
	private Image titleBackground;	

	public CubiesTitle(CubiesMain midlet) 
	{
		this.midlet = midlet;
		setFullScreenMode(true);		
		try
		{
			midlet.CubiesIntro.start();				
			titleBackground = Image.createImage("/Game Screens/CubiesTitleScreen.png");	
		}
		catch (MediaException me) {}			
		catch (IOException ioe) {}			
	}

	protected void paint(Graphics g)
	{	
		g.drawImage(titleBackground, this.getWidth() / 245, this.getHeight() / 300, Graphics.TOP|Graphics.LEFT);
	}
	
	protected void keyPressed(int keyCode)
	{
		int gameAction = getGameAction(keyCode);
		if (gameAction == FIRE)
		{
			midlet.setCurrent("InputName");
		}
	}
}