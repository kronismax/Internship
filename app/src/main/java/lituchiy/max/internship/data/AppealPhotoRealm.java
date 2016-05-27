package lituchiy.max.internship.data;

import io.realm.RealmObject;

public class AppealPhotoRealm extends RealmObject {

    private String imageUrl;

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

}
