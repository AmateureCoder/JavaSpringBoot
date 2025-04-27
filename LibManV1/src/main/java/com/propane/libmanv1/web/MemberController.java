package com.propane.libmanv1.web;

import com.propane.libmanv1.identity.model.Member;
import com.propane.libmanv1.identity.service.imp.MemberServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberServiceImpl memberService;

    @GetMapping
    public String listMembers(Model model) {
        model.addAttribute("members", memberService.getAllMembers());
        return "admin/members/list";
    }

    @GetMapping("/add")
    public String showAddMemberForm(Model model) {
        model.addAttribute("member", new Member());
        return "admin/members/add-edit";
    }

    @PostMapping("/add")
    public String addMember(@ModelAttribute Member member) {
        memberService.saveMember(member);
        return "redirect:/admin/members";
    }

    @GetMapping("/edit/{id}")
    public String showEditMemberForm(@PathVariable Long id, Model model) {
        model.addAttribute("member", memberService.getMemberById(id));
        return "admin/members/add-edit";
    }

    @PostMapping("/edit/{id}")
    public String editMember(@PathVariable Long id, @ModelAttribute Member member) {
        member.setId(id);
        memberService.saveMember(member);
        return "redirect:/admin/members";
    }

    @GetMapping("/delete/{id}")
    public String deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
        return "redirect:/admin/members";
    }
}