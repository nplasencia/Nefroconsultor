package com.madilon.nefroconsultor.helpers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.net.Uri;
import android.util.Log;

import com.madilon.nefroconsultor.R;
import com.madilon.nefroconsultor.commons.Globals;

public class NefroConsultorHelper {
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
    
    public static Intent getEmailIntent(Context context) {
		Intent emailIntent = new Intent(Intent.ACTION_VIEW);
		Uri data = Uri.parse("mailto:"+ Globals.emailContacto + "?subject="+ context.getString(R.string.emailSubject));
		emailIntent.setData(data);
		return Intent.createChooser(emailIntent, "Elige tu app de correo preferida");
	}
    
    public static Intent getValorarIntent(Context context) {
    	return new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + context.getPackageName()));
    }
    
    public static Intent getShareIntent(Context context) {
    	Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND); 
        sharingIntent.setType("text/plain"); 
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Prueba NefroConsultor"); 
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=com.naufre.guaguapp"); 
        return Intent.createChooser(sharingIntent, "Comparte NefroConsultor");
    }
}
