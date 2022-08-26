package com.hong.base.presentation.util

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

const val OG_TITLE: String = "og:title"
const val OG_DESCRIPTION: String = "og:description"
const val OG_TYPE: String = "og:type"
const val OG_IMAGE: String = "og:image"
const val OG_URL: String = "og:url"
const val OG_SITE_NAME: String = "og:site_name"

object OgParser {

    // This is the entry point of the library which gets url and the callback
    suspend fun getContents(urlToParse: String): LinkSourceContent? =
        doInBackground(urlToParse)

    /**
     * Using withContext as we don't need parallel execution.
     * withContext return the result of single task.
     */
    @Suppress("BlockingMethodInNonBlockingContext")
    private suspend fun doInBackground(urlToParse: String): LinkSourceContent? =
        withContext(Dispatchers.IO) {
            try {

                val url = if (urlToParse.contains("naver")) StringBuilder().apply {
                    append(urlToParse.replace(".com/", ".com/PostView.naver?blogId=").replace("?Redirect=", "&"))
                    append("&redirect=Dlog&widgetTypeCall=true")
                } else urlToParse

                val response = Jsoup.connect(url.toString()).get()
                return@withContext organizeFetchedData(response)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            null
        }


    private fun organizeFetchedData(doc: Document): LinkSourceContent {
        val linkSourceContent = LinkSourceContent()
        val ogTags = doc.select("meta[property^=og:]")
        if (ogTags.size > 0) {
            ogTags.forEachIndexed { index, _ ->
                val tag = ogTags[index]
                val property = tag.attr("property")
                val content = (tag.attr("content"))
                when (property) {
                    OG_IMAGE -> linkSourceContent.image = content
                    OG_DESCRIPTION -> linkSourceContent.ogDescription = content
                    OG_URL -> linkSourceContent.ogUrl = content
                    OG_TITLE -> linkSourceContent.ogTitle = content
                    OG_SITE_NAME -> linkSourceContent.ogSiteName = content
                    OG_TYPE -> linkSourceContent.ogType = content
                }
            }
        }
        return linkSourceContent
    }
}

data class LinkSourceContent(
    var ogTitle: String = "",
    var ogDescription: String = "",
    var ogUrl: String = "",
    var image: String = "",
    var ogSiteName: String = "",
    var ogType: String = ""
)
