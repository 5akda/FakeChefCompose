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

    private val mutableAnimation = MutableStateFlow("")
    val animation = mutableAnimation.asStateFlow()

    fun playAnimation() {
        if (animation.value.isEmpty()) {
            viewModelScope.launch {
                repository.getAnimatedString()
                    .collect {
                        mutableAnimation.emit(it)
                    }
            }
        }
    }
}