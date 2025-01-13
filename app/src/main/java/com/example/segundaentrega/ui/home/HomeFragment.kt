package com.example.segundaentrega.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.segundaentrega.ui.mascota.MascotaAdapter
import com.example.segundaentrega.databinding.FragmentHomeBinding
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.widget.EditText
import android.widget.Toast
import com.example.segundaentrega.R
import com.example.segundaentrega.data.Especie
import com.example.segundaentrega.data.Mascota
import com.example.segundaentrega.data.MascotaWithDetails
import com.example.segundaentrega.data.Raza
import com.example.segundaentrega.database.AppDatabase
import com.example.segundaentrega.repository.EspecieRepository
import com.example.segundaentrega.repository.MascotaRepository
import com.example.segundaentrega.repository.RazaRepository
import com.example.segundaentrega.ui.especie.EspecieViewModel
import com.example.segundaentrega.ui.especie.EspecieViewModelFactory
import com.example.segundaentrega.ui.raza.RazaViewModel
import com.example.segundaentrega.ui.raza.RazaViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Calendar

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var especieViewModel: EspecieViewModel
    private lateinit var razaViewModel: RazaViewModel
    private lateinit var mascotaAdapter: MascotaAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.addButton2.setOnClickListener {
            showAddPetPopup()
        }

        val database = AppDatabase.getDatabase(requireContext())

        val mascotaDao = database.mascotaDao()
        val mascotaRepository = MascotaRepository(mascotaDao)
        val homeFactory = HomeViewModelFactory(mascotaRepository)

        val especieDao = database.EspecieDao()
        val especieRepository = EspecieRepository(especieDao)
        val especieFactory = EspecieViewModelFactory(especieRepository)

        val razaDao = database.RazaDao()
        val razaRepository = RazaRepository(razaDao)
        val razaFactory = RazaViewModelFactory(razaRepository)

        homeViewModel = ViewModelProvider(this, homeFactory).get(HomeViewModel::class.java)
        especieViewModel = ViewModelProvider(this, especieFactory).get(EspecieViewModel::class.java)
        razaViewModel = ViewModelProvider(this, razaFactory).get(RazaViewModel::class.java)

        mascotaAdapter = MascotaAdapter()
        binding.rvPets.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mascotaAdapter
        }

        especieViewModel.isEspeciesDatabaseEmpty { isEmpty ->
            if (isEmpty) {
                CoroutineScope(Dispatchers.IO).launch {
                    initializeDatabase()
                }
            }
        }

        especieViewModel.especies.observe(viewLifecycleOwner) { listaEspecies ->
            val especieMap = listaEspecies.associateBy { it.id }

            razaViewModel.razas.observe(viewLifecycleOwner) { listaRazas ->
                val razaMap = listaRazas.associateBy { it.id }

                homeViewModel.mascotas.observe(viewLifecycleOwner) { mascotas ->
                    if (mascotas.isNullOrEmpty()) {
                        binding.rvPets.visibility = View.GONE
                        binding.emptyListMessage.visibility = View.VISIBLE
                    } else {
                        binding.rvPets.visibility = View.VISIBLE
                        binding.emptyListMessage.visibility = View.GONE

                        val mascotasConDetalles = mascotas.map { mascota ->
                            MascotaWithDetails(
                                id = mascota.id,
                                nombre = mascota.nombre,
                                fechaNacimiento = mascota.fechaNacimiento,
                                especieNombre = especieMap[mascota.especieId]?.nombre ?: "Desconocido",
                                razaNombre = razaMap[mascota.razaId]?.nombre ?: "Desconocido"
                            )
                        }

                        mascotaAdapter.setMascotas(mascotasConDetalles)
                    }
                }
            }
        }


        return binding.root
    }

    private suspend fun initializeDatabase() {
        initializeEspecie()
    }

    private suspend fun initializeEspecie(): List<Int> {
        val ids = mutableListOf<Int>()

        val perro = Especie(nombre = "Perro")
        val gato = Especie(nombre = "Gato")

        val perroId = especieViewModel.addEspecieAndGetId(perro)
        val gatoId = especieViewModel.addEspecieAndGetId(gato)

        ids.add(perroId)
        ids.add(gatoId)

        return ids
    }

    private fun showAddPetPopup() {
        val dialogView = LayoutInflater.from(context).inflate(R.layout.popup_add_pet, null)
        val dialogBuilder = AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .setTitle("Añadir Mascota")

        val alertDialog = dialogBuilder.show()

        val etName = dialogView.findViewById<EditText>(R.id.et_pet_name)
        val etBirthdate = dialogView.findViewById<EditText>(R.id.et_pet_birthdate)
        val etSpecies = dialogView.findViewById<EditText>(R.id.et_pet_species)
        val etBreed = dialogView.findViewById<EditText>(R.id.et_pet_breed)

        var selectedSpeciesId: Int? = null
        var selectedBreedId: Int? = null

        var especies = especieViewModel.especies.value ?: emptyList()
        val especieNames = especies.map { it.nombre }.toTypedArray()
        val especieIds = especies.map { it.id }

        var razas = razaViewModel.razas.value ?: emptyList()
        val razaNames = razas.map { it.nombre }.toTypedArray()
        val razaIds = razas.map { it.id }

        etBirthdate.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                requireContext(),
                { _, selectedYear, selectedMonth, selectedDay ->
                    val formattedDate = String.format("%04d-%02d-%02d", selectedYear, selectedMonth + 1, selectedDay)
                    etBirthdate.setText(formattedDate)
                },
                year,
                month,
                day
            )
            datePickerDialog.show()
        }

        etSpecies.setOnClickListener {
            especies = especieViewModel.especies.value ?: emptyList()
            if (especies.isNotEmpty()) {
                AlertDialog.Builder(requireContext())
                    .setTitle("Seleccionar especie")
                    .setItems(especieNames) { _, which ->
                        if (which in especieIds.indices) { // Validar que el índice exista
                            etSpecies.setText(especieNames[which])
                            selectedSpeciesId = especieIds[which]
                        } else {
                            Toast.makeText(context, "Selección inválida", Toast.LENGTH_SHORT).show()
                        }
                    }
                    .show()
            } else {
                Toast.makeText(context, "No hay especies disponibles", Toast.LENGTH_SHORT).show()
            }
        }

        etBreed.setOnClickListener {
            if (razas.isNotEmpty()) {
                AlertDialog.Builder(requireContext())
                    .setTitle("Seleccionar raza")
                    .setItems(razaNames) { _, which ->
                        etBreed.setText(razaNames[which])
                        selectedBreedId = razaIds[which]
                    }
                    .show()
            } else {
                showAddBreedPopup { newBreed ->
                    razaViewModel.razas
                    etBreed.setText(newBreed.nombre)
                    selectedBreedId = newBreed.id
                    alertDialog.dismiss()
                }
            }
        }

        dialogView.findViewById<View>(R.id.btn_save_pet).setOnClickListener {
            val name = etName.text.toString()
            val birthdate = etBirthdate.text.toString()

            if (name.isNotEmpty() && birthdate.isNotEmpty() && selectedSpeciesId != null && selectedBreedId != null) {
                val newPet = Mascota(
                    id = 0,
                    nombre = name,
                    fechaNacimiento = birthdate,
                    especieId = selectedSpeciesId!!,
                    razaId = selectedBreedId!!
                )
                addPetToRepository(newPet)
                alertDialog.dismiss()
            } else {
                Toast.makeText(context, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showAddBreedPopup(onBreedCreated: (Raza) -> Unit) {
        val dialogView = LayoutInflater.from(context).inflate(R.layout.popup_add_breed, null)
        val dialogBuilder = AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .setTitle("Añadir Raza")

        val alertDialog = dialogBuilder.show()

        val etBreedName = dialogView.findViewById<EditText>(R.id.et_breed_name)
        val etBreedSpecies = dialogView.findViewById<EditText>(R.id.et_breed_species)

        val especies = especieViewModel.especies.value ?: emptyList()

        var selectedSpeciesId: Int? = null

        etBreedSpecies.setOnClickListener {
            if (especies.isNotEmpty()) {
                val especieNames = especies.map { it.nombre }.toTypedArray()
                val especieIds = especies.map { it.id }

                AlertDialog.Builder(requireContext())
                    .setTitle("Seleccionar especie")
                    .setItems(especieNames) { _, which ->
                        etBreedSpecies.setText(especieNames[which])
                        selectedSpeciesId = especieIds[which]
                    }
                    .show()
            } else {
                Toast.makeText(context, "No hay especies disponibles", Toast.LENGTH_SHORT).show()
            }
        }

        dialogView.findViewById<View>(R.id.btn_save_breed).setOnClickListener {
            val breedName = etBreedName.text.toString()

            if (breedName.isNotEmpty() && selectedSpeciesId != null) {
                val newBreed = Raza(
                    id = 0,
                    nombre = breedName,
                    especieId = selectedSpeciesId!!
                )
                addBreedToRepository(newBreed)

                onBreedCreated(newBreed)
                alertDialog.dismiss()
            } else {
                Toast.makeText(context, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun addPetToRepository(mascota: Mascota) {
        CoroutineScope(Dispatchers.IO).launch {
            homeViewModel.addMascota(mascota)
            CoroutineScope(Dispatchers.Main).launch {
                Toast.makeText(context, "Mascota añadida con éxito", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun addBreedToRepository(raza: Raza) {
        CoroutineScope(Dispatchers.IO).launch {
            razaViewModel.addRaza(raza)
            CoroutineScope(Dispatchers.Main).launch {
                Toast.makeText(context, "Raza añadida con éxito", Toast.LENGTH_SHORT).show()
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}