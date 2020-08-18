import javax.microedition.lcdui.*;
import java.util.*;
import java.io.IOException;

public class CubiesLoading extends Canvas
{
	private CubiesMain midlet;
	private CubiesSettings settings;
	private Image loadingScreen;
	private Timer timer;
	
	public CubiesLoading(CubiesMain midlet, CubiesSettings settings)
	{
		this.midlet = midlet;
		this.settings = settings;
		setFullScreenMode(true);		
		try
		{
			loadingScreen = Image.createImage("/Game Screens/LoadingScreen.png");			
		}
		catch (IOException ioe) {}
	}
	
	protected void paint(Graphics g)
	{
		g.drawImage(loadingScreen, this.getWidth() / 176, this.getHeight() / 208, Graphics.TOP|Graphics.LEFT);	
		startTimer();
	}
	
	protected void startTimer() 
	{
		TimerTask GoToNewGame = new TimerTask()  
		{
			public void run() 
			{
				timer.cancel();
				if (settings.Difficulty == "Easy")
					midlet.setCurrent("EasyLv1");				
				else if (settings.Difficulty == "Normal")
					midlet.setCurrent("NormalLv1");
				else if (settings.Difficulty == "Hard")
					midlet.setCurrent("HardLv1");					
			}
		};
		timer = new Timer();
		timer.schedule(GoToNewGame, 4200);
	}
}