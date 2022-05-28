package app.sport.workoutlog

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import app.sport.workoutlog.databinding.ActivityScheduleBinding

class ScheduleActivity : AppCompatActivity() {
    lateinit var binding: ActivityScheduleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScheduleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bNav.setOnNavigationItemReselectedListener {
            when (it.itemId){
                R.id.btn_schedule -> {
                    val intent = Intent(this@ScheduleActivity, ScheduleActivity::class.java)
                    startActivity(intent)
                }
                R.id.btn_attendance -> {
                    val intent = Intent(this@ScheduleActivity, Attendance::class.java)
                    startActivity(intent)
                }
                R.id.btn_personal_account -> {
                    val intent = Intent(this@ScheduleActivity, PersonalAccount::class.java)
                    startActivity(intent)
                }
            }
            true
        }
    }
}