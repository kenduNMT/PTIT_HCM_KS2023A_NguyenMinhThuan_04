package ra.bussiness;

import ra.entity.Customer;
import ra.validate.CustomerValidator;

import java.util.Scanner;

public class CustomerBusiness {
    private static final int MAX_CUSTOMERS = 100;
    private Customer[] customers;
    private int customerCount;
    private CustomerValidator validator;

    public CustomerBusiness() {
        customers = new Customer[MAX_CUSTOMERS];
        customerCount = 0;
        validator = new CustomerValidator();
    }

    // 1. Hiện danh sách KH
    public void displayAllCustomers() {
        if (customerCount == 0) {
            System.out.println("Danh sách trống");
            return;
        }

        System.out.println("=================== DANH SÁCH KHÁCH HÀNG ===================");
        for (int i = 0; i < customerCount; i++) {
            customers[i].displayData();
        }
    }

    // 2. Thêm KH
    public void addCustomers(Scanner sc) {
        if (customerCount >= MAX_CUSTOMERS) {
            System.out.println("Danh sách khách hàng đã đầy, không thể thêm mới.");
            return;
        }

        System.out.print("Nhập số lượng khách hàng cần thêm: ");
        int numToAdd = validator.validateCustomerCount(sc);

        if (customerCount + numToAdd > MAX_CUSTOMERS) {
            System.out.println("Không thể thêm " + numToAdd + " khách hàng. Chỉ còn " + (MAX_CUSTOMERS - customerCount) + " vị trí trống.");
            return;
        }

        for (int i = 0; i < numToAdd; i++) {
            System.out.println("\n===== Nhập thông tin khách hàng thứ " + (i + 1) + " =====");
            Customer customer = new Customer();

            // Tạo ID độc nhất
            String customerId;
            boolean isUnique;
            do {
                customerId = validator.generateCustomerId();
                isUnique = true;

                for (int j = 0; j < customerCount; j++) {
                    if (customers[j].getCustomerId().equals(customerId)) {
                        isUnique = false;
                        break;
                    }
                }
            } while (!isUnique);

            customer.setCustomerId(customerId);
            customer.inputData(sc);

            customers[customerCount] = customer;
            customerCount++;
            System.out.println("Thêm khách hàng " + customerId + " thành công!");
        }
    }

    // 3. Sửa thông tin KH
    public void editCustomer(Scanner sc) {
        if (customerCount == 0) {
            System.out.println("Danh sách khách hàng trống.");
            return;
        }

        System.out.print("Nhập mã khách hàng cần chỉnh sửa: ");
        String customerId = sc.nextLine().trim();

        int customerIndex = findCustomerById(customerId);

        if (customerIndex == -1) {
            System.out.println("Không tìm thấy khách hàng có mã " + customerId);
            return;
        }

        Customer customer = customers[customerIndex];
        System.out.println("Thông tin khách hàng hiện tại:");
        customer.displayData();

        boolean continueEditing = true;
        while (continueEditing) {
            System.out.println("\n===== MENU CHỈNH SỬA =====");
            System.out.println("1. Chỉnh sửa tên");
            System.out.println("2. Chỉnh sửa họ");
            System.out.println("3. Chỉnh sửa ngày sinh");
            System.out.println("4. Chỉnh sửa giới tính");
            System.out.println("5. Chỉnh sửa địa chỉ");
            System.out.println("6. Chỉnh sửa số điện thoại");
            System.out.println("7. Chỉnh sửa email");
            System.out.println("8. Chỉnh sửa loại khách hàng");
            System.out.println("0. Hoàn thành chỉnh sửa");
            System.out.print("Chọn thuộc tính cần sửa: ");

            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập một số nguyên.");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Nhập tên mới: ");
                    customer.setFirstName(validator.validateFirstName(sc));
                    break;
                case 2:
                    System.out.print("Nhập họ mới: ");
                    customer.setLastName(validator.validateLastName(sc));
                    break;
                case 3:
                    System.out.print("Nhập ngày sinh mới (dd/MM/yyyy): ");
                    customer.setDateOfBirth(validator.validateDateOfBirth(sc));
                    break;
                case 4:
                    System.out.print("Nhập giới tính mới (true - Nam, false - Nữ): ");
                    customer.setGender(validator.validateGender(sc));
                    break;
                case 5:
                    System.out.print("Nhập địa chỉ mới: ");
                    customer.setAddress(validator.validateAddress(sc));
                    break;
                case 6:
                    System.out.print("Nhập số điện thoại mới: ");
                    customer.setPhoneNumber(validator.validatePhoneNumber(sc));
                    break;
                case 7:
                    System.out.print("Nhập email mới: ");
                    customer.setEmail(validator.validateEmail(sc));
                    break;
                case 8:
                    System.out.print("Nhập loại khách hàng mới: ");
                    customer.setCustomerType(validator.validateCustomerType(sc));
                    break;
                case 0:
                    continueEditing = false;
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        }

        System.out.println("Chỉnh sửa thông tin khách hàng thành công!");
    }

    // 4. Xóa KH
    public void deleteCustomer(Scanner sc) {
        if (customerCount == 0) {
            System.out.println("Danh sách khách hàng trống.");
            return;
        }

        System.out.print("Nhập mã khách hàng cần xóa: ");
        String customerId = sc.nextLine().trim();

        int customerIndex = findCustomerById(customerId);

        if (customerIndex == -1) {
            System.out.println("Không tìm thấy khách hàng có mã " + customerId);
            return;
        }

        System.out.println("Thông tin khách hàng cần xóa:");
        customers[customerIndex].displayData();

        System.out.print("Bạn có chắc chắn muốn xóa khách hàng này? (Y/N): ");
        String confirm = sc.nextLine().trim().toUpperCase();

        if (confirm.equals("Y")) {
            // Chuyển tất cả khách hàng sau khi đã xóa
            for (int i = customerIndex; i < customerCount - 1; i++) {
                customers[i] = customers[i + 1];
            }
            customers[customerCount - 1] = null;
            customerCount--;

            System.out.println("Xóa khách hàng thành công!");
        } else {
            System.out.println("Đã hủy thao tác xóa.");
        }
    }

    // 5. Tìm kiếm KH
    public void searchCustomers(Scanner sc) {
        if (customerCount == 0) {
            System.out.println("Danh sách khách hàng trống.");
            return;
        }

        System.out.println("\n===== MENU TÌM KIẾM =====");
        System.out.println("1. Tìm kiếm theo tên khách hàng");
        System.out.println("2. Tìm kiếm theo loại khách hàng");
        System.out.println("3. Tìm kiếm theo số điện thoại");
        System.out.print("Chọn tiêu chí tìm kiếm: ");

        int choice;
        try {
            choice = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Vui lòng nhập một số nguyên.");
            return;
        }

        switch (choice) {
            case 1:
                searchByName(sc);
                break;
            case 2:
                searchByCustomerType(sc);
                break;
            case 3:
                searchByPhoneNumber(sc);
                break;
            default:
                System.out.println("Lựa chọn không hợp lệ.");
        }
    }

    // Tìm kiếm theo tên KH
    private void searchByName(Scanner sc) {
        System.out.print("Nhập tên khách hàng cần tìm: ");
        String searchName = sc.nextLine().trim().toLowerCase();

        Customer[] results = new Customer[customerCount];
        int resultCount = 0;

        for (int i = 0; i < customerCount; i++) {
            String fullName = (customers[i].getFirstName() + " " + customers[i].getLastName()).toLowerCase();
            if (fullName.contains(searchName)) {
                results[resultCount] = customers[i];
                resultCount++;
            }
        }

        displaySearchResults(results, resultCount);
    }

    // Tìm kiếm theo loại KH
    private void searchByCustomerType(Scanner sc) {
        System.out.print("Nhập loại khách hàng cần tìm: ");
        String searchType = sc.nextLine().trim().toLowerCase();

        Customer[] results = new Customer[customerCount];
        int resultCount = 0;

        for (int i = 0; i < customerCount; i++) {
            if (customers[i].getCustomerType().toLowerCase().contains(searchType)) {
                results[resultCount] = customers[i];
                resultCount++;
            }
        }

        displaySearchResults(results, resultCount);
    }

    // Tìm kiếm theo SDT KH
    private void searchByPhoneNumber(Scanner sc) {
        System.out.print("Nhập số điện thoại cần tìm: ");
        String searchPhone = sc.nextLine().trim();

        Customer[] results = new Customer[customerCount];
        int resultCount = 0;

        for (int i = 0; i < customerCount; i++) {
            if (customers[i].getPhoneNumber().contains(searchPhone)) {
                results[resultCount] = customers[i];
                resultCount++;
            }
        }

        displaySearchResults(results, resultCount);
    }

    // Hiện kết quả tìm kiếm
    private void displaySearchResults(Customer[] results, int resultCount) {
        if (resultCount == 0) {
            System.out.println("Không tìm thấy kết quả phù hợp.");
        } else {
            System.out.println("\n===== KẾT QUẢ TÌM KIẾM (" + resultCount + " khách hàng) =====");
            for (int i = 0; i < resultCount; i++) {
                results[i].displayData();
            }
        }
    }

    // 6. Sắp xếp KH
    public void sortCustomers(Scanner sc) {
        if (customerCount == 0) {
            System.out.println("Danh sách khách hàng trống.");
            return;
        }

        System.out.println("\n===== MENU SẮP XẾP =====");
        System.out.println("1. Sắp xếp theo tên (A-Z)");
        System.out.println("2. Sắp xếp theo tên (Z-A)");
        System.out.println("3. Sắp xếp theo năm sinh (tăng dần)");
        System.out.println("4. Sắp xếp theo năm sinh (giảm dần)");
        System.out.print("Chọn tiêu chí sắp xếp: ");

        int choice;
        try {
            choice = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Vui lòng nhập một số nguyên.");
            return;
        }

        switch (choice) {
            case 1:
                sortByNameAscending();
                break;
            case 2:
                sortByNameDescending();
                break;
            case 3:
                sortByBirthYearAscending();
                break;
            case 4:
                sortByBirthYearDescending();
                break;
            default:
                System.out.println("Lựa chọn không hợp lệ.");
                return;
        }

        System.out.println("Sắp xếp thành công. Danh sách sau khi sắp xếp:");
        displayAllCustomers();
    }

    // Sắp xếp theo tên tăng dần (A-Z)
    private void sortByNameAscending() {
        for (int i = 0; i < customerCount - 1; i++) {
            for (int j = i + 1; j < customerCount; j++) {
                String name1 = customers[i].getFirstName();
                String name2 = customers[j].getFirstName();

                if (name1.compareToIgnoreCase(name2) > 0) {
                    Customer temp = customers[i];
                    customers[i] = customers[j];
                    customers[j] = temp;
                }
            }
        }
    }

    // Săp xếp theo tên giảm dần (Z-A)
    private void sortByNameDescending() {
        for (int i = 0; i < customerCount - 1; i++) {
            for (int j = i + 1; j < customerCount; j++) {
                String name1 = customers[i].getFirstName();
                String name2 = customers[j].getFirstName();

                if (name1.compareToIgnoreCase(name2) < 0) {
                    Customer temp = customers[i];
                    customers[i] = customers[j];
                    customers[j] = temp;
                }
            }
        }
    }

    // Săp xếp theo ngày sinh tăng dần
    private void sortByBirthYearAscending() {
        for (int i = 0; i < customerCount - 1; i++) {
            for (int j = i + 1; j < customerCount; j++) {
                if (compareDates(customers[i].getDateOfBirth(), customers[j].getDateOfBirth()) > 0) {
                    Customer temp = customers[i];
                    customers[i] = customers[j];
                    customers[j] = temp;
                }
            }
        }
    }

    // Sắp xếp theo ngày sinh giảm dần
    private void sortByBirthYearDescending() {
        for (int i = 0; i < customerCount - 1; i++) {
            for (int j = i + 1; j < customerCount; j++) {
                if (compareDates(customers[i].getDateOfBirth(), customers[j].getDateOfBirth()) < 0) {
                    Customer temp = customers[i];
                    customers[i] = customers[j];
                    customers[j] = temp;
                }
            }
        }
    }

    // Phương thức so sánh hai ngày có định dạng dd/MM/yyyy
    private int compareDates(String date1Str, String date2Str) {
        // Parse các thành phần của ngày
        String[] parts1 = date1Str.split("/");
        String[] parts2 = date2Str.split("/");

        // Chuyển thành định dạng yyyyMMdd để so sánh
        int numericDate1 = Integer.parseInt(parts1[2]) * 10000 + Integer.parseInt(parts1[1]) * 100 + Integer.parseInt(parts1[0]);
        int numericDate2 = Integer.parseInt(parts2[2]) * 10000 + Integer.parseInt(parts2[1]) * 100 + Integer.parseInt(parts2[0]);

        // So sánh và trả về kết quả (-1, 0, 1)
        return Integer.compare(numericDate1, numericDate2);
    }

    // Phuong thức tìm theo ID
    private int findCustomerById(String customerId) {
        for (int i = 0; i < customerCount; i++) {
            if (customers[i].getCustomerId().equals(customerId)) {
                return i;
            }
        }
        return -1;
    }
}