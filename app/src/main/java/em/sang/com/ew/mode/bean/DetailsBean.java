package em.sang.com.ew.mode.bean;

import com.google.gson.annotations.SerializedName;

import em.sang.com.ew.basic.BasicBean;

/**
 * Description：食谱详情
 *
 * @Author：桑小年
 * @Data：2016/11/10 9:57
 */
public class DetailsBean extends BasicBean {




    public static class ShowapiResBodyBean {
        /**
         * cbId : 441342
         * cl : 3个 鸡蛋 1.5 杯 刨细的 pecorino romano cheese（比parmesan 略味重点） 1/2 LB 培根 切片 1 LB 意面（我用了angel hair pasta，也是图快） 胡椒，盐，罗勒
         * flag : true
         * mainType : 猪肉
         * name : 咸肉奶酪意面
         * ret_code : 0
         * type : 咸肉
         * zf : 1、碗里将蛋打散，和奶酪粉拌匀。 2、一只炒锅，用点橄榄油炒培根，炒脆，撇出一部分油。 3、同时用一只深锅用盐开水煮面，煮到9成熟，捞出来放进炒好的培根的锅里，拌炒匀，关火。 4、将碗里的蛋液倒入面里，再勺1杯煮过面的开水倒入面锅里，拌匀即可。
         */

        public String cbId;
        public String cl;
        public boolean flag;
        public String mainType;
        public String name;
        public int ret_code;
        public String type;
        public String zf;

      
    }
}
