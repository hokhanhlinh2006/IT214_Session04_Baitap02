# Bài Tập 2: Xây Dựng 5 API Cơ Bản Cho Từng Microservice

Bệnh viện MediCare có 5 microservices độc lập được xây dựng bằng Spring Boot (Gradle). Mỗi service chạy trên cổng riêng và tự quản lý CSDL MySQL của mình theo đúng chuẩn Microservices.

## Danh Sách Các Services

| Tên Service | Port | Database (MySQL) | Bảng Chính (Entity) |
|-------------|------|-----------------------|-------------|
| `patient-service` | 8081 | `medicare_patient_db` | patients |
| `doctor-service` | 8082 | `medicare_doctor_db` | doctors |
| `appointment-service` | 8083 | `medicare_appointment_db` | appointments |
| `medical-record-service`| 8084 | `medicare_medical_record_db` | medical_records |
| `pharmacy-service`| 8085 | `medicare_pharmacy_db` | medications |

## Yêu Cầu Môi Trường
- **Java 21**
- **MySQL Server** đang chạy ở `localhost:3306` (Tài khoản: `root`, Mật khẩu: `root`).
- (Database sẽ tự động được Spring Boot tạo nhờ cờ `createDatabaseIfNotExist=true` mà tôi đã setup sẵn trên Connection String).

## Cách Chạy (Command Line / Gradle Wrapper)

Có thể chạy trực tiếp từng service bằng Gradle từ thư mục gốc của bài tập:

```sh
cd patient-service
./gradlew bootRun
```
*(Thực hiện tương tự cho 4 service còn lại ở các tab terminal khác nhau)*

## Kiểm Thử (Postman)

Tôi đã tạo sẵn thư mục `postman/` chứa file Collection: 
**`MediCare_Microservices.postman_collection.json`**. 

1. Tải ứng dụng [**Postman**](https://www.postman.com/downloads/).
2. Click **Import** và kéo thả file JSON kia vào.
3. Chạy thử các Request POST, GET, PUT, DELETE cho 5 resource đã được setup sẵn API body.

> **Lưu ý về Thư Mục Screenshots:** 
> Vui lòng sau khi import và test bằng Postman, hãy chụp ảnh màn hình và xếp vào thư mục `screenshots/` theo đúng yêu cầu đề bài của bạn trước khi nén nộp lại (Hoặc đẩy lại lên repo). Thư mục `screenshots/` hiện đã được tạo sẵn trống.
