package com.example.doglogbe.controller;

import com.example.doglogbe.model.PetItem;
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


}
