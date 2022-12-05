package com.example.studikasus_rizu

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.studikasus_rizu.databinding.ActivityDataBinding
import com.google.firebase.firestore.FirebaseFirestore

class ActivityData : AppCompatActivity() {
    lateinit var binding: ActivityDataBinding
    lateinit var db: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDataBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        db = FirebaseFirestore.getInstance()
        db.collection("Kredits")
            .get()
            .addOnSuccessListener {
                binding.edtNominal.setText(it.documents.last().data?.get("Nominal").toString())
                binding.edtTenor.setText(it.documents.last().data?.get("Tenor").toString())
                binding.edtAngsuran.setText(it.documents.last().data?.get("Angsuran").toString())
            }
            .addOnFailureListener{
                it.printStackTrace()
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
            }
        binding.btnDelete.setOnClickListener{
            delete()
            val intent = Intent(this, MainActivity::class.java)
        }
    }

    fun delete(){
        db.collection("kredits").document("0py2cPyvBDjWc4c6oNDC")
            .delete()
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot Successfully delete!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error Delete Document", e) }
    }
}