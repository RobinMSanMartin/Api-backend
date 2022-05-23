package local.prueba.api.controller;

import local.prueba.api.entities.User;
import local.prueba.api.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity create(@RequestBody User user){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(user));
    }

    @GetMapping
    public List<User> readAll(){
        List<User> users = StreamSupport
                .stream(userService.findall().spliterator(), false)
                .collect(Collectors.toList());
        return users;
    }

    @GetMapping("/{id}")
    public ResponseEntity read(@PathVariable Long id){
        Optional<User> oUser = userService.findById(id);
        if (!oUser.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(oUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable(value = "id") Long id, @RequestBody User user){
        Optional<User> oUser = userService.findById(id);
        if (!oUser.isPresent()){
            return ResponseEntity.notFound().build();
        }
        //BeanUtils.copyProperties(user, oUser.get());
        oUser.get().setName(user.getName());
        oUser.get().setDni(user.getDni());
        oUser.get().setEmail(user.getEmail());
        oUser.get().setPhone(user.getPhone());
        oUser.get().setBirthDate(user.getBirthDate());
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(oUser.get()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        if (!userService.findById(id).isPresent()){
            return ResponseEntity.notFound().build();
        }
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}
