package em.sang.com.ew.ui.inter;

import java.util.List;

import em.sang.com.ew.basic.BasicBean;
import em.sang.com.ew.mode.bean.SearchBean;

/**
 * Description：
 *
 * @Author：桑小年
 * @Data：2016/11/8 9:31
 */
public interface IMainView {
    /**
     * 隐藏加载进度
     */
    void dismiss();

    /**
     * 显示加载进度
     */
    void showLoding();

    /**
     * 设置adapter
     * @param datas 数据
     */
    void setAdapter(List< SearchBean.ShowapiResBodyBean.CbListBean> datas);
}
