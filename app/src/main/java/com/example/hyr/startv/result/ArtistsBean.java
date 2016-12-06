package com.example.hyr.startv.result;

/**
 * Description:
 * 作者：hyr on 2016/11/15 17:31
 * 邮箱：2045446584@qq.com
 */
public class ArtistsBean {

    private int artistId;
    private String artistName;
    private String artistAvatar;
    private String area;


    public String getArea() {
        return area;
    }


    public void setArea(String area) {
        this.area = area;
    }


    public String getArtistAvatar() {
        return artistAvatar;
    }


    public void setArtistAvatar(String artistAvatar) {
        this.artistAvatar = artistAvatar;
    }


    public String getArtistName() {
        return artistName;
    }


    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }


    public int getArtistId() {
        return artistId;
    }


    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }
}
