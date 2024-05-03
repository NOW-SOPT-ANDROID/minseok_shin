package com.sopt.now.activity

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.sopt.now.R
import com.sopt.now.ServicePool.authService
import com.sopt.now.dataClass.ResponseInfoDto
import com.sopt.now.databinding.ActivityHomeBinding
import com.sopt.now.fragment.HomeFragment
import com.sopt.now.fragment.MyPageFragment
import com.sopt.now.fragment.SearchFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding


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

        searchInfo(memberId)


    }

    private fun searchInfo(memberId: Int) {
        authService.info(memberId).enqueue(object : Callback<ResponseInfoDto> {
            override fun onResponse(
                call: Call<ResponseInfoDto>,
                response: Response<ResponseInfoDto>
            ) {
                if (response.isSuccessful) {
                    userId = response.body()!!.data.authenticationId
                    userNickname = response.body()!!.data.nickname
                    userPhone = response.body()!!.data.phone
                    clickBottomNavigation(memberId)
                    val currentFragment =
                        supportFragmentManager.findFragmentById(binding.fcvHome.id)
                    if (currentFragment == null) {
                        supportFragmentManager.beginTransaction()
                            .add(binding.fcvHome.id, HomeFragment(userNickname))
                            .commit()
                    }
                } else {
                    Log.d("HomeActivity", "response ${response.body()?.message}")
                }
            }

            override fun onFailure(call: Call<ResponseInfoDto>, t: Throwable) {
                Toast.makeText(this@HomeActivity, "조회 요청 실패: ${t.message}", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }


    private fun clickBottomNavigation(memberId: Int) {
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
                    replaceFragment(MyPageFragment(userId, userNickname, userPhone, memberId))
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
