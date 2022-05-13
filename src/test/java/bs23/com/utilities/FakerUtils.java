package bs23.com.utilities;

import com.github.javafaker.Faker;

public class FakerUtils {
    private String userName;
    private String email;
    private String password;

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }



    public FakerUtils(){
        Faker faker = new Faker();
        this.userName = faker.name().username();
        this.email = faker.internet().emailAddress(userName);
        this.password = faker.internet().password();
    }
}
