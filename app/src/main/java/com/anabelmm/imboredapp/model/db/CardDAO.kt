package com.anabelmm.imboredapp.model.db

import androidx.room.*

@Dao
interface CardDAO {
    @Query("SELECT * FROM card_table")
    suspend fun getAllCards(): List<CardEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAllCards(cards: List<CardEntity>)

    @Query("DELETE FROM card_table")
    suspend fun deleteAllCards()
}