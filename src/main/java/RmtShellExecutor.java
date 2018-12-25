import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RmtShellExecutor {

    public static void main(String[] args) throws IOException {
        Process process = Runtime.getRuntime().exec("curl 'https://sec-m.ctrip.com/restapi/soa2/13212/flightListSearch?_fxpcqlniredt=09031139211879980512' -H 'origin: http://m.ctrip.com' -H 'accept-encoding: gzip, deflate, br' -H 'accept-language: en-US,en;q=0.8,ja;q=0.6,th;q=0.4,zh-TW;q=0.2,zh;q=0.2,zh-CN;q=0.2' -H 'cookieorigin: http://m.ctrip.com' -H 'cookie: _abtest_userid=668aa59c-452e-4761-a610-5cd50c54a020; FlightIntl=Search=%5B%22Beijing%7C%E5%8C%97%E4%BA%AC(BJS)%7C1%7CBJS%7C480%22%2C%22Kuala%20Lumpur%7C%E5%90%89%E9%9A%86%E5%9D%A1(KUL)%7C315%7CKUL%7C480%22%2C%222017-05-17%22%2C%222017-05-19%22%5D; AHeadUserInfo=VipGrade=0&UserName=&NoReadMessageCount=0&U=D6F4EF34AEB438CB34D9C6845471375C; login_uid=21794AF13353453402BF234283540234; login_type=6; login_cardType=6; Customer=HAL=ctrip_gb; OUTFOX_SEARCH_USER_ID_NCOO=807253280.958351; FD_SearchHistorty={\"type\":\"D\",\"data\":\"D%24%u5174%u4E49%28ACX%29%24ACX%242017-10-07%24%u5B81%u6CE2%28NGB%29%24NGB%242017-10-07\"}; traceExt=campaign=CHNbaidu81&adid=index; appFloatCnt=3; Session=smartlinkcode=U130026&smartlinklanguage=zh&SmartLinkKeyWord=&SmartLinkQuary=&SmartLinkHost=; __zpspc=9.9.1500950579.1500950579.1%232%7Cwww.baidu.com%7C%7C%7C%7C%23; __utma=1.199292907.1489800796.1500950583.1500950583.1; __utmc=1; __utmz=1.1500950583.1.1.utmcsr=app.ctrip.com|utmccn=(referral)|utmcmd=referral|utmcct=/; _bfi=p1%3D75500128%26p2%3D0%26v1%3D31%26v2%3D0; _fpacid=09031139211879980512; GUID=09031139211879980512; cticket=23E91DF5A865878452817E355AA349167D3FD591C7EF9667F75471A264295D03; DUID=u=B1EC155EC7F837C88CDD4C7215A47708&v=0; IsNonUser=T; fplus_allianceid=22048; fplus_sid=455673; Union=OUID=53b807ed321543e9d94a65e59cd1d114%404.0.7%401&AllianceID=22048&SID=455673&SourceID=1&Expires=1502273779877; MKT_Pagesource=H5; _ga=GA1.2.199292907.1489800796; _gat=1; _RF1=123.113.106.207; _RSG=pLaTVFsQZZCusKGNdkVVG9; _RGUID=c8e36b63-df76-4573-941f-7ee9d7414db8' -H 'pragma: no-cache' -H 'user-agent: Mozilla/5.0 (iPhone; CPU iPhone OS 9_1 like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) Version/9.0 Mobile/13B143 Safari/601.1' -H 'content-type: application/json' -H 'accept: application/json' -H 'cache-control: no-cache' -H 'authority: sec-m.ctrip.com' -H 'referer: http://m.ctrip.com/html5/flight/swift/international/AAQ/ARH/2017-08-07' --data-binary '{\"preprdid\":\"\",\"trptpe\":1,\"flag\":8,\"searchitem\":[{\"dccode\":\"AAQ\",\"accode\":\"LED\",\"dtime\":\"2017-08-08\"}],\"psgList\":[{\"type\":1,\"count\":1}],\"token\":\"2\",\"seat\":0,\"segno\":1,\"head\":{\"cid\":\"09031139211879980512\",\"ctok\":\"\",\"cver\":\"1.0\",\"lang\":\"01\",\"sid\":\"8888\",\"syscode\":\"09\",\"auth\":null,\"extension\":[{\"name\":\"aid\",\"value\":\"22048\"},{\"name\":\"sid\",\"value\":\"455673\"},{\"name\":\"protocal\",\"value\":\"http\"}]},\"contentType\":\"json\"}' --compressed");
        try {
            process.waitFor();
            BufferedReader br = null;
            String line = null;

            br = new BufferedReader(new InputStreamReader(process.getInputStream()));
            while ((line = br.readLine()) != null) {
                System.out.println("\t" + line);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}