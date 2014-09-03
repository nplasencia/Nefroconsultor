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
}
