package lituchiy.max.internship.data;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class AccessTokenRealm extends RealmObject {

    @PrimaryKey
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
