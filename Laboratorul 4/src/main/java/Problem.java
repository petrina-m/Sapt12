import java.util.*;
import java.util.stream.IntStream;

public class Problem {
    public static int nrStudenti=0;
    public static int nrStudentiNeacceptati=0;

    public void incrementStudenti()
    {
        nrStudenti++;
    }
    public void incrementStudentiNeacceptati()
    {
        nrStudentiNeacceptati++;
    }

    Student[] students = IntStream.rangeClosed(0, 3)
            .mapToObj(i -> new Student("S" + i, i))
            .toArray(Student[]::new);


    List<Student> studentList = new ArrayList<>();



    School[] schools = IntStream.rangeClosed(0, 3)
            .mapToObj(i -> new School("h" + i, i))
            .toArray(School[]::new);

    Set<School> schoolList = new TreeSet<>();

    Map<Student, List<School>> stdPrefMap = new HashMap<>();
    //  Map<School, List<Student>> schPrefMap = new LinkedHashMap<>();



    public Problem() {

        studentList.addAll(Arrays.asList(students));
        // Collections.sort(studentList, ((s1, s2) -> s1.getName().compareTo(s2.getName())));

        //preferinta studentilor pentru scoli este introdusa manual
        stdPrefMap.put(students[0], Arrays.asList(schools[0], schools[1], schools[2]));
        stdPrefMap.put(students[1], Arrays.asList(schools[0], schools[1], schools[2]));
        stdPrefMap.put(students[2], Arrays.asList(schools[0], schools[1]));
        stdPrefMap.put(students[3], Arrays.asList(schools[0], schools[2]));

        //preferinta scolilor pentru studenti se face in functie de nota,nume, obtinuta de acestia la un test
        //creez o lista cu studentii in ordine pentru fiecare scoala


    }
}
