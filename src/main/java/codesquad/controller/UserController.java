package codesquad.controller;

import codesquad.model.User;
import codesquad.model.Users;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

@Controller
public class UserController {
    private Users users = new Users();

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public String create(User user) {
        users.add(user);
        return "redirect:/users";
    }

    @RequestMapping("/users")
    public String show(Model model) {
        model.addAttribute("users", users.getUsers());
        return "/user/list";
    }

    @RequestMapping("/users/{id}")
    public String get(Model model, @PathVariable("id") String id) {
        Optional<User> user = users.findById(Integer.parseInt(id));
        if (!user.isPresent()) {
            System.out.println("존재하지않는 사용자입니다.");
            return "/error/show";
        }
        model.addAttribute("user", user.get());
        return "/user/profile";
    }

    @RequestMapping(value = "/users/{id}/edit")
    public String edit(@PathVariable("id") String id, Model model) {
        Optional<User> user = users.findById(Integer.parseInt(id));
        model.addAttribute("user", user.get());
        return "/user/edit";
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.POST)
    public String update(@PathVariable("id") String id, String currentPasswd, String changePasswd, String name, String email) {
        Optional<User> optionalUser = users.findById(Integer.parseInt(id));
        if (!optionalUser.isPresent()) {
            System.out.println("에러에 의해 사용자 삭제된 경우, 데이터베이스 임의조작에 의해 사용자 삭제된 경우");
            return "/error/show";
        }

        try {
            optionalUser.get().changeInfo(currentPasswd, changePasswd, name, email);
            return "redirect:/users/" + id;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return "redirect:/";
        }
    }
}
