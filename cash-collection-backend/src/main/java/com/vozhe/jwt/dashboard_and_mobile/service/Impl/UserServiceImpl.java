package com.vozhe.jwt.dashboard_and_mobile.service.Impl;

import com.vozhe.jwt.dashboard_and_mobile.models.Agent;
import com.vozhe.jwt.dashboard_and_mobile.payload.request.UserRequest;
import com.vozhe.jwt.dashboard_and_mobile.repository.AgentRepository;
import com.vozhe.jwt.dashboard_and_mobile.service.UserService;
import com.vozhe.jwt.exceptions.UserExistsException;
import com.vozhe.jwt.dashboard_and_mobile.models.User;
import com.vozhe.jwt.dashboard_and_mobile.models.UserDetail;
import com.vozhe.jwt.dashboard_and_mobile.payload.BaseResult;
import com.vozhe.jwt.dashboard_and_mobile.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsService, UserService {

    private final UserRepository userRepository;
    private final AgentRepository agentRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findUserByUsername(username);
        user.orElseThrow(() -> new UsernameNotFoundException("User not found" + username));
        return user.map(UserDetail::new).get();
    }

    //    Create User
    @Override
    public ResponseEntity<BaseResult> createUser(User user) {
        System.out.println(user);
        String name = user.getUsername();
        Optional<User> userCheck = userRepository.findUserByUsername(name);
        System.out.println(userCheck);
        if(userCheck.isPresent()){
            throw new UserExistsException("User Already Exists " + name);
        }

        User toSaveUser = new User();
        toSaveUser.setFirstName(user.getFirstName());
        toSaveUser.setLastName(user.getLastName());
        toSaveUser.setUsername(user.getUsername());
        toSaveUser.setSbu(user.getSbu());
        toSaveUser.setPassword(encryptPassword(user.getPassword()));
        toSaveUser.setRoles(user.getRoles());
        toSaveUser.setPhoneNumber(user.getPhoneNumber());
        toSaveUser.setIdNumber(user.getIdNumber());
        userRepository.save(toSaveUser);

        return ResponseEntity.ok(new BaseResult("00", "User created Successfully", 200 ));

    }

    @Override
    public ResponseEntity<BaseResult> createAgent(User user) {
        String name = user.getUsername();
        Optional<User> userCheck = userRepository.findUserByUsername(name);
        System.out.println(userCheck);
        if(userCheck.isPresent()){
            throw new UserExistsException("Agent Already Exists " + name);
        }

        User toSaveUser = new User();
        toSaveUser.setFirstName(user.getFirstName());
        toSaveUser.setLastName(user.getLastName());
        toSaveUser.setUsername(user.getUsername());
        toSaveUser.setPhoneNumber(user.getPhoneNumber());
        toSaveUser.setSbu(user.getSbu());
        toSaveUser.setPassword(encryptPassword(user.getPassword()));
        toSaveUser.setRoles("Agent");
        toSaveUser.setAgentNumber(user.getAgentNumber());
        userRepository.save(toSaveUser);

        return ResponseEntity.ok(new BaseResult("00", "Agent created Successfully", 200 ));

    }

    @Override
    public ResponseEntity<BaseResult> updateUser(UserRequest user) {
        String name = user.getUsername();
        Optional<User> saveUser = userRepository.findUserByUsername(name);

        User toSaveUser = saveUser.get();
        toSaveUser.setAddress(user.getAddress());
        toSaveUser.setIdNumber(user.getIdNumber());
        toSaveUser.setDOB(user.getDOB());
        toSaveUser.setGender(user.getGender());
        userRepository.save(toSaveUser);
        return ResponseEntity.ok(new BaseResult("00", "User updated Successfully", 200 ));

    }

    @Override
    public ResponseEntity<BaseResult> getAllAgents() {
        System.out.println("hsdhshdshd "+userRepository.findAll());
        return ResponseEntity.ok(new BaseResult(userRepository.findAll(),"00", "User fetched Successfully", 200 ));
    }

    @Override
    public ResponseEntity<BaseResult> getAllAgentOfficers() {
        return ResponseEntity.ok(new BaseResult(userRepository.findAllByRoles("User"),"00", "User fetched Successfully", 200 ));
    }

    @Override
    public ResponseEntity<BaseResult> updatePass(UserRequest user) {
        String name = user.getUsername();
        Optional<User> userCheck = userRepository.findUserByUsername(name);
        System.out.println(userCheck);
        if(!userCheck.isPresent()){
            throw new UserExistsException("User Does Not Exists " + name);
        }
        userCheck.get().setPassword(encryptPassword(user.getPassword()));
        userRepository.save(userCheck.get());
        return ResponseEntity.ok(new BaseResult("00", "User updated Successfully", 200 ));

    }

    @Override
    public ResponseEntity<BaseResult> updateDetails(UserRequest user) {
        String name = user.getUsername();
        Optional<User> saveUser = userRepository.findUserByUsername(name);
        Agent savedAgent = agentRepository.findByAgentNumber(name);


        User toSaveUser = saveUser.get();
        toSaveUser.setFirstName(user.getFirstName());
        toSaveUser.setLastName(user.getLastName());
        toSaveUser.setPhoneNumber(user.getPhoneNumber());
        toSaveUser.setIdNumber(user.getIdNumber());
//        toSaveUser.setDOB(user.getDOB());
        toSaveUser.setAddress(user.getAddress());


        savedAgent.setPhoneNumber(user.getPhoneNumber());
        savedAgent.setFirstName(user.getFirstName());
        savedAgent.setLastName(user.getLastName());
        savedAgent.setNationalId(user.getIdNumber());
//        savedAgent.setDob(user.getDOB().toString());
        savedAgent.setAddress(user.getAddress());

        userRepository.save(toSaveUser);
        agentRepository.save(savedAgent);
        return ResponseEntity.ok(new BaseResult("00", "User updated Successfully", 200 ));

    }

    @Override
    public ResponseEntity<BaseResult> getUser(String user) {
        return ResponseEntity.ok(new BaseResult(userRepository.findByUsername(user),"00", "User fetched Successfully", 200 ));
    }

    public String encryptPassword(String password){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }

    @Override
    public String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || authentication.getPrincipal() == null) {
            return null;
        }
        if (authentication.getPrincipal() instanceof String) {
            String principal = (String) authentication.getPrincipal();

            if (principal.compareTo("anonymousUser") != 0) {
                return null;
            }
            return principal;
        }
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return userDetails.getUsername();
    }

    @Override
    public User getCurrentUser() {
        String username = getCurrentUsername();
        if (username == null || username.isEmpty()) {
            throw new UserExistsException("Username cannot be empty");
        }
        Optional<User> userOptional = userRepository.findUserByUsername(username);
        if (!userOptional.isPresent()) {
            throw new UserExistsException("User not found");
        }
        return userOptional.get();
    }

    @Override
    public User getDriverByUsername(String userName) {
        return userRepository.findByUsername(userName);
    }
}

