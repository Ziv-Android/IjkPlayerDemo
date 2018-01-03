package com.ziv.ijkplayer.demo.application;

/**
 * @author ziv on 17-12-26.
 */

public class PlayerConfig {
    private PlayerConfig playerConfig;

    private PlayerConfig() {
    }

    public PlayerConfig instance(){
        if (playerConfig == null) {
            playerConfig = new PlayerConfig();
        }
        return playerConfig;
    }

    public boolean enableBackgroundPlay;

    public int playerType;

    public boolean usingMediaCodec;

    public boolean getUsingMediaCodecAutoRotate;

    public boolean getMediaCodecHandleResolutionChange;

    public boolean getUsingOpenSLES;

    public String getPixelFormat;

    public boolean getEnableNoView;

    public boolean getEnableSurfaceView;

    public boolean getEnableTextureView;

    public boolean getEnableDetachedSurfaceTextureView;

    public boolean getUsingMediaDataSource;
}
