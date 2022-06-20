package com.o7solutions.roomdbexample.fragments

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.o7solutions.roomdbexample.R
import com.o7solutions.roomdbexample.adapter.RecyclerViewAdapterShowNotes
import com.o7solutions.roomdbexample.databinding.FragmentShowNoteListBinding
import com.o7solutions.roomdbexample.roomdb.AppDb
import com.o7solutions.roomdbexample.roomdb.NoteEntity


class ShowNoteFragment : Fragment() {
lateinit var binding:FragmentShowNoteListBinding
lateinit var adapter : RecyclerViewAdapterShowNotes
 lateinit var list:List<NoteEntity>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding= DataBindingUtil.inflate(inflater,R.layout.fragment_show_note_list, container, false)
       list= listOf()

        binding.list.layoutManager=LinearLayoutManager(requireContext())

        getDetails()

        return binding.root
    }


    fun getDetails(){
        class GetInfo: AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg p0: Void?):Void?{
                 list= AppDb.getDatabase(requireContext()).noteDao().getData()
                return null
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onPostExecute(result: Void?) {
                super.onPostExecute(result)

                adapter=RecyclerViewAdapterShowNotes(list,this@ShowNoteFragment)
                binding.list.adapter=adapter
                adapter.notifyDataSetChanged()

            }

        }
        GetInfo().execute()



    }

   fun myNote(note: NoteEntity){

       class DeleteItem:AsyncTask<Void,Void, Void>() {
           @SuppressLint("NotifyDataSetChanged")
           override fun doInBackground(vararg p0: Void?): Void? {

               AppDb.getDatabase(requireContext()).noteDao().deleteNote(note)
               return null
           }

           override fun onPostExecute(result: Void?) {
               super.onPostExecute(result)

               getDetails()
               Toast.makeText(requireContext(),"Note Delete!",Toast.LENGTH_SHORT).show()
           }
       }
       DeleteItem().execute()


   }
}