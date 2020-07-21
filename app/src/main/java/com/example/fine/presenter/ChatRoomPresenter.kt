package com.example.fine.presenter

import android.content.Context

class ChatRoomPresenter : ChatRoomContract.Presenter{
    override lateinit var mView: ChatRoomContract.View
    override lateinit var mContext: Context
    override val TAG: String = "ChatRoomPresenter"
}