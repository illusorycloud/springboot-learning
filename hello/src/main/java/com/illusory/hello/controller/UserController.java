package com.illusory.hello.controller;

import com.illusory.hello.pojo.User;
import com.illusory.hello.service.Impl.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    /**
     * 添加用户
     *
     * @param user user
     * @return
     */
    @RequestMapping("/addUser") //user/addUser
    public String addUser(User user) {
        userService.addUser(user);
        return "redirect:/user/queryAll";
    }

    /**
     * 页面跳转
     *
     * @param page
     * @return
     */
    @RequestMapping("/{page}")
    public String show(@PathVariable("page") String page) {
        return page;
    }

    /**
     * 查询所有用户
     *
     * @param mav ModelAndView
     * @return
     */
    @RequestMapping("/queryAll")
    public ModelAndView queryAll(ModelAndView mav) {
        List<User> users = userService.queryAll();
        mav.addObject("list", users);
        mav.setViewName("userList");
        return mav;
    }

    /**
     * 根据Id查询用户
     *
     * @param id    id
     * @param model
     * @return
     */
    @RequestMapping("/findUserById")
    public String findUserById(Integer id, Model model) {
        User user = userService.findUserById(id);
        model.addAttribute("user", user);
        return "editUser";
    }

    /**
     * 编辑用户
     *
     * @param user  user
     * @param model
     * @return
     */
    @RequestMapping("/editUser")
    public String editUser(User user, Model model) {
        userService.updateUser(user);
        //重定向到查询界面
        return "redirect:/user/queryAll";
    }

    /**
     * 根据Id删除用户
     *
     * @param id
     * @return
     */
    @RequestMapping("/deleteUserById")
    public String editUser(Integer id) {
        userService.deleteUserById(id);
        return "redirect:/user/queryAll";
    }
}
