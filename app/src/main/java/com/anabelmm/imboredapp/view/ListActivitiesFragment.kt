package com.anabelmm.imboredapp.view

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.anabelmm.imboredapp.databinding.FragmentListActivitiesBinding
import com.anabelmm.imboredapp.model.ActivityCard
import com.anabelmm.imboredapp.model.Repository
import com.anabelmm.imboredapp.model.db.CardDataBase
import com.anabelmm.imboredapp.view_model.ListActivitiesViewModel
import com.anabelmm.imboredapp.view_model.ListActivitiesViewModelFactory
import kotlinx.coroutines.launch

class ListActivitiesFragment : Fragment() {

    private var _binding: FragmentListActivitiesBinding? = null
    private var binding
        get() = _binding!!
        set(value) {
            _binding = value
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListActivitiesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val dao =
            CardDataBase.getDatabase(requireActivity().application, lifecycleScope).dao()
        val repository = Repository(dao)

        val listActivitiesViewModel: ListActivitiesViewModel by viewModels {
            ListActivitiesViewModelFactory(repository)
        }

        lifecycleScope.launch {
            initRecyclerView(listActivitiesViewModel.getFromDB())
        }
        listActivitiesViewModel.listSize.observe(viewLifecycleOwner) {
            binding.button.setOnClickListener {
                lifecycleScope.launch {
                    //showAlertDialog(listActivitiesViewModel)
                    listActivitiesViewModel.deleteList()
                }
            }
            binding.button.isVisible = it > 0
        }

        listActivitiesViewModel.listActivitiesModel.observe(viewLifecycleOwner) {
            initRecyclerView(it)
        }
    }

    private fun initRecyclerView(listActivities: List<ActivityCard?>) {
        binding.listActivitiesRecyclerView.layoutManager =
            LinearLayoutManager(requireActivity().application)
        binding.listActivitiesRecyclerView.adapter = ActivitiesListAdapter(listActivities)
    }


//    private fun showAlertDialog(listActivitiesViewModel: ListActivitiesViewModel) {
//        val builder = AlertDialog.Builder(requireActivity().application)
//        with(builder)
//        {
//            setTitle("ALERT!!!")
//            setMessage("Are you sure you want to delete the list?")
//            setPositiveButton("YES") { dialog, _ ->
//                deleteList(listActivitiesViewModel)
//                dialog.cancel()
//            }
//            setNegativeButton("NO") { dialog, _ ->
//                dialog.cancel()
//            }
//            create()
//            show()
//        }
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}