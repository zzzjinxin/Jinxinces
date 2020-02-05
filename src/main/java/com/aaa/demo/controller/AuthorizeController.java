package com.aaa.demo.controller;

import com.aaa.demo.dto.AccessTokenDto;
import com.aaa.demo.model.User;
import com.aaa.demo.provider.GithubProvider;
import com.aaa.demo.dto.GithubUser;
import com.aaa.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @ClassName 金鑫
 * @Description: TODO
 * @Author yinwe
 * @Date 2020/2/4
 * @Version V1.0
 **/
@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;
    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect_uri}")
    private String redirectUri;
    @Autowired
    private UserMapper userMapper;
    @GetMapping("/callback")
    public String callback(
            @RequestParam(name = "code") String code,
            @RequestParam(name = "state") String state,
            HttpServletResponse response
    ){
        //登入传递的参数
        AccessTokenDto accessTokenDto = new AccessTokenDto();
        accessTokenDto.setCode(code);
        accessTokenDto.setState(state);
        accessTokenDto.setRedirect_uri(redirectUri);
        accessTokenDto.setClient_id(clientId);
        accessTokenDto.setClient_secret(clientSecret);
        String accessToken = githubProvider.getAccessToken(accessTokenDto);
        GithubUser githubUser = githubProvider.getUser(accessToken);
        if (githubUser!=null&&githubUser.getId()!=null){
            User user=new User();
            //接收的是网络传输id
            user.setAccountId(String.valueOf(githubUser.getId()));
            //接收的是网络传递name
            user.setName(githubUser.getName());
            //uuid
            String token=UUID.randomUUID().toString();
            user.setToken(token);
            //传递当前时间
            user.setGmtCreate(System.currentTimeMillis());
            //传递的是时间搓
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
            response.addCookie(new Cookie("token",token));
            System.out.println(githubUser.getName());
            return "redirect:/";
        }else{
            return "redirect:/";
        }

    }
}
