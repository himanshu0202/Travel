package com.layout.chiindianatour;

import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.ArrayList;

public class ChiWebpageFragment extends Fragment {


    private static final String TAG = "ChiWebpageFragment";
    public static ArrayList<Uri> mUriList = new ArrayList<>();
    private WebView wv = null;
    private int mCurrIdx = -1;
    private int mTitleArrayLen;

    public int getShownIndex(){
        return mCurrIdx;
    }

    public void displayWebPage(int newIndex) {
        if (newIndex < 0 || newIndex >= mTitleArrayLen)
            return;
        mCurrIdx = newIndex;
        Uri aUri = mUriList.get(mCurrIdx);
        //set up the WebViewClient
        wv.setWebViewClient(new WebViewClient());
        //Load the url corresponding to the list item clicked
        wv.loadUrl(aUri.toString());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, getClass().getSimpleName() + ":entered onCreate()");
        super.onCreate(savedInstanceState);

        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chi_webpage, container, false);

    }

    // Set up some information about the WebView
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.i(TAG, getClass().getSimpleName() + ":entered onActivityCreated()");
        super.onActivityCreated(savedInstanceState);
        initializeWebpageUris();

        wv =(WebView) getActivity().findViewById(R.id.webView);
        mTitleArrayLen = ChiTown.mInterestArray.length;
    }

    //method to initializa the web urls
    public void initializeWebpageUris() {

        mUriList.add(Uri.parse(getString(R.string.MilleniumPark_URI)));
        mUriList.add(Uri.parse(getString(R.string.NavyPier_URI)));
        mUriList.add(Uri.parse(getString(R.string.LincolnPark_URI)));
        mUriList.add(Uri.parse(getString(R.string.WillisTower_URI)));
        mUriList.add(Uri.parse(getString(R.string.JHCenter_URI)));
        mUriList.add(Uri.parse(getString(R.string.MSNI_URI)));
        mUriList.add(Uri.parse(getString(R.string.Shedd_URI)));
        mUriList.add(Uri.parse(getString(R.string.AIT_URI)));
        mUriList.add(Uri.parse(getString(R.string.Adler_URI)));
        mUriList.add(Uri.parse(getString(R.string.Wrigley_URI)));
        mUriList.add(Uri.parse(getString(R.string.Grant_URI)));
        mUriList.add(Uri.parse(getString(R.string.Buckingham_URI)));

    }
}
