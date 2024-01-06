package com.projecty.core.output.policy;

import com.querydsl.core.annotations.QueryProjection;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;
import java.util.UUID;

/**
 * @author hdeng
 * @date 2023/6/28
 */
@Data
public class GeneralEnquiryPolicyListDTO {

    @Schema(description = "保单Id")
    private UUID id;

    @Schema(description = "保单号")
    private String policyNo;

    @Schema(description = "保单持有人英文名")
    private List<String> policyOwnerEnglishNameList;

    @Schema(description = "保单状态")
    private String policyStatus;

    @QueryProjection
    public GeneralEnquiryPolicyListDTO(UUID id, String policyNo, String policyStatus) {
        this.id = id;
        this.policyNo = policyNo;
        this.policyStatus = policyStatus;
    }
}
