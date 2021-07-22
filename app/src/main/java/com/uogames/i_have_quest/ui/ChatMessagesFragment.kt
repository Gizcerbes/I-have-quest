package com.uogames.i_have_quest.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.uogames.i_have_quest.R
import com.uogames.i_have_quest.databinding.FragmentChatMessagesBinding
import com.uogames.i_have_quest.models.NetworkModel
import com.uogames.i_have_quest.ui.adapters.ChatItemAdapter
import com.uogames.i_have_quest.ui.adapters.ChatSelectAdapter

class ChatMessagesFragment : Fragment() {

    private lateinit var binding: FragmentChatMessagesBinding
    private val networkModel by lazy { ViewModelProvider(requireActivity()).get(NetworkModel::class.java) }

    companion object {
        const val CHAT_NAME_KEY = "CHAT_NAME_KEY"
        const val ID_RECEIVER_KEY = "ID_RECEIVER_KEY"
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_chat_messages, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val chatName = arguments?.getString(CHAT_NAME_KEY)
        val idReceiver = arguments?.getInt(ID_RECEIVER_KEY)
        val myID = networkModel.loginData.value?.person?.id?.toInt() ?: 0

        if (!chatName.isNullOrEmpty()) {
            updateChat(chatName)
            binding.ibSendMessage.setOnClickListener {
                networkModel.sendMessageByChatName(chatName, binding.tiTextInput.text.toString()) {
                    updateChat(chatName)
                    binding.tiTextInput.setText("")
                }
            }
        } else if (idReceiver != myID) {
            idReceiver?.let {
                networkModel.getChatInfoByReceiverID(it) {
                    it.chatInfo?.nameChat?.let { it1 -> updateChat(it1) }
                }
            }
            binding.ibSendMessage.setOnClickListener {
                val message = binding.tiTextInput.text.toString().trim()
                if (message.isNotEmpty()) {
                    idReceiver?.let {
                        networkModel.sendMessageByPerson(
                            it,
                            binding.tiTextInput.text.toString()
                        ) { message ->
                            message.yourMessage?.name?.let { it1 -> updateChat(it1) }
                        }
                    }
                }
            }
        }

        binding.tiTextInput.addTextChangedListener(getWatcher())

    }

    private fun updateChat(chatName: String) {
        networkModel.getChatInfoByName(chatName) { chatInfo ->
            val countMessage = chatInfo.chatInfo?.countMessages ?: 0
            binding.rvFragmentChat.adapter =
                ChatItemAdapter(chatName, countMessage.toInt(), networkModel, requireContext())
            binding.rvFragmentChat.scrollToPosition(countMessage.toInt() - 1)
        }
    }


    private fun getWatcher(): TextWatcher {
        return object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                val len = s?.trim()?.length ?: 0
                if (len == 0) binding.ibSendMessage.visibility = View.INVISIBLE
                else binding.ibSendMessage.visibility = View.VISIBLE
            }
        }
    }

}