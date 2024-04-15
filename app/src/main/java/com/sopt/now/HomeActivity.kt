package com.sopt.now

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.sopt.now.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private val nameId="ID"
    private val namePassword = "PASSWORD"
    private val nameNickname = "NICKNAME"
    private val nameMbti = "MBTI"

    private val userId = intent.getStringExtra(nameId).toString()
    private val userPassword = intent.getStringExtra(namePassword).toString()
    private val userNickname = intent.getStringExtra(nameNickname).toString()
    private val userMbti = intent.getStringExtra(nameMbti).toString()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        clickBottomNavigation()

        val currentFragment = supportFragmentManager.findFragmentById(binding.fcvHome.id)
        if (currentFragment == null) {
            supportFragmentManager.beginTransaction()
                .add(binding.fcvHome.id, HomeFragment())
                .commit()
        }
    }

    private fun clickBottomNavigation() {
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
                    replaceFragment(MyPageFragment(userId,userPassword,userNickname,userMbti))
                    true
                }

                else -> false
            }
        }
    }

    // Activity에서 Fragment를 다뤄야 하니 supportFragmentManager를 사용합니다.
// 트렌젝션을 시작하고 replace 메서드를 통해 Fragment를 교체합니다.
    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.fcvHome.id, fragment)
            .commit()
    }
}