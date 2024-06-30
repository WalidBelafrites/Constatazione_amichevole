package com.example.constatazione_amichevole.data

import androidx.compose.ui.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Macchina(
    val name: String,
    val color: Color,
    val year: Int,
    @PrimaryKey
    val id: Int? = null
    )
