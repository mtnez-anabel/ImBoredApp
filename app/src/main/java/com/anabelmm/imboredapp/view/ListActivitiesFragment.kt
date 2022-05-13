package com.anabelmm.imboredapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.anabelmm.imboredapp.databinding.FragmentListActivitiesBinding
import com.anabelmm.imboredapp.view_model.ListActivitiesViewModel

class ListActivitiesFragment : Fragment() {

    private var _binding: FragmentListActivitiesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val listActivitiesViewModel =
            ViewModelProvider(this)[ListActivitiesViewModel::class.java]

        _binding = FragmentListActivitiesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textGallery
        listActivitiesViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}