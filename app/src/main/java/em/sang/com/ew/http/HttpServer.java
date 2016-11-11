package em.sang.com.ew.http;


import java.util.Map;

import em.sang.com.ew.mode.bean.DetailsBean;
import em.sang.com.ew.mode.bean.ListBean;
import em.sang.com.ew.mode.bean.SearchBean;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Description：
 *
 * @Author：桑小年
 * @Data：2016/11/9 11:59
 */
public interface HttpServer {


    /**
     * 获取分类详情
     *
     * @param params
     * @return
     */
    @POST("95-105")
    Observable<ListBean> getList(@QueryMap Map<String, String> params);

    /**
     * 获取菜单详情
     *
     * @param params 参数
     * @return
     */
    @POST("95-35")
    Observable<DetailsBean> getDetails(@QueryMap Map<String, String> params);

    /**
     * 获取查询菜单结果
     *
     * @param params
     * @return
     */
    @POST("95-106")
    Observable<SearchBean> getSearch(@QueryMap Map<String, String> params);

}
