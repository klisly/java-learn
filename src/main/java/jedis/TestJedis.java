package jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

/**
 * Created by wizardholy on 2017/8/22.
 */
public class TestJedis {
    static int recvCount = 0;
    public static void main(String[] args) {
        Jedis jedis = new Jedis("10.0.0.15");
        int count = 0;
        new Thread(new Runnable() {
            @Override
            public void run() {
                Jedis jedis = new Jedis("10.0.0.15");

                jedis.subscribe(new JedisPubSub() {
                    @Override
                    public void onMessage(String channel, String message) {
                        super.onMessage(channel, message);
                        if (recvCount == 1 || recvCount % 100 == 0) {
                            recvCount++;
                            System.out.println("recv count:" + recvCount + " " + System.currentTimeMillis() / 1000);
                        }
                    }
                }, "M_ENCOE_RESPOSNE");
            }
        }).start();
        while (true) {
//            jedis.set("out", "" + count++);
            jedis.publish("M_ENCOE_REQUEST ", " " + count++);
            System.out.println(count);
        }


    }
}
