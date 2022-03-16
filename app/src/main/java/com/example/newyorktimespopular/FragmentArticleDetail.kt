package com.example.newyorktimespopular

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.newyorktimespopular.databinding.FragmentArticleDetailBinding
import com.example.newyorktimespopular.viewmodel.ArticleViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentArticleDetail : BaseFragment() {

    private var _binding: FragmentArticleDetailBinding? = null
    private val articleViewModel: ArticleViewModel by sharedViewModel()


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentArticleDetailBinding.inflate(inflater, container, false)
        return super.onCreateBindingView(inflater, container, _binding!!.root)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var imageUrl =
            "https://www.industry.gov.au/sites/default/files/August%202018/image/news-placeholder-738.png"
        if (articleViewModel.article!!.media.isNotEmpty() && articleViewModel.article!!.media[0].media_metadata != null && articleViewModel.article!!.media[0].media_metadata!!.isNotEmpty()) {
            imageUrl = articleViewModel.article!!.media[0].media_metadata!![2].url.toString()
        }
        Glide
            .with(requireContext())
            .load(imageUrl)
            .centerCrop()
            .into(binding.imageView)
        binding.title.text = articleViewModel.article!!.title
        binding.byWhom.text = articleViewModel.article!!.byline
        binding.descriptionTv.text = articleViewModel.article!!.abstract
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}