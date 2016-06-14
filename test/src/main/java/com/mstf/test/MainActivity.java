package com.mstf.test;

import android.app.ListActivity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class MainActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String path = getIntent().getStringExtra("path");
        if(path == null) {
            path="";
        }
        setListAdapter(new SimpleAdapter(this, getData(path), android.R.layout.simple_list_item_1, new String[]{"title"}, new int[]{android.R.id.text1}));
        getListView().setTextFilterEnabled(true);
    }

    private List<HashMap<String, Object>> getData (String prefix) {
        List<HashMap<String, Object>> myData = new ArrayList<HashMap<String, Object>>();
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_MAIN);
        intent.addCategory("com.mstf.test");
        PackageManager manager = getPackageManager();
        List<ResolveInfo> infos = manager.queryIntentActivities(intent, 0);
        if(infos == null) {
            return myData;
        }
        String[] prefixPath;
        String prefixWithSlash = prefix;
        if(prefix.equals("")) {
            prefixPath = null;
        } else {
            prefixPath = prefix.split("/");
            prefixWithSlash = prefix+"/";
        }
        HashMap<String, Boolean> entries = new HashMap<String, Boolean>();
        for(ResolveInfo info:infos) {
            String labelSeq = info.loadLabel(getPackageManager()).toString();
            if(labelSeq == null) {
                labelSeq = info.activityInfo.name;
            }
            if(prefixWithSlash.length() == 0 || labelSeq.startsWith(prefixWithSlash)) {
                String[] labelPath = labelSeq.split("/");
                String nextLabel = labelPath[0];
                if(prefixPath != null) {
                    nextLabel = labelPath[prefixPath.length];
                }
                int length = 0;
                if(prefixPath != null) {
                    length = prefixPath.length;
                }
                if(length == (labelPath.length-1)) {
                    addItem(myData, nextLabel, activityIntent(info.activityInfo.applicationInfo.packageName, info.activityInfo.name));
                } else {
                    if(entries.get(nextLabel) == null) {
                        addItem(myData, nextLabel, browseIntent(prefix.equals("")?nextLabel:prefix+"/"+nextLabel));
                        entries.put(nextLabel, true);
                    }
                }
            }
        }
        return myData;
    }

    private void addItem(List<HashMap<String, Object>> myData, String label, Intent intent) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("title",label);
        map.put("intent", intent);
        myData.add(map);
    }

    private Intent activityIntent(String pkg, String name) {
        Intent intent = new Intent();
        intent.setClassName(pkg, name);
        return intent;
    }

    private Intent browseIntent(String path) {
        Intent intent = new Intent();
        intent.setClass(this, MainActivity.class);
        intent.putExtra("path", path);
        return intent;
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        HashMap<String, Object> map = (HashMap<String, Object>) l.getItemAtPosition(position);
        Intent intent = (Intent) map.get("intent");
        startActivity(intent);
    }
}