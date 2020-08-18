import javax.microedition.lcdui.*;
import javax.microedition.media.*;
import java.io.*;

public class CubiesMenu extends Canvas
{
	private CubiesMain midlet;
	private Image menuBackground, CubiesNewGame, CubiesHighScores, CubiesSettings;
	private Image CubiesInstructions, CubiesExitGame, CubiesNewGameHlite;
	private Image CubiesHighScoresHlite, CubiesSettingsHlite, CubiesInstructionsHlite;
	private Image CubiesExitGameHlite;
	private int MenuStatus = 1;
	
	public CubiesMenu(CubiesMain midlet)
	{
		this.midlet = midlet;
		setFullScreenMode(true);
		try
		{
			menuBackground = Image.createImage("/Game Screens/CubiesMainMenu.png");
			CubiesNewGame = Image.createImage("/Game Menu/ButtonNewNormal.png");
			CubiesHighScores = Image.createImage("/Game Menu/ButtonHighScoresNormal.png");
			CubiesSettings = Image.createImage("/Game Menu/ButtonSettingsNormal.png");
			CubiesInstructions = Image.createImage("/Game Menu/ButtonInstructionsNormal.png");
			CubiesExitGame = Image.createImage("/Game Menu/ButtonExitNormal.png");
			CubiesNewGameHlite = Image.createImage("/Game Menu/ButtonNewHLite.png");
			CubiesHighScoresHlite = Image.createImage("/Game Menu/ButtonHighScoresHLite.png");
			CubiesSettingsHlite = Image.createImage("/Game Menu/ButtonSettingsHLite.png");
			CubiesInstructionsHlite = Image.createImage("/Game Menu/ButtonInstructionsHLite.png");
			CubiesExitGameHlite = Image.createImage("/Game Menu/ButtonExitHLite.png");		
		}			
		catch (IOException ioe) {}
	}
	
	protected void paint(Graphics g)
	{
	    if (MenuStatus == 1)
		{
			g.drawImage(menuBackground, this.getWidth() / 176, this.getHeight() / 208, Graphics.TOP|Graphics.LEFT);
			g.drawImage(CubiesNewGameHlite, 10, 55, Graphics.TOP|Graphics.LEFT);
			g.drawImage(CubiesHighScores, 10, 70, Graphics.TOP|Graphics.LEFT);
			g.drawImage(CubiesSettings, 10, 85, Graphics.TOP|Graphics.LEFT);
			g.drawImage(CubiesInstructions, 10, 100, Graphics.TOP|Graphics.LEFT);
			g.drawImage(CubiesExitGame, 10, 115, Graphics.TOP|Graphics.LEFT);
		}
		else if (MenuStatus == 2)
		{
			g.drawImage(menuBackground, this.getWidth() / 176, this.getHeight() / 208, Graphics.TOP|Graphics.LEFT);
			g.drawImage(CubiesNewGame, 10, 55, Graphics.TOP|Graphics.LEFT);
			g.drawImage(CubiesHighScoresHlite, 10, 70, Graphics.TOP|Graphics.LEFT);;
			g.drawImage(CubiesSettings, 10, 85, Graphics.TOP|Graphics.LEFT);
			g.drawImage(CubiesInstructions, 10, 100, Graphics.TOP|Graphics.LEFT);
			g.drawImage(CubiesExitGame, 10, 115, Graphics.TOP|Graphics.LEFT);
		}
		else if(MenuStatus == 3)
		{
			g.drawImage(menuBackground, this.getWidth() / 176, this.getHeight() / 208, Graphics.TOP|Graphics.LEFT);
			g.drawImage(CubiesNewGame, 10, 55, Graphics.TOP|Graphics.LEFT);
			g.drawImage(CubiesHighScores, 10, 70, Graphics.TOP|Graphics.LEFT);
			g.drawImage(CubiesSettingsHlite, 10, 85, Graphics.TOP|Graphics.LEFT);
			g.drawImage(CubiesInstructions, 10, 100, Graphics.TOP|Graphics.LEFT);
			g.drawImage(CubiesExitGame, 10, 115, Graphics.TOP|Graphics.LEFT);
		}
		else if (MenuStatus == 4)
		{
			g.drawImage(menuBackground, this.getWidth() / 176, this.getHeight() / 208, Graphics.TOP|Graphics.LEFT);
			g.drawImage(CubiesNewGame, 10, 55, Graphics.TOP|Graphics.LEFT);
			g.drawImage(CubiesHighScores, 10, 70, Graphics.TOP|Graphics.LEFT);
			g.drawImage(CubiesSettings, 10, 85, Graphics.TOP|Graphics.LEFT);
		    g.drawImage(CubiesInstructionsHlite, 10, 100, Graphics.TOP|Graphics.LEFT);
			g.drawImage(CubiesExitGame, 10, 115, Graphics.TOP|Graphics.LEFT);
		}
		else if (MenuStatus == 5)
		{
			g.drawImage(menuBackground, this.getWidth() / 176, this.getHeight() / 208, Graphics.TOP|Graphics.LEFT);
			g.drawImage(CubiesNewGame, 10, 55, Graphics.TOP|Graphics.LEFT);
			g.drawImage(CubiesHighScores, 10, 70, Graphics.TOP|Graphics.LEFT);
			g.drawImage(CubiesSettings, 10, 85, Graphics.TOP|Graphics.LEFT);
			g.drawImage(CubiesInstructions, 10, 100, Graphics.TOP|Graphics.LEFT);
			g.drawImage(CubiesExitGameHlite, 10, 115, Graphics.TOP|Graphics.LEFT);
		}		
	}
	
	protected void keyPressed(int keyCode)
	{
		int gameAction = getGameAction(keyCode);
		if (gameAction == FIRE)
		{
			if (MenuStatus == 1)
			{
				try
				{
					midlet.CubiesOpening.stop();
				}
				catch (MediaException me) {}
				midlet.setCurrent("Loading");
			}
			else if (MenuStatus == 2)
			{
				midlet.setCurrent("HighScores");
			}
			else if (MenuStatus == 3)
			{
				midlet.setCurrent("Settings");
			}
			else if (MenuStatus == 4)
			{
				midlet.setCurrent("Instructions");
			}
			else if (MenuStatus == 5)
			{
				midlet.destroyApp(true);
			}
		}
		else if (gameAction == DOWN)
		{
			MenuStatus++;
			if (MenuStatus == 6)
			{
				MenuStatus = 1;
			}
		}
		else if (gameAction == UP)
		{
			MenuStatus--;
			if (MenuStatus == 0)
			{
				MenuStatus = 5;
			}
		}
		repaint();
	}
}