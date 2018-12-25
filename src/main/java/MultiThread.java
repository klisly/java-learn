import okhttp3.*;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class MultiThread {
    public static AtomicInteger count = new AtomicInteger(0);
    static ExecutorService executorService = Executors.newFixedThreadPool(30);

    public static void main(String[] args) {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(300, TimeUnit.SECONDS)
                .readTimeout(300, TimeUnit.SECONDS)
                .writeTimeout(300, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();
        for (int i = 0; i < 10000; i++) {

              executorService.submit(new Runnable() {
                  @Override
                  public void run() {
                      MediaType mediaType = MediaType.parse("application/json");
                      RequestBody body = RequestBody.create(mediaType, "{\n\t\"taskType\":\"com.mfw.roadbook\",\n\t\"data\":\"{\\\"data\\\":\\\"{\\\\\\\"screen_scale\\\\\\\":\\\\\\\"3.0\\\\\\\",\\\\\\\"screen_width\\\\\\\":\\\\\\\"1080\\\\\\\",\\\\\\\"jsondata\\\\\\\":\\\\\\\"{\\\\\\\\\\\\\\\"mdd_id\\\\\\\\\\\\\\\":\\\\\\\\\\\\\\\"17281\\\\\\\\\\\\\\\"}\\\\\\\",\\\\\\\"time_offset\\\\\\\":\\\\\\\"480\\\\\\\",\\\\\\\"device_id\\\\\\\":\\\\\\\"f8:a9:d0:62:1e:9e\\\\\\\",\\\\\\\"o_lat\\\\\\\":\\\\\\\"40.000367\\\\\\\",\\\\\\\"device_type\\\\\\\":\\\\\\\"android\\\\\\\",\\\\\\\"oauth_consumer_key\\\\\\\":\\\\\\\"5\\\\\\\",\\\\\\\"oauth_signature_method\\\\\\\":\\\\\\\"HMAC-SHA1\\\\\\\",\\\\\\\"oauth_version\\\\\\\":\\\\\\\"1.0\\\\\\\",\\\\\\\"oauth_timestamp\\\\\\\":\\\\\\\"1503455509\\\\\\\",\\\\\\\"screen_height\\\\\\\":\\\\\\\"1776\\\\\\\",\\\\\\\"oauth_nonce\\\\\\\":\\\\\\\"be20209e-01d4-450a-9eb4-a5ec83462113\\\\\\\",\\\\\\\"open_udid\\\\\\\":\\\\\\\"f8:a9:d0:62:1e:9e\\\\\\\",\\\\\\\"app_version_code\\\\\\\":\\\\\\\"390\\\\\\\",\\\\\\\"x_auth_mode\\\\\\\":\\\\\\\"client_auth\\\\\\\",\\\\\\\"oauth_token\\\\\\\":\\\\\\\"0_0969044fd4edf59957f4a39bce9200c6\\\\\\\",\\\\\\\"mfwsdk_ver\\\\\\\":\\\\\\\"20140507\\\\\\\",\\\\\\\"app_ver\\\\\\\":\\\\\\\"8.0.0\\\\\\\",\\\\\\\"sys_ver\\\\\\\":\\\\\\\"4.4.4\\\\\\\",\\\\\\\"hardware_model\\\\\\\":\\\\\\\"Nexus 5\\\\\\\",\\\\\\\"o_lng\\\\\\\":\\\\\\\"116.476278\\\\\\\",\\\\\\\"channel_id\\\\\\\":\\\\\\\"MFWUPDATE\\\\\\\",\\\\\\\"app_code\\\\\\\":\\\\\\\"com.mfw.roadbook\\\\\\\"}\\\",\\\"url\\\":\\\"https://mapi.mafengwo.cn/mdd/item/get_mdd_item/v2\\\"}\"\n}");
                      Request request = new Request.Builder()
                              .url("http://localhost:8080/signature?name=kk")
                              .post(body)
                              .addHeader("content-type", "application/json")
                              .addHeader("cache-control", "no-cache")
                              .addHeader("postman-token", "82cec2dc-b2f5-b699-3b68-c4b897fb07b8")
                              .build();

                      try {
                          Response response = client.newCall(request).execute();
//                        System.out.println(response.body().string());
                          int size = count.incrementAndGet();
                          if (size == 1 || size % 100 == 0) {
                              System.out.println("size:" + size + " " + (System.currentTimeMillis() / 1000));
                          }
                          response.close();
                      } catch (IOException e) {
                          e.printStackTrace();
                      }
                  }
              });

            if (i % 200 == 0) {
                System.out.println(i);
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
