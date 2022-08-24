package ist.challange.wahyubudiyanto.controller;

import ist.challange.wahyubudiyanto.model.User;
import ist.challange.wahyubudiyanto.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    UserService userServices;

    @PostMapping("/registration")
    public ResponseEntity<Object> userRegistration(@RequestBody User user) {
        return userServices.save(user);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> userLogin(@RequestBody User user) {
        return userServices.login(user);
    }

    @GetMapping("/list")
    public ResponseEntity<Object> listUser() {
        return userServices.list();
    }

    @PutMapping("/update")
    public ResponseEntity<Object> updateUser(@RequestBody User user) {
        return userServices.update(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        userServices.deleteUserById(id);
    }
}
