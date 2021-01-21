package com.UAS.oopcoba
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.UAS.oopcoba.Database.AppRoomDB
import com.UAS.oopcoba.Database.Constant
import com.UAS.oopcoba.Database.Penjual
import kotlinx.android.synthetic.main.activity_penjual.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PenjualActivity : AppCompatActivity() {

    val db by lazy { AppRoomDB(this) }
    lateinit var penjualAdapter: PenjualAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_penjual)
        setupListener()
        setupRecyclerView()
    }

    override fun onStart() {
        super.onStart()
        loadUser()
    }

    fun loadUser(){
        CoroutineScope(Dispatchers.IO).launch {
            val allUser = db.PenjualDao().getAllPenjual()
            Log.d("UserActivity", "dbResponse: $allUser")
            withContext(Dispatchers.Main) {
                penjualAdapter.setData(allUser)
            }
        }
    }

    fun setupListener() {
        btn_createPenjual.setOnClickListener {
            intentEdit(0, Constant.TYPE_CREATE)
        }
    }

    fun setupRecyclerView() {
        penjualAdapter = PenjualAdapter(arrayListOf(), object: PenjualAdapter.OnAdapterListener {
            override fun onClick(penjual: Penjual) {
                intentEdit(penjual.id, Constant.TYPE_READ)
            }

            override fun onDelete(penjual: Penjual) {
                deleteDialog(penjual)
            }
            override fun onUpdate(penjual: Penjual) {
                intentEdit(penjual.id, Constant.TYPE_UPDATE)
            }
        })
        list_penjual.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = penjualAdapter
        }
    }

    fun intentEdit(penjualId: Int, intentType: Int ) {
        startActivity(
            Intent(applicationContext, EditPenjualActivity::class.java)
                .putExtra("intent_id", penjualId)
                .putExtra("intent_type", intentType)
        )
    }

    private fun deleteDialog(penjual: Penjual) {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.apply {
            setTitle("Konfirmasi")
            setMessage("Yakin ingin menghapus data ini?")
            setNegativeButton("Batal") { dialogInterface, i ->
                dialogInterface.dismiss()
            }
            setPositiveButton("Hapus") { dialogInterface, i ->
                dialogInterface.dismiss()
                CoroutineScope(Dispatchers.IO).launch {
                    db.PenjualDao().deletePenjual(penjual)
                    loadUser()
                }
            }
        }
        alertDialog.show()
    }

}