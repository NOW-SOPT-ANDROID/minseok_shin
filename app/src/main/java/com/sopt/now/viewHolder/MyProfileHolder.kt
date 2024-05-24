package com.sopt.now.viewHolder

import androidx.recyclerview.widget.RecyclerView
import com.sopt.now.compose.databinding.ItemMyprofileBinding
import com.sopt.now.dataClass.MyProfile

class MyProfileHolder(val binding: ItemMyprofileBinding) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(profileData: MyProfile) {
        binding.run {
            ivProfile.setImageResource(profileData.profileImage)
            tvName.text = profileData.name
            tvSelfDescription.text = profileData.selfDescription
        }
    }
}