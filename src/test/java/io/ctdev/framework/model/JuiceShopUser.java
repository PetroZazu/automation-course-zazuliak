package io.ctdev.framework.model;

public class JuiceShopUser {
    private String email;
    private String password;


    public JuiceShopUser() {

    }

    JuiceShopUser(JuiceShopUserBuilder juiceShopUserBuilder) {
        this.email = juiceShopUserBuilder.getEmail();
        this.password = juiceShopUserBuilder.getPassword();
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
