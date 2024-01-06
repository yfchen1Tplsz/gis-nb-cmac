package com.projecty.core.output.policy;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.querydsl.core.annotations.QueryProjection;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PolicyBaseOutput {

    @Schema(description = "保单号码")
    private String policyNo;

    @Schema(description = "保单持有人")
    private String policyOwnerEnglishName;

    @Schema(description = "保单生效日期")
    @JsonFormat(pattern = "dd/MM/yyyy", timezone = "GMT+8")
    private LocalDate policyEffectDate;

    @Schema(description = "保单状态")
    private String policyStatus;

    @Schema(description = "下个保费到期日")
    @JsonFormat(pattern = "dd/MM/yyyy", timezone = "GMT+8")
    private LocalDate ndd;

    @Schema(description = "保单币种")
    private String policyCurrency;

    @QueryProjection
    public PolicyBaseOutput(String policyNo, String policyOwnerEnglishName, LocalDate policyEffectDate, String policyStatus, LocalDate ndd, String policyCurrency) {
        this.policyNo = policyNo;
        this.policyOwnerEnglishName = policyOwnerEnglishName;
        this.policyEffectDate = policyEffectDate;
        this.policyStatus = policyStatus;
        this.ndd = ndd;
        this.policyCurrency = policyCurrency;
    }
}
