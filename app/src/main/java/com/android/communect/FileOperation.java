package com.android.communect;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import java.io.OutputStream;

public class FileOperation {
    public static Bitmap DrawableToBitmap(Drawable Drawableimage) {
        return ((BitmapDrawable)Drawableimage).getBitmap();
    }


    // useless
    @Deprecated
    public static OutputStream BitmapToPNG(Bitmap bitmapImage, int quality, OutputStream outputStream) {
        bitmapImage.compress(Bitmap.CompressFormat.PNG, quality, outputStream);
        return outputStream;
    }
}
