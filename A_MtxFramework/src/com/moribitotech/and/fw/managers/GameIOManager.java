package com.moribitotech.and.fw.managers;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.moribitotech.and.fw.data.GameData;
import com.moribitotech.and.fw.data.PlayerData;
import com.moribitotech.and.fw.data.PuzzleLevel;
import com.moribitotech.and.fw.models.Word;
import com.moribitotech.mtx.managers.FileManager;
import com.moribitotech.mtx.managers.FileManager.FileType;

public class GameIOManager
{
  private static final int LINE_0_METER_BURN = 0;
  private static final int LINE_0_STAGE = 0;
  private static final int LINE_1_METER_WATER = 1;
  private static final int LINE_1_SCORE = 1;
  private static final int LINE_2_BOTTOM_FIRE = 2;
  private static final int LINE_2_CORRECT_GUESS = 2;
  private static final int LINE_3_HINT_NUMBER = 3;
  private static final int LINE_3_WRONG_GUESS = 3;
  private static final int LINE_4_FINGER_BURN = 4;
  private static final String TXT_GAME = "txtgame.txt";
  private static final String TXT_PLAYER = "txtplayer.txt";
  private static final String TXT_WORDS = "data/words.txt";

  public static void createFirstLaunchFiles()
  {
    FileManager.createTextFileInLocalStorage("txtplayer.txt");
    FileManager.createTextFileInLocalStorage("txtgame.txt");
    FileManager.writeLine("txtplayer.txt", "0", FileManager.FileType.LOCAL_FILE);
    FileManager.writeLine("txtplayer.txt", "0", FileManager.FileType.LOCAL_FILE);
    FileManager.writeLine("txtplayer.txt", "0", FileManager.FileType.LOCAL_FILE);
    FileManager.writeLine("txtplayer.txt", "0", FileManager.FileType.LOCAL_FILE);
    FileManager.writeLine("txtgame.txt", "0", FileManager.FileType.LOCAL_FILE);
    FileManager.writeLine("txtgame.txt", "0", FileManager.FileType.LOCAL_FILE);
    FileManager.writeLine("txtgame.txt", "1.0", FileManager.FileType.LOCAL_FILE);
    FileManager.writeLine("txtgame.txt", "5", FileManager.FileType.LOCAL_FILE);
    FileManager.writeLine("txtgame.txt", "0", FileManager.FileType.LOCAL_FILE);
  }

  public static GameData getGameData()
  {
    GameData localGameData = new GameData();
    String str1 = FileManager.readLine("txtgame.txt", 0, FileManager.FileType.LOCAL_FILE);
    String str2 = FileManager.readLine("txtgame.txt", 1, FileManager.FileType.LOCAL_FILE);
    String str3 = FileManager.readLine("txtgame.txt", 2, FileManager.FileType.LOCAL_FILE);
    String str4 = FileManager.readLine("txtgame.txt", 3, FileManager.FileType.LOCAL_FILE);
    String str5 = FileManager.readLine("txtgame.txt", 4, FileManager.FileType.LOCAL_FILE);
    localGameData.setCurrentMeterBurn(Integer.parseInt(str1));
    localGameData.setCurrentMeterWater(Integer.parseInt(str2));
    localGameData.setBottomFireScale(Float.parseFloat(str3));
    localGameData.setHintNumber(Integer.parseInt(str4));
    localGameData.setFireBurnNumber(Integer.parseInt(str5));
    return localGameData;
  }

  public static PlayerData getPlayerData()
  {
    PlayerData localPlayerData = new PlayerData();
    String str1 = FileManager.readLine("txtplayer.txt", 0, FileManager.FileType.LOCAL_FILE);
    String str2 = FileManager.readLine("txtplayer.txt", 1, FileManager.FileType.LOCAL_FILE);
    String str3 = FileManager.readLine("txtplayer.txt", 2, FileManager.FileType.LOCAL_FILE);
    String str4 = FileManager.readLine("txtplayer.txt", 3, FileManager.FileType.LOCAL_FILE);
    localPlayerData.setStage(Integer.parseInt(str1));
    localPlayerData.setScore(Integer.parseInt(str2));
    localPlayerData.setCorrectGuess(Integer.parseInt(str3));
    localPlayerData.setWrongGuess(Integer.parseInt(str4));
    return localPlayerData;
  }

  public static PuzzleLevel getPuzzleLevel(int paramInt, BitmapFont paramBitmapFont)
  {
    PuzzleLevel localPuzzleLevel = new PuzzleLevel();
    localPuzzleLevel.setWord(new Word(FileManager.readLine("data/words.txt", paramInt, FileManager.FileType.INTERNAL_FILE), false, 125.0F, 125.0F, paramBitmapFont));
    localPuzzleLevel.setPuzzleLevelNumber(paramInt);
    return localPuzzleLevel;
  }

	public static void saveGame(PlayerData paramPlayerData,
			GameData paramGameData)
  {
    savePlayerData(paramPlayerData);
    saveGameData(paramGameData);
  }
//save
  private static void saveGameData(GameData paramGameData)
  {
    FileManager.writeExistingLine("txtgame.txt", 0, String.valueOf(paramGameData.getCurrentMeterBurn()), FileManager.FileType.LOCAL_FILE);
    FileManager.writeExistingLine("txtgame.txt", 1, String.valueOf(paramGameData.getCurrentMeterWater()), FileManager.FileType.LOCAL_FILE);
    FileManager.writeExistingLine("txtgame.txt", 2, String.valueOf(paramGameData.getBottomFireScale()), FileManager.FileType.LOCAL_FILE);
    FileManager.writeExistingLine("txtgame.txt", 3, String.valueOf(paramGameData.getHintNumber()), FileManager.FileType.LOCAL_FILE);
    FileManager.writeExistingLine("txtgame.txt", 4, String.valueOf(paramGameData.getFireBurnNumber()), FileManager.FileType.LOCAL_FILE);
  }
//save player
  private static void savePlayerData(PlayerData paramPlayerData)
  {
    FileManager.writeExistingLine("txtplayer.txt", 0, String.valueOf(paramPlayerData.getStage()), FileManager.FileType.LOCAL_FILE);
    FileManager.writeExistingLine("txtplayer.txt", 1, String.valueOf(paramPlayerData.getScore()), FileManager.FileType.LOCAL_FILE);
    FileManager.writeExistingLine("txtplayer.txt", 2, String.valueOf(paramPlayerData.getCorrectGuess()), FileManager.FileType.LOCAL_FILE);
    FileManager.writeExistingLine("txtplayer.txt", 3, String.valueOf(paramPlayerData.getWrongGuess()), FileManager.FileType.LOCAL_FILE);
  }
}