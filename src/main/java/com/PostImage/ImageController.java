package com.PostImage;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ImageController {

    @GetMapping("/imageGallery")
    public String imageGallery(Model model) {
        // Specify the directory where your images are located
        String imageDirectoryPath = "src/main/resources/static/images";

        // Create a list to store image file names
        List<String> imageFileNames = new ArrayList<>();

        // Scan the directory for image files
        File imageDirectory = new File(imageDirectoryPath);
        File[] files = imageDirectory.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isFile() && isImageFile(file.getName())) {
                    imageFileNames.add(file.getName());
                }
            }
        }

        // Add the list of image file names to the model
        model.addAttribute("imageFileNames", imageFileNames);

        return "image"; // Return the Thymeleaf template name
    }

    private boolean isImageFile(String fileName) {
        // You can add more image file extensions as needed (e.g., .png, .gif, etc.)
        return fileName.toLowerCase().endsWith(".jpg") || fileName.toLowerCase().endsWith(".jpeg");
    }
}
