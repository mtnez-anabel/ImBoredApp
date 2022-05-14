package com.anabelmm.imboredapp.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.anabelmm.imboredapp.R
import com.anabelmm.imboredapp.databinding.FragmentHomeBinding
import com.anabelmm.imboredapp.view_model.HomeViewModel
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!
    private val factory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return this@HomeFragment.context?.let { HomeViewModel(it, lifecycleScope) } as T
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this, factory)[HomeViewModel::class.java]
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        _binding!!.activityButton.setOnClickListener {
            getActivity(homeViewModel)
            // _binding!!.gifCardView.isVisible = homeViewModel.isVisible.value!!
        }
        return root
    }


    private fun getActivity(homeViewModel: HomeViewModel) {
        lifecycleScope.launch {
            homeViewModel.getActivity()
            homeViewModel.homeModel.observe(viewLifecycleOwner, Observer {
                Log.i("Card..........", it.toString())
                if (it != null) {
                    _binding!!.textActivity.text = it.activity
                    _binding!!.textAccessibility.text = "${getString(R.string.accessibility)}  ${it.accessibility}"
                    _binding!!.textType.text = "${getString(R.string.type)}  ${it.type}"
                    _binding!!.textParticipants.text = "${getString(R.string.participants)}  ${it.participants}"
                    _binding!!.textPrice.text = "${getString(R.string.price)}  ${it.price}"
                    _binding!!.textKey.text = "${getString(R.string.key)}  ${it.key}"
                    val link = it.link
                    if (link != "")
                        _binding!!.textLink.text = "${getString(R.string.link)}  ${it.link}"
                    else
                        _binding!!.textLink.text = getString(R.string.no_link_required)
                }
                _binding!!.cardView.isVisible = true

            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}