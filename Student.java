public class Student extends Human {
    private int member_id;
    private int grade;

    Student(String name, int member_id, int grade) {
        super(name);
        this.member_id = member_id;
        this.grade = grade;
    }

    String getName() {
        return super.getName();
    }

    int getMember_id() {
        return member_id;
    }

    int getGrade() {
        return grade;
    }
}
