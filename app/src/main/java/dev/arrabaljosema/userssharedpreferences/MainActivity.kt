package dev.arrabaljosema.userssharedpreferences

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dev.arrabaljosema.userssharedpreferences.databinding.ActivityMainBinding

// 6º paso de la interface, se implementa en esta clase
class MainActivity : AppCompatActivity(), OnClickListener {

    private lateinit var userAdapter: UserAdapter
    private lateinit var linearLayoutManager: RecyclerView.LayoutManager
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 1er paso, se elimina setContentView y se sustituye inicializando a binding (vídeo 96)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val preferences = getPreferences(Context.MODE_PRIVATE)
        val isFirstTime = preferences.getBoolean(getString(R.string.sp_first_time), true)
        Log.i("SP", "${getString(R.string.sp_first_time)} = $isFirstTime")

        // 2º paso SP
        if (isFirstTime) {
            MaterialAlertDialogBuilder(this)
                .setTitle(R.string.dialog_title)
                .setPositiveButton(R.string.dialog_confirm, { dialogInterface, i ->
                    preferences.edit().putBoolean(getString(R.string.sp_first_time), false).commit()
                })
                .show()
        }

        // 2ª se pasa por el adaptador
        userAdapter = UserAdapter(getUsers(), this) // 6ª Se sustituye mutableListOf por getUsers
        //7º paso de la interface, es un listener ya
        // implemntedo por lo que se pone this.

        // 3º linearLayoutManager
        linearLayoutManager = LinearLayoutManager(this)

        // 4º binding al recyclerView
        binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = linearLayoutManager
            adapter = userAdapter
        }
    }

    // 5º Crear usuarios con un nuevo método devolviendo un array (vídeo 97)
    private fun getUsers(): MutableList<User> {
        val users = mutableListOf<User>()

        val josema = User(
            1,
            "Josema",
            "Arrabal",
            "https://cadenaser00.epimg.net/ser/imagenes/2018/11/29/radio_algeciras/1543518175_172309_1543519658_noticia_normal.jpg"
        )
        val reyes = User(
            2,
            "Reyes",
            "Del Castillo",
            "https://media-exp1.licdn.com/dms/image/D4D03AQEM9emoA_fQJA/profile-displayphoto-shrink_200_200/0/1636626588260?e=1645660800&v=beta&t=04uIUlLvn3Oq-WoLDzSoKX-UUnpjUH3De60_XxF_d6Y"
        )
        val abril = User(
            3,
            "Abril",
            "Arrabal",
            "https://lavozdeoromana.com/wp-content/uploads/2021/06/visita-arrabal-1030x615.jpeg"
        )
        val rick = User(
            4,
            "Rick",
            "Parecefalso",
            "https://www.elrincondewally.com/wp-content/uploads/2018/10/9mz6ld.jpg"
        )

        users.add(josema)
        users.add(reyes)
        users.add(abril)
        users.add(rick)
        users.add(josema)
        users.add(reyes)
        users.add(abril)
        users.add(rick)
        users.add(josema)
        users.add(reyes)
        users.add(abril)
        users.add(rick)
        users.add(josema)
        users.add(reyes)
        users.add(abril)
        users.add(rick)
        users.add(josema)
        users.add(reyes)
        users.add(abril)
        users.add(rick)


        return users
    }

    override fun onClick(user: User, position: Int) {
        // 8º de la interface, donde se controlará el evento de este click
        Toast.makeText(this, "${position + 1}: ${user.getFullname()}", Toast.LENGTH_SHORT).show()
    }
}