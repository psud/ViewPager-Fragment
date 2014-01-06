package com.example.slidingtut;

import android.app.Fragment;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class TutFragDownload extends Fragment {

	private View v;

	private TextView title;
	private TextView description;
	private ImageView image;
	private LinearLayout ll;
	private GridView grid;

	private Boolean[] readersAvailable = new Boolean[15];
	private int numReaders;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		v = inflater.inflate(R.layout.tutorial_download, container, false);

		Init();
		if (ReaderExists()) {
			// Write Du hast einen PDF reader
			Toast.makeText(getActivity(), "PDF Reader Availabel",
					Toast.LENGTH_SHORT).show();
		} else {
			// Write out du hast keinen PDF reader und solltest einen vom play
			// store runterladen.
			// Give choice of PDF Readers
			Toast.makeText(getActivity(), "KEIN PDF Reader", Toast.LENGTH_SHORT)
					.show();
		}

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

		} else {

		}

		description.setText(descStr.toString());
	}

	private String AvailableReaders() {
		//For right grammer and commas
		int[] installedReaders = new int[15];
		int filledInstalled = 0;
		for (int i = 0; i < numReaders; i++) {
			if (readersAvailable[i]) {
				installedReaders[filledInstalled]= i;
				filledInstalled++;
				Log.d("installed", "Slot: " + String.valueOf(filledInstalled) + ", Value: " + String.valueOf(i));
			}
		}
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < filledInstalled; i++){
			str.append(ReaderNumToName(installedReaders[i]));
			if (i == filledInstalled){
				str.append(" ");
			}
				else if (i == filledInstalled-1){
					str.append(" und ");
				}
				else {
					str.append(",");
			}
		}
		str.append(" ");
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
		}
		return null;
	}

	private void Init() {
		title = (TextView) v.findViewById(R.id.tutDownTitle);
		description = (TextView) v.findViewById(R.id.tutDownDesc);
		// image = (ImageView) v.findViewById(R.id.tutGenImage);
	}

	private boolean ReaderExists() {
		// TODO Auto-generated method stub
		String[] readers = { "com.adobe.reader",// Adobe Reader
				"com.quickoffice.android", // Quickoffice
				"com.foobnix.pdf.reader"// foobnix PDF Reader
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
}
