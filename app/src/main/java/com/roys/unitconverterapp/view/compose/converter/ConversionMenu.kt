package com.roys.unitconverterapp.view.compose.converter

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.roys.unitconverterapp.model.Conversion

@Composable
fun ConversionMenu(
    list: List<Conversion>,
    modifier: Modifier = Modifier,
    isLandscape: Boolean,
    convert: (Conversion) ->Unit
){

    var displayingText by rememberSaveable { mutableStateOf("Select the conversion type") }
    var textFieldSize by remember { mutableStateOf(Size.Zero) } //to assign Dropdown the same width as TextField
    var expanded by remember { mutableStateOf(false) }

    val icon = if (expanded){
        Icons.Filled.KeyboardArrowUp
    }else{
        Icons.Filled.KeyboardArrowDown
    }

    Column {
        if(isLandscape){
            OutlinedTextField(
                value = displayingText,
                onValueChange = {displayingText = it},
                textStyle = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
                modifier = modifier
                    .onGloballyPositioned { cordinates ->
                        textFieldSize = cordinates.size.toSize()
                    },
                label = { Text(text = "Conversion type") },
                trailingIcon = {
                    Icon(
                        icon,
                        contentDescription = "icons",
                        modifier.clickable { expanded = !expanded }
                    )
                },
                readOnly = true
            )
        }else{
            OutlinedTextField(
                value = displayingText,
                onValueChange = {displayingText = it},
                textStyle = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
                modifier = modifier.fillMaxWidth()
                    .onGloballyPositioned { cordinates ->
                        textFieldSize = cordinates.size.toSize()
                    },
                label = { Text(text = "Conversion type") },
                trailingIcon = {
                    Icon(
                        icon,
                        contentDescription = "icons",
                        modifier.clickable { expanded = !expanded }
                    )
                },
                readOnly = true
            )
        }


        DropdownMenu(
            expanded = expanded,
            onDismissRequest = {expanded = false},
            modifier = modifier.width(with(LocalDensity.current){textFieldSize.width.toDp()})
        ) {
            list.forEach { conversion ->
                DropdownMenuItem(
                    onClick = {
                        displayingText = conversion.description
                        expanded = false
                        convert(conversion)
                    },
                    text = {
                        Text(
                            text = conversion.description,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                )
            }
        }
    }
}