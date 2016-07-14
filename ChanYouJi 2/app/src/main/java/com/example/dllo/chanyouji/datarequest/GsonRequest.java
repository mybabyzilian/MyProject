package com.example.dllo.chanyouji.datarequest;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;

/**
 * Created by dllo on 16/6/21.
 */
public class GsonRequest<T> extends Request<T> {
    private Response.Listener<T> mListener;
    private Gson gson;
    private Class<T> mClass;
    public GsonRequest(int method, String url,Class<T> clazz,Response.Listener<T> listener,Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        gson = new Gson();
        mClass = clazz;
        mListener = listener;
    }

    public GsonRequest(String url, Class<T> clazz, Response.Listener<T> listener,
                       Response.ErrorListener errorListener) {
        this(Method.GET, url, clazz, listener, errorListener);
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers));
            return Response.success(gson.fromJson(jsonString, mClass),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        }


    }

    @Override
    protected void deliverResponse(T response) {

        mListener.onResponse(response);

    }
}
