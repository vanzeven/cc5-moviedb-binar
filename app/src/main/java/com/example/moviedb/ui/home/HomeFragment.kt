package com.example.moviedb.ui.home

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.moviedb.R
import com.example.moviedb.adapter.HomeAdapter
import com.example.moviedb.databinding.FragmentHomeBinding
import com.example.moviedb.model.GetPopularItem
import com.example.moviedb.service.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

//    private lateinit var noteViewModel: NoteViewModel
//    private lateinit var adapter: NoteAdapter

    private lateinit var sharedPreferences: SharedPreferences
    private val spLogin = "spLogin"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPreferences = requireContext().getSharedPreferences(spLogin, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val username = "Welcome, " + sharedPreferences.getString("username_key", null) + "!"
        binding.tvUser.text = username

//        val application = requireNotNull(this.activity).application
//        val dataSource = AppDatabase.getInstance(application).noteDatabaseDao()
//        val viewModelFactory = CreateViewModelFactory(dataSource, application)
//        noteViewModel = ViewModelProvider(this, viewModelFactory)[NoteViewModel::class.java]

//        val recyclerView = binding.rvNote

//        adapter = NoteAdapter(object: NoteItemClickListener {
//            override fun onDeleteMenuClicked(item: NoteEntity) {
//                adapter.deleteItem(item)
//                noteViewModel.deleteNote(item)
//                Toast.makeText(requireContext(), "Note berhasil dihapus", Toast.LENGTH_SHORT).show()
//            }
//
//            override fun onEditMenuClicked(item: NoteEntity) {
//                Toast.makeText(requireContext(), "To be implemented", Toast.LENGTH_SHORT).show()
//            }
//        })

//        recyclerView.layoutManager = LinearLayoutManager(requireContext())
//        recyclerView.adapter = adapter

//        noteViewModel.getNote().observe(viewLifecycleOwner){
//            adapter.setItems(it)
//        }


        binding.btnProfile.setOnClickListener {
            findNavController().navigate(R.id.action_noteFragment_to_profileFragment)
        }

        fetchAllData()
    }

    private fun fetchAllData() {
        ApiClient.instance.getPopular().enqueue(object : Callback<List<GetPopularItem>> {
            override fun onResponse(
                call: Call<List<GetPopularItem>>,
                response: Response<List<GetPopularItem>>
            ) {
                val body = response.body()
                val code = response.code()
                if (code == 200) {
                    Toast.makeText(requireContext(), "Fetch success", Toast.LENGTH_SHORT).show()
                    showList(body)
                    binding.pb.visibility = View.GONE
                } else {
                    Toast.makeText(requireContext(), "Error fetching data", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<GetPopularItem>>, t: Throwable) {
//                binding.pb.visibility = View.GONE
                Toast.makeText(requireContext(), "Failure", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun showList(data: List<GetPopularItem>?) {
        val adapter = HomeAdapter(object : HomeAdapter.OnClickListener {
            override fun onClickItem(data: GetPopularItem) {
//                Toast.makeText(requireContext(), "to be implemented", Toast.LENGTH_SHORT).show()
            }
        })

        adapter.submitData(data)
        binding.rvMovie.adapter = adapter
    }
}