import java.util.ArrayList;
import java.util.Scanner;

public class HN_KS24_CNTT2_LeDucAnh {
    public static class Student {
        private String id;
        private String name;
        private double score;


        public Student() {
        }


        public Student(String id, String name, double score) {
            this.id = id;
            this.name = name;
            this.score = score;
        }


        public String getId() { return id; }
        public void setId(String id) { this.id = id; }

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public double getScore() { return score; }
        public void setScore(double score) { this.score = score; }


        public String getRank() {
            if (this.score >= 8.0) {
                return "Gioi";
            } else if (this.score >= 6.5) {
                return "Kha";
            } else {
                return "Trung Binh";
            }
        }

        @Override
        public String toString() {
            return String.format("ID: %s | Ten: %s | Diem: %.1f | Hoc luc: %s", id, name, score, getRank());
        }

        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            ArrayList<Student> listStudent = new ArrayList<>();
            int choice;

            do {
                System.out.println("\n===== QUẢN LÝ ĐIỂM SINH VIÊN =====");
                System.out.println("1. Nhập danh sách sinh viên");
                System.out.println("2. Hiển thị danh sách sinh viên");
                System.out.println("3. Tìm kiếm sinh viên theo Học lực");
                System.out.println("4. Sắp xếp theo học lực giảm dần");
                System.out.println("5. Thoát");
                System.out.println("==================================");
                System.out.print("Chọn chức năng: ");
                choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                    case 1:
                        System.out.print("Nhập số lượng sinh viên: ");
                        int n = Integer.parseInt(sc.nextLine());
                        for (int i = 0; i < n; i++) {
                            System.out.println("Sinh viên thứ " + (i + 1) + ":");
                            String id;
                            while (true) {
                                System.out.print("Nhập mã SV (SVxxx): ");
                                id = sc.nextLine();
                                if (id.matches("^SV\\d{3}$")) {
                                    break;
                                }
                                System.out.println("Mã SV không hợp lệ! Vui lòng nhập lại (VD: SV001).");
                            }

                            System.out.print("Nhập tên: ");
                            String name = sc.nextLine();
                            System.out.print("Nhập điểm: ");
                            double score = Double.parseDouble(sc.nextLine());
                            listStudent.add(new Student(id, name, score));
                        }
                        break;

                    case 2:
                        System.out.println("--- DANH SÁCH SINH VIÊN ---");
                        for (Student s : listStudent) {
                            System.out.println(s.toString());
                        }
                        break;

                    case 3:
                        System.out.print("Nhập học lực cần tìm (Gioi/Kha/Trung Binh): ");
                        String rankSearch = sc.nextLine();
                        boolean found = false;
                        for (Student s : listStudent) {
                            if (s.getRank().equalsIgnoreCase(rankSearch)) {
                                System.out.println(s.toString());
                                found = true;
                            }
                        }
                        if (!found) System.out.println("Không tìm thấy sinh viên nào!");
                        break;

                    case 4:

                        for (int i = 0; i < listStudent.size() - 1; i++) {
                            for (int j = 0; j < listStudent.size() - i - 1; j++) {
                                if (listStudent.get(j).getScore() < listStudent.get(j + 1).getScore()) {
                                    Student temp = listStudent.get(j);
                                    listStudent.set(j, listStudent.get(j + 1));
                                    listStudent.set(j + 1, temp);
                                }
                            }
                        }
                        System.out.println("Đã sắp xếp danh sách theo học lực giảm dần.");
                        break;

                    case 5:
                        System.out.println("Tạm biệt!");
                        break;
                    default:
                        System.out.println("Lựa chọn không hợp lệ!");
                }
            } while (choice != 5);
        }
    }
}
