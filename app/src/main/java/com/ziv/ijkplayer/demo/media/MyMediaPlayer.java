package com.ziv.ijkplayer.demo.media;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.Surface;
import android.view.SurfaceHolder;

import java.io.FileDescriptor;
import java.io.IOException;
import java.util.Map;

import tv.danmaku.ijk.media.player.AbstractMediaPlayer;
import tv.danmaku.ijk.media.player.MediaInfo;
import tv.danmaku.ijk.media.player.misc.ITrackInfo;

/**
 * 使用MediaPlayer调用系统硬解播放视频
 *
 * @author ziv on 17-12-27.
 */

public class MyMediaPlayer extends AbstractMediaPlayer {
    private static final String TAG = MyMediaPlayer.class.getName();
    private final Object mInitLock = new Object();
    private MediaPlayer mInternalMediaPlayer;

    // 由客户设定
    private Uri mUri;
    private Map<String, String> mHeaders;

    // 所有可能的内部状态
    private static final int STATE_ERROR = -1;
    private static final int STATE_IDLE = 0;
    private static final int STATE_PREPARING = 1;
    private static final int STATE_PREPARED = 2;
    private static final int STATE_PLAYING = 3;
    private static final int STATE_PAUSED = 4;
    private static final int STATE_PLAYBACK_COMPLETED = 5;

    // mCurrentState是一个VideoView对象的当前状态
    // mTargetState是方法调用者想要达到的状态
    // 例如,不管VideoView对象的当前状态如何,调用pause()意图使对象达到目标状态STATE_PAUSED
    private int mCurrentState = STATE_IDLE;
    private int mTargetState = STATE_IDLE;

    public MyMediaPlayer() {
        synchronized (mInitLock) {
            mInternalMediaPlayer = new MediaPlayer();
        }
    }

    @Override
    public void setDisplay(SurfaceHolder surfaceHolder) {

    }

    @Override
    public void setDataSource(Context context, Uri uri) throws IOException, IllegalArgumentException, SecurityException, IllegalStateException {

    }

    @Override
    public void setDataSource(Context context, Uri uri, Map<String, String> map) throws IOException, IllegalArgumentException, SecurityException, IllegalStateException {

    }

    @Override
    public void setDataSource(FileDescriptor fileDescriptor) throws IOException, IllegalArgumentException, IllegalStateException {

    }

    @Override
    public void setDataSource(String s) throws IOException, IllegalArgumentException, SecurityException, IllegalStateException {

    }

    @Override
    public String getDataSource() {
        return null;
    }

    @Override
    public void prepareAsync() throws IllegalStateException {

    }

    @Override
    public void start() throws IllegalStateException {

    }

    @Override
    public void stop() throws IllegalStateException {

    }

    @Override
    public void pause() throws IllegalStateException {

    }

    @Override
    public void setScreenOnWhilePlaying(boolean b) {

    }

    @Override
    public int getVideoWidth() {
        return 0;
    }

    @Override
    public int getVideoHeight() {
        return 0;
    }

    @Override
    public boolean isPlaying() {
        return false;
    }

    @Override
    public void seekTo(long l) throws IllegalStateException {

    }

    @Override
    public long getCurrentPosition() {
        return 0;
    }

    @Override
    public long getDuration() {
        return 0;
    }

    @Override
    public void release() {

    }

    @Override
    public void reset() {

    }

    @Override
    public void setVolume(float v, float v1) {

    }

    @Override
    public int getAudioSessionId() {
        return 0;
    }

    @Override
    public MediaInfo getMediaInfo() {
        return null;
    }

    @Override
    public void setLogEnabled(boolean b) {

    }

    @Override
    public boolean isPlayable() {
        return false;
    }

    @Override
    public void setAudioStreamType(int i) {

    }

    @Override
    public void setKeepInBackground(boolean b) {

    }

    @Override
    public int getVideoSarNum() {
        return 0;
    }

    @Override
    public int getVideoSarDen() {
        return 0;
    }

    @Override
    public void setWakeMode(Context context, int i) {

    }

    @Override
    public void setLooping(boolean b) {

    }

    @Override
    public boolean isLooping() {
        return false;
    }

    @Override
    public ITrackInfo[] getTrackInfo() {
        return new ITrackInfo[0];
    }

    @Override
    public void setSurface(Surface surface) {

    }
}
