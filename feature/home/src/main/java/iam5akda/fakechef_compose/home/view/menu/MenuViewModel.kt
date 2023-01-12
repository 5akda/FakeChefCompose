package iam5akda.fakechef_compose.home.view.menu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import iam5akda.fakechef_compose.home.repository.HomeRepository
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    private val repository: HomeRepository
) : ViewModel() {

    val animationStateFlow: StateFlow<String> = appNameAnimationFlow().stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = ""
    )

    private fun appNameAnimationFlow(): Flow<String> {
        return repository.getAnimatedString(ANIMATION_REPEAT)
    }

    companion object {
        private const val ANIMATION_REPEAT = 3
    }
}