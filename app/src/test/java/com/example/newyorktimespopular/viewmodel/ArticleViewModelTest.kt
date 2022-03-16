package com.example.newyorktimespopular.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.newyorktimespopular.api.NYTArticlesClient
import com.example.newyorktimespopular.base.BaseUTTest
import com.example.newyorktimespopular.configureTestAppComponent
import com.example.newyorktimespopular.repo.ArticleRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.*
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import java.net.HttpURLConnection
import kotlin.test.assertNull

class ArticleViewModelTest : BaseUTTest() {

    @ExperimentalCoroutinesApi
    private val testCoroutineDispatcher = TestCoroutineDispatcher()
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    lateinit var mArticleViewModel: ArticleViewModel
    val mAPIService : NYTArticlesClient by inject()
    //Inject Mockwebserver created with koin
    val mockWebServer : MockWebServer by inject()
    private  val articleRepository: ArticleRepository by inject()


    @Before
    fun start() {
        super.setUp()
        startKoin { modules(configureTestAppComponent(getMockWebServerUrl())) }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun test_article_view_model_data_populates_expected_value()=runBlocking {


        mockNetworkResponseWithFileContent("success_resp_list.json", HttpURLConnection.HTTP_OK)
        mArticleViewModel = ArticleViewModel(testCoroutineDispatcher,articleRepository)
        val value = mArticleViewModel.listRepos.getOrAwaitValue {  }
        assertNotNull(value)
        assertEquals(value!!.num_results, 20)

    }
    @ExperimentalCoroutinesApi
    @Test
    fun test_article_view_model_data_get_error_on_first_try_then_get_expected_value_on_second_call()=runBlocking {


        mockNetworkResponseWithFileContent("success_with_empty.json", HttpURLConnection.HTTP_OK)
        mArticleViewModel = ArticleViewModel(testCoroutineDispatcher,articleRepository)
        val value = mArticleViewModel.listRepos.getOrAwaitValue {  }
        assertNull(value?.results)
        mockNetworkResponseWithFileContent("success_resp_list.json", HttpURLConnection.HTTP_OK)
        mArticleViewModel.getArticles()
        val secondValue = mArticleViewModel.listRepos.getOrAwaitValue {  }
        assertNotNull(secondValue)


    }
    @ExperimentalCoroutinesApi
    @Test
    fun test_article_view_model_article_value_is_not_null()=runBlocking {

        mockNetworkResponseWithFileContent("success_resp_list.json", HttpURLConnection.HTTP_OK)
        mArticleViewModel = ArticleViewModel(testCoroutineDispatcher,articleRepository)
        mArticleViewModel.getArticles()
        val secondValue = mArticleViewModel.listRepos.getOrAwaitValue {  }
        assertNotNull(secondValue)
        mArticleViewModel.article=secondValue!!.results[0]
        assertNotNull(mArticleViewModel.article)


    }
}