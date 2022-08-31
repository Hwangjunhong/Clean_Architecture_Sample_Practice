package com.hong.base.presentation.util.extension

import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment

fun Fragment.onFragmentBackCallback(
    callback: () -> Unit,
    predicate: () -> Boolean = { true }
) {
    requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            when {
                predicate() -> callback()
                else -> {
                    isEnabled = false
                    requireActivity().onBackPressed()
                }
            }
        }
    })
}