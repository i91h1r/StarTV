package com.example.hyr.startv.result;

import java.util.List;

/**
 * Description:
 * 作者：hyr on 2016/11/4 15:29
 * 邮箱：2045446584@qq.com
 */
public class BannerResult {

    /**
     * videoId : 4286849
     * type : playlist
     * title : 妈妈,我是Gay
     * desc : 有些人似乎注定就要做一些突出常人思考的事情。他们在混乱的时代里顺从自己的心意,寻找真爱,在真爱中叹息无奈,在不尽的无奈中独自醒来……他们想要过一种只属于自己的生活。

     而很多时候,这些人仍在黑暗中摸索,他们渴望找到自己灵魂的出口,渴望不被别人视为异类,渴望随时随地都能向爱人示爱。也许,这一天真正到来了,他们就可以呼吸自由的空气;如果无法到来,他们就会被压抑、窒息、死亡。


     异性只为繁衍后代
     同性才是人间真爱

     boy,勇敢show出你的取向!!!


     友情悦单:爸爸,我是Lesbian
     http://v.yinyuetai.com/playlist/4286946
     * posterPic : http://img2.c.yinyuetai.com/others/mobile_front_page/161104/0/-M-a9599191445a3e14d5c2a554260765ad_0x0.jpg
     * artists : [{"artistId":2737879,"artistName":"Lucifer_Morningstar_"}]
     * totalView : 3790
     * clickUrl : http://mapiv2.yinyuetai.com/statistics/click.json?id=4813
     * isVchart : false
     * dataTypeUrl : http://img0.c.yinyuetai.com/others/ad/160627/0/-M-b76f914be269db8641e9bda5f75b1de3_0x0.png
     * ad : false
     */

    private int videoId;
    private String type;
    private String title;
    private String desc;
    private String posterPic;
    private int totalView;
    private String clickUrl;
    private boolean isVchart;
    private String dataTypeUrl;
    private boolean ad;
    /**
     * artistId : 2737879
     * artistName : Lucifer_Morningstar_
     */

    private List<ArtistsBean> artists;


    public int getVideoId() { return videoId;}


    public void setVideoId(int videoId) { this.videoId = videoId;}


    public String getType() { return type;}


    public void setType(String type) { this.type = type;}


    public String getTitle() { return title;}


    public void setTitle(String title) { this.title = title;}


    public String getDesc() { return desc;}


    public void setDesc(String desc) { this.desc = desc;}


    public String getPosterPic() { return posterPic;}


    public void setPosterPic(String posterPic) { this.posterPic = posterPic;}


    public int getTotalView() { return totalView;}


    public void setTotalView(int totalView) { this.totalView = totalView;}


    public String getClickUrl() { return clickUrl;}


    public void setClickUrl(String clickUrl) { this.clickUrl = clickUrl;}


    public boolean isIsVchart() { return isVchart;}


    public void setIsVchart(boolean isVchart) { this.isVchart = isVchart;}


    public String getDataTypeUrl() { return dataTypeUrl;}


    public void setDataTypeUrl(String dataTypeUrl) { this.dataTypeUrl = dataTypeUrl;}


    public boolean isAd() { return ad;}


    public void setAd(boolean ad) { this.ad = ad;}


    public List<ArtistsBean> getArtists() { return artists;}


    public void setArtists(List<ArtistsBean> artists) { this.artists = artists;}


    public static class ArtistsBean {
        private int artistId;
        private String artistName;


        public int getArtistId() { return artistId;}


        public void setArtistId(int artistId) { this.artistId = artistId;}


        public String getArtistName() { return artistName;}


        public void setArtistName(String artistName) { this.artistName = artistName;}
    }
}
