package com.anabelmm.imboredapp.view

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
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
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.WithFragmentBindings
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
@WithFragmentBindings
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private var binding
        get() = _binding!!
        set(value) {
            _binding = value
        }

    private val homeViewModel by viewModels<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        homeViewModel.isGifVisible.observe(viewLifecycleOwner) {
            binding.gifCardView.isVisible = it    //It will be visible just the first time
            binding.cardView.isVisible = !it
        }
        binding.activityButton.setOnClickListener {
            if (!isNetworkAvailable(requireActivity().application)) {
                binding.gifCardView.isVisible = true
                Snackbar.make(
                    binding.activityButton,
                    "Please, check your internet connection!",
                    Snackbar.LENGTH_SHORT
                ).setAnchorView(binding.activityButton.id)
                    .show()
                return@setOnClickListener
            }
            getNewActivity(homeViewModel)
        }

        homeViewModel.homeModel.observe(viewLifecycleOwner) {
            //Update ActivityCard
            Log.i("Card: ", it.toString())
            if (it != null) {
                binding.textActivity.text = it.activity
                binding.textAccessibility.text = it.accessibility.toString()
                binding.textType.text = it.type
                binding.textParticipants.text = it.participants.toString()
                binding.textPrice.text = it.price.toString()
                binding.textKey.text = it.key.toString()
                val link = it.link
                if (link != "") {
                    binding.textLink.text = link
                    binding.textLink.setOnClickListener {
                        startActivity(
                            Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse(link)
                            )
                        )
                    }
                } else {
                    binding.textLink.text = getString(R.string.no_link_required)
                    binding.textLink.setOnClickListener(null)
                }
            } else {
                binding.gifCardView.isVisible = true
                Snackbar.make(
                    binding.activityButton,
                    "Sorry, the server is not available!",
                    Snackbar.LENGTH_SHORT
                ).setAnchorView(binding.activityButton.id)
                    .show()
            }
        }

        return binding.root
    }

    private fun getNewActivity(homeViewModel: HomeViewModel) {
        lifecycleScope.launch {
            homeViewModel.getActivity()
            if (homeViewModel.isGifVisible.value == true) {
                delay(1000)
                homeViewModel.isGifVisible.postValue(false)
            }
        }

    }

    private fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                return true
            }
        }
        return false
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}