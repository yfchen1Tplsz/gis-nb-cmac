package com.projecty.core.output.policy;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.querydsl.core.annotations.QueryProjection;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PolicyRecordVo {
    @Schema(description = "用户名")
    private String userId;

    @Schema(description = "操作时间")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime operationTime;

    @Schema(description = "保单变更前状态")
    private String policyBeforeStatus;

    @Schema(description = "保单变更后状态")
    private String policyAfterStatus;

    @Schema(description = "备注")
    private String remark;

    @QueryProjection
    public PolicyRecordVo(String userId, LocalDateTime operationTime, String policyBeforeStatus, String policyAfterStatus, String remark) {
        this.userId = userId;
        this.operationTime = operationTime;
        this.policyBeforeStatus = policyBeforeStatus;
        this.policyAfterStatus = policyAfterStatus;
        this.remark = remark;
    }
}
