package com.anabelmm.imboredapp.model.db

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * [CardDataBase] represents the Room database that stores the activities and uses [CardDAO]
 * to issue the queries.
 */
@Database(entities = [CardEntity::class], version = 1)
abstract class CardDataBase : RoomDatabase() {
    abstract fun dao(): CardDAO
}