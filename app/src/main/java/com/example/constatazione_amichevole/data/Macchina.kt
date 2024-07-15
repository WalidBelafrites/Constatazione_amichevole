package com.example.constatazione_amichevole.data


import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "macchine")
data class Macchina(
    val name: String,
    val color: String,
    val year: Int,
    @PrimaryKey
    val id: Int? = null
    )
