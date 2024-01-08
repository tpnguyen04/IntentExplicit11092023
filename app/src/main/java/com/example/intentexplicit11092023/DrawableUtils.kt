package com.example.intentexplicit11092023

import android.annotation.SuppressLint
import android.content.Context

class DrawableUtils {
    companion object {
        @SuppressLint("DiscouragedApi")
        fun getImageResource(nameImageResource: String, context: Context): Int {
            // hàm getIdentifier để trả về imageResource gốc trong thư mục drawable
            return context.resources.getIdentifier(nameImageResource, "drawable", context.packageName)
        }
    }
}