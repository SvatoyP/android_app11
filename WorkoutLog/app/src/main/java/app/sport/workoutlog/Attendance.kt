package app.sport.workoutlog

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import app.sport.workoutlog.databinding.ActivityAttendanceBinding
import app.sport.workoutlog.databinding.ActivityScheduleBinding

class Attendance : AppCompatActivity() {
    lateinit var binding: ActivityAttendanceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAttendanceBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bNav.selectedItemId = R.id.btn_attendance
        binding.bNav.setOnNavigationItemReselectedListener {
            when (it.itemId){
                R.id.btn_schedule -> {
                    val intent = Intent(this@Attendance, ScheduleActivity::class.java)
                    startActivity(intent)
                }
                R.id.btn_attendance -> {
                    val intent = Intent(this@Attendance, Attendance::class.java)
                    startActivity(intent)
                }
                R.id.btn_personal_account -> {
                    val intent = Intent(this@Attendance, PersonalAccount::class.java)
                    startActivity(intent)
                }
            }
            true
        }
    }
}