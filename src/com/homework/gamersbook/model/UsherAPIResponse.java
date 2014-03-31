/*
 * Copyright (C) 2014 Sony Mobile Communications AB.
 * All rights, including trade secret rights, reserved.
 */

package com.homework.gamersbook.model;

/**
 * @file UserResponse.java
 * @author Andre Tortolano (Andre.Tortolano@venturus.org.br)
 * @created 28/03/2014
 */
public class UsherAPIResponse {
    
    public static enum QUALITY {
        SOURCE, HIGH, MEDIUM, LOW, MOBILE
    }
    
    private String mSourceURL;
    private String mHighURL;
    private String mMediumURL;
    private String mLowURL;
    private String mMobileURL;
    
    public String getURL(QUALITY q){
        switch(q){
            case SOURCE:
                return mSourceURL;
            case HIGH:
                return mHighURL;
            case MEDIUM:
                return mMediumURL;
            case LOW:
                return mLowURL;
            case MOBILE:
                return mMobileURL;
            default:
                break;
        }
        return null;
    }
    
    public void setURL(String url){
        if(url.contains("fmt=chunked") || url.contains("fmt=source")){
            mSourceURL = url;
        } else if(url.contains("fmt=high")){
            mHighURL = url;
        } else if(url.contains("fmt=medium")){
            mMediumURL = url;
        } else if(url.contains("fmt=low")){
            mLowURL = url;
        } else if(url.contains("fmt=mobile")){
            mMobileURL = url;
        }
    }
}
