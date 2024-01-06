package com.projecty.core.input.policy;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.projecty.infrastucture.enums.PolicyStatusType;
import com.projecty.infrastucture.enums.UwResultType;
import com.projecty.infrastucture.validator.EnumValue;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import jakarta.validation.groups.Default;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author 陈宇锋
 * @date 2024/1/4
 */
@Data
public class PolicySaveInput {
    public interface CS extends Default {
    }

    @Schema(description = "保单状态")
    @NotBlank(message = "保單狀態不能爲空", groups = CS.class)
    @EnumValue(enumClass = PolicyStatusType.class, value = "code", message = "非法的保單狀態")
    private String policyStatus;

    @Schema(description = "中介人")
    @Size(max = 32, message = "中介人长度限制 32 个字符")
    private String middleMan;

    @Schema(description = "渠道")
    @Size(max = 32, message = "渠道长度限制 32 个字符")
    private String channel;

    @Schema(description = "保單幣種")
    private String policyCurrency;

    @Schema(description = "縂年度保費")
    @Min(value = 0L,message = "縂年度保費必須大於等於")
    @Digits(integer = 18, fraction = 2, message = "縂年度保費整數長度不超過17，小數點后精度不超過2")
    private BigDecimal yearPremium;

    @Schema(description = "繳費頻率")
    @Size(max = 20, message = "繳費頻率长度限制 20 个字符")
    private String paymentFrequency;

    @Schema(description = "行政方式")
    @Size(max = 2, message = "行政方式长度限制 2 个字符")
    private String administrationType;

    @Schema(description = "申請日期")
    @JsonFormat(pattern = "dd/MM/yyyy", timezone = "GMT+8")
    private LocalDate applicationDate;

    @Schema(description = "保單生效日期")
    @JsonFormat(pattern = "dd/MM/yyyy", timezone = "GMT+8")
    private LocalDate policyEffectDate;

    @Schema(description = "保障生效日期")
    @JsonFormat(pattern = "dd/MM/yyyy", timezone = "GMT+8")
    private LocalDate ensureEffectDate;

    @Schema(description = "保障終止日期")
    @JsonFormat(pattern = "dd/MM/yyyy", timezone = "GMT+8")
    private LocalDate ensureExpireDate;

    @Schema(description = "保單通過日期")
    @JsonFormat(pattern = "dd/MM/yyyy", timezone = "GMT+8")
    private LocalDate policyAdoptionDate;

    @Schema(description = "報價單號")
    @Size(max = 32, message = "報價單號长度限制 32 个字符")
    private String proposalNo;

    @Schema(description = "PRP")
    @Size(max = 2, message = "PRP长度限制 2 个字符")
    private String policyPrp;

    @Schema(description = "备注")
    private String policyRemark;

    @Schema(description = "核保决定")
    @EnumValue(enumClass = UwResultType.class, value = "code", message = "非法的核保決定")
    private String uwResult;

    @Schema(description = "加费项")
    @Size(max = 256, message = "加费项长度限制 256 个字符")
    private String extraInfo;

    @Schema(description = "不保事项")
    @Size(max = 256, message = "不保事项长度限制 256 个字符")
    private String exclusionsInfo;

    @Schema(description = "IMP")
    @Size(max = 50, message = "IMP长度限制 50 个字符")
    private String imp;

    @Schema(description = "下个保费到期日")
    @JsonFormat(pattern = "dd/MM/yyyy", timezone = "GMT+8")
    private LocalDate ndd;

}
