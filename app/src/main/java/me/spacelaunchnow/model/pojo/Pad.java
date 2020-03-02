package me.spacelaunchnow.model.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pad implements Parcelable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("agency_id")
    @Expose
    private Object agencyId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("info_url")
    @Expose
    private Object infoUrl;
    @SerializedName("wiki_url")
    @Expose
    private String wikiUrl;
    @SerializedName("map_url")
    @Expose
    private String mapUrl;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("location")
    @Expose
    private Location location;
    public final static Parcelable.Creator<Pad> CREATOR = new Creator<Pad>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Pad createFromParcel(Parcel in) {
            return new Pad(in);
        }

        public Pad[] newArray(int size) {
            return (new Pad[size]);
        }

    }
            ;

    protected Pad(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.agencyId = ((Object) in.readValue((Object.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.infoUrl = ((Object) in.readValue((Object.class.getClassLoader())));
        this.wikiUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.mapUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.latitude = ((String) in.readValue((String.class.getClassLoader())));
        this.longitude = ((String) in.readValue((String.class.getClassLoader())));
        this.location = ((Location) in.readValue((Location.class.getClassLoader())));
    }

    public Pad() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Object getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(Object agencyId) {
        this.agencyId = agencyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getInfoUrl() {
        return infoUrl;
    }

    public void setInfoUrl(Object infoUrl) {
        this.infoUrl = infoUrl;
    }

    public String getWikiUrl() {
        return wikiUrl;
    }

    public void setWikiUrl(String wikiUrl) {
        this.wikiUrl = wikiUrl;
    }

    public String getMapUrl() {
        return mapUrl;
    }

    public void setMapUrl(String mapUrl) {
        this.mapUrl = mapUrl;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(agencyId);
        dest.writeValue(name);
        dest.writeValue(infoUrl);
        dest.writeValue(wikiUrl);
        dest.writeValue(mapUrl);
        dest.writeValue(latitude);
        dest.writeValue(longitude);
        dest.writeValue(location);
    }

    public int describeContents() {
        return 0;
    }

}
