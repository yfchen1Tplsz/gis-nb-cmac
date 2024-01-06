package com.projecty.core.output.policy;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.querydsl.core.annotations.QueryProjection;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
//@AllArgsConstructor
@NoArgsConstructor
public class PolicyUwAnnotationBaseOutput {
    @Schema(name = "id", description = "uuid")
    private UUID id;

    @Schema(name = "annotationDate", description = "批注日期")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm", timezone = "GMT+8")
    private LocalDateTime annotationDate;

    @Schema(name = "annotationUser", description = "批注人")
    private String annotationUser;

    @Schema(name = "annotationContent", description = "批注内容")
    private String annotationContent;

    @QueryProjection
    public PolicyUwAnnotationBaseOutput(UUID id, LocalDateTime annotationDate, String annotationUser, String annotationContent) {
        this.id = id;
        this.annotationDate = annotationDate;
        this.annotationUser = annotationUser;
        this.annotationContent = annotationContent;
    }
}
