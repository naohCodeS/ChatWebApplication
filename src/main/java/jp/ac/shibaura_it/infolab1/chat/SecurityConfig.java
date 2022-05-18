package jp.ac.shibaura_it.infolab1.chat;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

@EnableWebSecurity //認証フィルタの設定
public class SecurityConfig extends WebSecurityConfigurerAdapter { //デフォルト設定の追加

    @Override
    public void configure(WebSecurity web) throws Exception{ //全体にかかわる設定
        web.ignoring().antMatchers("/webjars/**", "/css/**"); //静的リソースへのアクセスは設定を適用しない
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{ //認可、ログイン・ログアウト設定
        http
                .authorizeRequests() //認可に関する設定
                    .antMatchers("/login", "/registerForm").permitAll()
                    .anyRequest().authenticated()
                .and()
                .formLogin()//ログインに関する設定
                .loginProcessingUrl("/login")
                    .loginPage("/login")
                    .failureUrl("/login?error")
                    .defaultSuccessUrl("/chat", true)
                    .usernameParameter("username").passwordParameter("password")
                .and()//ログアウトに関する設定
                .logout().logoutSuccessUrl("/login");
    }

    @Bean
    PasswordEncoder passwordEncoder(){ //パスワードのハッシュ化
        return new Pbkdf2PasswordEncoder();
    }
}
