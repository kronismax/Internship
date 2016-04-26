package lituchiy.max.internship.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.UUID;

public class Appeal implements Parcelable {

    public static final String APPEALITEM = "appeal";

    private final String id;
    private AppealType type;
    private String address;
    private String status;
    private long created;
    private long registered;
    private long assigned;
    private String responsible;
    private String description;
    private int likes;

    private Appeal() { //[Comment] Can be private
        id = UUID.randomUUID().toString();
    }

    public Appeal(AppealType type, String address, String status, long created,
                  long registered, long assigned, String responsible,
                  String description, int likes) {
        this();
        this.type = type;
        this.address = address;
        this.status = status;
        this.created = created;
        this.registered = registered;
        this.assigned = assigned;
        this.responsible = responsible;
        this.description = description;
        this.likes = likes;
    }

    private Appeal(Parcel in) { //[Comment] Can be private
        id = in.readString();
        type = AppealType.fromInt(in.readInt());
        address = in.readString();
        status = in.readString();
        created = in.readLong();
        registered = in.readLong();
        assigned = in.readLong();
        responsible = in.readString();
        description = in.readString();
        likes = in.readInt();
    }

    public static final Creator<Appeal> CREATOR = new Creator<Appeal>() {
        @Override
        public Appeal createFromParcel(Parcel in) {
            return new Appeal(in);
        }

        @Override
        public Appeal[] newArray(int size) {
            return new Appeal[size];
        }
    };

    public AppealType getType() {
        return type;
    }

    public String getAddress() {
        return address;
    }

    public long getCreated() {
        return created;
    }

    public long getAssigned() {
        return assigned;
    }

    public int getLikes() {
        return likes;
    }

    public long getRegistered() {
        return registered;
    }

    public String getResponsible() {
        return responsible;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeInt(this.type.ordinal());
        dest.writeString(this.address);
        dest.writeString(this.status);
        dest.writeLong(this.created);
        dest.writeLong(this.registered);
        dest.writeLong(this.assigned);
        dest.writeString(this.responsible);
        dest.writeString(this.description);
        dest.writeInt(this.likes);
    }

    public enum AppealType {
        UTILITY,
        BUILDING,
        OTHER;

        public static AppealType fromInt(int i) {
            switch (i) {
                case 0:
                    return AppealType.UTILITY;
                case 1:
                    return AppealType.BUILDING;
                case 2:
                    return AppealType.OTHER;
            }
            return AppealType.OTHER;
        }
    }
}
