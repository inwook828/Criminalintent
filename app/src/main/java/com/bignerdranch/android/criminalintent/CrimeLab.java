package com.bignerdranch.android.criminalintent;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by jSonKang on 1/28/18.
 * This Class will hold array of Crime objects
 */

public class CrimeLab
{
    //the 's' is to denote that the variable is static
    private static CrimeLab m_sCrimeLab;
    private Crime  m_Crime;
    //Declaring it as generic List will enable the list to be casted into other List types
    private List<Crime> m_CrimeList;

    /**
     * Default constructor for CrimeLab
     * Create Array List for Crime
     * @param context
     */
    private CrimeLab(Context context)
    {
        m_CrimeList = new ArrayList<>();
        PopulateCrimeListWithDummyData();
    }

    /**
     * Singleton that will return the static CrimeLab Object
     * @return CrimeLab Object
     */
    public static CrimeLab get(Context context)
    {
        if(m_sCrimeLab == null)
        {
            m_sCrimeLab = new CrimeLab(context);
        }
        return m_sCrimeLab;
    }

    /**
     * Returns the Crime Object for the given UUID
     * @param uuid - Passed UUID for Crime Object
     * @return Crime - Matching Crime Object with UUID
     */
    public Crime getCrime(UUID uuid)
    {
        for (Crime crime : m_CrimeList)
        {
            if(crime.getID().equals(uuid))
            {
                return crime;
            }
        }
        return null;
    }

    /**
     * Returns List of Crimes
     * @return
     */
    public List<Crime> getCrimeList()
    {
        return m_CrimeList;
    }

    private void PopulateCrimeListWithDummyData()
    {
        for (int i = 1; i <= 100; i++)
        {
            Crime crime = new Crime();
            crime.setTitle("Crime #" + i);
            crime.setSolved(i%3 == 0);
            crime.setCrimeLevel(i%2);
            m_CrimeList.add(crime);
        }
    }

}
