package com.example.crazywah.interviewdemo.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentLifeCycyle extends Fragment {

    private static final String TAG = "FragmentLifeCycyle";

    /**
     * 在创建之前先依附到Activity上
     * @param context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG, "onAttach: executed");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: executed");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: executed");
        View view = new View(container.getContext());
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "onActivityCreated: executed");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: executed");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: executed");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: executed");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState: executed");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: executed");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView: executed");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: executed");
    }
}
