package com.example.doglogbe.service;

import com.example.doglogbe.entity.PetHospital;
import com.example.doglogbe.enums.PetHospitalBusiness;
import com.example.doglogbe.repository.PetHospitalRepository;
import com.example.doglogbe.util.CoordinateConverter;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;
import com.example.doglogbe.util.CommonFile;


@Service
@RequiredArgsConstructor
public class PetHospitalService {
    private final PetHospitalRepository petHospitalRepository;

    public void setPetHospitalByFile(MultipartFile multipartFile) throws IOException, CsvException {
        File file = CommonFile.multipartToFile(multipartFile);
        CSVReader csvReader = new CSVReader(new FileReader(file));
        List<String[]> rows = csvReader.readAll(); // 전체 CSV를 리스트로 저장

        for (int index = 1; index < rows.size(); index++) {
            String[] cols = rows.get(index);

            if (cols.length >= 28) {
                String status = cols[10].trim(); // 공백을 제거해준다
                if (status.equals("정상") || status.equals("휴업")) {
                    PetHospital petHospital = new PetHospital();
                    petHospital.setAnimalHospitalName(cols[21]);               // 사업장명
                    petHospital.setPhoneNumber(cols[15]);                      // 소재지전화
                    petHospital.setPostAddress(cols[18]);                      // 소재지전체주소
                    petHospital.setRoadNameAddress(cols[19]);                  // 도로명전체주소
                    petHospital.setPostCode(cols[20]);                         // 도로명우편번호
                    // epsg5174를 위도, 경도로 변환
                    if(cols[26] == "" || cols[27] == "") {
                        petHospital.setLatitude(null);
                        petHospital.setLongitude(null);
                    }else{
                        double[] latLng = CoordinateConverter.convert5174ToWGS84(Double.parseDouble(cols[26]), Double.parseDouble(cols[27]));
                        petHospital.setLatitude(latLng[0]);     // 위도
                        petHospital.setLongitude(latLng[1]);    // 경도
//                        String result = String.join(" | ",
//                                cols[21], cols[15], cols[18], cols[19], cols[20], Double.toString(latLng[0]), Double.toString(latLng[1]), cols[10]
//                        );
//                        System.out.println(result); // console에서 결과값 확인
                    }
                    if(status.equals("정상")){
                        petHospital.setBusiness(PetHospitalBusiness.영업);
                        petHospital.setIsEnabled(true);
                    }else if(status.equals("휴업")){
                        petHospital.setBusiness(PetHospitalBusiness.휴업);
                        petHospital.setIsEnabled(false);
                    }
                    petHospitalRepository.save(petHospital);
                }
            }
        }
        csvReader.close();
    }
}
