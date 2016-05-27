
package lituchiy.max.internship.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("middle_name")
    @Expose
    private String middleName;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("birthday")
    @Expose
    private Integer birthday;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("address")
    @Expose
    private Address address;
    @SerializedName("fb_registered")
    @Expose
    private Integer fbRegistered;

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
     *     The firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * 
     * @param firstName
     *     The first_name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * 
     * @return
     *     The lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * 
     * @param lastName
     *     The last_name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * 
     * @return
     *     The middleName
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * 
     * @param middleName
     *     The middle_name
     */
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    /**
     * 
     * @return
     *     The email
     */
    public String getEmail() {
        return email;
    }

    /**
     * 
     * @param email
     *     The email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 
     * @return
     *     The birthday
     */
    public Integer getBirthday() {
        return birthday;
    }

    /**
     * 
     * @param birthday
     *     The birthday
     */
    public void setBirthday(Integer birthday) {
        this.birthday = birthday;
    }

    /**
     * 
     * @return
     *     The phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 
     * @param phone
     *     The phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 
     * @return
     *     The address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * 
     * @param address
     *     The address
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * 
     * @return
     *     The fbRegistered
     */
    public Integer getFbRegistered() {
        return fbRegistered;
    }

    /**
     * 
     * @param fbRegistered
     *     The fb_registered
     */
    public void setFbRegistered(Integer fbRegistered) {
        this.fbRegistered = fbRegistered;
    }

}
