
package com.hncgc1990.refreshloadmore.data;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Result {

    @SerializedName("createdAt")
    private String mCreatedAt;
    @SerializedName("desc")
    private String mDesc;
    @SerializedName("publishedAt")
    private String mPublishedAt;
    @SerializedName("source")
    private String mSource;
    @SerializedName("type")
    private String mType;
    @SerializedName("url")
    private String mUrl;
    @SerializedName("used")
    private Boolean mUsed;
    @SerializedName("who")
    private String mWho;
    @SerializedName("_id")
    private String m_id;

    public String getCreatedAt() {
        return mCreatedAt;
    }

    public void setCreatedAt(String createdAt) {
        mCreatedAt = createdAt;
    }

    public String getDesc() {
        return mDesc;
    }

    public void setDesc(String desc) {
        mDesc = desc;
    }

    public String getPublishedAt() {
        return mPublishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        mPublishedAt = publishedAt;
    }

    public String getSource() {
        return mSource;
    }

    public void setSource(String source) {
        mSource = source;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public Boolean getUsed() {
        return mUsed;
    }

    public void setUsed(Boolean used) {
        mUsed = used;
    }

    public String getWho() {
        return mWho;
    }

    public void setWho(String who) {
        mWho = who;
    }

    public String get_id() {
        return m_id;
    }

    public void set_id(String _id) {
        m_id = _id;
    }

    @Override
    public String toString() {
        return "Result{" +
                "mCreatedAt='" + mCreatedAt + '\'' +
                ", mDesc='" + mDesc + '\'' +
                ", mPublishedAt='" + mPublishedAt + '\'' +
                ", mSource='" + mSource + '\'' +
                ", mType='" + mType + '\'' +
                ", mUrl='" + mUrl + '\'' +
                ", mUsed=" + mUsed +
                ", mWho='" + mWho + '\'' +
                ", m_id='" + m_id + '\'' +
                '}';
    }
}
