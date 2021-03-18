package org.experience.k.springboot.config.auth;

import lombok.RequiredArgsConstructor;
import org.experience.k.springboot.domain.user.Role;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable() //h2-console 화면을 사용하기 위해 disable
                .and().
                    authorizeRequests() //URL별 권한 관리 설정 옵션 시작
                    
                    //권한 권리 대상 지정 옵션, URL, HTTP 메소드별로 관리 가능, "/"등은 permitAll로 전체 열람 권한 부여, api/v1을 가진 주소에는 USER만 가능
                    .antMatchers("/", "/css/**","/images/**","/js/**","/h2-console/**").permitAll().antMatchers("/api/v1**").hasRole(Role.USER.name())
                        .anyRequest().authenticated() //설정된 값들 이외 나머지 URL들, authenticated를 추가해 나머지 URL들은 인증된 사용자만 허용, 로그인한 사용자들만 가능.
                .and()
                    .logout()
                        .logoutSuccessUrl("/")  //로그아웃 기능, 로그아웃시 "/"로 이동
                .and()
                    .oauth2Login()//로그인 기능 설정 시작, 성공시 /주소로 이동
                        .userInfoEndpoint()//로그인 성공 이후 사용자 정보를 가져올 때의 설정들 담당.
                            .userService(customOAuth2UserService);  //소셜 로그인 성공 시 후속 조치를 진행할 UserService 인터페이스의 구현체를 등록, 사용자 정보를 가져온 상태에서 추가로 진해하고자 하는 기능들 명시.
    }
}
