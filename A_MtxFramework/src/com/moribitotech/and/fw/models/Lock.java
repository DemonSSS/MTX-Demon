package com.moribitotech.and.fw.models;

public class Lock
{
  private boolean locked = false;

  public boolean isLocked()
  {
    return this.locked;
  }

  public void setLocked(boolean paramBoolean)
  {
    this.locked = paramBoolean;
  }
}