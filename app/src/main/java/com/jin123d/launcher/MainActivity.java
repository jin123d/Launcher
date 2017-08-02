package com.jin123d.launcher;

import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.text.CollationKey;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<AppItemBean> list = new ArrayList<>();
    private AppAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        adapter = new AppAdapter(MainActivity.this, list);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this, 5);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);

        getPackageList();
    }

    private void getPackageList() {
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);

        List<ResolveInfo> appList = getPackageManager().queryIntentActivities(mainIntent, 0);

        for (ResolveInfo resolveInfo : appList) {
            AppItemBean itemBean = new AppItemBean();
            itemBean.setName(resolveInfo.loadLabel(getPackageManager()).toString());
            itemBean.setIcon(resolveInfo.loadIcon(getPackageManager()));
            itemBean.setAppInfo(resolveInfo);
            list.add(itemBean);
        }
        Collections.sort(list, new CollatorComparator());

        adapter.notifyDataSetChanged();
    }


    @Override
    public void onBackPressed() {
    }


    public class CollatorComparator implements Comparator<AppItemBean> {
        Collator collator = Collator.getInstance();

        @Override
        public int compare(AppItemBean o1, AppItemBean o2) {
            CollationKey key1 = collator
                    .getCollationKey(o1.getName());
            CollationKey key2 = collator
                    .getCollationKey(o2.getName());
            return key1.compareTo(key2);
        }
    }
}
