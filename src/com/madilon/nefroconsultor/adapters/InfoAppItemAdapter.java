package com.madilon.nefroconsultor.adapters;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.TextView.BufferType;

import com.madilon.nefroconsultor.R;
import com.madilon.nefroconsultor.classes.InfoApp;
import com.madilon.nefroconsultor.helpers.SpannableHelper;
import com.madilon.nefroconsultor.helpers.Typefaces;

public class InfoAppItemAdapter extends BaseAdapter {
	protected Context context;
	protected List<InfoApp> items;

	public InfoAppItemAdapter(Context context, List<InfoApp> items) {
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
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View vi=convertView;

		if(convertView == null) {
			vi = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_general_info, parent, false);
		}
		
		InfoApp item = items.get(position);

		TextView titulo = (TextView) vi.findViewById(R.id.titulo);
		titulo.setTypeface(Typefaces.SignikaBold(context));
		titulo.setText(item.getTitulo().toUpperCase());

		final TextView descripcion = (TextView) vi.findViewById(R.id.descripcion);
		descripcion.setTypeface(Typefaces.SignikaRegular(context));
		descripcion.setText(SpannableHelper.applyTags(item.getDescripcion(), context), BufferType.SPANNABLE);

		return vi;
	}
}