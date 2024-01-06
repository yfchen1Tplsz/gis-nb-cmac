package com.projecty.core.input.policy;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PolicyUwAnnotationInput {
    @NotNull(message = "parentId不能为空")
    @Schema(name = "parentId", description = "保单 id")
    private UUID parentId;
    @NotBlank(message = "批注内容不能为空")
    @Schema(name = "annotationContent", description = "批注内容")
    private String annotationContent;
}
