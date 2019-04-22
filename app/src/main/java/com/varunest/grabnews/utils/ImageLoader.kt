package com.varunest.grabnews.utils

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.widget.ImageView
import com.varunest.grabnews.R

import java.io.*
import java.net.HttpURLConnection
import java.net.URL
import java.util.Collections
import java.util.WeakHashMap
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class ImageLoader(context: Context) {

    companion object {
        lateinit var instance: ImageLoader

        fun initialize(context: Context) {
            instance = ImageLoader(context)
        }
    }

    internal var memoryCache = MemoryCache()
    private var fileCache: FileCache = FileCache(context)
    private val imageViews = Collections.synchronizedMap(WeakHashMap<ImageView, String>())
    private var executorService: ExecutorService = Executors.newFixedThreadPool(5)

    internal val stubId = R.drawable.placeholder

    fun displayImage(url: String, imageView: ImageView) {
        imageViews[imageView] = url
        val bitmap = memoryCache.get(url)
        if (bitmap != null)
            imageView.setImageBitmap(bitmap)
        else {
            queuePhoto(url, imageView)
            imageView.setImageResource(stubId)
        }
    }

    private fun queuePhoto(url: String, imageView: ImageView) {
        val p = PhotoToLoad(url, imageView)
        executorService.submit(PhotosLoader(p))
    }

    private fun getBitmap(url: String): Bitmap? {
        val f = fileCache.getFile(url)

        //from SD cache
        val b = decodeFile(f)
        if (b != null)
            return b

        //from web
        try {
            var bitmap: Bitmap? = null
            val imageUrl = URL(url)
            val conn = imageUrl.openConnection() as HttpURLConnection
            conn.connectTimeout = 30000
            conn.readTimeout = 30000
            conn.instanceFollowRedirects = true
            val `is` = conn.inputStream
            val os = FileOutputStream(f)
            CommonUtils.copyStream(`is`, os)
            os.close()
            bitmap = decodeFile(f)
            return bitmap
        } catch (ex: Throwable) {
            ex.printStackTrace()
            if (ex is OutOfMemoryError)
                memoryCache.clear()
            return null
        }

    }

    private fun decodeFile(f: File): Bitmap? {
        try {
            val o = BitmapFactory.Options()
            o.inJustDecodeBounds = true
            BitmapFactory.decodeStream(FileInputStream(f), null, o)

            val REQUIRED_SIZE = 70
            var widthTemp = o.outWidth
            var heightTemp = o.outHeight
            var scale = 1
            while (true) {
                if (widthTemp / 2 < REQUIRED_SIZE || heightTemp / 2 < REQUIRED_SIZE)
                    break
                widthTemp /= 2
                heightTemp /= 2
                scale *= 2
            }

            val o2 = BitmapFactory.Options()
            o2.inSampleSize = scale
            return BitmapFactory.decodeStream(FileInputStream(f), null, o2)
        } catch (e: FileNotFoundException) {
        }

        return null
    }

    inner class PhotoToLoad(var url: String, var imageView: ImageView)

    internal inner class PhotosLoader(private var photoToLoad: PhotoToLoad) : Runnable {

        override fun run() {
            if (imageViewReused(photoToLoad))
                return
            val bmp = getBitmap(photoToLoad.url)
            if (bmp != null) {
                memoryCache.put(photoToLoad.url, bmp)
            }
            if (imageViewReused(photoToLoad))
                return
            val bd = BitmapDisplayer(bmp, photoToLoad)
            val a = photoToLoad.imageView.context as Activity
            a.runOnUiThread(bd)
        }
    }

    internal fun imageViewReused(photoToLoad: PhotoToLoad): Boolean {
        val tag = imageViews[photoToLoad.imageView]
        return tag == null || tag != photoToLoad.url
    }

    //Used to display bitmap in the UI thread
    internal inner class BitmapDisplayer(var bitmap: Bitmap?, var photoToLoad: PhotoToLoad) : Runnable {

        override fun run() {
            if (imageViewReused(photoToLoad))
                return
            if (bitmap != null)
                photoToLoad.imageView.setImageBitmap(bitmap)
            else
                photoToLoad.imageView.setImageResource(stubId)
        }
    }

    fun clearCache() {
        memoryCache.clear()
        fileCache.clear()
    }
}