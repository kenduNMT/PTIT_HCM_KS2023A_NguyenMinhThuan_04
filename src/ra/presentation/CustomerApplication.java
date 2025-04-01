package ra.presentation;

import ra.bussiness.CustomerBusiness;
import java.util.Scanner;

public class CustomerApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CustomerBusiness customerBusiness = new CustomerBusiness();
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\n----------------------------Customer Menu----------------------------");
            System.out.println("1. Hiển thị danh sách các khách hàng");
            System.out.println("2. Thêm mới khách hàng");
            System.out.println("3. Chỉnh sửa thông tin khách hàng");
            System.out.println("4. Xóa khách hàng");
            System.out.println("5. Tìm kiếm khách hàng");
            System.out.println("6. Sắp xếp");
            System.out.println("0. Thoát chương trình");
            System.out.println("------------------------------------------------------------------------");
            System.out.print("Chọn chức năng: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập một số nguyên.");
                continue;
            }

            switch (choice) {
                case 1:
                    customerBusiness.displayAllCustomers();
                    break;
                case 2:
                    customerBusiness.addCustomers(scanner);
                    break;
                case 3:
                    customerBusiness.editCustomer(scanner);
                    break;
                case 4:
                    customerBusiness.deleteCustomer(scanner);
                    break;
                case 5:
                    customerBusiness.searchCustomers(scanner);
                    break;
                case 6:
                    customerBusiness.sortCustomers(scanner);
                    break;
                case 0:
                    System.out.println("Cảm ơn bạn đã sử dụng chương trình!");
                    isRunning = false;
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        }

        scanner.close();
    }
}