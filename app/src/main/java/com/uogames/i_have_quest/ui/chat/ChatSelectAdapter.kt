package com.uogames.i_have_quest.ui.chat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.uogames.i_have_quest.R
import com.uogames.i_have_quest.data.entities.ChatObjectData
import com.uogames.i_have_quest.databinding.FragmentChatSelectBinding

class ChatSelectAdapter : RecyclerView.Adapter<ChatSelectAdapter.ChatViewHolder>() {

    private var dataList: List<ChatObjectData> = arrayListOf()

    inner class ChatViewHolder(val binding: FragmentChatSelectBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(chatData: ChatObjectData) {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val binding = DataBindingUtil.inflate<FragmentChatSelectBinding>(
            LayoutInflater.from(parent.context), R.layout.fragment_chat_select, parent, false
        )
        return ChatViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

}

