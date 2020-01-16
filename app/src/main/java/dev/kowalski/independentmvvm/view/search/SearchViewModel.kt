package dev.kowalski.independentmvvm.view.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import dev.kowalski.independentmvvm.api.api.model.LyricsSearchResult
import dev.kowalski.independentmvvm.api.repository.LyricsSearchRepository

class SearchViewModel : ViewModel() {

    val lyricsSearchResult: MutableLiveData<LyricsSearchResult> = MutableLiveData()
    private val repository = LyricsSearchRepository()

    private val searchResultObserver = Observer<LyricsSearchResult> { result ->
        lyricsSearchResult.postValue(result)
    }

    init {
        repository.searchResult.observeForever(searchResultObserver)
    }

    fun getLyrics() {
        repository.getLyrics("Coldplay", "Adventure of a Lifetime")
    }

    override fun onCleared() {
        repository.searchResult.removeObserver(searchResultObserver)
        super.onCleared()
    }

}