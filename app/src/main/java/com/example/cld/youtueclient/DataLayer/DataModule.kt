package com.example.cld.youtueclient.dataLayer

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
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
                .registerTypeAdapter(Array<Comment>::class.java,CommentDeserializer())
                .registerTypeAdapter(Array<Channel>::class.java,ChannelDeserializer())
                .create()
    }

    @Singleton
    @Provides
    fun getGsonConverterFactory(gson: Gson) : GsonConverterFactory {
        return GsonConverterFactory.create(gson)
    }

    @Singleton
    @Provides
    fun getRxJava2CallAdapterFactory() : RxJava2CallAdapterFactory{
        return RxJava2CallAdapterFactory.create()
    }

    @Singleton
    @Provides
    fun getRetrofit(
            gsonConverterFactory: GsonConverterFactory,
            rxJava2CallAdapterFactory: RxJava2CallAdapterFactory
    ): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(rxJava2CallAdapterFactory)
                .addConverterFactory(gsonConverterFactory)
                .build()
    }


    @Singleton
    @Provides
    fun getYoutubeApi(retrofit: Retrofit): YouTubeApi {
        return retrofit.create(YouTubeApi::class.java)
    }

    @Singleton
    @Provides
    fun getYouTubeRepository(youTubeApi: YouTubeApi): YouTubeRepository {
        return object : YouTubeRepository {
            override fun getSubscriptionsForChanel(channelId: String, accessToken: String): Observable<Array<Channel>> {
                return youTubeApi.getSubscriptionsForChanel(channelId = channelId,accessToken = accessToken)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
            }

            override fun getComments(videoId: String): Observable<Array<Comment>> {
                return youTubeApi.getComments(videoId = videoId)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
            }


            override fun searchVideo(queryText: String): Observable<Array<SearchListItem>> {
                return youTubeApi.searchVideo(queryText = queryText)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
            }
        }
    }

}