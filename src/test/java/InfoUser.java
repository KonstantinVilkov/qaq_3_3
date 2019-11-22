
public class InfoUser {
        private String city;
        private String fullName;
        private String phone;


    public InfoUser(String administrativeCenter, String fullName, String phoneNumber) {
        this.city = administrativeCenter;
        this.fullName = fullName;
        this.phone = phoneNumber;
    }

    public String getCity() {
        return city;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPhone() {
        return phone;
    }
}
