package com.sopt.now.ViewHolder

import androidx.recyclerview.widget.RecyclerView
import com.sopt.now.MyProfile
import com.sopt.now.databinding.ItemMyprofileBinding

class MyProfileHolder(val binding: ItemMyprofileBinding) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(profileData: MyProfile) {
        binding.run {
            ivProfile.setImageResource(profileData.profileImage)
            tvName.text = profileData.name
            tvSelfDescription.text = profileData.selfDescription
        }
    }
}