package com.stayli.app.model.database;


import android.arch.persistence.room.Entity;
import android.support.annotation.NonNull;

@Entity(primaryKeys = {"ctime", "jtime"})
public class DBJieQi {

    @NonNull
    private String jieqiid;
    @NonNull
    private String name;
    private String pic;
    @NonNull
    private String ctime;
    @NonNull
    private String jtime;
    @NonNull
    private String year;

    @NonNull
    public String getYear() {
        return year;
    }

    public void setYear(@NonNull String year) {
        this.year = year;
    }

    @NonNull
    public String getJieqiid() {
        return jieqiid;
    }

    public void setJieqiid(@NonNull String jieqiid) {
        this.jieqiid = jieqiid;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    @NonNull
    public String getCtime() {
        return ctime;
    }

    public void setCtime(@NonNull String ctime) {
        this.ctime = ctime;
    }

    @NonNull
    public String getJtime() {
        return jtime;
    }

    public void setJtime(@NonNull String jtime) {
        this.jtime = jtime;
    }

    @Override
    public String toString() {
        return "DBJieQi{" +
                "jieqiid='" + jieqiid + '\'' +
                ", name='" + name + '\'' +
                ", pic='" + pic + '\'' +
                ", ctime='" + ctime + '\'' +
                ", jtime='" + jtime + '\'' +
                '}';
    }
}
