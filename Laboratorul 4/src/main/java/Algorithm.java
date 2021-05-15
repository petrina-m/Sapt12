
import java.util.*;

/**
 *
 * @author 40757
 */
public class Algorithm {
    //fiecare scoala are o preferinta diferita

    //atat timp cat sunt studenti neacceptati,iterez prin toti studentii
    //daca un student este neacceptat, atunci atunci acesta incearca sa se scrie la prima scoala pe lista sa actuala
    //daca scoala e libera sau il prefera pe el in locul altuia acceptat, atunci acesta devine acceptat
    //altfel, incearca sa se inscrie la urmatoarea cea mai buna scoala


    List<Student> studentsOrderedByGrade = new ArrayList<>();
    List<Student> studentsOrderedByName = new ArrayList<>();
    List<School> scoalaPreferata = new ArrayList<>();
    Map<Student, School> match = new HashMap<>();
    Problem pb;

    public void computeAlg(List<Student> studentList, Set<School> schoolList, Map<Student, List<School>> stdPrefMap) {
        Collections.sort(studentsOrderedByGrade,  Student.GradeComparator() );
        Collections.sort(studentsOrderedByName,  Student.NameComparator() );
        int i;
        while(Problem.nrStudentiNeacceptati>0)
        {
            for(Student stud: studentList)
            {
                if(!stud.isAcceptat())
                {
                    //incearca in ordinea preferintii elevului
                    for(School sch : stdPrefMap.get(stud))
                    {
                        if(sch.getCapacitate()> sch.getCapacitateOcupata())
                        {
                            //daca nu sunt toate locurile ocupate, fac direct match
                            match.put(stud,sch);
                            //studentul devine asignat
                            stud.setAcceptat(true);
                            Problem.nrStudentiNeacceptati--;
                            //scoala isi mai ocupa un loc
                            sch.increaseCapacitateOcupata();

                        }
                        else
                        {
                                switch(sch.getCriteriu()){
                                    case "note":
                                       if(Student.GradeComparator().compare(stud,sch.getLastAcceptedStudent())>0)
                                       {
                                           //noul student este acceptat

                                           //il scot pe ultimul din coada
                                           Student unaccepted=sch.getLastAcceptedStudent();
                                           sch.removeLastAcceptedStudent();
                                           unaccepted.setAcceptat(false);
                                           //il pun pe cel nou
                                           sch.setGetLastAcceptedStudent(stud);
                                           stud.setAcceptat(true);


                                       }
                                        break;
                                    case "nume":
                                        if( Student.NameComparator().compare(stud,sch.getLastAcceptedStudent())>0)
                                        {
                                            //il scot pe ultimul din coada
                                            Student unaccepted=sch.getLastAcceptedStudent();
                                            sch.removeLastAcceptedStudent();
                                            unaccepted.setAcceptat(false);
                                            //il pun pe cel nou
                                            sch.setGetLastAcceptedStudent(stud);
                                            stud.setAcceptat(true);
                                        }
                                        break;
                                }
                        }
                    }

                }
            }

        }

    }


    public Algorithm(Problem pb) {
        this.pb = pb;
    }


}

