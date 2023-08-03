package com.likelionskhu.hagseubjang.web;

import com.likelionskhu.hagseubjang.domain.lecture.Lecture;
import com.likelionskhu.hagseubjang.domain.lecture.LectureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
public class LectureController {

    @Autowired
    private LectureRepository lectureRepository;

    // 파일 데이터로 강좌 초기화
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

            if (
                    edcEndDay2.isAfter(LocalDate.of(2023, 1, 1))
                            && referenceDate2.isAfter(LocalDate.of(2022, 6, 6))
            ) {
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

    // api로 강좌 업데이트
    public void lecture_update() throws Exception {
        String result;

        LocalDate today = LocalDate.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String referenceDate = today.format(dateTimeFormatter);

        URL url = new URL(
                "http://api.data.go.kr/openapi/tn_pubr_public_lftm_lrn_lctre_api"
                + "?serviceKey=U%2BFAmDElGz%2BqU3DnkL9KnewJSgBy7chmjHrqGFnZJIZF2DjSKEB0jHObepr4LW%2B5HVRVN%2BAPVVNdFfSle2m%2FBw%3D%3D"
                + "&pageNo=1&numOfRows=100"
                + "&type=json"
                + "referenceDate=" + referenceDate
        );

        BufferedReader bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
        result = bf.readLine();

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
                
            }
        }
    }
}
