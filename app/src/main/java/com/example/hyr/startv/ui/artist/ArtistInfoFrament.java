package com.example.hyr.startv.ui.artist;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import cn.bingoogolapple.refreshlayout.BGAMeiTuanRefreshViewHolder;
import com.example.hyr.startv.R;
import com.example.hyr.startv.constants.Constants;
import com.example.hyr.startv.listener.OnItemClickListener;
import com.example.hyr.startv.result.ArtistInfoResult;
import com.example.hyr.startv.result.BaseArtistInfo;
import com.example.hyr.startv.ui.artist.adapter.BaseArtistInfoSection;
import com.example.hyr.startv.ui.artist.adapter.SimilarAdapter;
import com.example.hyr.startv.utils.Utils;
import com.example.hyr.startv.widget.sectioned.SectionedRecyclerViewAdapter;
import com.github.hyr0318.baselibrary.base.fragment.BaseFragment;
import com.github.hyr0318.baselibrary.eventbus.EventCenter;
import com.github.hyr0318.baselibrary.rxBus.RxBus;
import java.util.List;

/**
 * Description:
 * 作者：hyr on 2016/11/16 14:03
 * 邮箱：2045446584@qq.com
 */
public class ArtistInfoFrament extends BaseFragment implements OnItemClickListener {
    private RecyclerView similarRecycle;
    private ArtistInfoResult artistInfoResult;
    private SectionedRecyclerViewAdapter similarSection;
    private TextView tvArtistInfo;


    @Override protected View getRefreshLayoutView() {
        return null;
    }


    @Override
    protected void initRefreshLayout(BGAMeiTuanRefreshViewHolder bgaMeiTuanRefreshViewHolder) {

    }


    @Override protected void getViewById(View view) {

        similarRecycle = (RecyclerView) view.findViewById(R.id.similarRecycle);
        tvArtistInfo = (TextView) view.findViewById(R.id.tv_artist_info);

    }


    @Override protected void onUserInvisible() {

    }


    @Override protected void onFirstUserVisible() {

    }


    @Override protected void onUserVisible() {

    }


    @Override protected void initViewsAndEvents() {

    }


    @Override protected View getLoadingView() {
        return null;
    }


    @Override protected int getContentViewLayoutID() {
        return R.layout.artist_info_layout;
    }


    @Override protected boolean isBindRxBusHere() {
        return true;
    }



    @Override protected void onEventComming(EventCenter eventCenter) {
        int eventCode = eventCenter.getEventCode();

        if (eventCode == Constants.EVENT_SEND_ARTIST_INFO) {

            artistInfoResult = (ArtistInfoResult) eventCenter.getData();

            refreshData();
        }
    }


    public static ArtistInfoFrament getInstance() {
        ArtistInfoFrament artistInfoFrament = new ArtistInfoFrament();
        return artistInfoFrament;
    }


    @Override public void onItemClick(View view, int position) {

        RxBus.getDefault().postSticky(new EventCenter(Constants.EVENT_UPDATA_ARTIST_INFO,artistInfoResult.getData().getSimilarList().get(position).getArtistId()));

    }


    public void refreshData() {
        List<ArtistInfoResult.DataBean.SimilarListBean> similarList = artistInfoResult.getData()
            .getSimilarList();

        similarSection = new SectionedRecyclerViewAdapter();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 4);

        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override public int getSpanSize(int position) {

                int sectionItemViewType = similarSection.getSectionItemViewType(position);

                switch (sectionItemViewType) {
                    case SectionedRecyclerViewAdapter.VIEW_TYPE_HEADER:

                        return 4;

                    default:

                        if (similarSection.getSectionForPosition(
                            position) instanceof BaseArtistInfoSection) {

                            return 4;
                        } else {
                            return 1;
                        }

                }

            }
        });

        similarRecycle.setLayoutManager(gridLayoutManager);

        similarRecycle.setAdapter(similarSection);

        SimilarAdapter similarAdapter = new SimilarAdapter(mContext, similarList);

        similarSection.addSection(similarAdapter);

        List<BaseArtistInfo> artistInfo = Utils.getArtistInfo(mContext, artistInfoResult);

        BaseArtistInfoSection baseArtistInfoSection = new BaseArtistInfoSection(mContext,
            artistInfo);

        similarSection.addSection(baseArtistInfoSection);

        similarSection.notifyDataSetChanged();

        similarAdapter.setOnItemClickListener(this);

        tvArtistInfo.setText(artistInfoResult.getData().getOtherInfo());
    }
}
