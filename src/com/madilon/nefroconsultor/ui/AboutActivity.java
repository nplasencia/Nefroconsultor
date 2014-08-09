package com.madilon.nefroconsultor.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.madilon.nefroconsultor.R;
import com.madilon.nefroconsultor.classes.ActionBarNefroConsultor;
import com.madilon.nefroconsultor.helpers.Typefaces;

public class AboutActivity extends ActionBarNefroConsultor {

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
		noteView.setMovementMethod(LinkMovementMethod.getInstance());
		noteView.setTypeface(Typefaces.SignikaRegular(this));
		
		((TextView) findViewById(R.id.text_advertencias)).setTypeface(Typefaces.SignikaBold(this));
		((TextView) findViewById(R.id.text_advertencias_desc)).setTypeface(Typefaces.SignikaRegular(this));
		
		final Button buttonCondiciones = ((Button) findViewById(R.id.btn_condiciones));
		buttonCondiciones.setTypeface(Typefaces.SignikaBold(this));
		buttonCondiciones.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(AboutActivity.this, CondicionesActivity.class);
				startActivity(intent);
			}
		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.actionbar_acercade, menu);
		return true;
	}
}
