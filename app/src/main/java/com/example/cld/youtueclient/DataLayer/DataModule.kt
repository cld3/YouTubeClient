package com.example.cld.youtueclient.DataLayer

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class DataModule {

    @Singleton
    @Provides
    fun getGson(): Gson {
        return GsonBuilder()
                .registerTypeAdapter(Array<SearchListItem>::class.java, SearchListItemDeserializer())
                .create()
    }

    @Singleton
    @Provides
    fun getRetrofit(gson: Gson): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
    }


    @Singleton
    @Provides
    fun getYoutubeApi(retrofit: Retrofit): YouTubeApi {
        return retrofit.create(YouTubeApi::class.java)
    }

}