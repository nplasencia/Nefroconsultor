package com.madilon.nefroconsultor.classes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.madilon.nefroconsultor.R;
import com.madilon.nefroconsultor.helpers.NefroConsultorHelper;
import com.madilon.nefroconsultor.ui.AboutActivity;

public class ActionBarNefroConsultor extends ActionBarActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActionBar actionBar = getSupportActionBar();
		actionBar.setIcon(R.drawable.ic_actionbar_logo);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.actionbar_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_documentacion) {
			NefroConsultorHelper.copyOpenAssets(this, "Enfermedad Renal Crónica - Concenso.pdf", "concenso_nefro.pdf");
			return true;
		} else if (id == R.id.action_info) {
			Intent intent = new Intent(this, AboutActivity.class);
			startActivity(intent);
			return true;
		} else if (id == android.R.id.home) {
			onBackPressed();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public void onResume() {
		super.onResume();
	}
}
