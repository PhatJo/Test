package com.example.kiptoomagutt.testas;



import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class ScreenSlidePageFragment extends Fragment {

    private static final String TAG = "ScreenSliderPageFragment";
    public static final String ARG_OBJECT = "object";

    public ScreenSlidePageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_screen_slide_page, container, false);
        Bundle args = getArguments();
        int pos = args.getInt(ARG_OBJECT);
        ((TextView) rootView.findViewById(R.id.text1)).setText(
                Integer.toString(pos));

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }
}
