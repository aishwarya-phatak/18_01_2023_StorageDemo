package com.example.a18_02_2023_internalandexternalstorage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var externalStorageState = Environment.getExternalStorageState()
        if(externalStorageState.equals(Environment.MEDIA_MOUNTED)) {
            Log.e("tag", "Media Mounted Storage")
        }

        if(externalStorageState.equals(Environment.MEDIA_MOUNTED_READ_ONLY)){
            Log.e("tag","Media Mounted Read Only")
        }

        var rootDir : File = Environment.getRootDirectory()
        Log.e("tag","${rootDir.absolutePath} -- ${rootDir.name} -- ${rootDir.totalSpace} -- ${rootDir.usableSpace} -- ${rootDir.path}")
        var publicStorageDir : File = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC)
        Log.e("tag","${publicStorageDir.canonicalPath} -- ${publicStorageDir.absolutePath} --${publicStorageDir.name}")

        var moviesDir : File = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES)
        Log.e("tag","${moviesDir.absolutePath}")
        var dcimDirectory = getExternalFilesDir(Environment.DIRECTORY_DCIM)
        var dataDir = Environment.getDataDirectory()
        Log.e("tag","${dataDir.path} -- ${dataDir.absolutePath}")

        var dir = Environment.getExternalStorageDirectory()
        Log.e("tag","${dir.path} -- ${dir.name}")

        var fileOutputStream : FileOutputStream = openFileOutput("internalStorageDemo_testFile",AppCompatActivity.MODE_PRIVATE)
        fileOutputStream.write("Durgesh is working Hard for placement".toByteArray())
        fileOutputStream.write("Android Jan Batch will get placed soon!".toByteArray())
        fileOutputStream.close()

        var fileInputStream : FileInputStream = openFileInput("internalStorageDemo_testFile")
        var count = 0
        var data = ByteArray(1024 * 2)
        count = fileInputStream.read(data)
        Log.e("tag","$count")

        while (count != -1){
            Log.e("tag",String(data,0,count))
            count = fileInputStream.read(data)
        }
        fileInputStream.close()
    }
}