package com.example.noteapp.ui.main.fragment
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.noteapp.R
import com.example.noteapp.ui.main.adapters.CardClickListener
import com.example.noteapp.ui.main.adapters.PinnedAdapter
import com.example.noteapp.ui.main.adapters.UpcomingAdapter

import com.example.noteapp.ui.main.data.local.entities.NoteEntity
import com.example.noteapp.databinding.FragmentHomeBinding
import com.example.noteapp.ui.main.view_models.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.ArrayList

@AndroidEntryPoint
class HomeFragment : Fragment(),CardClickListener{

    private val viewModel: NoteViewModel by viewModels()

    private lateinit var binding: FragmentHomeBinding;

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false)
        binding.frHome = this

        setupPinnedRecyclerview()
        setupUpcomingRecyclerview()


        // Inflate the layout for this fragment
        return binding.root
    }

    private fun setupPinnedRecyclerview() {
        viewModel.liveData.observe(viewLifecycleOwner){ listData ->
            val data: ArrayList<NoteEntity> = ArrayList()
            listData.forEach{
                if (it.notesModel.pinned){
                    data.add(it)
                }
            }
            if (data.isEmpty())
                binding.pinnedCon.visibility = View.GONE
            else
                binding.pinnedCon.visibility = View.VISIBLE

            binding.pinnedRv.adapter = PinnedAdapter(data,this)
        }
    }

    private fun setupUpcomingRecyclerview() {
        viewModel.liveData.observe(viewLifecycleOwner){ listData ->

            val data: ArrayList<NoteEntity> = ArrayList()
            listData.forEach{
                if (!it.notesModel.pinned){
                    data.add(it)
                }
            }

            if (data.isEmpty())
                binding.textView3.visibility = View.VISIBLE
            else
                binding.textView3.visibility = View.GONE

            binding.upcomingRv.adapter = UpcomingAdapter(data,this)

        }
    }

    fun fabOnClick(view: View){
        view.findNavController().navigate(R.id.action_homeFragment_to_singleNoteFragment)
    }

    override fun onItemClickListener(noteEntity: NoteEntity) {
        val bundle = bundleOf("datamodel" to noteEntity)
        Navigation.findNavController(binding.root).navigate(R.id.action_homeFragment_to_singleNoteFragment,bundle)
    }

    override fun onMenuItemClickListener(imageFilterButton: View, noteEntity: NoteEntity) {
        val popMenu = PopupMenu(requireActivity(), imageFilterButton)
        popMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener {
            when(it.itemId){
                R.id.dlt ->{
                    deleteNoteFromDb(noteEntity)
                    true
                }
                else -> return@OnMenuItemClickListener false
            }
        })
        popMenu.inflate(R.menu.actions)
        popMenu.show()
    }

    private fun deleteNoteFromDb(noteEntity: NoteEntity) {
        viewModel.deleteNote(noteEntity)
    }
}