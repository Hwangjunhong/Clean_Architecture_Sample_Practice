package com.hong.base.presentation.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.Environment
import android.view.View
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

object LayoutToImage {

    interface SaveImageCallback {
        fun imageResult(status: Int, uri: Uri?, flag: String)
    }

    var saveImageCallback: SaveImageCallback? = null

    fun saveBitMap(context: Context, drawView: View, flag: String) {
        val pictureFileDir = if (flag == "save") File(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
            "app_name"
        ) else File(context.cacheDir, "images")

        if (!pictureFileDir.exists()) {
            val isDirectoryCreated = pictureFileDir.mkdirs()
            if (!isDirectoryCreated) {
                saveImageCallback?.imageResult(400, null, flag)
                return
            }
        }
        val filename = pictureFileDir.path + File.separator + System.currentTimeMillis() + ".jpg"
        val pictureFile = if (flag == "save") File(filename) else File(pictureFileDir, "image.png")
        val bitmap: Bitmap = getBitmapFromView(drawView)
        try {
            pictureFile.createNewFile()
            val oStream = FileOutputStream(pictureFile)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, oStream)
            oStream.flush()
            oStream.close()
        } catch (e: IOException) {
            saveImageCallback?.imageResult(400, null, flag)
        }

        when (flag == "save") {
            true -> scanGallery(context, pictureFile.absolutePath, flag)
            false -> saveImageCallback?.imageResult(200, null, flag)
        }
    }

    private fun getBitmapFromView(view: View): Bitmap {
        val returnedBitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(returnedBitmap)
        val bgDrawable = view.background
        if (bgDrawable != null) {
            bgDrawable.draw(canvas)
        } else {
            canvas.drawColor(Color.WHITE)
        }
        view.draw(canvas)
        return returnedBitmap
    }

    private fun scanGallery(context: Context, path: String, flag: String) {
        try {
            MediaScannerConnection.scanFile(context, arrayOf(path), null) { _: String?, uri: Uri? ->
                saveImageCallback?.imageResult(200, uri, flag)
            }
        } catch (e: Exception) {
            saveImageCallback?.imageResult(400, null, flag)
        }
    }
}