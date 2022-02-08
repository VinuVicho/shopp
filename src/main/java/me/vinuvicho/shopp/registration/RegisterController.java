package me.vinuvicho.shopp.registration;

import lombok.AllArgsConstructor;
import me.vinuvicho.shopp.registration.RegistrationRequest;
import me.vinuvicho.shopp.registration.RegistrationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller                     //to send html
//@RestController               //if I want to send not html
@RequestMapping(path = "/register")
@AllArgsConstructor
public class RegisterController {

    private final RegistrationService registrationService;

    @PostMapping()
//    @ResponseBody               //to send non-html
    public String register(@ModelAttribute RegistrationRequest request, Model model) {       //@ModelAttribute isn't necessary
        String token = registrationService.register(request);
        model.addAttribute("token", token);
        return "pages/basic/check-email";
    }

    @GetMapping()
    public String registerPage(Model model) {
        model.addAttribute("registrationRequest", new RegistrationRequest());
        return "pages/user/register";
    }

    @GetMapping(path = "/confirm")
    public String confirm(@RequestParam("token") String token) {
        registrationService.confirmToken(token);
                //TODO: make post-register notification about filling profile
        return "redirect:/login";
    }

    @GetMapping(path = "/reject")
    public String reject(@RequestParam("token") String token) {
        registrationService.rejectToken(token);
        return "pages/basic/token-rejected";
    }
}
