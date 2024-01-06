package com.projecty.core.output.policy;

import com.querydsl.core.annotations.QueryProjection;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class PolicyUwAnnotationOutput {

    @Schema(name = "policyUwAnnotationBaseOutputs", description = "批注列表")
    private List<PolicyUwAnnotationBaseOutput> policyUwAnnotationBaseOutputs;

    @Schema(name = "saveAnnotation", description = "暂存批注内容")
    private String saveAnnotation;

    @QueryProjection
    public PolicyUwAnnotationOutput(String saveAnnotation) {
        this.saveAnnotation = saveAnnotation;
    }
}
