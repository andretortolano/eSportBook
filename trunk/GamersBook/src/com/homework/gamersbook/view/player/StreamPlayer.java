/*
 * Copyright (C) 2014 Sony Mobile Communications AB.
 * All rights, including trade secret rights, reserved.
 */

package com.homework.gamersbook.view.player;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.VideoView;

import com.homework.gamersbook.R;
import com.homework.gamersbook.model.UsherAPIResponse;
import com.homework.gamersbook.requesthttp.JsonCustomAsyncTask;
import com.homework.gamersbook.requesthttp.JsonListener;
import com.homework.gamersbook.utils.Constants;

/**
 * @file StreamPlayer.java
 * @author Andre Tortolano (Andre.Tortolano@venturus.org.br)
 * @created 28/03/2014
 */
public class StreamPlayer extends Activity {

    private JsonCustomAsyncTask mJsonAsyncTask;

    /*
     * (non-Javadoc)
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stream_view);

        final ProgressBar streamProgressBar = (ProgressBar)findViewById(R.id.streamProgressBar);
        final VideoView streamPlayer = (VideoView)findViewById(R.id.streamPlayer);

        mJsonAsyncTask = new JsonCustomAsyncTask(new JsonListener() {

            @Override
            public void onJsonGetFinished(UsherAPIResponse uAPIres) {
                streamPlayer.stopPlayback();
                streamPlayer.setVideoURI(Uri.parse(uAPIres.getURL(UsherAPIResponse.QUALITY.HIGH)));
                MediaController mc = new MediaController(StreamPlayer.this);
                mc.setKeepScreenOn(true);
                streamPlayer.setMediaController(mc);
                streamPlayer.setVisibility(View.VISIBLE);
                streamProgressBar.setVisibility(View.GONE);
                streamPlayer.requestFocus();
                streamPlayer.start();
            }
        });

        mJsonAsyncTask
                .execute(getIntent().getExtras().getString(Constants.INTENT_KEY_CHANNEL_NAME));
    }
}
