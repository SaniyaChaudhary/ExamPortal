package com.exam.service.Implementation;

import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.repo.RoleRepo;
import com.exam.repo.UserRepo;
import com.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
@Service
public class UserServiceImpl  implements UserService {
@Autowired
private UserRepo userRepo;
@Autowired
private RoleRepo roleRepo;

    //creating user
    @Override
    public User createUser(User user, Set<UserRole> userRoles) throws Exception {
      User local=  this.userRepo.findByUsername(user.getUsername());

      //check if user is already saved in the database
      if(local!=null){
          System.out.println("User already exists!!!");
          throw new Exception("User is already present!!!");
      }else

          //store the user in the database
      {
         for(UserRole ur: userRoles){
             roleRepo.save(ur.getRole());
         }
         user.getUserRoles().addAll(userRoles);
         local = this.userRepo.save(user);
      }
        return local;
    }


    //getting user by username
    @Override
    public User getUser(String username) {
        return this.userRepo.findByUsername(username);
    }

    @Override
    public void deleteUser(Long userId) {
        this.userRepo.deleteById(userId);
    }


}
