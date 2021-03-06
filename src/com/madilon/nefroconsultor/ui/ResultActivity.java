package com.madilon.nefroconsultor.ui;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TextView.BufferType;

import com.madilon.nefroconsultor.R;
import com.madilon.nefroconsultor.classes.ActionBarNefroConsultor;
import com.madilon.nefroconsultor.classes.OtroMotivo;
import com.madilon.nefroconsultor.commons.Globals;
import com.madilon.nefroconsultor.enums.AlbuminuriaEnum;
import com.madilon.nefroconsultor.enums.EstadioEnum;
import com.madilon.nefroconsultor.enums.FgeEnum;
import com.madilon.nefroconsultor.enums.SexoEnum;
import com.madilon.nefroconsultor.helpers.SpannableHelper;
import com.madilon.nefroconsultor.helpers.Typefaces;

public class ResultActivity extends ActionBarNefroConsultor {
	
	Integer edad;
	SexoEnum sexo;
	Double creatinina;
	Double albuminuria;
	Boolean razaNegra;
	Double cdkEpi;
	Double mdrdIms;
	AlbuminuriaEnum albuminuriaEstadio;
	FgeEnum fgEstadio;
	
	ArrayList<OtroMotivo> otrosMotivos;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);
		
		edad = Integer.parseInt(getIntent().getStringExtra(Globals.edadIntent));
		sexo = (SexoEnum) getIntent().getSerializableExtra(Globals.sexoIntent);
		creatinina = Double.parseDouble(getIntent().getStringExtra(Globals.creaIntent));
		albuminuria = Double.parseDouble(getIntent().getStringExtra(Globals.albuIntent));
		razaNegra = getIntent().getBooleanExtra(Globals.razaIntent, false);
		
		Boolean otros = false;
		String otrosExplicacion = getString(R.string.result_otros_by) + " ";
		if (getIntent().hasExtra(Globals.otrosIntent)){
			otrosMotivos = getIntent().getParcelableArrayListExtra(Globals.otrosIntent);
			for(OtroMotivo otroMotivo : otrosMotivos) {
				if (otroMotivo.isChecked()) {
					otros = true;
					if (otroMotivo.getTitulo().equals(getString(R.string.tituloMotivoOtros))) {
						otrosExplicacion += otroMotivo.getTitulo().toLowerCase() +" motivos y ";
					} else {
						otrosExplicacion += otroMotivo.getTitulo().toLowerCase() +" y ";
					}
				}
			}
			otrosExplicacion = otrosExplicacion.substring(0, otrosExplicacion.length()-3)+".";
		}
		
		cdkEpi = obtenerCKDEPI(edad, sexo, creatinina, albuminuria, razaNegra);
		mdrdIms = obtenerMDRDIDMS(edad, sexo, creatinina, albuminuria, razaNegra);
		
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		((TextView) findViewById(R.id.cabecera1)).setTypeface(Typefaces.SignikaBold(this));
		((TextView) findViewById(R.id.text_fgeckdepi_units)).setTypeface(Typefaces.SignikaRegular(this));
		((TextView) findViewById(R.id.text_mdrd)).setTypeface(Typefaces.SignikaBold(this));
		((TextView) findViewById(R.id.text_mdrd_units)).setTypeface(Typefaces.SignikaRegular(this));
		((TextView) findViewById(R.id.text_clasificacion)).setText(SpannableHelper.bold(this, getString(R.string.clasificacion)), BufferType.SPANNABLE);
		
		TextView fgecdkepi = (TextView) findViewById(R.id.text_fgeckdepi);
		fgecdkepi.setTypeface(Typefaces.SignikaBold(this));
		fgecdkepi.setText(String.format(getString(R.string.fgecdk), cdkEpi));
		
		TextView mdrd = (TextView) findViewById(R.id.text_mdrd);
		mdrd.setTypeface(Typefaces.SignikaBold(this));
		mdrd.setText(String.format(getString(R.string.mdrd), mdrdIms));
		
		TextView result = (TextView) findViewById(R.id.text_resultado);
		result.setTypeface(Typefaces.SignikaLight(this));
		
		TextView recomendacionesAst = (TextView) findViewById(R.id.text_recomendaciones_asterisco);
		recomendacionesAst.setTypeface(Typefaces.SignikaLight(this));
		
		((TextView) findViewById(R.id.cabecera2)).setTypeface(Typefaces.SignikaBold(this));
		
		TextView recomendacionesTitle = (TextView) findViewById(R.id.text_recomendaciones_title);
		recomendacionesTitle.setTypeface(Typefaces.SignikaBold(this));
		
		TextView recomendacionesSubtitle = (TextView) findViewById(R.id.text_recomendaciones_subtitle);
		recomendacionesSubtitle.setTypeface(Typefaces.SignikaRegular(this));
		
		TextView recomendacionesDesc = (TextView) findViewById(R.id.text_recomendaciones_descripcion);
		recomendacionesDesc.setTypeface(Typefaces.SignikaRegular(this));
		
		albuminuriaEstadio = AlbuminuriaEnum.albuminuriaEstadios(albuminuria);
		fgEstadio = FgeEnum.fgEstadios(mdrdIms);
		
		if ((fgEstadio.getId() < FgeEnum.G3a.getId()) && albuminuriaEstadio.getId() < AlbuminuriaEnum.A2.getId()) {
			result.setText(getString(R.string.resultAst) + " " + fgEstadio.getDescription() +" "+albuminuriaEstadio.getDescription());
			recomendacionesAst.setText(R.string.text_recomendaciones_asterisco);
			recomendacionesAst.setVisibility(View.VISIBLE);
		} else {
			result.setText(getString(R.string.result) + " " + fgEstadio.getDescription() + " " +albuminuriaEstadio.getDescription());
		}
		
		if (!otros) {	
			if (albuminuriaEstadio.equals(AlbuminuriaEnum.A2) && (fgEstadio.getId() < FgeEnum.G3a.getId())) {
				TextView textCasoA2 = (TextView) findViewById(R.id.text_recomendaciones_casoA2);
				textCasoA2.setTypeface(Typefaces.SignikaRegular(this));
				textCasoA2.setVisibility(View.VISIBLE);
				TextView textCasoA2Elena = (TextView) findViewById(R.id.text_recomendaciones_casoA2_elena);
				textCasoA2Elena.setTypeface(Typefaces.SignikaLight(this));
				textCasoA2Elena.setVisibility(View.VISIBLE);
			}
		}
		
		result.setBackgroundResource(EstadioEnum.getFromAlbuminuriaFgEnum(albuminuriaEstadio, fgEstadio).getEstadio().getBackground());
		
		if ((edad <= 80 && mdrdIms <= 30) || (edad > 80 && mdrdIms <=20 )) {
			recomendacionesTitle.setText(R.string.remitirCaso1);
		} else if (albuminuria >= 300) {
			recomendacionesTitle.setText(R.string.remitirCaso2);
		} else if (otros){
			recomendacionesTitle.setText(R.string.remitir);
			recomendacionesSubtitle.setText(otrosExplicacion);
		} else if (edad > 80 && (mdrdIms >20 && mdrdIms <30)) {
			recomendacionesTitle.setText(R.string.remitirVsNoRemitir);
			recomendacionesSubtitle.setText(R.string.noRemitirCaso1Subtitle);
			recomendacionesDesc.setText(R.string.noRemitirCaso1Desc);
		} else if (edad > 70 && (mdrdIms > 30 && mdrdIms < 45)) {
			recomendacionesTitle.setText(R.string.noRemitir);
			TextView textCasoEspecial = (TextView) findViewById(R.id.text_noRemitirCaso3Subtitle_elena);
			textCasoEspecial.setTypeface(Typefaces.SignikaLight(this));
			textCasoEspecial.setVisibility(View.VISIBLE);
			if (albuminuriaEstadio.equals(AlbuminuriaEnum.A2)) {
				recomendacionesSubtitle.setText(R.string.noRemitirCaso3Subtitle);
			}
			if (edad > 80) {
				recomendacionesSubtitle.setText(recomendacionesSubtitle.getText().toString() + getText(R.string.noRemitirCaso3Subtitle_80));
			}
			
		} else if (edad <= 70 && (mdrdIms > 30 && mdrdIms < 45)) {
			if (albuminuriaEstadio.getId() < AlbuminuriaEnum.A3a.getId()) {
				recomendacionesTitle.setText(R.string.remitirVsNoRemitir);
			} else {
				recomendacionesTitle.setText(R.string.noRemitir);
			}
			recomendacionesSubtitle.setText(R.string.noRemitirCaso2Subtitle);
			recomendacionesDesc.setTypeface(Typefaces.SignikaLight(this));
			recomendacionesDesc.setText(R.string.noRemitirCaso2Desc);
		} else if (albuminuriaEstadio.equals(AlbuminuriaEnum.A2)) {
			recomendacionesTitle.setText(R.string.noRemitir);
			if (fgEstadio.equals(FgeEnum.G3a)) {
				recomendacionesSubtitle.setText(SpannableHelper.applyTags(getString(R.string.noRemitirCaso3_2Subtitle), this), BufferType.SPANNABLE);
			} else {
				recomendacionesSubtitle.setText(R.string.noRemitirCaso3Subtitle);
			}
		} else if (albuminuria < 30 && (fgEstadio.getId() < FgeEnum.G3a.getId() && albuminuriaEstadio.getId() < AlbuminuriaEnum.A2.getId())) {
			recomendacionesTitle.setText(R.string.noRemitir);
			recomendacionesSubtitle.setText(R.string.noRemitirCaso4Subtitle);
		} else if (fgEstadio.equals(FgeEnum.G3a) && albuminuriaEstadio.getId() < AlbuminuriaEnum.A2.getId()) {
			recomendacionesTitle.setText(R.string.noRemitir);
			recomendacionesSubtitle.setTypeface(Typefaces.SignikaLight(this));
			recomendacionesSubtitle.setText(R.string.noRemitirCaso3_1Subtitle);
		}
		
		final Button buttonRecomendaciones = ((Button) findViewById(R.id.btn_recomendaciones));
		buttonRecomendaciones.setTypeface(Typefaces.SignikaBold(this));
		buttonRecomendaciones.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ResultActivity.this, RecomendacionesActivity.class);
				startActivity(intent);
			}
		});
		
		result.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ResultActivity.this, ExplainResultActivity.class);
				intent.putExtra(Globals.sexoIntent, sexo);
				intent.putExtra(Globals.edadIntent, edad.toString());
				intent.putExtra(Globals.albuIntent, albuminuria.toString());
				intent.putExtra(Globals.creaIntent, creatinina.toString());
				intent.putExtra(Globals.razaIntent, razaNegra);
				intent.putExtra(Globals.cdkEpiIntent, cdkEpi.toString());
				intent.putExtra(Globals.mdrdIdmsIntent, mdrdIms.toString());
				intent.putExtra(Globals.albuEstadioIntent, albuminuriaEstadio);
				intent.putExtra(Globals.fgEstadioIntent, fgEstadio);
				intent.putExtra(Globals.resultadoTextIntent, ((TextView) findViewById(R.id.text_recomendaciones_title)).getText().toString());
				startActivity(intent);
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
	
	@Override
	public void onBackPressed(){
		Intent intent = new Intent(ResultActivity.this, MainActivity.class);
		intent.putExtra(Globals.sexoIntent, sexo);
		intent.putExtra(Globals.edadIntent, edad.toString());
		intent.putExtra(Globals.albuIntent, albuminuria.toString());
		intent.putExtra(Globals.creaIntent, creatinina.toString());
		intent.putExtra(Globals.razaIntent, razaNegra);
		if (otrosMotivos != null) intent.putParcelableArrayListExtra(Globals.otrosIntent, otrosMotivos);
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
