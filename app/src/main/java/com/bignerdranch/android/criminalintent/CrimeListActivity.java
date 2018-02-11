package com.bignerdranch.android.criminalintent;

/**
 * Created by jSonKang on 1/28/18.
 * This Class will be the launcher activity.
 * This means that it will be the first screen
 * that he user will see when it is first opened.
 */

public class CrimeListActivity extends SingleFragmentActivity
{
    @Override
    protected android.support.v4.app.Fragment createFragment()
    {
        return new CrimeListFragment();
    }
}
