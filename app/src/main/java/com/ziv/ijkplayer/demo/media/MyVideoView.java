package com.ziv.ijkplayer.demo.media;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.util.AttributeSet;

import com.ziv.ijkplayer.demo.application.Settings;

import tv.danmaku.ijk.media.exo.IjkExoMediaPlayer;
import tv.danmaku.ijk.media.player.AndroidMediaPlayer;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;
import tv.danmaku.ijk.media.player.TextureMediaPlayer;

/**
 * @author ziv on 17-12-22.
 */

public class MyVideoView extends IjkVideoView {
    public MyVideoView(Context context) {
        super(context);
    }

    public MyVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void switchDecode(Uri uri) {
        stopPlayback();
        setVideoURI(uri);
        start();
    }

//    public IMediaPlayer createPlayer(int playerType) {
//        IMediaPlayer mediaPlayer = null;
//
//        switch (playerType) {
//            case Settings.PV_PLAYER__IjkExoMediaPlayer: {
//                IjkExoMediaPlayer IjkExoMediaPlayer = new IjkExoMediaPlayer(mAppContext);
//                mediaPlayer = IjkExoMediaPlayer;
//            }
//            break;
//            case Settings.PV_PLAYER__AndroidMediaPlayer: {
//                AndroidMediaPlayer androidMediaPlayer = new AndroidMediaPlayer();
//                mediaPlayer = androidMediaPlayer;
//            }
//            break;
//            case Settings.PV_PLAYER__IjkMediaPlayer:
//            default: {
//                IjkMediaPlayer ijkMediaPlayer = null;
//                if (mUri != null) {
//                    ijkMediaPlayer = new IjkMediaPlayer();
//                    ijkMediaPlayer.native_setLogLevel(IjkMediaPlayer.IJK_LOG_DEBUG);
//
//                    if (mSettings.getUsingMediaCodec()) {
//                        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "mediacodec", 1);
//                        if (mSettings.getUsingMediaCodecAutoRotate()) {
//                            ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "mediacodec-auto-rotate", 1);
//                        } else {
//                            ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "mediacodec-auto-rotate", 0);
//                        }
//                        if (mSettings.getMediaCodecHandleResolutionChange()) {
//                            ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "mediacodec-handle-resolution-change", 1);
//                        } else {
//                            ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "mediacodec-handle-resolution-change", 0);
//                        }
//                    } else {
//                        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "mediacodec", 0);
//                    }
//
//                    if (mSettings.getUsingOpenSLES()) {
//                        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "opensles", 1);
//                    } else {
//                        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "opensles", 0);
//                    }
//
//                    String pixelFormat = mSettings.getPixelFormat();
//                    if (TextUtils.isEmpty(pixelFormat)) {
//                        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "overlay-format", IjkMediaPlayer.SDL_FCC_RV32);
//                    } else {
//                        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "overlay-format", pixelFormat);
//                    }
//                    ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "framedrop", 1);
//                    ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "start-on-prepared", 0);
//
//                    ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "http-detect-range-support", 0);
//
//                    ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_CODEC, "skip_loop_filter", 48);
//                }
//                mediaPlayer = ijkMediaPlayer;
//            }
//            break;
//        }
//
//        if (mSettings.getEnableDetachedSurfaceTextureView()) {
//            mediaPlayer = new TextureMediaPlayer(mediaPlayer);
//        }
//
//        return mediaPlayer;
//    }
}
