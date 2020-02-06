package com.aaa.demo.controller;

import com.aaa.demo.mapper.QuestionMapper;
import com.aaa.demo.mapper.UserMapper;
import com.aaa.demo.model.Question;
import com.aaa.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName 金鑫
 * @Description: TODO
 * @Author yinwe
 * @Date 2020/2/5
 * @Version V1.0
 **/
@Controller
public class PublishController {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;
    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }
    @PostMapping("/publish")
    public String doPublish(
            @RequestParam(value = "title",required = false) String title,
            @RequestParam(value = "description",required = false) String description,
            @RequestParam(value = "tag",required = false) String tag,
            @RequestParam(value = "id", required = false) Long id,
            HttpServletRequest request,
            Model model){
        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);

        User user=null;
        //判断当前是否有用户登入，如果有登入则显示在页面上个人信息
        //没有登入，则跳转到登入页面

        Cookie[] cookies = request.getCookies();
        if (cookies!=null&&cookies.length!=0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    user = userMapper.findByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }
        //如果用户没有登入，则跳转到登入页面
        if (user == null){
            model.addAttribute("error","用户未登入");
            return "publish";
        }
        if (title!=null&&title.equals("")){
            model.addAttribute("error","没有问题标题");
            return "publish";
        }
        if (description!=null&&description.equals("")){
            model.addAttribute("error","还没有问题补充");
            return "publish";
        }if (tag!=null&&tag.equals("")){
            model.addAttribute("error","没有添加标签");
            return "publish";
        }
        Question question=new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        //存入问题提问人id
        question.setCreator(user.getId());
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());
        questionMapper.insert(question);
        //用户登入写入问题跳转到网站首页面
        return "redirect:/";
    }
}
