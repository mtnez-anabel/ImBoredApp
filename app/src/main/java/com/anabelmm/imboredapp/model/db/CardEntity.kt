package com.anabelmm.imboredapp.model.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "card_table")
data class CardEntity(
    @PrimaryKey
    @ColumnInfo(name = "key") val key: Int,
    @ColumnInfo(name = "activity") val activity: String,
    @ColumnInfo(name = "accessibility") val accessibility: Double,
    @ColumnInfo(name = "type") val type: String,
    @ColumnInfo(name = "participants") val participants: Int,
    @ColumnInfo(name = "price") val price: Double,
    @ColumnInfo(name = "link") val link: String,

    )
