package study.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfigV3_FormLogin {

    /**
     * anyRequest().authenticated() : 어떤 요청에 대해서든 인증을 받은 사용자만 접근을 허용한다.
     * -
     * loginPage : 로그인이 필요할 때, 이 경로로 Redirecting 된다. 로그인폼을 반환해줘야 한다.
     * defaultSuccessUrl : 로그인 성공 시, 이 경로로 Redirecting 된다.
     * failureUrl : 로그인 실패 시, 이 경로로 Redirecting 된다.
     * usernameParameter : 로그인폼 input 태그의 name
     * passwordParameter : 로그인폼 input 태그의 name
     * loginProcessingUrl : 로그인폼의 action 경로. 따로 Mapping된 Controller 메서드는 없어도 된다.
     *   - 필터에서 처리하고 매핑된 메서드는 호출되지도 않는다.
     *   - <form th:action="@{/login}" class="form-signin" method="post">
     * successHandler : 로그인 성공 시 호출되는 handler. 리다이렉팅 처리 직접 해줘야한다.
     *   - System.out.println() 만 하니 출력만하고 리다이렉팅은 되지 않았음
     *   - 특별한 경우 아니면 호출할 필요 없겠다.
     * failureHandler : 로그인 실패 시 호출되는 handler. 리다이렉팅 처리 직접 해줘야한다.
     *   - System.out.println() 만 하니 출력만하고 리다이렉팅은 되지 않았음
     *   - 특별한 경우 아니면 호출할 필요 없겠다.
     * permitAll : formLogin()에 연결되어 있다. 로그인 페이지와 관련된 모든 요청만 접근을 허용
     *   - formLogin().permitAll() 은 위의 anyRequest().authenticated() 보다 우선 순위가 높다.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        System.out.println("SecurityConfigV3_FormLogin.filterChain");
        http
                // 인가 정책
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                // 인증 정책
                .formLogin()
                .loginPage("/login-form")
                .defaultSuccessUrl("/home")
                .failureUrl("/login-form?error=true")
                .usernameParameter("username")
                .passwordParameter("password")
                .loginProcessingUrl("/login")
//                .successHandler(
//                        (req, resp, auth) -> System.out.println("Success Handler")
//                )
//                .failureHandler(
//                        (req, resp, exception) -> System.out.println("Failure Handler")
//                )
                .permitAll();

        return http.build();
    }
}
