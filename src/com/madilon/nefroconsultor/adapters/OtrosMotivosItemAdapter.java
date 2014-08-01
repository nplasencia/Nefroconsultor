package com.madilon.nefroconsultor.adapters;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

import com.madilon.nefroconsultor.R;
import com.madilon.nefroconsultor.classes.OtroMotivo;
import com.madilon.nefroconsultor.helpers.Typefaces;

public class OtrosMotivosItemAdapter extends BaseAdapter {
	protected Context context;
	protected List<OtroMotivo> items;

	public OtrosMotivosItemAdapter(Context context, List<OtroMotivo> items) {
		this.context = context;
		this.items = items;
	}

	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public Object getItem(int position) {
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		return items.get(position).getPosicion();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View vi=convertView;

		if(convertView == null) {
			vi = LayoutInflater.from(parent.getContext()).inflate(R.layout.otromotivo, parent, false);
			vi.setBackground(context.getResources().getDrawable(R.drawable.shape_otrosmotivos1));
		}
		
		final OtroMotivo item = items.get(position);
		
		switch (position % 3) {
			case 0:
				vi.setBackground(context.getResources().getDrawable(R.drawable.shape_otrosmotivos1));
				break;
			case 1:
				vi.setBackground(context.getResources().getDrawable(R.drawable.shape_otrosmotivos2));
				break;
			case 2:
				vi.setBackground(context.getResources().getDrawable(R.drawable.shape_otrosmotivos3));
				break;
			default:
				break;
		}
		
		CheckBox check = (CheckBox) vi.findViewById(R.id.check);
		check.setChecked(item.isChecked());
		check.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				item.setChecked(isChecked);
			}
		});
		
		TextView titulo = (TextView) vi.findViewById(R.id.titulo);
		titulo.setTypeface(Typefaces.SignikaBold(context));
		titulo.setText(item.getTitulo());

		final TextView descripcion = (TextView) vi.findViewById(R.id.descripcion);
		descripcion.setTypeface(Typefaces.SignikaRegular(context));
		descripcion.setText(item.getDescripcion());
		
		final Button buttonMas = (Button) vi.findViewById(R.id.btn_mostrarMas);
		buttonMas.setTypeface(Typefaces.SignikaRegular(context));
		
		final Button buttonMenos = (Button) vi.findViewById(R.id.btn_mostrarMenos);
		buttonMenos.setTypeface(Typefaces.SignikaRegular(context));
		
		buttonMas.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				buttonMenos.setVisibility(View.VISIBLE);
				buttonMas.setVisibility(View.GONE);
				descripcion.setVisibility(View.VISIBLE);
			}
		});
		
		buttonMenos.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				buttonMenos.setVisibility(View.GONE);
				buttonMas.setVisibility(View.VISIBLE);
				descripcion.setVisibility(View.GONE);
			}
		});

		return vi;
	}
}