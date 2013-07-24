package com.moribitotech.and.fw.data;

import com.moribitotech.and.fw.models.Word;

public class PuzzleLevel
{
  private int puzzleLevelNumber;
  private Word word;

  public int getPuzzleLevelNumber()
  {
    return this.puzzleLevelNumber;
  }

  public Word getWord()
  {
    return this.word;
  }

  public void setPuzzleLevelNumber(int paramInt)
  {
    this.puzzleLevelNumber = paramInt;
  }

  public void setWord(Word paramWord)
  {
    this.word = paramWord;
  }
}