package com.example.hyr.startv.result;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * 作者：hyr on 2016/11/16 11:08
 * 邮箱：2045446584@qq.com
 */
public class ArtistInfoResult extends BaseResult implements Parcelable {


    private String code;
    private String msg;
    private long now;
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


    public static class DataBean implements Parcelable {

        private int artistId;
        private String artistName;
        private String aliasName;
        private String smallAvatar;
        private String birthday;
        private String constellation;
        private String bloodType;
        private String nationality;
        private String nation;
        private String height;
        private String bodyWeight;
        private String birthplace;
        private String hometown;
        private String debutTime;
        private String brokerageFirm;
        private String otherInfo;
        private String area;
        private int videoCount;
        private int fanCount;
        private int subCount;
        private boolean sub;


        private List<SimilarListBean> similarList;

        private List<ArtistMVsBean> artistMVs;


        public int getArtistId() { return artistId;}


        public void setArtistId(int artistId) { this.artistId = artistId;}


        public String getArtistName() { return artistName;}


        public void setArtistName(String artistName) { this.artistName = artistName;}


        public String getAliasName() { return aliasName;}


        public void setAliasName(String aliasName) { this.aliasName = aliasName;}


        public String getSmallAvatar() { return smallAvatar;}


        public void setSmallAvatar(String smallAvatar) { this.smallAvatar = smallAvatar;}


        public String getBirthday() { return birthday;}


        public void setBirthday(String birthday) { this.birthday = birthday;}


        public String getConstellation() { return constellation;}


        public void setConstellation(String constellation) { this.constellation = constellation;}


        public String getBloodType() { return bloodType;}


        public void setBloodType(String bloodType) { this.bloodType = bloodType;}


        public String getNationality() { return nationality;}


        public void setNationality(String nationality) { this.nationality = nationality;}


        public String getNation() { return nation;}


        public void setNation(String nation) { this.nation = nation;}


        public String getHeight() { return height;}


        public void setHeight(String height) { this.height = height;}


        public String getBodyWeight() { return bodyWeight;}


        public void setBodyWeight(String bodyWeight) { this.bodyWeight = bodyWeight;}


        public String getBirthplace() { return birthplace;}


        public void setBirthplace(String birthplace) { this.birthplace = birthplace;}


        public String getHometown() { return hometown;}


        public void setHometown(String hometown) { this.hometown = hometown;}


        public String getDebutTime() { return debutTime;}


        public void setDebutTime(String debutTime) { this.debutTime = debutTime;}


        public String getBrokerageFirm() { return brokerageFirm;}


        public void setBrokerageFirm(String brokerageFirm) { this.brokerageFirm = brokerageFirm;}


        public String getOtherInfo() { return otherInfo;}


        public void setOtherInfo(String otherInfo) { this.otherInfo = otherInfo;}


        public String getArea() { return area;}


        public void setArea(String area) { this.area = area;}


        public int getVideoCount() { return videoCount;}


        public void setVideoCount(int videoCount) { this.videoCount = videoCount;}


        public int getFanCount() { return fanCount;}


        public void setFanCount(int fanCount) { this.fanCount = fanCount;}


        public int getSubCount() { return subCount;}


        public void setSubCount(int subCount) { this.subCount = subCount;}


        public boolean isSub() { return sub;}


        public void setSub(boolean sub) { this.sub = sub;}


        public List<SimilarListBean> getSimilarList() { return similarList;}


        public void setSimilarList(List<SimilarListBean> similarList) {
            this.similarList = similarList;
        }


        public List<ArtistMVsBean> getArtistMVs() { return artistMVs;}


        public void setArtistMVs(List<ArtistMVsBean> artistMVs) { this.artistMVs = artistMVs;}


        public static class SimilarListBean implements Parcelable {


            private int artistId;
            private String artistName;
            private String smallAvatar;
            private int videoCount;
            private int fanCount;
            private int subCount;
            private boolean sub;


            public int getArtistId() { return artistId;}


            public void setArtistId(int artistId) { this.artistId = artistId;}


            public String getArtistName() { return artistName;}


            public void setArtistName(String artistName) { this.artistName = artistName;}


            public String getSmallAvatar() { return smallAvatar;}


            public void setSmallAvatar(String smallAvatar) { this.smallAvatar = smallAvatar;}


            public int getVideoCount() { return videoCount;}


            public void setVideoCount(int videoCount) { this.videoCount = videoCount;}


            public int getFanCount() { return fanCount;}


            public void setFanCount(int fanCount) { this.fanCount = fanCount;}


            public int getSubCount() { return subCount;}


            public void setSubCount(int subCount) { this.subCount = subCount;}


            public boolean isSub() { return sub;}


            public void setSub(boolean sub) { this.sub = sub;}


            @Override public int describeContents() { return 0; }


            @Override public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(this.artistId);
                dest.writeString(this.artistName);
                dest.writeString(this.smallAvatar);
                dest.writeInt(this.videoCount);
                dest.writeInt(this.fanCount);
                dest.writeInt(this.subCount);
                dest.writeByte(this.sub ? (byte) 1 :(byte) 0);
            }


            public SimilarListBean() {}


            protected SimilarListBean(Parcel in) {
                this.artistId = in.readInt();
                this.artistName = in.readString();
                this.smallAvatar = in.readString();
                this.videoCount = in.readInt();
                this.fanCount = in.readInt();
                this.subCount = in.readInt();
                this.sub = in.readByte() != 0;
            }


            public static final Creator<SimilarListBean> CREATOR = new Creator<SimilarListBean>() {
                @Override
                public SimilarListBean createFromParcel(Parcel source) {
                    return new SimilarListBean(source);
                }


                @Override
                public SimilarListBean[] newArray(int size) {return new SimilarListBean[size];}
            };
        }


        public static class ArtistMVsBean implements Parcelable {


            private String name;
            private MoreParamsBean moreParams;
            private String contentType;


            private List<ListBean> list;


            public String getName() { return name;}


            public void setName(String name) { this.name = name;}


            public MoreParamsBean getMoreParams() { return moreParams;}


            public void setMoreParams(MoreParamsBean moreParams) { this.moreParams = moreParams;}


            public String getContentType() { return contentType;}


            public void setContentType(String contentType) { this.contentType = contentType;}


            public List<ListBean> getList() { return list;}


            public void setList(List<ListBean> list) { this.list = list;}


            public static class MoreParamsBean implements Parcelable {
                /**
                 * parentKey : sort
                 * childKey : REGDATE
                 */

                private List<SelectedBean> selected;
                private List<Integer> position;


                public List<SelectedBean> getSelected() { return selected;}


                public void setSelected(List<SelectedBean> selected) { this.selected = selected;}


                public List<Integer> getPosition() { return position;}


                public void setPosition(List<Integer> position) { this.position = position;}


                public static class SelectedBean implements Parcelable {


                    private String parentKey;
                    private String childKey;


                    public String getParentKey() { return parentKey;}


                    public void setParentKey(String parentKey) { this.parentKey = parentKey;}


                    public String getChildKey() { return childKey;}


                    public void setChildKey(String childKey) { this.childKey = childKey;}


                    @Override public int describeContents() { return 0; }


                    @Override public void writeToParcel(Parcel dest, int flags) {
                        dest.writeString(this.parentKey);
                        dest.writeString(this.childKey);
                    }


                    public SelectedBean() {}


                    protected SelectedBean(Parcel in) {
                        this.parentKey = in.readString();
                        this.childKey = in.readString();
                    }


                    public static final Creator<SelectedBean> CREATOR
                        = new Creator<SelectedBean>() {
                        @Override
                        public SelectedBean createFromParcel(Parcel source) {
                            return new SelectedBean(source);
                        }


                        @Override
                        public SelectedBean[] newArray(int size) {return new SelectedBean[size];}
                    };
                }


                @Override public int describeContents() { return 0; }


                @Override public void writeToParcel(Parcel dest, int flags) {
                    dest.writeList(this.selected);
                    dest.writeList(this.position);
                }


                public MoreParamsBean() {}


                protected MoreParamsBean(Parcel in) {
                    this.selected = new ArrayList<SelectedBean>();
                    in.readList(this.selected, SelectedBean.class.getClassLoader());
                    this.position = new ArrayList<Integer>();
                    in.readList(this.position, Integer.class.getClassLoader());
                }


                public static final Creator<MoreParamsBean> CREATOR
                    = new Creator<MoreParamsBean>() {
                    @Override
                    public MoreParamsBean createFromParcel(Parcel source) {
                        return new MoreParamsBean(source);
                    }


                    @Override
                    public MoreParamsBean[] newArray(int size) {return new MoreParamsBean[size];}
                };
            }


            public static class ListBean implements Parcelable {


                private int videoId;
                private String type;
                private String title;
                private String desc;
                private String posterPic;
                private String albumImg;
                private int totalView;
                private String regdate;
                private boolean isVchart;
                private boolean ad;
                /**
                 * artistId : 9265
                 * artistName : 何晟铭
                 */

                private List<ArtistsBean> artists;
                private List<Integer> videoTypes;


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


                public String getAlbumImg() { return albumImg;}


                public void setAlbumImg(String albumImg) { this.albumImg = albumImg;}


                public int getTotalView() { return totalView;}


                public void setTotalView(int totalView) { this.totalView = totalView;}


                public String getRegdate() { return regdate;}


                public void setRegdate(String regdate) { this.regdate = regdate;}


                public boolean isIsVchart() { return isVchart;}


                public void setIsVchart(boolean isVchart) { this.isVchart = isVchart;}


                public boolean isAd() { return ad;}


                public void setAd(boolean ad) { this.ad = ad;}


                public List<ArtistsBean> getArtists() { return artists;}


                public void setArtists(List<ArtistsBean> artists) { this.artists = artists;}


                public List<Integer> getVideoTypes() { return videoTypes;}


                public void setVideoTypes(List<Integer> videoTypes) { this.videoTypes = videoTypes;}


                public static class ArtistsBean implements Parcelable {


                    private int artistId;
                    private String artistName;


                    public int getArtistId() { return artistId;}


                    public void setArtistId(int artistId) { this.artistId = artistId;}


                    public String getArtistName() { return artistName;}


                    public void setArtistName(String artistName) { this.artistName = artistName;}


                    @Override public int describeContents() { return 0; }


                    @Override public void writeToParcel(Parcel dest, int flags) {
                        dest.writeInt(this.artistId);
                        dest.writeString(this.artistName);
                    }


                    public ArtistsBean() {}


                    protected ArtistsBean(Parcel in) {
                        this.artistId = in.readInt();
                        this.artistName = in.readString();
                    }


                    public static final Creator<ArtistsBean> CREATOR = new Creator<ArtistsBean>() {
                        @Override
                        public ArtistsBean createFromParcel(Parcel source) {
                            return new ArtistsBean(source);
                        }


                        @Override
                        public ArtistsBean[] newArray(int size) {return new ArtistsBean[size];}
                    };
                }


                @Override public int describeContents() { return 0; }


                @Override public void writeToParcel(Parcel dest, int flags) {
                    dest.writeInt(this.videoId);
                    dest.writeString(this.type);
                    dest.writeString(this.title);
                    dest.writeString(this.desc);
                    dest.writeString(this.posterPic);
                    dest.writeString(this.albumImg);
                    dest.writeInt(this.totalView);
                    dest.writeString(this.regdate);
                    dest.writeByte(this.isVchart ? (byte) 1 :(byte) 0);
                    dest.writeByte(this.ad ? (byte) 1 :(byte) 0);
                    dest.writeList(this.artists);
                    dest.writeList(this.videoTypes);
                }


                public ListBean() {}


                protected ListBean(Parcel in) {
                    this.videoId = in.readInt();
                    this.type = in.readString();
                    this.title = in.readString();
                    this.desc = in.readString();
                    this.posterPic = in.readString();
                    this.albumImg = in.readString();
                    this.totalView = in.readInt();
                    this.regdate = in.readString();
                    this.isVchart = in.readByte() != 0;
                    this.ad = in.readByte() != 0;
                    this.artists = new ArrayList<ArtistsBean>();
                    in.readList(this.artists, ArtistsBean.class.getClassLoader());
                    this.videoTypes = new ArrayList<Integer>();
                    in.readList(this.videoTypes, Integer.class.getClassLoader());
                }


                public static final Creator<ListBean> CREATOR = new Creator<ListBean>() {
                    @Override public ListBean createFromParcel(Parcel source) {
                        return new ListBean(source);
                    }


                    @Override public ListBean[] newArray(int size) {return new ListBean[size];}
                };
            }


            @Override public int describeContents() { return 0; }


            @Override public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.name);
                dest.writeParcelable(this.moreParams, flags);
                dest.writeString(this.contentType);
                dest.writeList(this.list);
            }


            public ArtistMVsBean() {}


            protected ArtistMVsBean(Parcel in) {
                this.name = in.readString();
                this.moreParams = in.readParcelable(MoreParamsBean.class.getClassLoader());
                this.contentType = in.readString();
                this.list = new ArrayList<ListBean>();
                in.readList(this.list, ListBean.class.getClassLoader());
            }


            public static final Creator<ArtistMVsBean> CREATOR = new Creator<ArtistMVsBean>() {
                @Override
                public ArtistMVsBean createFromParcel(Parcel source) {
                    return new ArtistMVsBean(source);
                }


                @Override
                public ArtistMVsBean[] newArray(int size) {return new ArtistMVsBean[size];}
            };
        }


        @Override public int describeContents() { return 0; }


        @Override public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.artistId);
            dest.writeString(this.artistName);
            dest.writeString(this.aliasName);
            dest.writeString(this.smallAvatar);
            dest.writeString(this.birthday);
            dest.writeString(this.constellation);
            dest.writeString(this.bloodType);
            dest.writeString(this.nationality);
            dest.writeString(this.nation);
            dest.writeString(this.height);
            dest.writeString(this.bodyWeight);
            dest.writeString(this.birthplace);
            dest.writeString(this.hometown);
            dest.writeString(this.debutTime);
            dest.writeString(this.brokerageFirm);
            dest.writeString(this.otherInfo);
            dest.writeString(this.area);
            dest.writeInt(this.videoCount);
            dest.writeInt(this.fanCount);
            dest.writeInt(this.subCount);
            dest.writeByte(this.sub ? (byte) 1 :(byte) 0);
            dest.writeList(this.similarList);
            dest.writeList(this.artistMVs);
        }


        public DataBean() {}


        protected DataBean(Parcel in) {
            this.artistId = in.readInt();
            this.artistName = in.readString();
            this.aliasName = in.readString();
            this.smallAvatar = in.readString();
            this.birthday = in.readString();
            this.constellation = in.readString();
            this.bloodType = in.readString();
            this.nationality = in.readString();
            this.nation = in.readString();
            this.height = in.readString();
            this.bodyWeight = in.readString();
            this.birthplace = in.readString();
            this.hometown = in.readString();
            this.debutTime = in.readString();
            this.brokerageFirm = in.readString();
            this.otherInfo = in.readString();
            this.area = in.readString();
            this.videoCount = in.readInt();
            this.fanCount = in.readInt();
            this.subCount = in.readInt();
            this.sub = in.readByte() != 0;
            this.similarList = new ArrayList<SimilarListBean>();
            in.readList(this.similarList, SimilarListBean.class.getClassLoader());
            this.artistMVs = new ArrayList<ArtistMVsBean>();
            in.readList(this.artistMVs, ArtistMVsBean.class.getClassLoader());
        }


        public static final Creator<DataBean> CREATOR = new Creator<DataBean>() {
            @Override public DataBean createFromParcel(Parcel source) {return new DataBean(source);}


            @Override public DataBean[] newArray(int size) {return new DataBean[size];}
        };
    }


    @Override public int describeContents() { return 0; }


    @Override public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.code);
        dest.writeString(this.msg);
        dest.writeLong(this.now);
        dest.writeParcelable(this.data, flags);
        dest.writeInt(this.cost);
    }


    public ArtistInfoResult() {}


    protected ArtistInfoResult(Parcel in) {
        this.code = in.readString();
        this.msg = in.readString();
        this.now = in.readLong();
        this.data = in.readParcelable(DataBean.class.getClassLoader());
        this.cost = in.readInt();
    }


    public static final Parcelable.Creator<ArtistInfoResult> CREATOR
        = new Parcelable.Creator<ArtistInfoResult>() {
        @Override
        public ArtistInfoResult createFromParcel(Parcel source) {
            return new ArtistInfoResult(source);
        }


        @Override public ArtistInfoResult[] newArray(int size) {return new ArtistInfoResult[size];}
    };
}
