package myApp.controller;

import myApp.model.User;
import myApp.serviсe.Serviсe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;




@RequestMapping("/users")
@Controller
public class UserController {


    private final Serviсe serviseImp;

    public UserController(Serviсe serviseImp) {
        this.serviseImp = serviseImp;
    }

    @GetMapping("/getUser")
    public String getUser(@RequestParam(value = "id", required = false) Long id,
                          Model model) {
        model.addAttribute("user", serviseImp.getUser(id));
        return "user";
    }

    @GetMapping({"", "/"})
    public String getAllUsers(Model model) {
        model.addAttribute("users", serviseImp.getAllUsers());
        return "users";
    }

    @GetMapping("/save-user")
    public String saveUser(Model model) {
        model.addAttribute("user", new User());
        return "save-user";
    }

    @PostMapping("/save-user")
    public String processSaveUser(@ModelAttribute("user") User user) {
        serviseImp.saveUser(user.getFirstName(), user.getLastName(), user.getYear());
        return "redirect:/users";
    }

    @GetMapping("/update")
    public String updateUser(@RequestParam(value = "id" , required = false)Long id , Model model) {
        User user = serviseImp.getUser(id);
        model.addAttribute("user" , user);
        return "update";
    }

    @PostMapping("/update")
    public String processUpdateUser(@ModelAttribute("user") User user) {
        serviseImp.updateUser(user);
        return "redirect:/users";
    }

    @GetMapping(value = "/delete")
    public String deleteUser(@RequestParam(value = "id", required = false) Long id) {
        serviseImp.deleteUser(id);
        return "redirect:/users";
    }

}