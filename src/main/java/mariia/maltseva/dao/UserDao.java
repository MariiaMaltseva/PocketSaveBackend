package mariia.maltseva.dao;

import mariia.maltseva.entities.User;

import java.util.List;

public interface UserDao {
    void save(User newUser);

    void update(User user);

    User getUserByEmail(String email);
}
