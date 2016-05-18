
package lituchiy.max.internship.data.model;


public class Address_ {

    private Integer id;
    private District_ district;
    private City_ city;
    private Street_ street;
    private House_ house;
    private String flat;

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
     *     The district
     */
    public District_ getDistrict() {
        return district;
    }

    /**
     * 
     * @param district
     *     The district
     */
    public void setDistrict(District_ district) {
        this.district = district;
    }

    /**
     * 
     * @return
     *     The city
     */
    public City_ getCity() {
        return city;
    }

    /**
     * 
     * @param city
     *     The city
     */
    public void setCity(City_ city) {
        this.city = city;
    }

    /**
     * 
     * @return
     *     The street
     */
    public Street_ getStreet() {
        return street;
    }

    /**
     * 
     * @param street
     *     The street
     */
    public void setStreet(Street_ street) {
        this.street = street;
    }

    /**
     * 
     * @return
     *     The house
     */
    public House_ getHouse() {
        return house;
    }

    /**
     * 
     * @param house
     *     The house
     */
    public void setHouse(House_ house) {
        this.house = house;
    }

    /**
     * 
     * @return
     *     The flat
     */
    public String getFlat() {
        return flat;
    }

    /**
     * 
     * @param flat
     *     The flat
     */
    public void setFlat(String flat) {
        this.flat = flat;
    }

}
