package com.example.hyr.startv.utils;

import android.media.MediaScannerConnection;
import android.os.Environment;
import com.example.hyr.startv.App;
import java.io.File;
import java.util.Locale;
import java.util.regex.Pattern;

/**
 * Description:
 * 作者：hyr on 2016/11/24 13:50
 * 邮箱：2045446584@qq.com
 */
public class FileUtils {
    public final static String CACHE_DIR = "StarTv";
    private static final String VIDEO_CACHE_DIR = "videos";


    public static String getVideoDir() {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            String path = getMountedCacheDir() + File.separator + VIDEO_CACHE_DIR;
            File path1 = new File(path);
            if (!path1.exists()) {
                path1.mkdirs();
            }
            return path1.getPath();
        }
        return null;
    }


    /**
     * 获取缓存主目录
     */
    public static String getMountedCacheDir() {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            // 创建一个文件夹对象，赋值为外部存储器的目录
            File sdcardDir = Environment.getExternalStorageDirectory();
            //得到一个路径，内容是sdcard的文件夹路径和名字
            String path = sdcardDir.getPath() + File.separator + CACHE_DIR;
            File path1 = new File(path);
            if (!path1.exists()) {
                path1.mkdirs();
            }
            return path1.getPath();
        }
        return null;
    }


    public static String filenameFilter(String str) {
        return str == null ? null :FilePattern.matcher(str).replaceAll("");
    }


    private static Pattern FilePattern = Pattern.compile("[\\\\/:*?\"<>|]");

    public static boolean existFile(String path) {
        File path1 = new File(path);
        return path1.exists();
    }

    /**
     * 媒体扫描，防止下载后在sdcard中获取不到歌曲的信息
     *
     * @param path
     */
    public static void mp3Scanner(String path) {
        MediaScannerConnection.scanFile(App.getInstance().getApplicationContext(),
            new String[]{path}, null, null);
    }
    /**
     * 获取下载文件的大小
     *
     * @param soFarBytes 已下载字节
     * @param totalBytes 总共的字节
     * @return
     */
    public static String getProgressSize(long soFarBytes, long totalBytes) {
        float progress = soFarBytes * 1.0f / 1024 / 1024;
        float total = totalBytes * 1.0f / 1024 / 1024;
        String format = "%.1fM/%.1fM";
        String str = String.format(Locale.CHINA, format, progress, total);
        return str;
    }


    /**
     * 获取下载进度
     *
     * @param soFarBytes 已下载字节
     * @param totalBytes 总共的字节
     * @return
     */
    public static int getProgress(long soFarBytes, long totalBytes) {
        if (totalBytes != 0) {
            long progress = soFarBytes * 100 / totalBytes;
            return (int) progress;
        }
        return 0;
    }

}
