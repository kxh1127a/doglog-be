package com.example.doglogbe.controller;

import com.example.doglogbe.entity.Member;
import com.example.doglogbe.model.MemberCreateRequest;
import com.example.doglogbe.model.MemberItem;
import com.example.doglogbe.model.MemberSearchRequest;
import com.example.doglogbe.model.result.CommonResult;
import com.example.doglogbe.model.result.SingleResult;
import com.example.doglogbe.service.MemberService;
import com.example.doglogbe.service.ResponseService;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/new")
    public CommonResult setMember(@RequestBody MemberCreateRequest request) {
        memberService.setMember(request);
        return ResponseService.getSuccessResult();
    }

    @GetMapping("/api")
    public SingleResult<Page<MemberItem>> getMembers(@RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "10") int size,
                                                     @RequestParam(defaultValue = "all") String filter
    ) {
        Page<MemberItem> memberPage = memberService.getMembers(page, size, filter);
        return ResponseService.getSingleResult(memberPage);
    }

    @GetMapping("/search")
    public SingleResult<Page<MemberItem>> getMembersBySearch(@ModelAttribute MemberSearchRequest request) {
//        MemberSearchRequest request = new MemberSearchRequest(name, userName, email, phone);

        Page<MemberItem> memberSearchPage = memberService.getMembersBySearch(request);
        return ResponseService.getSingleResult(memberSearchPage);
    }
}
