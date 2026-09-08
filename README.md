# IT214_Session04_Baitap02: Thiết kế lớp cấu trúc dữ liệu bóc tách phòng thủ

Bối cảnh: Hệ thống CRM cần bóc tách các tin nhắn thô gửi về từ tài xế thành thông tin có cấu trúc. 
Bài toán yêu cầu lựa chọn phương án lưu trữ thông tin bóc tách giữa việc map trực tiếp vào Entity hoặc qua lớp trung gian DTO.

## 1. Phân tích so sánh hai phương án

### Phương án 1: Dùng trực tiếp JPA Entity (`IncidentReport`) làm đối tượng đích cho AI bóc tách
**Ưu điểm:**
- Phát triển nhanh (ít lớp hơn).
- Không cần viết mapper.

**Khuyết điểm (Vi phạm nghiêm trọng Lập trình phòng thủ):**
- **Lifecycle của Entity bị phá vỡ:** Entity của Hibernate/JPA bắt buộc phải có Default Constructor hoặc Constructor không tham số. Nếu AI tạo trực tiếp Entity, nó có thể tạo ra các Entity ở "trạng thái không hợp lệ" (vd: thiếu ID, thiếu các trường required non-null).
- **ID Auto-generated & Mutability:** JPA cho phép các ID tự sinh (`@GeneratedValue`), nhưng khi gắn `@Setter` cho mọi trường để thư viện (như `BeanOutputConverter`) có thể mapping, ta đã vô tình cho phép thay đổi ID hoặc thay đổi các thuộc tính bất thành văn (vd: `createdAt`) từ bên ngoài.
- **Ràng buộc Nullable (Constraints):** Dữ liệu trả về qua bóc tách LLM có độ không ổn định (Hallucination cao). Nếu đẩy thẳng vào Database qua Entity, có thể gây ra exception `DataIntegrityViolationException` lúc save, làm crash luồng xử lý hoặc lưu rác vào Database nếu không cẩn thận.
- **Vi phạm tính đóng gói:** Entity đáng lẽ chỉ chứa business logic nội tại và state validation, việc gán trực tiếp dữ liệu thô đẩy trách nhiệm kiểm tra tính toàn vẹn dữ liệu từ lớp ngoài vào mạn trong, phá hỏng tính chặt chẽ của OOP.

### Phương án 2: Dùng DTO (`IncidentExtraction` Record) trung gian (Phương án tối ưu)
**Ưu điểm:**
- **Lập trình phòng thủ (Defensive Programming):** Record là Immutable (bất biến) và DTO đóng vai trò như một màng lọc dữ liệu bẩn (Sanitization boundary) trước khi đưa vào Domain logic.
- **Cách li (Decoupling):** Tách biệt thay đổi của định dạng AI output ra khỏi cấu trúc Table của Database. Nếu AI trả về tên field mới, ta chỉ việc sửa DTO mà không ảnh hưởng JPA Entity.
- **Đảm bảo tính toàn vẹn của Entity (Encapsulation):** JPA Entity chỉ cần public các Getter và expose 1 Factory method (`create()`) hoặc Builder có chứa Guard Clauses. Khi đó mọi Instance hợp lệ của Entity sẽ LUÔN ở trạng thái đúng đắn 100% về mặt nghiệp vụ. Ta có thể bỏ đi được cấu trúc `Setter` gây rủi ro.

**Khuyết điểm:**
- Cần viết thêm Service để Map từ DTO sang Entity. (Được bù đắp hoàn toàn bởi sự an toàn của toàn bộ hệ thống).

### Kết luận
**Phương án 2** đáp ứng chuẩn mực thiết kế an toàn và đáng tin cậy.

## 2. Cấu trúc Source Code

Dự án bao gồm các thành phần sau: 
- `dto/IncidentExtraction.java`: Dùng Java Record làm DTO biểu diễn dữ liệu bóc tách. Đã tích hợp validation (Fail-fast) cơ bản cho field bắt buộc.
- `entity/IncidentReport.java`: Lớp JPA Entity được thiết kế đóng gói cực kỳ chặt chẽ:
  + Dùng constructor `protected` để thỏa mãn JPA Default Constructor.
  + Không có bất kỳ Setter nào để đảm bảo Immutable Data Lifecycle sau khi tạo.
  + Sử dụng Factory Pattern `IncidentReport.create(...)` chứa các Guard Clauses kiểm tra ràng buộc trước khi khởi tạo.
- `service/IncidentMappingService.java`: Service mapping từ DTO sang Entity, có bắt `NumberFormatException` phòng thủ.

## 3. Minh chứng chạy thực tế không lỗi Runtime
Output từ Console Log khi chạy:
```text
===============================
TESTING INCIDENT EXTRACTION TO JPA ENTITY
[1] Created DTO (Record): IncidentExtraction[driverId=DRV-1337, incidentType=TRAFFIC_JAM, locationLatitude=21.028511, locationLongitude=105.804817, description=Heavy traffic near intersection, rawMessageTime=2026-09-08T08:15:30]
[2] Mapped to JPA Entity: IncidentReport{id=null, driverId='DRV-1337', incidentType='TRAFFIC_JAM', reportedAt=2026-09-08T08:15:30}
Entity Details -> Lat: 21.028511, Lng: 105.804817
SUCCESS: No runtime errors encountered during mapping!
===============================
```
