package com.example.testtaskstarwars.ui.base

import androidx.fragment.app.Fragment
import com.example.testtaskstarwars.domain.models.UiState

open class BaseFragment : Fragment() {

    open fun showProgressBar() {
    }

    open fun hideProgressBar() {
    }

    open fun render(uiState: UiState){

    }
}