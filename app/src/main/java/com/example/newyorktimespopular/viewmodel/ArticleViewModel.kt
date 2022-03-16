package com.example.newyorktimespopular.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newyorktimespopular.model.Article
import com.example.newyorktimespopular.model.Articles
import com.example.newyorktimespopular.repo.ArticleRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class ArticleViewModel(
    private val dispatcher: CoroutineDispatcher,
    private val articleRepository: ArticleRepository
) : ViewModel() {
    var listRepos: MutableLiveData<Articles?> = MutableLiveData()
    var article: Article? = null


    init {
        viewModelScope.launch(dispatcher) {
            listRepos.postValue(articleRepository.getArticles("1"))
        }

    }

    fun getArticles() {
        viewModelScope.launch(dispatcher) {
            listRepos.postValue(articleRepository.getArticles("1"))
        }

    }
}