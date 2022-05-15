package com.anabelmm.imboredapp.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.anabelmm.imboredapp.R
import com.anabelmm.imboredapp.databinding.FragmentHomeBinding
import com.anabelmm.imboredapp.view_model.HomeViewModel
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private var binding
        get() = _binding!!
        set(value) {
            _binding = value
        }

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        homeViewModel.isGifVisible.observe(viewLifecycleOwner) {
            binding.gifCardView.isVisible = it    //It will be visible just the first time
            binding.cardView.isVisible = !it
        }
        binding.activityButton.setOnClickListener {
            getNewActivity(homeViewModel)
        }

        homeViewModel.homeModel.observe(viewLifecycleOwner) {
            //Update ActivityCard
            Log.i("Card..........", it.toString())
            if (it != null) {
                binding.textActivity.text = it.activity
                binding.textAccessibility.text =
                    "${getString(R.string.accessibility)}  ${it.accessibility}"
                binding.textType.text = "${getString(R.string.type)}  ${it.type}"
                binding.textParticipants.text =
                    "${getString(R.string.participants)}  ${it.participants}"
                binding.textPrice.text = "${getString(R.string.price)}  ${it.price}"
                binding.textKey.text = "${getString(R.string.key)}  ${it.key}"
                val link = it.link
                if (link != "")
                    binding.textLink.text = "${getString(R.string.link)}  ${it.link}"
                else
                    binding.textLink.text = getString(R.string.no_link_required)
            }
        }

        return root
    }

    private fun getNewActivity(homeViewModel: HomeViewModel) {
        lifecycleScope.launch {
            homeViewModel.getActivity()
            if (homeViewModel.isGifVisible.value == true)
                homeViewModel.isGifVisible.postValue(false)
            binding.cardView.isVisible = true
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}