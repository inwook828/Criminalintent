package com.bignerdranch.android.criminalintent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by jSonKang on 1/28/18.
 */

public abstract class SingleFragmentActivity extends AppCompatActivity
{
    protected abstract android.support.v4.app.Fragment createFragment();

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
            crimeFragment = createFragment();
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
