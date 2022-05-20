package com.anabelmm.imboredapp.model.di

import android.content.Context
import androidx.room.Room
import com.anabelmm.imboredapp.model.APIActivityService
import com.anabelmm.imboredapp.model.db.CardDAO
import com.anabelmm.imboredapp.model.db.CardDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {


    /**
     * Builds a Retrofit object with a base URI and a converter factory to build a web service API.
     * The converter tells Retrofit what to do with the data it gets back from the web service.
     * It is using the default timeouts settings.
     */
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://www.boredapi.com/")
            .build()
    }

    /**
     * Instantiates and returns a singleton object that implements [APIActivityService].
     */
    @Singleton
    @Provides
    fun apiDataSource(retrofit: Retrofit): APIActivityService =
        retrofit.create(APIActivityService::class.java)

    @Singleton
    @Provides
    fun dbDataSource(@ApplicationContext context: Context): CardDataBase {
        return Room.databaseBuilder(context, CardDataBase::class.java, "card_database")
            .build()
    }

    @Singleton
    @Provides
    fun userDao(db: CardDataBase): CardDAO = db.dao()
}
