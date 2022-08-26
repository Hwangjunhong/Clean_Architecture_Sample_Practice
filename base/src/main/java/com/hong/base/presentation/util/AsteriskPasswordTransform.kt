package com.hong.base.presentation.util

import android.text.method.PasswordTransformationMethod
import android.view.View

class AsteriskPasswordTransform : PasswordTransformationMethod() {

    override fun getTransformation(source: CharSequence, view: View) = PasswordCharSequence(source)

    inner class PasswordCharSequence(private val source: CharSequence) : CharSequence {
        override val length = source.length
        override fun get(index: Int): Char = '*'
        override fun subSequence(startIndex: Int, endIndex: Int): CharSequence =
            source.subSequence(startIndex, endIndex)
    }

    // usage
    // binding.tvInput.transformationMethod = AsteriskPasswordTransform()
}
