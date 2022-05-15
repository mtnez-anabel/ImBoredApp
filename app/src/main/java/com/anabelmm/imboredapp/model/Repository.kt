package com.anabelmm.imboredapp.model

import com.anabelmm.imboredapp.model.db.CardDAO
import com.anabelmm.imboredapp.model.db.CardEntity

/**
 * [Repository] manages queries and allows to use multiple backends.
 *
 * It implements the logic for deciding whether to fetch
 * data from the network or use results cached in the local database.
 */
class Repository(private val dao: CardDAO) {
    private val api = APIClient()

    suspend fun getActivityFromAPI(): ActivityCard? = api.getActivity()

    suspend fun getListActivityFromDB(): List<ActivityCard?> {
        val listEntities = dao.getAllCards()
        val listActivityCards = mutableListOf<ActivityCard?>()
        for (i in listEntities.indices) {
            val card = ActivityCard(
                activity = listEntities[i].activity,
                accessibility = listEntities[i].accessibility,
                type = listEntities[i].type,
                participants = listEntities[i].participants,
                price = listEntities[i].price,
                link = listEntities[i].link,
                key = listEntities[i].key
            )
            listActivityCards.add(card)
        }
        return listActivityCards
    }

    suspend fun setListActivityToDB(list: List<ActivityCard?>) {
        val listEntities = mutableListOf<CardEntity>()
        for (i in list.indices) {
            val cardEntity = list[i]?.let {
                CardEntity(
                    key = it.key,
                    activity = it.activity,
                    accessibility = it.accessibility,
                    type = it.type,
                    participants = it.participants,
                    price = it.price,
                    link = it.link,
                )
            }
            if (cardEntity != null) listEntities.add(cardEntity)
        }
        dao.insertAllCards(listEntities)
    }

    suspend fun deleteAllCardActivities() = dao.deleteAllCards()
}
