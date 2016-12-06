package com.example.hyr.startv.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.text.TextUtils;
import com.example.hyr.startv.result.VideoDownloadResult;
import com.orhanobut.logger.Logger;
import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * 作者：hyr on 2016/11/25 10:34
 * 邮箱：2045446584@qq.com
 */
public class VideoDao extends BaseDao {

    private final static String INDEX = "unique_index_sid";
    private final static String COLUMN_SID = "sid";//正数表示为在线，负数表示为本地歌曲
    private static final String TABLE = "VIDEO";
    private final static String COLUMN_ID = "_id";
    private final static String COLUMN_URL = "url";
    private final static String COLUMN_TITLE = "title";
    private final static String COLUMN_VIDEO_ID = "videoId";
    private final static String COLUMN_POSTERPIC = "posterPic";
    private final static String COLUMN_SIZE = "videoSize";
    private final static String COLUMN_ARTIST_NAME = "artistName";
    private final static String COLUMN_DURATION = "duration";
    private final static String COLUMN_DOWNLOAD = "download";//0：代表未下载；1：代表下载完成；2：代表下载正在进行中
    private final static String COLUMN_PATH = "path";//存储路径
    private final static String COLUMN_DOWNLOAD_TYPE = "downloadType";


    public static String createTable() {
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE IF NOT EXISTS " + TABLE + "(");
        sb.append(COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,");
        sb.append(COLUMN_SID + " BIGINT,");
        sb.append(COLUMN_TITLE + " varchar(100),");
        sb.append(COLUMN_DURATION + " INTEGER,");
        sb.append(COLUMN_VIDEO_ID + " INTEGER,");
        sb.append(COLUMN_URL + " TEXT,");
        sb.append(COLUMN_POSTERPIC + " TEXT,");
        sb.append(COLUMN_SIZE + " INTEGER,");
        sb.append(COLUMN_ARTIST_NAME + " TEXT,");
        sb.append(COLUMN_DOWNLOAD + " INTEGER,");
        sb.append(COLUMN_PATH + " TEXT,");
        sb.append(COLUMN_DOWNLOAD_TYPE + " INTEGER");
        sb.append(");");
        return sb.toString();

    }


    /**
     * 建立索引
     *
     * @return sql
     */
    public static String createIndex() {
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE UNIQUE INDEX " + INDEX + " ON ");
        sb.append(TABLE + " (" + COLUMN_SID + ")");
        return sb.toString();
    }


    /**
     * 获取表上所有视频
     */

    public List<VideoDownloadResult.DataBean> queryAll() {
        List<VideoDownloadResult.DataBean> dataBeanList = new ArrayList<>();

        Cursor cursor = query(TABLE, null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            dataBeanList.add(getVideo(cursor));
        }

        cursor.close();

        Logger.i("dataBeanList-------", dataBeanList);

        return dataBeanList;
    }


    /**
     * 数据库中插入或更新歌曲
     */
    public void insertOrUpdateSong(VideoDownloadResult.DataBean dataBean) {
        replace(TABLE, null, getVideoContent(dataBean));
    }


    private ContentValues getVideoContent(VideoDownloadResult.DataBean dataBean) {

        ContentValues values = new ContentValues();
        values.put(COLUMN_SID, dataBean.getVideoId() + "_" + dataBean.getDowloadType());
        values.put(COLUMN_TITLE, dataBean.getTitle());
        values.put(COLUMN_DURATION, dataBean.getDuration());
        values.put(COLUMN_VIDEO_ID, dataBean.getVideoId());
        values.put(COLUMN_URL, dataBean.getUri() == null ? null :dataBean.getUri().toString());
        values.put(COLUMN_POSTERPIC, dataBean.getPosterPic());
        values.put(COLUMN_SIZE, dataBean.getVideoSize());
        values.put(COLUMN_ARTIST_NAME, dataBean.getArtists().get(0).getArtistName());
        values.put(COLUMN_DOWNLOAD, dataBean.getDownload());
        values.put(COLUMN_PATH, dataBean.getPath());
        values.put(COLUMN_DOWNLOAD_TYPE, dataBean.getDowloadType());
        return values;
    }


    public boolean deleteSong(VideoDownloadResult.DataBean dataBean) {
        String whereClause = COLUMN_SID + "=?";
        String[] whereArgs = new String[] { dataBean.getId() + "" };
        int count = delete(TABLE, whereClause, whereArgs);
        return count > 0;
    }


    private VideoDownloadResult.DataBean getVideo(Cursor cursor) {

        String id = cursor.getString(cursor.getColumnIndex(COLUMN_SID));

        String title = cursor.getString(cursor.getColumnIndex(COLUMN_TITLE));

        int duration = cursor.getInt(cursor.getColumnIndex(COLUMN_DURATION));

        int videoId = cursor.getInt(cursor.getColumnIndex(COLUMN_VIDEO_ID));

        String url = cursor.getString(cursor.getColumnIndex(COLUMN_URL));

        String posterPic = cursor.getString(cursor.getColumnIndex(COLUMN_POSTERPIC));

        int size = cursor.getInt(cursor.getColumnIndex(COLUMN_SIZE));

        String artistName = cursor.getString(cursor.getColumnIndex(COLUMN_ARTIST_NAME));

        int download = cursor.getInt(cursor.getColumnIndex(COLUMN_DOWNLOAD));

        String path = cursor.getString(cursor.getColumnIndex(COLUMN_PATH));

        int downloadType = cursor.getInt(cursor.getColumnIndex(COLUMN_DOWNLOAD_TYPE));

        boolean status = false;
        if (download == 1 && !TextUtils.isEmpty(path) || !TextUtils.isEmpty(url)) {
            status = true;
        }

        ArrayList<VideoDownloadResult.DataBean.ArtistsBean> artistsBeanArrayList
            = new ArrayList<>();
        VideoDownloadResult.DataBean.ArtistsBean artistsBean
            = new VideoDownloadResult.DataBean.ArtistsBean();

        artistsBean.setArtistName(artistName);

        artistsBeanArrayList.add(artistsBean);
        VideoDownloadResult.DataBean dataBean = new VideoDownloadResult.DataBean(id, download,
            duration,
            title, videoId, posterPic, size, url,
            path, artistsBeanArrayList, status,downloadType);

        return dataBean;
    }
}
