package em.sang.com.ew.mode.bean;

import java.util.List;

import em.sang.com.ew.basic.BasicBean;

/**
 * Description：食谱分类
 *
 * @Author：桑小年
 * @Data：2016/11/9 17:08
 */
public class ListBean extends BasicBean<ListBean.ShowapiResBodyBean>{


    public static class ShowapiResBodyBean {
        /**
         * 分类数量
         */
        public int count;
        /**
         *
         */
        public boolean flag;

        public String msg;
        public String remark;
        public String ret_code;
        /**
         * 分类列表
         */
        public List<CbTypeListBean> cbTypeList;

        @Override
        public String toString() {
            return "ShowapiResBodyBean{" +
                    "count=" + count +
                    ", flag=" + flag +
                    ", msg='" + msg + '\'' +
                    ", remark='" + remark + '\'' +
                    ", ret_code='" + ret_code + '\'' +
                    ", cbTypeList=" + cbTypeList +
                    '}';
        }

        public static class CbTypeListBean {

            /**
             * 是否是主分类
             */
            public int isMain;
            /**
             * 上级分类
             */
            public String mainType;
            /**
             * 分类名称
             */
            public String typeName;

            @Override
            public String toString() {
                return "CbTypeListBean{" +
                        "isMain=" + isMain +
                        ", mainType='" + mainType + '\'' +
                        ", typeName='" + typeName + '\'' +
                        '}';
            }
        }
    }
}
