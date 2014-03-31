/*
 * Copyright (C) 2014 Sony Mobile Communications AB.
 * All rights, including trade secret rights, reserved.
 */

package com.homework.gamersbook.model;

/**
 * @file AccessTokenAPIResponse.java
 * @author Andre Tortolano (Andre.Tortolano@venturus.org.br)
 * @created 28/03/2014
 */
public class AccessTokenAPIResponse {

    private String mSig;
    private String mToken;
    
    public AccessTokenAPIResponse(String sig, String token){
        mSig = sig;
        mToken = token;
    }
    
    public String getSig(){
        return mSig;
    }
    
    public String getToken(){
        return mToken;
    }
}
