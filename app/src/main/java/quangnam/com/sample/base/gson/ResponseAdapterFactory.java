package quangnam.com.sample.base.gson;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

import quangnam.com.sample.data.exception.ApiException;
import quangnam.com.sample.data.network.response.BaseErrorResponse;
import quangnam.com.sample.data.network.response.ResponseWrapper;
import quangnam.com.sample.util.Constant;

/**
 * Created by quangnam on 2/26/18.
 * Project Sample
 */

public class ResponseAdapterFactory implements TypeAdapterFactory {


    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        final TypeAdapter<T> delegate = gson.getDelegateAdapter(this, type);
        final TypeAdapter<JsonElement> elementTypeAdapter = gson.getAdapter(JsonElement.class);
        TypeToken<ResponseWrapper<BaseErrorResponse>> token = new TypeToken<ResponseWrapper<BaseErrorResponse>>(){};
        final TypeAdapter<ResponseWrapper<BaseErrorResponse>> errorAdapter = gson.getDelegateAdapter(this, token);

        if (!type.getRawType().isAssignableFrom(ResponseWrapper.class)) {
            return delegate;
        }

        return new TypeAdapter<T>() {
            @Override
            public void write(JsonWriter out, T value) throws IOException {
                delegate.write(out, value);
            }

            @Override
            public T read(JsonReader in) throws IOException {
                JsonElement element = elementTypeAdapter.read(in);

                if (element.isJsonObject()) {
                    JsonObject object = element.getAsJsonObject();
                    if (object.has(ResponseWrapper.STATUS)) {
                        String status = object.get(ResponseWrapper.STATUS).getAsString();
                        if (!status.equalsIgnoreCase(Constant.ResponseStatus.success)) {
                            ResponseWrapper<BaseErrorResponse> response = errorAdapter.fromJsonTree(element);
                            throw new ApiException(Integer.parseInt(response.getCode()),
                                    response.getObject().getValue());
                        }
                    }
                }

                return delegate.fromJsonTree(element);
            }
        };
    }
}
