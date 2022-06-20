package com.o7solutions.roomdbexample.fragments

import android.app.ActionBar
import android.os.AsyncTask
import android.os.Bundle
import android.text.Layout
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.o7solutions.roomdbexample.R
import com.o7solutions.roomdbexample.databinding.FragmentUpdateBinding
import com.o7solutions.roomdbexample.databinding.FragmentUpdateBindingImpl
import com.o7solutions.roomdbexample.roomdb.AppDb
import com.o7solutions.roomdbexample.roomdb.NoteEntity


class UpdateFragment : DialogFragment() {
    lateinit var binding: FragmentUpdateBinding
    var id: Int? = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_update, container, false)
        getData()


        binding.btnUpdate.setOnClickListener {
            updateData()
        }



        binding.btnClose.setOnClickListener {

            dismiss()
        }
        return binding.root
    }


    fun getData() {
        val bundle = arguments
//
        id = bundle?.getInt("id")
        val title = bundle?.getString("title")
        val desc = bundle?.getString("desc")

        binding.etTitle.setText(title)
        binding.etDesc.setText(desc)
    }


    fun updateData() {

        class UpdateData : AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg p0: Void?): Void? {
                val note = NoteEntity()
                note.desc = binding.etDesc.text.toString()
                note.title = binding.etTitle.text.toString()
                note.id = id

                id?.let { AppDb.getDatabase(requireContext()).noteDao().updateNote(note) }
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.MyContainer, ShowNoteFragment()).commit()
                return null
            }

            override fun onPostExecute(result: Void?) {
                super.onPostExecute(result)


                Toast.makeText(requireContext(), "Update data!", Toast.LENGTH_SHORT).show()
//                dismiss()

            }
        }
        UpdateData().execute()
    }


}