package com.preprod.tippp.wigets

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TotalAmountSplitter(personsPerBill:MutableState<Int>,
    onPersonsCountChange: () -> Unit
) {
    Row(
        modifier = Modifier.padding(horizontal = 3.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        Text(
            text = "Split",
            modifier = Modifier.align(alignment = Alignment.CenterVertically)
        )
        Spacer(modifier = Modifier.width(120.dp))
        Row(
            modifier = Modifier.padding(horizontal = 3.dp),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            RoundIconButton(imageVector = Icons.Default.Remove, onClick = {
                if (personsPerBill.value > 1) {
                    personsPerBill.value -= 1
                    onPersonsCountChange()
                }
            })
            Text(
                text = personsPerBill.value.toString(),
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(start = 9.dp, end = 9.dp)
            )
            RoundIconButton(imageVector = Icons.Default.Add, onClick = {
                personsPerBill.value += 1
                onPersonsCountChange()
            })
        }
    }
}