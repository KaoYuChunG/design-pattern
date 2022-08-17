package com.sme.modelapplication

import android.content.Context
import android.content.pm.ApplicationInfo
import android.util.Log
import java.lang.Exception

object LOG {

    private var LOG_Enable = false

    fun e(tag: String, msg: String) {
        if (LOG_Enable) Log.e(tag, msg)
    }

    fun w(tag: String, msg: String) {
        if (LOG_Enable) Log.w(tag, msg)
    }

    fun i(tag: String, msg: String) {
        if (LOG_Enable) Log.i(tag, msg)
    }

    fun setLOG_Enable(LOG_Enable: Boolean) {
        LOG.LOG_Enable = LOG_Enable
    }

    fun isApkInDebug(context: Context): Boolean {
        return try {
            val info = context.applicationInfo
            Log.i("LOG", "LOGG BUG "+ ApplicationInfo.FLAG_DEBUGGABLE)
            info.flags and ApplicationInfo.FLAG_DEBUGGABLE != 0
        } catch (e: Exception) {
            false
        }
    }
}