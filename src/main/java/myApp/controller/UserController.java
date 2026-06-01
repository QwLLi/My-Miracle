package myApp.controller;

import io.swagger.v3.oas.annotations.Operation;
import myApp.model.User;
import myApp.serviсe.Serviсe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;

@RequestMapping("/users")
@Controller
public class UserController {

    private final Serviсe serviseImp;
    public UserController(Serviсe serviseImp) {
        this.serviseImp = serviseImp;
    }

    @Operation(summary = "Вывод пользователя")
    @GetMapping("/getUser")
    public String getUser(@RequestParam(value = "id", required = false) Long id,
                          Model model) {
        model.addAttribute("user", serviseImp.getUser(id));
        return "user";
    }

    @Operation(summary = "Вывод всех пользователей")
    @GetMapping({"", "/"})
    public String getAllUsers(Model model) {
        model.addAttribute("users", serviseImp.getAllUsers());
        return "users";
    }

    @Operation(summary = "Сохранение пользователя")
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

    @Operation(summary = "Изменение пользователя")
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
    @Operation(summary = "Удаление пользователя")
    @GetMapping(value = "/delete")
    public String deleteUser(@RequestParam(value = "id", required = false) Long id) {
        serviseImp.deleteUser(id);
        return "redirect:/users";
    }

}