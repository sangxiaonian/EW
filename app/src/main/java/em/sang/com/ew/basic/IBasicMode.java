package em.sang.com.ew.basic;

import java.util.List;

import em.sang.com.ew.util.ToastUtil;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Description：Mode的基本接口
 *
 * @Author：桑小年
 * @Data：2016/11/8 9:17
 */
public interface IBasicMode<T> {
    /**
     * 获取数据
     */
     List<T> getData();


}
