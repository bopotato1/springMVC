package com.itheima.controller;


import com.sun.jersey.api.client.WebResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import com.sun.jersey.api.client.Client;

import javax.jws.WebParam;
import java.io.IOException;

@Controller
@RequestMapping("demo01")
@SessionAttributes("username")
public class demo01 {
    @RequestMapping("test01")
    public String test01(MultipartFile[] fileImg) throws IOException {
        System.out.println("文件上传的代码执行了、、、、");
        for (MultipartFile multipartFile : fileImg) {
            String originalFilename = multipartFile.getOriginalFilename();
            byte[] bytes = multipartFile.getBytes();
            String fileUrlServer="http://localhost:8081/file/";

            Client client = Client.create();
            WebResource resource = client.resource(fileUrlServer + originalFilename);
            resource.put(bytes);
        }
        return "success";
    }

    @RequestMapping("test02")
    public String test02(String username, String password, Model model) throws IOException {
       if("jack".equals(username)&&"123".equals(password)){
           model.addAttribute("username",username);
           return "success";
       }
        return "redirect:/login.html";
    }
}
