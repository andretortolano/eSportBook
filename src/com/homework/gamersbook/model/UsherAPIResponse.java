package com.homework.gamersbook.model;

/**
 * @file UserResponse.java
 * @author Andre Tortolano
 * @created 28/03/2014
 */
public class UsherAPIResponse {

	public static enum QUALITY {
		SOURCE, HIGH, MEDIUM, LOW, MOBILE
	}

	private String mSourceURL = null;
	private String mHighURL = null;
	private String mMediumURL = null;
	private String mLowURL = null;
	private String mMobileURL = null;

	private boolean mIsStreamOn = false;

	public String getURL(QUALITY q) {
		switch (q) {
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

	public void setURL(String url) {
		if (url.contains("fmt=chunked") || url.contains("fmt=source")) {
			mSourceURL = url;
			mIsStreamOn = true;
		} else if (url.contains("fmt=high")) {
			mHighURL = url;
			mIsStreamOn = true;
		} else if (url.contains("fmt=medium")) {
			mMediumURL = url;
			mIsStreamOn = true;
		} else if (url.contains("fmt=low")) {
			mLowURL = url;
			mIsStreamOn = true;
		} else if (url.contains("fmt=mobile")) {
			mMobileURL = url;
			mIsStreamOn = true;
		}
	}

	public String getBestQuality() {
		if (mSourceURL != null)
			return mSourceURL;
		else if (mHighURL != null)
			return mHighURL;
		else if (mMediumURL != null)
			return mMediumURL;
		else if (mLowURL != null)
			return mLowURL;
		else if (mMobileURL != null)
			return mMobileURL;
		return null;
	}

	public boolean isStreamOn() {
		return mIsStreamOn;
	}
}
