package com.example.slidingtut;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TextView.OnEditorActionListener;

public class FragmentLogin extends Fragment implements OnClickListener {
	private View v;

	private EditText etUsername;
	private EditText etPassword;
	private Button bLogin;
	private LinearLayout llBox;

	private String username;
	private String password;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		v = (ViewGroup) inflater.inflate(R.layout.login, container, false);

		Init();

		return v;
	}

	private void Init() {
		etUsername = (EditText) v.findViewById(R.id.logUsername);
		etPassword = (EditText) v.findViewById(R.id.logPassword);
		bLogin = (Button) v.findViewById(R.id.logBLogin);
		llBox = (LinearLayout) v.findViewById(R.id.logLlBox);

		bLogin.setOnClickListener(this);
		
		SetupEnterClickListener();
	}

	

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.logBLogin:
			username = etUsername.getText().toString();
			password = etPassword.getText().toString();

			boolean fielsEmpty;
			fielsEmpty = CheckIfFieldsEmpty();

			if (!fielsEmpty) {
				LoginMethode();
			}

			break;
		}

	}

	private void LoginMethode() {
		// TODO Auto-generated method stub
		//Put in Network Request
		
		//If Network Request Sucessful
//		Intent i = new Intent(this, MainSelection.class);
		//Token ist von Network 
//		String token;
//		i.putExtra("token", token);
//		startActivity(i);
		
		//If login Failed
		if (false){
			AnimateShake(llBox, false);
		
			//Make toast saying wrong creds
			String toastTxt;
			toastTxt = getString(R.string.login_failed);
			MakeToast(toastTxt);
		}
	}

	private boolean CheckIfFieldsEmpty() {
		Boolean empty = false;
		Boolean pasEmpty, userEmpty;
		userEmpty = IsEmpty(username);
		pasEmpty = IsEmpty(password);

		if (userEmpty && pasEmpty) {
			empty = true;
			AnimateShake(llBox, false);

			String toastTxt;
			toastTxt = getString(R.string.login_both_empty);
			MakeToast(toastTxt);
			// ShakeAll
		}
		if (userEmpty) {
			empty = true;
			String toastTxt;
			toastTxt = getString(R.string.login_username_empty);
			MakeToast(toastTxt);
			// Shake Username
			// AnimateShake(etUsername, false);
		}
		if (pasEmpty) {
			empty = true;
			String toastTxt;
			toastTxt = getString(R.string.login_password_empty);
			MakeToast(toastTxt);
			// Shake Password
			// AnimateShake(etPassword, false);
		}
		return false;
	}

	private void MakeToast(String in) {
		int duration = Toast.LENGTH_SHORT;
		Context c = getActivity();
		Toast t = Toast.makeText(c, in, duration);
		t.show();
	}

	private void AnimateShake(View object, boolean fade) {
		AnimationSet animS = new AnimationSet(true);

		Animation animShake;
		int shakeAnimation;
		shakeAnimation = R.anim.animshake;
		animShake = AnimationUtils.loadAnimation(getActivity(), shakeAnimation);

		animS.addAnimation(animShake);
		object.startAnimation(animS);

	}

	private boolean IsEmpty(String in) {
		if (in.length() == 0)
			return true;
		else
			return false;
	}
	
	private void SetupEnterClickListener() {
		etPassword.setOnEditorActionListener(new OnEditorActionListener() {
			public boolean onEditorAction(TextView v, int actionId,
					KeyEvent event) {
				if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER))
						|| (actionId == EditorInfo.IME_ACTION_DONE)) {
					bLogin.performClick();
				}
				return false;
			}
		});
	}
}
