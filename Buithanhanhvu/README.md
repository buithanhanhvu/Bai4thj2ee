# Dự án Bookstore - Quản lý Sách và Giỏ hàng

## Mô tả dự án
Đây là dự án web Spring Boot quản lý sách với chức năng giỏ hàng, kết nối với MySQL database.

## Công nghệ sử dụng
- **Java 17**
- **Spring Boot 4.0.2**
- **Spring Data JPA**
- **MySQL Database**
- **Thymeleaf Template Engine**
- **Lombok**
- **Bootstrap 5**

## Cấu trúc dự án
```
Buithanhanhvu/
├── src/
│   ├── main/
│   │   ├── java/nhom6/Buithanhanhvu/
│   │   │   ├── controllers/        # Các controller xử lý request
│   │   │   ├── daos/               # Data Access Objects (Cart, Item)
│   │   │   ├── entities/           # JPA Entities (Book, Category)
│   │   │   ├── repositories/       # JPA Repositories
│   │   │   ├── services/           # Business logic services
│   │   │   └── BuithanhanhvuApplication.java
│   │   └── resources/
│   │       ├── application.properties  # Cấu hình database
│   │       ├── static/                 # CSS, JS files
│   │       └── templates/              # Thymeleaf templates
│   └── test/
├── pom.xml                   # Maven dependencies
└── database.sql             # SQL script khởi tạo database
```

## Các lỗi đã được sửa

### 1. ❌ Thiếu dependencies trong pom.xml
**Đã thêm:**
- `spring-boot-starter-data-jpa` - Hỗ trợ JPA/Hibernate
- `mysql-connector-j` - MySQL connector

### 2. ❌ Cấu hình database sai
**Đã sửa trong application.properties:**
- Port MySQL từ `8080` → `3306`
- Sửa lỗi format và line breaks

### 3. ❌ Import sai package
**Tất cả các file đang import từ `fit.hutech.spring.*` → đã sửa thành `nhom6.Buithanhanhvu.*`:**
- IBookRepository.java
- ICategoryRepository.java  
- BookService.java
- CartService.java
- BookController.java

### 4. ❌ Thiếu imports cần thiết
**Đã thêm:**
- `java.util.List` trong IBookRepository
- `PageRequest`, `Sort` trong IBookRepository

### 5. ❌ BookController có lỗi cú pháp
**Đã sửa:**
- Thêm CartService dependency
- Sửa lại cấu trúc class đúng
- Thêm đầy đủ các method cho giỏ hàng

### 6. ❌ Thiếu CategoryService
**Đã tạo mới:** CategoryService.java với đầy đủ CRUD operations

### 7. ❌ Template HTML chưa hoàn chỉnh
**Đã sửa:**
- Thêm form "Add to Cart" trong list.html
- Sửa các route từ `/cart/*` → `/books/*`
- Thêm link Cart vào navigation menu
- Thêm null check cho category

## Hướng dẫn cài đặt và chạy dự án

### Bước 1: Cài đặt MySQL
1. Tải và cài đặt MySQL Server từ https://dev.mysql.com/downloads/
2. Khởi động MySQL service

### Bước 2: Tạo Database
Chạy file `database.sql` trong MySQL:

```bash
mysql -u root -p < database.sql
```

Hoặc mở MySQL Workbench/phpMyAdmin và import file `database.sql`

### Bước 3: Cấu hình Database
Kiểm tra file `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/bookstore
spring.datasource.username=root
spring.datasource.password=
```

**Lưu ý:** Nếu MySQL của bạn có password, hãy thêm vào dòng `spring.datasource.password=`

### Bước 4: Build project
```bash
mvn clean install
```

### Bước 5: Chạy ứng dụng
```bash
mvn spring-boot:run
```

Hoặc chạy trực tiếp từ IDE (IntelliJ IDEA, Eclipse):
- Right click vào `BuithanhanhvuApplication.java`
- Chọn `Run 'BuithanhanhvuApplication'`

### Bước 6: Truy cập ứng dụng
Mở trình duyệt và truy cập:
- **Trang chủ:** http://localhost:8081/
- **Danh sách sách:** http://localhost:8081/books
- **Giỏ hàng:** http://localhost:8081/books/cart

**Lưu ý:** Ứng dụng chạy trên port 8081 (thay vì mặc định 8080)

## Chức năng của ứng dụng

### 1. Quản lý sách (Book Management)
- ✅ Xem danh sách sách (phân trang)
- ✅ Sắp xếp theo: ID, Title, Author, Price, Category
- ✅ Tìm kiếm sách
- ✅ Thêm sách mới
- ✅ Sửa thông tin sách
- ✅ Xóa sách

### 2. Giỏ hàng (Shopping Cart)
- ✅ Thêm sách vào giỏ hàng
- ✅ Xem giỏ hàng
- ✅ Cập nhật số lượng
- ✅ Xóa sách khỏi giỏ hàng
- ✅ Xóa toàn bộ giỏ hàng
- ✅ Tính tổng tiền tự động

### 3. Quản lý danh mục (Category Management)
- ✅ Liên kết sách với danh mục
- ✅ Hiển thị danh mục của mỗi sách

## API Endpoints

### Book Controller
- `GET /books` - Danh sách sách (có phân trang)
- `GET /books/cart` - Xem giỏ hàng
- `POST /books/add-to-cart` - Thêm vào giỏ hàng
- `POST /books/remove-from-cart` - Xóa khỏi giỏ hàng
- `POST /books/update-cart` - Cập nhật số lượng
- `POST /books/clear-cart` - Xóa toàn bộ giỏ hàng

### Home Controller
- `GET /` - Trang chủ

## Cấu trúc Database

### Bảng `category`
| Cột | Kiểu dữ liệu | Mô tả |
|-----|-------------|--------|
| id | BIGINT (PK) | ID danh mục |
| name | VARCHAR(50) | Tên danh mục |

### Bảng `book`
| Cột | Kiểu dữ liệu | Mô tả |
|-----|-------------|--------|
| id | BIGINT (PK) | ID sách |
| title | VARCHAR(50) | Tiêu đề sách |
| author | VARCHAR(50) | Tác giả |
| price | DOUBLE | Giá sách |
| category_id | BIGINT (FK) | ID danh mục |

## Dữ liệu mẫu
Database đã được khởi tạo với:
- **5 categories:** Công nghệ phần mềm, Hệ thống thông tin, An toàn thông tin, Mạng máy tính, Khoa học dữ liệu
- **6 books:** Các sách về công nghệ thông tin

## Xử lý lỗi thường gặp

### Lỗi: "Access denied for user 'root'@'localhost'"
**Giải pháp:** Thêm password MySQL vào `application.properties`

### Lỗi: "Unknown database 'bookstore'"
**Giải pháp:** Chạy file `database.sql` để tạo database

### Lỗi: "Port 8080 already in use"
**Giải pháp:** Thêm vào `application.properties`:
```properties
server.port=8081
```

### Lỗi: "Could not connect to MySQL"
**Giải pháp:** 
- Kiểm tra MySQL service đã chạy chưa
- Kiểm tra port 3306 có bị block không

## Tác giả
- Nhóm 6 - Dự án Bài tập lớn

## License
Dự án học tập - HUTECH University

---
**Lưu ý:** Dự án đã được sửa lỗi hoàn chỉnh và sẵn sàng chạy! 🚀
