package com.sgut.android.nationalfootballleague.uiStyleDefinitions.design.style

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.sgut.android.nationalfootballleague.uiStyleDefinitions.design.extensions.withDefaultFontFamily

@Immutable
class Typography internal constructor(
    val titleL: TextStyle,
    val titleM: TextStyle,
    val titleS: TextStyle,
    val subtitle: TextStyle,
    val body: TextStyle,
    val small: TextStyle,
    val caption: TextStyle,
) {
    constructor(
        fontFamily: FontFamily = FontFamily.Default,
        titleL: TextStyle = DefaultTextStyle.copy(
            fontWeight = FontWeight.Normal,
            fontSize = 32.sp,
            lineHeight = 38.sp,
        ),
        titleM: TextStyle = DefaultTextStyle.copy(
            fontWeight = FontWeight.Normal,
            fontSize = 28.sp,
            lineHeight = 30.sp,
        ),
        titleS: TextStyle = DefaultTextStyle.copy(
            fontWeight = FontWeight.SemiBold,
            fontSize = 24.sp,
            lineHeight = 30.sp,
        ),
        subtitle: TextStyle = DefaultTextStyle.copy(
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            lineHeight = 22.sp,
        ),
        body: TextStyle = DefaultTextStyle.copy(
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            lineHeight = 22.sp,
        ),
        small: TextStyle = DefaultTextStyle.copy(
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            lineHeight = 18.sp,
        ),
        caption: TextStyle = DefaultTextStyle.copy(
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            lineHeight = 16.sp,
        ),
    ) : this(
        titleL = titleL.withDefaultFontFamily(fontFamily),
        titleM = titleM.withDefaultFontFamily(fontFamily),
        titleS = titleS.withDefaultFontFamily(fontFamily),
        subtitle = subtitle.withDefaultFontFamily(fontFamily),
        body = body.withDefaultFontFamily(fontFamily),
        small = small.withDefaultFontFamily(fontFamily),
        caption = caption.withDefaultFontFamily(fontFamily),
    )

    fun copy(
        titleL: TextStyle = this.titleL,
        titleM: TextStyle = this.titleM,
        titleS: TextStyle = this.titleS,
        subtitle: TextStyle = this.subtitle,
        body: TextStyle = this.body,
        small: TextStyle = this.small,
        caption: TextStyle = this.caption,
    ): Typography = Typography(
        titleL = titleL,
        titleM = titleM,
        titleS = titleS,
        subtitle = subtitle,
        body = body,
        small = small,
        caption = caption,
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Typography) return false

        if (titleL != other.titleL) return false
        if (titleM != other.titleM) return false
        if (titleS != other.titleS) return false
        if (subtitle != other.subtitle) return false
        if (body != other.body) return false
        if (small != other.small) return false
        if (caption != other.caption) return false

        return true
    }

    override fun hashCode(): Int {
        var result = titleL.hashCode()
        result = 31 * result + titleM.hashCode()
        result = 31 * result + titleS.hashCode()
        result = 31 * result + subtitle.hashCode()
        result = 31 * result + body.hashCode()
        result = 31 * result + small.hashCode()
        result = 31 * result + caption.hashCode()
        return result
    }
}

internal val DefaultTextStyle = TextStyle.Default
internal val LocalTypography = staticCompositionLocalOf { Typography() }

