package com.sopt.now.activity

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.sopt.now.R
import com.sopt.now.databinding.ActivityHomeBinding
import com.sopt.now.fragment.HomeFragment
import com.sopt.now.fragment.MyPageFragment
import com.sopt.now.fragment.SearchFragment
import com.sopt.now.viewModel.HomeState
import com.sopt.now.viewModel.HomeViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private val homeViewModel by viewModels<HomeViewModel>()


    private lateinit var userId: String
    private lateinit var userNickname: String
    private lateinit var userPhone: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val memberId = intent.getIntExtra("memberId", 1)

        Toast.makeText(this@HomeActivity, "HomeActivity", Toast.LENGTH_SHORT)
            .show()

        Log.d("HomeActivity", "HomeActivity onCreate")

        homeViewModel.searchInfo(memberId)
        initLayout()
        clickBottomNavigation(memberId)
        collectHomeState()
    }

    private fun initLayout() {
        supportFragmentManager.findFragmentById(R.id.fcv_home) ?: replaceFragment(HomeFragment())
    }

    private fun collectHomeState() {
        homeViewModel.homeState.flowWithLifecycle(lifecycle).onEach { homeState ->
            when (homeState) {
                is HomeState.Success -> {
                    userNickname = homeState.responseInfoDto.data.nickname
                }

                else -> Unit
            }
        }.launchIn(lifecycleScope)
    }


    private fun clickBottomNavigation(memberId: Int) {
        binding.bnvHome.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_home -> {
                    replaceFragment(HomeFragment())
                    true
                }

                R.id.menu_search -> {
                    replaceFragment(SearchFragment())
                    true
                }

                R.id.menu_mypage -> {
                    replaceFragment(MyPageFragment())
                    true
                }

                else -> false
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.fcvHome.id, fragment)
            .commit()
    }
}
