package com.example.newyorktimespopular
/**
 *
 * Main Koin DI component.
 * Helps to configure
 * 1) Mockwebserver
 * 2) Usecase
 * 3) Repository
 */
import MockWebServerDIPTest
import com.example.newyorktimespopular.di.configureNetworkModuleForTest
import com.example.newyorktimespopular.repo.ArticleRepository
import org.koin.dsl.module

fun configureTestAppComponent(baseApi: String)
        = listOf(
    MockWebServerDIPTest,
    configureNetworkModuleForTest(baseApi),
    module {
       factory {
       ArticleRepository() }}
    )

