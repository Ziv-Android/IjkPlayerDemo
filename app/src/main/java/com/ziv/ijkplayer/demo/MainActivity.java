package com.ziv.ijkplayer.demo;

import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;

import com.ziv.ijkplayer.demo.application.Settings;
import com.ziv.ijkplayer.demo.fragment.TracksFragment;
import com.ziv.ijkplayer.demo.media.AndroidMediaController;
import com.ziv.ijkplayer.demo.media.IjkVideoView;
import com.ziv.ijkplayer.demo.media.MeasureHelper;
import com.ziv.ijkplayer.demo.media.MyVideoView;

import tv.danmaku.ijk.media.player.IjkMediaPlayer;
import tv.danmaku.ijk.media.player.misc.IMediaFormat;
import tv.danmaku.ijk.media.player.misc.ITrackInfo;

public class MainActivity extends AppCompatActivity implements TracksFragment.ITrackHolder, View.OnClickListener {
    private AndroidMediaController mMediaController;
    private MyVideoView mVideoView;
    private TextView mToastTextView;
    private TableLayout mHudView;
    private DrawerLayout mDrawerLayout;
    private ViewGroup mRightDrawer;
    private Button choiceDecoderBtn;

    private Settings mSettings;
    private boolean mBackPressed;

    private String mVideoPath;
    private Uri mVideoUri;

    private boolean isHardDecode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSettings = new Settings(this);

//        String url = "http://ips.ifeng.com/video02.ifeng.com/video04/2011/03/24/480x360_offline20110324.mp4";
        String url = "http://183.60.23.18/moviets.tc.qq.com/AVHF97ewAx5UzQBPIS_pGkK0ikdCrXXnn-8mzAE2zGP0/KI2Pf19ddMTEF_04CLoG2ASa6xX2c3IGb1wu3pEIxCB-jAjGCnCllxT-kKAwYIOnaXNCDcfVIm4wZVHX3QOcfxN8KHCYbF3CxuoGOTl_iuIR9FYi5i_RIgTmUyuoYhpFXBl-m-9FgB_g_AozC4vUeQ/s0025cu1a4z.322003.ts.m3u8?ver=4";
        mVideoUri = Uri.parse(url);

        // init UI
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        mMediaController = new AndroidMediaController(this, false);
        mMediaController.setSupportActionBar(actionBar);

        mToastTextView = findViewById(R.id.toast_text_view);
        mHudView = findViewById(R.id.hud_view);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mRightDrawer = findViewById(R.id.right_drawer);

        mDrawerLayout.setScrimColor(Color.TRANSPARENT);

        choiceDecoderBtn = findViewById(R.id.change_decoder_btn);
        choiceDecoderBtn.setOnClickListener(this);
        // init player
        IjkMediaPlayer.loadLibrariesOnce(null);
        IjkMediaPlayer.native_profileBegin("libijkplayer.so");

        mVideoView = findViewById(R.id.video_view);
        mVideoView.setMediaController(mMediaController);
        mVideoView.setHudView(mHudView);

        mVideoView.setVideoURI(mVideoUri);
        mVideoView.start();
    }

    @Override
    public void onBackPressed() {
        mBackPressed = true;

        super.onBackPressed();
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (mBackPressed || !mVideoView.isBackgroundPlayEnabled()) {
            mVideoView.stopPlayback();
            mVideoView.release(true);
            mVideoView.stopBackgroundPlay();
        } else {
            mVideoView.enterBackground();
        }
        IjkMediaPlayer.native_profileEnd();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_player, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_toggle_ratio:
                int aspectRatio = mVideoView.toggleAspectRatio();
                String aspectRatioText = MeasureHelper.getAspectRatioText(this, aspectRatio);
                mToastTextView.setText(aspectRatioText);
                mMediaController.showOnce(mToastTextView);
                return true;
            case R.id.action_toggle_player:
                int player = mVideoView.togglePlayer();
                String playerText = IjkVideoView.getPlayerText(this, player);
                mToastTextView.setText(playerText);
                mMediaController.showOnce(mToastTextView);
                return true;
            case R.id.action_toggle_render:
                int render = mVideoView.toggleRender();
                String renderText = IjkVideoView.getRenderText(this, render);
                mToastTextView.setText(renderText);
                mMediaController.showOnce(mToastTextView);
                return true;
            case R.id.action_show_info:
                mVideoView.showMediaInfo();
                break;
            case R.id.action_show_tracks:
//                if (mDrawerLayout.isDrawerOpen(mRightDrawer)) {
//                    Fragment f = getSupportFragmentManager().findFragmentById(R.id.right_drawer);
//                    if (f != null) {
//                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//                        transaction.remove(f);
//                        transaction.commit();
//                    }
//                    mDrawerLayout.closeDrawer(mRightDrawer);
//                } else {
//                    Fragment f = TracksFragment.newInstance();
//                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//                    transaction.replace(R.id.right_drawer, f);
//                    transaction.commit();
//                    mDrawerLayout.openDrawer(mRightDrawer);
//                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public ITrackInfo[] getTrackInfo() {
        if (mVideoView == null)
            return null;

        return mVideoView.getTrackInfo();
    }

    @Override
    public int getSelectedTrack(int trackType) {
        if (mVideoView == null)
            return -1;

        return mVideoView.getSelectedTrack(trackType);
    }

    @Override
    public void selectTrack(int stream) {
        mVideoView.selectTrack(stream);
    }

    @Override
    public void deselectTrack(int stream) {
        mVideoView.deselectTrack(stream);
    }

    /**
     * 按钮点击事件
     *
     * @param view 被点击View
     */
    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.change_decoder_btn:

                if (!isHardDecode) {
                    mSettings.setPlayer("1");
                    choiceDecoderBtn.setText("硬解");
                    isHardDecode = true;
                } else {
                    mSettings.setPlayer("2");
                    choiceDecoderBtn.setText("软解");
                    isHardDecode = false;
                }
                mVideoView.switchDecode(mVideoUri);
                break;
            default:
                break;
        }
    }
}
