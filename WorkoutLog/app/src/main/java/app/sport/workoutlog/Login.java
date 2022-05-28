package app.sport.workoutlog;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    private EditText emailTV, passwordTV; //поля почты и пароля
    private Button loginBtn;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) { //конструктор класса логин
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registr); //отображает страницу входа

        mAuth = FirebaseAuth.getInstance(); //переменная которая связывается с экземпляром бд

        initializeUI(); //полям передаются значения кнопок

        loginBtn.setOnClickListener(new View.OnClickListener() { //логика нажатия кнопки
            @Override
            public void onClick(View v) {
                loginUserAccount();
            }
        });
    }

    public void loginUserAccount() {

        String email, password; //извлекает данные из полей ввода
        email = emailTV.getText().toString();
        password = passwordTV.getText().toString();


        if (TextUtils.isEmpty(email)) { //проверка на пустоту поля
            Toast.makeText(getApplicationContext(), "Please enter email...", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Please enter password!", Toast.LENGTH_LONG).show();
            return;
        }


        mAuth.signInWithEmailAndPassword(email, password) //запрос в бд на проверку данных
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Login successful!", Toast.LENGTH_LONG).show();

                            Intent intent = new Intent(Login.this, ScheduleActivity.class);
                            startActivity(intent);//переход на страницу расписания
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Login failed! Please try again later", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    private void initializeUI() { //присваение преременным айди обЪектов
        emailTV = findViewById(R.id.input_login);
        passwordTV = findViewById(R.id.input_pas);
        loginBtn = findViewById(R.id.login);
    }

    public void urireg(View view) //кнопка регистрации
    {
        Intent activityReg = new Intent(Login.this, Register.class);
        startActivity(activityReg);
    }


}