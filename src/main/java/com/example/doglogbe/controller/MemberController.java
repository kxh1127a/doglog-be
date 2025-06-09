package com.example.doglogbe.controller;

import com.example.doglogbe.model.MemberCreateRequest;
import com.example.doglogbe.model.MemberItem;
import com.example.doglogbe.service.MemberService;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/new")
    public String setMember(@RequestBody MemberCreateRequest request) {
        memberService.setMember(request);
        return "success";
    }

    @GetMapping("/api")
    public ResponseEntity<Page<MemberItem>> getMembers(@RequestParam(defaultValue = "0") int page,
                                                       @RequestParam(defaultValue = "10") int size,
                                                       @RequestParam(defaultValue = "all") String filter
    ) {
        Page<MemberItem> memberPage = memberService.getMembers(page, size, filter);
        return ResponseEntity.ok(memberPage);
    }
}
