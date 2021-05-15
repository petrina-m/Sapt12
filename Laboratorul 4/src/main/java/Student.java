import java.util.Comparator;
import java.util.Objects;

public class Student {

    private String name;
    int notaExamen;
    boolean acceptat;

    public boolean isAcceptat() {
        return acceptat;
    }

    public void setAcceptat(boolean acceptat) {
        this.acceptat = acceptat;
    }

    public Student(String name, int notaExamen) {
        this.name = name;
        this.notaExamen = notaExamen;
        Problem.nrStudenti++;
        Problem.nrStudentiNeacceptati++;
        acceptat=false;

    }

    public int getNotaExamen() {
        return notaExamen;
    }

    public void setNotaExamen(int notaExamen) {
        this.notaExamen = notaExamen;
    }

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Student other = (Student) obj;
        return Objects.equals(this.name, other.name);
    }

    static Comparator<Student> GradeComparator() {
        return new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                return Integer.compare(s1.getNotaExamen(),s2.getNotaExamen());
            }
            // compare using grade

        };
    }

    static Comparator<Student> NameComparator() {
        return new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                return s1.getName().compareTo(s2.getName());
            }
            // compare using name
        };
    }


}
