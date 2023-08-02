package com.likelionskhu.hagseubjang.web;

import com.likelionskhu.hagseubjang.domain.lecture.Lecture;
import com.likelionskhu.hagseubjang.domain.lecture.LectureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.Reader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
public class LectureController {

    @Autowired
    private LectureRepository lectureRepository;

    @RequestMapping("load_save")
    public String load_save() throws Exception {
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

            if (edcEndDay2.isAfter(LocalDate.of(2023, 1, 1)) && referenceDate2.isAfter(LocalDate.of(2022, 6, 6))) {
                Lecture lectureInfo = new Lecture(
                        i + 1,
                        (String) tmp.get("강좌명"),
                        (String) tmp.get("강사명"),
                        edcStartDay2,
                        edcEndDay2,
                        (String) tmp.get("교육시작시각"),
                        (String) tmp.get("교육종료시각"),
                        (String) tmp.get("강좌내용"),
                        (String) tmp.get("교육대상구분"),
                        (String) tmp.get("교육방법구분"),
                        (String) tmp.get("운영요일"),
                        (String) tmp.get("교육장소"),
                        (String) tmp.get("강좌정원수"),
                        (String) tmp.get("수강료"),
                        (String) tmp.get("교육장도로명주소"),
                        (String) tmp.get("운영기관명"),
                        (String) tmp.get("운영기관전화번호"),
                        rceptStartDate2,
                        rceptEndDate2,
                        (String) tmp.get("접수방법구분"),
                        (String) tmp.get("선정방법구분"),
                        (String) tmp.get("홈페이지주소"),
                        tmp.get("직업능력개발훈련비지원강좌여부").equals("Y"),
                        tmp.get("학점은행제평가(학점)인정여부").equals("Y"),
                        tmp.get("평생학습계좌제평가인정여부").equals("Y"),
                        referenceDate2,
                        (String) tmp.get("제공기관코드")
                );
                lectureRepository.save(lectureInfo);
            }

        }
        return "success";

    }
}
