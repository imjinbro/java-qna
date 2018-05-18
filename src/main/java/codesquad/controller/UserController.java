package codesquad.controller;

import codesquad.domain.User;
import codesquad.domain.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserController {
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserRepository userRepo;

    @PostMapping
    public String create(User user) {
        try {
            userRepo.save(user);
            return "redirect:/users";
        } catch (DataAccessException e) {
            logger.error("ERROR {} ", e.getMessage());
            return "redirect:/err";
        }
    }

    @GetMapping
    public String show(Model model) {
        model.addAttribute("users", userRepo.findAll());
        return "/users/list";
    }

    @GetMapping("/{id}")
    public String get(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userRepo.findById(id).get());
        return "/users/profile";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userRepo.findById(id).get());
        return "/users/edit";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable("id") Long id, String currentPasswd, User updateInfo) {
        try {
            User user = userRepo.findById(id).get();
            user.changeInfo(currentPasswd, updateInfo);
            userRepo.save(user);
            return "redirect:/users/" + id;
        } catch (DataAccessException | IllegalArgumentException e) {
            return "redirect:/error";
        }
    }

    @PostMapping("/login")
    public String login(String userId, String passwd, HttpSession session) {
        Optional<User> maybeUser = userRepo.findByUserId(userId);
        if (!maybeUser.isPresent() || !maybeUser.filter(user -> user.isMatch(passwd)).isPresent()) {
            return "redirect:/users/loginFail";
        }
        logger.info("USER {} ", maybeUser.get());
        session.setAttribute("user", maybeUser.get());
        return "redirect:/";
    }
}
