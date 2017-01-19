package data;

/**
 * Created by Soumaya Haj Youssef on 10/01/2017.
 */
public class Restaurant {
    private String URI;
    private String name;
    private String phone_number;
    private String category;
    private String street;
    private String city_name;

    public void setURI(String URI) {
        this.URI = URI;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getName() {
        return name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getCategory() {
        return category;
    }

    public String getStreet() {
        return street;
    }

    public String getCity_name() {
        return city_name;
    }

    public String getURI() {
        return URI;
    }

    public String toString(){
        return (name +"\t\t"+ phone_number +"\t\t"+category +"\t\t"+street +"\t\t"+ city_name);
    }
}
