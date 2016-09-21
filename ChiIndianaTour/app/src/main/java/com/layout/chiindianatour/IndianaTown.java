package com.layout.chiindianatour;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.app.Activity;

public class IndianaTown extends Activity implements IndianaFragment.ListSelectionListener {
    public static String[] mInterestArray;
    private String configChange;


    private final IndWebpageFragment mIndWebpageFragment = new IndWebpageFragment();
    private FragmentManager mFragmentManager;
    private FrameLayout mIndianaFrameLayout, mIndWebpageFrameLayout;

    private static final int MATCH_PARENT = LinearLayout.LayoutParams.MATCH_PARENT;
    private static final String TAG = "IndianaTownActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, getClass().getSimpleName() + ":entered onCreate()");

        mInterestArray = getResources().getStringArray(R.array.IndianapolisPlaces);



        setContentView(R.layout.content_indiana_town);


        // Get references to the IndianaFragment and to the IndWebpageFragment
        mIndianaFrameLayout = (FrameLayout) findViewById(R.id.indiana_places_container);
        mIndWebpageFrameLayout = (FrameLayout) findViewById(R.id.indiana_webpage_container);


        // Get a reference to the FragmentManager
        mFragmentManager = getFragmentManager();

        // Start a new FragmentTransaction
        FragmentTransaction fragmentTransaction = mFragmentManager
                .beginTransaction();

        // Add the IndianaFragment to the layout
        fragmentTransaction.add(R.id.indiana_places_container, new IndianaFragment());

        // Commit the FragmentTransaction
        fragmentTransaction.commit();

        // Add a OnBackStackChangedListener to reset the layout when the back stack changes
        mFragmentManager
                .addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
                    public void onBackStackChanged() {

                        setLayout();
                    }
                });
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
            configChange = "Landscape";
            System.out.println("In land");
            retainLandscapeState();
        }
        else
        {
            configChange = "Portrait";
            retainPortraitState();
        }
    }

    private void setLayout() {
        // Determine whether the IndWebpageFragment has been added
        if (!mIndWebpageFragment.isAdded()) {

            // Make the IndianaFragment occupy the entire layout
            mIndianaFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    MATCH_PARENT, MATCH_PARENT));
            mIndWebpageFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                    MATCH_PARENT));
        } else {
            if (configChange=="Landscape") {
                // Make the IndianaFragment Layout take 1/3 of the layout's width
                System.out.println("In Landscape");
                mIndianaFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                        MATCH_PARENT, 1f));

                // Make the IndWebpage Fragment Layout take 2/3's of the layout's width
                mIndWebpageFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                        MATCH_PARENT, 2f));
            }
            else {
                // Make the IndianaFragment occupy the entire layout
                System.out.println("In Portrait");
                mIndianaFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(
                        0, MATCH_PARENT));
                mIndWebpageFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(MATCH_PARENT,
                        MATCH_PARENT));
            }

        }

    }

    public void retainPortraitState(){
        if(mIndWebpageFragment.isAdded()){
            mIndianaFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    0, MATCH_PARENT));
            mIndWebpageFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(MATCH_PARENT,
                    MATCH_PARENT));
        }
    }

    public void retainLandscapeState(){
        if(mIndWebpageFragment.isAdded()){
            mIndianaFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    0, MATCH_PARENT,1f));
            mIndWebpageFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                    MATCH_PARENT, 2f));
        }
    }

    @Override
    public void onListSelection(int index) {
        // If the IndWebpage Fragment has not been added, add it now
        if (!mIndWebpageFragment.isAdded()) {

            // Start a new FragmentTransaction
            FragmentTransaction fragmentTransaction = mFragmentManager
                    .beginTransaction();

            // Add the IndWebpage Fragment to the layout
            fragmentTransaction.add(R.id.indiana_webpage_container, mIndWebpageFragment);

            // Add this FragmentTransaction to the backstack
            fragmentTransaction.addToBackStack(null);

            // Commit the FragmentTransaction
            fragmentTransaction.commit();

            // Force Android to execute the committed FragmentTransaction
            mFragmentManager.executePendingTransactions();
        }



        // Tell the IndWebpage Fragment to show the webpage position index

        if(mIndWebpageFragment.getShownIndex()!=index) {
            mIndWebpageFragment.displayWebPage(index);
        }






    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ind_town, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()){
            case R.id.action_chicago:
                Intent intent = new Intent(this,ChiTown.class);
                startActivity(intent);
                return true;

            default:
                return false;
        }
    }
}
