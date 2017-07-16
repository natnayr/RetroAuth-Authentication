package retroauth.test.cwdsg05.retroauthauthentication.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by cwdsg05 on 21/3/17.
 */
public class Member {

    @SerializedName("id")
    @Expose
    Integer id;

    @SerializedName("email")
    @Expose
    String email;

    @SerializedName("name")
    @Expose
    String name;

    @SerializedName("phone_number")
    @Expose
    String phoneNumber;

    @SerializedName("nationality")
    @Expose
    String nationality;

    @SerializedName("date_of_birth")
    @Expose
    String dateOfBirth;

    @SerializedName("address1")
    @Expose
    String address1;

    @SerializedName("address2")
    @Expose
    String address2;

    @SerializedName("city")
    @Expose
    String city;

    @SerializedName("postal_code")
    @Expose
    String postalCode;

    @SerializedName("country_of_residence")
    @Expose
    String countryOfResidence;

    @SerializedName("locale_preference")
    @Expose
    String localePreference;

    @SerializedName("session_country")
    @Expose
    String sessionCountry;

    @SerializedName("is_banned")
    @Expose
    Boolean isBanned;

    public Member(){ };

    public Member(Integer id, String email, String name, String phoneNumber,
                  String nationality, String dateOfBirth, String address1, String address2,
                  String city, String postalCode, String countryOfResidence,
                  String localePreference, String sessionCountry, Boolean isBanned) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.nationality = nationality;
        this.dateOfBirth = dateOfBirth;
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.postalCode = postalCode;
        this.countryOfResidence = countryOfResidence;
        this.localePreference = localePreference;
        this.sessionCountry = sessionCountry;
        this.isBanned = isBanned;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountryOfResidence() {
        return countryOfResidence;
    }

    public void setCountryOfResidence(String countryOfResidence) {
        this.countryOfResidence = countryOfResidence;
    }

    public String getLocalePreference() {
        return localePreference;
    }

    public void setLocalePreference(String localePreference) {
        this.localePreference = localePreference;
    }

    public String getSessionCountry() {
        return sessionCountry;
    }

    public void setSessionCountry(String sessionCountry) {
        this.sessionCountry = sessionCountry;
    }

    public Boolean getIsBanned() {
        return isBanned;
    }

    public void setIsBanned(Boolean isBanned) {
        this.isBanned = isBanned;
    }
}
