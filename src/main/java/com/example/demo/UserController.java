package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
@CrossOrigin
public class UserController {

    @Autowired
    private UserDao userDao;

    @GetMapping("/users")
    private List<User> index() {
        return userDao.findAll();
    }

    @PostMapping("/add")
    private List<User> addUser(@RequestBody User user) {
        user.setTimestamp(LocalDateTime.now());
        userDao.save(user);
        return userDao.findAll();
    }

    @PostMapping("/update")
    private List<User> updateUser(@RequestBody User user) {
        Integer id = user.getId();
        if (id != null) {
            User temp = userDao.getById(user.getId());
            userDao.save(temp);
        }

        return userDao.findAll();
    }
    
    @GetMapping("/search")
    private List<User> serach(@Nullable @RequestParam("id") Integer id,
                              @RequestParam("name") String name,
                              @RequestParam("email") String email) {
        List<User> res = new ArrayList<>();
        if (id != null) {
            Optional<User> target = userDao.findById(id);
            target.ifPresent(res::add);
        } else {
            res = userDao.findAll();
            List<User> list = new ArrayList<>();
            for (User p : res) {
                if ( p.getName().contains(name)
                        && p.getEmail().contains(email)) {
                    list.add(p);
                }
            }
            res = list;
        }
        return res;
    }

    @DeleteMapping("/remove")
    private List<User> remove(@RequestParam("id") Integer id) {
        if (userDao.existsById(id)) {
            userDao.deleteById(id);
        }
        return userDao.findAll();
    }
}
