package com.o7solutions.roomdbexample.adapter

import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.o7solutions.roomdbexample.MainActivity

import com.o7solutions.roomdbexample.databinding.FragmentShowNoteBinding
import com.o7solutions.roomdbexample.fragments.ShowNoteFragment
import com.o7solutions.roomdbexample.fragments.UpdateFragment
import com.o7solutions.roomdbexample.roomdb.NoteEntity


class RecyclerViewAdapterShowNotes(private val list: List<NoteEntity> ,var context:ShowNoteFragment

) : RecyclerView.Adapter<RecyclerViewAdapterShowNotes.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentShowNoteBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]

        holder.textViewTitle.text=item.title
        holder.textViewDesc.text=item.desc
        holder.btnDel.setOnClickListener {

            context.myNote(item)
        }

        holder.layout.setOnClickListener {

            val obj=UpdateFragment()
            val bundle=Bundle()

//            bundle.putString("id",item.id.toString())
            bundle.putString("title",item.title)
            bundle.putString("desc",item.desc)
            obj.arguments=bundle
            obj.isCancelable=false


            obj.show(context.requireActivity().supportFragmentManager,"updateFragment")




        }





          }

    override fun getItemCount(): Int = list.size

    inner class ViewHolder(binding: FragmentShowNoteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val textViewTitle: TextView = binding.tvTitle
        val textViewDesc: TextView = binding.tvDesc
        val btnDel: Button = binding.btnDelete
        val layout: LinearLayout = binding.layout


    }

}