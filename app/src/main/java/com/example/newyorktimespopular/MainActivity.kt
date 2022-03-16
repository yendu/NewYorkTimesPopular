package com.example.newyorktimespopular

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import com.example.newyorktimespopular.databinding.ActivityMainBinding
import com.example.newyorktimespopular.viewmodel.ArticleViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(){

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private val articleViewModel: ArticleViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val navController = findNavController(R.id.nav_host_fragment_content_base)
        appBarConfiguration = AppBarConfiguration(navController.graph)

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_base)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}