


//Stop use This file

package com.nha.nha_smos;

import lombok.extern.java.Log;
import org.apache.catalina.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

//listen from use request
@RestController
@RequestMapping("/api/user")
public class  HomeController{

//    path params meaning post params
    @GetMapping("/{id}/order/{orderId}")
    public String UserGet(@PathVariable Long id, @PathVariable Long orderId){
        return "Get user " + id + " Order " + orderId;
    }

//  @RequestParam meaning request param or query
    @GetMapping("/search")
    public String UserSearch(@RequestParam String textName, @RequestParam(defaultValue = "5") Long page, @RequestParam boolean status){
        return "Search user " + textName + " Page " + page + " Status " + status;
    }
    @GetMapping("/search1")
    public String UserSearch1(@RequestParam Map<String, String> param){
        String page = param.get("page");
        return page.toString();
    }

//  @RequestBody meaning post request body
    @PostMapping()
    public Map<String, Object> UserPost(@RequestBody Map<String, Object> body){
        return body;
    }

//    (/) is call  @Patchvariable
    @PutMapping("/{id}")
    public String UserPut(@PathVariable Long id, @RequestBody Map<String, Object> body){
        return "Update user";
    }


    @PatchMapping("/{id}")
    public String UserPatch(@PathVariable Long id, @RequestBody Map<String, Object> body){
        return "Update user";
    }


    @DeleteMapping("/{id}")
    public String UserDelete(@PathVariable Long id){
        return "Delete user";
    }
}
