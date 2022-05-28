package app.sport.workoutlog

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class RegistrActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registr)
    }



        fun urilog(view: android.view.View){
            val activityLog = Login::class.java
            activityLog
        }

}
