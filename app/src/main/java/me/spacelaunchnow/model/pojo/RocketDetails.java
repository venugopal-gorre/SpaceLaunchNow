package me.spacelaunchnow.model.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RocketDetails implements Parcelable
{

    @SerializedName("image_url")
    @Expose
    private String imageUrl;
    public final static Parcelable.Creator<RocketDetails> CREATOR = new Creator<RocketDetails>() {


        @SuppressWarnings({
                "unchecked"
        })
        public RocketDetails createFromParcel(Parcel in) {
            return new RocketDetails(in);
        }

        public RocketDetails[] newArray(int size) {
            return (new RocketDetails[size]);
        }

    }
            ;

    protected RocketDetails(Parcel in) {
        this.imageUrl = ((String) in.readValue((String.class.getClassLoader())));
    }

    public RocketDetails() {
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(imageUrl);
    }

    public int describeContents() {
        return 0;
    }

}
