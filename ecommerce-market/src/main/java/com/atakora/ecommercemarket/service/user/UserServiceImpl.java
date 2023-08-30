package com.atakora.ecommercemarket.service.user;

import com.atakora.ecommercemarket.dto.SignupDTO;
import com.atakora.ecommercemarket.dto.UserDTO;
import com.atakora.ecommercemarket.entities.User;
import com.atakora.ecommercemarket.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDTO createUser(SignupDTO signupDTO) {
        User user = new User();
        user.setName(signupDTO.getName());
        user.setEmail(signupDTO.getEmail());
        user.setUserRole(signupDTO.getUserRole());
        user.setPassword(new BCryptPasswordEncoder().encode(signupDTO.getPassword()));

        // Sauvegarder l'utilisateur dans la base de données
        User createdUser = userRepository.save(user);

        // Créer un objet UserDTO à partir des données sauvegardées
        UserDTO userDTO = new UserDTO();
        userDTO.setId(createdUser.getId());
        userDTO.setName(createdUser.getName());
        userDTO.setEmail(createdUser.getEmail());
        userDTO.setUserRole(createdUser.getUserRole());

        return userDTO;
    }

    @Override
    public boolean hasUserWithEmail(String email) {
        return userRepository.findFirstByEmail(email) != null;
    }

    @Override
    public UserDTO getUserById(Long userId) {
        User user = userRepository.findById(userId).orElse(null);

        if (user != null) {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setName(user.getName());
            userDTO.setEmail(user.getEmail());
            userDTO.setUserRole(user.getUserRole());
            return userDTO;
        } else {
            return null;
        }
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDTO> userDTOList = new ArrayList<>();

        for (User user : users) {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setName(user.getName());
            userDTO.setEmail(user.getEmail());
            userDTO.setUserRole(user.getUserRole());
            userDTOList.add(userDTO);
        }

        return userDTOList;
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
