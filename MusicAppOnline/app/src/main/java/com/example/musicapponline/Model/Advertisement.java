package com.example.musicapponline.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Advertisement implements Parcelable {
    private long id;
    private String image;
    private String content;
    private Song song;


    protected Advertisement(Parcel in) {
        id = in.readLong();
        image = in.readString();
        content = in.readString();
        song = in.readParcelable(Song.class.getClassLoader());
    }

    public Advertisement(){

    }

    public static final Creator<Advertisement> CREATOR = new Creator<Advertisement>() {
        @Override
        public Advertisement createFromParcel(Parcel in) {
            return new Advertisement(in);
        }

        @Override
        public Advertisement[] newArray(int size) {
            return new Advertisement[size];
        }
    };

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(image);
        dest.writeString(content);
        dest.writeParcelable(song, flags);
    }
}
