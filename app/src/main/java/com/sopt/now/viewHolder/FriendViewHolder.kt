package com.sopt.now.viewHolder

import androidx.recyclerview.widget.RecyclerView
import com.sopt.now.dataClass.Friend
import com.sopt.now.databinding.ItemFriendBinding

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