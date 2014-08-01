package com.madilon.nefroconsultor.helpers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.net.Uri;
import android.util.Log;

import com.madilon.nefroconsultor.classes.OtroMotivo;
import com.madilon.nefroconsultor.classes.Recomendacion;

public class NefroConsultorHelper {
	public static List<OtroMotivo> getMotivosFromXmlFile(int idFile, Context context) {
		List<OtroMotivo> otrosMotivos = new ArrayList<OtroMotivo>();
		try {
			XmlPullParser parser = context.getResources().getXml(idFile);
			parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            while (parser.next() != XmlPullParser.END_DOCUMENT) {
                OtroMotivo otroMotivo = new OtroMotivo();
                otrosMotivos.add(otroMotivo);
            }
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (XmlPullParserException e) {
			e.printStackTrace();
			return null;
		}
		return otrosMotivos;
	}
	
	public static List<Recomendacion> getRecomendacionesFromXmlFile(int idFile, Context context) {
		List<Recomendacion> recomendaciones = new ArrayList<Recomendacion>();
		try {
			XmlPullParser parser = context.getResources().getXml(idFile);
			parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            while (parser.next() != XmlPullParser.END_DOCUMENT) {
                Recomendacion recomendacion = new Recomendacion();
                recomendaciones.add(recomendacion);
            }
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (XmlPullParserException e) {
			e.printStackTrace();
			return null;
		}
		return recomendaciones;
	}
	
	public static void copyOpenAssets(Context context, String newFileName, String fileNameRead) {
        AssetManager assetManager = context.getAssets();

        InputStream in = null;
        OutputStream out = null;
        File file = new File(context.getFilesDir(), newFileName);
        if (!file.exists()) {
	        try {
	            in = assetManager.open(fileNameRead);
	            out = context.openFileOutput(file.getName(), Context.MODE_WORLD_READABLE);
	            copyFile(in, out);
	        } catch (Exception e) {
	            Log.e("CopyReadAssets", e.getMessage());
	        }
        }
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.parse("file://" + context.getFilesDir() + "/" + newFileName), "application/pdf");

        context.startActivity(intent);
    }

    private static void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while ((read = in.read(buffer)) != -1) {
            out.write(buffer, 0, read);
        }
        in.close();
        out.flush();
        out.close();
    }
}
