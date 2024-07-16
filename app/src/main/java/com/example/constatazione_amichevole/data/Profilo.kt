package com.example.constatazione_amichevole.data

import android.provider.ContactsContract.CommonDataKinds.Email
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date

@Entity(tableName = "profilo")
data class Profilo (
    val name: String,
    val surname: String,
    val email: String,
    val phone_number: String,
    val assurance: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null
)