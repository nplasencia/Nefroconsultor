package com.madilon.nefroconsultor.ui;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
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
			otrosMotivos.add(new OtroMotivo("Deterioro agudo de la función renal", "Deterioro agudo en la función renal (caída del FGe > 25 %) en menos de un mes " +
					"o un incremento de la creatinina sérica >25% en menos de 1 mes descartados factores exógenos (diarrea, vómitos, depleción por diuréticos en " +
					"tratamiento con IECAs, ARA II o inhibidores directos de la renina).", 1, false));
			otrosMotivos.add(new OtroMotivo("Deterioro progresivo de la función renal", getString(R.string.explicacionMotivo2), 2, false));
			otrosMotivos.add(new OtroMotivo("Hipertensión arterial resistente", "ERC e HTA resistente refractaria al tratamiento (> 140/90 mmHg) con tres fármacos " +
					"a plena dosis, uno de ellos diurético.", 3, false));
			otrosMotivos.add(new OtroMotivo("Alteraciones del potasio sérico", "Alteraciones en la concentración sérica de potasio (> 5,5 mmol/L o < 3,5 mmol/L sin " +
					"recibir diuréticos).", 1, false));
			otrosMotivos.add(new OtroMotivo("Anemia asociada a ERC", "Anemia: Hb < 10,5 g/dL con ERC a pesar de corregir ferropenia (índice de saturación de la " +
					"transferrina ISAT > 20% y ferritina > 100 ng/mL).", 2, false));
			otrosMotivos.add(new OtroMotivo("Hematuria no urológica asociada a proteinuria", "La presencia en el sedimento urinario de hematuria y/o leucocituria " +
					"durante más de tres meses, una vez se ha descartado la causa urológica o la infección de orina (incluida la tuberculosis urinaria), pueden ser " +
					"indicio de glomerulonefritis, pielonefritis o nefritis túbulo-intersticiales crónicas.", 3, false));
			otrosMotivos.add(new OtroMotivo("Otros", "•Sospecha de causas genéticas de ERC.\n\t•Litiasis renal con más de 4 episodios de cólicos nefríticos al año " +
					"y/o que precisen realización de un estudio metabólico (historia familiar de litiasis, presencia de litiasis bilateral, enfermedades inflamatorias " +
					"intestinales, diarrea crónica o síndromes de malabsorción, historia de cirugía bariátrica, presencia de nefrocalcinosis, etc.).", 1, false));
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
}
