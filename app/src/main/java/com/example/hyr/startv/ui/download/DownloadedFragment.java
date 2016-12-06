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
import com.example.hyr.startv.ui.download.adapter.DownloadedAdapter;

/**
 * Description:
 * 作者：hyr on 2016/11/25 15:38
 * 邮箱：2045446584@qq.com
 */
public class DownloadedFragment extends Fragment {
    public static final String TITLE = App.getInstance()
        .getString(R.string.downloaded_fragment_title);

    private static DownloadedFragment downloadedFragment;
    private RecyclerView downloadedRecycle;
    private DownloadedAdapter downloadedAdapter;


    public static DownloadedFragment getInstance() {
        if (downloadedFragment == null) {

            downloadedFragment = new DownloadedFragment();

        }
        return downloadedFragment;
    }


    @Nullable @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_downloaded_layout, container, false);
        initView(view);
        initRecycleView();
        return view;
    }


    private void initView(View view) {

        downloadedRecycle = (RecyclerView) view.findViewById(R.id.downloaded_recycle);

    }


    private void initRecycleView() {
        downloadedAdapter = new DownloadedAdapter(getActivity());

        downloadedRecycle.setLayoutManager(new LinearLayoutManager(getActivity()));

        downloadedRecycle.setAdapter(downloadedAdapter);

    }


    @Override public void onDestroy() {
        super.onDestroy();
        downloadedAdapter.onDestroy();
    }
}
