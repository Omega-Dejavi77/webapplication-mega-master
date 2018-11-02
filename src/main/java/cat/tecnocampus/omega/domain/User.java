package cat.tecnocampus.omega.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class User {

    /*@NotNull(message = "nickname cannot be null")
    @Size(min = 4, max = 20,message = "nickname must be between 4 and 15 characters long")*/
    private String nickname;

    /*@NotNull(message = "password cannot be null")
    @Size(min = 4, max = 20,message = "password must be between 4 and 15 characters long")*/
    private String password;

    /*@NotNull(message = "email cannot be null")
    @Size(min = 4, max = 60,message = "email must be between 4 and 15 characters long")*/
    private String email;

    public User() {}

    public User(UserBuilder builder) {
        this.nickname = builder.nickname;
        this.password = builder.password;
        this.email = builder.email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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

    public static class UserBuilder {
        private String nickname;
        private String password;
        private String email;

        public UserBuilder() {}

        public UserBuilder nickName(String nickname) {
            this.nickname = nickname;
            return this;
        }

        public UserBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder email(String email) {
            this.email = email;
            return this;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
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

        public User build() {
            return new User(this);
        }
    }
}
