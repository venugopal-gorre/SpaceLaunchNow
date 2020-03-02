package me.spacelaunchnow.model.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result implements Parcelable
{

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("img_url")
    @Expose
    private Object imgUrl;
    @SerializedName("status")
    @Expose
    private Status status;
    @SerializedName("net")
    @Expose
    private String net;
    @SerializedName("rocket")
    @Expose
    private Rocket rocket;
    @SerializedName("mission")
    @Expose
    private Mission mission;
    @SerializedName("pad")
    @Expose
    private Pad pad;
    @SerializedName("image_url")
    @Expose
    private Object imageUrl;
    @SerializedName("infographic_url")
    @Expose
    private Object infographicUrl;
    public final static Parcelable.Creator<Result> CREATOR = new Creator<Result>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Result createFromParcel(Parcel in) {
            return new Result(in);
        }

        public Result[] newArray(int size) {
            return (new Result[size]);
        }

    }
            ;

    protected Result(Parcel in) {
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.imgUrl = ((Object) in.readValue((Object.class.getClassLoader())));
        this.status = ((Status) in.readValue((Status.class.getClassLoader())));
        this.net = ((String) in.readValue((String.class.getClassLoader())));
        this.rocket = ((Rocket) in.readValue((Rocket.class.getClassLoader())));
        this.mission = ((Mission) in.readValue((Mission.class.getClassLoader())));
        this.pad = ((Pad) in.readValue((Pad.class.getClassLoader())));
        this.imageUrl = ((Object) in.readValue((Object.class.getClassLoader())));
        this.infographicUrl = ((Object) in.readValue((Object.class.getClassLoader())));
    }

    public Result() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(Object imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getNet() {
        return net;
    }

    public void setNet(String net) {
        this.net = net;
    }

    public Rocket getRocket() {
        return rocket;
    }

    public void setRocket(Rocket rocket) {
        this.rocket = rocket;
    }

    public Mission getMission() {
        return mission;
    }

    public void setMission(Mission mission) {
        this.mission = mission;
    }

    public Pad getPad() {
        return pad;
    }

    public void setPad(Pad pad) {
        this.pad = pad;
    }

    public Object getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(Object imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Object getInfographicUrl() {
        return infographicUrl;
    }

    public void setInfographicUrl(Object infographicUrl) {
        this.infographicUrl = infographicUrl;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(name);
        dest.writeValue(imgUrl);
        dest.writeValue(status);
        dest.writeValue(net);
        dest.writeValue(rocket);
        dest.writeValue(mission);
        dest.writeValue(pad);
        dest.writeValue(imageUrl);
        dest.writeValue(infographicUrl);
    }

    public int describeContents() {
        return 0;
    }

}
