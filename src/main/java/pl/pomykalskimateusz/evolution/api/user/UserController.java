package pl.pomykalskimateusz.evolution.api.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.pomykalskimateusz.evolution.api.user.create.CreateUserResponse;
import pl.pomykalskimateusz.evolution.service.user.UserService;

@Controller
@RequestMapping(value = "/users")
public record UserController(UserService userService) {
    @PostMapping()
    public @ResponseBody CreateUserResponse createUser() {
        return CreateUserResponse.of(userService.createUser());
    }
}
