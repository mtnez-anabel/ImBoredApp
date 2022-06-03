package com.anabelmm.imboredapp.model

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.anabelmm.imboredapp.model.db.CardDAO
import com.anabelmm.imboredapp.model.db.CardEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private val card1 = CardEntity(
    key = 9924423,
    activity = "Learn how to make a website",
    accessibility = 0.3,
    type = "education",
    participants = 1,
    price = 0.1,
    link = ""
)
private val card2 = CardEntity(
    key = 8442249,
    activity = "Have a bonfire with your close friends",
    accessibility = 0.1,
    type = "social",
    participants = 4,
    price = 0.1,
    link = ""
)

@ExperimentalCoroutinesApi
class RepositoryTest {

    private val mockWebServer = MockWebServer()
    private lateinit var mockedResponse: String
    private lateinit var restDataSource: APIActivityService


    private lateinit var testAPIClient: APIClient


    private lateinit var testRepository: RepositoryImp

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun onBefore() {

        mockWebServer.start(8000)
        mockedResponse = MockResponseFileReader.content("api_response.json")
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(mockedResponse)
        )

        restDataSource = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(APIActivityService::class.java)
        testAPIClient = APIClient(restDataSource)
        testRepository = RepositoryImp(MockDao(), testAPIClient)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `getActivityFromAPI function is responding correctly when response is not null`() {

        val newActivityCard = runBlocking { testRepository.getActivityFromAPI()!! }
        println(newActivityCard.toString())
        assertEquals(newActivityCard.activity, "Learn the NATO phonetic alphabet")
        assertEquals(newActivityCard.accessibility, 0.0, 0.1)
        assertEquals(newActivityCard.type, "education")
        assertEquals(newActivityCard.participants, 1)
        assertEquals(newActivityCard.price, 0.0, 0.1)
        assertEquals(
            newActivityCard.link,
            "https://en.wikipedia.org/wiki/NATO_phonetic_alphabet"
        )
        assertEquals(newActivityCard.key, 6706598)
    }

    @Test
    fun `Activities on the DB are retrieved correctly`() {
        val activityCardList = runBlocking { testRepository.getListActivityFromDB() }
        assertEquals(2, activityCardList.size)
    }

    @Test
    fun `All activities are deleted correctly from DB`() {
        lateinit var activityCardList: List<ActivityCard?>
        runBlocking {
            testRepository.deleteAllCardActivities()
            activityCardList = testRepository.getListActivityFromDB()
        }
        assert(activityCardList.isEmpty())
    }

    @Test
    fun `A new CardActivity is inserted correctly into DB`() {
        val activityCard = ActivityCard(
            activity = "Learn the NATO phonetic alphabet",
            accessibility = 0.0,
            type = "education",
            participants = 1,
            price = 0.0,
            link = "https://en.wikipedia.org/wiki/NATO_phonetic_alphabet",
            key = 6706598
        )
        lateinit var activityCardList: List<ActivityCard?>
        runBlocking {
            testRepository.insertActivityCardToDB(activityCard)
            activityCardList = testRepository.getListActivityFromDB()
            assertEquals(3, activityCardList.size)
        }
    }

}

class MockDao : CardDAO {
    private val cards = mutableListOf(card1, card2)

    override suspend fun getAllCards(): List<CardEntity> = cards

    override suspend fun insertCard(card: CardEntity) {
        cards.add(card)
    }

    override suspend fun deleteAllCards() {
        cards.clear()
    }

}
