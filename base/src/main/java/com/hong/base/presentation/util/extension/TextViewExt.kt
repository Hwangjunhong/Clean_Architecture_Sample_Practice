package com.hong.base.presentation.util.extension

import android.annotation.SuppressLint
import android.text.style.LeadingMarginSpan
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.core.text.buildSpannedString
import androidx.core.text.inSpans


fun TextView.setTextIndent(value: String, indent: Float) {
    text = value
    val span = LeadingMarginSpan.Standard(0, indent.toInt())
    text = buildSpannedString {
        inSpans(span) {
            append(text)
        }
    }
}

@SuppressLint("SetTextI18n")
fun TextView.getEmojiByUnicode(unicode: Int, content: String) {
    text = "${String(Character.toChars(unicode))}  $content"
}

fun TextView.toHtml(html: String) {
    this.text = HtmlCompat.fromHtml(html, HtmlCompat.FROM_HTML_MODE_COMPACT)
}
