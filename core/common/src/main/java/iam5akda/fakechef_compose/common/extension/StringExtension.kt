package iam5akda.fakechef_compose.common.extension

import android.net.Uri

fun String?.uriEncode(): String {
    return this?.let {
        Uri.encode(this)
    } ?: ""
}

fun String?.uriDecode(): String {
    return this?.let {
        Uri.decode(this)
    } ?: ""
}

fun String.lettersAndDigits(): String {
    return this.filter { it.isLetterOrDigit() }
}