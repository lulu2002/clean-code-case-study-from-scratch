package me.lulu.cleancodecasestudy

data class User(
    val id: String,
    val name: String
) {
    override fun equals(other: Any?): Boolean {
        if (other !is User) return false
        return id == other.id
    }
}