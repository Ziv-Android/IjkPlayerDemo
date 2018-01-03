package com.ziv.ijkplayer.demo;

import android.graphics.Color;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
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

import java.util.ArrayList;
import java.util.HashMap;

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

    private ArrayList<HashMap<String, String>> datas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSettings = new Settings(this);

//        String url = "http://ips.ifeng.com/video02.ifeng.com/video04/2011/03/24/480x360_offline20110324.mp4";
        String url = "http://113.105.141.16/moviets.tc.qq.com/AL0GUV3WKKglywgolnfVsv8suBKh0asuJk2QTRfnC_q8/5ct543isjV5whUTwYuhfzWNpI3PcpwLQnWFqb3b0yk4lp1ryE2kDYGQl7zoKDdfbfs8pOe_hIRUA8Emjwc2i7hnss7_3zvEURnkPcakLVPYIBdwUb4bA2JsItrY3MUaHqsMOko8BzpodUWr6thVPUQ/o0025aj844b.322002.ts.m3u8?ver=4";
        mVideoUri = Uri.parse(url);

        // init UI
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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

//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
//            showDecodeSupport(MediaCodecList.ALL_CODECS);
//            showDecodeSupport(MediaCodecList.REGULAR_CODECS);
//        }
//
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
//            int codecCount = MediaCodecList.getCodecCount();
//            Log.d("MediaCodecList", "codecCount = " + codecCount);
//            HashMap<String, String> map;
//            for (int i = 0; i < codecCount; i++) {
//                MediaCodecInfo codecInfo = MediaCodecList.getCodecInfoAt(i);
//                map = new HashMap<>();
//                if (!codecInfo.isEncoder()) {
//                    continue;
//                }
//                map.put("decoderName", codecInfo.getName());
//                String[] types = codecInfo.getSupportedTypes();
//                for (int j = 0; j < types.length; j++) {
//                    if (map.containsValue(types[j])) {
//                        continue;
//                    } else {
//                        map.put("decoderType", types[j]);
//                    }
//                }
//                Log.d("MediaCodecList", "Decode[" + i + "]" + "\t:name = " + map.get("decoderName") + "\t,Type = " + map.get("decoderType"));
//                datas.add(map);
//            }
//        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void showDecodeSupport(int type) {
        MediaCodecList mediaCodecList = new MediaCodecList(type);
        MediaCodecInfo[] codecInfos = mediaCodecList.getCodecInfos();
        for (MediaCodecInfo info : codecInfos) {
            String name = info.getName();
            String supportedTypes = "";
            String[] supportedTypesArray = info.getSupportedTypes();
            for (String supportedType : supportedTypesArray) {
                supportedTypes = supportedTypes + supportedType + ", ";
            }
            Log.d("MediaCodecList", "name = " + name);
            Log.d("MediaCodecList", "supportedTypes = " + supportedTypes);
        }
        Log.d("MediaCodecList", "=========================================");
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
        boolean usingMediaCodec = mSettings.getUsingMediaCodec();
        switch (id) {
            case R.id.change_decoder_btn:

                if (!isHardDecode) {
                    mSettings.setPlayer("1");
//                    mSettings.setUsingMediaCodec(true);
                    choiceDecoderBtn.setText("硬解");
                    isHardDecode = true;
                } else {
                    mSettings.setPlayer("2");
//                    mSettings.setUsingMediaCodec(false);
                    choiceDecoderBtn.setText("软解");
                    isHardDecode = false;
                }
                mVideoView.switchDecode(mVideoUri);
                break;
            default:
                break;
        }
        Log.e("videoview", "usingMediaCodec is " + usingMediaCodec);
    }
}
