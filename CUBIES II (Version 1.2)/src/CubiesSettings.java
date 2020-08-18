import javax.microedition.lcdui.*;
import javax.microedition.media.*;
import javax.microedition.media.control.*;
import java.io.*;

public class CubiesSettings extends Canvas
{
	private CubiesMain midlet;
	private Image settingsBackground; 
	private Image SettingsSounds, SettingsSoundsOn, SettingsSoundsOff;
	private Image SettingsDifficulty, SettingsDEasy, SettingsDNormal, SettingsDHard;
	private Image SettingsControls, SetA, SetB, SetC, SetD, ArrowLeft, ArrowRight;
	public int SettingsStatus = 0, Settings_SStatus = 0, Settings_DStatus = 1;
	public int Settings_CStatus = 0, SuperCube = 3;
	public String Difficulty = "Normal";
	public char Control = 'A';

	public CubiesSettings(CubiesMain midlet)
	{
		this.midlet = midlet;
		setFullScreenMode(true);
		try
		{
			settingsBackground = Image.createImage("/Game Screens/CubiesSettings.png");
			SettingsSounds = Image.createImage("/Game Settings/SettingsSounds.png");
			SettingsSoundsOn = Image.createImage("/Game Settings/SettingsSoundsOn.png");
			SettingsSoundsOff = Image.createImage("/Game Settings/SettingsSoundsOff.png");
			SettingsDifficulty = Image.createImage("/Game Settings/SettingsDifficulty.png");
			SettingsDEasy = Image.createImage("/Game Settings/SettingsDEasy.png");
			SettingsDNormal = Image.createImage("/Game Settings/SettingsDNormal.png");
			SettingsDHard = Image.createImage("/Game Settings/SettingsDHard.png");
			SettingsControls = Image.createImage("/Game Settings/SettingsControls.png");
			SetA = Image.createImage("/Game Settings/SettingsControlA.png");
			SetB = Image.createImage("/Game Settings/SettingsControlB.png");
			SetC = Image.createImage("/Game Settings/SettingsControlC.png");
			SetD = Image.createImage("/Game Settings/SettingsControlD.png");
			ArrowLeft = Image.createImage("/Game Directions/ArrowLeft.png");
			ArrowRight = Image.createImage("/Game Directions/ArrowRight.png");
		}
		catch(IOException ioe) {}
	}
	
	public void paint(Graphics g)
	{
		g.drawImage(settingsBackground, this.getWidth() / 240, this.getHeight() / 300, Graphics.TOP|Graphics.LEFT);
		
		//Sounds Setting
		if (SettingsStatus == 0) 
			g.drawImage(SettingsSounds, 16, 86, Graphics.TOP|Graphics.LEFT);
		//Difficulty Setting
		else if (SettingsStatus == 1)
			g.drawImage(SettingsDifficulty, 16, 110, Graphics.TOP|Graphics.LEFT);
		//Controls Setting
		else if (SettingsStatus == 2)	
			g.drawImage(SettingsControls, 16, 134, Graphics.TOP|Graphics.LEFT);
		
		//SoundsOnOff
		if (Settings_SStatus == 0)
		{
			g.drawImage(SettingsSoundsOn, 47, 99, Graphics.TOP|Graphics.LEFT);
			SetSound(100);
		}
		else if (Settings_SStatus == 1)
		{
			g.drawImage(SettingsSoundsOff, 70, 99, Graphics.TOP|Graphics.LEFT);
			SetSound(0);			
		}	
		
		//Easy
		if (Settings_DStatus == 0)
		{
			g.drawImage(SettingsDEasy, 47, 123, Graphics.TOP|Graphics.LEFT);
			Difficulty = "Easy";
		}
		//Normal
		else if (Settings_DStatus == 1)
		{
			g.drawImage(SettingsDNormal, 81, 123, Graphics.TOP|Graphics.LEFT);
			Difficulty = "Normal";
		}
		//Hard
		else if (Settings_DStatus == 2)
		{
			g.drawImage(SettingsDHard, 124, 123, Graphics.TOP|Graphics.LEFT);
			Difficulty = "Hard";			
		}
			
		//Control A
		if (Settings_CStatus == 0)
		{		
			//g.drawImage(ArrowRight, 130, 134, Graphics.TOP|Graphics.LEFT);
			g.drawImage(SetA, 87, 134, Graphics.TOP|Graphics.LEFT);
			g.setColor(255,255,255);
			g.setFont(Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_PLAIN, Font.SIZE_SMALL));
			g.drawString("Key *", 80, 144, Graphics.TOP|Graphics.LEFT); 
			g.drawString("Key ENTER", 80, 156, Graphics.TOP|Graphics.LEFT); 
			g.drawString("Key UP", 80, 168, Graphics.TOP|Graphics.LEFT); 
			g.drawString("Key DOWN", 80, 180, Graphics.TOP|Graphics.LEFT); 
			Control = 'A';
		}
		//Control B
		else if (Settings_CStatus == 1)
		{
			//g.drawImage(ArrowLeft, 120, 197, Graphics.TOP|Graphics.LEFT);		
			g.drawImage(SetB, 98, 134, Graphics.TOP|Graphics.LEFT);
			//g.drawImage(ArrowRight, 193, 197, Graphics.TOP|Graphics.LEFT);			
			g.setColor(255,255,255);
			g.setFont(Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_PLAIN, Font.SIZE_SMALL));
			g.drawString("Key *", 80, 144, Graphics.TOP|Graphics.LEFT); 
			g.drawString("Key 7", 80, 156, Graphics.TOP|Graphics.LEFT); 
			g.drawString("Key 9", 80, 168, Graphics.TOP|Graphics.LEFT); 
			g.drawString("Key #", 80, 180, Graphics.TOP|Graphics.LEFT); 
			Control = 'B';
		}
		//Control C
		else if (Settings_CStatus == 2)
		{
			//g.drawImage(ArrowLeft, 120, 197, Graphics.TOP|Graphics.LEFT);		
			g.drawImage(SetC, 110, 134, Graphics.TOP|Graphics.LEFT);
			//g.drawImage(ArrowRight, 193, 197, Graphics.TOP|Graphics.LEFT);			
			g.setColor(255,255,255);
			g.setFont(Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_PLAIN, Font.SIZE_SMALL));
			g.drawString("Key #", 80, 144, Graphics.TOP|Graphics.LEFT); 
			g.drawString("Key 9", 80, 156, Graphics.TOP|Graphics.LEFT); 
			g.drawString("Key 7", 80, 168, Graphics.TOP|Graphics.LEFT); 
			g.drawString("Key *", 80, 180, Graphics.TOP|Graphics.LEFT);
			Control = 'C';			
		}
		//Control D
		else if (Settings_CStatus == 3)
		{
			//g.drawImage(ArrowLeft, 120, 197, Graphics.TOP|Graphics.LEFT);		
			g.drawImage(SetD, 121, 134, Graphics.TOP|Graphics.LEFT);
			g.setColor(255,255,255);
			g.setFont(Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_PLAIN, Font.SIZE_SMALL));
			g.drawString("Key *", 80, 144, Graphics.TOP|Graphics.LEFT); 
			g.drawString("Key ENTER", 80, 156, Graphics.TOP|Graphics.LEFT); 
			g.drawString("Key 9", 80, 168, Graphics.TOP|Graphics.LEFT); 
			g.drawString("Key #", 80, 180, Graphics.TOP|Graphics.LEFT);
			Control = 'D';			
		}
		setFullScreenMode(true);
	}
	
    private void SetSound(int diff) 
	{
        VolumeControl settingsSound1, settingsSound2, settingsSound3, settingsSound4, settingsSound5;
		VolumeControl settingsSound6, settingsSound7, settingsSound8, settingsSound9, settingsSound10;
        settingsSound1 = (VolumeControl)midlet.CubiesIntro.getControl("VolumeControl");		
        settingsSound2 = (VolumeControl)midlet.CubiesOpening.getControl("VolumeControl");
        settingsSound3 = (VolumeControl)midlet.CubeMovement.getControl("VolumeControl");		
        settingsSound4 = (VolumeControl)midlet.Append.getControl("VolumeControl");
        settingsSound5 = (VolumeControl)midlet.Explosion.getControl("VolumeControl");
        settingsSound6 = (VolumeControl)midlet.Swap.getControl("VolumeControl");
        settingsSound7 = (VolumeControl)midlet.Warning.getControl("VolumeControl");
        settingsSound8 = (VolumeControl)midlet.LevelComplete.getControl("VolumeControl");
        settingsSound9 = (VolumeControl)midlet.GameOver.getControl("VolumeControl");
        settingsSound10 = (VolumeControl)midlet.Credit.getControl("VolumeControl");			
		int cv1 = settingsSound1.getLevel();
		int cv2 = settingsSound2.getLevel();
		int cv3 = settingsSound3.getLevel();
		int cv4 = settingsSound4.getLevel();
		int cv5 = settingsSound5.getLevel();
		int cv6 = settingsSound6.getLevel();
		int cv7 = settingsSound7.getLevel();
		int cv8 = settingsSound8.getLevel();
		int cv9 = settingsSound9.getLevel();
		int cv10 = settingsSound10.getLevel();		
		cv1 = settingsSound1.setLevel(diff);
		cv2 = settingsSound2.setLevel(diff);
		cv3 = settingsSound3.setLevel(diff);
		cv4 = settingsSound4.setLevel(diff);
		cv5 = settingsSound5.setLevel(diff);
		cv6 = settingsSound6.setLevel(diff);
		cv7 = settingsSound7.setLevel(diff);
		cv8 = settingsSound8.setLevel(diff);
		cv9 = settingsSound9.setLevel(diff);
		cv10 = settingsSound10.setLevel(diff);		
	}
	
	protected void keyPressed(int keyCode)
	{
		if (keyCode == -6)
		{
			Settings_SStatus = 0;
			Settings_DStatus = 1;
			Settings_CStatus = 0;
			repaint();
		}
		else if (keyCode == -7)
		{
			midlet.setCurrent("MainMenu");
			SettingsStatus = 0;			
		}
		else
		{
			int gameAction = getGameAction(keyCode);
			if (gameAction == UP)
			{
				SettingsStatus--;
				if (SettingsStatus < 0)
				{
					SettingsStatus = 2;
				}
			}
			else if (gameAction == DOWN)
			{
				SettingsStatus++;
				if (SettingsStatus > 2)
				{
					SettingsStatus = 0;
				}
			}		
			else if (gameAction == LEFT)
			{
				if (SettingsStatus == 0)
				{
					Settings_SStatus--;
					if (Settings_SStatus < 0)
					{
						Settings_SStatus = 0;
					}
				}
				else if (SettingsStatus == 1)
				{
					Settings_DStatus--;
					if (Settings_DStatus < 0)
					{
						Settings_DStatus = 0;
					}
				}
				else if (SettingsStatus == 2)
				{
					Settings_CStatus--;
					if (Settings_CStatus < 0)
					{
						Settings_CStatus = 0;
					}
				}
			}
			else if (gameAction == RIGHT)
			{
				if (SettingsStatus == 0)
				{
					Settings_SStatus++;
					if (Settings_SStatus > 1)
					{
						Settings_SStatus = 1;
					}
				}
				else if (SettingsStatus == 1)
				{
					Settings_DStatus++;
					if (Settings_DStatus > 2)
					{
						Settings_DStatus = 2;
					}
				}
				else if (SettingsStatus == 2)
				{
					Settings_CStatus++;
					if (Settings_CStatus > 3)
					{
						Settings_CStatus = 3;
					}
				}
			}
			repaint();	
		}
			
	}
}