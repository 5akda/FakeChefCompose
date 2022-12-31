package iam5akda.fakechef_compose.home.view.menu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import iam5akda.fakechef_compose.home.repository.HomeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    private val repository: HomeRepository
) : ViewModel() {

    init {
        playAnimation()
    }

    private val mutableAnimation = MutableStateFlow("")
    val animation = mutableAnimation.asStateFlow()

    private fun playAnimation() {
        viewModelScope.launch {
            repository.getAnimatedString()
                .collect {
                    mutableAnimation.emit(it)
                }
        }
    }
}