package com.aoxing.mymvvm.gson;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.lzy.okgo.convert.Converter;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Response;
import okhttp3.ResponseBody;

public class CustomConvert<T> implements Converter<T> {

    private Type type;
    private Class<T> clazz;

    public CustomConvert() {
    }

    public CustomConvert(Type type) {
        this.type = type;
    }

    public CustomConvert(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public T convertResponse(Response response) throws IOException {
        ResponseBody body = response.body();
        if (body == null) {
            return null;
        } else if (clazz == String.class) {
            return (T) body.string();
        } else {
            T data;
            Gson gson = new Gson();
            JsonReader jsonReader = new JsonReader(body.charStream());
            if (type != null) {
                data = gson.fromJson(jsonReader, type);
            } else if (clazz != null) {
                data = gson.fromJson(jsonReader, clazz);
            } else {
                Type genType = getClass().getGenericSuperclass();
                Type type = ((ParameterizedType) genType).getActualTypeArguments()[0];
                data = gson.fromJson(jsonReader, type);
            }
            return data;
        }
    }
}
