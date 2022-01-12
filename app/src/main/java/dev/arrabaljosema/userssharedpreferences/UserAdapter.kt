package dev.arrabaljosema.userssharedpreferences

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import dev.arrabaljosema.userssharedpreferences.databinding.ItemUserBinding

//Se  especifica el tipo y por otra parte se debe extender de la clase viewHolder
// (inner clase interna). Recibe vista de tipo view y hereda de RecyclerView.
class UserAdapter(private val users: List<User>) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context

        val view = LayoutInflater.from(context).inflate(R.layout.item_user, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = users.get(position)

        with(holder) {
            binding.tvOrder.text = (position + 1).toString()
            binding.tvName.text = user.getFullname()

            // Glide para el adaptador
            Glide.with(context)
                .load(user.url) // Carga de la url
                .diskCacheStrategy(DiskCacheStrategy.ALL) // Caché
                .centerCrop()
                .circleCrop() // Función para que la imagen sea redonda
                .into(binding.imgPhoto) // Conexión con el imageView del item_user.xml
        }
    }

    override fun getItemCount(): Int = users.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemUserBinding.bind(view)
    }
}