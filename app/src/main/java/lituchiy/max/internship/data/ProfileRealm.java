package lituchiy.max.internship.data;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ProfileRealm extends RealmObject {

    @PrimaryKey
    private String name;
    private String link;
    private String profilePicture;


    public String getName() {
        return name;
    }

    public String getLink() {
        return link;
    }

    public String getProfilePicture() {
        return profilePicture;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }
}
