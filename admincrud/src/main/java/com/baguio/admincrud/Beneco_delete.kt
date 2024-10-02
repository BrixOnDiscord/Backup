package com.baguio.admincrud

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.baguio.admincrud.databinding.ActivityBenecoDeleteBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Beneco_delete : AppCompatActivity() {

    private lateinit var binding:ActivityBenecoDeleteBinding
    private lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityBenecoDeleteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.deletebtn.setOnClickListener{
            val id = binding.EnterID.text.toString()
            if(id.isNotEmpty()){
                deleteData(id)
            }else{
                Toast.makeText(this,"Please enter correct UID", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun deleteData(id: String) {
        databaseReference = FirebaseDatabase.getInstance().getReference("Beneco_Announcements")
        databaseReference.child(id).get().addOnSuccessListener { snapshot ->
            if (snapshot.exists()) {
                // The ID exists, proceed with deletion
                databaseReference.child(id).removeValue().addOnSuccessListener {
                    binding.EnterID.text.clear()
                    Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show()
                }.addOnFailureListener {
                    Toast.makeText(this, "Unable to Delete", Toast.LENGTH_SHORT).show()
                }
            } else {
                // The ID does not exist
                Toast.makeText(this, "ID does not exist. Please enter a correct UID.", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener {
            Toast.makeText(this, "Error retrieving data", Toast.LENGTH_SHORT).show()
        }
    }

}