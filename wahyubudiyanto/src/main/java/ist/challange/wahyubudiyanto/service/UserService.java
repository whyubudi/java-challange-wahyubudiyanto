package ist.challange.wahyubudiyanto.service;

import ist.challange.wahyubudiyanto.model.User;
import ist.challange.wahyubudiyanto.repository.UserRepository;
import ist.challange.wahyubudiyanto.web.response.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public ResponseEntity<Object> list() {
        try {
            List<User> userList = userRepository.findAll();
            return ResponseHandler.response(userList);
        } catch (Exception e) {
            throw e;
        }
    }

    public ResponseEntity<Object> save(User user) {
        try {
            Optional<User> userOptional = userRepository.findByUsername(user.getUsername());
            if (userOptional.isPresent()) {
                return ResponseHandler.response(
                        "username sudah ada!",
                        HttpStatus.CONFLICT,
                        user);
            } else {
                userRepository.saveAndFlush(user);
                return ResponseHandler.response(
                        "Sukses!",
                        HttpStatus.CREATED,
                        user);
            }
        } catch (Exception e) {
            return ResponseHandler.response(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    user);
        }
    }

    public ResponseEntity<Object> login(User user) {
        try {
            Optional<User> userOptional = userRepository.findByUsername(user.getUsername());
            String password = userRepository.getPasswordById(user.getId());
            String username = userRepository.getUsernameById(user.getId());

            if (username.isEmpty() && password.isEmpty()) {
                return ResponseHandler.response(
                        "username atau password kosong!",
                        HttpStatus.BAD_REQUEST,
                        null);
            } else if (userOptional.isPresent() && user.getPassword().equals(password)) {
                return ResponseHandler.response(
                        "login berhasil!",
                        HttpStatus.OK,
                        user);
            } else if (userOptional.isPresent() && !user.getPassword().equals(password) || !user.getUsername().equals(username)) {
                return ResponseHandler.response(
                        "username atau password salah!",
                        HttpStatus.UNAUTHORIZED,
                        null);
            } else {
                return ResponseHandler.response(
                        "request gagal!",
                        HttpStatus.BAD_REQUEST,
                        null);
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public ResponseEntity<Object> update(User user) {

        try {
            Optional<User> userOptionalu1 = userRepository.findByUsername(user.getUsername());
            String exitingPassword = userRepository.getPasswordById(user.getId());

            if (userOptionalu1.isPresent()) {
                return ResponseHandler.response(
                        "username sudah terpakai!",
                        HttpStatus.CONFLICT,
                        user);
            } else if (exitingPassword.equals(user.getPassword())) {
                return ResponseHandler.response(
                        "password tidak boleh sama dengan sebelumnya!",
                        HttpStatus.BAD_REQUEST,
                        user);
            } else {
                userRepository.save(user);
                return ResponseHandler.response(
                        "sukses!",
                        HttpStatus.CREATED,
                        user);
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public Object deleteUserById(Long id) {
        userRepository.deleteById(id);
        return ResponseHandler.response(
                "id: " + id + " delete berhasil!",
                HttpStatus.OK,
                null);
    }

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
