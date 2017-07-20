package com.kcode.photolib;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import me.xiaopan.sketch.SketchImageView;

/**
 * Created by caik on 2017/7/20.
 */

public class PreviewAdapter extends PagerAdapter {
    private Context mContext;
    private ArrayList<String> mPath;

    public PreviewAdapter(Context context,ArrayList<String> path) {
        mContext = context;
        mPath = path;
    }

    @Override
    public int getCount() {
        return mPath == null ? 0 : mPath.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.item_sketch_view, container, false);

        SketchImageView imageView = (SketchImageView) view.findViewById(R.id.sketchView);
        imageView.displayImage(mPath.get(position));
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
