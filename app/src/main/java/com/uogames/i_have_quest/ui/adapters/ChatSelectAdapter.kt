package com.uogames.i_have_quest.ui.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.uogames.i_have_quest.R
import com.uogames.data.entities.responseData.ChatInfoData
import com.uogames.i_have_quest.databinding.FragmentChatSelectBinding
import com.uogames.i_have_quest.models.NetworkModel

class ChatSelectAdapter(
    private val size: Int,
    private val type: String,
    private val networkModel: NetworkModel
) :
    RecyclerView.Adapter<ChatSelectAdapter.ChatViewHolder>() {

    companion object {
        const val GLOBAL = "GLOBAL"
        const val PERSONAL = "PERSONAL"
        const val FORUM = "FORUM"

        const val CHAT_NAME_KEY = "CHAT_NAME_KEY"
        const val CHAT_LENGTH_KEY = "CHAT_LENGTH_KEY"
        const val CHAT_NAME_GLOBAL = "GLOBAL"
    }


    inner class ChatViewHolder(val binding: FragmentChatSelectBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            when (type) {
                GLOBAL -> {
                    when (position) {
                        0 -> networkModel.getChatInfoByName(CHAT_NAME_GLOBAL) { setChatInfo(it) }
                    }
                }
                PERSONAL -> {
                    networkModel.getChatInfoByNumber(position) { setChatInfo(it) }
                }
                FORUM -> {

                }
                else -> {
                    networkModel.getChatInfoByName(CHAT_NAME_GLOBAL) { setChatInfo(it) }
                }
            }

        }

        private fun setChatInfo(chatInfo: ChatInfoData) {
            if (type == GLOBAL) {
                binding.tvChatName.visibility = View.VISIBLE
                binding.tvUserName.visibility = View.GONE
            } else {
                binding.tvChatName.visibility = View.GONE
                binding.tvUserName.visibility = View.VISIBLE
            }
            binding.tvChatName.text = chatInfo.chatInfo?.nameChat
            if (chatInfo.chatInfo?.idAuthor != networkModel.loginData.value?.user?.id) {
                networkModel.getPersonById(chatInfo.chatInfo?.idAuthor?.toInt()) {
                    binding.tvUserName.text = it.person?.personName
                }
            } else {
                binding.tvUserName.text = networkModel.loginData.value?.person?.personName
            }

            binding.tvMessage.text = chatInfo.chatInfo?.lastMessage

            itemView.setOnClickListener {
                itemView.findNavController().navigate(
                    R.id.chatMessagesFragment,
                    Bundle().apply { putString(CHAT_NAME_KEY, chatInfo.chatInfo?.nameChat)})
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val binding = DataBindingUtil.inflate<FragmentChatSelectBinding>(
            LayoutInflater.from(parent.context), R.layout.fragment_chat_select, parent, false
        )
        return ChatViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return size
    }

}

