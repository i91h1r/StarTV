package com.example.hyr.startv.result;

import java.util.List;

/**
 * Description:
 * 作者：hyr on 2016/12/2 10:38
 * 邮箱：2045446584@qq.com
 */
public class TabResult extends BaseResult{

    /**
     * code : 0
     * msg : SUCCESS
     * now : 1480643403255
     * data : [{"position":14,"parentKey":"area","parentValue":"地区","show":false,"children":[{"key":"ALL","selected":true,"value":"全部"},{"selected":false,"key":"ML","value":"内地"},{"value":"港台","key":"HT","selected":false},{"selected":false,"value":"欧美","key":"US"},{"selected":false,"key":"KR","value":"韩国"},{"selected":false,"value":"日本","key":"JP"}]}]
     * cost : 1
     */

    private String code;
    private String msg;
    private long now;
    private int cost;
    /**
     * position : 14
     * parentKey : area
     * parentValue : 地区
     * show : false
     * children : [{"key":"ALL","selected":true,"value":"全部"},{"selected":false,"key":"ML","value":"内地"},{"value":"港台","key":"HT","selected":false},{"selected":false,"value":"欧美","key":"US"},{"selected":false,"key":"KR","value":"韩国"},{"selected":false,"value":"日本","key":"JP"}]
     */

    private List<DataBean> data;


    public String getCode() { return code;}


    public void setCode(String code) { this.code = code;}


    public String getMsg() { return msg;}


    public void setMsg(String msg) { this.msg = msg;}


    public long getNow() { return now;}


    public void setNow(long now) { this.now = now;}


    public int getCost() { return cost;}


    public void setCost(int cost) { this.cost = cost;}


    public List<DataBean> getData() { return data;}


    public void setData(List<DataBean> data) { this.data = data;}


    public static class DataBean {
        private int position;
        private String parentKey;
        private String parentValue;
        private boolean show;
        /**
         * key : ALL
         * selected : true
         * value : 全部
         */

        private List<ChildrenBean> children;


        public int getPosition() { return position;}


        public void setPosition(int position) { this.position = position;}


        public String getParentKey() { return parentKey;}


        public void setParentKey(String parentKey) { this.parentKey = parentKey;}


        public String getParentValue() { return parentValue;}


        public void setParentValue(String parentValue) { this.parentValue = parentValue;}


        public boolean isShow() { return show;}


        public void setShow(boolean show) { this.show = show;}


        public List<ChildrenBean> getChildren() { return children;}


        public void setChildren(List<ChildrenBean> children) { this.children = children;}


        public static class ChildrenBean {
            private String key;
            private boolean selected;
            private String value;


            public String getKey() { return key;}


            public void setKey(String key) { this.key = key;}


            public boolean isSelected() { return selected;}


            public void setSelected(boolean selected) { this.selected = selected;}


            public String getValue() { return value;}


            public void setValue(String value) { this.value = value;}
        }
    }
}
