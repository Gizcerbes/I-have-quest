package com.uogames.i_have_quest.ui.adapters

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.uogames.data.entities.objectData.PersonObjectData
import com.uogames.i_have_quest.R
import com.uogames.data.entities.responseData.ChatInfoData
import com.uogames.i_have_quest.databinding.FragmentChatSelectBinding
import com.uogames.i_have_quest.models.NetworkModel
import com.uogames.i_have_quest.ui.ChatMessagesFragment

class ChatSelectAdapter(
    private val size: Int,
    private val type: String,
    private val networkModel: NetworkModel,
    private val context: Context?
) :
    RecyclerView.Adapter<ChatSelectAdapter.ChatViewHolder>() {

    companion object {
        const val GLOBAL = "GLOBAL"
        const val PERSONAL = "PERSONAL"
        const val FORUM = "FORUM"

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

                if (chatInfo.chatInfo?.idAuthor != networkModel.loginData.value?.user?.id) {
                    networkModel.getPersonById(chatInfo.chatInfo?.idAuthor?.toInt()) {
                        binding.tvUserName.text = it.person?.personName
                    }
                    networkModel.getPersonById(chatInfo.chatInfo?.idAuthor?.toInt()) {
                        loadIcon(it.person)
                    }
                } else {
                    binding.tvUserName.text = networkModel.loginData.value?.person?.personName
                    loadIcon(networkModel.loginData.value?.person)
                }
            }
            binding.tvChatName.text = chatInfo.chatInfo?.nameChat

            binding.tvMessage.text = chatInfo.chatInfo?.lastMessage

            itemView.setOnClickListener {
                itemView.findNavController().navigate(
                    R.id.chatMessagesFragment,
                    Bundle().apply {
                        putString(
                            ChatMessagesFragment.CHAT_NAME_KEY,
                            chatInfo.chatInfo?.nameChat
                        )
                    })
            }
        }

        private fun loadIcon(personData: PersonObjectData?) {
            try {
                context?.let {
                    Glide.with(it)
                        .load(context.getString(R.string.link_image_server) + personData?.image)
                        .diskCacheStrategy(
                            DiskCacheStrategy.NONE
                        ).skipMemoryCache(true).into(binding.ivPhoto)
                }
            } catch (e: Throwable) {

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

