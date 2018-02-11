package com.bignerdranch.android.criminalintent;

//This is the version of the support library version

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

/**
 * Created by jSonKang on 3/19/17.
 * This class is the Fragment class
 * All of the fragments properties and behavior is done here
 */

public class CrimeFragment extends Fragment
{
    private Crime mCrime;
    private EditText mTitleField;
    private Button mDateButton;
    private CheckBox mSolvedCheckBox;

    /**
     * Creation of Fragment layout
     * @param savedInstance
     */
    @Override
    public void onCreate(Bundle savedInstance)
    {
        super.onCreate(savedInstance);
        mCrime = new Crime();
    }

    /**
     * This method is what the activity class will call to display the Fragment.
     * @param inflater - Used to inflate the layout
     * @param container - Used to inflate the layout
     * @param savedInstanceState - Used to get the data in the active view or state.
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_crime, container, false);

        mTitleField = v.findViewById(R.id.crime_title);
        mTitleField.addTextChangedListener(CreateTextWatcher());

        mDateButton = v.findViewById(R.id.crime_date);
        mDateButton.setText(mCrime.getDate().toString());
        mDateButton.setEnabled(false);

        mSolvedCheckBox = v.findViewById(R.id.image_crime_solved);
        mSolvedCheckBox.setOnCheckedChangeListener(CreateCheckedChangeListener());


        return v;
    }

    /**
     * Creates the OnCheckedChangeListener Object and Returns it
     * @return - Populates Check Button Object
     */
    private CompoundButton.OnCheckedChangeListener CreateCheckedChangeListener()
    {
        return new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b)
            {
                mCrime.setSolved(b);
            }
        };
    }

    /**
     * Creates the TextWatcher Object and returns it
     * @return - Populated Text Fields
     */
    private TextWatcher CreateTextWatcher()
    {
        return new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                //Intentionally Left Blank
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                mCrime.setTitle(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable)
            {
                //Intentionally Left Blank
            }
        };
    }
}
