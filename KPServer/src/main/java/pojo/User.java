package pojo;

import enums.UserRole;

import java.io.Serializable;

public class User implements Serializable {
    private Integer id;
    private String userName;
    private String password;
    private String email;
    private String userSalt;
    private Float wallet;
    private Boolean isBanned;
    private UserRole userRole;
    private int userCurrencyID;

    public User() {
        userRole = UserRole.user;
        wallet = 0.0f;
        isBanned = false;
    }

    public User(String userName, String password, String email) {
        this();
        this.userName = userName;
        this.password = password;
        this.email = email;
    }

    public User(String userName, String password) {
        this();
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Float getWallet() {
        return wallet;
    }

    public void setWallet(Float wallet) {
        this.wallet = wallet;
    }

    public Boolean getBanned() {
        return isBanned;
    }

    public void setBanned(Boolean banned) {
        isBanned = banned;
    }

    public String getUserSalt() {
        return userSalt;
    }

    public void setUserSalt(String userSalt) {
        this.userSalt = userSalt;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public int getUserCurrencyID() {
        return userCurrencyID;
    }

    public void setUserCurrencyID(int userCurrencyID) {
        this.userCurrencyID = userCurrencyID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
