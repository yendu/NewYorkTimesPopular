package com.example.newyorktimespopular


import android.app.Application
import com.example.newyorktimespopular.api.NYTArticlesClient
import com.example.newyorktimespopular.repo.ArticleRepository
import com.example.newyorktimespopular.viewmodel.ArticleViewModel
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit



val appModule = module {

    // single instance of ArticleRepository
    single{ ArticleRepository() }

    single {
        val okHttpClient= OkHttpClient.Builder()
            .readTimeout(20, TimeUnit.SECONDS)
            .connectTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
         Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://api.nytimes.com/svc/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()


    }
    factory{ get<Retrofit>().create(NYTArticlesClient::class.java) }
    viewModel { ArticleViewModel(Dispatchers.Main,get()) }
}
class MainApp:Application(){
    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidLogger(Level.ERROR)
            androidContext(this@MainApp)
            modules(appModule)
        }


    }
}