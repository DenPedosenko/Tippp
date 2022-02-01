package com.preprod.tippp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.preprod.tippp.components.BillForm
import com.preprod.tippp.components.AmountPerPerson


@ExperimentalComposeUiApi
@Composable
fun MainScreen() {
    Column {
        val totalPerPerson = remember {
            mutableStateOf(0.0)
        }
        AmountPerPerson(totalPerPerson.value)
        Spacer(modifier = Modifier.height(4.dp))
        BillForm(amountPerPerson = totalPerPerson)
    }
}

