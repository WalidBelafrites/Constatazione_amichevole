package com.example.constatazione_amichevole.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Macchina::class, Constatazione::class, Profilo::class],
    version = 1
)


abstract class UserDatabase : RoomDatabase() {

    abstract val macchinaDao: MacchinaDao
    abstract val constatazioneDao: ConstatazioneDao
    abstract val profiloDao: ProfiloDao

    companion object {
        @Volatile
        private var INSTANCE: UserDatabase? = null

        fun getDatabase(context: Context): UserDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "user_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }

}