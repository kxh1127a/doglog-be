package com.example.doglogbe.service;

import com.example.doglogbe.entity.PetHospital;
import com.example.doglogbe.enums.PetHospitalBusiness;
import com.example.doglogbe.repository.PetHospitalRepository;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

import com.example.doglogbe.util.CommonFile;


@Service
@RequiredArgsConstructor
public class PetHospitalService {
    private final PetHospitalRepository petHospitalRepository;

    public void setPetHospitalByFile(MultipartFile multipartFile) throws IOException, CsvException {
        File file = CommonFile.multipartToFile(multipartFile);
//        BufferedReader bufferedReader = new BufferedReader(new FileReader(file)); // 파일 문자 스트림 생성 및 버퍼 추가
        CSVReader csvReader = new CSVReader(new FileReader(file));
        List<String[]> rows = csvReader.readAll(); // 전체 CSV를 리스트로

        // 버퍼에서 넘어온 string 한줄을 임시로 담아 둘 변수가 필요하다
        String line= "";
        // 줄 번호 수동체크 하기 위해 int로 줄 번호 1씩 증가해서 기록해 둘 변수가 필요하다
//        int index = 0;
//
//        while ((line = bufferedReader.readLine()) != null) { // 반복문을 돌려서 한줄씩 처리
//            if(index > 0){
//                String[] cols = line.split(",");
//                PetHospital petHospital = new PetHospital();
//                if(cols[10].equals("정상") || cols[10].equals("휴업")){
////                    petHospital.setAnimalHospitalName(cols[21]);               // 사업장명
////                    petHospital.setPhoneNumber(cols[15]);                      // 소재지전화
////                    petHospital.setPostAddress(cols[18]);                      // 소재지전체주소
////                    petHospital.setRoadNameAddress(cols[19]);                  // 도로명전체주소
////                    petHospital.setPostCode(cols[20]);                         // 도로명우편번호
////                    petHospital.setLatitude(Double.parseDouble(cols[26]));     // 위도
////                    petHospital.setLongitude(Double.parseDouble(cols[27]));    // 경도
////                    if(cols[10] == "정상"){
////                        petHospital.setBusiness(PetHospitalBusiness.영업);
////                        petHospital.setIsEnabled(true);
////                    }else if(cols[10] == "휴업"){
////                        petHospital.setBusiness(PetHospitalBusiness.휴업);
////                        petHospital.setIsEnabled(false);
////                    }
////                    System.out.println(cols[21]+cols[15]+cols[18]+cols[19]+cols[20]+cols[26]+cols[27]+cols[10]);
//                    String result = String.join(" | ",
//                            cols[21], cols[15], cols[18], cols[19], cols[20], cols[26], cols[27], cols[10]
//                    );
//                    System.out.println(result);
//                }
//            }
//            index++;
//        }
//        bufferedReader.close(); // buffer 읽기 종료

        for (int index = 1; index < rows.size(); index++) {
            String[] cols = rows.get(index);

            if (cols.length >= 28) {
                String status = cols[10].trim();
                if (status.equals("정상") || status.equals("휴업")) {
                    String result = String.join(" | ",
                            cols[21], cols[15], cols[18], cols[19], cols[20], cols[26], cols[27], cols[10]
                    );
                    System.out.println(result);
                }
            }
        }

        csvReader.close();
    }
}
