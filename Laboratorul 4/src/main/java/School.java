import java.util.*;
import java.util.Objects;


public class School {
    private String SchoolName;
    int capacitate;
    int capacitateOcupata;
    Stack<Student> studentiAcceptati = new Stack<>();
    String criteriu;

    public int getCapacitateOcupata() {
        return capacitateOcupata;
    }
    public Student getLastAcceptedStudent(){
        return studentiAcceptati.peek();
    }
    public void setGetLastAcceptedStudent(Student s){
        studentiAcceptati.push(s);
    }
    public void removeLastAcceptedStudent(){
        studentiAcceptati.pop();
    }

    public void increaseCapacitateOcupata() {
        this.capacitateOcupata ++;
    }

    public int getCapacitate() {
        return capacitate;
    }

    public void setCapacitate(int capacitate) {
        this.capacitate = capacitate;
    }

    public School(String SchoolName, int capacitate) {
        this.SchoolName = SchoolName;
        this.capacitate = capacitate;
        capacitateOcupata=0;
        criteriu="note"; //sau "nume"
    }

    public String getCriteriu() {
        return criteriu;
    }

    public void setCriteriu(String criteriu) {
        this.criteriu = criteriu;
    }

    public School(String SchoolName) {
        this.SchoolName = SchoolName;
        capacitateOcupata=0;
    }

    public String getSchoolName() {
        return SchoolName;
    }

    public void setSchoolName(String SchoolName) {
        this.SchoolName = SchoolName;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.SchoolName);
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
        final School other = (School) obj;
        if (!Objects.equals(this.SchoolName, other.SchoolName)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "School{" + "SchoolName=" + SchoolName + '}';
    }


}
