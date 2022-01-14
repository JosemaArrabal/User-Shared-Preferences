package dev.arrabaljosema.userssharedpreferences

// 1er paso en para la creación de interfaces, el segundo en UserAdapter
interface OnClickListener {
    fun onClick(user: User, position: Int)
}