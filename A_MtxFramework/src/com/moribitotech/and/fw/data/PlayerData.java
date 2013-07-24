package com.moribitotech.and.fw.data;

public class PlayerData
{
  private int correctGuess = 0;
  private int score = 0;
  private int stage = 0;
  private int wrongGuess = 0;

  public void decreaseScore(int paramInt)
  {
    if (this.score - paramInt >= 0)
    {
      this.score -= paramInt;
      return;
    }
    this.score = 0;
  }

  public int getCorrectGuess()
  {
    return this.correctGuess;
  }

  public int getScore()
  {
    return this.score;
  }

  public int getStage()
  {
    return this.stage;
  }

  public int getWrongGuess()
  {
    return this.wrongGuess;
  }

  public void increaseScore(int paramInt)
  {
    this.score = (paramInt + this.score);
  }

  public void setCorrectGuess(int paramInt)
  {
    this.correctGuess = paramInt;
  }

  public void setScore(int paramInt)
  {
    this.score = paramInt;
  }

  public void setStage(int paramInt)
  {
    this.stage = paramInt;
  }

  public void setWrongGuess(int paramInt)
  {
    this.wrongGuess = paramInt;
  }
}