package com.bignerdranch.android.criminalintent;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * CrimeActivity - Responsible for holding CrimeFragment object
 * Holds Fragment Manager
 * Changed in Ch.8 pg 164 - AppCompatActivity to SingleFragmentActivity
 */
public class CrimeActivity extends SingleFragmentActivity
{
    @Override
    protected android.support.v4.app.Fragment createFragment()
    {
        return new Fragment();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        InitializeFragmentManager();
    }

    private void InitializeFragmentManager()
    {
        android.support.v4.app.FragmentManager FM = getSupportFragmentManager();

        Fragment crimeFragment = FM.findFragmentById(R.id.fragment_container);

        if(crimeFragment == null)
        {
            //CrimeFragment EXTENDS Fragment
            crimeFragment = new CrimeFragment();
            //Creates and commits the Fragment Transactions
            //Create a new fragment transaction, include one add operation in it, commit it
            FM.beginTransaction()
                    //Resource ID tells where in the activity's view the fragment will appear
                    //Also used as an unique ID in the FragmentManager List
                    .add(R.id.fragment_container, crimeFragment)
                    .commit();
        }
    }
}
