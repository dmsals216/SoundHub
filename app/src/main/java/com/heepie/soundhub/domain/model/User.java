package com.heepie.soundhub.domain.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Heepie on 2017. 11. 30..
 */

public class User implements Parcelable
{
    private String total_liked;

    private String id;

    private String is_active;

    private String is_staff;

    private String nickname;

    private String email;

    private String instrument;

    private String last_login;

    public User(){

    }

    public User(String id, String nickname, String email, String instrument) {
        this.id = id;
        this.nickname = nickname;
        this.email = email;
        this.instrument = instrument;
    }

    public String getTotal_liked() {
        return total_liked;
    }

    public void setTotal_liked(String total_liked) {
        this.total_liked = total_liked;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIs_active() {
        return is_active;
    }

    public void setIs_active(String is_active) {
        this.is_active = is_active;
    }

    public String getIs_staff() {
        return is_staff;
    }

    public void setIs_staff(String is_staff) {
        this.is_staff = is_staff;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getInstrument() {
        return instrument;
    }

    public void setInstrument(String instrument) {
        this.instrument = instrument;
    }

    public String getLast_login() {
        return last_login;
    }

    public void setLast_login(String last_login) {
        this.last_login = last_login;
    }

    protected User(Parcel in) {
        total_liked = in.readString();
        id = in.readString();
        is_active = in.readString();
        is_staff = in.readString();
        nickname = in.readString();
        email = in.readString();
        instrument = in.readString();
        last_login = in.readString();
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
        dest.writeString(total_liked);
        dest.writeString(id);
        dest.writeString(is_active);
        dest.writeString(is_staff);
        dest.writeString(nickname);
        dest.writeString(email);
        dest.writeString(instrument);
        dest.writeString(last_login);
    }
}

