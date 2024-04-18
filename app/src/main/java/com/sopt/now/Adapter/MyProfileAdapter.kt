package com.sopt.now.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sopt.now.DataFile.MyProfile
import com.sopt.now.ViewHolder.MyProfileHolder
import com.sopt.now.databinding.ItemMyprofileBinding

class MyProfileAdapter : RecyclerView.Adapter<MyProfileHolder>() {
    // 임시의 빈 리스트
    private var myprofile: List<MyProfile> = emptyList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyProfileHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMyprofileBinding.inflate(inflater, parent, false)
        return MyProfileHolder(binding)
    }

    override fun onBindViewHolder(holder: MyProfileHolder, position: Int) {
        holder.onBind(myprofile[position])
    }

    override fun getItemCount() = myprofile.size

    fun setMyProfile(myprofile: List<MyProfile>) {
        this.myprofile = myprofile.toList()
        notifyDataSetChanged()
    }
}