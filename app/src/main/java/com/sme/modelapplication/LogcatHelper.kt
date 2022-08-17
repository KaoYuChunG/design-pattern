package com.sme.modelapplication

import android.content.Context
import android.os.Build
import android.os.Environment
import android.os.Process
import androidx.annotation.RequiresApi
import java.io.*
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

object LogcatHelper {


    private val TAG = this.javaClass.simpleName
    private lateinit var PATH_LOGCAT: String
    private var mLogDumper: LogDumper? = null
    private var mPId = 0

    /**
     *
     * 初始化目录
     *
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    fun initLogcat(context: Context) {
        try {
            if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) { // 优先保存到SD卡中
                PATH_LOGCAT = (context.getExternalFilesDirs(Environment.DIRECTORY_DOCUMENTS)[0].toString()
                        + File.separator + "BLH87")
            }
            LOG.i("BENNWW", "PATH" + PATH_LOGCAT)
            val file = File(PATH_LOGCAT)
            if (!file.exists()) {
                val mk = file.mkdirs()
            }
            mPId = Process.myPid()
        } catch (e: Exception) {
            e.stackTrace
            LOG.e(TAG, "init:$e")
        }
    }

    fun openLog(context: Context){
        if (LOG.isApkInDebug(context)) {
            initLogcat(context)
            try {
                start()
            } catch (e: Exception) {
                LOG.e("MainActivity", """Crash${e.message}""".trimIndent())
                stop()
                start()
            }
        }

        LOG.setLOG_Enable(LOG.isApkInDebug(context))
//        AppInfoUtils.setAppAllInfo(context)
    }

    fun start(){
        if (mLogDumper == null) mLogDumper = LogDumper(mPId.toString(), PATH_LOGCAT)
        mLogDumper?.start()
    }

    fun stop() {
        mLogDumper?.apply {
            this.stopLogs()
            mLogDumper = null
        }
    }

    fun getFileName(): String {
        val format = SimpleDateFormat("yyyy-MM-dd^HH_mm")
        return format.format(Date(System.currentTimeMillis())) // 2012年10月03日 23:41:31
    }

    private class LogDumper(private val mPID: String, dir: String) : Thread() {
        private var logcatProc: java.lang.Process? =null
        private var mReader: BufferedReader? = null
        private var mRunning = true
        private var cmds: String
        private var out: FileOutputStream? = null

        init {
            try {
                val s: String = getFileName()
                val yourFile = File(
                    dir, "log-" + s + ".log"
                )
                yourFile.createNewFile()
                out = FileOutputStream(yourFile)
            } catch (e: FileNotFoundException) {
                // TODO Auto-generated catch block
                LOG.e("LogHelper", """Crash${e.message}""")
            } catch (e: IOException) {
                LOG.e("LogHelper", """Crash${e.message}""")
            }
            /**
             *
             * 日志等级：*:v , *:d , *:w , *:e , *:f , *:s
             *
             * 显示当前mPID程序的 E和W等级的日志.
             *
             */

            // cmds = "logcat *:e *:w | grep \"(" + mPID + ")\"";
            // cmds = "logcat  | grep \"(" + mPID + ")\"";//打印所有日志信息
            // cmds = "logcat -s way";//打印标签过滤信息
            cmds = "logcat *:e *:i | grep \"($mPID)\""
        }

        fun stopLogs() {
            mRunning = false
        }

        override fun run() {
            try {
                logcatProc = Runtime.getRuntime().exec(cmds)
                logcatProc?.run{
                    mReader = BufferedReader(
                        InputStreamReader(
                            this.getInputStream()
                        ), 1024
                    )
                }
                var line: String? = null
                while (mRunning && mReader?.readLine().also { line = it } != null) {
                    if (!mRunning) {
                        break
                    }
                    if (line?.length == 0) {
                        continue
                    }
                    out?.apply {
                        if(line!!.contains(mPID)){
                            this.write((line + "\n").toByteArray())
                        }
                    }
                }
            } catch (e: IOException) {
                LOG.e("LogHelper", """Crash${e.message}""")
            } finally {
                logcatProc?.apply  {
                    this.destroy()
                    logcatProc = null
                }
                mReader?.apply  {
                    try {
                        this.close()
                        mReader = null
                    } catch (e: IOException) {
                        LOG.e("LogHelper", """Crash${e.message}""")
                    }
                }
                out?.apply {
                    try {
                        this.close()
                    } catch (e: IOException) {
                        LOG.e("LogHelper", """Crash${e.message}""")
                    }
                    out = null
                }

            }
        }
    }

}