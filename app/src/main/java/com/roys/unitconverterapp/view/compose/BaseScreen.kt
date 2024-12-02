package com.roys.unitconverterapp.view.compose

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.roys.unitconverterapp.view.compose.converter.TopComponent
import com.roys.unitconverterapp.view.compose.history.HistoryComponent
import com.roys.unitconverterapp.viewmodel.ConverterViewModel
import com.roys.unitconverterapp.viewmodel.ConverterViewModelFactory

@Composable
fun BaseScreen(
    factory: ConverterViewModelFactory,
    modifier: Modifier = Modifier,
    converterViewModel: ConverterViewModel = viewModel(factory = factory)
) {
    val list = converterViewModel.getConversion()
    val historyList = converterViewModel.results.collectAsState(initial = emptyList())

    val configuration = LocalConfiguration.current
    var isLandscape by remember { mutableStateOf(false) }

    when(configuration.orientation){
        Configuration.ORIENTATION_LANDSCAPE ->{
            isLandscape = true
            Row(
                modifier = modifier.padding(30.dp).fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                TopComponent(
                    list,
                    converterViewModel.selectedConversion,
                    converterViewModel.inputText,
                    converterViewModel.typedValue,
                    isLandscape
                ){message1, message2 ->
                    converterViewModel.addResult(message1, message2)
                }
                Spacer(modifier.width(10.dp))
                HistoryComponent(
                    historyList,
                    {item->
                        converterViewModel.deleteResult(item)
                    },
                    {
                        converterViewModel.deleteAllResults()
                    }
                )
            }
        } else -> {
            isLandscape = false
            Column(
                modifier = modifier.padding(30.dp),
            ) {
                TopComponent(
                    list,
                    converterViewModel.selectedConversion,
                    converterViewModel.inputText,
                    converterViewModel.typedValue,
                    isLandscape
                ){message1, message2 ->
                    converterViewModel.addResult(message1, message2)
                }
                Spacer(modifier.height(20.dp))
                HistoryComponent(
                    historyList,
                    {item->
                        converterViewModel.deleteResult(item)
                    },
                    {
                        converterViewModel.deleteAllResults()
                    }
                )
            }
        }
    }
}