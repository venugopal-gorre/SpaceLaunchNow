package me.spacelaunchnow.model.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Mission implements Parcelable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("launch_library_id")
    @Expose
    private Integer launchLibraryId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("orbit")
    @Expose
    private Object orbit;
    @SerializedName("orbit_abbrev")
    @Expose
    private String orbitAbbrev;
    public final static Parcelable.Creator<Mission> CREATOR = new Creator<Mission>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Mission createFromParcel(Parcel in) {
            return new Mission(in);
        }

        public Mission[] newArray(int size) {
            return (new Mission[size]);
        }

    }
            ;

    protected Mission(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.launchLibraryId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.description = ((String) in.readValue((String.class.getClassLoader())));
        this.type = ((String) in.readValue((String.class.getClassLoader())));
        this.orbit = ((Object) in.readValue((Object.class.getClassLoader())));
        this.orbitAbbrev = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Mission() {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getOrbit() {
        return orbit;
    }

    public void setOrbit(Object orbit) {
        this.orbit = orbit;
    }

    public String getOrbitAbbrev() {
        return orbitAbbrev;
    }

    public void setOrbitAbbrev(String orbitAbbrev) {
        this.orbitAbbrev = orbitAbbrev;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(launchLibraryId);
        dest.writeValue(name);
        dest.writeValue(description);
        dest.writeValue(type);
        dest.writeValue(orbit);
        dest.writeValue(orbitAbbrev);
    }

    public int describeContents() {
        return 0;
    }

}
