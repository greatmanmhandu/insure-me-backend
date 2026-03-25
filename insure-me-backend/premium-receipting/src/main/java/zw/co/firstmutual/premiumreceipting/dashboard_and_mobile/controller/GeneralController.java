package zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.controller;

import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.models.User;
import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.payload.BaseResult;
import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.payload.request.UserRequest;
import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//Create user

@RestController
@RequestMapping("/whitelist/api/")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class GeneralController {
    private final UserService userService;


    @PostMapping("create-user")
    public ResponseEntity<BaseResult> createUser(@RequestBody User user){

        return userService.createUser(user);
    }


    @GetMapping("driver-details")
    public User getDriverByUsername(@RequestParam String userName) {
        System.out.println(userName);
        return userService.getDriverByUsername(userName);
    }
    @GetMapping("getAllUsers")
    ResponseEntity<BaseResult> getAllAgents ()
    {
        return userService.getAllAgents();
    }

    @GetMapping("getUser/{user}")
    ResponseEntity<BaseResult> getUser (@PathVariable String user)
    {
        return userService.getUser(user);
    }

    @GetMapping("getAllAgentOfficers")
    ResponseEntity<BaseResult> getAllAgentOfficers ()
    {
        return userService.getAllAgentOfficers();
    }
    @PostMapping("update-user")
    public ResponseEntity<BaseResult> updateUser(@RequestBody UserRequest user){
        return userService.updateUser(user);
    }

    @PostMapping("update-password")
    public ResponseEntity<BaseResult> updatePass(@RequestBody UserRequest user){
        return userService.updatePass(user);
    }

    @PostMapping("update-user-details")
    public ResponseEntity<BaseResult> updateDetails(@RequestBody UserRequest user){
        return userService.updateDetails(user);
    }

}

