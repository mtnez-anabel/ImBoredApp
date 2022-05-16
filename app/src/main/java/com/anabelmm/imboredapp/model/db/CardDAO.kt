package com.anabelmm.imboredapp.model.db

import androidx.room.*

/**
 * DAO (Data Access Object) specifies SQL queries and associates them with method calls.
 */
@Dao
interface CardDAO {
    @Query("SELECT * FROM card_table")
    suspend fun getAllCards(): List<CardEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCard(card: CardEntity)

    @Query("DELETE FROM card_table")
    suspend fun deleteAllCards()


}