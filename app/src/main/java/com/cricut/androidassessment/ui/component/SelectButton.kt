package com.cricut.androidassessment.ui.component

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.selection.triStateToggleable
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.cricut.androidassessment.ui.theme.AppTheme

@Composable
fun SelectButton(
    selected: Boolean,
    onSelectedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    content: @Composable (RowScope.() -> Unit)
) {
//    val toggleableModifier =
//        if (onSelectedChange != null) {
//            Modifier.triStateToggleable(
//                state = ToggleableState(selected),
//                onClick = { onSelectedChange(!selected) },
//                enabled = enabled,
//                role = Role.Checkbox,
//                interactionSource = null,
//                indication = null
//            )
//        } else {
//            Modifier
//        }
    val isSelected = remember { mutableStateOf(selected) }
    if (isSelected.value) {
        Button(
            onClick = {
                isSelected.value = !isSelected.value
                onSelectedChange(isSelected.value)
            },
            modifier = modifier,
            enabled = enabled,
            content = content
        )
    } else {
        OutlinedButton(
            onClick = {
                isSelected.value = !isSelected.value
                onSelectedChange(isSelected.value)
            },
            modifier = modifier,
            enabled = enabled,
            content = content
        )
    }
//    Button(
//        onClick = onClick,
//        modifier = modifier,
//        colors = ButtonDefaults.buttonColors(
//            containerColor = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface
//        )
//    ) {
//        Text(
//            text = text,
//            color = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface,
//        )
//    }
}

@Preview
@Composable
private fun SelectableButtonPreview(@PreviewParameter(BooleanProvider::class) isSelected: Boolean) {
    var isSelected = isSelected
    AppTheme {
        SelectButton(
            selected = isSelected,
            onSelectedChange = { isSelected = it },
        ) {
            Text(text = "Select")
        }
    }
}

private class BooleanProvider : PreviewParameterProvider<Boolean> {
    override val values = sequenceOf(true, false)
}
