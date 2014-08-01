package com.madilon.nefroconsultor.ui;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.madilon.nefroconsultor.R;
import com.madilon.nefroconsultor.classes.ActionBarNefroConsultor;
import com.madilon.nefroconsultor.classes.OtroMotivo;
import com.madilon.nefroconsultor.commons.Globals;
import com.madilon.nefroconsultor.enums.SexoEnum;
import com.madilon.nefroconsultor.helpers.Typefaces;

public class MainActivity extends ActionBarNefroConsultor {
	
	private SexoEnum sexo;
	private EditText edad;
	private EditText albuminuria;
	private EditText creatinina;
	private CheckBox razaNegra;
	private ArrayList<OtroMotivo> otrosMotivos;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		((TextView) findViewById(R.id.text_edad)).setTypeface(Typefaces.SignikaBold(this));
		((TextView) findViewById(R.id.text_units_edad)).setTypeface(Typefaces.SignikaRegular(this));
		((TextView) findViewById(R.id.text_sexo)).setTypeface(Typefaces.SignikaBold(this));
		((TextView) findViewById(R.id.text_albuminuria)).setTypeface(Typefaces.SignikaBold(this));
		((TextView) findViewById(R.id.text_albuminuria_exp)).setTypeface(Typefaces.SignikaRegular(this));
		((TextView) findViewById(R.id.text_units_albuminuria)).setTypeface(Typefaces.SignikaRegular(this));
		((TextView) findViewById(R.id.text_creatinina)).setTypeface(Typefaces.SignikaBold(this));
		((TextView) findViewById(R.id.text_units_creatinina)).setTypeface(Typefaces.SignikaRegular(this));
		((TextView) findViewById(R.id.text_raza)).setTypeface(Typefaces.SignikaBold(this));
		((TextView) findViewById(R.id.text_otros)).setTypeface(Typefaces.SignikaBold(this));
		
		final Button sexoMasculino = (Button) findViewById(R.id.btn_masculino);
		final Button sexoFemenino = (Button) findViewById(R.id.btn_femenino);
		sexoMasculino.setOnClickListener(activado(SexoEnum.MASCULINO));
		sexoFemenino.setOnClickListener(activado(SexoEnum.FEMENINO));
		
		razaNegra = (CheckBox) findViewById(R.id.check_raza);
		edad = (EditText) findViewById(R.id.edit_edad);
		albuminuria = (EditText) findViewById(R.id.edit_albuminuria);
		creatinina = (EditText) findViewById(R.id.edit_creatinina);
		
		if (getIntent().getExtras() != null) {
			otrosMotivos = getIntent().getExtras().getParcelableArrayList(Globals.otrosIntent);
			edad.setText(getIntent().getStringExtra(Globals.edadIntent));
			sexo = (SexoEnum) getIntent().getSerializableExtra(Globals.sexoIntent);
			if (sexo.equals(SexoEnum.FEMENINO)) {
				sexoFemenino.performClick();
			} else {
				sexoMasculino.performClick();
			}
			creatinina.setText(getIntent().getStringExtra(Globals.creaIntent));
			albuminuria.setText(getIntent().getStringExtra(Globals.albuIntent));
			razaNegra.setChecked(getIntent().getBooleanExtra(Globals.razaIntent, false));
		}
		
		final Button buttonCalcular = ((Button) findViewById(R.id.btn_calcular));
		buttonCalcular.setTypeface(Typefaces.SignikaBold(this));
		buttonCalcular.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (comprobarValores(v.getContext())){
					Intent intent = new Intent(MainActivity.this, ResultActivity.class);
					intent.putExtra(Globals.sexoIntent, sexo);
					intent.putExtra(Globals.edadIntent, edad.getText().toString());
					intent.putExtra(Globals.albuIntent, albuminuria.getText().toString());
					intent.putExtra(Globals.creaIntent, creatinina.getText().toString());
					intent.putExtra(Globals.razaIntent, razaNegra.isChecked());
					if (otrosMotivos != null) intent.putParcelableArrayListExtra(Globals.otrosIntent, otrosMotivos);
					startActivity(intent);
					finish();
				}
			}
		});
		
		final Button buttonOtrosMotivos = ((Button) findViewById(R.id.btn_otros));
		buttonOtrosMotivos.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (comprobarValores(v.getContext())){
					Intent intent = new Intent(MainActivity.this, OtrosActivity.class);
					intent.putExtra(Globals.sexoIntent, sexo);
					intent.putExtra(Globals.edadIntent, edad.getText().toString());
					intent.putExtra(Globals.albuIntent, albuminuria.getText().toString());
					intent.putExtra(Globals.creaIntent, creatinina.getText().toString());
					intent.putExtra(Globals.razaIntent, razaNegra.isChecked());
					if (otrosMotivos != null) intent.putParcelableArrayListExtra(Globals.otrosIntent, otrosMotivos);
					startActivity(intent);
					finish();
				}
			}
		});
	}
	
	private OnClickListener activado(SexoEnum sexoSeleccionado) {
		final Button sexoMasculino = (Button) findViewById(R.id.btn_masculino);
		final Button sexoFemenino = (Button) findViewById(R.id.btn_femenino);
		
		if (sexoSeleccionado.equals(SexoEnum.MASCULINO)) {
			return new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					sexoMasculino.setCompoundDrawablesWithIntrinsicBounds(R.drawable.btn_masculino_act, 0, 0, 0);
					sexoFemenino.setCompoundDrawablesWithIntrinsicBounds(R.drawable.btn_femenino_desact, 0, 0, 0);
					sexo = SexoEnum.MASCULINO;
					Log.d(this.toString(), "Se ha seleccionado el sexo masculino");
				}
			};
		} else {
			return new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					sexoMasculino.setCompoundDrawablesWithIntrinsicBounds(R.drawable.btn_masculino_desact, 0, 0, 0);
					sexoFemenino.setCompoundDrawablesWithIntrinsicBounds(R.drawable.btn_femenino_act, 0, 0, 0);
					sexo = SexoEnum.FEMENINO;
					Log.d(this.toString(), "Se ha seleccionado el sexo femenino");
				}
			};
		}
	}
	
	private boolean comprobarValores(Context context) {
		if (edad.getText().toString().equals("0")||edad.getText().toString().equals("")) {
			Toast.makeText(context, "El valor Edad es incorrecto", Toast.LENGTH_SHORT).show();
		} else if (sexo == null){
			Toast.makeText(context, "Debe seleccionar un Sexo para el paciente", Toast.LENGTH_SHORT).show();
		} else if (creatinina.getText().toString().equals("0")||creatinina.getText().toString().equals("")) {
			Toast.makeText(context, "El valor Creatina es incorrecto", Toast.LENGTH_SHORT).show();
		} else if (albuminuria.getText().toString().equals("0")||albuminuria.getText().toString().equals("")) {
			Toast.makeText(context, "El valor Albuminuria es incorrecto", Toast.LENGTH_SHORT).show();
		} else {
			return true;
		}
		return false;
	}
	
	@Override
	public void onBackPressed(){
		finish();
	}
}