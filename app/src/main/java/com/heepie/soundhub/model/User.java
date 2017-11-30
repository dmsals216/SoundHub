package com.heepie.soundhub.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Heepie on 2017. 11. 24..
 */

public class User implements Parcelable {
    public String user_name;
    public int user_image_path;
    public String user_like_count;


    public User(String user_name, int user_image_path, String user_like_count) {
        this.user_name = user_name;
        this.user_image_path = user_image_path;
        this.user_like_count = user_like_count;
    }

    public User() {

    }

    protected User(Parcel in) {
        user_name = in.readString();
        user_image_path = in.readInt();
        user_like_count = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(user_name);
        dest.writeInt(user_image_path);
        dest.writeString(user_like_count);
    }
}
