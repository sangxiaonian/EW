package em.sang.com.ew.basic;

/**
 * Description：基础的Bean类,用来储存系统数据
 *
 * @Author：桑小年
 * @Data：2016/11/10 9:37
 */
public class BasicBean<T> {
    /**
     * 易源返回标志,0为成功，其他为失败。
     0成功
     -1，系统调用错误
     -2，可调用次数或金额为0
     -3，读取超时
     -4，服务端返回数据解析错误
     -5，后端服务器DNS解析错误
     -6，服务不存在或未上线
     -1000，系统维护
     -1002，showapi_appid字段必传
     -1003，showapi_sign字段必传
     -1004，签名sign验证有误
     -1005，showapi_timestamp无效
     -1006，app无权限调用接口
     -1007，没有订购套餐
     -1008，服务商关闭对您的调用权限
     -1010，找不到您的应用
     -1011，子授权app_child_id无效
     -1012，子授权已过期或失效
     -1013，子授权ip受限
     */
    public int showapi_res_code;
    /**
     * 错误信息的展示
     */
    public String showapi_res_error;

    public T showapi_res_body;

    @Override
    public String toString() {
        return "BasicBean{" +
                "showapi_res_code=" + showapi_res_code +
                ", showapi_res_error='" + showapi_res_error + '\'' +
                ", showapi_res_body=" + showapi_res_body.toString() +
                '}';
    }
}
