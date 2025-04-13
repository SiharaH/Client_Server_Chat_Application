package Models;


import java.io.File;

public class User {
    protected String email;
    protected String username;
    protected String password;
    protected String nickname;
    private boolean isAdmin;

    public User(String email, String username, String password, String nickname, boolean isAdmin) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.isAdmin = isAdmin;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

}
