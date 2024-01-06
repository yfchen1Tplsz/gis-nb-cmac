package com.projecty.core.input.policy;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
/**
 * @author 陈宇锋
 * @date 2024/1/4
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(name = "PolicyListInput", description = "条件分页查询保单列表请求参数")
public class PolicyListInput {

    @Schema(description = "保单号")
    private String policyNo;

    @Schema(description = "保单状态")
    private List<String> policyStatusList;

    @Schema(description = "核保决定")
    private List<String> uwResultList;

    @Schema(description = "用户id")
    private List<String> userIdList;

    @Schema(description = "产品代码")
    private String productCodeAndName;

    @Schema(name = "pageNumber", description = "页号")
    private Integer pageNumber;

    @Schema(name = "pageSize", description = "每页数据量")
    private Integer pageSize;

}
