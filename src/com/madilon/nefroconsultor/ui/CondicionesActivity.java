package com.madilon.nefroconsultor.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.madilon.nefroconsultor.R;
import com.madilon.nefroconsultor.adapters.InfoAppItemAdapter;
import com.madilon.nefroconsultor.classes.ActionBarNefroConsultor;
import com.madilon.nefroconsultor.classes.InfoApp;
import com.madilon.nefroconsultor.commons.Globals;
import com.madilon.nefroconsultor.helpers.SpannableHelper;
import com.madilon.nefroconsultor.helpers.Typefaces;

public class CondicionesActivity extends ActionBarNefroConsultor {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_general_info);
		
		final CheckBox check = (CheckBox) findViewById(R.id.check);
		final Button buttonAceptar = ((Button) findViewById(R.id.btn_aceptar));
		
		final SharedPreferences userPrefs = PreferenceManager.getDefaultSharedPreferences(this);
		if (userPrefs.getBoolean(Globals.condicionesPreferences, false)){
			getSupportActionBar().setDisplayHomeAsUpEnabled(true);
			check.setVisibility(View.GONE);
			buttonAceptar.setVisibility(View.GONE);
		} else {
			getSupportActionBar().setDisplayHomeAsUpEnabled(false);
		}
		
		((TextView) findViewById(R.id.cabecera)).setText(SpannableHelper.bold(this, getString(R.string.title_condiciones).toUpperCase(Locale.getDefault())));
		check.setText(SpannableHelper.bold(this, getString(R.string.condiciones_check)));
		
		
		buttonAceptar.setTypeface(Typefaces.SignikaBold(this));
		buttonAceptar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (check.isChecked()) {
					userPrefs.edit().putBoolean(Globals.condicionesPreferences, true).commit();
					Intent intent = new Intent(CondicionesActivity.this, MainActivity.class);
					startActivity(intent);
					finish();
				} else {
					Toast.makeText(v.getContext(), SpannableHelper.applyTags(getString(R.string.debes_aceptar),v.getContext()), Toast.LENGTH_LONG).show();
				}
			}
		});
		
		List<InfoApp> info = new ArrayList<InfoApp>();
		info.add(new InfoApp(getString(R.string.title_presentacion), getString(R.string.desc_presentacion)));
		info.add(new InfoApp(getString(R.string.title_advertencias), getString(R.string.desc_advertencias)));
		info.add(new InfoApp(getString(R.string.title_condicionestotal), getString(R.string.desc_condicionestotal)));
		
		ListAdapter adapter = new InfoAppItemAdapter(this, info);
		
		ListView list = (ListView) findViewById(R.id.list);
		list.setAdapter(adapter);
	}
}
