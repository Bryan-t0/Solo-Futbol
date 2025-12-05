package com.example.solofutbol.ui.usuarios

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Switch
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.solofutbol.R
import com.example.solofutbol.datos.Usuario

class UsuariosAdapter(private val listener: Listener) :
    RecyclerView.Adapter<UsuariosAdapter.VH>() {

    interface Listener {
        fun onEditar(item: Usuario)
        fun onEliminar(item: Usuario)
    }

    private val data = mutableListOf<Usuario>()

    fun submit(list: List<Usuario>) {
        data.clear()
        data.addAll(list)
        notifyDataSetChanged()
    }

    class VH(v: View) : RecyclerView.ViewHolder(v) {
        val txtUsuario: TextView = v.findViewById(R.id.txtUsuario)
        val txtNombre: TextView = v.findViewById(R.id.txtNombre)
        val txtRut: TextView = v.findViewById(R.id.txtRut)
        val swAdmin: Switch = v.findViewById(R.id.swAdminRow)
        val btnEditar: ImageButton = v.findViewById(R.id.btnEditar)
        val btnEliminar: ImageButton = v.findViewById(R.id.btnEliminar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_usuario, parent, false)
        return VH(v)
    }

    override fun onBindViewHolder(h: VH, pos: Int) {
        val item = data[pos]

        h.txtUsuario.text = item.usuario
        h.txtNombre.text = "${item.nombre} ${item.apellido}"
        h.txtRut.text = item.rut
        h.swAdmin.isChecked = item.esAdmin
        h.swAdmin.isEnabled = false

        h.btnEditar.setOnClickListener { _ ->
            listener.onEditar(item)
        }
        h.btnEliminar.setOnClickListener { _ ->
            listener.onEliminar(item)
        }
    }


    override fun getItemCount(): Int = data.size
}
