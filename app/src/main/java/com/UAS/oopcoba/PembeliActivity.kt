package com.UAS.oopcoba
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.UAS.oopcoba.Database.AppRoomDB
import com.UAS.oopcoba.Database.Constant
import com.UAS.opp.Database.Pembeli
import kotlinx.android.synthetic.main.activity_pembeli.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.collections.arrayListOf as arrayListOf1

class PembeliActivity : AppCompatActivity() {

    val db by lazy { AppRoomDB(this) }
    lateinit var pembeliAdapter: PembeliAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pembeli)
        setupListener()
        setupRecyclerView()
    }

    override fun onStart() {
        super.onStart()
        loadPembeli()
    }

    fun loadPembeli() {
        CoroutineScope(Dispatchers.IO).launch {
            val allPembeli = db.pembeliDao().getAllPembeli()
            Log.d("PembeliActivity", "dbResponse: $allPembeli")
            withContext(Dispatchers.Main) {
                pembeliAdapter.setData(allPembeli)
            }
        }
    }

    fun setupListener() {
        btn_createPembeli.setOnClickListener {
            intentEdit(0, Constant.TYPE_CREATE)
        }
    }

    fun setupRecyclerView() {
        pembeliAdapter = PembeliAdapter(arrayListOf1(), object: PembeliAdapter.OnAdapterListener {
            override fun onClick(pembeli: Pembeli) {
                intentEdit(pembeli.id, Constant.TYPE_READ)
            }

            override fun onDelete(pembeli: Pembeli) {
                deleteDialog(pembeli)
            }
            override fun onUpdate(pembeli: Pembeli) {
                // edit data
                intentEdit(pembeli.id, Constant.TYPE_UPDATE)
            }

        })
        list_pembeli.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = pembeliAdapter
        }
    }

    fun intentEdit(pembeliId: Int, intentType: Int ) {
        startActivity(
            Intent(applicationContext, EditPembeliActivity::class.java)
                .putExtra("intent_id", pembeliId)
                .putExtra("intent_type", intentType)
        )
    }

    private fun deleteDialog(pembeli: Pembeli) {
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
                    db.pembeliDao().deletePembeli(pembeli)
                    loadPembeli()
                }
            }
        }
        alertDialog.show()
    }
}