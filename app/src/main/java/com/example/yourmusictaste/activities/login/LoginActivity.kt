package com.example.yourmusictaste.activities.login

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.yourmusictaste.R
import com.example.yourmusictaste.activities.api.LastFmClient
import com.example.yourmusictaste.activities.api.UserInfo
import com.example.yourmusictaste.activities.wall.WallActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        createLoginBtnListener()
    }

    private fun fieldErrorMsg(field: EditText, errorMsg: String?){
        field.error = errorMsg
        field.requestFocus()
    }

    private fun createLoginBtnListener() {
        val loginBtn: Button = findViewById(R.id.login_btn)
        val usernameField: EditText = findViewById(R.id.username)
        val afterLoginActivity = Intent(this, WallActivity::class.java)


        loginBtn.setOnClickListener {
            if (usernameField.text.isEmpty()) {
                fieldErrorMsg(usernameField, "The username filed is required!")
                return@setOnClickListener
            }
            val call = LastFmClient.service.getInfo(usernameField.text.toString())

            call.enqueue(object : Callback<UserInfo> {
                override fun onFailure(call: Call<UserInfo>, t: Throwable) {
                    fieldErrorMsg(usernameField, t.message)
                }

                override fun onResponse(call: Call<UserInfo>, response: Response<UserInfo>) {
                    // TODO: change if to when?
                    if (response.isSuccessful) {
                        response.body()?.user?.image?.forEach{
                            if(it.size == "large"){
                                afterLoginActivity.putExtra("avatar", it.text)
                            }
                        }
                        afterLoginActivity.putExtra("username", response.body()!!.user.name)
                        startActivity(afterLoginActivity)
                    } else if (response.code() == 404) {
                        fieldErrorMsg(usernameField, "User not found, please provide existing user")
                    } else{
                        fieldErrorMsg(usernameField, "${response.code()}: ${response.message()}")
                    }

                }
            }
            )
        }
    }
}