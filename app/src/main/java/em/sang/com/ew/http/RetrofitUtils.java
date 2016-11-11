package em.sang.com.ew.http;

import em.sang.com.ew.mode.bean.ListBean;
import em.sang.com.ew.util.JLog;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Description：
 *
 * @Author：桑小年
 * @Data：2016/11/9 15:11
 */
public class RetrofitUtils<T>  {

    private static RetrofitUtils utils;


    private RetrofitUtils(){}

    public static RetrofitUtils getInstance(){
        if (utils==null){
            synchronized (RetrofitUtils.class){
                if (utils==null){
                    utils = new RetrofitUtils();
                }
            }
        }
        return utils;
    }

    public HttpServer getClient(String basUrl ){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(basUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(getOKClient())
                .build();

        return  retrofit.create(HttpServer.class);
    }

    public OkHttpClient getOKClient(){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder okClient = new OkHttpClient.Builder();
        okClient.addInterceptor(logging);
        return okClient.build();
    }
}
