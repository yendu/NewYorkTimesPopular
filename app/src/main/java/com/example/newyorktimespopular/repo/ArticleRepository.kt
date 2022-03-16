package com.example.newyorktimespopular.repo

import com.example.newyorktimespopular.api.NYTArticlesClient
import com.example.newyorktimespopular.model.Articles
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class ArticleRepository : KoinComponent {
    private val nytArticlesClient: NYTArticlesClient by inject()
    suspend fun getArticles(days: String?): Articles? {
        return withContext(Dispatchers.IO) {
            nytArticlesClient.getArticles(days)
        }
    }

}