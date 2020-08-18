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
		g.drawImage(highscoresBackground, this.getWidth() / 245, this.getHeight() / 300, Graphics.TOP|Graphics.LEFT);
		g.setColor(255, 255, 255);
		setFullScreenMode(true);
		
		if (HighScoresStatus == 0)
		{
			g.drawImage(HighScoresEasy, 34, 155, Graphics.TOP|Graphics.LEFT);
			g.drawString(midlet.EasyPlayer1, 55, 180, Graphics.TOP|Graphics.LEFT);
			g.drawString(midlet.EasyScore1, 133, 180, Graphics.TOP|Graphics.RIGHT);
			g.drawString(midlet.EasyLevel1, 170, 180, Graphics.TOP|Graphics.LEFT);
			g.drawString(midlet.EasyPlayer2, 55, 200, Graphics.TOP|Graphics.LEFT);
			g.drawString(midlet.EasyScore2, 133, 200, Graphics.TOP|Graphics.RIGHT);
			g.drawString(midlet.EasyLevel2, 170, 200, Graphics.TOP|Graphics.LEFT);
			g.drawString(midlet.EasyPlayer3, 55, 220, Graphics.TOP|Graphics.LEFT);
			g.drawString(midlet.EasyScore3, 133, 220, Graphics.TOP|Graphics.RIGHT);
			g.drawString(midlet.EasyLevel3, 170, 220, Graphics.TOP|Graphics.LEFT);
			g.drawString(midlet.EasyPlayer4, 55, 240, Graphics.TOP|Graphics.LEFT);
			g.drawString(midlet.EasyScore4, 133, 240, Graphics.TOP|Graphics.RIGHT);
			g.drawString(midlet.EasyLevel4, 170, 240, Graphics.TOP|Graphics.LEFT);
			g.drawString(midlet.EasyPlayer5, 55, 260, Graphics.TOP|Graphics.LEFT);
			g.drawString(midlet.EasyScore5, 133, 260, Graphics.TOP|Graphics.RIGHT);
			g.drawString(midlet.EasyLevel5, 170, 260, Graphics.TOP|Graphics.LEFT);			
		}
		else if (HighScoresStatus == 1)
		{
			g.drawImage(HighScoresNormal, 95, 155, Graphics.TOP|Graphics.LEFT);
			g.drawString(midlet.NormalPlayer1, 55, 180, Graphics.TOP|Graphics.LEFT);
			g.drawString(midlet.NormalScore1, 133, 180, Graphics.TOP|Graphics.RIGHT);
			g.drawString(midlet.NormalLevel1, 170, 180, Graphics.TOP|Graphics.LEFT);
			g.drawString(midlet.NormalPlayer2, 55, 200, Graphics.TOP|Graphics.LEFT);
			g.drawString(midlet.NormalScore2, 133, 200, Graphics.TOP|Graphics.RIGHT);
			g.drawString(midlet.NormalLevel2, 170, 200, Graphics.TOP|Graphics.LEFT);
			g.drawString(midlet.NormalPlayer3, 55, 220, Graphics.TOP|Graphics.LEFT);
			g.drawString(midlet.NormalScore3, 133, 220, Graphics.TOP|Graphics.RIGHT);
			g.drawString(midlet.NormalLevel3, 170, 220, Graphics.TOP|Graphics.LEFT);
			g.drawString(midlet.NormalPlayer4, 55, 240, Graphics.TOP|Graphics.LEFT);
			g.drawString(midlet.NormalScore4, 133, 240, Graphics.TOP|Graphics.RIGHT);
			g.drawString(midlet.NormalLevel4, 170, 240, Graphics.TOP|Graphics.LEFT);
			g.drawString(midlet.NormalPlayer5, 55, 260, Graphics.TOP|Graphics.LEFT);
			g.drawString(midlet.NormalScore5, 133, 260, Graphics.TOP|Graphics.RIGHT);
			g.drawString(midlet.NormalLevel5, 170, 260, Graphics.TOP|Graphics.LEFT);			
		}
		else if (HighScoresStatus == 2)
		{
			g.drawImage(HighScoresHard, 165, 155, Graphics.TOP|Graphics.LEFT);
			g.drawString(midlet.HardPlayer1, 55, 180, Graphics.TOP|Graphics.LEFT);
			g.drawString(midlet.HardScore1, 133, 180, Graphics.TOP|Graphics.RIGHT);
			g.drawString(midlet.HardLevel1, 170, 180, Graphics.TOP|Graphics.LEFT);
			g.drawString(midlet.HardPlayer2, 55, 200, Graphics.TOP|Graphics.LEFT);
			g.drawString(midlet.HardScore2, 133, 200, Graphics.TOP|Graphics.RIGHT);
			g.drawString(midlet.HardLevel2, 170, 200, Graphics.TOP|Graphics.LEFT);
			g.drawString(midlet.HardPlayer3, 55, 220, Graphics.TOP|Graphics.LEFT);
			g.drawString(midlet.HardScore3, 133, 220, Graphics.TOP|Graphics.RIGHT);
			g.drawString(midlet.HardLevel3, 170, 220, Graphics.TOP|Graphics.LEFT);
			g.drawString(midlet.HardPlayer4, 55, 240, Graphics.TOP|Graphics.LEFT);
			g.drawString(midlet.HardScore4, 133, 240, Graphics.TOP|Graphics.RIGHT);
			g.drawString(midlet.HardLevel4, 170, 240, Graphics.TOP|Graphics.LEFT);
			g.drawString(midlet.HardPlayer5, 55, 260, Graphics.TOP|Graphics.LEFT);
			g.drawString(midlet.HardScore5, 133, 260, Graphics.TOP|Graphics.RIGHT);
			g.drawString(midlet.HardLevel5, 170, 260, Graphics.TOP|Graphics.LEFT);			
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