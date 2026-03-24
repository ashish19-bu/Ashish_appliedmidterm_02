package com.example.ashish_appliedmidterm2

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

/**
 * ViewModel for the Counter screen.
 * This class holds the UI state and handles the business logic (incrementing).
 */
class CounterViewModel : ViewModel() {

    // 1. The counter state is stored inside the ViewModel
    // We use a private MutableState to encapsulate the state and prevent direct modification from the UI.
    private val _count = mutableStateOf(0)
    
    // We expose an immutable State to the UI to follow Unidirectional Data Flow (UDF).
    val count: State<Int> = _count

    // 3. ViewModel function called by the UI
    fun onIncrement() {
        _count.value++
    }
}
