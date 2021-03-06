package life.syang.community.community.controller;

import life.syang.community.community.dto.AccessTokenDTO;
import life.syang.community.community.dto.GithubUser;
import life.syang.community.community.model.User;
import life.syang.community.community.provider.GithubProvider;
import life.syang.community.community.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.Cookie;
import java.util.UUID;

@Controller
@Slf4j
public class AuthorizeController extends BaseController {
    @Autowired
    private GithubProvider githubProvider;
    @Autowired
    private UserService userService;
    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.uri}")
    private String redirectUri;
    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state")String state){
        AccessTokenDTO accessTokenDTO=new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        String accessToken=githubProvider.gerAccessToken(accessTokenDTO);
        GithubUser gituser=githubProvider.getUser(accessToken);
        if(gituser!=null){
            //登录成功
            User user=new User();
            user.setToken(UUID.randomUUID().toString());
            user.setName(gituser.getName());
            user.setAccountId(String.valueOf(gituser.getId()));
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            user.setAvatarUrl(gituser.getAvatarUrl());
            if(userService.queryByAccountId(user.getAccountId())==null){
                userService.insertUser(user);
            }else {
                userService.userLogin(user);
            }
            response.addCookie(new Cookie("token",user.getToken()));
            return "redirect:/";
        }else {
            //登录失败
            log.error("github-callbackFail"+gituser.getId()+gituser.getName());
            return "redirect:/";
        }
    }

    @GetMapping("/logout")
    public String logout(){
        request.getSession().removeAttribute("user");
        Cookie cookie=new Cookie("token",null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";
    }
}
