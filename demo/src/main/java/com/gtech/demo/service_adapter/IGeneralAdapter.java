package com.gtech.demo.service_adapter;

import com.gtech.demo.entity.User;
import java.util.*;

public interface IGeneralAdapter {
    String login(String username, String password);
    User register(User user);
    String logout(String token);
    String forgotPassword(String email);
    String resetPassword(String resetpassToken,String password);
    List<User> getProfile(String token);
}
