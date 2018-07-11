package com.example.cld.youtueclient.searchScreen

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.cld.youtueclient.dataLayer.DaggerDataComponent
import com.example.cld.youtueclient.dataLayer.SearchListItem
import com.example.cld.youtueclient.dataLayer.YouTubeApi
import com.example.cld.youtueclient.dataLayer.YouTubeRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import javax.inject.Inject

@InjectViewState
class SearchPresenter: MvpPresenter<SearchView>() {

    @Inject
    lateinit var youTubeRepository: YouTubeRepository

    init {
        DaggerDataComponent.builder()
                .build()
                .inject(this)
    }

    fun search(queryText: String) {
        youTubeRepository.searchVideo(queryText)
                .subscribe {viewState.updateList(it.asList().toMutableList())}
    }

    fun playVideo(item : SearchListItem){
        viewState.playVideo(item)
    }
}