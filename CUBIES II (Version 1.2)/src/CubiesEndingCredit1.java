import javax.microedition.lcdui.*;
import javax.microedition.lcdui.game.*;
import javax.microedition.media.*;
import java.util.*;
import java.io.IOException;

public class CubiesEndingCredit1 extends GameCanvas implements Runnable
{
	private CubiesMain midlet;	
	private Image EndingCredit1;
	private Timer timer;

	public CubiesEndingCredit1(CubiesMain midlet) 
	{
		super(true);	
		this.midlet = midlet;
	}

	public void start()
	{
		try
		{
			setFullScreenMode(true);
			EndingCredit1 = Image.createImage("/Game Screens/Credits1.png");	
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
		g.drawImage(EndingCredit1, this.getWidth() / 245, this.getHeight() / 300, Graphics.TOP|Graphics.LEFT);
		flushGraphics();		
		startTimer();
	}
	
	protected void startTimer() 
	{
		TimerTask GoToCredit2 = new TimerTask()  
		{
			public void run() 
			{
				timer.cancel();
				midlet.setCurrent("Ending2");
			}
		};
		timer = new Timer();
		timer.schedule(GoToCredit2, 6000);
	}}