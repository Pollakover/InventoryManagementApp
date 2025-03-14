package com.example.inventorymanagementapp.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel


class MainViewModel : ViewModel() {
    var searchButtonState by mutableStateOf(false)
        private set

    var searchFieldText by mutableStateOf(TextFieldValue(""))

    var focusRequester by mutableStateOf(FocusRequester())

    fun requestSearchFocus() {
        focusRequester.requestFocus()
    }

    fun changeButtonState() {
        searchButtonState = !searchButtonState
        searchFieldText = TextFieldValue("")
    }
}