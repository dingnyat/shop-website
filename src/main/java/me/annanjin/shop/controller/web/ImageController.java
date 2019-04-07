package me.annanjin.shop.controller.web;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Controller
public class ImageController {

    @GetMapping(value = "/images/{imgName}")
    public void loadImage(@PathVariable("imgName") String imgName, HttpServletResponse response) throws IOException {
        String filePath = "D:\\user"+ File.separator + imgName;
        File file = new File(filePath);
        response.setContentType(MediaType.IMAGE_PNG_VALUE);
        if (file.isFile()){
            Files.copy(file.toPath(), response.getOutputStream());
        }
    }
}