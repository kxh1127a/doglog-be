package com.example.doglogbe.service;

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
        boolean deleted = file.delete();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

        // 버퍼에서 넘어온 string 한줄을 임시로 담아 둘 변수가 필요하다
        String line= "";
        // 줄 번호 수동체크 하기 위해 int로 줄 번호 1씩 증가해서 기록해 둘 변수가 필요하다
        int index = 0;

        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);


            index++;
        }

        bufferedReader.close();
    }
}
