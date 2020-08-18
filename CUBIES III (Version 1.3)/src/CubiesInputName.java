import javax.microedition.lcdui.*;
import javax.microedition.media.*;
import java.io.*;

public class CubiesInputName extends Form implements CommandListener 
{
	private CubiesMain midlet;
    private Command OKCommand = new Command("OK", Command.EXIT, 1);
    private Command CancelCommand = new Command("Cancel", Command.EXIT, 2);
	public TextField NameCode = new TextField("Enter a name:" + "\n" + "                         ", "", 5, TextField.ANY);

    public CubiesInputName(CubiesMain midlet) 
	{	
		super("", null);
		this.midlet = midlet;
		append(NameCode);
		addCommand(OKCommand);			
		addCommand(CancelCommand);
		setCommandListener(this);		
    }	

	public void commandAction(Command c, Displayable s) 
	{
        if (c == OKCommand) 
		{
			try
			{
				midlet.CubiesIntro.stop();
			}
			catch (MediaException me) {}
			midlet.setCurrent("MainMenu");
        }		
        else if (c == CancelCommand) 
		{
			midlet.exitMIDlet();
        }	
    }
}