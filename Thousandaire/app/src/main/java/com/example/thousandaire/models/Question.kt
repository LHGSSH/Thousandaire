package com.example.thousandaire.models

import androidx.annotation.StringRes

data class Question(@StringRes val questionTextId: Int, val answerId: Int, val choiceIds: MutableList<Int>, val amount: Int)