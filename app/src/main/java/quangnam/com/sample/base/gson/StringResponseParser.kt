package quangnam.com.sample.base.gson

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import quangnam.com.sample.data.network.response.StringResponse
import java.lang.reflect.Type

/**
 * Created by quangnam on 2/23/18.
 * Project Sample
 */

class StringResponseParser : JsonDeserializer<StringResponse> {

    @Throws(JsonParseException::class)
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): StringResponse {
        var response: StringResponse
        try {
            val clazz = typeOfT as Class<*>
            response = clazz.newInstance() as StringResponse
        } catch (e: Exception) {
            e.printStackTrace()
            response = StringResponse()
        }

        response.value = json.asJsonPrimitive.asString

        return response
    }
}
