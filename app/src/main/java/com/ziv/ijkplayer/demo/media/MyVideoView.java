package com.ziv.ijkplayer.demo.media;

import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;

/**
 * @author ziv on 17-12-22.
 */

public class MyVideoView extends IjkVideoView{
    public MyVideoView(Context context) {
        super(context);
    }

    public MyVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void switchDecode(Uri uri){
        stopPlayback();
        setVideoURI(uri);
        start();
    }
}
