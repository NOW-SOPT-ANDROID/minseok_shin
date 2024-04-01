package com.sopt.now

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.sopt.now.databinding.ActivityMainBinding

class MainActivity: AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater) //TODO: inflate 두 번 하면 오류
        setContentView(binding.root)
        Snackbar.make(
            binding.root,
            "로그인 성공!",
            Snackbar.LENGTH_SHORT
        ).show()
    }

}