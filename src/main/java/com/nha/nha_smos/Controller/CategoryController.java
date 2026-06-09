package com.nha.nha_smos.Controller;

import ch.qos.logback.core.model.Model;
import com.nha.nha_smos.Service.CategoryService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RequiredArgsConstructor
@Component
@RequestMapping("api/category")
public class CategoryController {
    private CategoryService categoryService;
    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

//    @GetMapping
//    public String getCategories(Model model){}


}
