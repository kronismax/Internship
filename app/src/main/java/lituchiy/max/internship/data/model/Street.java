
package lituchiy.max.internship.data.model;

public class Street {

    private Integer id;
    private String name;
    private String ruName;
    private StreetType streetType;

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
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     The ruName
     */
    public String getRuName() {
        return ruName;
    }

    /**
     * 
     * @param ruName
     *     The ru_name
     */
    public void setRuName(String ruName) {
        this.ruName = ruName;
    }

    /**
     * 
     * @return
     *     The streetType
     */
    public StreetType getStreetType() {
        return streetType;
    }

    /**
     * 
     * @param streetType
     *     The street_type
     */
    public void setStreetType(StreetType streetType) {
        this.streetType = streetType;
    }

}
