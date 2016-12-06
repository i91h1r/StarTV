package com.example.hyr.startv.result;

import android.text.TextUtils;
import java.io.Serializable;
import java.util.List;

/**
 * Description:
 * 作者：hyr on 2016/11/24 15:41
 * 邮箱：2045446584@qq.com
 */
public class VideoDownloadResult extends BaseResult implements Serializable {
    private static final long serialVersionUID = 1L;



    private String code;
    private String msg;
    private long now;
    private int cost;

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

        public static final int DOWNLOAD_NONE = 0;//未下载
        public static final int DOWNLOAD_COMPLETE = 1;//下载完成
        public static final int DOWNLOAD_ING = 2;//下载中
        public static final int DOWNLOAD_DISABLE = 3;//无可用网络
        public static final int DOWNLOAD_WITH_WIFI = 4;//wifi下下载歌曲

        private String id;

        private  int dowloadType ;
        private int download;
        private int duration;
        private String title;
        private String specialType;
        private int status;
        private int videoId;
        private String posterPic;
        private int videoSize;
        private String url;

        private String path;
        private List<ArtistsBean> artists;

        private boolean downloadstatus;


        private  boolean isDownloading ;

        public int getDowloadType() {
            return dowloadType;
        }


        public boolean isDownloading() {
            return isDownloading;
        }


        public void setDownloading(boolean downloading) {
            isDownloading = downloading;
        }


        public void setDowloadType(int dowloadType) {
            this.dowloadType = dowloadType;
        }


        public DataBean(String id, int download, int duration, String title, int videoId, String posterPic, int videoSize, String url, String path, List<ArtistsBean> artists, boolean downloadstatus,int dowloadType) {
            this.download = download;
            this.duration = duration;
            this.title = title;
            this.videoId = videoId;
            this.posterPic = posterPic;
            this.videoSize = videoSize;
            this.url = url;
            this.path = path;
            this.artists = artists;
            this.downloadstatus = downloadstatus;
            this.id = id;
            this.dowloadType = dowloadType ;
        }


        public String getId() {
            return id;
        }


        public void setId(String id) {
            this.id = id;
        }


        public boolean isDownloadstatus() {
            return downloadstatus;
        }


        public void setDownloadstatus(boolean downloadstatus) {
            this.downloadstatus = downloadstatus;
        }


        public int getDownload() {
            return download;
        }


        public void setDownload(int download) {
            this.download = download;
        }


        public String getPath() {
            return path;
        }


        public void setPath(String path) {
            this.path = path;
        }


        public String getUri() {
            if (download == DOWNLOAD_COMPLETE  && !TextUtils.isEmpty(path)) {
                return path;
            } else {
                return url;
            }

        }


        public int getDuration() { return duration;}


        public void setDuration(int duration) { this.duration = duration;}


        public String getTitle() { return title;}


        public void setTitle(String title) { this.title = title;}


        public String getSpecialType() { return specialType;}


        public void setSpecialType(String specialType) { this.specialType = specialType;}


        public int getStatus() { return status;}


        public void setStatus(int status) { this.status = status;}


        public int getVideoId() { return videoId;}


        public void setVideoId(int videoId) { this.videoId = videoId;}


        public String getPosterPic() { return posterPic;}


        public void setPosterPic(String posterPic) { this.posterPic = posterPic;}


        public int getVideoSize() { return videoSize;}


        public void setVideoSize(int videoSize) { this.videoSize = videoSize;}


        public String getUrl() { return url;}


        public void setUrl(String url) { this.url = url;}


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
}
