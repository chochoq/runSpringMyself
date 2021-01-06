package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data","hello!!");
        return "hello";
    }

    @GetMapping("hello-mvc")//http://localhost:8080/hello-mvc?name=spring
    public String helloMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name",name);
        return "hello-template";

    }

    @GetMapping("hello-string") //http://localhost:8080/hello-string?name=spring
    @ResponseBody
    public String hellSpring(@RequestParam("name") String name){
        return "hello " + name;
    }

    //api 방식 json방식
    @GetMapping("hello-api") //http://localhost:8080/hello-api?name=spring
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;

    }

    //api를 위한 객체(레디겟셋 > javabean 표준, 프로퍼티접근방식
    static class Hello{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


}
