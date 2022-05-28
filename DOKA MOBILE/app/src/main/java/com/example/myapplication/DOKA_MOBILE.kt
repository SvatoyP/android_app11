package com.example.myapplication

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import android.widget.VideoView


class DOKA_MOBILE : AppCompatActivity() {
    private var counter: Int = 0
    private var stop: Boolean = false
    private var mainText: String = "Подгрузка эльфов"
    private var mainText1: String? = null
    private var loading1: ProgressBar? = null
    private var loadingText: TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loading1 = findViewById(R.id.loading1)
        loadingText = findViewById(R.id.loadingText)
        loadingText?.setText(mainText.toString())
        val m_activity = Intent (this@DOKA_MOBILE, MainActivity:: class.java)

        Thread {
            stop = true
            while (stop) {
                Thread.sleep(150)
                if (counter == 110)
                    stop = false
                counter++
                runOnUiThread {
                    if (counter == 20) loadingText?.setText("Ходим вокруг рошпита".toString())
                    if (counter == 40) loadingText?.setText("Вешаем иконку Папича".toString())
                    if (counter == 60) loadingText?.setText("Одеваем Паджеро в новый лук".toString())
                    if (counter == 80) loadingText?.setText("Смотрим на Усатую Крысу".toString())
                    if (counter == 95) loadingText?.setText("Поиск игры".toString())
                    if (counter==110) startActivity(m_activity)

                    loading1?.setProgress(counter)


                }

            }
        }.start()


    }

}
