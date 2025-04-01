package ra.validate;

import java.util.Scanner;
import java.util.regex.Pattern;

public class CustomerValidator {
    // Xác thực tên
    public String validateFirstName(Scanner sc) {
        String firstName;
        while (true) {
            firstName = sc.nextLine().trim();
            if (firstName.isEmpty()) {
                System.out.print("Tên không được để trống. Vui lòng nhập lại: ");
            } else if (firstName.length() > 50) {
                System.out.print("Tên không được vượt quá 50 ký tự. Vui lòng nhập lại: ");
            } else {
                return firstName;
            }
        }
    }

    // Xác thực họ
    public String validateLastName(Scanner sc) {
        String lastName;
        while (true) {
            lastName = sc.nextLine().trim();
            if (lastName.isEmpty()) {
                System.out.print("Họ không được để trống. Vui lòng nhập lại: ");
            } else if (lastName.length() > 30) {
                System.out.print("Họ không được vượt quá 30 ký tự. Vui lòng nhập lại: ");
            } else {
                return lastName;
            }
        }
    }

    // Xác thực dateOfBirth (ko rỗng, format dd/MM/yyyy)
    public String validateDateOfBirth(Scanner sc) {
        String dateOfBirth;
        // Mẫu biểu thức chính quy cho định dạng dd/MM/yyyy
        String dateRegex = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/(19|20)\\d\\d$";

        while (true) {
            dateOfBirth = sc.nextLine().trim();
            if (dateOfBirth.isEmpty()) {
                System.out.print("Ngày sinh không được để trống. Vui lòng nhập lại (dd/MM/yyyy): ");
            } else if (!Pattern.matches(dateRegex, dateOfBirth)) {
                System.out.print("Định dạng ngày sinh không đúng. Vui lòng nhập lại (dd/MM/yyyy): ");
            } else {
                // Xác thực bổ sung tùy chọn cho các ngày hợp lệ (ví dụ: 31/04 không hợp lệ)
                if (isValidDate(dateOfBirth)) {
                    return dateOfBirth;
                } else {
                    System.out.print("Ngày không hợp lệ. Vui lòng nhập lại (dd/MM/yyyy): ");
                }
            }
        }
    }

    // Phương pháp trợ giúp để kiểm tra xem ngày có hợp lệ hay không (ví dụ: không có ngày 31/02)
    private boolean isValidDate(String dateStr) {
        try {
            String[] parts = dateStr.split("/");
            int day = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);
            int year = Integer.parseInt(parts[2]);

            // Kiểm tra tháng với 30 ngày
            if (month == 4 || month == 6 || month == 9 || month == 11) {
                return day <= 30;
            }
            // Kiểm tra cho tháng Hai
            else if (month == 2) {
                // Kiểm tra năm nhuận
                boolean isLeapYear = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
                return isLeapYear ? day <= 29 : day <= 28;
            }

            return true; // Áp dụng cho các tháng khác với 31 ngày
        } catch (Exception e) {
            return false;
        }
    }

    // Xác thực giói tính
    public boolean validateGender(Scanner sc) {
        while (true) {
            String input = sc.nextLine().trim().toLowerCase();
            if (input.equals("true") || input.equals("nam") || input.equals("1")) {
                return true;
            } else if (input.equals("false") || input.equals("nữ") || input.equals("nu") || input.equals("0")) {
                return false;
            } else {
                System.out.print("Giá trị không hợp lệ. Vui lòng nhập lại (true - Nam, false - Nữ): ");
            }
        }
    }

    // Xác thự địa chỉ
    public String validateAddress(Scanner sc) {
        String address;
        while (true) {
            address = sc.nextLine().trim();
            if (address.isEmpty()) {
                System.out.print("Địa chỉ không được để trống. Vui lòng nhập lại: ");
            } else if (address.length() > 255) {
                System.out.print("Địa chỉ không được vượt quá 255 ký tự. Vui lòng nhập lại: ");
            } else {
                return address;
            }
        }
    }

    // Xác thự SDT (Định dạng sdt chuẩn VN)
    public String validatePhoneNumber(Scanner sc) {
        String phoneNumber;
        // Biểu thức chính quy cho SDT
        String phoneRegex = "^(0|\\+84)(3|5|7|8|9)\\d{8}$";

        while (true) {
            phoneNumber = sc.nextLine().trim();
            if (phoneNumber.isEmpty()) {
                System.out.print("Số điện thoại không được để trống. Vui lòng nhập lại: ");
            } else if (!Pattern.matches(phoneRegex, phoneNumber)) {
                System.out.print("Số điện thoại không đúng định dạng Việt Nam. Vui lòng nhập lại: ");
            } else {
                return phoneNumber;
            }
        }
    }

    // Xác thực email (định dạng gmail)
    public String validateEmail(Scanner sc) {
        String email;
        // Biểu thức chính quy cho Gmail
        String emailRegex = "^[a-zA-Z0-9._%+-]+@gmail\\.com$";

        while (true) {
            email = sc.nextLine().trim();
            if (email.isEmpty()) {
                System.out.print("Email không được để trống. Vui lòng nhập lại: ");
            } else if (!Pattern.matches(emailRegex, email)) {
                System.out.print("Email không đúng định dạng Gmail. Vui lòng nhập lại: ");
            } else {
                return email;
            }
        }
    }

    // Xác thực loại khách hàng
    public String validateCustomerType(Scanner sc) {
        String customerType;
        while (true) {
            customerType = sc.nextLine().trim();
            if (customerType.isEmpty()) {
                System.out.print("Loại khách hàng không được để trống. Vui lòng nhập lại: ");
            } else {
                return customerType;
            }
        }
    }

    // Tạo ID theo định dạng
    public String generateCustomerId() {
        // Generate a random 4-digit number
        int randomNum = (int) (Math.random() * 9000) + 1000;
        return "C" + randomNum;
    }

    // Kiểm tra số kượng khách hàng thêm vào
    public int validateCustomerCount(Scanner sc) {
        int count;
        while (true) {
            try {
                count = Integer.parseInt(sc.nextLine().trim());
                if (count <= 0) {
                    System.out.print("Số lượng khách hàng phải lớn hơn 0. Vui lòng nhập lại: ");
                } else {
                    return count;
                }
            } catch (NumberFormatException e) {
                System.out.print("Vui lòng nhập một số nguyên dương: ");
            }
        }
    }
}