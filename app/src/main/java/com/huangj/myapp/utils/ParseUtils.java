package com.huangj.myapp.utils;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

/**
 * Created by Administrator on 2017/5/4.
 */

public class ParseUtils {

    /**
     * gson解析的特殊情况
     */
    public class MapBean {

        /**
         * result : {"中国画":"中国画","ckey":{"d":"G"}}
         */


        private ResultBean result;

        public ResultBean getResult() { return result;}

        public void setResult(ResultBean result) { this.result = result;}

//                MapBean mapBean = new Gson().fromJson("", MapBean.class);
//                Iterator<String> iter = mapBean.getResult().getCkey().keySet().iterator();
//                while(iter.hasNext()){
//                    String key=iter.next();
//                    String value = mapBean.getResult().getCkey().get(key);
//                }
        public class ResultBean {
            //键是中文或数字
            @SerializedName("中国画")
            private String chinaHua;

            public String getChinaHua() {

                return chinaHua;
            }

            public void setChinaHua(String chinaHua) {
                this.chinaHua = chinaHua;
            }

            //键值对都是动态的时候

            private Map<String, String> ckey;

            public Map<String, String> getCkey() { return ckey;}

            public void setCkey(Map<String, String> ckey) { this.ckey = ckey;}

        }
    }


}
