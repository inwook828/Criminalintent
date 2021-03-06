package com.bignerdranch.android.criminalintent;

import java.util.Date;
import java.util.UUID;

/**
 * Created by jSonKang on 3/19/17.
 * This is the MODEL class for this application
 * Hold all the necessary data needed for the Crime Object
 */

public class Crime
{
    private UUID mID;
    private String mTitle;
    private Date mDate;
    private boolean mSolved;
    private int mRequiresPolice;

    public UUID getID()
    {
        return mID;
    }

    public Date getDate()
    {
        return mDate;
    }

    public boolean isSolved() { return mSolved; }

    public void setTitle(String title)
    {
        mTitle = title;
    }

    public void setDate(Date date)
    {
        mDate = date;
    }

    public void setSolved(boolean solved)
    {
        mSolved = solved;
    }

    public void setCrimeLevel(int level)
    {
        mRequiresPolice = level;
    }

    public int getCrimeLevel()
    {
        return mRequiresPolice;
    }

    public String getTitle()
    {
        return mTitle;
    }

    public Crime()
    {
        mID = UUID.randomUUID();
        mDate = new Date();
    }
}
