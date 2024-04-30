package com.sopt.now.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.sopt.now.R
import com.sopt.now.databinding.ActivityHomeBinding
import com.sopt.now.fragment.HomeFragment
import com.sopt.now.fragment.MyPageFragment
import com.sopt.now.fragment.SearchFragment

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private val nameId = "ID"
    private val nameNickname = "NICKNAME"
    private val namePhone = "PHONE"

    private lateinit var userId: String
    private lateinit var userNickname: String
    private lateinit var userPhone: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d("HomeActivity", "HomeActivity onCreate")


        userId = intent.getStringExtra(nameId).toString()
        userNickname = intent.getStringExtra(nameNickname).toString()
        userPhone = intent.getStringExtra(namePhone).toString()

        clickBottomNavigation()

        val currentFragment = supportFragmentManager.findFragmentById(binding.fcvHome.id)
        if (currentFragment == null) {
            supportFragmentManager.beginTransaction()
                .add(binding.fcvHome.id, HomeFragment(userNickname))
                .commit()
        }
    }

    private fun clickBottomNavigation() {
        binding.bnvHome.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_home -> {
                    replaceFragment(HomeFragment(userNickname))
                    true
                }

                R.id.menu_search -> {
                    replaceFragment(SearchFragment())
                    true
                }

                R.id.menu_mypage -> {
                    replaceFragment(MyPageFragment(userId, userNickname, userPhone))
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
