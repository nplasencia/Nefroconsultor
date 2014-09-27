package com.madilon.nefroconsultor.customization;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import com.madilon.nefroconsultor.R;
import com.madilon.nefroconsultor.helpers.SpannableHelper;

public class NefroConsultorTextView extends TextView {
	
	public NefroConsultorTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(attrs);
	}
	
	public NefroConsultorTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(attrs);
		
	}

	public NefroConsultorTextView(Context context) {
		super(context);
		init(null);
	}
	
	
	private void init(AttributeSet attrs) {
		if (attrs!=null) {
			 TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.nefroConsultorTextView);
			 String fontName = a.getString(R.styleable.nefroConsultorTextView_fontName);
			 if (fontName!=null) {
				 setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/signika/"+fontName));
				 setTextColor(getResources().getColor(R.color.titleTextColor));
				 setText(SpannableHelper.applyTags(getText(), getContext()), BufferType.SPANNABLE);
			 }
			 a.recycle();
		}
	}
}
