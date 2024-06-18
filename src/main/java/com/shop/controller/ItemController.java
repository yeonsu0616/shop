package com.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ItemController {
    @GetMapping(value = "/admin/item/new") // / ->넣는 이유 앞에 내용 다 지우고 이거부터 넣음
    public String itemForm(){
        return "/item/itemForm";
    }
}
