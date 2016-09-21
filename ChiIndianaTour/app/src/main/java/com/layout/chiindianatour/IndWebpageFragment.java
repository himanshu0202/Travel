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

public class IndWebpageFragment extends Fragment {

    private static final String TAG = "IndWebpageFragment";
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
        wv.setWebViewClient(new WebViewClient());
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
        return inflater.inflate(R.layout.fragment_ind_webpage, container, false);

    }

    // Set up some information about the WbView
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.i(TAG, getClass().getSimpleName() + ":entered onActivityCreated()");
        super.onActivityCreated(savedInstanceState);
        initializeWebpageUris();

        wv =(WebView) getActivity().findViewById(R.id.webView);
        mTitleArrayLen = IndianaTown.mInterestArray.length;
    }

    //Load the Webpage Urls
    public void initializeWebpageUris() {

        mUriList.add(Uri.parse(getString(R.string.IMS_URI)));
        mUriList.add(Uri.parse(getString(R.string.WRSP_URI)));
        mUriList.add(Uri.parse(getString(R.string.VICTORY_URI)));
        mUriList.add(Uri.parse(getString(R.string.INDIANAZOO_URI)));
        mUriList.add(Uri.parse(getString(R.string.NCAA_URI)));
        mUriList.add(Uri.parse(getString(R.string.MUSEUM_URI)));
        mUriList.add(Uri.parse(getString(R.string.INDSTATEMUSEUM_URI)));

    }
}