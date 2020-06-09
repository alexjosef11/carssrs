package user.registration.model;

public class User {

    private String username;
    private String password;
    private String passwordconfirm;
    private String firstname;
    private String secondname;
    private String phonenumber;
    private String address;
    private String role;

    public User() {
    }
    public User(String username, String password,String passwordconfirm, String firstname,String secondname,String phonenumber,String address,String role) {
        this.username = username;
        this.password = password;
        this.passwordconfirm = passwordconfirm;
        this.firstname = firstname;
        this.secondname = secondname;
        this.phonenumber = phonenumber;
        this.address = address;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordconfirm() {
        return passwordconfirm;
    }
    public void setPasswordconfirm(String passwordconfirm) {
        this.passwordconfirm = passwordconfirm;
    }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSecondname() {
        return secondname;
    }
    public void setSecondname(String secondname) { this.secondname = secondname; }

    public String getPhonenumber() {
        return phonenumber;
    }
    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!username.equals(user.username)) return false;
        if (!password.equals(user.password)) return false;
        return role.equals(user.role);
    }

    @Override
    public int hashCode() {
        int result = username.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + role.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
