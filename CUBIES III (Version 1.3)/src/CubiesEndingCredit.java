import javax.microedition.lcdui.*;
import javax.microedition.lcdui.game.*;
import javax.microedition.media.*;
import java.util.*;
import java.io.IOException;

public class CubiesEndingCredit extends GameCanvas implements Runnable
{
	private CubiesMain midlet;
	private CubiesSettings settings;
	private CubiesInputName inputname;
	private CubiesEasyLv5 easylv5;
	private CubiesNormalLv5 normallv5;
	private CubiesHardLv5 hardlv5;		
	private Image EndingCredit;
	private Timer timer;

	public CubiesEndingCredit(CubiesMain midlet, CubiesSettings settings, CubiesInputName inputname, CubiesEasyLv5 easylv5, CubiesNormalLv5 normallv5, CubiesHardLv5 hardlv5) 
	{
		super(true);	
		this.midlet = midlet;
		this.settings = settings;
		this.inputname = inputname;
		this.easylv5 = easylv5;		
		this.normallv5 = normallv5;
		this.hardlv5 = hardlv5;
	}

	public void start()
	{
		try
		{
			setFullScreenMode(true);
			EndingCredit = Image.createImage("/Game Screens/CubiesEnding.png");	
			midlet.Credit.setLoopCount(-1);
			midlet.Credit.start();
		}
		catch (IOException ioe) {}
		catch (MediaException me) {}		
		Thread runner = new Thread(this);
		runner.start();			
	}

	public void run()
	{
		Graphics g = getGraphics();
		g.drawImage(EndingCredit, this.getWidth() / 240, this.getHeight() / 300, Graphics.TOP|Graphics.LEFT);
		flushGraphics();		
		startTimer();
	}
	
	protected void startTimer() 
	{
		TimerTask GoToMainMenu = new TimerTask()  
		{
			public void run() 
			{
				timer.cancel();
				try
				{
					midlet.Credit.stop();					
				}
				catch (MediaException me) {}
				if (settings.Difficulty == "Easy")
				{
					String[] a = {inputname.NameCode.getString()};
					int[] b = {easylv5.ScoreValue};
					String[] c = {"5"};
					midlet.writeStream1(a, b, c);
				}
				else if (settings.Difficulty == "Normal")
				{
					String[] a = {inputname.NameCode.getString()};
					int[] b = {normallv5.ScoreValue};
					String[] c = {"5"};
					midlet.writeStream2(a, b, c);
				}
				else if (settings.Difficulty == "Hard")
				{
					String[] a = {inputname.NameCode.getString()};
					int[] b = {hardlv5.ScoreValue};
					String[] c = {"5"};
					midlet.writeStream3(a, b, c);
				}	
				midlet.setCurrent("MainMenu");
			}
		};
		timer = new Timer();
		timer.schedule(GoToMainMenu, 6500);
	}}