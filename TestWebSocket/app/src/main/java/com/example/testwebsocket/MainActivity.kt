package com.example.testwebsocket

import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.annotation.MainThread
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import ua.naiksoftware.stomp.Stomp
import ua.naiksoftware.stomp.StompClient


class MainActivity : AppCompatActivity() {
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var chatAdapter: ChatAdapter


    private lateinit var client: StompClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerView()
        initClient()
        initView()
    }

    override fun finish() {
        client.disconnect()
        super.finish()

    }

    private fun initView(){
        ActivityMainSendButton.setOnClickListener {
            val sendContents = ActivityMainChatEditText.text.toString()
            client.send("/app/echo", sendContents)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {},
                    { err -> Log.d("testClient", err.toString()) })
            chatAdapter.addMessageList(Message(sendContents, SEND_TYPE))

        }
    }

    private fun initRecyclerView() {
        viewManager = LinearLayoutManager(this)
        chatAdapter = ChatAdapter()
        ActivityMainChatRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = chatAdapter
        }
    }

    private fun initClient() {
        client = Stomp.over(
            Stomp.ConnectionProvider.JWS,
            "ws://10.106.163.100:8080/test-websocket/websocket"
        )
        client.topic("/test/msg")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { message -> chatAdapter.addMessageList(Message(message.payload, RECEIVE_TYPE)) },
                { err -> Log.d("testClient", err.toString()) })

        client.connect()
    }

    fun initTestList() {
        chatAdapter.addMessageList(Message("tsetst", SEND_TYPE))
        chatAdapter.addMessageList(Message("tseteeeeest", RECEIVE_TYPE))
        chatAdapter.addMessageList(Message("tseteeest", RECEIVE_TYPE))
        chatAdapter.addMessageList(Message("ts111eteeeeest", RECEIVE_TYPE))
        chatAdapter.addMessageList(Message("tsewwwtst", SEND_TYPE))
        chatAdapter.addMessageList(Message("tsete111eeeest", RECEIVE_TYPE))


    }

}
