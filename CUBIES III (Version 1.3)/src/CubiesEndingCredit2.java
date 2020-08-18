import javax.microedition.lcdui.*;
import javax.microedition.lcdui.game.*;
import javax.microedition.media.*;
import java.util.*;
import java.io.IOException;

public class CubiesEndingCredit2 extends GameCanvas implements Runnable
{
	private CubiesMain midlet;
	private Image EndingCredit2;
	private Timer timer;

	public CubiesEndingCredit2(CubiesMain midlet) 
	{
		super(true);	
		this.midlet = midlet;
	}

	public void start()
	{
		try
		{
			setFullScreenMode(true);
			EndingCredit2 = Image.createImage("/Game Screens/Credits2.png");	
		}
		catch (IOException ioe) {}
		Thread runner = new Thread(this);
		runner.start();			
	}

	public void run()
	{
		Graphics g = getGraphics();
		g.drawImage(EndingCredit2, this.getWidth() / 245, this.getHeight() / 300, Graphics.TOP|Graphics.LEFT);
		flushGraphics();		
		startTimer();
	}
	
	protected void startTimer() 
	{
		TimerTask GoToCredit3 = new TimerTask()  
		{
			public void run() 
			{
				timer.cancel();
				midlet.setCurrent("Ending3");
			}
		};
		timer = new Timer();
		timer.schedule(GoToCredit3, 6000);
	}}