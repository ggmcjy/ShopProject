package com.example.torderproject.modules.menu.service;

import com.example.torderproject.modules.menu.dto.MenuDto;
import com.example.torderproject.modules.menu.jpa.Menu;
import com.example.torderproject.modules.menu.jpa.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService{

    private final MenuRepository menuRepository;

    /**
     * menu select All
     */
    @Override
    public List<MenuDto> selectAllMenu() {
        return menuRepository
                .findAll()
                .stream()
                .map(MenuDto::new)
                .collect(Collectors.toList());
    }

    /**
     * menu select One
     */
    @Override
    public Menu selectOneMenu(Long id) {
        return menuRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Menu ID is not found"));
    }

}
