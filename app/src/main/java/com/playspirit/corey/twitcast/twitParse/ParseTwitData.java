package com.playspirit.corey.twitcast.twitParse;

/**
 * Created by Corey-Surface on 26-Jul-15.
 */
public class ParseTwitData {
    private ParseTwitShows[] mParseTwitShows;
    private ParseTwitEpisodes[] mParseTwitEpisodes;

    public ParseTwitEpisodes[] getParseTwitEpisodes() {
        return mParseTwitEpisodes;
    }

    public void setParseTwitEpisodes(ParseTwitEpisodes[] parseTwitEpisodes) {
        mParseTwitEpisodes = parseTwitEpisodes;
    }

    public ParseTwitShows[] getParseTwitShows() {

        return mParseTwitShows;
    }

    public void setParseTwitShows(ParseTwitShows[] parseTwitShows) {
        mParseTwitShows = parseTwitShows;
    }
}
