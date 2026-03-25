package zw.co.lifewebcore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;
import zw.co.hcpwebcommons.config.FeignClientSecurityConfig;
import zw.co.hcpwebcommons.config.LocalRibbonClientConfiguration;
import zw.co.hcpwebcommons.security.JwtAuthenticationEntryPoint;

@SpringBootApplication(scanBasePackages = "zw.co")
@EnableDiscoveryClient
@EnableFeignClients("zw.co")
@Import({FeignClientSecurityConfig.class})
@RibbonClient(name = "HCP-WEB-CORE", configuration = LocalRibbonClientConfiguration.class)
@EnableAsync
public class LifeWebFormApplication {

    public static void main(String[] args) {
        SpringApplication.run(LifeWebFormApplication.class, args);
    }

    @Bean
    public JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint() {
        return new JwtAuthenticationEntryPoint();
    }
}
