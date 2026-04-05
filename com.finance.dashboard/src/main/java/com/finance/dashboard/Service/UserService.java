package com.finance.dashboard.Service;

import com.finance.dashboard.Dao.UserDao;
import com.finance.dashboard.Dto.ResponseStructure;
import com.finance.dashboard.Dto.UserRequest;
import com.finance.dashboard.Entity.User;
import com.finance.dashboard.Exception.IdNotFoundException;
import com.finance.dashboard.Exception.NoRecordFoundException;
import com.finance.dashboard.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseEntity<ResponseStructure<User>> createUser(UserRequest userRequest) {

        User user = new User(
                userRequest.getName(),userRequest.getEmail(),userRequest.getRole(),passwordEncoder.encode(userRequest.getPassword())
        );
        user.setActive(true);


        ResponseStructure<User> response = new ResponseStructure<>();
        response.setStatusCode(HttpStatus.CREATED.value());
        response.setMessage("User Created Successfully");
        response.setData(userDao.createUser(user));

        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    public ResponseEntity<ResponseStructure<List<User>>> fetchUser() {
        List<User> user = userDao.fetchUser();
        if(user.isEmpty())
            throw new NoRecordFoundException("No User");

        ResponseStructure<List<User>> response = new ResponseStructure<>();
        response.setData(user);
        response.setMessage("Data Fetched");
        response.setStatusCode(HttpStatus.OK.value());

        return new ResponseEntity<>(response,HttpStatus.OK);

    }
    public ResponseEntity<ResponseStructure<User>> fetchUserById(Long id) {
        Optional<User> opt = userDao.getById(id);

        if(opt.isEmpty())
            throw  new IdNotFoundException("Id does not exits in DB");

        ResponseStructure<User> response = new ResponseStructure<>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("User Details Fetched based on Id");
        response.setData(opt.get());

        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    public ResponseEntity<ResponseStructure<User>> updateUser(Long id,UserRequest userRequest) {

        User user = userDao.getById(id).orElseThrow(()-> new IdNotFoundException("Id not Found"));

        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setRole(userRequest.getRole());

        ResponseStructure<User> response = new ResponseStructure<>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("Updated");
        response.setData(userDao.updateUser(user));

        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    public ResponseEntity<ResponseStructure<String>> deleteUser(Long id) {
        User user = userDao.getById(id).orElseThrow(
                ()-> new IdNotFoundException("Id not Found")
        );

        ResponseStructure<String> response = new ResponseStructure<>();
        userDao.deleteUser(id);
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("User Deleted");
        response.setData("Success");
        return new ResponseEntity<>(response,HttpStatus.OK);

    }
}
