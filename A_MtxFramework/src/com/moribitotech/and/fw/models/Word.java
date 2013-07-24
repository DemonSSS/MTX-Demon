package com.moribitotech.and.fw.models;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.moribitotech.mtx.utils.UtilsOrigin;
import com.moribitotech.mtx.utils.UtilsOrigin.Origin;
import java.util.Random;

public class Word extends Table
{
  private boolean isWordCorrect;
  private boolean isWordReset;
  private BitmapFont letterFont;
  private float letterHeight;
  private float letterWidth;
  private Letter[] letters;
  private int numberOfTouches;
  private String puzzleString;
  private String puzzleStringBeingWritten;

  public Word(String paramString, boolean paramBoolean, float paramFloat1, float paramFloat2, BitmapFont paramBitmapFont)
  {
    this.puzzleString = paramString;
    this.letterWidth = paramFloat1;
    this.letterHeight = paramFloat2;
    this.letterFont = paramBitmapFont;
    this.numberOfTouches = 0;
    this.isWordReset = false;
    this.puzzleStringBeingWritten = "";
    setUpLetters(paramString);
  }

  private void setUpLetters(String paramString)
  {
    int i = paramString.length();
    this.letters = new Letter[i];
    Random localRandom = new Random();
    for (int j = 0; ; j++)
    {
      if (j >= i)
        return;
      char c = paramString.charAt(j);
      Letter localLetter = new Letter(this.letterWidth, this.letterHeight, true, localRandom, this.letterFont);
      localLetter.setPuzzleLetter(c);
      localLetter.getLabel().setText(c+"");
      localLetter.setName(c+"");
      localLetter.setIndexOriginal(j);
      UtilsOrigin.setActorOrigin(localLetter, UtilsOrigin.Origin.CENTER);
      this.letters[j] = localLetter;
      Gdx.app.log("LetterLog", "Letter: " + c + " , Index: " + j);
    }
  }

  public boolean getIsWordCorrect()
  {
    return this.isWordCorrect;
  }

  public Letter[] getLetters()
  {
    return this.letters;
  }

  public int getNumberOfTouches()
  {
    return this.numberOfTouches;
  }

  public String getPuzzleString()
  {
    return this.puzzleString;
  }

  public String getPuzzleStringBeingWritten()
  {
    return this.puzzleStringBeingWritten;
  }

  public void increaseNumberOfTouches()
  {
    this.numberOfTouches = (1 + this.numberOfTouches);
  }

  public boolean isWordReset()
  {
    return this.isWordReset;
  }

  public void reset()
  {
    setWordReset(true);
    setNumberOfTouches(0);
    for (int i = 0; ; i++)
    {
      if (i >= getLetters().length)
        return;
      getLetters()[i].setTouched(false);
    }
  }

  public void setIsWordCorrect(boolean paramBoolean)
  {
    this.isWordCorrect = paramBoolean;
  }

  public void setLetters(Letter[] paramArrayOfLetter)
  {
    this.letters = paramArrayOfLetter;
  }

  public void setNumberOfTouches(int paramInt)
  {
    this.numberOfTouches = paramInt;
  }

  public void setPuzzleString(String paramString)
  {
    this.puzzleString = paramString;
  }

  public void setPuzzleStringBeingWritten(String paramString)
  {
    this.puzzleStringBeingWritten = paramString;
  }

  public void setWordReset(boolean paramBoolean)
  {
    this.isWordReset = paramBoolean;
  }
}