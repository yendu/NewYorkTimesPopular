package com.example.newyorktimespopular

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newyorktimespopular.databinding.FragmentArticlelistBinding
import com.example.newyorktimespopular.model.Article
import com.example.newyorktimespopular.presentation.ArticleClick
import com.example.newyorktimespopular.presentation.ArticlesAdapter
import com.example.newyorktimespopular.viewmodel.ArticleViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class ArticleListFragment : BaseFragment(), ArticleClick {

    private var _binding: FragmentArticlelistBinding? = null
    private val articleViewModel: ArticleViewModel by sharedViewModel()

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentArticlelistBinding.inflate(LayoutInflater.from(requireContext()))
        return super.onCreateBindingView(inflater, container, _binding!!.root)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showProgress(true)
        val layoutManager = LinearLayoutManager(requireContext())
        val dividerItemDecoration = DividerItemDecoration(
            requireContext(),
            LinearLayoutManager.VERTICAL,

            )


        dividerItemDecoration.setDrawable(
            ContextCompat.getDrawable(requireContext(), R.drawable.divider)!!
        )
        binding.recyclerView.addItemDecoration(dividerItemDecoration)
        binding.recyclerView.layoutManager = layoutManager
        val articlesAdapter = ArticlesAdapter(requireContext(), this)
        binding.recyclerView.adapter = articlesAdapter

        articleViewModel.listRepos.observe(requireActivity(), {
            if (it != null) {
                showProgress(false)
                articlesAdapter.setArticles(it.results)
            } else {

                handleError()
            }
        })
    }

    private fun handleError() {
        showProgress(false)
        showNoConnectionIcon(true)
        showStatusText(true)
        setStatusText("Something went wrong. Try Again!!")
        showTryAgain(true)
        setTryAgainClickListener {
            showProgress(true)
            showNoConnectionIcon(false)
            showStatusText(false)
            setStatusText("Something went wrong. Try Again!!")
            showTryAgain(false)

            articleViewModel.getArticles()

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(article: Article) {
        articleViewModel.article = article
        findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
    }
}