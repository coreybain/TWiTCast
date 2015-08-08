package com.playspirit.corey.twitcast.twitParse;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Corey-Surface on 26-Jul-15.
 */
public class ParseTwitEpisodes {

    //THIS SECTION IS FOR THE LIST OF EPISODES:
    private int mId;
    private String mLabel;
    private String mCleanPath;
    private int mEpisodeNumber;
    private String mTeaser;
    private long mAiringDate;
    private String mShowNotes;
    private JSONObject mRelatedLinks;
    private String mRelatedLinksUrl;
    private String mRelatedLinksTitle;
    private String mHeroImageUrl;
    private JSONObject mHeroImageObject;
    private JSONObject mVideo_hd;
    private JSONObject mVideo_large;
    private JSONObject mVideo_small;
    private JSONObject mVideo_audio;
    private String mMediaUrl;
    private String mCoverArtUrl300;
    private String mCoverArtUrlThumb;

    private long mRunningTime;
    private int mHours;
    private int mMinutes;
    private int mSeconds;
    private int mSize;
    private String mFormat;
    private String mCoverArtUrl;
    private String mCoverArtUrlSmall;
    private int mPublished;
    private JSONObject mEmbedded;
    private JSONArray mShows;
    private int mShowId;
    private String mShowLabel;
    private String mShowDescription;
    private JSONObject mShowCoverArt;
    private String mShowCoverArtUrl;
    private String mShowCoverArtUrlSmall;
    private String mShowCoverArtTitle;

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

    public String getCleanPath() {
        return mCleanPath;
    }

    public void setCleanPath(String cleanPath) {
        mCleanPath = cleanPath;
    }

    public int getEpisodeNumber() {
        return mEpisodeNumber;
    }

    public void setEpisodeNumber(int episodeNumber) {
        mEpisodeNumber = episodeNumber;
    }

    public String getTeaser() {
        return mTeaser;
    }

    public void setTeaser(String teaser) {
        mTeaser = teaser;
    }

    public long getAiringDate() {
        return mAiringDate;
    }

    public void setAiringDate(long airingDate) {
        mAiringDate = airingDate;
    }

    public String getShowNotes() {
        return mShowNotes;
    }

    public void setShowNotes(String showNotes) {
        mShowNotes = showNotes;
    }

    public JSONObject getRelatedLinks() {
        return mRelatedLinks;
    }

    public void setRelatedLinks(JSONObject relatedLinks) {
        mRelatedLinks = relatedLinks;
    }

    public String getRelatedLinksUrl() {
        return mRelatedLinksUrl;
    }

    public void setRelatedLinksUrl(String relatedLinksUrl) {
        mRelatedLinksUrl = relatedLinksUrl;
    }

    public String getRelatedLinksTitle() {
        return mRelatedLinksTitle;
    }

    public void setRelatedLinksTitle(String relatedLinksTitle) {
        mRelatedLinksTitle = relatedLinksTitle;
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

    public JSONObject getVideo_hd() {
        return mVideo_hd;
    }

    public void setVideo_hd(JSONObject video_hd) {
        mVideo_hd = video_hd;
    }

    public JSONObject getVideo_large() {
        return mVideo_large;
    }

    public void setVideo_large(JSONObject video_large) {
        mVideo_large = video_large;
    }

    public JSONObject getVideo_small() {
        return mVideo_small;
    }

    public void setVideo_small(JSONObject video_small) {
        mVideo_small = video_small;
    }

    public JSONObject getVideo_audio() {
        return mVideo_audio;
    }

    public void setVideo_audio(JSONObject video_audio) {
        mVideo_audio = video_audio;
    }

    public String getMediaUrl() {
        return mMediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        mMediaUrl = mediaUrl;
    }

    public long getRunningTime() {
        return mRunningTime;
    }

    public void setRunningTime(long runningTime) {
        mRunningTime = runningTime;
    }

    public int getHours() {
        return mHours;
    }

    public void setHours(int hours) {
        mHours = hours;
    }

    public int getMinutes() {
        return mMinutes;
    }

    public void setMinutes(int minutes) {
        mMinutes = minutes;
    }

    public int getSeconds() {
        return mSeconds;
    }

    public void setSeconds(int seconds) {
        mSeconds = seconds;
    }

    public int getSize() {
        return mSize;
    }

    public void setSize(int size) {
        mSize = size;
    }

    public String getFormat() {
        return mFormat;
    }

    public void setFormat(String format) {
        mFormat = format;
    }

    public String getCoverArtUrl() {
        return mCoverArtUrl;
    }

    public void setCoverArtUrl(String coverArtUrl) {
        mCoverArtUrl = coverArtUrl;
    }

    public String getCoverArtUrlSmall() {
        return mCoverArtUrlSmall;
    }

    public void setCoverArtUrlSmall(String coverArtUrlSmall) {
        mCoverArtUrlSmall = coverArtUrlSmall;
    }

    public int getPublished() {
        return mPublished;
    }

    public void setPublished(int published) {
        mPublished = published;
    }

    public JSONObject getEmbedded() {
        return mEmbedded;
    }

    public void setEmbedded(JSONObject embedded) {
        mEmbedded = embedded;
    }

    public JSONArray getShows() {
        return mShows;
    }

    public void setShows(JSONArray shows) {
        mShows = shows;
    }

    public int getShowId() {
        return mShowId;
    }

    public void setShowId(int showId) {
        mShowId = showId;
    }

    public String getShowLabel() {
        return mShowLabel;
    }

    public void setShowLabel(String showLabel) {
        mShowLabel = showLabel;
    }

    public String getShowDescription() {
        return mShowDescription;
    }

    public void setShowDescription(String showDescription) {
        mShowDescription = showDescription;
    }

    public JSONObject getShowCoverArt() {
        return mShowCoverArt;
    }

    public void setShowCoverArt(JSONObject showCoverArt) {
        mShowCoverArt = showCoverArt;
    }

    public String getShowCoverArtUrl() {
        return mShowCoverArtUrl;
    }

    public void setShowCoverArtUrl(String showCoverArtUrl) {
        mShowCoverArtUrl = showCoverArtUrl;
    }

    public String getShowCoverArtUrlSmall() {
        return mShowCoverArtUrlSmall;
    }

    public void setShowCoverArtUrlSmall(String showCoverArtUrlSmall) {
        mShowCoverArtUrlSmall = showCoverArtUrlSmall;
    }

    public String getShowCoverArtTitle() {
        return mShowCoverArtTitle;
    }

    public void setShowCoverArtTitle(String showCoverArtTitle) {
        mShowCoverArtTitle = showCoverArtTitle;
    }
}
