import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import wagu.Block;
import wagu.Board;
import wagu.Table;

public class PrintList {

    /**
     * Print table
     */
    public void printTable(List<String> header, List<List<String>> rowList, int boardSize) {
        Board board = new Board(boardSize);
        Table table = new Table(board, boardSize, header, rowList);
        table.setGridMode(Table.GRID_COLUMN);
        //setting width and data-align of columns
        List<Integer> colWidthsList = new ArrayList<>();
        for (int i = 0; i < header.size(); i++) {
            colWidthsList.add(22);
        }
        List<Integer> colAlignList = new ArrayList<>();
        for (int i = 0; i < header.size(); i++) {
            colAlignList.add(Block.DATA_CENTER);
        }
        table.setColWidthsList(colWidthsList);
        table.setColAlignsList(colAlignList);

        board.setInitialBlock(table.tableToBlocks());
        board.build();
        System.out.println(board.getPreview());
    }

    /**
     * Sprint 1: US 29, list deceased
     */
    public List<List<String>> US29(List<Person> people, List<Family> families) {
        List<String> headerList_death = Arrays.asList("ID", "Name", "Death Date");
        ;        //table header for deceased
        List<List<String>> rowList_death = new ArrayList<>();        //list of death for print table

        for (int i = 0; i < people.size(); i++) {
            List<String> deathPerson = new ArrayList<>(Arrays.asList("0", "0", "0"));
            if (people.get(i).alive.equals("False")) {
                deathPerson.set(0, people.get(i).id_indi);
                deathPerson.set(1, people.get(i).name);
                deathPerson.set(2, people.get(i).deat_year + "-" + people.get(i).deat_month + "-" + people.get(i).deat_day);
                rowList_death.add(deathPerson);
            }
        }
        if (rowList_death.size() > 0) {
            System.out.println("Deceased");
            printTable(headerList_death, rowList_death, 530);
        } else {
            System.out.println("No Deceased In the GEDCOM");
        }

        return rowList_death;
    }

    /**
     * Sprint 3: US 33, list orphans
     */
    public List<List<String>> US33(List<Person> people, List<Family> families) {
        List<String> headerList_orphans = Arrays.asList("ID", "Name", "Family ID");
        ;    //table header for orphans
        List<List<String>> rowList_orphans = new ArrayList<>();                            //list of orphans for print table
        for (int i = 0; i < families.size(); i++) {
            List<String> orphans = new ArrayList<>(Arrays.asList("0", "0", "0"));
            if (families.get(i).husband.alive.equals("False") && families.get(i).wife.alive.equals("False")) {
                for (Person p : families.get(i).children) {
                    if (p.alive.equals("True") && Integer.parseInt(p.age) < 18) {
                        orphans.set(0, p.id_indi);
                        orphans.set(1, p.name);
                        orphans.set(2, families.get(i).id_fam);
                        rowList_orphans.add(orphans);
                    }
                }
            }
        }
        if (rowList_orphans.size() > 0) {
            System.out.println("Orphans");
            printTable(headerList_orphans, rowList_orphans, 530);
        } else {
            System.out.println("No Orphans In the GEDCOM");
        }
        return rowList_orphans;
    }

    /**
     * sprint3 US30 List all living married people in a GEDCOM file
     */
    public List<List<String>> US30(List<Person> people, List<Family> families) {
        List<String> headerList_living_married = Arrays.asList("ID", "Name", "Family ID");
        List<List<String>> rowList_living_married = new ArrayList<>();
        for (int i = 0; i < families.size(); i++) {
            if (families.get(i).marryDateValid && !families.get(i).marr_year.equals("NA") && families.get(i).div_year.equals("NA")) {
                for (int j = 0; j < people.size(); j++) {
                    if (people.get(j).id_indi.equals(families.get(i).id_husband) ||
                            people.get(j).id_indi.equals(families.get(i).id_wife)) {
                        if (people.get(j).alive.equals("True")) {
                            List<String> marriedPerson = new ArrayList<>(Arrays.asList("0", "0", "0"));
                            marriedPerson.set(0, people.get(j).id_indi);
                            marriedPerson.set(1, people.get(j).name);
                            marriedPerson.set(2, families.get(i).id_fam);
                            rowList_living_married.add(marriedPerson);
                        }
                    }
                }
            }
        }
        if (rowList_living_married.size() > 0) {
            System.out.println("Living married ");
            printTable(headerList_living_married, rowList_living_married, 530);
        } else {
            System.out.println("No living married  In the GEDCOM");
        }
        return rowList_living_married;
    }

    /**
     * sprint3 US27 Include person's current age when listing individuals
     */
    public List<List<String>> US27(List<Person> people, List<Family> families) {
        List<String> person_info = Arrays.asList("ID", "Name", "Gender", "Birthday", "Age", "Alive", "Death", "Is child of family", "Is spouse of family");
        List<List<String>> rowList_person = new ArrayList<>();
        for (int i = 0; i < people.size(); i++) {
            if (people.get(i).alive == "False") {
                try {
                    LocalDate birthday = LocalDate.of(Integer.parseInt(people.get(i).birt_year),
                            Integer.parseInt(people.get(i).birt_month),
                            Integer.parseInt(people.get(i).birt_day));
                    LocalDate deathday = LocalDate.of(Integer.parseInt(people.get(i).deat_year), Integer.parseInt(people.get(i).deat_month),
                            Integer.parseInt(people.get(i).deat_day));
                    Period diff = Period.between(birthday, deathday);
                    people.get(i).age = Integer.toString(diff.getYears());
                } catch (Exception e) {
                    people.get(i).deathValid = false;
                }
            } else {
                LocalDate today = LocalDate.now();
                try {
                    LocalDate birthday = LocalDate.of(Integer.parseInt(people.get(i).birt_year), Integer.parseInt(people.get(i).birt_month), Integer.parseInt(people.get(i).birt_day));
                    Period diff = Period.between(birthday, today);
                    people.get(i).age = Integer.toString(diff.getYears());
                } catch (Exception e) {
                    people.get(i).birthValid = false;
                }
            }
            List<String> person = new ArrayList<>(Arrays.asList("0", "0", "0", "0", "0", "0", "0", "0", "0"));
            person.set(0, people.get(i).id_indi);
            person.set(1, people.get(i).name);
            person.set(2, people.get(i).sex);
            person.set(3, people.get(i).birt_year + "-" + people.get(i).birt_month + "-" + people.get(i).birt_day);
            person.set(4, people.get(i).age);
            person.set(5, people.get(i).alive);
            if (people.get(i).alive == "True") {
                person.set(6, "NA");
            } else {
                person.set(6, people.get(i).deat_year + "-" + people.get(i).deat_month + "-" + people.get(i).deat_day);
            }
            if (people.get(i).id_famc.size() != 0) {
                person.set(7, people.get(i).id_famc.toString());
            }
            if (people.get(i).id_fams.size() != 0) {
                person.set(8, people.get(i).id_fams.toString());
            }
            for (int k = 0; k < 9; k++) {
                if (person.get(k) == "0") {
                    person.set(k, "NA");
                }
            }
            rowList_person.add(person);
        }
        System.out.println("individuals information: ");
        printTable(person_info, rowList_person, 530);

        return rowList_person;
    }

    /**
     * Sprint 4: US 35, List recent births
     * List all people in a GEDCOM file who were born in the last 30 days
     */

    public List<List<String>> US35(List<Person> people, List<Family> families) {
        LocalDate now = LocalDate.now();
        LocalDate recent;
        List<String> headerList_recent_births = Arrays.asList("ID", "Name", "Births Date");//table header
        List<List<String>> rowList_recent_births = new ArrayList<>();        //list for print table
        recent = now.minus(30, ChronoUnit.DAYS);
        for (int i = 0; i < people.size(); i++) {
            List<String> person_recent_births = new ArrayList<>(Arrays.asList("0", "0", "0"));
            if (people.get(i).birthValid && people.get(i).birthBeforeToday) {
                LocalDate birthday = LocalDate.of(Integer.parseInt(people.get(i).birt_year), Integer.parseInt(people.get(i).birt_month),
                        Integer.parseInt(people.get(i).birt_day));
                if (birthday.isAfter(recent)) {
                    person_recent_births.set(0, people.get(i).id_indi);
                    person_recent_births.set(1, people.get(i).name);
                    person_recent_births.set(2, people.get(i).birt_year + "-" + people.get(i).birt_month + "-" + people.get(i).birt_day);
                    rowList_recent_births.add(person_recent_births);
                }
            }
        }
        if (rowList_recent_births.size() > 0) {
            System.out.println("Recent births");
            printTable(headerList_recent_births, rowList_recent_births, 530);
        } else {
            System.out.println("No Recent births In the GEDCOM");
        }
        return rowList_recent_births;
    }

    /**
     * Sprint 4: US 36, List recent deaths
     * List all people in a GEDCOM file who died in the last 30 days
     */
    public List<List<String>> US36(List<Person> people, List<Family> families) {
        LocalDate now = LocalDate.now();
        LocalDate recent;
        List<String> headerList_recent_deaths = Arrays.asList("ID", "Name", "Deaths Date");//table header
        List<List<String>> rowList_recent_deaths = new ArrayList<>();        //list for print table
        recent = now.minus(30, ChronoUnit.DAYS);
        for (int i = 0; i < people.size(); i++) {
            List<String> person_recent_deaths = new ArrayList<>(Arrays.asList("0", "0", "0"));
            if (people.get(i).deathValid && people.get(i).deathBeforeToday) {
                LocalDate deathday = LocalDate.of(Integer.parseInt(people.get(i).deat_year), Integer.parseInt(people.get(i).deat_month),
                        Integer.parseInt(people.get(i).deat_day));
                if (deathday.isAfter(recent)) {
                    person_recent_deaths.set(0, people.get(i).id_indi);
                    person_recent_deaths.set(1, people.get(i).name);
                    person_recent_deaths.set(2, people.get(i).deat_year + "-" + people.get(i).deat_month + "-" + people.get(i).deat_day);
                    rowList_recent_deaths.add(person_recent_deaths);
                }
            }
        }
        if (rowList_recent_deaths.size() > 0) {
            System.out.println("Recent deaths ");
            printTable(headerList_recent_deaths, rowList_recent_deaths, 530);
        } else {
            System.out.println("No Recent deaths In the GEDCOM");
        }
        return rowList_recent_deaths;
    }

}
