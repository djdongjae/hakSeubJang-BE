package com.likelionskhu.hagseubjang.domain.lecture;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@NoArgsConstructor
@Entity
public class Lecture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String lctreNm;
    private String instrctrNm;
    private LocalDate edcStartDay;
    private LocalDate edcEndDay;
    private String edcStartTime;
    private String edcCloseTime;
    private String lctreCo;
    private String edcTrgetType;
    private String edcMthType;
    private String operDay;
    private String edcPlace;
    private String psncpa;
    private String lctreCost;
    private String edcRdnmadr;
    private String operInstitutionNm;
    private String operPhoneNumber;
    private LocalDate rceptStartDate;
    private LocalDate rceptEndDate;
    private String rceptMthType;
    private String slctnMthType;
    private String homepageUrl;
    private Boolean oadtCtLctreYn;
    private Boolean pntBankAckestYn;
    private Boolean lrnAcnutAckestYn;
    private LocalDate referenceDate;
    private String insttCode;

    @Builder
    public Lecture(
            int id,
            String lctreNm,
            String instrctrNm,
            LocalDate edcStartDay,
            LocalDate edcEndDay,
            String edcStartTime,
            String edcCloseTime,
            String lctreCo,
            String edcTrgetType,
            String edcMthType,
            String operDay,
            String edcPlace,
            String psncpa,
            String lctreCost,
            String edcRdnmadr,
            String operInstitutionNm,
            String operPhoneNumber,
            LocalDate rceptStartDate,
            LocalDate rceptEndDate,
            String rceptMthType,
            String slctnMthType,
            String homepageUrl,
            Boolean oadtCtLctreYn,
            Boolean pntBankAckestYn,
            Boolean lrnAcnutAckestYn,
            LocalDate referenceDate,
            String insttCode
    ) {
        this.id = id;
        this.lctreNm = lctreNm;
        this.instrctrNm = instrctrNm;
        this.edcStartDay = edcStartDay;
        this.edcEndDay = edcEndDay;
        this.edcStartTime = edcStartTime;
        this.edcCloseTime = edcCloseTime;
        this.lctreCo = lctreCo;
        this.edcTrgetType = edcTrgetType;
        this.edcMthType = edcMthType;
        this.operDay = operDay;
        this.edcPlace = edcPlace;
        this.psncpa = psncpa;
        this.lctreCost = lctreCost;
        this.edcRdnmadr = edcRdnmadr;
        this.operInstitutionNm = operInstitutionNm;
        this.operPhoneNumber = operPhoneNumber;
        this.rceptStartDate = rceptStartDate;
        this.rceptEndDate = rceptEndDate;
        this.rceptMthType = rceptMthType;
        this.slctnMthType = slctnMthType;
        this.homepageUrl = homepageUrl;
        this.oadtCtLctreYn = oadtCtLctreYn;
        this.pntBankAckestYn = pntBankAckestYn;
        this.lrnAcnutAckestYn = lrnAcnutAckestYn;
        this.referenceDate = referenceDate;
        this.insttCode = insttCode;
    }
}
