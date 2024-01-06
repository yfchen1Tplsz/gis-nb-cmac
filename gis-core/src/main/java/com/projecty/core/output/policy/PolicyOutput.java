package com.projecty.core.output.policy;

import com.querydsl.core.annotations.QueryProjection;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author 陈宇锋
 * @date 2024/1/5
 */
@Data
public class PolicyOutput {

    @Schema(description = "保单id")
    private String id;

    @Schema(description = "保单号码")
    private String policyNo;

    @Schema(description = "保单状态")
    private String policyStatus;

    @Schema(description = "中介人")
    private String middleMan;

    @Schema(description = "渠道")
    private String channel;

    @Schema(description = "保单币种")
    private String policyCurrency;

    @Schema(description = "总年度保费")
    private BigDecimal yearPremium;

    @Schema(description = "缴费频率")
    private String paymentFrequency;

    @Schema(description = "行政方式")
    private String administrationType;

    @Schema(description = "申请日期")
    private LocalDate applicationDate;

    @Schema(description = "保单生效日期")
    private LocalDate policyEffectDate;

    @Schema(description = "保障生效日期")
    private LocalDate ensureEffectDate;

    @Schema(description = "保障终止日期")
    private LocalDate ensureExpireDate;

    @Schema(description = "保单通过日期")
    private LocalDate policyAdoptionDate;

    @Schema(description = "报价单号")
    private String proposalNo;

    @Schema(description = "保单备注")
    private String policyRemark;

    @Schema(description = "PRP")
    private String policyPrp;

    @Schema(description = "下个保费到期日")
    private LocalDate ndd;

    @QueryProjection
    public PolicyOutput(String id, String policyNo, String policyStatus, String middleMan, String channel, String policyCurrency, BigDecimal yearPremium, String paymentFrequency, String administrationType, LocalDate applicationDate, LocalDate policyEffectDate, LocalDate ensureEffectDate, LocalDate ensureExpireDate, LocalDate policyAdoptionDate, String proposalNo, String policyRemark, String policyPrp, LocalDate ndd) {
        this.id = id;
        this.policyNo = policyNo;
        this.policyStatus = policyStatus;
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
        this.policyRemark = policyRemark;
        this.policyPrp = policyPrp;
        this.ndd = ndd;
    }
}
