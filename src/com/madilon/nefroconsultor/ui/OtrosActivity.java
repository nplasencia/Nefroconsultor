package com.madilon.nefroconsultor.ui;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.madilon.nefroconsultor.R;
import com.madilon.nefroconsultor.adapters.OtrosMotivosItemAdapter;
import com.madilon.nefroconsultor.classes.ActionBarNefroConsultor;
import com.madilon.nefroconsultor.classes.OtroMotivo;
import com.madilon.nefroconsultor.commons.Globals;
import com.madilon.nefroconsultor.enums.SexoEnum;
import com.madilon.nefroconsultor.helpers.Typefaces;

public class OtrosActivity extends ActionBarNefroConsultor {

	private String edad;
	private SexoEnum sexo;
	private String creatinina;
	private String albuminuria;
	private Boolean razaNegra;
	private ArrayList<OtroMotivo> otrosMotivos;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_otros);
		
		edad = getIntent().getStringExtra(Globals.edadIntent);
		sexo = (SexoEnum) getIntent().getSerializableExtra(Globals.sexoIntent);
		creatinina = getIntent().getStringExtra(Globals.creaIntent);
		albuminuria = getIntent().getStringExtra(Globals.albuIntent);
		razaNegra = getIntent().getBooleanExtra(Globals.razaIntent, false);
		
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		((TextView) findViewById(R.id.cabecera)).setTypeface(Typefaces.SignikaBold(this));
		
		if (getIntent().hasExtra(Globals.otrosIntent)){
			otrosMotivos = getIntent().getParcelableArrayListExtra(Globals.otrosIntent);
		} else {
			otrosMotivos = new ArrayList<OtroMotivo>(7);
			otrosMotivos.add(new OtroMotivo(getString(R.string.tituloMotivo1), getString(R.string.explicacionMotivo1), 1, false));
			otrosMotivos.add(new OtroMotivo(getString(R.string.tituloMotivo2), getString(R.string.explicacionMotivo2), 2, false));
			otrosMotivos.add(new OtroMotivo(getString(R.string.tituloMotivo3), getString(R.string.explicacionMotivo3), 3, false));
			otrosMotivos.add(new OtroMotivo(getString(R.string.tituloMotivo4), getString(R.string.explicacionMotivo4), 4, false));
			otrosMotivos.add(new OtroMotivo(getString(R.string.tituloMotivo5), getString(R.string.explicacionMotivo5), 5, false));
			otrosMotivos.add(new OtroMotivo(getString(R.string.tituloMotivo6), getString(R.string.explicacionMotivo6), 6, false));
			otrosMotivos.add(new OtroMotivo(getString(R.string.tituloMotivoOtros), getString(R.string.explicacionMotivoOtros), 7, false));
		}
		ListAdapter adapter = new OtrosMotivosItemAdapter(this,otrosMotivos);
		
		ListView list = (ListView) findViewById(R.id.list);
		list.setAdapter(adapter);
		
		final Button buttonCalcular = ((Button) findViewById(R.id.btn_calcular));
		buttonCalcular.setTypeface(Typefaces.SignikaBold(this));
		buttonCalcular.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(OtrosActivity.this, ResultActivity.class);
				intent.putExtra(Globals.sexoIntent, sexo);
				intent.putExtra(Globals.edadIntent, edad);
				intent.putExtra(Globals.albuIntent, albuminuria);
				intent.putExtra(Globals.creaIntent, creatinina);
				intent.putExtra(Globals.razaIntent, razaNegra);
				intent.putParcelableArrayListExtra(Globals.otrosIntent, otrosMotivos);
				startActivity(intent);
				finish();
			}
		});
	}
	
	@Override
	public void onBackPressed(){
		Intent intent = new Intent(OtrosActivity.this, MainActivity.class);
		intent.putExtra(Globals.sexoIntent, sexo);
		intent.putExtra(Globals.edadIntent, edad);
		intent.putExtra(Globals.albuIntent, albuminuria);
		intent.putExtra(Globals.creaIntent, creatinina);
		intent.putExtra(Globals.razaIntent, razaNegra);
		intent.putParcelableArrayListExtra(Globals.otrosIntent, otrosMotivos);
		startActivity(intent);
		finish();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.actionbar_result, menu);
		return true;
	}
}
