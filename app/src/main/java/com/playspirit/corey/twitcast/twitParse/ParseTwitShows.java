package com.playspirit.corey.twitcast.twitParse;

import org.json.JSONObject;

/**
 * Created by Corey-Surface on 26-Jul-15.
 */
public class ParseTwitShows {

    //THIS SECTION IS FOR THE LIST OF SHOWS:
    private int mId;
    private String mLabel;
    private String mDiscription;
    private String mShowDate;
    private String mShowContactInfo;
    private String mTageLine;
    private String mShortCode;
    private String mHeroImageUrl;
    private JSONObject mHeroImageObject;
    private String mCoverArtUrl;
    private boolean mActiveStatus;
    private String mShowUrl;
    private String mCoverArtUrlSmall;
    private String mSHowCoverArtTitle;
    private String mCoverArtUrl300;
    private String mCoverArtUrlThumb;

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getLabel() {
        return mLabel;
    }

    public void setLabel(String label) {
        mLabel = label;
    }

    public String getDiscription() {
        return mDiscription;
    }

    public void setDiscription(String discription) {
        mDiscription = discription;
    }

    public String getShowDate() {
        return mShowDate;
    }

    public void setShowDate(String showDate) {
        mShowDate = showDate;
    }

    public String getShowContactInfo() {
        return mShowContactInfo;
    }

    public void setShowContactInfo(String showContactInfo) {
        mShowContactInfo = showContactInfo;
    }

    public String getTageLine() {
        return mTageLine;
    }

    public void setTageLine(String tageLine) {
        mTageLine = tageLine;
    }

    public String getShortCode() {
        return mShortCode;
    }

    public void setShortCode(String shortCode) {
        mShortCode = shortCode;
    }

    public String getHeroImageUrl() {
        return mHeroImageUrl;
    }

    public void setHeroImageUrl(String heroImageUrl) {
        mHeroImageUrl = heroImageUrl;
    }

    public JSONObject getHeroImageObject() {
        return mHeroImageObject;
    }

    public void setHeroImageObject(JSONObject heroImageObject) {
        mHeroImageObject = heroImageObject;
    }

    public String getCoverArtUrl() {
        return mCoverArtUrl;
    }

    public void setCoverArtUrl(String coverArtUrl) {
        mCoverArtUrl = coverArtUrl;
    }

    public boolean isActiveStatus() {
        return mActiveStatus;
    }

    public void setActiveStatus(boolean activeStatus) {
        mActiveStatus = activeStatus;
    }

    public String getShowUrl() {
        return mShowUrl;
    }

    public void setShowUrl(String showUrl) {
        mShowUrl = showUrl;
    }

    public String getCoverArtUrlSmall() {
        return mCoverArtUrlSmall;
    }

    public void setCoverArtUrlSmall(String coverArtUrlSmall) {
        mCoverArtUrlSmall = coverArtUrlSmall;
    }

    public String getSHowCoverArtTitle() {
        return mSHowCoverArtTitle;
    }

    public void setSHowCoverArtTitle(String SHowCoverArtTitle) {
        mSHowCoverArtTitle = SHowCoverArtTitle;
    }

    public String getCoverArtUrl300() {
        return mCoverArtUrl300;
    }

    public void setCoverArtUrl300(String coverArtUrl300) {
        mCoverArtUrl300 = coverArtUrl300;
    }

    public String getCoverArtUrlThumb() {
        return mCoverArtUrlThumb;
    }

    public void setCoverArtUrlThumb(String coverArtUrlThumb) {
        mCoverArtUrlThumb = coverArtUrlThumb;
    }
}
