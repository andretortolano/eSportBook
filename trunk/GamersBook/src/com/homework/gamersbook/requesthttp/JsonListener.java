package com.homework.gamersbook.requesthttp;

import com.homework.gamersbook.model.UsherAPIResponse;

public interface JsonListener {
    public void onJsonGetFinished(UsherAPIResponse uAPIres);
}
