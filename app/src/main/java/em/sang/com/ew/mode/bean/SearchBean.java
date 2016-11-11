package em.sang.com.ew.mode.bean;

import java.util.List;

import em.sang.com.ew.basic.BasicBean;

/**
 * Description：菜谱搜索
 *
 * @Author：桑小年
 * @Data：2016/11/9 16:48
 */
public class SearchBean extends BasicBean<SearchBean.ShowapiResBodyBean>{


    public static class ShowapiResBodyBean {

        /**
         * 是否操作成功
         */
        public boolean flag;

        public String msg;
        public String remark;
        /**
         * 操作编码 0成功 其他值为失败
         */
        public String ret_code;
        /**
         * 食谱列表
         */
        public List<CbListBean> cbList;

        @Override
        public String toString() {
            return "ShowapiResBodyBean{" +
                    "flag=" + flag +
                    ", msg='" + msg + '\'' +
                    ", remark='" + remark + '\'' +
                    ", ret_code='" + ret_code + '\'' +
                    ", cbList=" + cbList +
                    '}';
        }

        public static class CbListBean {


            /**
             * 食谱ID
             */
            public String cbId;
            /**
             * 材料
             */
            public String cl;
            /**
             * 简介
             */
            public String introduce;
            /**
             * 主分类名称
             */
            public String mainType;
            /**
             * 菜谱名称
             */
            public String name;
            /**
             * 分类名称
             */
            public String type;
            /**
             * 做法
             */
            public String zf;
            /**
             * 诀窍
             */
            public String knack;
            /**
             * 图片列表
             */
            public List<ImgListBean> imgList;

            @Override
            public String toString() {
                return "CbListBean{" +
                        "cbId='" + cbId + '\'' +
                        ", cl='" + cl + '\'' +
                        ", introduce='" + introduce + '\'' +
                        ", mainType='" + mainType + '\'' +
                        ", name='" + name + '\'' +
                        ", type='" + type + '\'' +
                        ", zf='" + zf + '\'' +
                        ", knack='" + knack + '\'' +
                        ", imgList=" + imgList +
                        '}';
            }

            public static class ImgListBean {
                /**
                 * 图片链接地址
                 */
                public String imgUrl;

                @Override
                public String toString() {
                    return "ImgListBean{" +
                            "imgUrl='" + imgUrl + '\'' +
                            '}';
                }
            }
        }
    }
}
