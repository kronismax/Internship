package lituchiy.max.internship.data;

import io.realm.RealmList;
import io.realm.RealmObject;

public class AppealRealm extends RealmObject {

    private int id;
    private String title;
    private String address;
    private long createdDate;
    private long registeredDate;
    private long assignedDate;
    private long deadline;
    private String responsible;
    private int likes;
    private String description;
    private RealmList<TaskPhotoRealm> photo;


}
