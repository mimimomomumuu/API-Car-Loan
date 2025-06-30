package m1.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import m1.api.entity.InterestRate;
import m1.api.model.InstallmentOptionDTO;
import m1.api.repository.InterestRateRepository;
import m1.api.request.LoanCalculationRequest;
import m1.api.response.LoanCalculationResponse;

@Service
@Transactional
public class LoanCalculationServiceImpl implements LoanCalculationService {
    
    @Autowired
    InterestRateRepository interestRateRepository;

    @Override
    public LoanCalculationResponse loanCalculation(LoanCalculationRequest request) {

        LoanCalculationResponse response = new LoanCalculationResponse();

        // 1. คำนวณ "ยอดจัดไฟแนนซ์" (Loan Amount)
        double carPrice = request.getCarPrice();
        double downPayment = request.getDownPayment();
        double loanAmount = carPrice - downPayment;

        // 1.1 ตรวจสอบยอดจัดไฟแนนซ์เบื้องต้น
        if (loanAmount <= 0) {
            response.setErrorCode("4002");
            response.setErrorMessage("เงินดาวน์ไม่ถูกต้อง หรือยอดจัดสินเชื่อเป็นศูนย์/ติดลบ");
            return response;
        }

        // 2. ดึงรายการอัตราดอกเบี้ยทั้งหมดจาก Database
        List<InterestRate> allInterestRates = interestRateRepository.findAll();

        // 3. กรองข้อมูล InterestRate ตาม modelYear ที่ได้รับมา
        int requestModelYear = request.getModelYear();
        List<InterestRate> filteredRates = allInterestRates.stream()
                .filter(rate -> requestModelYear >= rate.getYearFrom() && requestModelYear <= rate.getYearTo())
                .collect(Collectors.toList());

        // 4. จัดการกรณีไม่พบอัตราดอกเบี้ยที่ตรงกับ modelYear
        if (filteredRates.isEmpty()) {
            response.setErrorCode("4001");
            response.setErrorMessage("ไม่พบข้อมูลอัตราดอกเบี้ยสำหรับปีรถที่ระบุ (" + requestModelYear + ")");
            return response;
        }

        // 5. วนลูปคำนวณแต่ละงวด สำหรับ InterestRate แต่ละตัวที่ถูกกรองมาได้
        List<InstallmentOptionDTO> installmentOptions = new ArrayList<>();

        for (InterestRate rate : filteredRates) {
            // ดึงค่าที่จำเป็นสำหรับการคำนวณ
            int installmentMonths = rate.getInstallmentMonths(); // จำนวนงวด
            double annualInterestRate = rate.getRate(); // อัตราดอกเบี้ยต่อปี (เป็น %)

            // คำนวณ "จำนวนปี"
            // ใช้ 12.0 เพื่อให้ผลลัพธ์เป็น double เสมอ ไม่ใช่ integer division
            double numberOfYears = (double) installmentMonths / 12.0;

            // คำนวณ "ดอกเบี้ยทั้งหมด"
            // หาร 100.0 เพื่อแปลงอัตราดอกเบี้ยจาก % เป็นทศนิยม
            double totalInterest = loanAmount * (annualInterestRate / 100.0) * numberOfYears;

            // คำนวณ "ยอดชำระทั้งหมด"
            double totalPayment = loanAmount + totalInterest;

            // คำนวณ "ค่างวดรายเดือน"
            double monthlyPayment = totalPayment / installmentMonths;

            // สร้าง InstallmentOptionDTO พร้อมข้อมูลที่คำนวณได้
            InstallmentOptionDTO option = new InstallmentOptionDTO();
            option.setInstallmentMonths(installmentMonths);
            option.setMonthlyPayment(monthlyPayment);
            option.setTotalInterest(totalInterest);
            option.setTotalPayment(totalPayment);

            // เพิ่ม option เข้าไปใน List
            installmentOptions.add(option);
        }

        // 6. รวบรวม InstallmentOptionDTO ทั้งหมดไว้ใน List และส่งกลับใน LoanCalculationResponse
        response.setErrorCode("0000"); // กำหนดเป็นสำเร็จ
        response.setErrorMessage("สำเร็จ"); // ข้อความสำเร็จ
        response.setData(installmentOptions); // ส่ง List ของ InstallmentOptionDTO กลับไป

        return response;
    }
}
