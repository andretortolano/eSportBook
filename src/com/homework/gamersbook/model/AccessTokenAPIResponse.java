package com.homework.gamersbook.model;

/**
 * @file AccessTokenAPIResponse.java
 * @author Andre Tortolano
 * @created 28/03/2014
 */
public class AccessTokenAPIResponse {

	private String mSig;
	private String mToken;

	public AccessTokenAPIResponse(String sig, String token) {
		mSig = sig;
		mToken = token;
	}

	public String getSig() {
		return mSig;
	}

	public String getToken() {
		return mToken;
	}
}
