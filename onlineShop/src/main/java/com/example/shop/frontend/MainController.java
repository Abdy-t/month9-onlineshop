package com.example.shop.frontend;

import com.example.shop.domain.brand.BrandDTO;
import com.example.shop.domain.brand.BrandRepository;
import com.example.shop.domain.brand.BrandService;
import com.example.shop.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
@RequestMapping
@AllArgsConstructor
public class MainController {

    private final BrandService brandService;

    private final PropertiesService propertiesService;

    private static <T> void constructPageable(Page<T> list, int pageSize, Model model, String uri) {
        if (list.hasNext()) {
            model.addAttribute("nextPageLink", constructPageUri(uri, list.nextPageable().getPageNumber(), list.nextPageable().getPageSize()));
        }

        if (list.hasPrevious()) {
            model.addAttribute("prevPageLink", constructPageUri(uri, list.previousPageable().getPageNumber(), list.previousPageable().getPageSize()));
        }

        model.addAttribute("hasNext", list.hasNext());
        model.addAttribute("hasPrev", list.hasPrevious());
        model.addAttribute("items", list.getContent());
        model.addAttribute("defaultPageSize", pageSize);
    }

    private static String constructPageUri(String uri, int page, int size) {
        return String.format("%s?page=%s&size=%s", uri, page, size);
    }

    @GetMapping
    public String index(Model model, Pageable pageable, HttpServletRequest uriBuilder) {
        Page<BrandDTO> places = brandService.getBrands(pageable);
        String uri = uriBuilder.getRequestURI();
        constructPageable(places, propertiesService.getDefaultPageSize(), model, uri);
        return "index";
    }

    @RequestMapping("/")
    public String getMainPage(Model model) {
        model.addAttribute("brands", brandService.findAll());
        return "index";
    }

    @RequestMapping("/jql/{name}")
    public String getMainPageJql(Model model, @PathVariable("name") String name) {
        model.addAttribute("brands", brandService.getByName(name));
        return "index";
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    private String handleRNF(ResourceNotFoundException ex, Model model) {
        model.addAttribute("resource", ex.getResource());
        model.addAttribute("id", ex.getId());
        return "resource-not-found";
    }

//    @GetMapping("/images/{name}")
//    @ResponseBody
//    public ResponseEntity<byte[]> getImage(@PathVariable("name") String name) {
//        String path = "/images";
//        try {
//            InputStream is = new FileInputStream(new File(path) + "/" + name);
//            return ResponseEntity
//                    .ok()
//                    .contentType(name.toLowerCase().contains(".png")? MediaType.IMAGE_PNG:MediaType.IMAGE_JPEG)
//                    .body(StreamUtils.copyToByteArray(is));
//        } catch (Exception e) {
//            InputStream is = null;
//            try {
//                is = new FileInputStream(new File(path) + "/" + name);
//                return ResponseEntity
//                        .ok()
//                        .contentType(name.toLowerCase().contains(".png")?MediaType.IMAGE_PNG: MediaType.IMAGE_JPEG)
//                        .body(StreamUtils.copyToByteArray(is));
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
//            e.printStackTrace();
//        }
//        return null;
//    }

}
