package com.example.slidingtut;

import android.app.Fragment;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class TutFragDownload extends Fragment implements OnClickListener {

	private View v;

	private TextView title;
	private TextView description;
	private ImageView image;
	private LinearLayout ll;
	private GridView grid;
	private View line1, line2;

	private Boolean[] readersAvailable = new Boolean[15];
	private int numReaders;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		v = inflater.inflate(R.layout.tutorial_download, container, false);

		Init();

		SetContent();

		return v;
	}

	private void SetContent() {
		// TODO Auto-generated method stub
		title.setText(R.string.pdf_title);
		StringBuilder descStr = new StringBuilder();
		descStr.append(getText(R.string.pdf_description));
		descStr.append("\n");
		if (ReaderExists()) {
			// pdf_reader_available_start" --> Du hast schon
			descStr.append(getText(R.string.pdf_reader_available_start));
			descStr.append(AvailableReaders());
			descStr.append(getText(R.string.pdf_reader_available_end));

			SetLineColorRed(false);
			
		} else {
			SetLineColorRed(true);
		}

		description.setText(descStr.toString());
	}

	private void SetLineColorRed(boolean red) {
		int color;
		if (red){
			color = getResources().getColor(R.color.red);
		}
		//else is color should stay blue
		else {
			color = getResources().getColor(R.color.main);
		}
		line1.setBackgroundColor(color);
		line2.setBackgroundColor(color);
	}

	private String AvailableReaders() {
		// For right grammer and commas
		int[] installedReaders = new int[15];
		int filledInstalled = 0;
		for (int i = 0; i < numReaders; i++) {
			if (readersAvailable[i]) {
				installedReaders[filledInstalled] = i;
				filledInstalled++;
				// Log.d("installed", "Slot: " + String.valueOf(filledInstalled)
				// + ", Value: " + String.valueOf(i));
			}
		}
		StringBuilder str = new StringBuilder();
		str.append(" ");
		for (int i = 0; i < filledInstalled; i++) {
			str.append(ReaderNumToName(installedReaders[i]));
			Log.d("readers",
					String.valueOf(i) + " ; " + String.valueOf(filledInstalled));
			if (i == filledInstalled - 1) {
				str.append(" ");
			} else if (i == filledInstalled - 2) {
				str.append(" und ");
			} else {
				str.append(", ");
			}
		}
		return str.toString();

	}

	private String ReaderNumToName(int i) {
		switch (i) {
		case 0:
			return "Adobe Reader";
		case 1:
			return "Quickoffice";
		case 2:
			return "Foobnix PDF Reader";
		case 3:
			return "PDF Viewer";
		case 4:
			return "Foxit Mobile PDF";
		case 5:
			return "Foxit Mobile PDF with RMS";
		case 6:
			return "OfficeSuite 7";
		case 7:
			return "Polaris Office";
		case 8:
			return "Polaris Viewer";
		}
		return null;
	}

	private void Init() {
		title = (TextView) v.findViewById(R.id.tutDownTitle);
		description = (TextView) v.findViewById(R.id.tutDownDesc);
		// image = (ImageView) v.findViewById(R.id.tutGenImage);
		 line1 = (View) v.findViewById(R.id.tutDownSplit1);
		 line2 = (View) v.findViewById(R.id.tutDownSplit2);

		MakeAppIconsClickable();
	}

	private boolean ReaderExists() {
		// TODO Auto-generated method stub
		String[] readers = { "com.adobe.reader",// Adobe Reader
				"com.quickoffice.android", // Quickoffice
				"com.foobnix.pdf.reader",// foobnix PDF Reader
				"the.pdfviewer3", // PDF Viewer
				"com.foxit.mobile.pdf.lite", // Foxit Mobile PDF
				"com.foxit.mobile.pdf.rms", // Foxit Mobile PDF with RMS
				"com.mobisystems.office", // OfficeSuite 7
				"com.infraware.polarisoffice4", // Polaris Office 4.0
				"com.infraware.polarisoffice.entbiz.gd.viewer", // Polaris
																// Office for
																// Good
		};

		numReaders = readers.length;
		Boolean someReaderInstalled = false;
		Log.d("Download", "Array Length: " + String.valueOf(readers.length));
		for (int i = 0; i < readers.length; i++) {
			if (appInstalledOrNot(readers[i])) {
				readersAvailable[i] = true;
				someReaderInstalled = true;
			} else {
				readersAvailable[i] = false;
			}
			Log.d("Readers Installed",
					readers[i] + " is installed: "
							+ String.valueOf(readersAvailable[i]));
		}
		return someReaderInstalled;

	}

	private boolean appInstalledOrNot(String uri) {
		PackageManager pm = getActivity().getPackageManager();
		boolean app_installed = false;
		try {
			pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
			app_installed = true;
		} catch (PackageManager.NameNotFoundException e) {
			app_installed = false;
		}
		return app_installed;
	}

	private void MakeAppIconsClickable() {
		ImageView adobe = (ImageView) v.findViewById(R.id.tutDownImageAdobe);
		ImageView foxit = (ImageView) v.findViewById(R.id.tutDownImageFoxit);
		ImageView quickOffice = (ImageView) v
				.findViewById(R.id.tutDownImageQuickoffice);
		ImageView officeSuite = (ImageView) v
				.findViewById(R.id.tutDownImageOfficeSuite);

		adobe.setOnClickListener(this);
		foxit.setOnClickListener(this);
		quickOffice.setOnClickListener(this);
		officeSuite.setOnClickListener(this);

	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.tutDownImageAdobe:
			OpenInPlayStore("com.adobe.reader");
			break;
		case R.id.tutDownImageQuickoffice:
			OpenInPlayStore("com.quickoffice.android");
			break;
		case R.id.tutDownImageFoxit:
			OpenInPlayStore("com.foxit.mobile.pdf.lite");
			break;
		case R.id.tutDownImageOfficeSuite:
			OpenInPlayStore("com.mobisystems.office");
			break;
		}
	}

	private void OpenInPlayStore(String packageName) {

		try {
			String uriStand = "market://details?id=";
			String totalUri = uriStand + packageName;
			Uri uri = Uri.parse(totalUri);
			Intent i = new Intent(Intent.ACTION_VIEW, uri);
			startActivity(i);
		} catch (android.content.ActivityNotFoundException anfe) {
			// If PlayStore isnt installed
			String playStoreWeb = "http://play.google.com/store/apps/details?id=";
			String totalWebUri = playStoreWeb + packageName;
			Uri uriWeb = Uri.parse(totalWebUri);
			startActivity(new Intent(Intent.ACTION_VIEW, uriWeb));
		}
	}
	
	@Override
	public void onResume() {
		super.onResume();
		SetContent();
	}
}
