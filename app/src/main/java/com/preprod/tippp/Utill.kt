package com.preprod.tippp

fun calculateTotalPerPersonBill(totalAmount: Double, tipsPercentage: Int, splitBy: Int) =
    (totalAmount + totalAmount / 100 * tipsPercentage) / splitBy