package em.sang.com.ew.presenter;

import android.app.Activity;

import em.sang.com.ew.basic.IBasePresenter;


public interface IMainPresenter<T> extends IBasePresenter  {



    String getTranData(Activity activity,String name);
}
