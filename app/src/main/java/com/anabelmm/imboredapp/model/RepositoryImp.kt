package com.anabelmm.imboredapp.model

import com.anabelmm.imboredapp.model.db.CardDAO
import com.anabelmm.imboredapp.model.db.CardEntity
import javax.inject.Inject

/**
 * [Repository] manages queries and allows to use multiple backends.
 *
 * It implements the logic for deciding whether to fetch
 * data from the network or use results cached in the local database.
 */
interface Repository {
    suspend fun getActivityFromAPI(): ActivityCard?
    suspend fun getListActivityFromDB(): List<ActivityCard?>
    suspend fun insertActivityCardToDB(card: ActivityCard?)
    suspend fun deleteAllCardActivities()
}


class RepositoryImp @Inject constructor(
    private val dao: CardDAO,
    private val api: APIClient
) :
    Repository {

    override suspend fun getActivityFromAPI(): ActivityCard? = api.response()

    override suspend fun getListActivityFromDB(): List<ActivityCard?> {
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

    override suspend fun insertActivityCardToDB(card: ActivityCard?) {
        val cardEntity =
            card?.let {
                CardEntity(
                    key = it.key,
                    activity = card.activity,
                    accessibility = card.accessibility,
                    type = card.type,
                    participants = card.participants,
                    price = card.price,
                    link = card.link,
                )
            }
        if (cardEntity != null) {
            dao.insertCard(cardEntity)
        }
    }

    override suspend fun deleteAllCardActivities() = dao.deleteAllCards()

}
