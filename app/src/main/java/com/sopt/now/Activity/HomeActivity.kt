package com.sopt.now.Activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.sopt.now.Fragment.HomeFragment
import com.sopt.now.Fragment.MyPageFragment
import com.sopt.now.Fragment.SearchFragment
import com.sopt.now.R
import com.sopt.now.databinding.ActivityHomeBinding
class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private val nameId="ID"
    private val namePassword = "PASSWORD"
    private val nameNickname = "NICKNAME"
    private val nameMbti = "MBTI"

    private lateinit var userId: String
    private lateinit var userPassword: String
    private lateinit var userNickname: String
    private lateinit var userMbti: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d("HomeActivity","HomeActivity onCreate")


        userId = intent.getStringExtra(nameId).toString()
        userPassword = intent.getStringExtra(namePassword).toString()
        userNickname = intent.getStringExtra(nameNickname).toString()
        userMbti = intent.getStringExtra(nameMbti).toString()

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
                    replaceFragment(MyPageFragment(userId,userPassword,userNickname,userMbti))
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
