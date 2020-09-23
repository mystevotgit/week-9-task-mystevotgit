package com.project.projectmanager.servicesimpl;

import com.project.projectmanager.models.User;
import com.project.projectmanager.repository.UserRepository;
import com.project.projectmanager.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public void SaveUserData(HashMap<String, String> formData, User user) {
        user.setFirstname(formData.get("firstname"));
        user.setLastname(formData.get("lastname"));
        user.setEmail(formData.get("email").toLowerCase());
        user.setGender(formData.get("gender"));
        user.setPassword(formData.get("password"));
        user.setOrganization(formData.get("organization").toLowerCase());
        user.setRole(formData.get("role").toLowerCase());
        userRepository.save(user);
    }

    @Override
    public void FindUserData(HashMap<String, String> formData, HttpSession session) {
        List<User> userData = userRepository.findByEmail(formData.get("email").toLowerCase());
        if (userData.size() != 0){
            if (userData.get(0).getPassword().equals(formData.get("password"))) {
                session.setAttribute("user", userData.get(0));
            }
        }
    }

    @Override
    public void FindUsers(HttpSession session) {
        List<User> users = userRepository.findAll();
        session.setAttribute("employees", users);
    }
}
