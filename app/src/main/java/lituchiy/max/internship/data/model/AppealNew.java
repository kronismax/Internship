
package lituchiy.max.internship.data.model;

import java.util.ArrayList;
import java.util.List;

public class AppealNew {

    private Integer id;
    private User user;
    private Address_ address;
    private Category category;
    private Type type;
    private String title;
    private String body;
    private Integer createdDate;
    private State state;
    private String ticketId;
    private List<File> files = new ArrayList<File>();
    private List<Object> performers = new ArrayList<Object>();
    private Integer deadline;
    private Integer likesCounter;

    /**
     * 
     * @return
     *     The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The user
     */
    public User getUser() {
        return user;
    }

    /**
     * 
     * @param user
     *     The user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * 
     * @return
     *     The address
     */
    public Address_ getAddress() {
        return address;
    }

    /**
     * 
     * @param address
     *     The address
     */
    public void setAddress(Address_ address) {
        this.address = address;
    }

    /**
     * 
     * @return
     *     The category
     */
    public Category getCategory() {
        return category;
    }

    /**
     * 
     * @param category
     *     The category
     */
    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * 
     * @return
     *     The type
     */
    public Type getType() {
        return type;
    }

    /**
     * 
     * @param type
     *     The type
     */
    public void setType(Type type) {
        this.type = type;
    }

    /**
     * 
     * @return
     *     The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 
     * @param title
     *     The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 
     * @return
     *     The body
     */
    public String getBody() {
        return body;
    }

    /**
     * 
     * @param body
     *     The body
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * 
     * @return
     *     The createdDate
     */
    public Integer getCreatedDate() {
        return createdDate;
    }

    /**
     * 
     * @param createdDate
     *     The created_date
     */
    public void setCreatedDate(Integer createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * 
     * @return
     *     The state
     */
    public State getState() {
        return state;
    }

    /**
     * 
     * @param state
     *     The state
     */
    public void setState(State state) {
        this.state = state;
    }

    /**
     * 
     * @return
     *     The ticketId
     */
    public String getTicketId() {
        return ticketId;
    }

    /**
     * 
     * @param ticketId
     *     The ticket_id
     */
    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    /**
     * 
     * @return
     *     The files
     */
    public List<File> getFiles() {
        return files;
    }

    /**
     * 
     * @param files
     *     The files
     */
    public void setFiles(List<File> files) {
        this.files = files;
    }

    /**
     * 
     * @return
     *     The performers
     */
    public List<Object> getPerformers() {
        return performers;
    }

    /**
     * 
     * @param performers
     *     The performers
     */
    public void setPerformers(List<Object> performers) {
        this.performers = performers;
    }

    /**
     * 
     * @return
     *     The deadline
     */
    public Integer getDeadline() {
        return deadline;
    }

    /**
     * 
     * @param deadline
     *     The deadline
     */
    public void setDeadline(Integer deadline) {
        this.deadline = deadline;
    }

    /**
     * 
     * @return
     *     The likesCounter
     */
    public Integer getLikesCounter() {
        return likesCounter;
    }

    /**
     * 
     * @param likesCounter
     *     The likes_counter
     */
    public void setLikesCounter(Integer likesCounter) {
        this.likesCounter = likesCounter;
    }

}
