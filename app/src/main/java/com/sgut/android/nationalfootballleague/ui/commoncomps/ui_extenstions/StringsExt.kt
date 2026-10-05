package com.sgut.android.nationalfootballleague.ui.commoncomps.ui_extenstions

import android.text.TextUtils
import android.util.Patterns
import com.google.type.DateTime
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern

private const val MIN_PASS_LENGTH = 6
private const val PASS_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{4,}$"

fun String.isValidEmail(): Boolean {
    return this.isNotBlank() && Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

fun String.isValidPassword(): Boolean {
    return this.isNotBlank() &&
            this.length >= MIN_PASS_LENGTH &&
            Pattern.compile(PASS_PATTERN).matcher(this).matches()
}

fun String.passwordMatches(repeated: String): Boolean {
    return this == repeated
}

fun String.idFromParameter(): String {
    return this.substring(1, this.length - 1)
}



fun String.toDate(dateFormat: String = "yyyy-MM-dd'T'HH:mm'Z'", timeZone: TimeZone = TimeZone.getTimeZone("UTC")): Date? {
    val parser = SimpleDateFormat(dateFormat, Locale.getDefault())
    parser.timeZone = timeZone
    return parser.parse(this)
}

fun Date.formatTo(dateFormat: String, timeZone: TimeZone = TimeZone.getDefault()): String {
    val formatter = SimpleDateFormat(dateFormat, Locale.getDefault())
    formatter.timeZone = timeZone
    return formatter.format(this)
}


fun String.toFloatNum(): Float {
    return if(isNotEmpty() && length == 1 && TextUtils.equals(get(0).toString(), ".")) {
        "0".toFloat()
    } else  if (isEmpty()) {
        "0".toFloat()
    } else {
        toFloat()
    }
}
