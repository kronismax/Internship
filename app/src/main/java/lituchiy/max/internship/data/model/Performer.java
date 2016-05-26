
package lituchiy.max.internship.data.model;


public class Performer {

    private Integer id;
    private String organization;
    private String person;
    private Integer deadline;

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
     *     The organization
     */
    public String getOrganization() {
        return organization;
    }

    /**
     * 
     * @param organization
     *     The organization
     */
    public void setOrganization(String organization) {
        this.organization = organization;
    }

    /**
     * 
     * @return
     *     The person
     */
    public String getPerson() {
        return person;
    }

    /**
     * 
     * @param person
     *     The person
     */
    public void setPerson(String person) {
        this.person = person;
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

}
