package com.madilon.nefroconsultor.adapters;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.madilon.nefroconsultor.R;
import com.madilon.nefroconsultor.classes.Recomendacion;
import com.madilon.nefroconsultor.helpers.Typefaces;

public class RecomendacionesItemAdapter extends BaseAdapter {
	protected Context context;
	protected List<Recomendacion> items;

	public RecomendacionesItemAdapter(Context context, List<Recomendacion> items) {
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
		}
		
		Recomendacion item = items.get(position);

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