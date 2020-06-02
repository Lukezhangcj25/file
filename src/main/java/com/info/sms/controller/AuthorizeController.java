package com.info.sms.controller;

import com.info.sms.dto.AccessTokenDTO;
import com.info.sms.dto.GithubUser;
import com.info.sms.model.User;
import com.info.sms.provider.GithubProvider;
import com.info.sms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * Created by Luke 2020/4/1 14:23
 */
@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;

    @Value("${server.Client.id}")
    private String Clientid ;
    @Value("${server.Client.secret}")
    private String Clientsecret;
    @Value("${server.Redirect.uri}")
    private String Redirecturi;

    @Autowired
    private UserService userService;



    @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code,
                           @RequestParam(name="state") String state,
                           HttpServletResponse response,
                           Model model){
        // HttpServletRequest request等于js+servlet的使用方式
        // @RequestParam 完成gitHub2个参数的接收
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(Clientid);
        accessTokenDTO.setClient_secret(Clientsecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(Redirecturi);
        accessTokenDTO.setState(state);

        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser = githubProvider.getUser(accessToken);
        // System.out.println(user.getName());
        if(githubUser != null && githubUser.getId() != null){

            User user = new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(githubUser.getName());
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setAvatarUrl(githubUser.getAvatar_url());

            userService.createOrUpdate(user);
            response.addCookie(new Cookie("token", token));
            // 登录成功，写cookie和session
            // request.getSession().setAttribute("user",githubUser);

            // url显示index地址而不是带有get参数的地址
            return "redirect:/";
        }else{
            // 登录失败，重新登录
            return "redirect:/";
        }
    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest request,
                         HttpServletResponse response){
        request.getSession().removeAttribute("user");

        Cookie cookie = new Cookie("token",null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";
    }

    
}