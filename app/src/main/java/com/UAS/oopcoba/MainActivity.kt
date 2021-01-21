package com.UAS.oopcoba
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_pembeli.setOnClickListener{
            val intent = Intent(this,PembeliActivity::class.java)
            startActivity(intent)
        }

        btn_penjual.setOnClickListener{
            val intent = Intent(this, PenjualActivity::class.java)
            startActivity(intent)
        }
    }
}