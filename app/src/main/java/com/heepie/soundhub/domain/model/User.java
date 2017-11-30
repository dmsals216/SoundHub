package com.heepie.soundhub.domain.model;

/**
 * Created by Heepie on 2017. 11. 30..
 */

public class User
{
    private String id;

    private String is_active;

    private String is_staff;

    private String nickname;

    private String email;

    private String instrument;

    private String last_login;

    public User(String id, String nickname, String email, String instrument) {
        this.id = id;
        this.nickname = nickname;
        this.email = email;
        this.instrument = instrument;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getIs_active ()
    {
        return is_active;
    }

    public void setIs_active (String is_active)
    {
        this.is_active = is_active;
    }

    public String getIs_staff ()
    {
        return is_staff;
    }

    public void setIs_staff (String is_staff)
    {
        this.is_staff = is_staff;
    }

    public String getNickname ()
    {
        return nickname;
    }

    public void setNickname (String nickname)
    {
        this.nickname = nickname;
    }

    public String getEmail ()
    {
        return email;
    }

    public void setEmail (String email)
    {
        this.email = email;
    }

    public String getInstrument ()
    {
        return instrument;
    }

    public void setInstrument (String instrument)
    {
        this.instrument = instrument;
    }

    public String getLast_login ()
{
    return last_login;
}

    public void setLast_login (String last_login)
    {
        this.last_login = last_login;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", is_active = "+is_active+", is_staff = "+is_staff+", nickname = "+nickname+", email = "+email+", instrument = "+instrument+", last_login = "+last_login+"]";
    }
}

