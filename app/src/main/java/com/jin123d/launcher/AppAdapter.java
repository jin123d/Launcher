package com.jin123d.launcher;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by hekr_jds on 8/2 0002.
 **/

public class AppAdapter extends RecyclerView.Adapter<AppAdapter.AppViewHolder> {
    private LayoutInflater layoutInflater;
    private List<AppItemBean> list;

    public AppAdapter(Context context, List<AppItemBean> lists) {
        layoutInflater = LayoutInflater.from(context);
        this.list = lists;
    }

    @Override
    public AppViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = layoutInflater.inflate(R.layout.item_layout_launcher, parent, false);
        return new AppViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(AppViewHolder holder, int position) {
        AppItemBean itemBean = list.get(position);
        holder.app_icon.setImageDrawable(itemBean.getIcon());
        holder.app_name.setText(itemBean.getName());
        holder.itemBean = itemBean;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class AppViewHolder extends RecyclerView.ViewHolder {

        private final ImageView app_icon;
        private final TextView app_name;
        private AppItemBean itemBean;

        public AppViewHolder(final View itemView) {
            super(itemView);
            app_icon = (ImageView) itemView.findViewById(R.id.icon);
            app_name = (TextView) itemView.findViewById(R.id.tvName);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String pkg = itemBean.getAppInfo().activityInfo.packageName;
                    String cls = itemBean.getAppInfo().activityInfo.name;
                    ComponentName component = new ComponentName(pkg, cls);
                    Intent intent = new Intent();
                    intent.setComponent(component);
                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }


}
