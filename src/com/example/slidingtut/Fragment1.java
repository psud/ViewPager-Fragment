package com.example.slidingtut;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class Fragment1 extends Fragment implements OnClickListener{
	View v;
	Button login, next;
	TutorialActivity tutAct;
	
	 @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	        v = inflater.inflate(R.layout.tutorial_start, container, false);
	        
	        Init();

	        return v;
	    }
	private void Init() {
		tutAct = new TutorialActivity();
		
		
		next = (Button) v.findViewById(R.id.tutStartButNext);
		login = (Button) v.findViewById(R.id.tutStartButLogin);
		
		next.setOnClickListener(this);
		login.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()){
		case R.id.tutStartButNext:
			((TutorialActivity)getActivity()).setNextPage();
			break;
		case R.id.tutStartButLogin:
			((TutorialActivity)getActivity()).setLastPage();
			
			break;
		}
	}
}
