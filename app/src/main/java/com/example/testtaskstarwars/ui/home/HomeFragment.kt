package com.example.testtaskstarwars.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testtaskstarwars.databinding.FragmentHomeBinding
import com.example.testtaskstarwars.domain.models.UiState
import com.example.testtaskstarwars.ui.adapters.MainPageAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val viewModel: HomeViewModel by viewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val adapter = MainPageAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

//        val textView: TextView = binding.textHome
//        homeViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

//        viewLifecycleOwner.lifecycleScope.launch {
//            viewModel.people.collect { peopleList ->
//                peopleList?.let {
//                    adapter.submitList(it.map { MainPageItem.PeopleItem(it) })
//                }
//            }
//        }
//
//        viewModel.getPeople()

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getPeople.collect { peopleList ->
                    render(peopleList)
            }
        }

//        viewModel.getPeople()


    }

    private fun render(uiState: UiState) {
        when (uiState) {
            is UiState.Loading -> {
                binding.progressBar.visibility = View.VISIBLE

            }

            is UiState.Success -> {
                adapter.submitList(uiState.dataList)
                binding.progressBar.visibility = View.GONE
            }

            is UiState.Error -> {

                Toast.makeText(requireContext(), uiState.errorMessage, Toast.LENGTH_SHORT).show()

                binding.progressBar.visibility = View.GONE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}