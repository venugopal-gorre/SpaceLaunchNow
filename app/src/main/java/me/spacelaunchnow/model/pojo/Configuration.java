package me.spacelaunchnow.model.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Configuration implements Parcelable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("launch_library_id")
    @Expose
    private Integer launchLibraryId;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("launch_service_provider")
    @Expose
    private String launchServiceProvider;
    public final static Parcelable.Creator<Configuration> CREATOR = new Creator<Configuration>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Configuration createFromParcel(Parcel in) {
            return new Configuration(in);
        }

        public Configuration[] newArray(int size) {
            return (new Configuration[size]);
        }

    }
            ;

    protected Configuration(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.launchLibraryId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.url = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.launchServiceProvider = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Configuration() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLaunchLibraryId() {
        return launchLibraryId;
    }

    public void setLaunchLibraryId(Integer launchLibraryId) {
        this.launchLibraryId = launchLibraryId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLaunchServiceProvider() {
        return launchServiceProvider;
    }

    public void setLaunchServiceProvider(String launchServiceProvider) {
        this.launchServiceProvider = launchServiceProvider;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(launchLibraryId);
        dest.writeValue(url);
        dest.writeValue(name);
        dest.writeValue(launchServiceProvider);
    }

    public int describeContents() {
        return 0;
    }

}
