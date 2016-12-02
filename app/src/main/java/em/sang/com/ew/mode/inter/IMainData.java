package em.sang.com.ew.mode.inter;

import em.sang.com.ew.basic.IBasicMode;


public interface IMainData extends IBasicMode {



    String getSearchMsg(String msg);

    void   startSearch(String msg);
}
