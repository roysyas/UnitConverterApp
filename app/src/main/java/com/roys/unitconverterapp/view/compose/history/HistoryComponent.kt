package com.roys.unitconverterapp.view.compose.history

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.roys.unitconverterapp.model.database.ConversionResult

@Composable
fun HistoryComponent(
    list: State<List<ConversionResult>>,
    onCloseTask: (ConversionResult) ->Unit,
    onDeleteAll: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column {
        if(list.value.isNotEmpty()){
            Row(
                modifier = modifier.fillMaxWidth()
                    .padding(bottom = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "History",
                    color = Color.Gray
                )
                OutlinedButton(onClick = {onDeleteAll()}) {
                    Text(
                        text = "Clear all",
                        color = Color.Gray
                    )
                }
            }
        }

        HistoryList(
            list = list,
            onCloseTask = {item->
                onCloseTask(item)
            }
        )
    }
}