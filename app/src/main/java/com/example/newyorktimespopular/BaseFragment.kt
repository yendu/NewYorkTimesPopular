package com.example.newyorktimespopular


import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.newyorktimespopular.databinding.FragmentBaseBinding
import androidx.annotation.Nullable


abstract class BaseFragment : Fragment() {



    private lateinit var _binding: FragmentBaseBinding

    open fun onCreateBindingView(
        inflater: LayoutInflater,
        @Nullable container: ViewGroup?,
        view: View?
    ): View {
        _binding = FragmentBaseBinding.inflate(inflater, container, false)
        _binding.fragmentContainer.addView(view)
        return _binding.root
    }

    open fun showProgress(visibility: Boolean) {
        _binding.progressBar.visibility = if (visibility) View.VISIBLE else View.GONE
    }


    open fun showNoConnectionIcon(visibility: Boolean) {
        _binding.noConnectionImage.visibility = if (visibility) View.VISIBLE else View.GONE
    }

    open fun showStatusText(visibility: Boolean) {
        _binding.statsText.visibility = if (visibility) View.VISIBLE else View.GONE
    }

    open fun setStatusText(text: String?) {
        _binding.statsText.text = text
    }
    open fun showTryAgain(visibility: Boolean) {
        _binding.tryAgain.visibility = if (visibility) View.VISIBLE else View.GONE

    }
    open fun setTryAgainClickListener(clickListener:View.OnClickListener){
        _binding.tryAgain.setOnClickListener(clickListener)
    }



}