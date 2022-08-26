package com.hong.base.presentation.util.extension

import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import androidx.loader.content.CursorLoader

// 이미지의 절대경로 가져오기
fun Uri.getRealPathFromUri(context: Context): String? {
    val proj = arrayOf(MediaStore.Images.Media.DATA)
    val cursorLoader = CursorLoader(context, this, proj, null, null, null)
    return cursorLoader.loadInBackground()?.use {
        val columnIndex: Int = it.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        it.moveToFirst()
        it.getString(columnIndex)
    }
}