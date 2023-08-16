package com.example.notemvvm

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteAdapter(private val mContext: Context, private val lst: MutableList<Note>, var rcvInterface: RcvInterface): RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {
    inner class NoteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.custom_rcv, parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.itemView.apply {
            findViewById<TextView>(R.id.txtTitle).text = lst[position].title
            findViewById<TextView>(R.id.txtContents).text = lst[position].contents
        }

        holder.itemView.setOnClickListener {
            rcvInterface.clickItem(position)
        }
        holder.itemView.setOnLongClickListener {
            rcvInterface.longCLickItem(position)
        }
    }

    override fun getItemCount(): Int {
        return lst.size
    }
}