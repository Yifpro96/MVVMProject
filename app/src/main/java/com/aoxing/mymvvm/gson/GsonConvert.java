package com.aoxing.mymvvm.gson;

import com.aoxing.erpproject.common.gson.typebuilder.TypeBuilder;
import com.lzy.okgo.convert.Converter;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

import okhttp3.Response;

public class GsonConvert<T> implements Converter<T> {

    private Class mClazz;
    private boolean mIsList;

    public GsonConvert(Class c) {
        mClazz = c;
    }

    public GsonConvert(Class c, boolean isList) {
        mIsList = isList;
        mClazz = c;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T convertResponse(Response response) throws Exception {
        String body = response.body().string();
        if (mClazz == String.class) {
            JSONObject jsonObject = new JSONObject(body);
            StringResult result = new StringResult();
            result.setIsSucceed(jsonObject.optBoolean("IsSucceed"));
            result.setMessage(jsonObject.optString("Message"));
            result.setStatusCode(jsonObject.optInt("StatusCode"));
            result.setEntity(jsonObject.optString("Entity"));
            return (T) result.toYifproResult();
        }
        Type type;
        if (mIsList) {
            type = TypeBuilder
                    .newInstance(YifproResult.class)
                    .beginSubType(List.class)
                    .addTypeParam(mClazz)
                    .endSubType()
                    .build();
        } else {
            type = TypeBuilder
                    .newInstance(YifproResult.class)
                    .addTypeParam(mClazz)
                    .build();
        }
        return GsonCreator.getInstance().fromJson(body, type);
    }
}
