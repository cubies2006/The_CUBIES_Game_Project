import javax.microedition.lcdui.*;
import javax.microedition.rms.*;
import java.io.*;

public class CubiesHighScores extends Canvas
{
	private CubiesMain midlet;
	private Image highscoresBackground;
	private Image HighScoresEasy, HighScoresNormal, HighScoresHard;
	private int HighScoresStatus;

	public CubiesHighScores(CubiesMain midlet) 
	{
		this.midlet = midlet;
		setFullScreenMode(true);
		try
		{
			highscoresBackground = Image.createImage("/Game Screens/CubiesHighScores.png");
			HighScoresEasy = Image.createImage("/Game High Scores/HighScoresEasy.png");
			HighScoresNormal = Image.createImage("/Game High Scores/HighScoresNormal.png");
			HighScoresHard = Image.createImage("/Game High Scores/HighScoresHard.png");
		}		
		catch (IOException ioe)
		{
			System.out.println("Can't load CUBIES HighScores Screen");
		}
	}
	
	protected void paint(Graphics g)
	{
		g.drawImage(highscoresBackground, this.getWidth() / 176, this.getHeight() / 208, Graphics.TOP|Graphics.LEFT);
		g.setColor(255, 255, 255);
		
		if (HighScoresStatus == 0)
		{
			g.drawImage(HighScoresEasy, 19, 95, Graphics.TOP|Graphics.LEFT);
			g.drawString(midlet.EasyPlayer1, 19, 120, Graphics.TOP|Graphics.LEFT);
			g.drawString(midlet.EasyScore1, 105, 120, Graphics.TOP|Graphics.RIGHT);
			g.drawString(midlet.EasyLevel1, 140, 120, Graphics.TOP|Graphics.LEFT);
			g.drawString(midlet.EasyPlayer2, 19, 132, Graphics.TOP|Graphics.LEFT);
			g.drawString(midlet.EasyScore2, 105, 132, Graphics.TOP|Graphics.RIGHT);
			g.drawString(midlet.EasyLevel2, 140, 132, Graphics.TOP|Graphics.LEFT);	
			g.drawString(midlet.EasyPlayer3, 19, 144, Graphics.TOP|Graphics.LEFT);
			g.drawString(midlet.EasyScore3, 105, 144, Graphics.TOP|Graphics.RIGHT);
			g.drawString(midlet.EasyLevel3, 140, 144, Graphics.TOP|Graphics.LEFT);	
			g.drawString(midlet.EasyPlayer4, 19, 156, Graphics.TOP|Graphics.LEFT);
			g.drawString(midlet.EasyScore4, 105, 156, Graphics.TOP|Graphics.RIGHT);
			g.drawString(midlet.EasyLevel4, 140, 156, Graphics.TOP|Graphics.LEFT);	
			g.drawString(midlet.EasyPlayer5, 19, 168, Graphics.TOP|Graphics.LEFT);
			g.drawString(midlet.EasyScore5, 105, 168, Graphics.TOP|Graphics.RIGHT);
			g.drawString(midlet.EasyLevel5, 140, 168, Graphics.TOP|Graphics.LEFT);				
		}
		else if (HighScoresStatus == 1)
		{
			g.drawImage(HighScoresNormal, 69, 95, Graphics.TOP|Graphics.LEFT);
			g.drawString(midlet.NormalPlayer1, 19, 120, Graphics.TOP|Graphics.LEFT);
			g.drawString(midlet.NormalScore1, 105, 120, Graphics.TOP|Graphics.RIGHT);
			g.drawString(midlet.NormalLevel1, 140, 120, Graphics.TOP|Graphics.LEFT);
			g.drawString(midlet.NormalPlayer2, 19, 132, Graphics.TOP|Graphics.LEFT);
			g.drawString(midlet.NormalScore2, 105, 132, Graphics.TOP|Graphics.RIGHT);
			g.drawString(midlet.NormalLevel2, 140, 132, Graphics.TOP|Graphics.LEFT);
			g.drawString(midlet.NormalPlayer3, 19, 144, Graphics.TOP|Graphics.LEFT);
			g.drawString(midlet.NormalScore3, 105, 144, Graphics.TOP|Graphics.RIGHT);
			g.drawString(midlet.NormalLevel3, 140, 144, Graphics.TOP|Graphics.LEFT);
			g.drawString(midlet.NormalPlayer4, 19, 156, Graphics.TOP|Graphics.LEFT);
			g.drawString(midlet.NormalScore4, 105, 156, Graphics.TOP|Graphics.RIGHT);
			g.drawString(midlet.NormalLevel4, 140, 156, Graphics.TOP|Graphics.LEFT);
			g.drawString(midlet.NormalPlayer5, 19, 168, Graphics.TOP|Graphics.LEFT);
			g.drawString(midlet.NormalScore5, 105, 168, Graphics.TOP|Graphics.RIGHT);
			g.drawString(midlet.NormalLevel5, 140, 168, Graphics.TOP|Graphics.LEFT);			
		}
		else if (HighScoresStatus == 2)
		{
			g.drawImage(HighScoresHard, 128, 95, Graphics.TOP|Graphics.LEFT);
			g.drawString(midlet.HardPlayer1, 19, 120, Graphics.TOP|Graphics.LEFT);
			g.drawString(midlet.HardScore1, 105, 120, Graphics.TOP|Graphics.RIGHT);
			g.drawString(midlet.HardLevel1, 140, 120, Graphics.TOP|Graphics.LEFT);
			g.drawString(midlet.HardPlayer2, 19, 132, Graphics.TOP|Graphics.LEFT);
			g.drawString(midlet.HardScore2, 105, 132, Graphics.TOP|Graphics.RIGHT);
			g.drawString(midlet.HardLevel2, 140, 132, Graphics.TOP|Graphics.LEFT);
			g.drawString(midlet.HardPlayer3, 19, 144, Graphics.TOP|Graphics.LEFT);
			g.drawString(midlet.HardScore3, 105, 144, Graphics.TOP|Graphics.RIGHT);
			g.drawString(midlet.HardLevel3, 140, 144, Graphics.TOP|Graphics.LEFT);
			g.drawString(midlet.HardPlayer4, 19, 156, Graphics.TOP|Graphics.LEFT);
			g.drawString(midlet.HardScore4, 105, 156, Graphics.TOP|Graphics.RIGHT);
			g.drawString(midlet.HardLevel4, 140, 156, Graphics.TOP|Graphics.LEFT);
			g.drawString(midlet.HardPlayer5, 19, 168, Graphics.TOP|Graphics.LEFT);
			g.drawString(midlet.HardScore5, 105, 168, Graphics.TOP|Graphics.RIGHT);
			g.drawString(midlet.HardLevel5, 140, 168, Graphics.TOP|Graphics.LEFT);			
		}
	}		
	
	protected void keyPressed(int keyCode)
	{
		if (keyCode == -7)
		{
			midlet.setCurrent("MainMenu");
			HighScoresStatus = 0;
		}			
		else
		{
			int gameAction = getGameAction(keyCode);
			if (gameAction == RIGHT)
			{
				HighScoresStatus++;
				if (HighScoresStatus > 2)
					HighScoresStatus = 2;
			
			}
			if (gameAction == LEFT)
			{
				HighScoresStatus--;
				if (HighScoresStatus < 0)
					HighScoresStatus = 0;
			}
			repaint();
		}
	}
}