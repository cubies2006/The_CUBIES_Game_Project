import javax.microedition.midlet.*;
import javax.microedition.media.*;
import javax.microedition.rms.*;
import javax.microedition.lcdui.*;
import java.io.*;

public class CubiesMain extends MIDlet implements CommandListener
{
	public Player CubiesIntro, CubiesOpening, CubeMovement, Append, Explosion;
	public Player Swap, Warning, LevelComplete, GameOver, Credit;
	public String EasyPlayer1 = "", EasyPlayer2 = "", EasyPlayer3 = "", EasyPlayer4 = "", EasyPlayer5 = "";
	public String EasyScore1 = "", EasyScore2 = "", EasyScore3 = "", EasyScore4 = "", EasyScore5 = "";
	public String EasyLevel1 = "", EasyLevel2 = "", EasyLevel3 = "", EasyLevel4 = "", EasyLevel5 = "";	
	public String NormalPlayer1 = "", NormalPlayer2 = "", NormalPlayer3 = "", NormalPlayer4 = "", NormalPlayer5 = "";
	public String NormalScore1 = "", NormalScore2 = "", NormalScore3 = "", NormalScore4 = "", NormalScore5 = "";
	public String NormalLevel1 = "", NormalLevel2 = "", NormalLevel3 = "", NormalLevel4 = "", NormalLevel5 = "";
	public String HardPlayer1 = "", HardPlayer2 = "", HardPlayer3 = "", HardPlayer4 = "", HardPlayer5 = "";
	public String HardScore1 = "", HardScore2 = "", HardScore3 = "", HardScore4 = "", HardScore5 = "";
	public String HardLevel1 = "", HardLevel2 = "", HardLevel3 = "", HardLevel4 = "", HardLevel5 = "";	
    private RecordStore rs1 = null;  
    static final String REC_STORE1 = "CubiesEasy";
    private RecordStore rs2 = null;  
    static final String REC_STORE2 = "CubiesNormal";
    private RecordStore rs3 = null;  
    static final String REC_STORE3 = "CubiesHard";	
	private Display display;
	private CubiesTitle cubiesTitle;
	private CubiesMenu cubiesMenu;
	private CubiesSettings cubiesSettings;
	private CubiesInstructions cubiesInstructions;
	private CubiesHighScores cubiesHighScores;
	private CubiesInputName cubiesInputName;
	private CubiesLoading cubiesLoading;
	private CubiesEasyLv1 cubiesEasyLv1;
	private CubiesEasyLv2 cubiesEasyLv2;	
	private CubiesEasyLv3 cubiesEasyLv3;
	private CubiesEasyLv4 cubiesEasyLv4;	
	private CubiesEasyLv5 cubiesEasyLv5;		
	private CubiesNormalLv1 cubiesNormalLv1;
	private CubiesNormalLv2 cubiesNormalLv2;
	private CubiesNormalLv3 cubiesNormalLv3;
	private CubiesNormalLv4 cubiesNormalLv4;
	private CubiesNormalLv5 cubiesNormalLv5;
	private CubiesHardLv1 cubiesHardLv1;
	private CubiesHardLv2 cubiesHardLv2;
	private CubiesHardLv3 cubiesHardLv3;
	private CubiesHardLv4 cubiesHardLv4;
	private CubiesHardLv5 cubiesHardLv5;
	private CubiesEndingCredit1 cubiesEndingCredit1;
	private CubiesEndingCredit2 cubiesEndingCredit2;
	private CubiesEndingCredit3 cubiesEndingCredit3;	
	private String GameLevel;
	final static Command Exit = new Command("Exit", Command.EXIT, 1);
	public boolean ExitGame, ExitEnabled;
	
	public CubiesMain()
	{
		display = Display.getDisplay(this);
		openRecStore();	
		try
		{
			InputStream intro = getClass().getResourceAsStream("/audio/Intro.wav");		
			InputStream opening = getClass().getResourceAsStream("/audio/Opening.wav");
			InputStream movement = getClass().getResourceAsStream("/audio/Movement.wav");
			InputStream append = getClass().getResourceAsStream("/audio/Append.wav");
			InputStream explosion = getClass().getResourceAsStream("/audio/Explosion.wav");				
			InputStream swap = getClass().getResourceAsStream("/audio/Swap.wav");
			InputStream warning = getClass().getResourceAsStream("/audio/Warning.wav");				
			InputStream levelcomplete = getClass().getResourceAsStream("/audio/LevelComplete.wav");			
			InputStream gameover = getClass().getResourceAsStream("/audio/GameOver.wav");
			InputStream credit = getClass().getResourceAsStream("/audio/Credit.wav");
		    CubiesIntro = Manager.createPlayer(intro, "audio/x-wav");			
		    CubiesOpening = Manager.createPlayer(opening, "audio/x-wav");		
		    CubeMovement = Manager.createPlayer(movement, "audio/x-wav");
			Append = Manager.createPlayer(append, "audio/x-wav");	
			Explosion = Manager.createPlayer(explosion, "audio/x-wav");				
		    Swap = Manager.createPlayer(swap, "audio/x-wav");
			Warning = Manager.createPlayer(warning, "audio/x-wav");
			LevelComplete = Manager.createPlayer(levelcomplete, "audio/x-wav");			
		    GameOver = Manager.createPlayer(gameover, "audio/x-wav");
		    Credit = Manager.createPlayer(credit, "audio/x-wav");
			CubiesIntro.realize();
			CubiesOpening.realize();			
			CubeMovement.realize();
			Append.realize();
			Explosion.realize();			
			Swap.realize();
			Warning.realize();
			LevelComplete.realize();
			GameOver.realize();
			Credit.realize();		
			CubiesIntro.prefetch();
			CubiesOpening.prefetch();				
			CubeMovement.prefetch();
			Append.prefetch();
			Explosion.prefetch();			
			Swap.prefetch();
			Warning.prefetch();
			LevelComplete.prefetch();
			GameOver.prefetch();
			Credit.prefetch();
			CubiesIntro.setLoopCount(-1);				
			CubiesOpening.setLoopCount(-1);				
		}
		catch (MediaException me) {}
		catch (IOException ioe) {}
		cubiesTitle = new CubiesTitle(this);
		cubiesMenu = new CubiesMenu(this);	
		cubiesHighScores = new CubiesHighScores(this);		
		cubiesSettings = new CubiesSettings(this);
		cubiesInstructions = new CubiesInstructions(this);
		cubiesInputName = new CubiesInputName(this);
		cubiesLoading = new CubiesLoading(this, cubiesSettings);
		cubiesEasyLv1 = new CubiesEasyLv1(this, cubiesSettings, cubiesInputName);
		cubiesEasyLv2 = new CubiesEasyLv2(this, cubiesSettings, cubiesInputName, cubiesEasyLv1);
		cubiesEasyLv3 = new CubiesEasyLv3(this, cubiesSettings, cubiesInputName, cubiesEasyLv2);
		cubiesEasyLv4 = new CubiesEasyLv4(this, cubiesSettings, cubiesInputName, cubiesEasyLv3);		
		cubiesEasyLv5 = new CubiesEasyLv5(this, cubiesSettings, cubiesInputName, cubiesEasyLv4);		
		cubiesNormalLv1 = new CubiesNormalLv1(this, cubiesSettings, cubiesInputName);
		cubiesNormalLv2 = new CubiesNormalLv2(this, cubiesSettings, cubiesInputName, cubiesNormalLv1);
		cubiesNormalLv3 = new CubiesNormalLv3(this, cubiesSettings, cubiesInputName, cubiesNormalLv2);
		cubiesNormalLv4 = new CubiesNormalLv4(this, cubiesSettings, cubiesInputName, cubiesNormalLv3);		
		cubiesNormalLv5 = new CubiesNormalLv5(this, cubiesSettings, cubiesInputName, cubiesNormalLv4);
		cubiesHardLv1 = new CubiesHardLv1(this, cubiesSettings, cubiesInputName);
		cubiesHardLv2 = new CubiesHardLv2(this, cubiesSettings, cubiesInputName, cubiesHardLv1);
		cubiesHardLv3 = new CubiesHardLv3(this, cubiesSettings, cubiesInputName, cubiesHardLv2);
		cubiesHardLv4 = new CubiesHardLv4(this, cubiesSettings, cubiesInputName, cubiesHardLv3);		
		cubiesHardLv5 = new CubiesHardLv5(this, cubiesSettings, cubiesInputName, cubiesHardLv4);
		cubiesEndingCredit1 = new CubiesEndingCredit1(this);			
		cubiesEndingCredit2 = new CubiesEndingCredit2(this);					
		cubiesEndingCredit3 = new CubiesEndingCredit3(this, cubiesSettings, cubiesInputName, cubiesEasyLv5, cubiesNormalLv5, cubiesHardLv5);			
	}

	public void startApp()
	{
		display.setCurrent(cubiesTitle);
	}
	
	protected void pauseApp()
	{
	}
	
	protected void destroyApp(boolean unconditional)
	{
		closeRecStore();	
		CubiesIntro.deallocate();
		CubiesOpening.deallocate();
		CubeMovement.deallocate();
		Append.deallocate();
		Explosion.deallocate();		
		Swap.deallocate();
		Warning.deallocate();
		LevelComplete.deallocate();		
		GameOver.deallocate();
		Credit.deallocate();
		exitMIDlet();
	}
	
	protected void exitMIDlet()
	{
		notifyDestroyed();	
	}
	
	protected void setCurrent(String d)
	{
		if (d == "InputName")
		{
			display.setCurrent(cubiesInputName);
		}		
		else if (d == "MainMenu")
		{
			try
			{
				CubiesOpening.start();
			}
			catch (MediaException me) {}
			readStream1();
			readStream2();
			readStream3();
			display.setCurrent(cubiesMenu);		
		}
		else if (d == "Loading")
		{
			display.setCurrent(cubiesLoading);
		}
		else if (d == "EasyLv1")
		{
			ExitEnabled = true;
			ExitGame = false;
			cubiesEasyLv1.start();			
			display.setCurrent(cubiesEasyLv1);
			GameLevel = "EasyLv1";
			cubiesEasyLv1.addCommand(Exit);
			cubiesEasyLv1.setCommandListener(this);
		}
		else if (d == "EasyLv2")
		{
			ExitEnabled = true;
			ExitGame = false;
			cubiesEasyLv2.start();			
			display.setCurrent(cubiesEasyLv2);
			GameLevel = "EasyLv2";
			cubiesEasyLv2.addCommand(Exit);
			cubiesEasyLv2.setCommandListener(this);
		}
		else if (d == "EasyLv3")
		{
			ExitEnabled = true;
			ExitGame = false;
			cubiesEasyLv3.start();			
			display.setCurrent(cubiesEasyLv3);
			GameLevel = "EasyLv3";
			cubiesEasyLv3.addCommand(Exit);
			cubiesEasyLv3.setCommandListener(this);
		}
		else if (d == "EasyLv4")
		{
			ExitEnabled = true;
			ExitGame = false;
			cubiesEasyLv4.start();			
			display.setCurrent(cubiesEasyLv4);
			GameLevel = "EasyLv4";
			cubiesEasyLv4.addCommand(Exit);
			cubiesEasyLv4.setCommandListener(this);
		}
		else if (d == "EasyLv5")
		{
			ExitEnabled = true;
			ExitGame = false;
			cubiesEasyLv5.start();			
			display.setCurrent(cubiesEasyLv5);
			GameLevel = "EasyLv5";
			cubiesEasyLv5.addCommand(Exit);
			cubiesEasyLv5.setCommandListener(this);
		}
		else if (d == "NormalLv1")
		{
			ExitEnabled = true;
			ExitGame = false;
			cubiesNormalLv1.start();
			display.setCurrent(cubiesNormalLv1);
			GameLevel = "NormalLv1";
			cubiesNormalLv1.addCommand(Exit);
			cubiesNormalLv1.setCommandListener(this);
		}
		else if (d == "NormalLv2")
		{
			ExitEnabled = true;
			ExitGame = false;
			cubiesNormalLv2.start();			
			display.setCurrent(cubiesNormalLv2);
			GameLevel = "NormalLv2";
			cubiesNormalLv2.addCommand(Exit);
			cubiesNormalLv2.setCommandListener(this);
		}
		else if (d == "NormalLv3")
		{
			ExitEnabled = true;
			ExitGame = false;
			cubiesNormalLv3.start();			
			display.setCurrent(cubiesNormalLv3);
			GameLevel = "NormalLv3";
			cubiesNormalLv3.addCommand(Exit);
			cubiesNormalLv3.setCommandListener(this);			
		}
		else if (d == "NormalLv4")
		{
			ExitEnabled = true;
			ExitGame = false;
			cubiesNormalLv4.start();			
			display.setCurrent(cubiesNormalLv4);
			GameLevel = "NormalLv4";
			cubiesNormalLv4.addCommand(Exit);
			cubiesNormalLv4.setCommandListener(this);			
		}
		else if (d == "NormalLv5")
		{
			ExitEnabled = true;
			ExitGame = false;
			cubiesNormalLv5.start();			
			display.setCurrent(cubiesNormalLv5);
			GameLevel = "NormalLv5";
			cubiesNormalLv5.addCommand(Exit);
			cubiesNormalLv5.setCommandListener(this);			
		}
		else if (d == "HardLv1")
		{
			ExitEnabled = true;
			ExitGame = false;
			cubiesHardLv1.start();			
			display.setCurrent(cubiesHardLv1);
			GameLevel = "HardLv1";
			cubiesHardLv1.addCommand(Exit);
			cubiesHardLv1.setCommandListener(this);
		}
		else if (d == "HardLv2")
		{
			ExitEnabled = true;
			ExitGame = false;
			cubiesHardLv2.start();			
			display.setCurrent(cubiesHardLv2);
			GameLevel = "HardLv2";
			cubiesHardLv2.addCommand(Exit);
			cubiesHardLv2.setCommandListener(this);
		}
		else if (d == "HardLv3")
		{
			ExitEnabled = true;
			ExitGame = false;
			cubiesHardLv3.start();			
			display.setCurrent(cubiesHardLv3);
			GameLevel = "HardLv3";
			cubiesHardLv3.addCommand(Exit);
			cubiesHardLv3.setCommandListener(this);
		}
		else if (d == "HardLv4")
		{
			ExitEnabled = true;
			ExitGame = false;
			cubiesHardLv4.start();			
			display.setCurrent(cubiesHardLv4);
			GameLevel = "HardLv4";
			cubiesHardLv4.addCommand(Exit);
			cubiesHardLv4.setCommandListener(this);
		}
		else if (d == "HardLv5")
		{
			ExitEnabled = true;
			ExitGame = false;
			cubiesHardLv5.start();			
			display.setCurrent(cubiesHardLv5);
			GameLevel = "HardLv5";
			cubiesHardLv5.addCommand(Exit);
			cubiesHardLv5.setCommandListener(this);
		}
		else if (d == "Ending1")
		{	
			cubiesEndingCredit1.start();
			display.setCurrent(cubiesEndingCredit1);
		}	
		else if (d == "Ending2")
		{	
			cubiesEndingCredit2.start();
			display.setCurrent(cubiesEndingCredit2);
		}			
		else if (d == "Ending3")
		{	
			cubiesEndingCredit3.start();
			display.setCurrent(cubiesEndingCredit3);
		}	
		else if (d == "HighScores")
		{
			display.setCurrent(cubiesHighScores);
		}
		else if (d == "Settings")
		{
			display.setCurrent(cubiesSettings);
		}	  
		else if (d == "Instructions")
		{
			display.setCurrent(cubiesInstructions);
		}
	}
	
	public void openRecStore()
	{
		try
		{
			rs1 = RecordStore.openRecordStore(REC_STORE1, true);
			rs2 = RecordStore.openRecordStore(REC_STORE2, true);
			rs3 = RecordStore.openRecordStore(REC_STORE3, true);			
		}
		catch (Exception e) 
		{
			// We should pay attention to the actual error thrown
			db(e.toString());
		}
	}
	
	public void closeRecStore()
	{
		try
		{
			rs1.closeRecordStore();
			rs2.closeRecordStore();
			rs3.closeRecordStore();
		}
		catch (Exception e)
		{
			db(e.toString());
		}
	}

	public void readStream1()
	{
		try
		{
			byte[] recData1 = new byte[50];
			ByteArrayInputStream strmBytes1 = new ByteArrayInputStream(recData1);
			DataInputStream strmDataType1 = new DataInputStream(strmBytes1);		
			if (rs1.getNumRecords() > 0)
			{
				ComparatorInteger comp = new ComparatorInteger();
				RecordEnumeration re1 = rs1.enumerateRecords(null, comp, false);
				int Ranking1 = 0;					
				while (re1.hasNextElement())
				{
					rs1.getRecord(re1.nextRecordId(), recData1, 0);
					if (Ranking1 == 0)
					{
						EasyPlayer1 = strmDataType1.readUTF();
						EasyScore1 = String.valueOf(strmDataType1.readInt());
						EasyLevel1 = strmDataType1.readUTF();
					}
					else if (Ranking1 == 1)
					{
						EasyPlayer2 = strmDataType1.readUTF();
						EasyScore2 = String.valueOf(strmDataType1.readInt());
						EasyLevel2 = strmDataType1.readUTF();
					}
					else if (Ranking1 == 2)
					{
						EasyPlayer3 = strmDataType1.readUTF();
						EasyScore3 = String.valueOf(strmDataType1.readInt());
						EasyLevel3 = strmDataType1.readUTF();
					}
					else if (Ranking1 == 3)
					{
						EasyPlayer4 = strmDataType1.readUTF();
						EasyScore4 = String.valueOf(strmDataType1.readInt());
						EasyLevel4 = strmDataType1.readUTF();
					}
					else if (Ranking1 == 4)
					{
						EasyPlayer5 = strmDataType1.readUTF();
						EasyScore5 = String.valueOf(strmDataType1.readInt());
						EasyLevel5 = strmDataType1.readUTF();
					}			
					Ranking1++;
					strmBytes1.reset();
				}
				comp.compareIntClose();
				re1.destroy();
			}
			strmBytes1.close();
			strmDataType1.close();
		}
		catch (Exception e)
		{
			db(e.toString());
		}
	}	
	
	public void readStream2()
	{
		try
		{
			byte[] recData2 = new byte[50];
			ByteArrayInputStream strmBytes2 = new ByteArrayInputStream(recData2);
			DataInputStream strmDataType2 = new DataInputStream(strmBytes2);
			
			if (rs2.getNumRecords() > 0)
			{
				ComparatorInteger comp = new ComparatorInteger();
				RecordEnumeration re2 = rs2.enumerateRecords(null, comp, false);
				int Ranking2 = 0;				
				while (re2.hasNextElement())
				{
					rs2.getRecord(re2.nextRecordId(), recData2, 0);
					if (Ranking2 == 0)
					{
						NormalPlayer1 = strmDataType2.readUTF();
						NormalScore1 = String.valueOf(strmDataType2.readInt());
						NormalLevel1 = strmDataType2.readUTF();
					}
					else if (Ranking2 == 1)
					{
						NormalPlayer2 = strmDataType2.readUTF();
						NormalScore2 = String.valueOf(strmDataType2.readInt());
						NormalLevel2 = strmDataType2.readUTF();
					}
					else if (Ranking2 == 2)
					{
						NormalPlayer3 = strmDataType2.readUTF();
						NormalScore3 = String.valueOf(strmDataType2.readInt());
						NormalLevel3 = strmDataType2.readUTF();
					}
					else if (Ranking2 == 3)
					{
						NormalPlayer4 = strmDataType2.readUTF();
						NormalScore4 = String.valueOf(strmDataType2.readInt());
						NormalLevel4 = strmDataType2.readUTF();
					}
					else if (Ranking2 == 4)
					{
						NormalPlayer5 = strmDataType2.readUTF();
						NormalScore5 = String.valueOf(strmDataType2.readInt());
						NormalLevel5 = strmDataType2.readUTF();
					}			
					Ranking2++;
					strmBytes2.reset();
				}
				comp.compareIntClose();
				re2.destroy();
			}
			strmBytes2.close();
			strmDataType2.close();
		}
		catch (Exception e)
		{
			db(e.toString());
		}
	}
	
	public void readStream3()
	{
		try
		{
			byte[] recData3 = new byte[50];
			ByteArrayInputStream strmBytes3 = new ByteArrayInputStream(recData3);
			DataInputStream strmDataType3 = new DataInputStream(strmBytes3);
			
			if (rs3.getNumRecords() > 0)
			{
				ComparatorInteger comp = new ComparatorInteger();
				RecordEnumeration re3 = rs3.enumerateRecords(null, comp, false);
				int Ranking3 = 0;				
				while (re3.hasNextElement())
				{
					rs3.getRecord(re3.nextRecordId(), recData3, 0);
					if (Ranking3 == 0)
					{
						HardPlayer1 = strmDataType3.readUTF();
						HardScore1 = String.valueOf(strmDataType3.readInt());
						HardLevel1 = strmDataType3.readUTF();
					}
					else if (Ranking3 == 1)
					{
						HardPlayer2 = strmDataType3.readUTF();
						HardScore2 = String.valueOf(strmDataType3.readInt());
						HardLevel2 = strmDataType3.readUTF();
					}
					else if (Ranking3 == 2)
					{
						HardPlayer3 = strmDataType3.readUTF();
						HardScore3 = String.valueOf(strmDataType3.readInt());
						HardLevel3 = strmDataType3.readUTF();
					}
					else if (Ranking3 == 3)
					{
						HardPlayer4 = strmDataType3.readUTF();
						HardScore4 = String.valueOf(strmDataType3.readInt());
						HardLevel4 = strmDataType3.readUTF();
					}
					else if (Ranking3 == 4)
					{
						HardPlayer5 = strmDataType3.readUTF();
						HardScore5 = String.valueOf(strmDataType3.readInt());
						HardLevel5 = strmDataType3.readUTF();
					}			
					Ranking3++;
					strmBytes3.reset();
				}
				comp.compareIntClose();
				re3.destroy();
			}
			strmBytes3.close();
			strmDataType3.close();
		}
		catch (Exception e)
		{
			db(e.toString());
		}
	}	

	class ComparatorInteger implements RecordComparator
	{
		private byte[] recData = new byte[10];
		private ByteArrayInputStream strmBytes = null;
		private DataInputStream strmDataType = null;
	
		public void compareIntClose()
		{
			try
			{
				if (strmBytes != null)
					strmBytes.close();
				if (strmDataType != null)
					strmDataType.close();
			}
			catch (Exception e) {}
		}
	
		public int compare(byte[] rec1, byte[] rec2)
		{
			int x1, x2;
		
			try
			{
				int maxsize = Math.max(rec1.length, rec2.length);
				if (maxsize > recData.length)
					recData = new byte[maxsize];
				
			strmBytes = new ByteArrayInputStream(rec1);
			strmDataType = new DataInputStream(strmBytes);
			strmDataType.readUTF();       // Read string
			x1 = strmDataType.readInt();  // Read integer
			strmDataType.readUTF();       // Read string			

         // Read record #2
			strmBytes = new ByteArrayInputStream(rec2);
			strmDataType = new DataInputStream(strmBytes);
			strmDataType.readUTF();       // Read string
			x2 = strmDataType.readInt();  // Read integer
			strmDataType.readUTF();       // Read string			

         // Compare record #1 and #2
			if (x1 == x2)
				return RecordComparator.EQUIVALENT;
			else if (x1 > x2)
				return RecordComparator.PRECEDES;
			else
				return RecordComparator.FOLLOWS;
			}
			catch (Exception e)
			{
				return RecordComparator.EQUIVALENT;
			}
		}
	}
	
	public void writeStream1(String[] EasyPlayer, int[] EasyScore, String[] EasyLevel)
	{
		try
		{
			ByteArrayOutputStream strmBytes1 = new ByteArrayOutputStream();
			DataOutputStream strmDataType1 = new DataOutputStream(strmBytes1);
			byte[] record1;
			for (int i = 0; i < EasyPlayer.length; i++)
			{			
				strmDataType1.writeUTF(EasyPlayer[i]);
				strmDataType1.writeInt(EasyScore[i]);
				strmDataType1.writeUTF(EasyLevel[i]);		   
				strmDataType1.flush();
				
				record1 = strmBytes1.toByteArray();
				rs1.addRecord(record1, 0, record1.length);
				
				strmBytes1.reset();
			}
			strmBytes1.close();
			strmDataType1.close();
		}
		catch (Exception e)
		{
			db(e.toString());
		}
	}

	public void writeStream2(String[] NormalPlayer, int[] NormalScore, String[] NormalLevel)
	{
		try
		{
			ByteArrayOutputStream strmBytes2 = new ByteArrayOutputStream();
			DataOutputStream strmDataType2 = new DataOutputStream(strmBytes2);
			byte[] record2;
			
			for (int i = 0; i < NormalPlayer.length; i++)
			{
				strmDataType2.writeUTF(NormalPlayer[i]);
				strmDataType2.writeInt(NormalScore[i]);
				strmDataType2.writeUTF(NormalLevel[i]);		   
				strmDataType2.flush();
				
				record2 = strmBytes2.toByteArray();
				rs2.addRecord(record2, 0, record2.length);
				
				strmBytes2.reset();
			}
			strmBytes2.close();
			strmDataType2.close();
		}
		catch (Exception e)
		{
			db(e.toString());
		}
	}
	
	public void writeStream3(String[] HardPlayer, int[] HardScore, String[] HardLevel)
	{
		try
		{
			ByteArrayOutputStream strmBytes3 = new ByteArrayOutputStream();
			DataOutputStream strmDataType3 = new DataOutputStream(strmBytes3);
			byte[] record3;
			
			for (int i = 0; i < HardPlayer.length; i++)
			{
				strmDataType3.writeUTF(HardPlayer[i]);
				strmDataType3.writeInt(HardScore[i]);
				strmDataType3.writeUTF(HardLevel[i]);		   
				strmDataType3.flush();
				
				record3 = strmBytes3.toByteArray();
				rs3.addRecord(record3, 0, record3.length);
				
				strmBytes3.reset();
			}
			strmBytes3.close();
			strmDataType3.close();
		}
		catch (Exception e)
		{
			db(e.toString());
		}
	}	
	
	private void db(String str)
	{
		System.err.println("Msg: " + str);
	}
	
	public void commandAction(Command c, Displayable s)
	{
		if (c.getCommandType() == Command.EXIT)
		{
			if (ExitEnabled != false)
			{
				if (GameLevel == "EasyLv1")
				{
					ExitGame = true;
					cubiesEasyLv1.stop();
					cubiesEasyLv1.stopTimer();
				}
				else if (GameLevel == "EasyLv2")
				{
					ExitGame = true;
					cubiesEasyLv2.stop();
					cubiesEasyLv2.stopTimer();
				}
				else if (GameLevel == "EasyLv3")
				{
					ExitGame = true;
					cubiesEasyLv3.stop();
					cubiesEasyLv3.stopTimer();
				}
				else if (GameLevel == "EasyLv4")
				{
					ExitGame = true;
					cubiesEasyLv4.stop();
					cubiesEasyLv4.stopTimer();
				}
				else if (GameLevel == "EasyLv5")
				{
					ExitGame = true;
					cubiesEasyLv5.stop();
					cubiesEasyLv5.stopTimer();
				}
				else if (GameLevel == "NormalLv1")
				{
					ExitGame = true;
					cubiesNormalLv1.stop();
					cubiesNormalLv1.stopTimer();
				}
				else if (GameLevel == "NormalLv2")
				{
					ExitGame = true;
					cubiesNormalLv2.stop();
					cubiesNormalLv2.stopTimer();
				}
				else if (GameLevel == "NormalLv3")
				{
					ExitGame = true;
					cubiesNormalLv3.stop();
					cubiesNormalLv3.stopTimer();
				}
				else if (GameLevel == "NormalLv4")
				{
					ExitGame = true;
					cubiesNormalLv4.stop();
					cubiesNormalLv4.stopTimer();
				}
				else if (GameLevel == "NormalLv5")
				{
					ExitGame = true;
					cubiesNormalLv5.stop();
					cubiesNormalLv5.stopTimer();
				}
				else if (GameLevel == "HardLv1")
				{
					ExitGame = true;
					cubiesHardLv1.stop();
					cubiesHardLv1.stopTimer();
				}
				else if (GameLevel == "HardLv2")
				{
					ExitGame = true;
					cubiesHardLv2.stop();
					cubiesHardLv2.stopTimer();
				}
				else if (GameLevel == "HardLv3")
				{
					ExitGame = true;
					cubiesHardLv3.stop();
					cubiesHardLv3.stopTimer();
				}
				else if (GameLevel == "HardLv4")
				{
					ExitGame = true;
					cubiesHardLv4.stop();
					cubiesHardLv4.stopTimer();
				}
				else if (GameLevel == "HardLv5")
				{
					ExitGame = true;
					cubiesHardLv5.stop();
					cubiesHardLv5.stopTimer();
				}
				try
				{
					CubiesOpening.start();
				}
				catch (MediaException me) {}
				display.setCurrent(cubiesMenu);
			}
		}	
	}
}