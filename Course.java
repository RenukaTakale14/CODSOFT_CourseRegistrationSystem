public class Course {

    private final String courseCode;
    private final String title;
    private final String description;
    private final int capacity;
    private final String schedule;

    private int registeredStudents;

    public Course(
            String courseCode,
            String title,
            String description,
            int capacity,
            String schedule) {

        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.registeredStudents = 0;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getSchedule() {
        return schedule;
    }

    public int getAvailableSlots() {
        return capacity - registeredStudents;
    }

    public boolean hasAvailableSlot() {
        return registeredStudents < capacity;
    }

    public void registerStudent() {

        if (!hasAvailableSlot()) {
            throw new IllegalStateException(
                    "Course is already full."
            );
        }

        registeredStudents++;
    }

    public void removeStudent() {

        if (registeredStudents > 0) {
            registeredStudents--;
        }
    }

    public void displayCourse() {

        System.out.println("----------------------------------------------");
        System.out.println("Course Code : " + courseCode);
        System.out.println("Title       : " + title);
        System.out.println("Description : " + description);
        System.out.println("Schedule    : " + schedule);
        System.out.println(
                "Available   : "
                        + getAvailableSlots()
                        + "/" + capacity
        );
    }
}