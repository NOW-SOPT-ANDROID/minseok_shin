package com.sopt.now.viewHolder

import androidx.recyclerview.widget.RecyclerView
import com.sopt.now.compose.databinding.ItemFriendBinding
import com.sopt.now.dataClass.Friend

class FriendViewHolder(private val binding: ItemFriendBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(friendData: Friend) {
        binding.run {
            ivProfile.setImageResource(friendData.profileImage)
            tvName.text = friendData.name
            tvSelfDescription.text = friendData.selfDescription
        }
    }
}