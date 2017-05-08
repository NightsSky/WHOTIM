package com.nightssky.whotim.View.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.nightssky.whotim.Model.entity.test;
import com.nightssky.whotim.R;
import com.nightssky.whotim.View.activity.SettingActivity;
import com.nightssky.whotim.View.adapter.TraditionHeaderAdapter;
import com.nightssky.whotim.View.adapter.testAdapter;
import com.nightssky.whotim.View.common.DividerItemDecoration;
import com.nightssky.whotim.View.common.RecyclerViewScrollListener;
import com.nightssky.whotim.utils.DisplayUtils;
import com.sak.ultilviewlib.UltimateRefreshView;
import com.sak.ultilviewlib.interfaces.OnHeaderRefreshListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class MessageFragment extends Fragment {


    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.recycleView)
    RecyclerView mRecycleView;
    Unbinder unbinder;
    @BindView(R.id.titel)
    TextView mTitel;
    @BindView(R.id.right_btn)
    ImageView mRightBtn;
    @BindView(R.id.refreshView)
    UltimateRefreshView mRefreshView;

    public MessageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_message, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        DisplayUtils.setToolbarHeight(mToolbar, getActivity());

        mRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecycleView.setHasFixedSize(true);
        mRecycleView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        testAdapter adapter = new testAdapter(R.layout.item_message_fragment);
        mRecycleView.setAdapter(adapter);
        test test = new test();
        ArrayList<test> arrayList = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            arrayList.add(test);
        }
        adapter.setData(arrayList);
        mRecycleView.addOnScrollListener(new RecyclerViewScrollListener() {
            @Override
            public void hide() {
                mToolbar.animate()
                        .setDuration(500)
                        .translationY(-mToolbar.getHeight() + DisplayUtils.getStatusBarHight(getActivity()))
                        .setInterpolator(new AccelerateDecelerateInterpolator());
                mRecycleView.animate()
                        .setDuration(500)
                        .translationY(-mToolbar.getHeight() + DisplayUtils.getStatusBarHight(getActivity()))
                        .setInterpolator(new AccelerateDecelerateInterpolator());
                mTitel.animate()
                        .setDuration(500)
                        .alphaBy(1.0f)
                        .alpha(0)
                        .setInterpolator(new AccelerateDecelerateInterpolator());
                mRightBtn.animate()
                        .setDuration(500)
                        .alphaBy(1.0f)
                        .alpha(0)
                        .setInterpolator(new AccelerateDecelerateInterpolator());
            }

            @Override
            public void show() {
                mToolbar.animate()
                        .setDuration(500)
                        .translationY(0)
                        .setInterpolator(new AccelerateDecelerateInterpolator());
                mRecycleView.animate()
                        .setDuration(500)
                        .translationY(0)
                        .setInterpolator(new AccelerateDecelerateInterpolator());
                mTitel.animate()
                        .setDuration(500)
                        .alphaBy(0)
                        .alpha(1.0f)
                        .setInterpolator(new AccelerateDecelerateInterpolator());
                mRightBtn.animate()
                        .setDuration(500)
                        .alphaBy(0)
                        .alpha(1.0f)
                        .setInterpolator(new AccelerateDecelerateInterpolator());
            }
        });

        mRightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), SettingActivity.class));
            }
        });
        mRefreshView.setBaseHeaderAdapter(new TraditionHeaderAdapter(getContext()));
        mRefreshView.setOnHeaderRefreshListener(new OnHeaderRefreshListener() {
            @Override
            public void onHeaderRefresh(UltimateRefreshView view) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mRefreshView.onHeaderRefreshComplete();
                    }
                },2000);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
