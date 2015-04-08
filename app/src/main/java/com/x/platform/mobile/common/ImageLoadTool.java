package com.x.platform.mobile.common;

import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.x.platform.mobile.R;

/**
 * Created by chaochen on 14-9-22.
 */
public class ImageLoadTool {

    public ImageLoader imageLoader = ImageLoader.getInstance();



    public static DisplayImageOptions optionsImage = new DisplayImageOptions
            .Builder()
            .showImageOnLoading(R.drawable.ic_launcher)
            .showImageForEmptyUri(R.drawable.ic_launcher)
            .showImageOnFail(R.drawable.ic_launcher)
            .cacheInMemory(true)
            .cacheOnDisk(true)
            .considerExifParams(true)
            .build();

    //两像素圆角
    public static final DisplayImageOptions optionsRounded = new DisplayImageOptions.Builder()
            .showImageOnLoading(R.drawable.ic_launcher)
            .showImageForEmptyUri(R.drawable.ic_launcher)
            .showImageOnFail(R.drawable.ic_launcher)
            .cacheInMemory(true)
            .cacheOnDisk(true)
            .considerExifParams(true)
            .displayer(new RoundedBitmapDisplayer(2))
            .build();



    public ImageLoadTool() {
    }
    public void loadImageFromUrl(ImageView imageView, String url) {
        imageLoader.displayImage(url, imageView, optionsImage);
    }

    public void loadImageFromUrl(ImageView imageView, String url, DisplayImageOptions displayImageOptions) {
        imageLoader.displayImage(url, imageView, displayImageOptions);
    }
}

