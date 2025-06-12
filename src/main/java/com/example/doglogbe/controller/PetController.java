package com.example.doglogbe.controller;

import com.example.doglogbe.model.PetItem;
import com.example.doglogbe.model.PetSearchRequest;
import com.example.doglogbe.model.result.ListResult;
import com.example.doglogbe.model.result.SingleResult;
import com.example.doglogbe.service.PetService;
import com.example.doglogbe.service.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pet")
public class PetController {
    private final PetService petService;

    @GetMapping("/all")
    public SingleResult<Page<PetItem>> getPets(@RequestParam int page,
                                              @RequestParam int size) {
        return ResponseService.getSingleResult(petService.getPets(page, size));
    }

    @GetMapping("/search")
    public SingleResult<Page<PetItem>> getPetsBySearch(@ModelAttribute PetSearchRequest request) {
        return ResponseService.getSingleResult(petService.getPetsBySearch(request));
    }

}
