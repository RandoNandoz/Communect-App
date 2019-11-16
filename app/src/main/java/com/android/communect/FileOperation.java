package com.android.communect;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

public class FileOperation {
    public static Bitmap DrawableToBitmap(Drawable image) {
        return ((BitmapDrawable)image).getBitmap();
    }

    //TODO: IMPLETEMENT THIS
    public static void BitmapToType() {

    }
}
