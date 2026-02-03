import java.util.Scanner;
import java.util.regex.Pattern;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] listMSSV = new String[100];
        int currentSize = 0; // Biến theo dõi số lượng phần tử thực tế trong mảng
        String regex = "^B\\d{7}$"; // Định dạng: B + 7 chữ số

        while (true) {
            System.out.println("\n--- HỆ THỐNG QUẢN LÝ MSSV ---");
            System.out.println("1. Hiển thị danh sách MSSV");
            System.out.println("2. Thêm mới MSSV");
            System.out.println("3. Cập nhật MSSV");
            System.out.println("4. Xóa MSSV");
            System.out.println("5. Tìm kiếm MSSV");
            System.out.println("6. Thoát");
            System.out.print("Chọn chức năng (1-6): ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.err.println("Vui lòng nhập số từ 1 đến 6!");
                continue;
            }

            switch (choice) {
                case 1:
                    if (currentSize == 0) {
                        System.out.println("Danh sách hiện đang rỗng.");
                    } else {
                        System.out.println("DANH SÁCH SINH VIÊN:");
                        for (int i = 0; i < currentSize; i++) {
                            System.out.printf("STT: %d | MSSV: %s\n", (i + 1), listMSSV[i]);
                        }
                    }
                    break;

                case 2:
                    if (currentSize >= 100) {
                        System.err.println("Mảng đã đầy (tối đa 100 phần tử)!");
                    } else {
                        String newId;
                        while (true) {
                            System.out.print("Nhập MSSV mới (Định dạng Bxxxxxxx): ");
                            newId = scanner.nextLine();
                            if (Pattern.matches(regex, newId)) {
                                listMSSV[currentSize] = newId;
                                currentSize++;
                                System.out.println("Thêm mới thành công!");
                                break;
                            } else {
                                System.err.println("Sai định dạng! MSSV phải bắt đầu bằng 'B' và có 7 chữ số.");
                            }
                        }
                    }
                    break;

                case 3:
                    System.out.print("Nhập vị trí (index) cần sửa (0 - " + (currentSize - 1) + "): ");
                    int updateIndex;
                    try {
                        updateIndex = Integer.parseInt(scanner.nextLine());
                        if (updateIndex >= 0 && updateIndex < currentSize) {
                            while (true) {
                                System.out.print("Nhập MSSV cập nhật mới: ");
                                String updateId = scanner.nextLine();
                                if (Pattern.matches(regex, updateId)) {
                                    listMSSV[updateIndex] = updateId;
                                    System.out.println("Cập nhật thành công!");
                                    break;
                                } else {
                                    System.err.println("Sai định dạng! Vui lòng nhập lại.");
                                }
                            }
                        } else {
                            System.err.println("Vị trí không hợp lệ!");
                        }
                    } catch (NumberFormatException e) {
                        System.err.println("Chỉ được nhập số!");
                    }
                    break;

                case 4:
                    System.out.print("Nhập MSSV cụ thể cần xóa: ");
                    String deleteId = scanner.nextLine();
                    int foundIndex = -1;
                    for (int i = 0; i < currentSize; i++) {
                        if (listMSSV[i].equalsIgnoreCase(deleteId)) {
                            foundIndex = i;
                            break;
                        }
                    }

                    if (foundIndex != -1) {

                        for (int i = foundIndex; i < currentSize - 1; i++) {
                            listMSSV[i] = listMSSV[i + 1];
                        }
                        listMSSV[currentSize - 1] = null;
                        currentSize--;
                        System.out.println("Đã xóa MSSV: " + deleteId);
                    } else {
                        System.err.println("Không tìm thấy MSSV này trong danh sách.");
                    }
                    break;

                case 5:
                    System.out.print("Nhập chuỗi ký tự cần tìm: ");
                    String keyword = scanner.nextLine().toLowerCase();
                    boolean isFound = false;
                    System.out.println("Kết quả tìm kiếm:");
                    for (int i = 0; i < currentSize; i++) {
                        if (listMSSV[i].toLowerCase().contains(keyword)) {
                            System.out.printf("- %s\n", listMSSV[i]);
                            isFound = true;
                        }
                    }
                    if (!isFound) System.out.println("Không tìm thấy kết quả nào.");
                    break;

                case 6:
                    System.out.println("Cảm ơn bạn đã sử dụng hệ thống. Tạm biệt!");
                    System.exit(0);

                default:
                    System.err.println("Lựa chọn không hợp lệ (1-6).");
            }
        }
    }
}