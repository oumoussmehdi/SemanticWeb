package data;

/**
 * Created by Soumaya Haj Youssef on 09/01/2017.
 */
public class Person {
    private String URI;
    private String age;
    private String given_name;
    private String surname;
    private String date_of_birth;
    private String social_security_number;
    private String phone_number;
    private String street;
    private String house_number;
    private String postcode;
    private String suburb_name;
    private String state;


    public void setURI(String URI) {
        this.URI = URI;
    }

    public String getURI() {
        return URI;
    }

    public String getAge() {
        return age;
    }

    public String getGiven_name() {
        return given_name;
    }

    public String getSurname() {
        return surname;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public String getSocial_security_number() {
        return social_security_number;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getStreet() {
        return street;
    }

    public String getHouse_number() {
        return house_number;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getSuburb_name() {
        return suburb_name;
    }

    public String getState() {
        return state;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setGiven_name(String given_name) {
        this.given_name = given_name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public void setSocial_security_number(String social_security_number) {
        this.social_security_number = social_security_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setHouse_number(String house_number) {
        this.house_number = house_number;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public void setSuburb_name(String suburb_name) {
        this.suburb_name = suburb_name;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String toString(){
        return (given_name +"\t\t"+ surname +"\t\t"+date_of_birth +"\t\t"+age +"\t\t"+ phone_number+"\t\t"+ social_security_number+
                "\t\t"+street +"\t\t"+ house_number+"\t\t"+ postcode+"\t\t"+suburb_name +"\t\t"+state );
    }
}
