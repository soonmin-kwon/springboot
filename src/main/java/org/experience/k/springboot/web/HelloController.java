package org.experience.k.springboot.web;
import org.experience.k.springboot.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// JSON을 반환하는 컨트롤러를 만들어 준다.
// ResponseBody를 각 메소드마다 선언하지 않고 한번에 사용할 수 있게 해준다.
@RestController

public class HelloController {
    //HTTP 메소드인 Get의 요청을 받을 수 있는 API를 만들어 준다.
    @GetMapping("/hello")
    public String hello(){
        return "test";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name, @RequestParam(value ="amount", required = false) int amount) {
        return new HelloResponseDto(name, amount);
    }
}
