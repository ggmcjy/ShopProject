package com.example.torderproject.modules.menu.controller.api;

import com.example.torderproject.modules.menu.dto.MenuDto;
import com.example.torderproject.modules.menu.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/menu")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @GetMapping("/all")
    public ResponseEntity<List<MenuDto>> menuGetList() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(menuService.selectAllMenu());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuDto> menuGetById(@PathVariable("id") Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new MenuDto(menuService.selectOneMenu(id)));
    }
}
