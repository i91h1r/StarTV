package com.example.hyr.startv.ui.download;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.hyr.startv.App;
import com.example.hyr.startv.R;
import com.example.hyr.startv.ui.download.adapter.DownloadingAdapter;
import com.example.hyr.startv.ui.download.adapter.MyItemAnimator;

/**
 * Description:
 * 作者：hyr on 2016/11/25 15:38
 * 邮箱：2045446584@qq.com
 */
public class DownloadingFragment extends Fragment {
    public static final String TITLE = App.getInstance()
        .getString(R.string.downloading_fragment_title);

    private static DownloadingFragment downloadingFragment;
    private RecyclerView downloadingRecycle;
    private DownloadingAdapter downloadingAdapter;


    public static DownloadingFragment getInstance() {
        if (downloadingFragment == null) {
            downloadingFragment = new DownloadingFragment();
        }

        return downloadingFragment;
    }


    @Nullable @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_downloading_layout, container, false);

        initView(view);

        initRecycleView();
        return view;
    }


    private void initRecycleView() {

        downloadingAdapter = new DownloadingAdapter(getActivity());

        downloadingRecycle.setLayoutManager(new LinearLayoutManager(getActivity()));

        downloadingRecycle.setAdapter(downloadingAdapter);

        downloadingRecycle.setItemAnimator(new MyItemAnimator());

    }


    private void initView(View view) {
        downloadingRecycle = (RecyclerView) view.findViewById(R.id.downloading_recycle);

    }


    @Override public void onDestroy() {
        super.onDestroy();
        downloadingAdapter.onDestroy();
    }
}
