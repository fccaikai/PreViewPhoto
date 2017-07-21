package com.kcode.photolib;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.ArrayList;

/**
 * Created by caik on 2017/7/20.
 */

public class PreviewPhotoAvitvity extends AppCompatActivity {

    private final static String PTAH = "path";
    private final static String INDEX = "index";

    private final static int DEFAULT_INDEX = 1;

    /**
     * @param context Context
     * @param paths   image url arrayList
     * @return Intent
     */
    public static Intent newIntent(Context context, ArrayList<String> paths) {
        return newIntent(context, paths, DEFAULT_INDEX);
    }

    /**
     * @param context Context
     * @param paths   image url arrayList
     * @param index   Show the first few photos,starting form 0
     * @return Intent
     */
    public static Intent newIntent(Context context, ArrayList<String> paths, int index) {
        if (paths == null || paths.size() == 0) {
            throw new NullPointerException("image url not be null");
        }

        if (index >= paths.size()) {
            throw new RuntimeException("index must be <  paths.size()");
        }
        Intent intent = new Intent(context, PreviewPhotoAvitvity.class);
        intent.putStringArrayListExtra(PTAH, paths);
        intent.putExtra(INDEX, index);
        return intent;
    }

    private final static String FORMAT = "%d/%d";
    private int mIndex;
    private ArrayList<String> mPath;
    private ActionBar mActionBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preview_photo_activity);
        mIndex = getIntent().getIntExtra(INDEX, 0);
        mPath = getIntent().getStringArrayListExtra("path");

        if (pathIsEmpty(mPath)) {
            finish();
            return;
        }

        initToolbar();

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new PreviewAdapter(getApplicationContext(), mPath));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                updateTitle(position + 1);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setCurrentItem(mIndex);
        updateTitle(mIndex+1);

    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mActionBar = getSupportActionBar();
        if (mActionBar == null) {
            finish();
            return;
        }
        mActionBar.setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void updateTitle(int index) {
        mActionBar.setTitle(String.format(FORMAT, index, mPath.size()));
    }

    private boolean pathIsEmpty(ArrayList<String> path) {
        if (path == null || path.size() == 0) {
            return true;
        }

        return false;
    }
}
