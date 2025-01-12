package com.example.segundaentrega

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.segundaentrega.data.Mascota
import com.example.segundaentrega.databinding.PetLayoutBinding


class MascotaAdapter(
    private val mascotas: List<Mascota>
) : RecyclerView.Adapter<MascotaAdapter.MascotaViewHolder>() {

    inner class MascotaViewHolder(val binding: PetLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MascotaViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = PetLayoutBinding.inflate(layoutInflater, parent, false)
        return MascotaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MascotaViewHolder, position: Int) {
        val mascota = mascotas[position]
        holder.binding.textDashboard.text = mascota.nombre
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int = mascotas.size
}