package com.madilon.nefroconsultor.ui;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.Menu;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.madilon.nefroconsultor.R;
import com.madilon.nefroconsultor.adapters.RecomendacionesItemAdapter;
import com.madilon.nefroconsultor.classes.ActionBarNefroConsultor;
import com.madilon.nefroconsultor.classes.Recomendacion;
import com.madilon.nefroconsultor.helpers.Typefaces;

public class RecomendacionesActivity extends ActionBarNefroConsultor {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recomendaciones);
		
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		((TextView) findViewById(R.id.cabecera)).setTypeface(Typefaces.SignikaBold(this));
		
		List<Recomendacion> recomendaciones = new ArrayList<Recomendacion>();
		recomendaciones.add(new Recomendacion(getString(R.string.recomendacion1title), getString(R.string.recomendacion1txt), 1));
		recomendaciones.add(new Recomendacion(getString(R.string.recomendacion2title), getString(R.string.recomendacion2txt), 2));
		recomendaciones.add(new Recomendacion(getString(R.string.recomendacion3title), getString(R.string.recomendacion3txt), 3));
		recomendaciones.add(new Recomendacion(getString(R.string.recomendacion4title), getString(R.string.recomendacion4txt), 4));
		recomendaciones.add(new Recomendacion(getString(R.string.recomendacion5title), getString(R.string.recomendacion5txt), 5));
		recomendaciones.add(new Recomendacion(getString(R.string.recomendacion6title), getString(R.string.recomendacion6txt), 6));
		recomendaciones.add(new Recomendacion(getString(R.string.recomendacion7title), getString(R.string.recomendacion7txt), 7));
		recomendaciones.add(new Recomendacion(getString(R.string.recomendacion8title), getString(R.string.recomendacion8txt), 8));
		recomendaciones.add(new Recomendacion(getString(R.string.recomendacion9title), getString(R.string.recomendacion9txt), 9));
		recomendaciones.add(new Recomendacion(getString(R.string.recomendacion10title), getString(R.string.recomendacion10txt), 10));
		
		ListAdapter adapter = new RecomendacionesItemAdapter(this, recomendaciones);
		
		ListView list = (ListView) findViewById(R.id.list);
		list.setAdapter(adapter);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.actionbar_result, menu);
		return true;
	}
}
