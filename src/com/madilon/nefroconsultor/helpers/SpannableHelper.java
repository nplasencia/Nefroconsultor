package com.madilon.nefroconsultor.helpers;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Layout.Alignment;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.AlignmentSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.UnderlineSpan;

public class SpannableHelper {
	
	public static String OPEN_BOLD = "<b>";
	public static String CLOSE_BOLD = "</b>";
	public static String OPEN_UNDER = "<u>";
	public static String CLOSE_UNDER = "</u>";
	public static String OPEN_SUP = "<sup>";
	public static String CLOSE_SUP = "</sup>";
	public static String OPEN_LIGHT = "<light>";
	public static String CLOSE_LIGHT = "</light>";
	public static String OPEN_SMALL = "<small>";
	public static String CLOSE_SMALL = "</small>";
	public static String OPEN_CENTER = "<center>";
	public static String CLOSE_CENTER = "</center>";

	/**
	 * Returns a CharSequence that concatenates the specified array of CharSequence
	 * objects and then applies a list of zero or more tags to the entire range.
	 *
	 * @param content an array of character sequences to apply a style to
	 * @param tags the styled span objects to apply to the content
	 *        such as android.text.style.StyleSpan
	 *
	 */
	private static CharSequence apply(CharSequence[] content, Object... tags) {
	    SpannableStringBuilder text = new SpannableStringBuilder();
	    openTags(text, tags);
	    for (CharSequence item : content) {
	        text.append(item);
	    }
	    closeTags(text, tags);
	    return text;
	}

	/**
	 * Iterates over an array of tags and applies them to the beginning of the specified
	 * Spannable object so that future text appended to the text will have the styling
	 * applied to it. Do not call this method directly.
	 */
	private static void openTags(Spannable text, Object[] tags) {
	    for (Object tag : tags) {
	        text.setSpan(tag, 0, 0, Spannable.SPAN_MARK_MARK);
	    }
	}

	/**
	 * "Closes" the specified tags on a Spannable by updating the spans to be
	 * endpoint-exclusive so that future text appended to the end will not take
	 * on the same styling. Do not call this method directly.
	 */
	private static void closeTags(Spannable text, Object[] tags) {
	    int len = text.length();
	    for (Object tag : tags) {
	        if (len > 0) {
	            text.setSpan(tag, 0, len, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
	        } else {
	            text.removeSpan(tag);
	        }
	    }
	}
	
	/**
	 * Returns a CharSequence that applies boldface to the concatenation
	 * of the specified CharSequence objects.
	 */
	public static CharSequence bold(Context context, CharSequence... content) {
		return apply(content, new CustomTypefaceSpan("Signika Bold", Typefaces.SignikaBold(context)));
	}
	
	/**
	 * Returns a CharSequence that applies lightface to the concatenation
	 * of the specified CharSequence objects.
	 */
	public static CharSequence light(Context context, CharSequence... content) {
		return apply(content, new CustomTypefaceSpan("Signika Light", Typefaces.SignikaLight(context)));
	}
	
	/**
	 * Returns a CharSequence that applies regularface to the concatenation
	 * of the specified CharSequence objects.
	 */
	public static CharSequence regular(Context context, CharSequence... content) {
	    return apply(content, new CustomTypefaceSpan("Signika Regular", Typefaces.SignikaRegular(context)));
	}

	/**
	 * Returns a CharSequence that applies italics to the concatenation
	 * of the specified CharSequence objects.
	 */
	public static CharSequence italic(CharSequence... content) {
	    return apply(content, new StyleSpan(Typeface.ITALIC));
	}
	
	/**
	 * Returns a CharSequence that applies superscript to the concatenation
	 * of the specified CharSequence objects.
	 */
	public static CharSequence supString(CharSequence... content) {
	    return apply(content, new SuperscriptSpan());
	}
	
	/**
	 * Returns a CharSequence that applies a size to the concatenation
	 * of the specified CharSequence objects using the
	 */
	public static CharSequence resize(float relativeSize, CharSequence... content) {
	    return apply(content, new RelativeSizeSpan(relativeSize));
	}
	
	public static CharSequence centerAlignment(CharSequence... content) {
	    return apply(content, new AlignmentSpan.Standard(Alignment.ALIGN_CENTER));
	}
	
	public static CharSequence underline(CharSequence... content) {
	    return apply(content, new UnderlineSpan());
	}

	/**
	 * Returns a CharSequence that applies a foreground color to the
	 * concatenation of the specified CharSequence objects.
	 * TODO: Completar el metodo para que revise si faltan tags.
	 */
	public static CharSequence color(int color, Context context, CharSequence... content) {
	    return apply(content, new ForegroundColorSpan(color));
	}
	
	public static CharSequence applyTags(CharSequence content, Context context) {
		String contentString = content.toString();
		
		
		while (contentString.contains(OPEN_BOLD)) {
			content = TextUtils.concat(content.subSequence(0, contentString.indexOf(OPEN_BOLD)), 
							 	   	   bold(context, content.subSequence(contentString.indexOf(OPEN_BOLD)+OPEN_BOLD.length(), contentString.indexOf(CLOSE_BOLD))), 
							 	   	   content.subSequence(contentString.indexOf(CLOSE_BOLD)+CLOSE_BOLD.length(), content.length()));
			contentString = content.toString();
		}
		
		while (contentString.contains(OPEN_UNDER)) {
			content = TextUtils.concat(content.subSequence(0, contentString.indexOf(OPEN_UNDER)), 
							 	   	   underline(content.subSequence(contentString.indexOf(OPEN_UNDER)+OPEN_UNDER.length(), contentString.indexOf(CLOSE_UNDER))), 
							 	   	   content.subSequence(contentString.indexOf(CLOSE_UNDER)+CLOSE_UNDER.length(), content.length()));
			contentString = content.toString();
		}
		
		while (contentString.contains(OPEN_SUP)) {
			content = TextUtils.concat(content.subSequence(0, contentString.indexOf(OPEN_SUP)), 
					 			   	   supString(content.subSequence(contentString.indexOf(OPEN_SUP)+OPEN_SUP.length(), contentString.indexOf(CLOSE_SUP))), 
					 			   	   content.subSequence(contentString.indexOf(CLOSE_SUP)+CLOSE_SUP.length(), content.length()));
			contentString = content.toString();
		}
		
		while (contentString.contains(OPEN_LIGHT)) {
			content = TextUtils.concat(content.subSequence(0, contentString.indexOf(OPEN_LIGHT)), 
					 			   	   light(context, content.subSequence(contentString.indexOf(OPEN_LIGHT)+OPEN_LIGHT.length(), contentString.indexOf(CLOSE_LIGHT))), 
					 			   	   content.subSequence(contentString.indexOf(CLOSE_LIGHT)+CLOSE_LIGHT.length(), content.length()));
			contentString = content.toString();
		}
		
		while (contentString.contains(OPEN_SMALL)) {
			content = TextUtils.concat(content.subSequence(0, contentString.indexOf(OPEN_SMALL)), 
					 			   	   resize(0.8f, content.subSequence(contentString.indexOf(OPEN_SMALL)+OPEN_SMALL.length(), contentString.indexOf(CLOSE_SMALL))), 
					 			   	   content.subSequence(contentString.indexOf(CLOSE_SMALL)+CLOSE_SMALL.length(), content.length()));
			contentString = content.toString();
		}
		
		while (contentString.contains(OPEN_CENTER)) {
			content = TextUtils.concat(content.subSequence(0, contentString.indexOf(OPEN_CENTER)), 
					 			   	   centerAlignment(content.subSequence(contentString.indexOf(OPEN_CENTER)+OPEN_CENTER.length(), contentString.indexOf(CLOSE_CENTER))), 
					 			   	   content.subSequence(contentString.indexOf(CLOSE_CENTER)+CLOSE_CENTER.length(), content.length()));
			contentString = content.toString();
		}
		
		return content;
	}
}
