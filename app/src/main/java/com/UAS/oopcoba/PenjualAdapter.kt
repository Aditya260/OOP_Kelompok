package com.UAS.oopcoba

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.UAS.oopcoba.Database.Penjual
import kotlinx.android.synthetic.main.adapter_penjual.view.*

class PenjualAdapter (private val AllPenjual: ArrayList<Penjual>, private val listener: OnAdapterListener) : RecyclerView.Adapter<PenjualAdapter.PenjualViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PenjualViewHolder {
        return PenjualViewHolder(
            LayoutInflater.from(parent.context).inflate( R.layout.adapter_penjual, parent, false)
        )
    }

    override fun getItemCount() = AllPenjual.size

    override fun onBindViewHolder(holder: PenjualViewHolder, position: Int) {
        val penjual = AllPenjual[position]
        holder.view.text_username.text = penjual.username
        holder.view.text_username.setOnClickListener {
            listener.onClick(penjual)
        }

        holder.view.icon_delete.setOnClickListener {
            listener.onDelete(penjual)
        }
        holder.view.icon_edit.setOnClickListener {
            listener.onUpdate(penjual)
        }
    }

    class PenjualViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    fun setData(list: List<Penjual>) {
        AllPenjual.clear()
        AllPenjual.addAll(list)
        notifyDataSetChanged()
    }

    interface OnAdapterListener {
        fun onClick(penjual: Penjual)
        fun onDelete(penjual: Penjual)
        fun onUpdate(penjual: Penjual)
    }

}