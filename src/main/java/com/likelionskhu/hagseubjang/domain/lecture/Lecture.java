package com.likelionskhu.hagseubjang.domain.lecture;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.likelionskhu.hagseubjang.domain.review.Review;
import com.likelionskhu.hagseubjang.domain.wish.Wish;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
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
    @Column(length = 1000)
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
    private Long remainDay;
    private Boolean inWish;
    private double score;

    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "lecture")
    private List<Review> reviews;

    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "lecture")
    private List<Wish> wishes;

    @Builder
    public Lecture(
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
            String insttCode,
            Long remainDay,
            Boolean inWish,
            double score
    ) {
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
        this.remainDay = remainDay;
        this.inWish = inWish;
        this.score = score;
    }

    @Override
    public String toString() {
        return "Lecture{" +
                "id=" + id +
                ", lctreNm='" + lctreNm + '\'' +
                ", instrctrNm='" + instrctrNm + '\'' +
                ", edcStartDay=" + edcStartDay +
                ", edcEndDay=" + edcEndDay +
                ", edcStartTime='" + edcStartTime + '\'' +
                ", edcCloseTime='" + edcCloseTime + '\'' +
                ", lctreCo='" + lctreCo + '\'' +
                ", edcTrgetType='" + edcTrgetType + '\'' +
                ", edcMthType='" + edcMthType + '\'' +
                ", operDay='" + operDay + '\'' +
                ", edcPlace='" + edcPlace + '\'' +
                ", psncpa='" + psncpa + '\'' +
                ", lctreCost='" + lctreCost + '\'' +
                ", edcRdnmadr='" + edcRdnmadr + '\'' +
                ", operInstitutionNm='" + operInstitutionNm + '\'' +
                ", operPhoneNumber='" + operPhoneNumber + '\'' +
                ", rceptStartDate=" + rceptStartDate +
                ", rceptEndDate=" + rceptEndDate +
                ", rceptMthType='" + rceptMthType + '\'' +
                ", slctnMthType='" + slctnMthType + '\'' +
                ", homepageUrl='" + homepageUrl + '\'' +
                ", oadtCtLctreYn=" + oadtCtLctreYn +
                ", pntBankAckestYn=" + pntBankAckestYn +
                ", lrnAcnutAckestYn=" + lrnAcnutAckestYn +
                ", referenceDate=" + referenceDate +
                ", insttCode='" + insttCode + '\'' +
                '}';
    }
}
