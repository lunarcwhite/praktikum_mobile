package com.example.studikasus_rizu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.studikasus_rizu.databinding.ActivityTambahBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ActivityTambah : AppCompatActivity() {
    lateinit var binding: ActivityTambahBinding
    val db = Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTambahBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val editNominal = binding.nominalPinjaman.text
        val editTenor = binding.tenorBulan.text

        binding.btnSimulasi.setOnClickListener {
            binding.tfPinjaman.setText(editNominal.toString())
            binding.tfTenor.setText(editTenor.toString())
            hitungTenor(editNominal.toString().toInt(), editTenor.toString().toInt())
        }

        binding!!.btnSubmit.setOnClickListener {
            if (editNominal.isEmpty()){
                Toast.makeText(this, "ANJAY", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val kredit = hashMapOf(
                "Nominal" to editNominal.toString(),
                "Tenor" to editTenor.toString(),
            )

            db.collection("kredits")
                .add(kredit)
                .addOnSuccessListener { documentReference ->
                    Log.d("TAG", "DocumentSnapshot added with ID: ${documentReference.id}")
                    Toast.makeText(this, "ANJAY", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener { e ->
                    Log.w("TAG", "Error adding document", e)
                }



            //link ke layout read
            //val intent = Intent(this, DataActivity::class.java)
            //startActivity(intent)
        }
    }

    fun hitungTenor(nominal:Int, tenor:Int) {
        val hasil = (nominal*0.5) / tenor
        binding.tfAngsuran.setText(hasil.toString())
    }

}