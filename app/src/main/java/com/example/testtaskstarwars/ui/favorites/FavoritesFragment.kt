package com.example.testtaskstarwars.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testtaskstarwars.databinding.FragmentFavoritiesBinding
import com.example.testtaskstarwars.domain.models.UiState
import com.example.testtaskstarwars.ui.adapters.MainPageAdapter
import com.example.testtaskstarwars.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoritesFragment : BaseFragment() {

    private var _binding: FragmentFavoritiesBinding? = null
    private val viewModel: FavoritesViewModel by viewModels()

    private val adapter by lazy {
        MainPageAdapter(viewModel, lifecycleScope)
    }

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFavoritiesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = binding.rvFavorites
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiState.collect { uiState ->
                render(uiState)
            }

        }
    }


    override fun render(uiState: UiState) {
        when (uiState) {
            is UiState.Loading -> {
                showProgressBar()
            }

            is UiState.Success -> {
                adapter.submitList(uiState.dataList)
                hideProgressBar()
            }

            is UiState.Error -> {
                adapter.submitList(emptyList())
                Toast.makeText(requireContext(), uiState.errorMessage, Toast.LENGTH_SHORT).show()
                hideProgressBar()
            }
        }
    }

    override fun showProgressBar() {
        binding.progressBarFav.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        binding.progressBarFav.visibility = View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}