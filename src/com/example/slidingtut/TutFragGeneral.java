package com.example.slidingtut;



import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class TutFragGeneral  extends Fragment {
	
	View v;
	
	TextView title;
	TextView description;
	ImageView image;
	
	private int pageNum;
	
	public static TutFragGeneral create(int pageNumber) {
		TutFragGeneral fragment = new TutFragGeneral();
		Bundle args = new Bundle();
        args.putInt("page", pageNumber);
        fragment.setArguments(args);
        return fragment;
    }
	public TutFragGeneral(){
		
	}
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        pageNum = getArguments().getInt("page");
	    }
	 @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		 
	        	v = (ViewGroup) inflater
	               .inflate(R.layout.tutorial_general, container, false);
	        	SetContent(pageNum);
	        	
	        return v;
	    }

	private void SetContent(int pageNum) {
		Init();
		switch (pageNum){
		case 1:
			//Navigation
			title.setText(getText(R.string.navigation_title));
			description.setText(getText(R.string.navigation_description));
			image.setImageResource(R.drawable.screenshot_kurse_overview);
			break;
		case 2:
			//Kurse
			title.setText(getText(R.string.kurse_title));
			description.setText(getText(R.string.kurse_description));
			image.setImageResource(R.drawable.screenshot_kurse_overview);
			break;
			//QuickVP
		case 3:
			title.setText(getText(R.string.quickvp_title));
			description.setText(getText(R.string.quickvp_description));
			image.setImageResource(R.drawable.logo);
			break;
			//Widget
		case 4:
			title.setText(getText(R.string.widget_title));
			description.setText(getText(R.string.widget_description));
			image.setImageResource(R.drawable.screenshot_kurse_overview);
			break;
		}
		
	}
	private void Init() {
		title = (TextView) v.findViewById(R.id.tutGenTitle);
		description = (TextView) v.findViewById(R.id.tutGenDesc);
		image = (ImageView) v.findViewById(R.id.tutGenImage);
	}
}