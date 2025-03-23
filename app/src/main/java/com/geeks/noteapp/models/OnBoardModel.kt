package com.geeks.noteapp.models

import java.io.Serializable

data class OnBoardModel(
    val animation: Int,
    val title: String,
    val desc: String,
): Serializable