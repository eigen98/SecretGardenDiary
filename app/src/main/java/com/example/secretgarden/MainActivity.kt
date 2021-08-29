package com.example.secretgarden

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Color.RED
import android.hardware.camera2.params.RggbChannelVector.RED
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.NumberPicker
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.edit

class MainActivity : AppCompatActivity() {

    //뷰가 그려지기 전에 lazy하게 변수 생성
    private val numberPicker1 : NumberPicker by lazy {
        findViewById<NumberPicker>(R.id.numberPicker1)
            .apply {
                minValue = 0
                maxValue = 9
            }
    }
    private val numberPicker2 : NumberPicker by lazy {
        findViewById<NumberPicker>(R.id.numberPicker2)
            .apply {
                minValue = 0
                maxValue = 9
            }
    }
    private val numberPicker3 : NumberPicker by lazy {
        findViewById<NumberPicker>(R.id.numberPicker3)
            .apply {
                minValue = 0
                maxValue = 9
            }
    }

    private val openButton : AppCompatButton by lazy {
        findViewById<AppCompatButton>(R.id.openButton)
    }
    private val changePasswordButton : AppCompatButton by lazy {
        findViewById<AppCompatButton>(R.id.changePasswordButton)
    }
    private var changePasswordMode = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        numberPicker1
        numberPicker2
        numberPicker3


        openButton.setOnClickListener{

            if(changePasswordMode){
                Toast.makeText(this,"비밀번호 변경 중입니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            //SharedPreferences -> 파일을 다른앱과 같이 사용할 수 있도록, (private으로 공유는 안 함)
            val passwordPreferences = getSharedPreferences("password", Context.MODE_PRIVATE)

            val passwordFromUser = "${numberPicker1.value}${numberPicker2.value}${numberPicker3.value}"

            if(passwordPreferences.getString("password", "000").equals(passwordFromUser)){
            // 초기 비밀번호와 일치 성공

                //ToDO 다이어리 페이지 작성 후에 넘겨주어야함

                startActivity(Intent(this, DiaryActivity::class.java))
            }else{
                //에러팝업, 빌더패턴?-> 빌더에 셋한다음에 마지막으로 create show 방식
                showErrorAlertDialog()
                //실패
            }
        }


        changePasswordButton.setOnClickListener{
            if(changePasswordMode){
                //번호를 저장하는 기능
                val passwordPreferences = getSharedPreferences("password", Context.MODE_PRIVATE)
                val passwordFromUser = "${numberPicker1.value}${numberPicker2.value}${numberPicker3.value}"
                //val editor = passwordPreferences.edit() 예전 방식
                //editor.putString()

                //지금 방식
                passwordPreferences.edit{ //commit 파일이 저장될 때까지 ui멈춤 , apply 비동기 방식

                    putString("password", passwordFromUser)
                    commit()
                }
//                passwordPreferences.edit(true){
//                    val passwordFromUser = "${numberPicker1.value}${numberPicker2.value}${numberPicker3.value}"
//                    putString("password", passwordFromUser)
//                }

                changePasswordMode = false
                changePasswordButton.setBackgroundColor(Color.BLACK)

            }else{
                //changePasswordMode가 활성화 :: 비밀번호가 맞는지 체크

                val passwordPreferences = getSharedPreferences("password", Context.MODE_PRIVATE)

                val passwordFromUser = "${numberPicker1.value}${numberPicker2.value}${numberPicker3.value}"

                if(passwordPreferences.getString("password", "000").equals(passwordFromUser)){
                    // 초기 비밀번호와 일치 성공

                    changePasswordMode = true
                    Toast.makeText(this,"변경할 패스워드를 입력해주세요 ", Toast.LENGTH_SHORT).show()

                    changePasswordButton.setBackgroundColor(Color.RED)

                    //startActivity()
                }else{
                    //에러팝업, 빌더패턴?-> 빌더에 셋한다음에 마지막으로 create show 방식
                    showErrorAlertDialog()
                    //실패
                }
            }

        }

    }

    private fun showErrorAlertDialog(){
        AlertDialog.Builder(this)
            .setTitle("실패!")
            .setMessage("비밀번호가 잘못되었습니다.")
            .setPositiveButton("확인"){ dialog, which ->// dialog,which사용 안 함
            }.create()
            .show()
    }
}