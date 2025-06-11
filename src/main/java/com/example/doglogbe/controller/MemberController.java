package com.example.doglogbe.controller;

import com.example.doglogbe.entity.Member;
import com.example.doglogbe.model.MemberCreateRequest;
import com.example.doglogbe.model.MemberItem;
import com.example.doglogbe.model.MemberResponse;
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

    // 페이징 READ
    @GetMapping("/api")
    public SingleResult<Page<MemberItem>> getMembers(@RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "10") int size,
                                                     @RequestParam(defaultValue = "createdAt") String sortBy,
                                                     @RequestParam(defaultValue = "desc") String direction,
                                                     @RequestParam(defaultValue = "all") String filter
    ) {
        Page<MemberItem> memberPage = memberService.getMembers(page, size, sortBy, direction, filter);
        return ResponseService.getSingleResult(memberPage);
    }

    // 검색 READ
    @GetMapping("/search")
    public SingleResult<Page<MemberItem>> getMembersBySearch(@ModelAttribute MemberSearchRequest request) {
//        MemberSearchRequest request = new MemberSearchRequest(name, userName, email, phone);

        Page<MemberItem> memberSearchPage = memberService.getMembersBySearch(request);
        return ResponseService.getSingleResult(memberSearchPage);
    }

    // 단일 READ (멤버 정보 상세 보기)
    @GetMapping("/detail/{id}")
    public SingleResult<MemberResponse> getMember(@PathVariable long id) {
        return ResponseService.getSingleResult(memberService.getMember(id));
    }

}
