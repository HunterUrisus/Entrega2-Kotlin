package com.example.segundaentrega.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.segundaentrega.MascotaAdapter
import com.example.segundaentrega.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        val recyclerView = binding.rvPets
        recyclerView.layoutManager = LinearLayoutManager(context)

        // Observa los datos del ViewModel y configura el adaptador
        homeViewModel.mascotas.observe(viewLifecycleOwner) { mascotas ->
            recyclerView.adapter = MascotaAdapter(mascotas)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}