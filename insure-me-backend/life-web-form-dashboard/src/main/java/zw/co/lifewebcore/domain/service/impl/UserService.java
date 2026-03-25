package zw.co.lifewebcore.domain.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zw.co.hcpwebcommons.domain.value.Email;
import zw.co.lifewebcore.domain.model.User;
import zw.co.lifewebcore.feign.UserFeignClient;

@Service
@RequiredArgsConstructor
public class UserService {


    private final UserFeignClient userFeignClient;

    public User findByEmail(Email email) {
        return userFeignClient.findByEmail(email);
    }
}
