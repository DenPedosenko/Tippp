package com.preprod.tippp.wigets

import androidx.compose.foundation.layout.*
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

@Composable
fun TipsCalculator(
    tipsPercentage:MutableState<Int>,
    billAmount: Double,
    onTipsPercentageChange: () -> Unit
) {
    val sliderPosition = remember {
        mutableStateOf(0f)
    }

    Row(
        modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = "Tips:",
            modifier = Modifier.align(alignment = Alignment.CenterVertically)
        )
        Spacer(modifier = Modifier.width(200.dp))
        Text(
            text = "$${(billAmount * tipsPercentage.value).roundToInt() / 100.0}",
            modifier = Modifier.align(alignment = Alignment.CenterVertically)
        )
    }
    Column(
        modifier = Modifier
            .padding(horizontal = 3.dp, vertical = 12.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "${tipsPercentage.value} %")

        Spacer(modifier = Modifier.height(12.dp))

        Slider(
            modifier = Modifier.padding(start = 16.dp, end = 16.dp),
            value = sliderPosition.value, onValueChange = { newValue ->
                sliderPosition.value = newValue
                tipsPercentage.value = newValue.toInt()
                onTipsPercentageChange()
            },
            valueRange = 0.0f.rangeTo(25f)
        )
    }
}