package com.example.secretgarden

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.PersistableBundle
import android.provider.Settings.Global.putString
import android.util.Log
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.core.widget.addTextChangedListener

class DiaryActivity: AppCompatActivity() {

    private val handler = Handler(Looper.getMainLooper()) //(2)핸들러생성

    private val diaryEditText : EditText by lazy{
        findViewById<EditText>(R.id.diaryEditText)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diary)

        val detailPreferences = getSharedPreferences("diary", Context.MODE_PRIVATE)
        diaryEditText.setText(detailPreferences.getString("detail",""))


        val runnable = Runnable {//쓰레드 기능 이용 ->
            getSharedPreferences("diary", Context.MODE_PRIVATE).edit{
                putString("detail", diaryEditText.text.toString())
            }

            Log.d("detail", "SAVE!!!!!!! ${diaryEditText.text.toString()}")
        }


        diaryEditText.addTextChangedListener{//변할때마다 감지
            //mainThread(UI Thread)와 생성한 Thread연결해줄 필요 -> 핸들러이용

            Log.d("DiaryActivity", "TextChanged :: $it")

            handler.removeCallbacks(runnable)//이전의 runnable이 있다면 지움
            handler.postDelayed(runnable, 500)//0.5초뒤에 runnable실행

        }


    }
}