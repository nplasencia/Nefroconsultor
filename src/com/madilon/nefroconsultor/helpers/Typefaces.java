package com.madilon.nefroconsultor.helpers;

import android.content.Context;
import android.graphics.Typeface;

public final class Typefaces {
	public static final Typeface SignikaBold(Context ctx){
        Typeface typeface = Typeface.createFromAsset(ctx.getAssets(), "fonts/signika/Signika-Bold.ttf");
        return typeface;
    }
	
	public static final Typeface SignikaLight(Context ctx){
        Typeface typeface = Typeface.createFromAsset(ctx.getAssets(), "fonts/signika/Signika-Light.ttf");
        return typeface;
    }
	
	public static final Typeface SignikaRegular(Context ctx){
        Typeface typeface = Typeface.createFromAsset(ctx.getAssets(), "fonts/signika/Signika-Regular.ttf");
        return typeface;
    }
	
	public static final Typeface SignikaSemibold(Context ctx){
        Typeface typeface = Typeface.createFromAsset(ctx.getAssets(), "fonts/signika/Signika-Semibold.ttf");
        return typeface;
    }
}
