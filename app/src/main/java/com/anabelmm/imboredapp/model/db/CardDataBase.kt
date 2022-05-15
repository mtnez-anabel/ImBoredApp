package com.anabelmm.imboredapp.model.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * [CardDataBase] represents the Room database that stores the activities and uses [CardDAO]
 * to issue the queries.
 */
@Database(entities = [CardEntity::class], version = 1)
abstract class CardDataBase : RoomDatabase() {
    companion object {
        @Volatile
        private var INSTANCE: CardDataBase? = null

        // It returns a Singleton CardDataBase
        fun getDatabase(context: Context): CardDataBase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CardDataBase::class.java,
                    "card_database"
                )
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}