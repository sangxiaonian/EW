package em.sang.com.ew.basic;


import java.util.List;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * 操作层的基本接口
 */
public interface IBasePresenter<T> {
    /**
     * 获取数据
     */
    List<T> getData();
}
