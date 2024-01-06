package com.projecty.core.output.policy;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

/**
 * @author hdeng
 * @date 2023/6/28
 */
@Data
public class PolicyListDTO {

    @Schema(description = "保单Id")
    private UUID id;

    @Schema(description = "工作日期", format = "dd/MM/yyyy")
    @JsonFormat(pattern = "dd/MM/yyyy", timezone = "GMT+8")
    private LocalDate workDate;

    @Schema(description = "保单号")
    private String policyNo;

    @Schema(description = "产品代码")
    private String productCodeAndName;

    @Schema(description = "保单状态")
    private String policyStatus;

    @Schema(description = "核保决定")
    private String uwResult;

    @Schema(description = "用户id")
    private String userId;

}
