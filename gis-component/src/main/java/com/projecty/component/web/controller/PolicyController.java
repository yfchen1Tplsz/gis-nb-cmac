package com.projecty.component.web.controller;

import com.projecty.core.application.PolicyApplication;
import com.projecty.core.input.policy.PolicyInput;
import com.projecty.core.output.policy.PolicyOutput;
import com.projecty.model.domain.policy.service.PolicyService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author 陈宇锋
 * @date 2024/1/5
 */
@RestController
@RequestMapping("/api")
public class PolicyController {
    @Autowired
    private PolicyApplication policyApplication;
    @GetMapping("/test")
    public ResponseEntity<String> test(){
        return ResponseEntity.ok("ok!");
    }

    @PostMapping("/policy")
    public ResponseEntity<PolicyOutput> createNbPolicy(@Valid @RequestBody PolicyInput policyInput){
        return ResponseEntity.ok()
                        .body(policyApplication.createNbPolicy(policyInput.getPolicyNo()));

    }
}
