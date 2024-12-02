package com.roys.unitconverterapp.view.compose.converter

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.roys.unitconverterapp.model.Conversion
import java.math.RoundingMode
import java.text.DecimalFormat

@Composable
fun TopComponent(
    list: List<Conversion>,
    selectedConversion : MutableState<Conversion?>,
    inputText : MutableState<String>,
    typedValue: MutableState<String>,
    isLandscape: Boolean,
    save: (String,String) -> Unit
) {

    var toSave by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        ConversionMenu(list, isLandscape = isLandscape){
            selectedConversion.value = it
            typedValue.value = "0.0"
            inputText.value = ""
        }

        selectedConversion.value?.let {
            InputBlock(it, inputText, isLandscape = isLandscape){ input->
                typedValue.value = input
                toSave = true
            }
        }

        if(typedValue.value != "0.0"){
            val input = typedValue.value.toDouble()
            val multiply = selectedConversion.value!!.multiplyBy
            val result = input * multiply
            val df  = DecimalFormat("#.####")
            df.roundingMode = RoundingMode.DOWN
            val roundResult = df.format(result)

            val message1 = "${typedValue.value} ${selectedConversion.value!!.convertFrom} is equal to"
            val message2 = "$roundResult ${selectedConversion.value!!.convertTo}"
            if(toSave){
                save(message1, message2)
                toSave = false
            }
            ResultBlock(message1,message2)
        }
    }
}