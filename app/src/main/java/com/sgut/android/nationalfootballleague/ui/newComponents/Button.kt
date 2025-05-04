package com.sgut.android.nationalfootballleague.ui.newComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.isSpecified
import androidx.compose.ui.graphics.takeOrElse
import androidx.compose.ui.semantics.SemanticsProperties.Role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.sgut.android.nationalfootballleague.uiStyleDefinitions.design.extensions.emphasize
import com.sgut.android.nationalfootballleague.uiStyleDefinitions.design.style.LocalContentAlpha
import com.sgut.android.nationalfootballleague.uiStyleDefinitions.design.style.LocalContentColor
import com.sgut.android.nationalfootballleague.uiStyleDefinitions.design.style.Theme
import com.sgut.android.nationalfootballleague.uiStyleDefinitions.design.style.contentColorFor
import com.sgut.android.nationalfootballleague.uiStyleDefinitions.design.style.value
import com.sgut.android.nationalfootballleague.uiStyleDefinitions.design.tokens.ButtonTokens
import com.sgut.android.nationalfootballleague.uiStyleDefinitions.design.tokens.FilledButtonTokens
import com.sgut.android.nationalfootballleague.uiStyleDefinitions.design.tokens.MeasurementTokens
import com.sgut.android.nationalfootballleague.uiStyleDefinitions.design.tokens.OutlineButtonTokens

@Composable
fun FilledButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    loading: Boolean = false,
    enabled: Boolean = !loading,
    destructive: Boolean = false,
    interactionSource: MutableInteractionSource? = null,
    colors: ButtonColors = ButtonDefaults.filledButtonColors(destructive, loading),
    content: @Composable RowScope.() -> Unit,
) = Button(
    onClick = onClick,
    modifier = modifier,
    enabled = enabled,
    loading = loading,
    interactionSource = interactionSource,
    colors = colors,
    content = content,
)


@Composable
fun OutlineButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    loading: Boolean = false,
    enabled: Boolean = !loading,
    destructive: Boolean = false,
    interactionSource: MutableInteractionSource? = null,
    colors: ButtonColors = ButtonDefaults.outlineButtonColors(destructive, loading),
    content: @Composable RowScope.() -> Unit,
) = Button(
    onClick = onClick,
    modifier = modifier,
    enabled = enabled,
    loading = loading,
    interactionSource = interactionSource,
    colors = colors,
    content = content,
)


@Composable
internal fun Button(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    loading: Boolean = false,
    enabled: Boolean = !loading,
    interactionSource: MutableInteractionSource? = null,
    colors: ButtonColors,
    content: @Composable RowScope.() -> Unit,
) {
    @Suppress("NAME_SHADOWING")
    val interactionSource = interactionSource ?: remember { MutableInteractionSource() }

    val shape = ButtonDefaults.shape

    val backgroundColor = colors.containerColor(enabled).value
    val contentColor = colors.contentColor(enabled).value
    val borderColor = colors.borderColor(enabled).value

    val outlineModifier =
        if (borderColor.isSpecified) Modifier.border(1.dp, borderColor, shape)
        else Modifier

    val backgroundModifier =
        if (backgroundColor.isSpecified) Modifier.background(backgroundColor, shape)
        else Modifier


    CompositionLocalProvider(
        LocalContentColor provides contentColor,
        LocalContentAlpha provides contentColor.alpha,
        LocalTextStyle provides Theme.typography.body.emphasize(),
    ) {
        Row(modifier
            .height(ButtonTokens.Height)
            .fillMaxWidth()
            .then(backgroundModifier)
            .then(outlineModifier)
            .clip(shape)
            .clickable(
//                interactionSource = interactionSource,
//                indication = ripple(),
                enabled = enabled,
                onClick = onClick,
            )
            .padding(ButtonDefaults.padding),
            horizontalArrangement = Arrangement.spacedBy(MeasurementTokens.Spacing.Small, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically,
        ) {
//            if (loading) CircularProgressIndicatorIcon() else content()
        }
    }
}

object ButtonDefaults {

    val disabledContentAlpha = ButtonTokens.DisabledContentOpacity
    val shape = ButtonTokens.Shape
    val padding = PaddingValues(
        start = ButtonTokens.LeadingSpace,
        end = ButtonTokens.TrailingSpace,
    )

    @Composable
    fun filledButtonColors(
        destructive: Boolean = false,
        loading: Boolean = false,
        containerColor: Color = (if (destructive) FilledButtonTokens.DestructiveContainerColor else FilledButtonTokens.ContainerColor).value,
        contentColor: Color = Theme.colors.contentColorFor(containerColor),
        disabledContainerColor: Color = containerColor.copy(disabledContentAlpha),
        disabledContentColor: Color = contentColor.copy(if (loading) 1F else disabledContentAlpha),
    ) = DefaultButtonColors(
        containerColor = containerColor,
        contentColor = contentColor,
        borderColor = Color.Unspecified,
        disabledContainerColor = disabledContainerColor,
        disabledContentColor = disabledContentColor,
        disabledBorderColor = Color.Unspecified,
    )

    @Composable
    fun outlineButtonColors(
        destructive: Boolean = false,
        loading: Boolean = false,
        contentColor: Color = (if (destructive) OutlineButtonTokens.DestructiveContentColor else OutlineButtonTokens.ContentColor).value,
        borderColor: Color = contentColor,
        disabledContentColor: Color = contentColor.copy(if (loading) 1F else disabledContentAlpha),
        disabledBorderColor: Color = borderColor.copy(disabledContentAlpha),
    ) = DefaultButtonColors(
        containerColor = Color.Unspecified,
        contentColor = contentColor,
        borderColor = borderColor,
        disabledContainerColor = Color.Unspecified,
        disabledContentColor = disabledContentColor,
        disabledBorderColor = disabledBorderColor,
    )
}

interface ButtonColors {

    @Composable fun containerColor(enabled: Boolean): State<Color>
    @Composable fun contentColor(enabled: Boolean): State<Color>
    @Composable fun borderColor(enabled: Boolean): State<Color>
}

//@Immutable
class DefaultButtonColors(
    val containerColor: Color,
    val contentColor: Color,
    val borderColor: Color,

    val disabledContainerColor: Color,
    val disabledContentColor: Color,
    val disabledBorderColor: Color,
) : ButtonColors {

    fun copy(
        containerColor: Color = this.containerColor,
        contentColor: Color = this.contentColor,
        borderColor: Color = this.borderColor,
        disabledContainerColor: Color = this.disabledContainerColor,
        disabledContentColor: Color = this.disabledContentColor,
        disabledBorderColor: Color = this.disabledBorderColor,
    ) = DefaultButtonColors(
        containerColor.takeOrElse { this.containerColor },
        contentColor.takeOrElse { this.contentColor },
        borderColor.takeOrElse { this.borderColor },
        disabledContainerColor.takeOrElse { this.disabledContainerColor },
        disabledContentColor.takeOrElse { this.disabledContentColor },
        disabledBorderColor.takeOrElse { this.disabledBorderColor },
    )

    @Composable
    override fun containerColor(enabled: Boolean): State<Color> =
        rememberUpdatedState(if (enabled) containerColor else disabledContainerColor)

    @Composable
    override fun contentColor(enabled: Boolean): State<Color> =
        rememberUpdatedState(if (enabled) contentColor else disabledContentColor)

    @Composable
    override fun borderColor(enabled: Boolean): State<Color> =
        rememberUpdatedState(if (enabled) borderColor else disabledBorderColor)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || other !is DefaultButtonColors) return false

        if (containerColor != other.containerColor) return false
        if (contentColor != other.contentColor) return false
        if (borderColor != other.borderColor) return false
        if (disabledContainerColor != other.disabledContainerColor) return false
        if (disabledContentColor != other.disabledContentColor) return false
        if (disabledBorderColor != other.disabledBorderColor) return false

        return true
    }

    override fun hashCode(): Int {
        var result = containerColor.hashCode()
        result = 31 * result + contentColor.hashCode()
        result = 31 * result + borderColor.hashCode()
        result = 31 * result + disabledContainerColor.hashCode()
        result = 31 * result + disabledContentColor.hashCode()
        result = 31 * result + disabledBorderColor.hashCode()
        return result
    }
}
