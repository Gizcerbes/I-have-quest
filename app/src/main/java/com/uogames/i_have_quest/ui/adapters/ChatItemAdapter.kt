package com.uogames.i_have_quest.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.uogames.data.entities.responseData.PersonData
import com.uogames.i_have_quest.R
import com.uogames.data.entities.responseData.YourMessageData
import com.uogames.i_have_quest.databinding.FragmentChatItemBinding
import com.uogames.i_have_quest.models.NetworkModel

class ChatItemAdapter(
    private val chatName: String,
    private val size: Int,
    private val networkModel: NetworkModel,
    private val context: Context
) : RecyclerView.Adapter<ChatItemAdapter.ChatViewHolder>() {


    inner class ChatViewHolder(val binding: FragmentChatItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            networkModel.getMessage(chatName, position) { showMessage(it) }
        }

        private fun showMessage(yourMessageData: YourMessageData) {
            binding.tvMessage.text = yourMessageData.yourMessage?.message
            networkModel.getPersonById(yourMessageData.yourMessage?.idAuthor) {
                binding.tvUserName.text = it.person?.personName
                loadIcon(it)
            }
        }

        private fun loadIcon(personData:PersonData){
            try {
                Glide.with(context)
                    .load(context.getString(R.string.link_image_server) + personData.person?.image)
                    .diskCacheStrategy(
                        DiskCacheStrategy.NONE
                    ).skipMemoryCache(true).into(binding.ivPhoto)
            } catch (e: Throwable) {

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val binding = DataBindingUtil.inflate<FragmentChatItemBinding>(
            LayoutInflater.from(parent.context), R.layout.fragment_chat_item, parent, false
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