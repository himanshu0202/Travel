package com.layout.chiindianatour;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.content.res.Configuration;



public class ChiTown extends Activity
        implements ChicagoFragment.ListSelectionListener {
    public static String[] mInterestArray;
    private String configChange;


    private final ChiWebpageFragment mChiWebpageFragment = new ChiWebpageFragment();
    private FragmentManager mFragmentManager;
    private FrameLayout mChicagoFrameLayout, mChiWebpageFrameLayout;
    private static final int MATCH_PARENT = LinearLayout.LayoutParams.MATCH_PARENT;
    private static final String TAG = "ChiTownActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




        Log.i(TAG, getClass().getSimpleName() + ":entered onCreate()");

        mInterestArray = getResources().getStringArray(R.array.ChicagoPlaces);



        setContentView(R.layout.content_chi_town);

        // Get references to the ChicagoFragment and to the ChiWebpageFragment
        mChicagoFrameLayout = (FrameLayout) findViewById(R.id.interest_fragment_container);

        mChiWebpageFrameLayout = (FrameLayout) findViewById(R.id.webpage_fragment_container);


        // Get a reference to the FragmentManager
        mFragmentManager = getFragmentManager();

        // Start a new FragmentTransaction
        FragmentTransaction fragmentTransaction = mFragmentManager
                .beginTransaction();

        // Add the ChicagoFragment to the layout
        fragmentTransaction.add(R.id.interest_fragment_container, new ChicagoFragment());

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

            retainLandscapeState();
        }
        else
        {
            configChange = "Portrait";

            retainPortraitState();
        }
    }


    private void setLayout() {
            // Determine whether the ChiWebpageFragment has been added
           if (!mChiWebpageFragment.isAdded()) {

                    // Make the ChicagoFragment occupy the entire layout
                    mChicagoFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(
                            MATCH_PARENT, MATCH_PARENT));

                    mChiWebpageFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                            MATCH_PARENT));
            } else {
               if (configChange=="Landscape") {
                   // Make the ChicagoLayout take 1/3 of the layout's width

                   mChicagoFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                           MATCH_PARENT, 1f));

                   // Make the ChiWebpageLayout take 2/3's of the layout's width
                   mChiWebpageFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                           MATCH_PARENT, 2f));
               }
               else {
                   // Make the ChicagoFragment occupy the entire layout

                   mChicagoFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(
                           0, MATCH_PARENT));

                   mChiWebpageFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(MATCH_PARENT,
                           MATCH_PARENT));
               }

            }

    }

    //Called when the screen orientation changes from landscape to portrait mode
    public void retainPortraitState(){
        if(mChiWebpageFragment.isAdded()){

            mChicagoFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    0, MATCH_PARENT));

            mChiWebpageFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(MATCH_PARENT,
                    MATCH_PARENT));
        }
    }

    //Called when the screen orientation changes from portrait to landscape mode
    public void retainLandscapeState(){
        if(mChiWebpageFragment.isAdded()){

            mChicagoFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    0, MATCH_PARENT,1f));

            mChiWebpageFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                    MATCH_PARENT,2f));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_chi_town, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.


        switch (item.getItemId()){
            case R.id.action_indiana:
                Intent intent = new Intent(this,IndianaTown.class);
                startActivity(intent);
                break;
            default:
                return false;
        }
        return true;

    }

    @Override
    public void onListSelection(int index) {
        // If the QuoteFragment has not been added, add it now
        if (!mChiWebpageFragment.isAdded()) {

            // Start a new FragmentTransaction
            FragmentTransaction fragmentTransaction = mFragmentManager
                    .beginTransaction();

            // Add the ChiWebpageFragment to the layout
            fragmentTransaction.add(R.id.webpage_fragment_container, mChiWebpageFragment);

            // Add this FragmentTransaction to the backstack
            fragmentTransaction.addToBackStack(null);

            // Commit the FragmentTransaction
            fragmentTransaction.commit();

            // Force Android to execute the committed FragmentTransaction
            mFragmentManager.executePendingTransactions();
        }



            // Tell the ChiWebpageFragment to show the webpage at position index

            if(mChiWebpageFragment.getShownIndex()!=index) {
                mChiWebpageFragment.displayWebPage(index);
            }






    }
}



























