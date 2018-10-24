import java.time.LocalDate;
import java.util.*;

public class CheckDate {


    /**
     * sprint1 US02 Birth before marriage
     */
    public void US02(List<Person> people, List<Family> families) {
        Family fam;
        Person hus = null;
        Person wife = null;
        for (int i = 0; i < families.size(); i++) {
            fam = families.get(i);
            for (int j = 0; j < people.size(); j++) {
                if (people.get(j).id_indi.equals(fam.id_husband)) {
                    hus = people.get(j);
                }
                if (people.get(j).id_indi.equals(fam.id_wife)) {
                    wife = people.get(j);
                }
            }
            if (fam.marryDateValid) {
                if (Integer.parseInt(hus.birt_year) > Integer.parseInt(fam.marr_year)) {
                    System.out.println("ERROR: FAMILY: US02: " + fam.id_fam + ": Husband's birth date " + hus.birt_year + "-" + hus.birt_month + "-" + hus.birt_day + " after marriage date " + fam.marr_year + "-" + fam.marr_month + "-" + fam.marr_day);
                } else if (Integer.parseInt(hus.birt_year) == Integer.parseInt(fam.marr_year)) {
                    if (Integer.parseInt(hus.birt_month) > Integer.parseInt(fam.marr_month)) {
                        System.out.println("ERROR: FAMILY: US02: " + fam.id_fam + ": Husband's birth date " + hus.birt_year + "-" + hus.birt_month + "-" + hus.birt_day + " after marriage date " + fam.marr_year + "-" + fam.marr_month + "-" + fam.marr_day);
                    } else if (Integer.parseInt(hus.birt_month) == Integer.parseInt(fam.marr_month)) {
                        if (Integer.parseInt(hus.birt_day) > Integer.parseInt(fam.marr_day)) {
                            System.out.println("ERROR: FAMILY: US02: " + fam.id_fam + ": Husband's birth date " + hus.birt_year + "-" + hus.birt_month + "-" + hus.birt_day + " after marriage date " + fam.marr_year + "-" + fam.marr_month + "-" + fam.marr_day);
                        }
                    }
                }
                if (Integer.parseInt(wife.birt_year) > Integer.parseInt(fam.marr_year)) {
                    System.out.println("ERROR: FAMILY: US02: " + fam.id_fam + ": Wife's birth date " + wife.birt_year + "-" + wife.birt_month + "-" + wife.birt_day + " after marriage date " + fam.marr_year + "-" + fam.marr_month + "-" + fam.marr_day);
                } else if (Integer.parseInt(wife.birt_year) == Integer.parseInt(fam.marr_year)) {
                    if (Integer.parseInt(wife.birt_month) > Integer.parseInt(fam.marr_month)) {
                        System.out.println("ERROR: FAMILY: US02: " + fam.id_fam + ": Wife's birth date " + wife.birt_year + "-" + wife.birt_month + "-" + wife.birt_day + " after marriage date " + fam.marr_year + "-" + fam.marr_month + "-" + fam.marr_day);
                    } else if (Integer.parseInt(wife.birt_month) == Integer.parseInt(fam.marr_month)) {
                        if (Integer.parseInt(wife.birt_day) > Integer.parseInt(fam.marr_day)) {
                            System.out.println("ERROR: FAMILY: US02: " + fam.id_fam + ": Wife's birth date " + wife.birt_year + "-" + wife.birt_month + "-" + wife.birt_day + " after marriage date " + fam.marr_year + "-" + fam.marr_month + "-" + fam.marr_day);
                        }
                    }
                }
            } else {
                System.out.println("Error: the marriage date is not vaild");
            }
        }
    }


    /**
     * sprint1 US12 Parents not too old
     */
    public void US12(List<Person> people, List<Family> families) {
        Family fam;
        Person hus = null;
        Person wife = null;
        Person child = null;
        for (int i = 0; i < families.size(); i++) {
            fam = families.get(i);
            for (int j = 0; j < people.size(); j++) {
                if (people.get(j).id_indi.equals(fam.id_husband)) {
                    hus = people.get(j);
                }
                if (people.get(j).id_indi.equals(fam.id_wife)) {
                    wife = people.get(j);
                }
            }
            if (fam.id_children.size() > 0) {
                for (int k = 0; k < fam.id_children.size(); k++) {
                    for (int n = 0; n < people.size(); n++) {
                        if (people.get(n).id_indi.equals(fam.id_children.get(k))) {
                            child = people.get(n);
                        }
                    }
                    if (!wife.age.equals("NA") && !child.age.equals("NA") && Integer.parseInt(wife.age) - Integer.parseInt(child.age) >= 60) {
                        System.out.println("ERROR: FAMILY: US12: " + fam.id_fam + ": wife " + wife.id_indi + " should be less than 60 years older than her children " + child.id_indi);
                    }
                    if (!hus.age.equals("NA") && !child.age.equals("NA") && Integer.parseInt(hus.age) - Integer.parseInt(child.age) >= 80) {
                        System.out.println("ERROR: FAMILY: US12: " + fam.id_fam + ": husband " + hus.id_indi + " should be less than 80 years older than his children " + child.id_indi);
                    }
                }

            }
        }
    }


    /**
     * sprint1 US42 Reject illegal dates
     */
    public void US42(List<Person> people, List<Family> families) {
        for (int i = 0; i < people.size(); i++) {
            if (!people.get(i).birthValid)
                System.out.println("ERROR: INDIVIDUAL: US42: " + people.get(i).id_indi + ": Birthday date (" + people.get(i).birt_year + "-" + people.get(i).birt_month + "-" + people.get(i).birt_day + ") is invalid");

            if (!people.get(i).deathValid)
                System.out.println("ERROR: INDIVIDUAL: US42: " + people.get(i).id_indi + ": Death date (" + people.get(i).deat_year + "-" + people.get(i).deat_month + "-" + people.get(i).deat_day + ") is invalid");
        }

        for (int i = 0; i < families.size(); i++) {
            if (!families.get(i).marryDateValid)
                System.out.println("ERROR: FAMILY: US42: " + families.get(i).id_fam + ": Marry date (" + families.get(i).marr_year + "-" + families.get(i).marr_month + "-" + families.get(i).marr_day + ") is invalid");

            if (!families.get(i).divorceDateValid)
                System.out.println("ERROR: FAMILY: US42: " + families.get(i).id_fam + ": Divorce date (" + families.get(i).div_year + "-" + families.get(i).div_month + "-" + families.get(i).div_day + ") is invalid");
        }

    }


    /**
     * sprint1 US01 Dates before current date
     */
    public void US01(List<Person> people, List<Family> families) {
        LocalDate today = LocalDate.now();
        for (int i = 0; i < families.size(); i++) {
            //marriage before today
            if (families.get(i).marryDateValid) {
                LocalDate marrday = LocalDate.of(Integer.parseInt(families.get(i).marr_year),
                        Integer.parseInt(families.get(i).marr_month), Integer.parseInt(families.get(i).marr_day));
                families.get(i).marryBeforeToday = !marrday.isAfter(today);
            }
            //divorce before today
            if (families.get(i).divorceDateValid && !families.get(i).div_year.equals("NA")) {
                LocalDate divday = LocalDate.of(Integer.parseInt(families.get(i).div_year),
                        Integer.parseInt(families.get(i).div_month), Integer.parseInt(families.get(i).div_day));
                families.get(i).divorceBeforeToday = !divday.isAfter(today);
            }
            //print error message 
            if (!families.get(i).marryBeforeToday) {
                System.out.println("ERROR: FAMILY: US1: " + families.get(i).id_fam + ": Marriage date (" + families.get(i).marr_year
                        + "-" + families.get(i).marr_month + "-" + families.get(i).marr_day + ") occurs in the future.");
            }
            if (!families.get(i).divorceBeforeToday) {
                System.out.println("ERROR: FAMILY: US1: " + families.get(i).id_fam + ": Divorce date (" + families.get(i).div_year
                        + "-" + families.get(i).div_month + "-" + families.get(i).div_day + ") occurs in the future.");
            }
        }
        for (int i = 0; i < people.size(); i++) {
            //birth before today
            if (people.get(i).birthValid) {
                LocalDate birthday = LocalDate.of(Integer.parseInt(people.get(i).birt_year), Integer.parseInt(people.get(i).birt_month),
                        Integer.parseInt(people.get(i).birt_day));
                people.get(i).birthBeforeToday = !birthday.isAfter(today);
            }
            //death before today
            if (people.get(i).deathValid && !people.get(i).deat_year.equals("NA")) {
                LocalDate deathday = LocalDate.of(Integer.parseInt(people.get(i).deat_year), Integer.parseInt(people.get(i).deat_month),
                        Integer.parseInt(people.get(i).deat_day));
                people.get(i).deathBeforeToday = !deathday.isAfter(today);
            }
            //print error message
            if (!people.get(i).birthBeforeToday)
                System.out.println("ERROR: INDIVIDUAL: US1: " + people.get(i).id_indi + ": Birthday date (" + people.get(i).birt_year + "-"
                        + people.get(i).birt_month + "-" + people.get(i).birt_day + ") occurs in the future.");

            if (!people.get(i).deathBeforeToday)
                System.out.println("ERROR: INDIVIDUAL: US1: " + people.get(i).id_indi + ": Death date (" + people.get(i).deat_year + "-"
                        + people.get(i).deat_month + "-" + people.get(i).deat_day + ") occurs in the future.");
        }
    }

    /**
     * sprint1 US10 Marriage after 14
     */
    public void US10(List<Person> people, List<Family> families) {
        for (int i = 0; i < families.size(); i++) {
            for (int j = 0; j < people.size(); j++) {
                if (families.get(i).marryDateValid) {
                    if (families.get(i).id_husband.equals(people.get(j).id_indi) || families.get(i).id_wife.equals(people.get(j).id_indi)) {
                        LocalDate birthday = LocalDate.of(Integer.parseInt(people.get(j).birt_year), Integer.parseInt(people.get(j).birt_month),
                                Integer.parseInt(people.get(j).birt_day));
                        LocalDate marrday = LocalDate.of(Integer.parseInt(families.get(i).marr_year),
                                Integer.parseInt(families.get(i).marr_month), Integer.parseInt(families.get(i).marr_day));
                        int age = birthday.until(marrday).getYears();
                        if (age < 14) {
                            System.out.println("ERROR: FAMILY: US10: " + people.get(j).id_indi + ": Marriage before 14.");
                        }
                    }//husband wife
                }
            }
        }
    }

    /**
     * sprint2 US07 less than 150 years old
     */
    public void US07(List<Person> people) {
        for (Person person : people) {
            if (person.birthValid && person.deathValid) {
                if (person.alive.equals("True") && !person.birt_year.equals("NA") && Integer.parseInt(person.age) >= 150) {
                    System.out.println("ERROR: INDIVIDUAL: US07: " + person.id_indi + ": More than 150 years old.");
                }

                if (person.alive.equals("False") && !person.deat_year.equals("NA") && Integer.parseInt(person.age) >= 150) {
                    System.out.println("ERROR: INDIVIDUAL: US07: " + person.id_indi + ": More than 150 years old.");
                }
            }
        }
    }

    /**
     * sprint2 US03 Birth before death
     * Birth should occur before death of an individual
     */
    public void US03(List<Person> people) {
        for (int i = 0; i < people.size(); i++) {
            if (people.get(i).birthValid && people.get(i).deathValid && !people.get(i).deat_year.equals("NA")) {
                LocalDate birthday = LocalDate.of(Integer.parseInt(people.get(i).birt_year), Integer.parseInt(people.get(i).birt_month),
                        Integer.parseInt(people.get(i).birt_day));
                LocalDate deathday = LocalDate.of(Integer.parseInt(people.get(i).deat_year), Integer.parseInt(people.get(i).deat_month),
                        Integer.parseInt(people.get(i).deat_day));
                if (!deathday.isAfter(birthday)) {
                    System.out.println("ERROR: INDIVIDUAL: US03: " + people.get(i).id_indi + ": Birthday date (" + people.get(i).birt_year + "-"
                            + people.get(i).birt_month + "-" + people.get(i).birt_day + ") is after Death date (" + people.get(i).deat_year + "-"
                            + people.get(i).deat_month + "-" + people.get(i).deat_day + ").");
                }
            }
        }
    }

}