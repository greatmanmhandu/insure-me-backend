package com.vozhe.jwt.dashboard_and_mobile.service;

import com.vozhe.jwt.dashboard_and_mobile.models.User;
import com.vozhe.jwt.dashboard_and_mobile.payload.BaseResult;
import com.vozhe.jwt.dashboard_and_mobile.payload.request.UserRequest;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<BaseResult> createUser(User user);
    ResponseEntity<BaseResult> createAgent(User user);
    public String getCurrentUsername();
    public User getCurrentUser();


    User getDriverByUsername(String userName);

    ResponseEntity<BaseResult> updateUser(UserRequest user);


    ResponseEntity<BaseResult> getAllAgents();

    ResponseEntity<BaseResult> getAllAgentOfficers();

    ResponseEntity<BaseResult> updatePass(UserRequest user);

    ResponseEntity<BaseResult> updateDetails(UserRequest user);

    ResponseEntity<BaseResult> getUser(String user);
}
