package com.example.slidingtut;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class TutorialFragment extends Fragment {
    /**
     * The argument key for the page number this fragment represents.
     */
    public static final String ARG_PAGE = "page";

    /**
     * The fragment's page number, which is set to the argument value for {@link #ARG_PAGE}.
     */
    private int mPageNumber;

    /**
     * Factory method for this fragment class. Constructs a new fragment for the given page number.
     */
    public static TutorialFragment create(int pageNumber) {
        TutorialFragment fragment = new TutorialFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, pageNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public TutorialFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPageNumber = getArguments().getInt(ARG_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	 
  
    	// Inflate the layout containing a title and body text.
    	ViewGroup rootView = null;
    	switch (mPageNumber){
    	case 0:
    		  rootView = (ViewGroup) inflater
             .inflate(R.layout.tutorial_start, container, false);
    		  
    		  
    		break;
    	case 1:
    		  rootView = (ViewGroup) inflater
             .inflate(R.layout.tutorial_navigation, container, false);
    		break;
    		
    		default:
    			 rootView = (ViewGroup) inflater
	             .inflate(R.layout.tutorial_einstellungen, container, false);
    			 
    			 //Button b = (Button) rootView.findViewById(R.id.buttonFrag);
//    			 b.setOnClickListener(new View.OnClickListener() {
//					
//					@Override
//					public void onClick(View v) {
//						// TODO Auto-generated method stub
//					Toast.makeText(getActivity(), "Clicked", Toast.LENGTH_SHORT).show();	
//					}
//				});
    	}
    
       

//        // Set the title view to show the page number.
//        ((TextView) rootView.findViewById(android.R.id.text1)).setText(
//                getString(R.string.title_template_step, mPageNumber + 1)+" PAT");

        return rootView;
    }

    /**
     * Returns the page number represented by this fragment object.
     */
    public int getPageNumber() {
        return mPageNumber;
    }
}
