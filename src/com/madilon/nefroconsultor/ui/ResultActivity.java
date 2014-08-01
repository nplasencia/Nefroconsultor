package com.madilon.nefroconsultor.ui;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.madilon.nefroconsultor.R;
import com.madilon.nefroconsultor.classes.ActionBarNefroConsultor;
import com.madilon.nefroconsultor.classes.OtroMotivo;
import com.madilon.nefroconsultor.commons.Globals;
import com.madilon.nefroconsultor.enums.SexoEnum;
import com.madilon.nefroconsultor.helpers.Typefaces;

public class ResultActivity extends ActionBarNefroConsultor {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);
		
		Integer edad = Integer.parseInt(getIntent().getStringExtra(Globals.edadIntent));
		SexoEnum sexo = (SexoEnum) getIntent().getSerializableExtra(Globals.sexoIntent);
		Double creatinina = Double.parseDouble(getIntent().getStringExtra(Globals.creaIntent));
		Double albuminuria = Double.parseDouble(getIntent().getStringExtra(Globals.albuIntent));
		Boolean razaNegra = getIntent().getBooleanExtra(Globals.razaIntent, false);
		
		Boolean otros = false;
		if (getIntent().hasExtra(Globals.otrosIntent)){
			ArrayList<OtroMotivo> otrosMotivos = getIntent().getParcelableArrayListExtra(Globals.otrosIntent);
			for(OtroMotivo otroMotivo : otrosMotivos) {
				if (otroMotivo.isChecked()) {
					otros = true;
					break;
				}
			}
		}
		
		Double cdkEpi = obtenerCKDEPI(edad, sexo, creatinina, albuminuria, razaNegra);
		Double mdrdIms = obtenerMDRDIDMS(edad, sexo, creatinina, albuminuria, razaNegra);
		
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		((TextView) findViewById(R.id.cabecera1)).setTypeface(Typefaces.SignikaBold(this));
		((TextView) findViewById(R.id.text_fgeckdepi_units)).setTypeface(Typefaces.SignikaRegular(this));
		((TextView) findViewById(R.id.text_mdrd)).setTypeface(Typefaces.SignikaBold(this));
		((TextView) findViewById(R.id.text_mdrd_units)).setTypeface(Typefaces.SignikaRegular(this));
		((TextView) findViewById(R.id.text_clasificacion)).setTypeface(Typefaces.SignikaBold(this));
		
		TextView fgecdkepi = (TextView) findViewById(R.id.text_fgeckdepi);
		fgecdkepi.setTypeface(Typefaces.SignikaBold(this));
		fgecdkepi.setText(String.format(getString(R.string.fgecdk), cdkEpi));
		
		TextView mdrd = (TextView) findViewById(R.id.text_mdrd);
		mdrd.setTypeface(Typefaces.SignikaBold(this));
		mdrd.setText(String.format(getString(R.string.mdrd), mdrdIms));
		
		TextView result = (TextView) findViewById(R.id.text_resultado);
		result.setTypeface(Typefaces.SignikaLight(this));
		
		TextView recomendacionesAst = (TextView) findViewById(R.id.text_recomendaciones_asterisco);
		recomendacionesAst.setTypeface(Typefaces.SignikaRegular(this));
		
		((TextView) findViewById(R.id.cabecera2)).setTypeface(Typefaces.SignikaBold(this));
		
		TextView recomendacionesTitle = (TextView) findViewById(R.id.text_recomendaciones_title);
		recomendacionesTitle.setTypeface(Typefaces.SignikaBold(this));
		
		TextView recomendacionesSubtitle = (TextView) findViewById(R.id.text_recomendaciones_subtitle);
		recomendacionesSubtitle.setTypeface(Typefaces.SignikaRegular(this));
		
		TextView recomendacionesDesc = (TextView) findViewById(R.id.text_recomendaciones_descripcion);
		recomendacionesDesc.setTypeface(Typefaces.SignikaRegular(this));
		
		String albuminuriaEstadio = albuminuriaEstadios(albuminuria);
		String fgEstadio = fgEstadios(mdrdIms);
		
		if ((fgEstadio.equals("G1") || fgEstadio.equals("G2")) && albuminuriaEstadio.equals("A1")) {
			result.setText(getString(R.string.resultAst) + " " + fgEstadio +" "+albuminuriaEstadio);
			recomendacionesAst.setText(R.string.text_recomendaciones_asterisco);
		} else {
			result.setText(getString(R.string.result) + " " + fgEstadio + " " +albuminuriaEstadio);
		}
		
		if (albuminuriaEstadio.equals("A2") && (!fgEstadio.equals("G4") && !fgEstadio.equals("G5"))) {
			TextView textCasoA2 = (TextView) findViewById(R.id.text_recomendaciones_casoA2);
			textCasoA2.setTypeface(Typefaces.SignikaRegular(this));
			textCasoA2.setVisibility(View.VISIBLE);
			TextView textCasoA2Elena = (TextView) findViewById(R.id.text_recomendaciones_casoA2_elena);
			textCasoA2Elena.setTypeface(Typefaces.SignikaLight(this));
			textCasoA2Elena.setVisibility(View.VISIBLE);
		}
		
		cambiarFondoResult(result, fgEstadio, albuminuriaEstadio, albuminuria);
		
		if ((edad <= 80 && mdrdIms <= 30) || (edad > 80 && mdrdIms <=20 )) {
			recomendacionesTitle.setText(R.string.remitirCaso1);
		} else if (albuminuria >= 300) {
			recomendacionesTitle.setText(R.string.remitirCaso2);
		} else if (otros){
			recomendacionesTitle.setText(R.string.remitir);
		} else if (edad > 80 && (mdrdIms >20 && mdrdIms <30)) {
			recomendacionesTitle.setText(R.string.noRemitir);
			recomendacionesSubtitle.setText(R.string.noRemitirCaso1Subtitle);
			recomendacionesDesc.setText(R.string.noRemitirCaso1Desc);
		} else if (edad <=80 && (mdrdIms >30 && mdrdIms <45)) {
			recomendacionesTitle.setText(R.string.noRemitir);
			recomendacionesSubtitle.setText(R.string.noRemitirCaso2Subtitle);
			recomendacionesDesc.setText(R.string.noRemitirCaso2Desc);
		} else if (albuminuria >=30 && albuminuria < 299) {
			recomendacionesTitle.setText(R.string.noRemitir);
			recomendacionesSubtitle.setText(R.string.noRemitirCaso3Subtitle);
		} else if (albuminuria < 30) {
			recomendacionesTitle.setText(R.string.noRemitir);
			recomendacionesSubtitle.setText(R.string.noRemitirCaso4Subtitle);
		}
		
		final Button buttonRecomendaciones = ((Button) findViewById(R.id.btn_recomendaciones));
		buttonRecomendaciones.setTypeface(Typefaces.SignikaBold(this));
		buttonRecomendaciones.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(v.getContext(), "Vamos a ver las recomendaciones", Toast.LENGTH_SHORT).show();
			}
		});
	}
	
	private static double obtenerCKDEPI(Integer edad, SexoEnum sexo, Double creatinina, Double albuminuria, Boolean razaNegra) {
		int constanteInicial;
		double constanteElevado = -1.209d;
		double constanteDivisor;
		
		if (sexo.equals(SexoEnum.FEMENINO)) {
			constanteDivisor = 0.7f;
			
			if (!razaNegra) {
				constanteInicial = 144;
			} else {
				constanteInicial = 166;
			}
			
			if (creatinina <= 0.7) {
				constanteElevado = -0.329f;
			}
			
		} else {
			constanteDivisor = 0.9f;
			if (!razaNegra) {
				constanteInicial = 141;
			} else {
				constanteInicial = 163;
			}
			
			if (creatinina <= 0.7) {
				constanteElevado = -0.411f;
			}
		}
		
		double resultado = constanteInicial * Math.pow((creatinina/constanteDivisor),constanteElevado) * Math.pow(0.993,edad);
		Log.d("CDK-EPI", "El resultado es " + resultado);
		
		return resultado;
	}
	
	private static double obtenerMDRDIDMS(Integer edad, SexoEnum sexo, Double creatinina, Double albuminuria, Boolean razaNegra) {
		double resultado = 175*Math.pow(creatinina,-1.154)*Math.pow(edad, -0.203);
		if (sexo.equals(SexoEnum.FEMENINO)) {
			resultado = resultado * 0.742;
		}
		
		if (razaNegra) {
			resultado = resultado * 1.21;
		}
		Log.d("MDRD-IDMS", "El resultado es " + resultado);
		
		return resultado;
	}
	
	public static String albuminuriaEstadios(Double albuminuria) {
		if (albuminuria < 30) {
			return "A1";
		} else if (albuminuria >=30 && albuminuria < 300) {
			return "A2";
		}
		return "A3";
	}
	
	public static String fgEstadios(double resultadoMdrd) {
		if (resultadoMdrd >=90 ) {
			return "G1"; 
		} else if (resultadoMdrd < 90 && resultadoMdrd >= 60) {
			return "G2";
		} else if (resultadoMdrd < 60 && resultadoMdrd >= 45) {
			return "G3a";
		} else if (resultadoMdrd < 45 && resultadoMdrd >= 30) {
			return "G3b";
		} else if (resultadoMdrd < 30 && resultadoMdrd >= 15) {
			return "G4";
		}
		return "G5";
	}
	
	private static void cambiarFondoResult (TextView caja, String estadioFg, String estadioAlbu, Double albuminuria) {
		if (estadioFg.equals("G5") || albuminuria >= 2000) {
			caja.setBackgroundResource(R.drawable.shape_resultado_muymalo);
		} else if (estadioFg.equals("G4") || (estadioFg.equals("G3b") && (estadioAlbu.equals("A2") || estadioAlbu.equals("A3")))) {
			caja.setBackgroundResource(R.drawable.shape_resultado_malo);
		} else if (albuminuria >=300 || (estadioFg.equals("G3a") && estadioAlbu.equals("A2"))|| (estadioFg.equals("G3b"))) {
			caja.setBackgroundResource(R.drawable.shape_resultado_regular);
		} else if (estadioAlbu.equals("A2") || (estadioFg.equals("G3a") && estadioAlbu.equals("A1"))) {
			caja.setBackgroundResource(R.drawable.shape_resultado_bueno);
		} else {
			caja.setBackgroundResource(R.drawable.shape_resultado_muybueno);
		}
	}
	
	@Override
	public void onBackPressed(){
		super.onBackPressed();
		Intent intent = new Intent(ResultActivity.this, MainActivity.class);
		startActivity(intent);
		finish();
	}
}
