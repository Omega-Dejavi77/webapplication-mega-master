package cat.tecnocampus.omega.domain;

public class User {

    private String nickname;
    private String password;
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

        public UserBuilder name(String nickname) {
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

        public User build() {
            return new User(this);
        }
    }
}
