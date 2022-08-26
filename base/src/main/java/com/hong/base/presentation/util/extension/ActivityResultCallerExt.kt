package com.hong.base.presentation.util.extension

import android.app.Activity
import android.content.Intent
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultCaller
import androidx.activity.result.contract.ActivityResultContracts

fun ActivityResultCaller.register(action: (ActivityResult) -> Unit) =
    registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        action(it)
    }


fun ActivityResultCaller.registerResult(
    resultCode: Int = Activity.RESULT_OK,
    action: (Intent?) -> Unit,
) = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
    if (it.resultCode == resultCode) {
        action(it.data)
    }
}

// 다중 callback 구현 시 필요
fun activityResultCallback(action: () -> Unit) = ActivityResultCallback<ActivityResult> {
    if (it.resultCode == Activity.RESULT_OK) {
        action()
    }
}
