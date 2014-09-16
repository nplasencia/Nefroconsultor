package com.madilon.nefroconsultor.ui;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.madilon.nefroconsultor.R;
import com.madilon.nefroconsultor.adapters.InformacionesItemAdapter;
import com.madilon.nefroconsultor.classes.ActionBarNefroConsultor;
import com.madilon.nefroconsultor.classes.Informacion;
import com.madilon.nefroconsultor.helpers.NefroConsultorHelper;
import com.madilon.nefroconsultor.helpers.Typefaces;

public class AboutActivity extends ActionBarNefroConsultor {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recomendaciones);
		
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		((TextView) findViewById(R.id.cabecera)).setText(R.string.title_activity_about);
		((TextView) findViewById(R.id.cabecera)).setTypeface(Typefaces.SignikaBold(this));
		
		List<Informacion> informaciones = new ArrayList<Informacion>();
		informaciones.add(new Informacion(getString(R.string.title_presentacion), getString(R.string.desc_presentacion), 1, null));
		informaciones.add(new Informacion(getString(R.string.title_descripcion), getString(R.string.desc_descripcion), 2, null));
		informaciones.add(new Informacion(getString(R.string.title_advertencias), getString(R.string.desc_advertencias), 3, null));
		informaciones.add(new Informacion(getString(R.string.title_condicionestotal), getString(R.string.desc_condicionestotal), 4, null));
		informaciones.add(new Informacion(getString(R.string.title_guia), getString(R.string.desc_guia), 5, null));
		informaciones.add(new Informacion(getString(R.string.title_abreviaturas), getString(R.string.desc_abreviaturas), 6, null));
		informaciones.add(new Informacion(getString(R.string.title_comite), getString(R.string.desc_comite), 7, null));
		informaciones.add(new Informacion(getString(R.string.title_bibliografia), getString(R.string.desc_bibliografia), 8, null));
		informaciones.add(new Informacion(getString(R.string.title_contacto), null, 9, NefroConsultorHelper.getEmailIntent(this)));
		informaciones.add(new Informacion(getString(R.string.title_share), null, 10, NefroConsultorHelper.getShareIntent(this)));
		informaciones.add(new Informacion(getString(R.string.title_valorar), null, 11, NefroConsultorHelper.getValorarIntent(this)));
		
		final ListAdapter adapter = new InformacionesItemAdapter(this, informaciones);
		
		ListView list = (ListView) findViewById(R.id.list);
		list.setAdapter(adapter);
		
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Informacion item = (Informacion) adapter.getItem(position);
				if (item.getIntent() != null) {
					startActivity(item.getIntent());
				}
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
