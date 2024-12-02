package com.roys.unitconverterapp.view.compose.history

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import com.roys.unitconverterapp.model.database.ConversionResult

@Composable
fun HistoryList(
    list: State<List<ConversionResult>>,
    onCloseTask: (ConversionResult) -> Unit,
    modifier: Modifier = Modifier
){
    LazyColumn {
        items(
            items = list.value,
            key = {item ->  item.id}
        ){ item ->
            HistoryItem(
                message1 = item.message1,
                message2 = item.message2,
                onClose = {onCloseTask(item)}
            )
        }
    }
}