
package lituchiy.max.internship.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Appeal {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("category")
    @Expose
    private Category category;
    @SerializedName("type")
    @Expose
    private Type type;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("body")
    @Expose
    private String body;
    @SerializedName("created_date")
    private long createdDate;
    @SerializedName("start_date")
    @Expose
    private long startDate;
    @SerializedName("completed_date")
    @Expose
    private long completedDate;
    @SerializedName("state")
    @Expose
    private State state;
    @SerializedName("ticket_id")
    @Expose
    private String ticketId;
    @SerializedName("files")
    @Expose
    private List<Files> files = new ArrayList<>();
    @SerializedName("performers")
    @Expose
    private List<Performer> performers = new ArrayList<>();
    @SerializedName("deadline")
    @Expose
    private long deadline;
    @SerializedName("likes_counter")
    @Expose
    private Integer likesCounter;

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Category getCategory() {
        return category;
    }

    public Type getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public long getCreatedDate() {
        return createdDate;
    }

    public long getStartDate() {
        return startDate;
    }

    public long getCompletedDate() {
        return completedDate;
    }

    public State getState() {
        return state;
    }

    public String getTicketId() {
        return ticketId;
    }

    public List<Files> getFiles() {
        return files;
    }

    public List<Performer> getPerformers() {
        return performers;
    }

    public long getDeadline() {
        return deadline;
    }

    public Integer getLikesCounter() {
        return likesCounter;
    }

}
