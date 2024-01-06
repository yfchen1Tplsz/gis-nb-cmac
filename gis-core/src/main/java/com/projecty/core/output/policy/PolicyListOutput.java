package com.projecty.core.output.policy;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @author hdeng
 * @date 2023/6/28
 */
@Data
public class PolicyListOutput {

    @Schema(description = "工作池列表数据")
    private List<PolicyListDTO> result;

    @Schema(description = "总条数")
    private Long total;

}
