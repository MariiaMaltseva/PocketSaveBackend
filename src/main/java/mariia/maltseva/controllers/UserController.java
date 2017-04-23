package mariia.maltseva.controllers;

import mariia.maltseva.dto.Credentials;
import mariia.maltseva.dto.DTOUser;
import mariia.maltseva.entities.User;
import mariia.maltseva.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class UserController {
    @Autowired
    private UserService service;

    @PostMapping("/register")
    public User save(@RequestBody DTOUser user){
        return service.register(user);
    }

    @PostMapping("/login")
    public User login(@RequestBody Credentials credentials){
        return service.login(credentials);
    }

    @PostMapping("/update")
    public User update(@RequestBody DTOUser user){
        return service.updateBalance(user);
    }

    @PostMapping("/history/save")
    public User saveBalanceHistory(@RequestBody DTOUser user){
        return service.saveBalanceHistory(user);
    }

    @PostMapping("/category/add")
    public User addNewCategory(@RequestBody DTOUser user){
        return service.addNewCategory(user);
    }


    @PostMapping("/category/delete")
    public User deleteCategory(@RequestBody DTOUser user){
        return service.deleteCategory(user);
    }
}
