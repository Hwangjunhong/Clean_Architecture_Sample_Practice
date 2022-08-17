package com.hong.presentation.view

import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.hong.presentation.R
import com.hong.presentation.base.BaseActivity
import com.hong.presentation.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(
    R.layout.activity_main
) {
    private val viewModel: MainViewModel by viewModels()

    override fun init() {
        viewModel.state
            .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach { state ->
                if (state is MainState.SuccessGetUser) {
                    val sb = StringBuilder()
                    state.userEntity.forEach {
                        sb.append("${it.first_name} ${it.last_name} \n")
                    }
                    binding.tvUser.text = sb
                }
            }
            .catch { exception -> Timber.e(exception) }
            .launchIn(lifecycleScope)
    }

}