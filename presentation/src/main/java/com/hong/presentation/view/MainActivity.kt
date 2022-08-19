package com.hong.presentation.view

import androidx.activity.viewModels
import com.hong.base.presentation.BaseActivity
import com.hong.presentation.R
import com.hong.presentation.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(
    R.layout.activity_main
) {
    private val viewModel: MainViewModel by viewModels()

    override fun init() {
        setObserve()
    }

    private fun setObserve() {
        viewModel.userList.observe(this) {
            val sb = StringBuilder()
            it.forEach { user ->
                sb.append("${user.firstName} ${user.lastName} \n")
            }
            binding.tvUser.text = sb
        }
    }
}