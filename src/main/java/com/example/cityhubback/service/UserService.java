package com.example.cityhubback.service;

import com.example.cityhubback.model.User;
import com.example.cityhubback.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getById(String id, String currentUserId, boolean isAdmin) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Użytkownik nie istnieje"));
        if (!user.getId().equals(currentUserId) && !isAdmin) {
            throw new RuntimeException("Brak uprawnień");
        }
        return user;
    }

    public User update(String id, User updated, String currentUserId, boolean isAdmin) {
        if (!id.equals(currentUserId) && !isAdmin) {
            throw new RuntimeException("Brak uprawnień");
        }
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Użytkownik nie istnieje"));
        user.setFirstName(updated.getFirstName());
        user.setLastName(updated.getLastName());
        user.setPhone(updated.getPhone());
        return userRepository.save(user);
    }

    public void delete(String id, boolean isAdmin) {
        if (!isAdmin) throw new RuntimeException("Brak uprawnień");
        userRepository.deleteById(id);
    }
}