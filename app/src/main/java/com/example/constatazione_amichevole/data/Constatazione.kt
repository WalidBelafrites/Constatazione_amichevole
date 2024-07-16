package com.example.constatazione_amichevole.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "constatazione")
data class Constatazione (
    val name: String,
    val surname: String,
    val phone_number: String,
    val assurance: String,
    val accident_description: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val macchinaId: Int? = null,
    val macchinaNome: String? = "",
    val nameB: String = "",
    val surnameB: String = "",
    val phone_numberB: String = "",
    val assuranceB: String = ""
)