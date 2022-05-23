package local.prueba.api.service;


import local.prueba.api.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public List<User> findall();

    public void deleteUser(Long id);

    public User saveUser(User user);

    public Optional<User> findById(Long id);
}
