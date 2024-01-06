package com.projecty.core.input.policy;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

/**
 * @author 陈宇锋
 * @date 2024/1/4
 */
@Data
public class PolicyInput {

    @Schema(description = "保单号")
    @Pattern(regexp = "^TPLHK[0-9]{12}009$", message = "保單號碼有誤！")
    private String policyNo;

}
