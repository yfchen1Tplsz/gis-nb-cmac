package com.projecty.core.output.policy;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.projecty.infrastucture.enums.UwResultType;
import com.projecty.infrastucture.validator.EnumValue;
import com.querydsl.core.annotations.QueryProjection;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author hdeng
 * @date 2023/6/29
 */
@Data
public class PolicySaveOutput {

    @Schema(description = "中介人")
    @Size(max = 32, message = "中介人长度限制 32 个字符")
    private String middleMan;

    @Schema(description = "渠道")
    @Size(max = 32, message = "渠道长度限制 32 个字符")
    private String channel;

    @Schema(description = "保單幣種")
    private String policyCurrency;

    @Schema(description = "縂年度保費")
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

    @QueryProjection
    public PolicySaveOutput(String middleMan, String channel, String policyCurrency, BigDecimal yearPremium, String paymentFrequency, String administrationType, LocalDate applicationDate, LocalDate policyEffectDate, LocalDate ensureEffectDate, LocalDate ensureExpireDate, LocalDate policyAdoptionDate, String proposalNo, String policyPrp, String policyRemark, String uwResult, String extraInfo, String exclusionsInfo, String imp, LocalDate ndd) {
        this.middleMan = middleMan;
        this.channel = channel;
        this.policyCurrency = policyCurrency;
        this.yearPremium = yearPremium;
        this.paymentFrequency = paymentFrequency;
        this.administrationType = administrationType;
        this.applicationDate = applicationDate;
        this.policyEffectDate = policyEffectDate;
        this.ensureEffectDate = ensureEffectDate;
        this.ensureExpireDate = ensureExpireDate;
        this.policyAdoptionDate = policyAdoptionDate;
        this.proposalNo = proposalNo;
        this.policyPrp = policyPrp;
        this.policyRemark = policyRemark;
        this.uwResult = uwResult;
        this.extraInfo = extraInfo;
        this.exclusionsInfo = exclusionsInfo;
        this.imp = imp;
        this.ndd = ndd;
    }
}
