package com.madilon.nefroconsultor.adapters;

import java.util.List;

import android.content.Context;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TextView.BufferType;

import com.madilon.nefroconsultor.R;
import com.madilon.nefroconsultor.classes.Informacion;
import com.madilon.nefroconsultor.helpers.SpannableHelper;
import com.madilon.nefroconsultor.helpers.Typefaces;

public class InformacionesItemAdapter extends BaseAdapter {
	private Context context;
	private List<Informacion> items;

	public InformacionesItemAdapter(Context context, List<Informacion> items) {
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
	public View getView(final int position, View convertView, final ViewGroup parent) {
		View vi=convertView;

		if(convertView == null) {
			vi = LayoutInflater.from(parent.getContext()).inflate(R.layout.recomendacion, parent, false);
		}
		
		Informacion item = items.get(position);

		TextView titulo = (TextView) vi.findViewById(R.id.titulo);
		titulo.setTypeface(Typefaces.SignikaBold(context));
		titulo.setText(item.getTitulo());
		
		if (item.getDescripcion() != null) {
		
			final TextView descripcion = (TextView) vi.findViewById(R.id.descripcion);
			descripcion.setTypeface(Typefaces.SignikaRegular(context));
			descripcion.setMovementMethod(LinkMovementMethod.getInstance());
			descripcion.setText(SpannableHelper.applyTags(item.getDescripcion(), context), BufferType.SPANNABLE);
			
			final Button buttonMas = (Button) vi.findViewById(R.id.btn_mostrarMas);
			buttonMas.setTypeface(Typefaces.SignikaRegular(context));
			
			final Button buttonMenos = (Button) vi.findViewById(R.id.btn_mostrarMenos);
			buttonMenos.setTypeface(Typefaces.SignikaRegular(context));
			
			final Button buttonMenosSup = (Button) vi.findViewById(R.id.btn_mostrarMenosSup);
			buttonMenosSup.setTypeface(Typefaces.SignikaRegular(context));
			
			buttonMas.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					buttonMenos.setVisibility(View.VISIBLE);
					buttonMenosSup.setVisibility(View.VISIBLE);
					buttonMas.setVisibility(View.GONE);
					descripcion.setVisibility(View.VISIBLE);
					if (position == (items.size()-1)) {
						((ListView) parent).setSelection(items.size()-1);
					}
				}
			});
			
			final OnClickListener ocultarListener = new OnClickListener() {
				@Override
				public void onClick(View v) {
					buttonMenos.setVisibility(View.GONE);
					buttonMenosSup.setVisibility(View.GONE);
					buttonMas.setVisibility(View.VISIBLE);
					descripcion.setVisibility(View.GONE);
				}
			}; 
			
			buttonMenos.setOnClickListener(ocultarListener);
			buttonMenosSup.setOnClickListener(ocultarListener);
		} else {
			vi.findViewById(R.id.btn_mostrarMas).setVisibility(View.GONE);
		}
		return vi;
	}
}