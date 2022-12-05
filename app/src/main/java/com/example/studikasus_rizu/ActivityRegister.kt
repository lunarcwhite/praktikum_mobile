package com.example.studikasus_rizu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.example.studikasus_rizu.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth

class ActivityRegister : AppCompatActivity() {
    private lateinit var binding : ActivityRegisterBinding
    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.tvToLogin.setOnClickListener {
            val intent = Intent(this, ActivityLogin::class.java)
            startActivity(intent)
        }

        binding.btnRegister.setOnClickListener {
            val email = binding.edtEmailRegister.text.toString()
            val password = binding.edtPasswordRegister.text.toString()

            if (email.isEmpty()){
                binding.edtEmailRegister.error = "email harus diisi"
                binding.edtEmailRegister.requestFocus()
                return@setOnClickListener
            }
            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                binding.edtEmailRegister.error = "email tidak valid"
                binding.edtEmailRegister.requestFocus()
                return@setOnClickListener
            }
            if (password.isEmpty()){
                binding.edtPasswordRegister.error = "password harus diisi"
                binding.edtPasswordRegister.requestFocus()
                return@setOnClickListener
            }
            if (password.length < 6){
                binding.edtPasswordRegister.error = "password minimal 8 karakter"
                binding.edtPasswordRegister.requestFocus()
                return@setOnClickListener
            }
            registerFirebase(email, password)
        }
    }

    private fun registerFirebase(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener(this){
                if (it.isSuccessful){
                    Toast.makeText(this, "Register Berhasil", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, ActivityLogin::class.java)
                    startActivity(intent)
                }else{
                    Toast.makeText(this,"${it.exception?.message}",Toast.LENGTH_SHORT).show()
                }
            }
    }
}