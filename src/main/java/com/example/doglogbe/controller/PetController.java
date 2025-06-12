package com.example.doglogbe.controller;

import com.example.doglogbe.model.PetItem;
import com.example.doglogbe.model.result.ListResult;
import com.example.doglogbe.service.PetService;
import com.example.doglogbe.service.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pet")
public class PetController {
    private final PetService petService;

    @GetMapping("/all")
    public ListResult<PetItem> getPets() {
        return ResponseService.getListResult(petService.getPets());
    }


}
