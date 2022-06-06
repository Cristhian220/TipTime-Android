package com.example.tiptime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tiptime.databinding.ActivityMainBinding
import com.example.tiptime.databinding.ActivitySegundaBinding

class SegundaActivity : AppCompatActivity() {
    lateinit var binding: ActivitySegundaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySegundaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setContentView(R.layout.activity_segunda)
        recuperaDados()


    }

    private fun recuperaDados(){

        binding.editGorjeta.text = getString(R.string.tip_amount)
        val nome  = intent.getStringExtra("resultado")

        binding.editGorjeta.text=nome
    }


}