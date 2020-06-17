package library.text.presentation

interface StringFormat {
    fun getString(id: Int): String
    fun getString(id: Int, vararg formatArgs: Any): String
    fun getQuantityString(id: Int, quantity: Int): String
    fun getQuantityString(id: Int, quantity: Int, vararg formatArgs: Any): String
}
