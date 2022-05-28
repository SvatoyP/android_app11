package app.sport.workoutlog

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import app.sport.workoutlog.databinding.ActivityPersonalAccountBinding
import app.sport.workoutlog.databinding.ActivityScheduleBinding

class PersonalAccount : AppCompatActivity() {
    lateinit var binding: ActivityPersonalAccountBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPersonalAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bNav.selectedItemId = R.id.btn_personal_account
        binding.bNav.setOnNavigationItemReselectedListener {
            when (it.itemId){
                R.id.btn_schedule -> {
                    val intent = Intent(this@PersonalAccount, ScheduleActivity::class.java)
                    startActivity(intent)
                }
                R.id.btn_attendance -> {
                    val intent = Intent(this@PersonalAccount, Attendance::class.java)
                    startActivity(intent)
                }
                R.id.btn_personal_account -> {
                    val intent = Intent(this@PersonalAccount, PersonalAccount::class.java)
                    startActivity(intent)
                }
            }
            true
        }
    }
}