package com.example.pract16

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.core.content.edit

class MainActivity : AppCompatActivity() {
    private lateinit var prefs: SharedPreferences
    private lateinit var Login: EditText
    private lateinit var Password: EditText

    companion object {
        const val PREFS_NAME = "auth_prefs"
        const val KEY_LOGIN = "login"
        const val KEY_PASSWORD = "password"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
        initViews()
    }

    private fun initViews() {
        Login = findViewById(R.id.Login)
        Password = findViewById(R.id.Password)

        findViewById<Button>(R.id.buttonSave).setOnClickListener {
            if (Login.text.toString().isNotEmpty()) {
                if (Password.text.toString().isNotEmpty()) {
                    saveData()
                }
            }
            else
            {
                val alert = AlertDialog.Builder(this)
                    .setTitle("Ошибка")
                    .setMessage("У вас есть незаполненные поля")
                    .setPositiveButton("OK", null)
                    .create()
                    .show()
            }
        }
        findViewById<Button>(R.id.buttonLoad).setOnClickListener { loadData() }
    }

    private fun saveData() {
        prefs.edit {
            putString(KEY_LOGIN, Login.text.toString())
            putString(KEY_PASSWORD, Password.text.toString())
        }
    }

    private fun loadData() {
        Login.setText(prefs.getString(KEY_LOGIN, ""))
        Password.setText(prefs.getString(KEY_PASSWORD, ""))
    }
}