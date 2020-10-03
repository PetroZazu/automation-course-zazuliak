package io.ctdev.framework.model;

public class JuiceShopUserBuilder {
    private String email;
    private String password;

    public JuiceShopUserBuilder email(String email) {
        this.email = email;
        return this;
    }

    public JuiceShopUserBuilder password(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public JuiceShopUser build () {
        return new JuiceShopUser(this);
    }
}
