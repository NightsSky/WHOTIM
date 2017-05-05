package com.nightssky.whotim.View.common;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2017/3/31.
 */
//D(实体类)和VH分别代表了的 数据源、ViewHold
public abstract class BaseRecycleViewAdapter<D,VH extends RecyclerView.ViewHolder>
        extends RecyclerView.Adapter<VH> implements View.OnClickListener{
    private List<D> mList;
    private int layoutResId;
    /**
     * Item点击事件
     */
    private onItemClickListener clickListener;
    public BaseRecycleViewAdapter(List<D> list, int layoutResId) {
        mList = list;

        if (layoutResId != 0) {
            this.layoutResId = layoutResId;
        } else {
            throw new NullPointerException("请设置Item资源id");
        }
    }
    public BaseRecycleViewAdapter( int layoutResId) {
        mList = new ArrayList<>();
        if (layoutResId != 0) {
            this.layoutResId = layoutResId;
        } else {
            throw new NullPointerException("请设置Item资源id");
        }
    }
    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutResId, parent, false);
        return (VH) new BaseViewHolder(view);
    }
//添加数据
    public void addData(List<D> list) {
        int size = this.mList.size();
        this.mList.addAll(list);
        notifyItemInserted(size+1);
    }

    //更新数据
    public void setData(List<D> list) {
        if (mList.size() > 0) {
            mList.clear();
        }
        mList.addAll(list);
        notifyDataSetChanged();
    }
    @Override
    public void onBindViewHolder(VH holder, int position) {
        holder.itemView.setOnClickListener(this);
        holder.itemView.setTag(position);
        bindTheData(holder, mList.get(position),position);
    }
    /**
     * 绑定数据
     *
     * @param holder 视图管理者
     * @param data   数据源
     */
    protected abstract void bindTheData(VH holder, D data,int position);

    @Override
    public int getItemCount() {
        return mList.size();
    }


    @Override
    public void onClick(View v) {
        //点击回调
        if (clickListener != null) {
            clickListener.onItemClick((Integer) v.getTag(), v);
        }
    }

    public D getItem(int position) {
        if (position < mList.size()) {
            return mList.get(position);
        }
        return null;
    }
    /**
     * 自定义ViewHolder
     */
   public class BaseViewHolder extends RecyclerView.ViewHolder {
        private SparseArray<View> views;

        public BaseViewHolder(View itemView) {
            super(itemView);
            views = new SparseArray<>();
        }

        public <T extends View> T findViewById(int resId) {
            View view = views.get(resId);
            if (view == null) {
                view = itemView.findViewById(resId);
                views.put(resId,view);
            }
            return (T) view;
        }
        /**
         * 设置文本资源
         *
         * @param viewId view id
         * @param s      字符
         */
        public TextView setText(int viewId, CharSequence s) {
            TextView view = findViewById(viewId);
            view.setText(s);
            return view;
        }

        /**
         *  设置图片
         * @param viewId
         * @param url
         * @return
         */
        public ImageView setImg(int viewId, CharSequence url) {
            ImageView view = findViewById(viewId);
//            Glide.with(MyApplication.getInstance())
//                    .load(url)
//                    .placeholder(R.mipmap.image_loading)
//                    .crossFade(100)
//                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
//                    .centerCrop()
//                    .into(view);
            return view;
        }

        public View getView(int viewId) {
            View view = findViewById(viewId);
            return view;
        }
    }
    /**
     * 设置点击监听
     *
     * @param clickListener 监听器
     */
    public void setItemClickListener(onItemClickListener clickListener) {
        this.clickListener = clickListener;
    }
    public interface onItemClickListener {
        void onItemClick(int position, View v);
    }

    public interface onItemLongClickListener {
        boolean onItemLonClick(int position, View v);
    }
}
