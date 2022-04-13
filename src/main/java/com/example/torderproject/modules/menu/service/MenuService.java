package com.example.torderproject.modules.menu.service;

import com.example.torderproject.modules.menu.dto.MenuDto;
import com.example.torderproject.modules.menu.jpa.Menu;

import java.util.List;

public interface MenuService {


    List<MenuDto> selectAllMenu();
    Menu selectOneMenu(Long id);
}
