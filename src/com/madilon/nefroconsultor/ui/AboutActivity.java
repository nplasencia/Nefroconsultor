package com.madilon.nefroconsultor.ui;

import android.os.Bundle;
import android.widget.TextView;

import com.madilon.nefroconsultor.R;
import com.madilon.nefroconsultor.classes.ActionBarAcercaDeNefroConsultor;
import com.madilon.nefroconsultor.helpers.Typefaces;

public class AboutActivity extends ActionBarAcercaDeNefroConsultor {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);
		
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		((TextView) findViewById(R.id.cabecera)).setTypeface(Typefaces.SignikaBold(this));
		
		((TextView) findViewById(R.id.text_presentacion)).setTypeface(Typefaces.SignikaBold(this));
		((TextView) findViewById(R.id.text_presentacion_desc)).setTypeface(Typefaces.SignikaRegular(this));
		
		((TextView) findViewById(R.id.text_descripcion)).setTypeface(Typefaces.SignikaBold(this));
		TextView noteView = ((TextView) findViewById(R.id.text_descripcion_desc));
		noteView.setTypeface(Typefaces.SignikaRegular(this));
		
		((TextView) findViewById(R.id.text_advertencias)).setTypeface(Typefaces.SignikaBold(this));
		((TextView) findViewById(R.id.text_advertencias_desc)).setTypeface(Typefaces.SignikaRegular(this));
	}
}
