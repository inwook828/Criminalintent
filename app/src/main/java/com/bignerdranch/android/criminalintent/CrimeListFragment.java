package com.bignerdranch.android.criminalintent;

import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.List;

/**
 * Created by jSonKang on 1/28/18.
 * Creates the RecyclerView for the app
 * Binds the layout to the activity
 */

public class CrimeListFragment extends android.support.v4.app.Fragment
{
    //RecyclerView that will be used
    private RecyclerView m_CrimeRecyclerView;
    //Adapter that will bind the data to the View
    private CrimeAdapter m_CrimeAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance)
    {
        View view = inflater.inflate(R.layout.fragment_crime_list, container, false);
        m_CrimeRecyclerView = view.findViewById(R.id.crime_recycler_view);
        //Delegates the job to position items and how scrolling works
        //There are many different kinds of Layout managers which extends LayoutManager
        //Different ones can be used here
        m_CrimeRecyclerView.setLayoutManager(new LinearLayoutManager((getActivity())));

        updateUI();

        return view;
    }

    /**
     * Connects the RecyclerView to the Adapter
     */
    private void updateUI()
    {
        CrimeLab crimeLab = CrimeLab.get(getActivity());
        List<Crime> listCrimes = crimeLab.getCrimeList();

        m_CrimeAdapter = new CrimeAdapter(listCrimes);
        m_CrimeRecyclerView.setAdapter(m_CrimeAdapter);
    }

    public abstract class AbstractCrimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        //Property
        Crime m_Crime;
        /**
         * Construct for Base ViewHolder Object
         * @param inflater
         * @param viewGroup
         */
        public AbstractCrimeHolder(LayoutInflater inflater, ViewGroup viewGroup, int layoutID)
        {
            super(inflater.inflate(layoutID, viewGroup,false));

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view)
        {
            Crime crime = new Crime();
            Toast.makeText(getActivity(), m_Crime.getTitle() + " Clicked!", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Class that will inflate the layout of ViewHolder
     * Defines the ViewHolder
     * Responsible for Binding Data
     */
    private class CrimeHolder extends AbstractCrimeHolder
    {
        ImageView mCrimeSolvedImageView;
        TextView mTitleTextView;
        TextView mDateTextView;

        //Constructor that will Call base class constructor
        public CrimeHolder(LayoutInflater inflater, ViewGroup parent)
        {
            super(inflater, parent, R.layout.list_item_crime);

            mCrimeSolvedImageView = itemView.findViewById(R.id.image_crime_solved);

            mTitleTextView = itemView.findViewById(R.id.crime_title);
            mDateTextView = itemView.findViewById(R.id.crime_date);
        }

        /**
         * Binds all the widgets in the current fragment
         * This will only happen one time
         * @param crime
         */
        public void bind(Crime crime)
        {
            m_Crime = crime;
            mCrimeSolvedImageView.setVisibility(m_Crime.isSolved() ? View.VISIBLE : View.GONE);

            mTitleTextView.setText(m_Crime.getTitle());
            mDateTextView.setText(DateFormat.getDateInstance().format(m_Crime.getDate()));
        }
    }

    /**
     * Class that will be resposible for displaying a different
     * Row of Crime data with a button that can enact as
     * Calling Police
     */
    private class CrimeLevelHolder extends AbstractCrimeHolder
    {
        //Property
        Button mButton;
        TextView mTitleTextView;
        TextView mDateTextView;

        //Constructor that will Call base class constructor
        public CrimeLevelHolder(LayoutInflater inflater, ViewGroup parent)
        {
            super(inflater, parent, R.layout.list_item_crime_level);

            mButton = itemView.findViewById(R.id.crime_level_button);
            mTitleTextView = itemView.findViewById(R.id.crime_title);
            mDateTextView = itemView.findViewById(R.id.crime_date);
        }

        public void bind(Crime crime)
        {
            m_Crime = crime;
            SimpleDateFormat spf = new SimpleDateFormat("EEEE");
            String strCurrentDay = spf.format(m_Crime.getDate());
            mTitleTextView.setText(crime.getTitle());
            mDateTextView.setText(strCurrentDay + ", " + DateFormat.getDateInstance().format(m_Crime.getDate()));

            mButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Toast.makeText(getActivity(), "Call the Cops Button!", Toast.LENGTH_SHORT);
                }
            });
        }
    }

    /**
     * Class that knows about the Crime's details.
     * RecyclerView will know about each Crime object through
     * this Adapter Class
     */
    private class CrimeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
    {
        private static final int CRIME_LEVEL_HIGH = 0;
        private static final int CRIME_LEVEL_LOW = 1;

        //Holds all Crime Objects
        List<Crime> m_CrimeList;

        public CrimeAdapter(List<Crime> crimeList)
        {
            m_CrimeList = crimeList;
        }

        /**
         * Creates the ViewHolder that is called by the RecyclerView
         * Returns the CrimeHolder
         * This will get called for each TextView that is to be displayed on the screen.
         * If the TextView has already been created, it will not call this method.
         * @param parent
         * @param viewType
         * @return
         */
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            switch(getItemViewType(viewType))
            {
                case CRIME_LEVEL_HIGH:
                {
                    return new CrimeLevelHolder(layoutInflater, parent);
                }
                case CRIME_LEVEL_LOW:
                {
                    return new CrimeHolder(layoutInflater, parent);
                }
                default:
                {
                    return null;
                }
            }
        }

        /**
         * Binds the Data on to the View
         * @param holder
         * @param position
         */
        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
        {
            Crime crime = m_CrimeList.get(position);

            if(holder instanceof CrimeHolder)
            {
                CrimeHolder crimeHolder = (CrimeHolder)holder;
                crimeHolder.bind(crime);
            }
            else
            {
                CrimeLevelHolder crimeLevelholder = (CrimeLevelHolder)holder;
                crimeLevelholder.bind(crime);
            }
        }

        /**
         * Returns the Total Items in Crime List
         * @return List<Crime>
         */
        @Override
        public int getItemCount()
        {
            return m_CrimeList.size();
        }

        @Override
        public int getItemViewType(int position)
        {
            int retInt;
            int iCrimeLevel = m_CrimeList.get(position).getCrimeLevel();

            switch(iCrimeLevel)
            {
                case 0:
                {
                    retInt = CRIME_LEVEL_LOW;
                    break;
                }
                case 1:
                {
                    retInt = CRIME_LEVEL_HIGH;
                    break;
                }
                default:
                {
                    retInt = CRIME_LEVEL_LOW;
                    break;
                }
            }

            return retInt;
        }
    }
}
