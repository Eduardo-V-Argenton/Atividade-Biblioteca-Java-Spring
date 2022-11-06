package br.com.arqsoft.Library.dto;

import br.com.arqsoft.Library.models.UserModel;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link br.com.arqsoft.Library.models.UserModel} entity
 */
public class UserModelDto implements Serializable {
    private final String username;
    private final String password;

    public UserModelDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserModelDto entity = (UserModelDto) o;
        return Objects.equals(this.username, entity.username) &&
                Objects.equals(this.password, entity.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "username = " + username + ", " +
                "password = " + password + ")";
    }

    public UserModel toUser(UserModel user) {
        user.setUsername(this.username);
        user.setPassword(new BCryptPasswordEncoder().encode(this.password));
        return user;
    }
}