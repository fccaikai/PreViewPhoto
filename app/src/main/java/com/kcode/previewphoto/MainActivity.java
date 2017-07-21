package com.kcode.previewphoto;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.kcode.photolib.PreviewPhotoAvitvity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> paths = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        paths.add("http://www.pp3.cn/uploads/201410/2014101101.jpg");
        paths.add("http://imgsrc.baidu.com/imgad/pic/item/a8014c086e061d9589ca911d71f40ad162d9ca03.jpg");
        paths.add("http://imgsrc.baidu.com/imgad/pic/item/faedab64034f78f0a40136d773310a55b3191c8f.jpg");
        paths.add("http://imgsrc.baidu.com/imgad/pic/item/5366d0160924ab1890b71a123ffae6cd7b890b75.jpg");


    }

    public void preview(View view){
        startActivity(PreviewPhotoAvitvity.newIntent(this, paths,2));
    }
}
