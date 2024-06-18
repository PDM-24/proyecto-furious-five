package com.ff.funum.utils

import android.app.Activity
import android.content.Context
import android.content.Intent

fun changeActivity(context: Context, activity: Class<*>){
    context.startActivity(Intent(context, activity))
    (context as Activity).finish()
}