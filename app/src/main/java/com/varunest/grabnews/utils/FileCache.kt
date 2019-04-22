package com.varunest.grabnews.utils

import android.content.Context

import java.io.File

class FileCache(context: Context) {

    private val cacheDir: File = context.cacheDir

    init {

        if (!cacheDir.exists())
            cacheDir.mkdirs()
    }

    fun getFile(url: String): File {
        //I identify images by hashcode. Not a perfect solution, good for the demo.
        val filename = url.hashCode().toString()
        //Another possible solution (thanks to grantland)
        //String filename = URLEncoder.encode(url);
        return File(cacheDir, filename)

    }

    fun clear() {
        val files = cacheDir.listFiles() ?: return
        for (f in files)
            f.delete()
    }

}