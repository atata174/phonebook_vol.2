package ru.razhapov.dinoApp.phonebookDino;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Component
public class UserController {

    private UserMng userMng;

    @Autowired
    public UserController(UserMng userMng) {
        this.userMng = userMng;
    }

    @GetMapping("/{id}")
    public User getId(@PathVariable int id){
        return userMng.userGetId(id);
    }

}
