package com.nightssky.whotim.View.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nightssky.whotim.Model.entity.test;
import com.nightssky.whotim.R;
import com.nightssky.whotim.View.activity.SettingActivity;
import com.nightssky.whotim.View.adapter.testAdapter;
import com.nightssky.whotim.View.widget.pullRecyclerView.PullBaseView;
import com.nightssky.whotim.View.widget.pullRecyclerView.PullRecyclerView;
import com.nightssky.whotim.utils.DisplayUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class MessageFragment extends Fragment implements PullBaseView.OnRefreshListener {


    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    Unbinder unbinder;
    @BindView(R.id.titel)
    TextView mTitel;
    @BindView(R.id.right_btn)
    ImageView mRightBtn;
    @BindView(R.id.recycleView)
    PullRecyclerView mRecycleView;


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
        mRecycleView.setOnRefreshListener(this);
//        mRecycleView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        testAdapter adapter = new testAdapter(R.layout.item_message_fragment);
        mRecycleView.setAdapter(adapter);
        test test = new test();
        ArrayList<test> arrayList = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            arrayList.add(test);
        }
        adapter.setData(arrayList);
//        mRecycleView.addOnScrollListener(new RecyclerViewScrollListener() {
//            @Override
//            public void hide() {
//                mToolbar.animate()
//                        .setDuration(500)
//                        .translationY(-mToolbar.getHeight() + DisplayUtils.getStatusBarHight(getActivity()))
//                        .setInterpolator(new AccelerateDecelerateInterpolator());
//                mRefreshLayout.animate()
//                        .setDuration(500)
//                        .translationY(-mToolbar.getHeight() + DisplayUtils.getStatusBarHight(getActivity()))
//                        .setInterpolator(new AccelerateDecelerateInterpolator());
//                mTitel.animate()
//                        .setDuration(500)
//                        .alphaBy(1.0f)
//                        .alpha(0)
//                        .setInterpolator(new AccelerateDecelerateInterpolator());
//                mRightBtn.animate()
//                        .setDuration(500)
//                        .alphaBy(1.0f)
//                        .alpha(0)
//                        .setInterpolator(new AccelerateDecelerateInterpolator());
//            }
//
//            @Override
//            public void show() {
//                mToolbar.animate()
//                        .setDuration(500)
//                        .translationY(0)
//                        .setListener(new Animator.AnimatorListener() {
//                            @Override
//                            public void onAnimationStart(Animator animation) {
//
//                            }
//
//                            @Override
//                            public void onAnimationEnd(Animator animation) {
//
//                            }
//
//                            @Override
//                            public void onAnimationCancel(Animator animation) {
//
//                            }
//
//                            @Override
//                            public void onAnimationRepeat(Animator animation) {
//
//                            }
//                        })
//                        .setInterpolator(new AccelerateDecelerateInterpolator());
//
//                mTitel.animate()
//                        .setDuration(500)
//                        .alphaBy(0)
//                        .alpha(1.0f)
//                        .setInterpolator(new AccelerateDecelerateInterpolator());
//                mRightBtn.animate()
//                        .setDuration(500)
//                        .alphaBy(0)
//                        .alpha(1.0f)
//                        .setInterpolator(new AccelerateDecelerateInterpolator());
//            }
//        });

        mRightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), SettingActivity.class));
            }
        });


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onHeaderRefresh(PullBaseView view) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                mRecycleView.onHeaderRefreshComplete();
            }
        }, 1500);
    }

    @Override
    public void onFooterRefresh(PullBaseView view) {

    }
}
