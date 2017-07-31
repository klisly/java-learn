package reflection.okhttp;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by wizardholy on 2017/7/20.
 * OkHttp请求Hook获取所有的数据
 */
public class OkHttpTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("http://square.github.io/okhttp/")
                .build();

        Response response = client.newCall(request).execute();
//        response.networkResponse().body().charStream();
        response.request().url();
        Object object = response;
        Class<?> requestCls = Class.forName("okhttp3.Request");
        Class<?> responseCls = Class.forName("okhttp3.Response");
        Method method = responseCls.getMethod("code");
        int code = (Integer) method.invoke(object);
        System.out.println("code:" + code);

        method = responseCls.getMethod("request");
        Object requestObj = method.invoke(object);
        System.out.println("request:" + requestObj);

        method = responseCls.getMethod("body");
        Object bodyObj = method.invoke(object);
        Class<?> clsBody = Class.forName("okhttp3.ResponseBody");
        method = clsBody.getMethod("source");
        Object bufferedSource = method.invoke(bodyObj);

        Class<?> clsBufferedSource = Class.forName("okio.BufferedSource");
        method = clsBufferedSource.getMethod("request", long.class);
        method.invoke(bufferedSource, Long.MAX_VALUE);

        method = clsBufferedSource.getMethod("buffer");
        Object buffer = method.invoke(bufferedSource);

        Class<?> clsBuffer = Class.forName("okio.Buffer");
        method = clsBuffer.getMethod("clone");
        Object cloneBuffer = method.invoke(buffer);

        method = clsBuffer.getMethod("readUtf8");
        String content = (String) method.invoke(cloneBuffer);


//        BufferedSource bufferedSource = (BufferedSource) method.invoke(bodyObj);
//        bufferedSource.request(Long.MAX_VALUE); // Buffer the entire body.
//
//        Buffer srcBuffer = bufferedSource.buffer();

        System.out.println("buffer string:" + content);
//        System.out.println("toString string:" + response.networkResponse().body().string());

//        ResponseBody responseBody = response.body();
//        BufferedSource source = responseBody.source();
//        source.request(Long.MAX_VALUE); // Buffer the entire body.
//        Buffer buffer = source.buffer();
//        String responseBodyString = buffer.clone().readString(Charset.forName("UTF-8"));
//        System.out.println("responseBodyString:" + responseBodyString);
        System.out.println("response.body().string:" + response.body().string());

    }

}
