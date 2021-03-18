package org.experience.k.springboot.web;

import org.experience.k.springboot.config.auth.LoginUser;
import org.experience.k.springboot.config.auth.dto.SessionUser;
import org.experience.k.springboot.web.dto.PostsResponseDto;
import org.springframework.ui.Model;
import lombok.RequiredArgsConstructor;
import org.experience.k.springboot.service.posts.PostsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user){
        model.addAttribute("posts", postsService.findAllDesc());

        if(user !=null){
            model.addAttribute("userName", user.getName());
        }
        return "index"; //src/main/resources/templates/ + "index" +.mustache
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);
        return "posts-update";
    }


    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }
}
