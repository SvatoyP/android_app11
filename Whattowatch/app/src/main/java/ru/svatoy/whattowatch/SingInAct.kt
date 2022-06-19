package ru.svatoy.whattowatch

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import ru.svatoy.whattowatch.databinding.ActivitySingInBinding


class SingInAct : AppCompatActivity() {

    lateinit var launcher: ActivityResultLauncher<Intent>
    lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivitySingInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySingInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        auth = Firebase.auth
        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){

            val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
            try {
                val account = task.getResult(ApiException::class.java)
                if (account != null){
                    firebaseAuthWithGoogle(account.idToken!!)
                }
            }catch (e:ApiException){
                Log.d("MyLog","Api Exception error")
            }

        }
        binding.bSingIn.setOnClickListener{
            singInWithGoogle()
        }
        checkNewUser()
        checkAuthState()
    }


    private fun getClient(): GoogleSignInClient {
        val gso = GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        return GoogleSignIn.getClient(this, gso)
    }

    private fun singInWithGoogle(){
        val singInClient = getClient()
        launcher.launch(singInClient.signInIntent)
    }

    private fun firebaseAuthWithGoogle(idToken:String){
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful){
                Log.d("MyLog","Google signIn done")
                checkAuthState()
            }else {
                Log.d("MyLog","Google signIn error")
            }
        }
    }
    private fun checkAuthState(){
        if(auth.currentUser != null){
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }else{
            Log.d("MyLog","Google signIn error on check")
        }
    }

    private fun sendNewUser(){
        auth = Firebase.auth
        val db = Firebase.firestore
        val currentEmail = auth.currentUser?.email
        val user = hashMapOf(
            "name" to auth.currentUser?.displayName.toString(),
            "email" to auth.currentUser?.email.toString(),
            "avatarURL" to auth.currentUser?.photoUrl.toString(),


        )
        db.collection("users").document(currentEmail.toString())
            .set(user)
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully written!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error writing document", e) }
    }

    private fun checkNewUser() {
        auth = Firebase.auth
        val db = Firebase.firestore
        val currentEmail = auth.currentUser?.email
        val searchDoc = db.collection("users").document(currentEmail.toString())

        if(searchDoc != null){
            Log.d("MyLog","User got reg")
        }else{
            sendNewUser()
        }
    }
}



