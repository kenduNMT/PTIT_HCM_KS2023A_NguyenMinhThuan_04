package ra.entity;

import java.util.Scanner;
import ra.validate.CustomerValidator;

public class Customer implements IApp {
    private String customerId;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private boolean gender;
    private String address;
    private String phoneNumber;
    private String email;
    private String customerType;

    // Constructor mặc định
    public Customer() {
    }

    // Constructor có đối số
    public Customer(String customerId, String firstName, String lastName, String dateOfBirth, boolean gender,
                    String address, String phoneNumber, String email, String customerType) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.customerType = customerType;
    }

    // Getters and setters
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    @Override
    public void inputData(Scanner sc) {
        CustomerValidator validator = new CustomerValidator();

        System.out.print("Nhập tên khách hàng: ");
        this.firstName = validator.validateFirstName(sc);
        System.out.print("Nhập họ khách hàng: ");
        this.lastName = validator.validateLastName(sc);
        System.out.print("Nhập ngày sinh (dd/MM/yyyy): ");
        this.dateOfBirth = validator.validateDateOfBirth(sc);
        System.out.print("Nhập giới tính (true - Nam, false - Nữ): ");
        this.gender = validator.validateGender(sc);
        System.out.print("Nhập địa chỉ: ");
        this.address = validator.validateAddress(sc);
        System.out.print("Nhập số điện thoại (định dạng Việt Nam): ");
        this.phoneNumber = validator.validatePhoneNumber(sc);
        System.out.print("Nhập địa chỉ email: ");
        this.email = validator.validateEmail(sc);
        System.out.print("Nhập loại khách hàng (cá nhân, doanh nghiệp): ");
        this.customerType = validator.validateCustomerType(sc);
    }

    @Override
    public void displayData() {
        System.out.println("|---------------------------------------------------------------------------------------|");
        System.out.println("| Mã KH: " + customerId);
        System.out.println("| Tên: " + firstName + " " + lastName);
        System.out.println("| Ngày sinh: " + dateOfBirth);
        System.out.println("| Giới tính: " + (gender ? "Nam" : "Nữ"));
        System.out.println("| Địa chỉ: " + address);
        System.out.println("| Số điện thoại: " + phoneNumber);
        System.out.println("| Email: " + email);
        System.out.println("| Loại khách hàng: " + customerType);
        System.out.println("|---------------------------------------------------------------------------------------|");
    }
}