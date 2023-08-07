package com.likelionskhu.hagseubjang.service;

import com.likelionskhu.hagseubjang.domain.lecture.Lecture;
import com.likelionskhu.hagseubjang.domain.lecture.LectureRepository;
import com.likelionskhu.hagseubjang.domain.review.Review;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RequiredArgsConstructor
@EnableScheduling
@Service
public class LectureService {

    private final LectureRepository lectureRepository;

    @Transactional
    public List<Lecture> findAll() {
        return lectureRepository.findAll();
    }

    @Transactional
    public Lecture findById(int id) {
        Lecture lecture = lectureRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 강좌가 없습니다. id=" + id));

        return lecture;
    }

    // 파일 데이터 이용하여 강좌 테이블 초기화 저장
    @Transactional
    public void loadSave() throws Exception {
        JSONParser parser = new JSONParser();
        Reader reader = new FileReader("/Users/dongjae/Desktop/lecture.json");
        JSONObject jsonObject = (JSONObject) parser.parse(reader);
        JSONArray records = (JSONArray) jsonObject.get("records");

        for (int i = 0; i < records.size(); i++) {
            JSONObject tmp = (JSONObject) records.get(i);

            String edcStartDay1 = ((String) tmp.get("교육시작일자")).length() == 0 ? "1000-01-01" : (String) tmp.get("교육시작일자");
            LocalDate edcStartDay2 = LocalDate.parse(edcStartDay1, DateTimeFormatter.ISO_DATE);

            String edcEndDay1 = ((String) tmp.get("교육종료일자")).length() == 0 ? "1000-01-01" : (String) tmp.get("교육종료일자");
            LocalDate edcEndDay2 = LocalDate.parse(edcEndDay1, DateTimeFormatter.ISO_DATE);

            String rceptStartDate1 = ((String) tmp.get("접수시작일자")).length() == 0 ? "1000-01-01" : (String) tmp.get("접수시작일자");
            LocalDate rceptStartDate2 = LocalDate.parse(rceptStartDate1, DateTimeFormatter.ISO_DATE);

            String rceptEndDate1 = ((String) tmp.get("접수종료일자")).length() == 0 ? "1000-01-01" : (String) tmp.get("접수종료일자");
            LocalDate rceptEndDate2 = LocalDate.parse(rceptEndDate1, DateTimeFormatter.ISO_DATE);

            String referenceDate1 = ((String) tmp.get("데이터기준일자")).length() == 0 ? "1000-01-01" : (String) tmp.get("데이터기준일자");
            LocalDate referenceDate2 = LocalDate.parse(referenceDate1, DateTimeFormatter.ISO_DATE);

            if (
                    edcEndDay2.isAfter(LocalDate.of(2023, 1, 1))
                            && referenceDate2.isAfter(LocalDate.of(2022, 6, 6))
            ) {
                Lecture lecture = Lecture.builder()
                        .lctreNm((String) tmp.get("강좌명"))
                        .instrctrNm((String) tmp.get("강사명"))
                        .edcStartDay(edcStartDay2)
                        .edcEndDay(edcEndDay2)
                        .edcStartTime((String) tmp.get("교육시작시각"))
                        .edcCloseTime((String) tmp.get("교육종료시각"))
                        .lctreCo((String) tmp.get("강좌내용"))
                        .edcTrgetType((String) tmp.get("교육대상구분"))
                        .edcMthType((String) tmp.get("교육방법구분"))
                        .operDay((String) tmp.get("운영요일"))
                        .edcPlace((String) tmp.get("교육장소"))
                        .psncpa((String) tmp.get("강좌정원수"))
                        .lctreCost((String) tmp.get("수강료"))
                        .edcRdnmadr((String) tmp.get("교육장도로명주소"))
                        .operInstitutionNm((String) tmp.get("운영기관명"))
                        .operPhoneNumber((String) tmp.get("운영기관전화번호"))
                        .rceptStartDate(rceptStartDate2)
                        .rceptEndDate(rceptEndDate2)
                        .rceptMthType((String) tmp.get("접수방법구분"))
                        .slctnMthType((String) tmp.get("선정방법구분"))
                        .homepageUrl((String) tmp.get("홈페이지주소"))
                        .oadtCtLctreYn(tmp.get("직업능력개발훈련비지원강좌여부").equals("Y"))
                        .pntBankAckestYn(tmp.get("학점은행제평가(학점)인정여부").equals("Y"))
                        .lrnAcnutAckestYn(tmp.get("평생학습계좌제평가인정여부").equals("Y"))
                        .referenceDate(referenceDate2)
                        .insttCode((String) tmp.get("제공기관코드"))
                        .build();

                lectureRepository.save(lecture);
            }
        }
    }

    // OpenAPI를 이용하여 매일 11시 55분에 새로운 강좌 추가
    @Scheduled(cron = "0 55 23 * * *")
    @Transactional
    public void update() throws Exception {

        LocalDate today = LocalDate.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String referenceDate = today.format(dateTimeFormatter);

        URL url = new URL(
                "http://api.data.go.kr/openapi/tn_pubr_public_lftm_lrn_lctre_api"
                        + "?serviceKey=U%2BFAmDElGz%2BqU3DnkL9KnewJSgBy7chmjHrqGFnZJIZF2DjSKEB0jHObepr4LW%2B5HVRVN%2BAPVVNdFfSle2m%2FBw%3D%3D"
                        + "&pageNo=1&numOfRows=100"
                        + "&type=json"
                        + "&referenceDate=" + referenceDate
        );

        BufferedReader bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
        String result = bf.readLine();

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(result);
        JSONObject response = (JSONObject) jsonObject.get("response");
        JSONObject header = (JSONObject) response.get("header");
        String resultCode = (String) header.get("resultCode");

        if (resultCode.equals("00")) {
            JSONObject body = (JSONObject) response.get("body");
            JSONArray items = (JSONArray) body.get("items");

            for (int i = 0; i < items.size(); i++) {
                JSONObject tmp = (JSONObject) items.get(i);

                String edcStartDay1 = ((String) tmp.get("edcStartDay")).length() == 0 ? "1000-01-01" : (String) tmp.get("edcStartDay");
                LocalDate edcStartDay2 = LocalDate.parse(edcStartDay1, DateTimeFormatter.ISO_DATE);

                String edcEndDay1 = ((String) tmp.get("edcEndDay")).length() == 0 ? "1000-01-01" : (String) tmp.get("edcEndDay");
                LocalDate edcEndDay2 = LocalDate.parse(edcEndDay1, DateTimeFormatter.ISO_DATE);

                String rceptStartDate1 = ((String) tmp.get("rceptStartDate")).length() == 0 ? "1000-01-01" : (String) tmp.get("rceptStartDate");
                LocalDate rceptStartDate2 = LocalDate.parse(rceptStartDate1, DateTimeFormatter.ISO_DATE);

                String rceptEndDate1 = ((String) tmp.get("rceptEndDate")).length() == 0 ? "1000-01-01" : (String) tmp.get("rceptEndDate");
                LocalDate rceptEndDate2 = LocalDate.parse(rceptEndDate1, DateTimeFormatter.ISO_DATE);

                String referenceDate1 = ((String) tmp.get("referenceDate")).length() == 0 ? "1000-01-01" : (String) tmp.get("referenceDate");
                LocalDate referenceDate2 = LocalDate.parse(referenceDate1, DateTimeFormatter.ISO_DATE);

                Lecture lecture = Lecture.builder()
                        .lctreNm((String) tmp.get("lctreNm"))
                        .instrctrNm((String) tmp.get("instrctrNm"))
                        .edcStartDay(edcStartDay2)
                        .edcEndDay(edcEndDay2)
                        .edcStartTime((String) tmp.get("edcStartTime"))
                        .edcCloseTime((String) tmp.get("edcColseTime"))
                        .lctreCo((String) tmp.get("lctreCo"))
                        .edcTrgetType((String) tmp.get("edcTrgetType"))
                        .edcMthType((String) tmp.get("edcMthType"))
                        .operDay((String) tmp.get("operDay"))
                        .edcPlace((String) tmp.get("edcPlace"))
                        .psncpa((String) tmp.get("psncpa"))
                        .lctreCost((String) tmp.get("lctreCost"))
                        .edcRdnmadr((String) tmp.get("edcRdnmadr"))
                        .operInstitutionNm((String) tmp.get("operInstitutionNm"))
                        .operPhoneNumber((String) tmp.get("operPhoneNumber"))
                        .rceptStartDate(rceptStartDate2)
                        .rceptEndDate(rceptEndDate2)
                        .rceptMthType((String) tmp.get("rceptMthType"))
                        .slctnMthType((String) tmp.get("slctnMthType"))
                        .homepageUrl((String) tmp.get("homepageUrl"))
                        .oadtCtLctreYn(tmp.get("oadtCtLctreYn").equals("Y"))
                        .pntBankAckestYn(tmp.get("pntBankAckestYn").equals("Y"))
                        .lrnAcnutAckestYn(tmp.get("lrnAcnutAckestYn").equals("Y"))
                        .referenceDate(referenceDate2)
                        .insttCode((String) tmp.get("insttCode"))
                        .build();

                lectureRepository.save(lecture);
            }
        }
    }

}
