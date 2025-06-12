package com.example.doglogbe.service;

import com.example.doglogbe.entity.PetHospital;
import com.example.doglogbe.enums.PetHospitalBusiness;
import com.example.doglogbe.repository.PetHospitalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import com.example.doglogbe.util.CommonFile;


@Service
@RequiredArgsConstructor
public class PetHospitalService {
    private final PetHospitalRepository petHospitalRepository;

    public void setPetHospitalByFile(MultipartFile multipartFile) throws IOException {
        File file = CommonFile.multipartToFile(multipartFile);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file)); // 파일 문자 스트림 생성 및 버퍼 추가

        // 버퍼에서 넘어온 string 한줄을 임시로 담아 둘 변수가 필요하다
        String line= "";
        // 줄 번호 수동체크 하기 위해 int로 줄 번호 1씩 증가해서 기록해 둘 변수가 필요하다
        int index = 0;

        while ((line = bufferedReader.readLine()) != null) { // 반복문을 돌려서 한줄씩 처리
            if(index > 0){
                String[] cols = line.split(",");
                PetHospital petHospital = new PetHospital();
                if(cols[10] == "정상" || cols[10] == "휴업"){
//                    petHospital.setAnimalHospitalName(cols[27]);
//                    petHospital.setPhoneNumber(cols[15]);
//                    petHospital.setPostAddress(cols[18]);
//                    petHospital.setRoadNameAddress(cols[19]);
//                    petHospital.setPostCode(cols[26]);
//                    petHospital.setLatitude(Double.parseDouble(cols[32]));
//                    petHospital.setLongitude(Double.parseDouble(cols[33]));
//                    if(cols[10] == "정상"){
//                        petHospital.setBusiness(PetHospitalBusiness.영업);
//                        petHospital.setIsEnabled(true);
//                    }else if(cols[10] == "휴업"){
//                        petHospital.setBusiness(PetHospitalBusiness.휴업);
//                        petHospital.setIsEnabled(false);
//                    }

//
                }
                System.out.println(line);
            }
            index++;
        }
        bufferedReader.close(); // buffer 읽기 종료
    }
}
