package com.preprod.tippp.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import com.preprod.tippp.calculateTotalPerPersonBill
import com.preprod.tippp.wigets.InputField
import com.preprod.tippp.wigets.TipsCalculator
import com.preprod.tippp.wigets.TotalAmountSplitter

@ExperimentalComposeUiApi
@Composable
fun BillForm(
    modifier: Modifier = Modifier,
    amountPerPerson:MutableState<Double>
) {
    val usersInput = remember {
        mutableStateOf("")
    }

    val personsPerBill = remember {
        mutableStateOf(1)
    }

    val tipsPercentage = remember {
        mutableStateOf(0)
    }

    val isInputValid = remember(usersInput.value) {
        usersInput.value.trim().isNotEmpty()
    }

    val keyboardController = LocalSoftwareKeyboardController.current

    Surface(
        modifier = modifier
            .padding(start = 10.dp, end = 10.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(corner = CornerSize(8.dp)),
        border = BorderStroke(width = 1.dp, color = Color.LightGray)
    ) {
        Column(
            modifier = Modifier.padding(6.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            InputField(
                valueState = usersInput,
                labelId = "Enter bill amount",
                enabled = true,
                isSingleLine = true,
                onAction = KeyboardActions {
                    if (!isInputValid) return@KeyboardActions
                    amountPerPerson.value = calculateTotalPerPersonBill(
                        totalAmount = usersInput.value.toDouble(),
                        tipsPercentage = tipsPercentage.value,
                        splitBy = personsPerBill.value
                    )
                    keyboardController?.hide()
                })
            TotalAmountSplitter(personsPerBill = personsPerBill) {
                amountPerPerson.value = calculateTotalPerPersonBill(
                    totalAmount = usersInput.value.toDouble(),
                    tipsPercentage = tipsPercentage.value,
                    splitBy = personsPerBill.value
                )
            }
            TipsCalculator(tipsPercentage = tipsPercentage,
                billAmount = if (isInputValid) usersInput.value.trim().toDouble() else 0.0
            ) {
                amountPerPerson.value = calculateTotalPerPersonBill(
                    totalAmount = usersInput.value.toDouble(),
                    tipsPercentage = tipsPercentage.value,
                    splitBy = personsPerBill.value
                )
            }
        }
    }
}

