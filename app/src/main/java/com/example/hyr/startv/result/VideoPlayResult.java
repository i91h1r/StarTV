package com.example.hyr.startv.result;

import java.util.List;

/**
 * Description:
 * 作者：hyr on 2016/11/9 15:13
 * 邮箱：2045446584@qq.com
 */
public class VideoPlayResult extends BaseResult {

    /**
     * code : 0
     * msg : SUCCESS
     * now : 1478675516313
     * data : {"specialType":"common","status":200,"hdUrl":"http://hc.yinyuetai.com/uploads/videos/common/C8DD01582DF16AC294A5D3B0BA774FFC.flv?sc=af2110afa3ec0c00&br=789&rd=Android","videoSize":99403285,"url":"http://dd.yinyuetai.com/uploads/videos/common/CBE801582F3E9E183CEB8426AC605ECC.mp4?sc=9c720c451b1f113f&br=578&rd=Android","videoTypes":[1,2,3,4],"uhdUrl":"http://hd.yinyuetai.com/uploads/videos/common/822F01582F3E9E3C200CD53780E9E0D6.flv?sc=97b831b9d6fdc322&br=1099&rd=Android","shdUrl":"http://he.yinyuetai.com/uploads/videos/common/1ABD01582F3E9E32F19122FC0FF1052B.flv?sc=5ca425ae0848ae20&br=3131&rd=Android","shdVideoSize":538382819,"title":"[专访Marvel《奇异博士》]本尼BC领衔全奥斯卡团队 开启漫威宇宙第三阶段 魔法世界的天才ICON","hdVideoSize":135722827,"duration":1375,"videoId":2716931,"ad":false,"uhdVideoSize":189079823}
     * cost : 1
     */

    private String code;
    private String msg;
    private long now;
    /**
     * specialType : common
     * status : 200
     * hdUrl : http://hc.yinyuetai.com/uploads/videos/common/C8DD01582DF16AC294A5D3B0BA774FFC.flv?sc=af2110afa3ec0c00&br=789&rd=Android
     * videoSize : 99403285
     * url : http://dd.yinyuetai.com/uploads/videos/common/CBE801582F3E9E183CEB8426AC605ECC.mp4?sc=9c720c451b1f113f&br=578&rd=Android
     * videoTypes : [1,2,3,4]
     * uhdUrl : http://hd.yinyuetai.com/uploads/videos/common/822F01582F3E9E3C200CD53780E9E0D6.flv?sc=97b831b9d6fdc322&br=1099&rd=Android
     * shdUrl : http://he.yinyuetai.com/uploads/videos/common/1ABD01582F3E9E32F19122FC0FF1052B.flv?sc=5ca425ae0848ae20&br=3131&rd=Android
     * shdVideoSize : 538382819
     * title : [专访Marvel《奇异博士》]本尼BC领衔全奥斯卡团队 开启漫威宇宙第三阶段 魔法世界的天才ICON
     * hdVideoSize : 135722827
     * duration : 1375
     * videoId : 2716931
     * ad : false
     * uhdVideoSize : 189079823
     */

    private DataBean data;
    private int cost;


    public String getCode() { return code;}


    public void setCode(String code) { this.code = code;}


    public String getMsg() { return msg;}


    public void setMsg(String msg) { this.msg = msg;}


    public long getNow() { return now;}


    public void setNow(long now) { this.now = now;}


    public DataBean getData() { return data;}


    public void setData(DataBean data) { this.data = data;}


    public int getCost() { return cost;}


    public void setCost(int cost) { this.cost = cost;}


    public static class DataBean {
        private String specialType;
        private int status;
        private String hdUrl;
        private int videoSize;
        private String url;
        private String uhdUrl;
        private String shdUrl;
        private int shdVideoSize;
        private String title;
        private int hdVideoSize;
        private int duration;
        private int videoId;
        private boolean ad;
        private int uhdVideoSize;
        private List<Integer> videoTypes;


        public String getSpecialType() { return specialType;}


        public void setSpecialType(String specialType) { this.specialType = specialType;}


        public int getStatus() { return status;}


        public void setStatus(int status) { this.status = status;}


        public String getHdUrl() { return hdUrl;}


        public void setHdUrl(String hdUrl) { this.hdUrl = hdUrl;}


        public int getVideoSize() { return videoSize;}


        public void setVideoSize(int videoSize) { this.videoSize = videoSize;}


        public String getUrl() { return url;}


        public void setUrl(String url) { this.url = url;}


        public String getUhdUrl() { return uhdUrl;}


        public void setUhdUrl(String uhdUrl) { this.uhdUrl = uhdUrl;}


        public String getShdUrl() { return shdUrl;}


        public void setShdUrl(String shdUrl) { this.shdUrl = shdUrl;}


        public int getShdVideoSize() { return shdVideoSize;}


        public void setShdVideoSize(int shdVideoSize) { this.shdVideoSize = shdVideoSize;}


        public String getTitle() { return title;}


        public void setTitle(String title) { this.title = title;}


        public int getHdVideoSize() { return hdVideoSize;}


        public void setHdVideoSize(int hdVideoSize) { this.hdVideoSize = hdVideoSize;}


        public int getDuration() { return duration;}


        public void setDuration(int duration) { this.duration = duration;}


        public int getVideoId() { return videoId;}


        public void setVideoId(int videoId) { this.videoId = videoId;}


        public boolean isAd() { return ad;}


        public void setAd(boolean ad) { this.ad = ad;}


        public int getUhdVideoSize() { return uhdVideoSize;}


        public void setUhdVideoSize(int uhdVideoSize) { this.uhdVideoSize = uhdVideoSize;}


        public List<Integer> getVideoTypes() { return videoTypes;}


        public void setVideoTypes(List<Integer> videoTypes) { this.videoTypes = videoTypes;}
    }
}
