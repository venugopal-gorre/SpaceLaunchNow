package me.spacelaunchnow.model.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Rocket implements Parcelable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("configuration")
    @Expose
    private Configuration configuration;

    public final static Parcelable.Creator<Rocket> CREATOR = new Creator<Rocket>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Rocket createFromParcel(Parcel in) {
            return new Rocket(in);
        }

        public Rocket[] newArray(int size) {
            return (new Rocket[size]);
        }

    }
            ;

    protected Rocket(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.configuration = ((Configuration) in.readValue((Configuration.class.getClassLoader())));
    }

    public Rocket() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(configuration);
    }

    public int describeContents() {
        return 0;
    }

}
