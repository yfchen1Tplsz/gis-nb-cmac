package com.projecty.core.output.policy;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.querydsl.core.annotations.QueryProjection;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

/**
 * @author hdeng
 * @date 2023/6/29
 */
@Data
public class PolicyDetailOutput {

    @Schema(description = "保单id")
    private UUID id;

    @Schema(description = "保单id")
    private String policyStatus;

    @Schema(description = "保单号")
    private String policyNo;

    @Schema(description = "中介人")
    private String middleMan;

    @Schema(description = "渠道")
    private String channel;

    @Schema(description = "保單幣種")
    private String policyCurrency;

    @Schema(description = "縂年度保費")
    private BigDecimal yearPremium;

    @Schema(description = "繳費頻率")
    private String paymentFrequency;

    @Schema(description = "行政方式")
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
    private String proposalNo;

    @Schema(description = "PRP")
    private String policyPrp;

    @Schema(description = "备注")
    private String policyRemark;

    @Schema(description = "核保决定")
    private String uwResult;

    @Schema(description = "加费项")
    private String extraInfo;

    @Schema(description = "不保事项")
    private String exclusionsInfo;

    @Schema(description = "IMP")
    private String imp;

    @Schema(description = "下个保费到期日")
    @JsonFormat(pattern = "dd/MM/yyyy", timezone = "GMT+8")
    private LocalDate ndd;

    @QueryProjection
    public PolicyDetailOutput(UUID id, String policyStatus, String policyNo, String middleMan, String channel, String policyCurrency, BigDecimal yearPremium, String paymentFrequency, String administrationType, LocalDate applicationDate, LocalDate policyEffectDate, LocalDate ensureEffectDate, LocalDate ensureExpireDate, LocalDate policyAdoptionDate, String proposalNo, String policyPrp, String policyRemark, String uwResult, String extraInfo, String exclusionsInfo, String imp, LocalDate ndd) {
        this.id = id;
        this.policyStatus = policyStatus;
        this.policyNo = policyNo;
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
