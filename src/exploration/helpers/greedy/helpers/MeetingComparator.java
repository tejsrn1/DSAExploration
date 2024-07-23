package exploration.helpers.greedy.helpers;

import java.util.Comparator;

    /*
        Tips :

        Java provides two interfaces to sort objects using data members of the class:

        Comparable
        Comparator

        1. A comparable object is capable of comparing itself with another object.
         The class itself must implements the java.lang.Comparable interface to compare its instances.

         e.g. class Project implements Comparable<Project> {...

          @Override
            public int compareTo(Project prj) {
                return this.capital - prj.capital;
            }

         then it can be used like Array.sort(projects);

         OR ::: directly sent to Arrays as anonymous method.
          e.g. Arrays.sort(jobArr, (a, b) -> b.profit - a.profit);

        2. Unlike Comparable, Comparator is external to the element type we are comparing.
        It’s a separate class. like this MeetingComparator class. Collections.sort takes comparator.

    * */
public class MeetingComparator implements Comparator<Meeting> {


    @Override
    public int compare(Meeting o1, Meeting o2) {

        //first object smaller i.e.
        // first meeting ends earlier
        if (o1.end < o2.end){
            return -1;
        }
        //second object smaller i.e.
        // second meeting ends earlier
        else if(o2.end<o1.end){
            return 1;

        }

        return 0; //equal
    }
}
