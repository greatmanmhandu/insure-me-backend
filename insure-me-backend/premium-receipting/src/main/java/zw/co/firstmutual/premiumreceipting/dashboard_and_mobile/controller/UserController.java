package zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.controller;

import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.models.User;
import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.payload.BaseResult;
import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.payload.request.JwtTokenRequest;
import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.payload.response.JwtTokenResponse;
import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.payload.response.UserDTO;
import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.service.AccountCreationService;
import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/whitelist/api/")

@CrossOrigin("*")
public class UserController {
    private final UserService userService;

    private final AccountCreationService accountCreationService;

    @PostMapping("authenticate")
    public ResponseEntity<JwtTokenResponse> createAuthenticationToken(@RequestBody JwtTokenRequest authenticationRequest) throws Exception {
        System.out.println("authenticationRequest   ..  "+authenticationRequest);
        return accountCreationService.createToken(authenticationRequest);
    }

    @PostMapping("authenticate-mobile-app")
    public ResponseEntity<JwtTokenResponse> createAuthenticationTokenMobileApp(@RequestBody JwtTokenRequest authenticationRequest) throws Exception {
        System.out.println("authenticationRequest   ..  "+authenticationRequest);
        return accountCreationService.createTokenMobileApp(authenticationRequest);
    }

    @PostMapping("update-password-old")
    public ResponseEntity<JwtTokenResponse> updatePassword(@RequestBody JwtTokenRequest authenticationRequest) throws Exception {
        System.out.println("authenticationRequest   ..  "+authenticationRequest);
        return null;
    }

    @PostMapping("create-user3")
    public ResponseEntity<BaseResult> createUser(@RequestBody JwtTokenRequest user){
        System.out.println(user);
        return null;
    }

    @GetMapping("user/info")
    public ResponseEntity<UserDTO> getUserInfo(Principal user){
        return  accountCreationService.getUserInfo(user);
    }


    @GetMapping("getUserByUsername")
    public User getUserByUsername(@RequestParam("username") String username){
        return  accountCreationService.getUserByUsername(username);
    }



}

