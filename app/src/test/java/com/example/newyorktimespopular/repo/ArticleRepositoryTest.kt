package com.example.newyorktimespopular.repo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.newyorktimespopular.api.NYTArticlesClient
import com.example.newyorktimespopular.base.BaseUTTest
import com.example.newyorktimespopular.configureTestAppComponent
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import java.net.HttpURLConnection

class ArticleRepositoryTest:BaseUTTest(){
    //Target
    private lateinit var mRepo: ArticleRepository
    //Inject api service created with koin
    val mAPIService : NYTArticlesClient by inject()
    //Inject Mockwebserver created with koin
    val mockWebServer : MockWebServer by inject()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    val mCount =20

    @Before
    fun start(){
        super.setUp()

        startKoin{ modules(configureTestAppComponent(getMockWebServerUrl()))}
    }

    @Test
    fun test_get_articles_retrieves_expected_data() =  runBlocking {

        mockNetworkResponseWithFileContent("success_resp_list.json", HttpURLConnection.HTTP_OK)
        mRepo = ArticleRepository()

        val dataReceived = mRepo.getArticles("1")

        assertNotNull(dataReceived)
        assertEquals(dataReceived!!.num_results, mCount)

    }
    @Test
    fun test_get_articles_retrieves_expected_null() =  runBlocking {

        mockNetworkResponseWithNUll( "success_with_empty.json",HttpURLConnection.HTTP_OK)
        mRepo = ArticleRepository()

        val dataReceived = mRepo.getArticles("1")

        assertNull(dataReceived?.results)


    }
}