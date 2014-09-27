package com.madilon.nefroconsultor.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TextView.BufferType;

import com.madilon.nefroconsultor.R;
import com.madilon.nefroconsultor.classes.ActionBarNefroConsultor;
import com.madilon.nefroconsultor.commons.Globals;
import com.madilon.nefroconsultor.enums.AlbuminuriaEnum;
import com.madilon.nefroconsultor.enums.EstadioEnum;
import com.madilon.nefroconsultor.enums.FgeEnum;
import com.madilon.nefroconsultor.enums.SexoEnum;
import com.madilon.nefroconsultor.helpers.SpannableHelper;
import com.madilon.nefroconsultor.helpers.Typefaces;

public class ExplainResultActivity extends ActionBarNefroConsultor {
	
	FgeEnum fgeEstadio;
	AlbuminuriaEnum albuEstadio;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_explain);
		
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		((TextView) findViewById(R.id.cabecera)).setTypeface(Typefaces.SignikaBold(this));
		
		Integer edad = Integer.parseInt(getIntent().getStringExtra(Globals.edadIntent));
		SexoEnum sexo = (SexoEnum) getIntent().getSerializableExtra(Globals.sexoIntent);
		//Double creatinina = Double.parseDouble(getIntent().getStringExtra(Globals.creaIntent));
		Double albuminuria = Double.parseDouble(getIntent().getStringExtra(Globals.albuIntent));
		//boolean razaNegra = getIntent().getBooleanExtra(Globals.razaIntent, false);
		Double mdrdIdms = Double.parseDouble(getIntent().getStringExtra(Globals.mdrdIdmsIntent));
		Double cdkEpi = Double.parseDouble(getIntent().getStringExtra(Globals.cdkEpiIntent));
		fgeEstadio = (FgeEnum) getIntent().getSerializableExtra(Globals.fgEstadioIntent);
		albuEstadio = (AlbuminuriaEnum) getIntent().getSerializableExtra(Globals.albuEstadioIntent);
		String resultado = getIntent().getStringExtra(Globals.resultadoTextIntent);
		
		String text = String.format(getString(R.string.desc_explain), 
				getString(sexo.getStringText()), edad, String.format(getString(R.string.doubleFormat), mdrdIdms), String.format(getString(R.string.doubleFormat), cdkEpi), 
				String.format(getString(R.string.doubleFormat), albuminuria), fgeEstadio.getDescription(),
				fgeEstadio.getRange(), albuEstadio.getDescription(), albuEstadio.getLimitDesc(), resultado);
		
		((TextView) findViewById(R.id.text_explanation)).setTypeface(Typefaces.SignikaRegular(this));
		((TextView) findViewById(R.id.text_explanation)).setText(SpannableHelper.applyTags(text, this), BufferType.SPANNABLE);
		
		((ImageView) findViewById(R.id.img_table)).setImageResource(EstadioEnum.getFromAlbuminuriaFgEnum(albuEstadio, fgeEstadio).getEstadio().getTabla());
		((ImageView) findViewById(R.id.img_table)).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ExplainResultActivity.this, TableActivity.class);
				intent.putExtra(Globals.albuEstadioIntent, albuEstadio);
				intent.putExtra(Globals.fgEstadioIntent, fgeEstadio);
				startActivity(intent);
			}
		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.actionbar_result, menu);
		return true;
	}
}
