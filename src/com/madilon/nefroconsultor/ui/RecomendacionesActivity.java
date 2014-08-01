package com.madilon.nefroconsultor.ui;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
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
		recomendaciones.add(new Recomendacion("Revisar hábitos higiénico-dietéticos. Actitudes y estilo de vida", "Deterioro agudo en la función renal (caída del FGe > 25 %) en menos de un mes " +
				"o un incremento de la creatinina sérica >25% en menos de 1 mes descartados factores exógenos (diarrea, vómitos, depleción por diuréticos en " +
				"tratamiento con IECAs, ARA II o inhibidores directos de la renina).",null, 1));
		ListAdapter adapter = new RecomendacionesItemAdapter(this, recomendaciones);
		
		ListView list = (ListView) findViewById(R.id.list);
		list.setAdapter(adapter);
	}
}
