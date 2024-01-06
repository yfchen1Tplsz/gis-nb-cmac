package com.projecty.core.output.policy;

import com.projecty.infrastucture.enums.PolicyStatusType;
import com.querydsl.core.annotations.QueryProjection;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Data
public class PolicyRecordOutput {

    @Schema(description = "保单号码")
    private String policyNo;

    @Schema(description = "投保公司英文名列表")
    private List<String> policyOwnerEnglishNameList;

    @Schema(description = "保单生效日期")
    private String policyEffectDate;

    @Schema(description = "保单当前状态")
    private String policyStatus;

    @Schema(description = "操作记录列表")
    List<PolicyRecordVo> operationRecordList;

    @QueryProjection
    public PolicyRecordOutput(String policyNo, String policyStatus, LocalDate policyEffectDate) {
        this.policyNo = policyNo;
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.policyStatus = policyStatus;
        if (policyEffectDate != null) {
            this.policyEffectDate = policyEffectDate.format(fmt);
            if (!PolicyStatusType.INFORCE.code().equals(policyStatus)) {
                this.policyEffectDate = this.policyEffectDate.concat("（未生效）");
            }
        } else {
            this.policyEffectDate = "";
        }

    }
}
