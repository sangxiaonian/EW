package em.sang.com.ew.mode.bean;

import android.os.Parcel;
import android.os.Parcelable;

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

        public static class CbListBean implements Parcelable{


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

            protected CbListBean(Parcel in) {
                cbId = in.readString();
                cl = in.readString();
                introduce = in.readString();
                mainType = in.readString();
                name = in.readString();
                type = in.readString();
                zf = in.readString();
                knack = in.readString();
                imgList = in.createTypedArrayList(ImgListBean.CREATOR);
            }

            public static final Creator<CbListBean> CREATOR = new Creator<CbListBean>() {
                @Override
                public CbListBean createFromParcel(Parcel in) {
                    return new CbListBean(in);
                }

                @Override
                public CbListBean[] newArray(int size) {
                    return new CbListBean[size];
                }
            };

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

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(cbId);
                dest.writeString(cl);
                dest.writeString(introduce);
                dest.writeString(mainType);
                dest.writeString(name);
                dest.writeString(type);
                dest.writeString(zf);
                dest.writeString(knack);
                dest.writeTypedList(imgList);
            }

            public static class ImgListBean implements Parcelable {
                /**
                 * 图片链接地址
                 */
                public String imgUrl;

                protected ImgListBean(Parcel in) {
                    imgUrl = in.readString();
                }

                public static final Creator<ImgListBean> CREATOR = new Creator<ImgListBean>() {
                    @Override
                    public ImgListBean createFromParcel(Parcel in) {
                        return new ImgListBean(in);
                    }

                    @Override
                    public ImgListBean[] newArray(int size) {
                        return new ImgListBean[size];
                    }
                };

                @Override
                public String toString() {
                    return "ImgListBean{" +
                            "imgUrl='" + imgUrl + '\'' +
                            '}';
                }

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeString(imgUrl);
                }
            }
        }
    }
}
