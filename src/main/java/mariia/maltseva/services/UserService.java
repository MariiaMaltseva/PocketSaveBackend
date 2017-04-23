package mariia.maltseva.services;

import mariia.maltseva.dto.Credentials;
import mariia.maltseva.dto.DTOUser;
import mariia.maltseva.entities.User;

public interface UserService {
    User register(DTOUser newUser);

    User updateBalance(DTOUser user);

    User login(Credentials credentials);

    User saveBalanceHistory(DTOUser user);

    User addNewCategory(DTOUser user);

    User deleteCategory(DTOUser user);
}

