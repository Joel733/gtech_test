package com.gtech.demo.service_adapter;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.*;

import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gtech.demo.entity.Product;
import com.gtech.demo.entity.User;
import com.gtech.demo.repository.CRUDRepository;
import com.gtech.demo.repository.UserRepository;



@Service
public class GeneralAdapter implements IGeneralAdapter{
    @Autowired
    private UserRepository userRepository;

    @Override
    public String login(String username, String password){
        String token ="";
       
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String loginDate = sdf.format(date).toString();
        try {
            String inputPassword = hashString(password);
            List<User> existingUser = userRepository.findByUsernameAndPassword(username, inputPassword);
            System.out.println(existingUser.get(0).getUsername());
            String rand = java.util.UUID.randomUUID().toString();
            if(existingUser.size()>0 && existingUser.get(0).getPassword().equals(inputPassword)){
                token = hashString(username+rand);
                existingUser.get(0).setLogintoken(token);
                existingUser.get(0).setLastLogin(loginDate);
                userRepository.save(existingUser.get(0));
                return token;
            }
            else return token;
            
        }catch(Exception e) {
            return token;
        }
    }
    public String hashString(String data){
        String hashed = "";
        if(data.equals("")){
            return hashed;
        }else{
            MessageDigest messageDigest;
            try {
                messageDigest = MessageDigest.getInstance("MD5");
                messageDigest.update(data.getBytes());
                byte[] digest = messageDigest.digest();
                StringBuffer sb = new StringBuffer();
                for(byte b : digest){
                    sb.append(Integer.toHexString((int)(b& 0xff)));
                }
                hashed = sb.toString();
                return hashed;
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                return hashed;
            }
           
        }
    }
    @Override
    public User register(User user){
        User us = new User();
        String inputPassword = hashString(user.getPassword());
        us.setUsername(user.getUsername());
        us.setPassword(inputPassword);
        us.setFirstname(user.getFirstname());
        us.setLastname(user.getLastname());
        us.setBirthdate(user.getBirthdate());
        us.setEmail(user.getEmail());
        userRepository.save(us);
        return us;
    }
    @Override
    public String logout(String token){
       List<User> user = userRepository.findByLogintoken(token);
       if(user.size()>0){
           user.get(0).setLogintoken(null);
           userRepository.save(user.get(0));
           return "success";
       }else{
           return "error";
       }
    }
    @Override
    public String forgotPassword(String email){
        List<User> user = userRepository.findByEmail(email);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String today = sdf.format(date).toString();
        String resetPassToken = "";
        if(user.size()>0){
            resetPassToken = hashString((email+today));
            user.get(0).setResetpassToken(resetPassToken);
            userRepository.save(user.get(0));
            return resetPassToken;
        }else{
            return resetPassToken;
        }
    }
    @Override
    public String resetPassword(String resetpassToken, String password){
        List<User> user = userRepository.findByResetpassToken(resetpassToken);
        String newPassword = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String today = sdf.format(date).toString();
        if(user.size()>0){
            user.get(0).setResetpassToken(null);
            newPassword = hashString(password);
            user.get(0).setPassword(newPassword);
            userRepository.save(user.get(0));
            return "success";
        }else{
            return "error";
        }
    }
    @Override
    public List<User> getProfile(String token){
        List<User> user = null;
        try {
            user = userRepository.findByLogintoken(token);
            return user;
        } catch (Exception e) {
            return new ArrayList<User>();
        }
    }
}
