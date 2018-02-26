package quangnam.com.sample.base.gson;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import quangnam.com.sample.data.network.response.StringResponse;

/**
 * Created by quangnam on 2/23/18.
 * Project Sample
 */

public class StringResponseParser implements JsonDeserializer<StringResponse> {

    @Override
    public StringResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        StringResponse response;
        try {
            Class clazz = (Class) typeOfT;
            response = (StringResponse) clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            response = new StringResponse();
        }
        response.setValue(json.getAsJsonPrimitive().getAsString());

        return response;
    }
}
