package com.UAS.oopcoba

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.UAS.oopcoba.Database.AppRoomDB
import com.UAS.oopcoba.Database.Constant
import com.UAS.oopcoba.Database.Penjual
import kotlinx.android.synthetic.main.activity_edit_penjual.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditPenjualActivity : AppCompatActivity() {

    val db by lazy { AppRoomDB(this) }
    private var penjualId: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_penjual)
        setupListener()
        setupView()
    }

    fun setupListener(){
        btn_savePenjual.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {
                db.PenjualDao().addPenjual(
                    Penjual(0, txt_nama.text.toString(), txt_username.text.toString())
                )
                finish()
            }
        }
        btn_updatePenjual.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {
                db.PenjualDao().updatePenjual(
                    Penjual(penjualId, txt_nama.text.toString(), txt_username.text.toString())
                )
                finish()
            }
        }
    }

    fun setupView() {
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val intentType = intent.getIntExtra("intent_type", 0)
        when (intentType) {
            Constant.TYPE_CREATE -> {
                btn_updatePenjual.visibility = View.GONE
            }
            Constant.TYPE_READ -> {
                btn_savePenjual.visibility = View.GONE
                btn_updatePenjual.visibility = View.GONE
                getPenjual()
            }
            Constant.TYPE_UPDATE -> {
                btn_savePenjual.visibility = View.GONE
                getPenjual()
            }
        }
    }

    fun getPenjual() {
        penjualId = intent.getIntExtra("intent_id", 0)
        CoroutineScope(Dispatchers.IO).launch {
            val pnj =  db.PenjualDao().getPenjual( penjualId )[0]
            txt_nama.setText( pnj.nama )
            txt_username.setText( pnj.username )
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}