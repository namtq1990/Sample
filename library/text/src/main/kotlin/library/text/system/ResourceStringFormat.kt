package library.text.system

import android.content.res.Resources
import library.text.presentation.StringFormat

class ResourceStringFormat(private val resources: Resources) : StringFormat {

    override fun getString(id: Int) = resources.getString(id)

    @Suppress("SpreadOperator")
    override fun getString(id: Int, vararg formatArgs: Any) = resources.getString(id, *formatArgs)

    override fun getQuantityString(id: Int, quantity: Int): String {
        return resources.getQuantityString(id, quantity)
    }

    @Suppress("SpreadOperator")
    override fun getQuantityString(id: Int, quantity: Int, vararg formatArgs: Any): String {
        return resources.getQuantityString(id, quantity, *formatArgs)
    }
}
