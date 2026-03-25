package zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.service.Impl;


import zw.co.firstmutual.premiumreceipting.config.JwtTokenUtil;
import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.models.ShiftLogin;
import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.models.User;
import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.payload.request.JwtTokenRequest;
import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.payload.response.JwtTokenResponse;
import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.payload.response.UserDTO;
import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.repository.UserRepository;
import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.service.AccountCreationService;
import zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.service.ShiftLogingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountCreationImpl implements AccountCreationService {

    private final AuthenticationManager authenticationManager;

    private final UserDetailsService userDetailsService;

    private final JwtTokenUtil jwtTokenUtil;

    private final UserRepository userRepository;

    private final ShiftLogingService shiftLogingService;

    @Override
    public ResponseEntity<JwtTokenResponse> createToken(JwtTokenRequest authenticationRequest) throws Exception{
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        System.out.println(userDetails);
        final String token = jwtTokenUtil.generateToken(userDetails);

        Optional<User> userResponse = userRepository.findUserByUsername(authenticationRequest.getUsername());
        if(!userResponse.isPresent()){
            userResponse.orElseThrow(() -> new UsernameNotFoundException("User not found" + authenticationRequest.getUsername()));
        }

        UserDTO user = new UserDTO();
        user.setUsername(userResponse.get().getUsername().toUpperCase());
        user.setFullNames(userResponse.get().getFirstName() + " "+ userResponse.get().getLastName());
//        user.setAuthorities(userResponse.get().getRoles());
        user.setAuthorities(userResponse.get().getRoles());
        user.setSbu(userResponse.get().getSbu());

        return ResponseEntity.ok(new JwtTokenResponse(token, user, "00"));
    }

    @Override
    public ResponseEntity<JwtTokenResponse> createTokenMobileApp(JwtTokenRequest authenticationRequest) throws Exception{
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        System.out.println(userDetails);
        final String token = jwtTokenUtil.generateToken(userDetails);
        System.out.println("here ..........");
        Optional<User> userResponse = userRepository.findUserByUsername(authenticationRequest.getUsername());
        if(!userResponse.isPresent()){
            System.out.println("here 2..........");
            UserDTO userDTO = new UserDTO();
            userDTO.setMessage("User not found");
//            userResponse.orElseThrow(() -> new UsernameNotFoundException("User not found" + authenticationRequest.getUsername()));
            return ResponseEntity.ok(new JwtTokenResponse(token, userDTO, "00"));
        }

        UserDTO user = new UserDTO();
        user.setUsername(userResponse.get().getUsername());
        user.setFullNames(userResponse.get().getFirstName() + " "+ userResponse.get().getLastName());
//        user.setAuthorities(userResponse.get().getRoles());
        user.setAuthorities(userResponse.get().getRoles());
        user.setFirstName(userResponse.get().getFirstName());
        user.setLastName(userResponse.get().getLastName());
        user.setSbu(userResponse.get().getSbu());
        // check if shift is open
        ShiftLogin shiftLogin = shiftLogingService.getPendingShiftByAgentNumberSecond(authenticationRequest.getUsername());

        return ResponseEntity.ok(new JwtTokenResponse(token, user, "00",shiftLogin));
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    //    public ResponseEntity<JwtTokenResponse> updatePasswordForMobileApp(JwtTokenRequest authenticationRequest) throws Exception {
//        String username = authenticationRequest.getUsername();
//        String newPassword = authenticationRequest.getPassword();
//
//        // Update the password in the database
//        userRepository.updatePasswordByUsername(username, newPassword);
//
//        // Continue with authentication
//        authenticate(username, newPassword);
//
//        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//
//        System.out.println(userDetails);
//        final String token = jwtTokenUtil.generateToken(userDetails);
//        System.out.println("here ..........");
//
//        User user = (User) userDetails;
//        UserDTO userDTO = new UserDTO();
//        userDTO.setUsername(user.getUsername());
//        userDTO.setFullNames(user.getFirstName() + " " + user.getLastName());
//        // Set other user properties as needed
//
//        // check if shift is open
//        ShiftLogin shiftLogin = shiftLogingService.getPendingShiftByAgentNumberSecond(username);
//
//        return ResponseEntity.ok(new JwtTokenResponse(token, userDTO, "00", shiftLogin));
//    }
    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    @Override
    public ResponseEntity<UserDTO> getUserInfo(Principal user) {
        Optional<User> userObject = userRepository.findUserByUsername(user.getName());
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(userObject.get().getFirstName() + " " +  userObject.get().getFirstName());
//        userDTO.setAuthorities(userObject.get().getRoles());
        userDTO.setAuthorities("User");
        return ResponseEntity.ok(userDTO);
    }
}

