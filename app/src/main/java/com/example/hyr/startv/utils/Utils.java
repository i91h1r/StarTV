package com.example.hyr.startv.utils;

import android.content.Context;
import android.text.TextUtils;
import com.example.hyr.startv.R;
import com.example.hyr.startv.result.ArtistInfoResult;
import com.example.hyr.startv.result.BaseArtistInfo;
import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * 作者：hyr on 2016/11/10 15:34
 * 邮箱：2045446584@qq.com
 */
public class Utils {
    public static int sp2px(Context context, int i) {

        float scaledDensity = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (i * scaledDensity + 0.5f);
    }


    public static List<BaseArtistInfo> getArtistInfo(Context mContext, ArtistInfoResult artistInfoResult) {
        ArtistInfoResult.DataBean data = artistInfoResult.getData();

        List<BaseArtistInfo> list = new ArrayList<>();

        BaseArtistInfo baseArtistInfo1 = new BaseArtistInfo();
        BaseArtistInfo baseArtistInfo2 = new BaseArtistInfo();
        if (data.getArtistName() != null) {
            baseArtistInfo2.setKey(mContext.getResources().getString(R.string.artist_name));
            baseArtistInfo2.setValue(data.getArtistName());
            list.add(baseArtistInfo2);
        }
        if (!TextUtils.isEmpty(data.getAliasName()) && data.getArtistName() != null) {
            baseArtistInfo1.setKey(mContext.getResources().getString(R.string.alias_name));
            baseArtistInfo1.setValue(data.getAliasName());
            list.add(baseArtistInfo1);
        }

        BaseArtistInfo baseArtistInfo3 = new BaseArtistInfo();
        if (data.getBirthday() != null && !TextUtils.isEmpty(data.getBirthday())) {
            baseArtistInfo3.setKey(mContext.getResources().getString(R.string.birthday));
            baseArtistInfo3.setValue(data.getBirthday());
            list.add(baseArtistInfo3);
        }

        BaseArtistInfo baseArtistInfo4 = new BaseArtistInfo();

        if (data.getBirthplace() != null && !TextUtils.isEmpty(data.getBirthplace())) {
            baseArtistInfo4.setKey(mContext.getResources().getString(R.string.birthday_place));
            baseArtistInfo4.setValue(data.getBirthplace());

            list.add(baseArtistInfo4);
        }

        BaseArtistInfo baseArtistInfo5 = new BaseArtistInfo();
        if (data.getHeight() != null && !TextUtils.isEmpty(data.getHeight())) {
            baseArtistInfo5.setKey(mContext.getResources().getString(R.string.height));
            baseArtistInfo5.setValue(data.getHeight());
            list.add(baseArtistInfo5);
        }

        BaseArtistInfo baseArtistInfo7 = new BaseArtistInfo();
        if (data.getBodyWeight() != null && !TextUtils.isEmpty(data.getBodyWeight())) {
            baseArtistInfo7.setKey(mContext.getResources().getString(R.string.weight));
            baseArtistInfo7.setValue(data.getBodyWeight());
            list.add(baseArtistInfo7);
        }

        BaseArtistInfo baseArtistInfo8 = new BaseArtistInfo();
        if (data.getConstellation() != null && !TextUtils.isEmpty(data.getConstellation())) {
            baseArtistInfo8.setKey(mContext.getResources().getString(R.string.constellation));
            baseArtistInfo8.setValue(data.getConstellation());
            list.add(baseArtistInfo8);
        }
        BaseArtistInfo baseArtistInfo9 = new BaseArtistInfo();
        if (data.getBloodType() != null && !TextUtils.isEmpty(data.getBloodType())) {
            baseArtistInfo9.setKey(mContext.getResources().getString(R.string.BloodType));
            baseArtistInfo9.setValue(data.getBloodType());
            list.add(baseArtistInfo9);
        }
        BaseArtistInfo baseArtistInfo10 = new BaseArtistInfo();
        if (data.getNation() != null && !TextUtils.isEmpty(data.getNation())) {
            baseArtistInfo10.setKey(mContext.getResources().getString(R.string.nation));
            baseArtistInfo10.setValue(data.getNation());
            list.add(baseArtistInfo10);
        }
        BaseArtistInfo baseArtistInfo11 = new BaseArtistInfo();
        if (data.getNationality() != null && !TextUtils.isEmpty(data.getNationality())) {
            baseArtistInfo11.setKey(mContext.getResources().getString(R.string.nationality));
            baseArtistInfo11.setValue(data.getNationality());
            list.add(baseArtistInfo11);
        }

        BaseArtistInfo baseArtistInfo12 = new BaseArtistInfo();
        if (data.getHometown() != null && !TextUtils.isEmpty(data.getHometown())) {
            baseArtistInfo12.setKey(mContext.getResources().getString(R.string.hometown));
            baseArtistInfo12.setValue(data.getHometown());
            list.add(baseArtistInfo12);
        }

        BaseArtistInfo baseArtistInfo13 = new BaseArtistInfo();
        if (data.getDebutTime() != null && !TextUtils.isEmpty(data.getDebutTime())) {
            baseArtistInfo13.setKey(mContext.getResources().getString(R.string.debut_time));
            baseArtistInfo13.setValue(data.getDebutTime());
            list.add(baseArtistInfo13);
        }
        BaseArtistInfo baseArtistInfo14 = new BaseArtistInfo();
        if (data.getBrokerageFirm() != null && !TextUtils.isEmpty(data.getBrokerageFirm())) {
            baseArtistInfo14.setKey(mContext.getResources().getString(R.string.brokerage_firm));
            baseArtistInfo14.setValue(data.getBrokerageFirm());
            list.add(baseArtistInfo14);
        }

        BaseArtistInfo baseArtistInfo15 = new BaseArtistInfo();
        if (data.getArea() != null && !TextUtils.isEmpty(data.getArea())) {
            baseArtistInfo15.setKey(mContext.getResources().getString(R.string.area));
            baseArtistInfo15.setValue(data.getArea());
            list.add(baseArtistInfo15);
        }

        return list;
    }

}
