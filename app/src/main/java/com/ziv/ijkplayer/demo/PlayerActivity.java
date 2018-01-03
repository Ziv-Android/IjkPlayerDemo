package com.ziv.ijkplayer.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/**
 * @author ziv on 17-12-29.
 */

public class PlayerActivity extends AppCompatActivity {
    private SurfaceView mSurfaceViewVideo;
    IjkMediaPlayer ijkMediaPlayer;
//    private String url = "http://ips.ifeng.com/video02.ifeng.com/video04/2011/03/24/480x360_offline20110324.mp4";
    private String url = "http://219.135.57.140/moviets.tc.qq.com/ACb17d7LLDu6P863jc6xqU6VTmB3XXoinL6kEc0543xM/7fuLya9FY3pJGeKHFaMWFKiE2nCzRwRbaVZeudOgJ5A85Uj0TmvzqgdXiWQqSayyYJ6UO1F_aUzrut4BSIs7yH5RYJDLEs7TKikPDLeITDkO6C8693p50Utg2arck0WdMTUdmva4QXJGOX8CdnJT8Q/s0025cu1a4z.322002.ts.m3u8?cdncode=%2F18907E7BE0798990%2F&ver=4&sdtfrom=v8137&cost=low";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        mSurfaceViewVideo = findViewById(R.id.video_view);
        // init player
        IjkMediaPlayer.loadLibrariesOnce(null);
        IjkMediaPlayer.native_profileBegin("libijkplayer.so");

        try {
            ijkMediaPlayer = new IjkMediaPlayer();
            ijkMediaPlayer.setDataSource(url);
            ijkMediaPlayer.setOnPreparedListener(onPreparedListener);
            ijkMediaPlayer.setOnErrorListener(onErrorListener);
            ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "start-on-prepared", 0);
            ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "framedrop", 50);
            ijkMediaPlayer.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }

        mSurfaceViewVideo.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
//                ijkMediaPlayer.setDisplay(surfaceHolder);
                ijkMediaPlayer.setSurface(surfaceHolder.getSurface());
            }

            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

            }
        });
    }

    IMediaPlayer.OnPreparedListener onPreparedListener = new IMediaPlayer.OnPreparedListener(){
        @Override
        public void onPrepared(IMediaPlayer iMediaPlayer) {
            ijkMediaPlayer.start();
        }
    };

    IMediaPlayer.OnErrorListener onErrorListener = new IMediaPlayer.OnErrorListener() {
        @Override
        public boolean onError(IMediaPlayer iMediaPlayer, int i, int i1) {
            Log.e("ziv", "ijkplayer error , [ " + i + " ], [ " + i1 +" ]");
            return false;
        }
    };

    @Override
    protected void onStop() {
        IjkMediaPlayer.native_profileEnd();
        super.onStop();
    }
}
