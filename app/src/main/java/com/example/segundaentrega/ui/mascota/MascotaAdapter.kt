package com.example.segundaentrega.ui.mascota

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.segundaentrega.data.Mascota
import com.example.segundaentrega.data.MascotaWithDetails
import com.example.segundaentrega.databinding.PetLayoutBinding

class MascotaAdapter : RecyclerView.Adapter<MascotaAdapter.MascotaViewHolder>() {

    private var mascotas: List<MascotaWithDetails> = emptyList()

    inner class MascotaViewHolder(private val binding: PetLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(mascota: MascotaWithDetails) {
            binding.apply {
                binding.mascota = mascota
                binding.executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MascotaViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = PetLayoutBinding.inflate(layoutInflater, parent, false)
        return MascotaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MascotaViewHolder, position: Int) {
        holder.bind(mascota = mascotas[position])
    }

    override fun getItemCount(): Int = mascotas.size

    // Metodo para actualizar los datos manualmente
    fun setMascotas(nuevasMascotas: List<MascotaWithDetails>) {
        this.mascotas = nuevasMascotas
        notifyDataSetChanged()
    }
}


class MascotaDiffCallback : DiffUtil.ItemCallback<Mascota>() {
    override fun areItemsTheSame(oldItem: Mascota, newItem: Mascota): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Mascota, newItem: Mascota): Boolean {
        return oldItem == newItem
    }
}
