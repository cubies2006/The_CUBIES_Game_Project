import javax.microedition.lcdui.*;
import javax.microedition.lcdui.game.*;
import javax.microedition.media.*;
import java.util.*;
import java.io.IOException;

public class CubiesNormalLv1 extends GameCanvas implements Runnable
{
	private CubiesMain midlet;
	private CubiesSettings settings;
	private CubiesInputName inputname;
	public Image Background, Cubiescubes, cube, supercube, NormalLv1, GameOver, LevelComplete;
	private TiledLayer CubePattern;
	private LayerManager manager;
	private Sprite CubeLadder, SuperCubeLadder;
	private Random rand;
	private int PosX, LPosX, PosY, LPosY, Column, LColumn, Row, LRow;
	private int cubeVal, ElimCube, timerMinute, timerSecond;
	public int ScoreValue, SCubeNo;
	private int Red, Green, Blue, Yellow, count;	
	private boolean SuperCubeSelect, GameRunning, CubePress, SCubePress, Level1Win, FirePress;
    public Timer timer, timerCounter;	
	
	public CubiesNormalLv1(CubiesMain midlet, CubiesSettings settings, CubiesInputName inputname)
	{
		super(true);
		this.midlet = midlet;
		this.settings = settings;
		this.inputname = inputname;	
	}
	
	public void start()
	{
		ScoreValue = 0;
		SCubeNo = 3;	
		PosX = 225;
		PosY = 270;
		Column = 15;
		Row = 12;
		timerMinute = 1;
		timerSecond = 0;
		Red = 4;
		Green = 4;
		Blue = 4;
		Yellow = 4;
		SuperCubeSelect = false;	
		CubePress = true; 
		SCubePress = true;
		FirePress = true;
		timer = new Timer();
        timerCounter = new Timer();
		GameRunning = true;		
		try
		{
			Background = Image.createImage("/Game Screens/GameBackground.png");	
			NormalLv1 = Image.createImage("/Numerals/1.png");
			LevelComplete = Image.createImage("/Game State/LevelComplete.png");			
			GameOver = Image.createImage("/Game State/GameOver.png");
			createCubePattern();				
			CubePattern.setPosition(0, 90);
			manager = new LayerManager();
			manager.append(CubePattern);
			setSuperCube();			
			setCubeLadder();
		}
		catch (IOException ioe) {}
		Thread runner = new Thread(this);
		runner.start();		
	}
	
	public void run()
	{
		Graphics g = getGraphics();	
		startTimer();	
		
		while(GameRunning == true)
		{
			verifyGameState();
			GameElements(getGraphics());
			int KeyState = getKeyStates();
			if(KeyState < 0)
				KeyState = 0;
			CheckUserInput(KeyState);	
			try
			{
				Thread.currentThread().sleep(250);
			}
			catch (Exception e) {}
		}
		if (midlet.ExitGame != true)
		{	
			if (Level1Win == true)
			{
				midlet.ExitEnabled = false;
				LevelCompleteMessage(getGraphics());		
			}
			else 
			{
				midlet.ExitEnabled = false;
				GameOverMessage(getGraphics());
			}
			MakeorBreak();
		}			
	}
	
	protected void MakeorBreak()
	{
		TimerTask Level2 = new TimerTask()  
		{
			public void run() 
			{
				timer.cancel();
				if (count == 0)
					midlet.setCurrent("NormalLv2");
				else
				{
					String[] a = {inputname.NameCode.getString()};
					int[] b = {ScoreValue};
					String[] c = {"1"};
					midlet.writeStream2(a, b, c);
					midlet.setCurrent("MainMenu");
				}
			}
		};
		timer = new Timer();
		timer.schedule(Level2, 4500);
	}
	
	protected void startTimer()
	{
        TimerTask myCounter = new TimerTask()  
		{
            public void run() 
			{		
				if (timerSecond == 0) 
				{
					timerMinute--;
					timerSecond = 60;
				}
				timerSecond--;					
            }
        };
        timerCounter = new Timer();		
		timerCounter.schedule(myCounter, 1000, 1000);		
	}
	
	protected void verifyGameState() 
	{
		int TimeLeft, CubeLadderVal, i;
		Graphics g = getGraphics();
		// Check if the time runs out
		if ((timerMinute == 0) && (timerSecond == 0))
		{
			stopTimer();
			stop();
			Level1Win = false;
			FirePress = false;
			manager.remove(CubeLadder);
		}
		//Check if a cube is placed on the ladder
		for (i = 0; i <= 12; i++)
		{
			CubeLadderVal = CubePattern.getCell(15, i);
			if (CubeLadderVal != 0)
			{
				stopTimer();
				stop();
				Level1Win = false;
				FirePress = false;
				break;
			}
		}
		//Check all cubes are destroyed
		if (count == 0)
		{
			Level1Win = true;
			stopTimer();
			TimeLeft = timerMinute * 60 + timerSecond;
			timerMinute = 0;
			timerSecond = 0;
			GameElements(getGraphics());
			ScoreValue += (TimeLeft * 10 + SCubeNo * 500);			
			stop();
			manager.remove(CubeLadder);
		}
	}
	
    protected void stopTimer()
    {
        timer.cancel();
        timerCounter.cancel();
    }

	public void stop()
	{
		GameRunning = false;		
	}	

	protected void LevelCompleteMessage(Graphics g)
	{
		try
		{
			midlet.LevelComplete.start();
		}
		catch (MediaException me) {}	
		g.drawImage(LevelComplete, 15, 165, Graphics.TOP|Graphics.LEFT);
		manager.paint(g, 0, 0);		
		flushGraphics();
	}	
	
	protected void GameOverMessage(Graphics g)
	{
		try
		{
			midlet.GameOver.start();
		}
		catch (MediaException me) {}				
		g.drawImage(GameOver, 42, 165, Graphics.TOP|Graphics.LEFT);
		manager.paint(g, 0, 0);		
		flushGraphics();
	}		
	
	protected void CheckUserInput(int KeyState)
	{
		if((count != 0) && (FirePress != false))
		{
			if (settings.Control == 'A')
			{
				if ((KeyState & UP_PRESSED) != 0)
				{
					PosY = Math.max(90, PosY - 15);
					Row = Math.max(0, Row - 1);
					try
					{
						midlet.CubeMovement.start();
					}
					catch (MediaException me) {}	
				}
				else if ((KeyState & DOWN_PRESSED) != 0)
				{
					PosY = Math.min(270, PosY + 15);		
					Row = Math.min(12, Row + 1);
					try
					{
						midlet.CubeMovement.start();
					}
					catch (MediaException me) {}	
				}
				else if ((KeyState & FIRE_PRESSED) != 0)
				{
					SCubePress = true;
					MoveX();
				}
				else if ((KeyState & GAME_C_PRESSED) != 0)
				{
					if ((SCubeNo != 0) && (SCubePress == true))
					{
						SuperCubeSelect = true;
						manager.remove(CubeLadder);
						try
						{
							midlet.Swap.start();
							setCubeLadder();
							setSuperCube();					
						}
						catch (IOException ioe) {}
						catch (MediaException me) {}					
						SCubePress = false;
					}
				}
			}
			else if (settings.Control == 'B')
			{
				if ((KeyState & GAME_B_PRESSED) != 0)
				{
					PosY = Math.max(90, PosY - 15);
					Row = Math.max(0, Row - 1);
					try
					{
						midlet.CubeMovement.start();
					}
					catch (MediaException me) {}	
				}
				else if ((KeyState & GAME_D_PRESSED) != 0)
				{
					PosY = Math.min(270, PosY + 15);		
					Row = Math.min(12, Row + 1);
					try
					{
						midlet.CubeMovement.start();
					}
					catch (MediaException me) {}	
				}
				else if ((KeyState & GAME_A_PRESSED) != 0)
				{
					SCubePress = true;
					MoveX();
				}
				else if ((KeyState & GAME_C_PRESSED) != 0)
				{
					if ((SCubeNo != 0) && (SCubePress == true))
					{
						SuperCubeSelect = true;
						manager.remove(CubeLadder);
						try
						{
							midlet.Swap.start();
							setCubeLadder();
							setSuperCube();					
						}
						catch (IOException ioe) {}
						catch (MediaException me) {}					
						SCubePress = false;
					}
				}
			}
			else if (settings.Control == 'C')
			{
				if ((KeyState & GAME_A_PRESSED) != 0)
				{
					PosY = Math.max(90, PosY - 15);
					Row = Math.max(0, Row - 1);
					try
					{
						midlet.CubeMovement.start();
					}
					catch (MediaException me) {}	
				}
				else if ((KeyState & GAME_C_PRESSED) != 0)
				{
					PosY = Math.min(270, PosY + 15);		
					Row = Math.min(12, Row + 1);
					try
					{
						midlet.CubeMovement.start();
					}
					catch (MediaException me) {}	
				}
				else if ((KeyState & GAME_B_PRESSED) != 0)
				{
					SCubePress = true;
					MoveX();
				}
				else if ((KeyState & GAME_D_PRESSED) != 0)
				{
					if ((SCubeNo != 0) && (SCubePress == true))
					{
						SuperCubeSelect = true;
						manager.remove(CubeLadder);
						try
						{
							midlet.Swap.start();
							setCubeLadder();
							setSuperCube();					
						}
						catch (IOException ioe) {}
						catch (MediaException me) {}					
						SCubePress = false;
					}
				}
			}
			else if (settings.Control == 'D')
			{
				if ((KeyState & GAME_B_PRESSED) != 0)
				{
					PosY = Math.max(90, PosY - 15);
					Row = Math.max(0, Row - 1);
					try
					{
						midlet.CubeMovement.start();
					}
					catch (MediaException me) {}	
				}
				else if ((KeyState & GAME_D_PRESSED) != 0)
				{
					PosY = Math.min(270, PosY + 15);		
					Row = Math.min(12, Row + 1);
					try
					{
						midlet.CubeMovement.start();
					}
					catch (MediaException me) {}	
				}
				else if ((KeyState & FIRE_PRESSED) != 0)
				{
					SCubePress = true;
					MoveX();
				}
				else if ((KeyState & GAME_C_PRESSED) != 0)
				{
					if ((SCubeNo != 0) && (SCubePress == true))
					{
						SuperCubeSelect = true;
						manager.remove(CubeLadder);
						try
						{
							midlet.Swap.start();
							setCubeLadder();
							setSuperCube();					
						}
						catch (IOException ioe) {}
						catch (MediaException me) {}					
						SCubePress = false;
					}
				}
			}
		}
	}
	
	protected void GameElements(Graphics g)
	{
		String GameScore = new String();
		String timerMinuteStr = new String();
		String timerSecondStr = new String();
		String currentTime = new String();	
		g.setFont(Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_BOLD, Font.SIZE_LARGE));		
		g.drawImage(Background, this.getWidth() / 245, this.getHeight() / 300, Graphics.TOP|Graphics.LEFT);
		GameScore = String.valueOf(ScoreValue);
		if (GameScore.length() == 1)
			GameScore = "00000" + GameScore;
		else if (GameScore.length() == 2)
			GameScore = "0000" + GameScore;			
		else if (GameScore.length() == 3)
			GameScore = "000" + GameScore;
		else if (GameScore.length() == 4)
			GameScore = "00" + GameScore;
		else if (GameScore.length() == 5)
			GameScore = "0" + GameScore;
		g.setColor(143, 248, 247);							
		g.drawString(GameScore, 70, 14, Graphics.TOP|Graphics.LEFT);
		g.drawImage(supercube, 90, 56, Graphics.TOP|Graphics.LEFT);
		g.drawImage(NormalLv1, 200, 56, Graphics.TOP|Graphics.LEFT);
		timerMinuteStr = String.valueOf(timerMinute);
        timerSecondStr = String.valueOf(timerSecond);
		if (timerSecondStr.length() == 1)
            timerSecondStr = "0" + timerSecondStr;		
		currentTime = timerMinuteStr + ":" + timerSecondStr;	
		if ((timerMinute == 1) && (timerSecond == 0))
		{
			g.setColor(249, 95, 109);
			try
			{
				midlet.Warning.start();
			}
			catch (MediaException me) {}
		}
		else if (timerMinute == 0)
			g.setColor(249, 95, 109);
		else
			g.setColor(143, 248, 247);
		g.drawString(currentTime, 195, 14, Graphics.TOP|Graphics.LEFT);	
		CubeLadder.setPosition(PosX, PosY);
		CubeLadder.nextFrame();
		CubePattern.paint(g);
		manager.paint(g, 0, 0);
		flushGraphics();
	}
	
	protected void createCubePattern() throws IOException
	{
		Cubiescubes = Image.createImage("/Game State/TiledCubes.png");
		CubePattern = new TiledLayer(16, 13, Cubiescubes, 15, 15);

		int	[] Cubies = {
						9, 9, 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, //15 
						9, 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, //31 
						9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, //47 
						0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, //63 
						0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, //79
						0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, //95
						0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, //111
						0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, //127
						0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, //143
						4, 2, 2, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, //159
						3, 1, 1, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, //175
						1, 3, 3, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, //191
						4, 2, 2, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0  //207
						};
					
		for (int i = 0; i < Cubies.length; i++)
		{
			int column = i % 16;
			int row = (i - column) / 15;
			CubePattern.setCell(column, row, Cubies[i]);
		}
	}	

	protected void setSuperCube() throws IOException
	{
		if (SCubeNo == 0)
			supercube = Image.createImage("/Numerals/0.png");
		else if (SCubeNo == 1)
			supercube = Image.createImage("/Numerals/1.png");
		else if (SCubeNo == 2)
			supercube = Image.createImage("/Numerals/2.png");
		else if (SCubeNo == 3)
			supercube = Image.createImage("/Numerals/3.png");
	}
	
	protected void setCubeLadder() throws IOException
	{
		if (SuperCubeSelect == false)
		{
			int num [] = {Red,Green,Blue,Yellow}; 
			String Color [] = {"Red","Green","Blue","Yellow"};
			int temp; 
			String Temp;
			for (int y=0; y<num.length-1; y++)
				for (int x=0; x<num.length-1-y; x++)
					if (num[x] < num[x+1])
					{	
						temp = num[x];
						num[x] = num[x+1];
						num[x+1] = temp;
					
						Temp = Color[x];
						Color[x] = Color[x+1];
						Color[x+1] = Temp;
					}
			count = 4;
			for (int y = 0; y<num.length; y++)
				if (num[y] == 0)
					count--;
					
			if (count != 0)
			{
				rand = new Random();
				cubeVal = rand.nextInt(count) % count;
				if (Color[cubeVal] == "Red")
				{
					cube = Image.createImage("/Game Animation/RedCubeAnimated.png");
					cubeVal = 1;
				}
				else if (Color[cubeVal] == "Green")
				{	
					cube = Image.createImage("/Game Animation/GreenCubeAnimated.png");
					cubeVal = 2;
				}
				else if (Color[cubeVal] == "Blue")
				{
					cube = Image.createImage("/Game Animation/BlueCubeAnimated.png");
					cubeVal = 3;
				}
				else if (Color[cubeVal] == "Yellow")
				{	
					cube = Image.createImage("/Game Animation/YellowCubeAnimated.png");
					cubeVal = 4;
				}
			}
		}
		else if (SCubeNo > 0)
		{
			cubeVal = 10;
			cube = Image.createImage("/Game Animation/SuperCubeAnimated.png");
			SCubeNo--;
			SuperCubeSelect = false;
		}
		CubeLadder = new Sprite(cube, 15, 15);
		manager.append(CubeLadder);
	}
	
	protected void MoveX() 
	{
		ElimCube = 0;
		LPosY = PosY;
		LPosX = PosX;
		LRow = Row;
		LColumn = Column;
		int xcounter = Column - 1;
		int ycounter;
		int cellVal;
		
		while (PosX != 0)
		{
			cellVal = CubePattern.getCell(xcounter, Row);
			if (cellVal == 0)
			{
				PosX -= 15;
				Column--;
				GameElements(getGraphics());
				if (xcounter == 0)
					MoveY();
			}
			else if (cellVal == 9)
			{
					MoveY();
					PosX = 0;
			}
			else if ((cellVal > 0) && (cellVal < 9))
			{
				if (cubeVal == 10)
				{
					while (PosX != 0)
					{
						try
						{
							midlet.Explosion.start();
						}
						catch (MediaException me) {}					
						cellVal = CubePattern.getCell(xcounter, Row);
						if (cellVal == 1)
							Red--;
						else if (cellVal == 2)
							Green--;
						else if (cellVal == 3)
							Blue--;
						else if (cellVal == 4)
							Yellow--;
						CubePattern.setCell(xcounter, Row, 0);
						trim(xcounter);
						xcounter--;
						if (xcounter < 0)
							break;
						PosX -= 15;
						cellVal = CubePattern.getCell(xcounter, Row);						
					}
				}			
				else if (cellVal == cubeVal)
				{
					while (cellVal == cubeVal)
					{
						try
						{
							midlet.Explosion.start();
						}
						catch (MediaException me) {}					
						cellVal = CubePattern.getCell(xcounter, Row);
						if (cellVal == 1)
							Red--;
						else if (cellVal == 2)
							Green--;
						else if (cellVal == 3)
							Blue--;
						else if (cellVal == 4)
							Yellow--;
						CubePattern.setCell(xcounter, Row, 0);
						trim(xcounter);
						xcounter--;
						if (xcounter < 0)
						{
							ElimCube++;
							break;
						}
						ElimCube++;
						cellVal = CubePattern.getCell(xcounter, Row);
					}
					ScoreValue += ElimCube * ElimCube * 10;
				}
				else
				{
					if (xcounter < 14)
						MoveY();
					else
					{
						CubePattern.setCell(Column, Row, cubeVal);
					}
				}
				PosX = 0;
			}	
			xcounter--;
		}
		manager.remove(CubeLadder);
		PosY = LPosY;
		PosX = LPosX;
		Row = LRow;
		Column = LColumn;
		try
		{
			setCubeLadder();
		}
		catch (IOException ioe) {}
	}
	
	protected void MoveY()
	{
		int ycounter = Row + 1;
		int cellVal;
		
		while (PosY<270)
		{
			cellVal = CubePattern.getCell(Column, ycounter);
			if (cellVal == 0)
			{
				PosY += 15;
				Row++;
				GameElements(getGraphics());
				if (ycounter == 12)
					if(cubeVal == 10)
						CubePattern.setCell(Column, Row, 0);
					else	
						CubePattern.setCell(Column, Row, cubeVal);
			}
			else if ((cellVal > 0) && (cellVal <= 9))
			{
				if (cubeVal == 10)
				{
					while (PosY != 270)
					{
						try
						{
							midlet.Explosion.start();
						}
						catch (MediaException me) {}					
						cellVal = CubePattern.getCell(Column, ycounter);
						if(cellVal == 1)
							Red--;
						else if (cellVal == 2)
							Green--;
						else if (cellVal == 3)
							Blue--;
						else if (cellVal == 4)
							Yellow--;
						CubePattern.setCell(Column, ycounter, 0);
						ycounter++;
						if (ycounter > 12)
							break;
						cellVal = CubePattern.getCell(Column, ycounter);						
					}
				}			
				else if (cellVal == cubeVal)
				{
					while (cellVal == cubeVal)
					{
						try
						{
							midlet.Explosion.start();
						}
						catch (MediaException me) {}					
						cellVal = CubePattern.getCell(Column, ycounter);
						if (cellVal == 1)
							Red--;
						else if (cellVal == 2)
							Green--;
						else if (cellVal == 3)
							Blue--;
						else if (cellVal == 4)
							Yellow--;
						CubePattern.setCell(Column, ycounter, 0);
						ycounter++;
						if (ycounter > 12)
						{
							ElimCube++;
							break;
						}
						ElimCube++;
						cellVal = CubePattern.getCell(Column, ycounter);
					}
				}
				else 
				{
					try
					{
						midlet.Append.start();
					}
					catch (MediaException me) {}					
					CubePattern.setCell(Column, Row, cubeVal);
					if (cubeVal == 1)
						Red++;
					else if (cubeVal == 2)
						Green++;
					else if (cubeVal == 3)
						Blue++;
					else if (cubeVal == 4)
						Yellow++;
				}
				PosY = 285;
			}
			ycounter++;
			ScoreValue += ElimCube * ElimCube * 10;
		}
		if (PosY == 270)
		{
			if(cubeVal==10)
			{
				try
				{
					midlet.Explosion.start();
				}
				catch (MediaException me) {}			
				CubePattern.setCell(Column, Row, 0);
			}
			else
			{
				try
				{
					midlet.Append.start();
				}
				catch (MediaException me) {}			
				CubePattern.setCell(Column, Row, cubeVal);
				if (cubeVal == 1)
					Red++;
				else if (cubeVal == 2)
					Green++;
				else if (cubeVal == 3)
					Blue++;
				else if (cubeVal == 4)
					Yellow++;
			}
		}
		manager.remove(CubeLadder);
	}
	
	protected void trim(int txcounter)
	{
		int temp, x, y;
		for(y = Row; y > 0; y--)
		{
			temp = CubePattern.getCell(txcounter, y - 1);
			if((temp > 0) && (temp < 9))
			{
				CubePattern.setCell(txcounter, y, temp);
				CubePattern.setCell(txcounter, y - 1, 0);
			}
			else if(temp == 9)
				break;
		}
	}		
}		