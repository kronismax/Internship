package lituchiy.max.internship.data;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class AppealRealm extends RealmObject {

    public static final String APPEALITEM = "appeal";

    @PrimaryKey
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
    private String type;
    private String category;
    private int state;
    private String stateName;
    private RealmList<AppealPhotoRealm> photo;

    public AppealRealm() {
    }

    public AppealRealm(int id, String title, String address, long createdDate, long registeredDate,
                       long assignedDate, long deadline, String responsible, int likes,
                       String description, RealmList<AppealPhotoRealm> photo, String type) {
        this.id = id;
        this.title = title;
        this.address = address;
        this.createdDate = createdDate;
        this.registeredDate = registeredDate;
        this.assignedDate = assignedDate;
        this.deadline = deadline;
        this.responsible = responsible;
        this.likes = likes;
        this.description = description;
        this.photo = photo;
        this.type = type;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCreatedDate(long createdDate) {
        this.createdDate = createdDate;
    }

    public void setRegisteredDate(long registeredDate) {
        this.registeredDate = registeredDate;
    }

    public void setAssignedDate(long assignedDate) {
        this.assignedDate = assignedDate;
    }

    public void setDeadline(long deadline) {
        this.deadline = deadline;
    }

    public void setResponsible(String responsible) {
        this.responsible = responsible;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPhoto(RealmList<AppealPhotoRealm> photo) {
        this.photo = photo;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAddress() {
        return address;
    }

    public long getCreatedDate() {
        return createdDate;
    }

    public long getRegisteredDate() {
        return registeredDate;
    }

    public long getAssignedDate() {
        return assignedDate;
    }

    public long getDeadline() {
        return deadline;
    }

    public String getResponsible() {
        return responsible;
    }

    public int getLikes() {
        return likes;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public RealmList<AppealPhotoRealm> getPhoto() {
        return photo;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }
    public String getStateName() {
        return stateName;
    }
}
