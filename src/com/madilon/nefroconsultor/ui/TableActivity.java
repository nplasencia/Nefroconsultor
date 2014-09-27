package com.madilon.nefroconsultor.ui;

import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageView;

import com.madilon.nefroconsultor.R;
import com.madilon.nefroconsultor.classes.ActionBarNefroConsultor;
import com.madilon.nefroconsultor.commons.Globals;
import com.madilon.nefroconsultor.enums.AlbuminuriaEnum;
import com.madilon.nefroconsultor.enums.EstadioEnum;
import com.madilon.nefroconsultor.enums.FgeEnum;

public class TableActivity extends ActionBarNefroConsultor {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_table);
		
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		FgeEnum fgeEstadio = (FgeEnum) getIntent().getSerializableExtra(Globals.fgEstadioIntent);
		AlbuminuriaEnum albuEstadio = (AlbuminuriaEnum) getIntent().getSerializableExtra(Globals.albuEstadioIntent);
		
		((ImageView) findViewById(R.id.img_table)).setImageResource(EstadioEnum.getFromAlbuminuriaFgEnum(albuEstadio, fgeEstadio).getEstadio().getTabla());
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.actionbar_result, menu);
		return true;
	}
}
