import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class NewMain {
    static Scanner sc = new Scanner(System.in);
    static List<ikeas> dt = new ArrayList<>();

    static List<ikeas> readFromFile() {
        List<ikeas> rtrn = new ArrayList<>();
        try (FileReader fr = new FileReader(new File("ikea.csv")); BufferedReader br = new BufferedReader(fr)) {
            String inputs = br.readLine();
            while ((inputs = br.readLine()) != null) {
                String[] elements = inputs.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                rtrn.add(new ikeas(Integer.parseInt(elements[0]), Integer.parseInt(elements[1]), elements[2],
                        elements[3], Double.parseDouble(elements[4]), elements[5],
                        Boolean.parseBoolean(elements[6]), elements[7], elements[8], elements[9], elements[10],
                        Double.parseDouble(elements[11].isEmpty() ? "0" : elements[11]),
                        Double.parseDouble(elements[12].isEmpty() ? "0" : elements[12]),
                        Double.parseDouble(elements[13].isEmpty() ? "0" : elements[13])));
            }
            return rtrn;
        } catch (Exception e) {
            System.out.println("Occurence of some problems while reading from file: " + e);
        }
        return rtrn;
    }

    public static void main(String[] args) {
        dt = readFromFile();
        Set<String> unique_designers = new HashSet<>();
        unique_designers = generate_designers(dt);
        System.out.println("\t\tWelcome to Our Project!");
        int formenu = 1;
        while (formenu == 1) {
            System.out.println(
                    "\tPress number 1 for Listing Options\n\tPress number 2 for Sorting Options\n\tPress number 3 for Searching Options\n\tPress number 4 for Listing Column Names\n\tPress Number 5 for Filtering Option\n\tPress 6 for Bonus 1\n\tPress 7 for Bonus 2\n\tPress any other number to exit the Menu\n");
            int press = sc.nextInt();
            switch (press) {
                case 1 -> {
                    System.out.println(
                            "Press 1 for Listing Randomly Selected 20 Entity\nPress 2 for Listing Top 20 Entities\nPress 3 for Listing Bottom 20 Entities\n");
                    int pr = sc.nextInt(); // 1
                    switch (pr) {
                        case 1:
                            System.out.println(
                                    "Press 1 For Listing all the fields of Each Entity\nPress 2 For Listing the Selected Fields of Each Entity:");
                            int press1 = sc.nextInt();
                            List<ikeas> forRandom = new ArrayList<>();
                            Random rd = new Random();
                            int upperBound = dt.size();
                            if (dt.size() > 20) {
                                while (forRandom.size() != 20) {
                                    int int_R = rd.nextInt(upperBound);
                                    forRandom.add(dt.get(int_R));
                                }
                            } else {
                                forRandom = dt;
                            }
                            switch (press1) {
                                case 1:
                                    for (ikeas ikea : forRandom) {
                                        ikea.printobj();
                                        System.out.println();
                                    }
                                    System.out.println("The Size is: " + forRandom.size());
                                    break;

                                case 2:
                                    PrinttheRequiredFields(forRandom, 2, 2);
                                    break;
                                default:
                                    break;
                            }
                        case 2:
                            System.out.println(
                                    "Press 1 For Listing all the fields of Each Entity\nPress 2 For Listing the Selected Fields of Each Entity:");
                            int press2 = sc.nextInt();
                            PrinttheRequiredFields(dt, press2, 2);
                            break;
                        case 3:
                            System.out.println(
                                    "Press 1 For Listing all the fields of Each Entity\nPress 2 For Listing the Selected Fields of Each Entity:");
                            int press3 = sc.nextInt();
                            PrinttheRequiredFields(dt, press3, 3);
                            break;
                    }
                    break;
                }
                case 2 -> {
                    System.out.println(
                            "Press 1 for Sorting only Numbers\nPress 2 for Sorting only Item_ID\nPress 3 for Sorting only Name\nPress 4 for Sorting only Category\nPress 5 for Sorting only Price\nPress 6 for Sorting only Old_Price\nPress 7 for Sorting only Sellable_Online\nPress 8 for Sorting only Link\nPress 9 for Sorting only Other_Colors\nPress 10 for Sorting only Short_Description\nPress 11 for Sorting only Designer\nPress 12 for Sorting only Depth\nPress 13 for Sorting only Heigtht\nPress 14 for Sorting only Width");
                    int srt = sc.nextInt();
                    System.out.println("Press 1 for ASCEDNING order\nPress 2 for Descending order");
                    int ord = sc.nextInt();
                    Collections.sort(dt, new ToComp(srt));
                    if (ord == 2)
                        Collections.reverse(dt);
                    break;
                }
                case 3 -> {
                    System.out.println(
                            "Press 1 for Searching only Numbers\nPress 2 for Searching only Item_ID\nPress 3 for Searching only Name\nPress 4 for Searching only Category\nPress 5 for Searching only Price\nPress 6 for Searching only Old_Price\nPress 7 for Searching only Sellable_Online\nPress 8 for Searching only Link\nPress 9 for Searching only Other_Colors\nPress 10 for Searching only Short_Description\nPress 11 for Searching only Designer\nPress 12 for Searching only Depth\nPress 13 for Searching only Heigtht\nPress 14 for Searching only Width");
                    int searchpress = sc.nextInt();
                    if (searchpress == 1 || searchpress == 2 || searchpress == 5 || searchpress == 6
                            || searchpress == 12 || searchpress == 13 || searchpress == 14)
                        dt = FilterFields(dt, searchpress, 1);
                    if (searchpress == 3 || searchpress == 4 || searchpress == 8 || searchpress == 10
                            || searchpress == 11)
                        dt = FilterFields(dt, searchpress, 1);
                    if (searchpress == 7 || searchpress == 9)
                        dt = FilterFields(dt, searchpress, 1);
                    for (ikeas ikeas : dt)
                        ikeas.printobj();
                    System.out.println(
                            "Do you want to extract the results to a file?\nPress 1 for - Yes\nPress 2 for - No");
                    int ex = sc.nextInt();
                    if (ex == 1) {
                        System.out.println("Enter a file name you want to save results in: ");
                        sc.nextLine();
                        String name = sc.nextLine();
                        PrinttoCSV(dt, name);
                    }
                    break;
                }
                case 4 -> {
                    System.out.println(columnname());
                    break;
                }
                case 5 -> {
                    System.out.println(
                            "Press 1 for Filtering Numbers\nPress 2 for Filtering Item_ID\nPress 3 for Filtering  Name\nPress 4 for Filtering  Category\nPress 5 for Filtering  Price\nPress 6 for Filtering  Old_Price\nPress 7 for Filtering  Sellable_Online\nPress 8 for Filtering  Link\nPress 9 for Filtering  Other_Colors\nPress 10 for Filtering  Short_Description\nPress 11 for Filtering  Designer\nPress 12 for Filtering  Depth\nPress 13 for Filtering  Heigtht\nPress 14 for Filtering  Width");
                    System.out.print(
                            "Enter the indexes in one line in order to subsequently filter them:\n");
                    sc.nextLine();
                    String inps = sc.nextLine();
                    String[] values = inps.split(" ");
                    List<Integer> vals = new ArrayList<>();
                    for (String v : values) {
                        vals.add(Integer.parseInt(v));
                    }
                    for (Integer integer : vals) {
                        if (integer == 1)
                            System.out.println("\nNumber Field:\n");
                        if (integer == 2)
                            System.out.println("\nItem_ID Field:\n");
                        if (integer == 3)
                            System.out.println("\nName Field:\n");
                        if (integer == 4)
                            System.out.println("\nCategory Field:\n");
                        if (integer == 5)
                            System.out.println("\nPrice Field:\n");
                        if (integer == 6)
                            System.out.println("\nOld_Price Field:\n");
                        if (integer == 7)
                            System.out.println("\nSellable_Online Field:\n");
                        if (integer == 8)
                            System.out.println("\nLink Field:\n");
                        if (integer == 9)
                            System.out.println("\nOther Field:\n");
                        if (integer == 10)
                            System.out.println("\nShort Description Field:\n");
                        if (integer == 11)
                            System.out.println("\nDesigner Field:\n");
                        if (integer == 12)
                            System.out.println("\nDepth Field:\n");
                        if (integer == 13)
                            System.out.println("\nHeight Field:\n");
                        if (integer == 14)
                            System.out.println("\nWidth Field:\n");

                        if (integer == 1 || integer == 2 || integer == 5 || integer == 6 || integer == 12
                                || integer == 13 || integer == 14) {
                            System.out.println(
                                    "Choose the Option: 1-Equal\n2-Greater than\n3-Less than\n4-Greater and Equal to\n5- Less and Equal to\n6-Between\n7-null");
                            int option = sc.nextInt();
                            dt = FilterFields(dt, integer, option);
                        }
                        if (integer == 3 || integer == 4 || integer == 8 || integer == 10 || integer == 11) {
                            System.out.println("Choose the Option: 1-Contains\n2-Null");
                            int option = sc.nextInt();
                            dt = FilterFields(dt, integer, option);
                        }
                        if (integer == 9 || integer == 7)
                            dt = FilterFields(dt, integer, 1);
                    }
                    for (ikeas ikeas : dt)
                        ikeas.printobj();

                    System.out.println(
                            "\nDo you want to extract the results to a file?\nPress 1 for - Yes\nPress 2 for - No");
                    int ex = sc.nextInt();
                    if (ex == 1) {
                        System.out.println("Enter a file name you want to save results in: ");
                        sc.nextLine();
                        String name = sc.nextLine();
                        PrinttoCSV(dt, name);
                    }
                    break;
                }
                case 6 -> {
                    for (String string : unique_designers)
                        System.out.println(string);
                }
                case 7 -> {
                    PrintBonus_Categoires(dt, generate_categories_report(dt));
                }
                default ->{
                    formenu=0;
                    break;
                }
            }
        }
    }

    static void PrinttheRequiredFields(List<ikeas> dt, int press2, int casenumber) {
        int initial = 0;
        int end = 0;
        if (casenumber == 2) {
            end = 20;
        }
        if (casenumber == 3) {
            if (dt.size() > 20)
                initial = dt.size() - 20;
            end = dt.size();
        }
        switch (press2) {
            case 1:
                for (int i = initial; i < end; i++) {
                    if (dt.get(i) != null)
                        dt.get(i).printobj();
                }
                break;
            case 2:
                System.out.println(
                        "Press 1 for Listing only Numbers\nPress 2 for Listing only Item_ID\nPress 3 for Listing only Name\nPress 4 for Listing only Category\nPress 5 for Listing only Price\nPress 6 for Listing only Old_Price\nPress 7 for Listing only Sellable_Online\nPress 8 for Listing only Link\nPress 9 for Listing only Other_Colors\nPress 10 for Listing only Short_Description\nPress 11 for Listing only Designer\nPress 12 for Listing only Depth\nPress 13 for Listing only Heigtht\nPress 14 for Listing only Width");
                System.out.print(
                        "Enter the indexes in one line in order to print specific fields:\n");
                sc.nextLine();
                String inps = sc.nextLine();
                String[] values = inps.split(" ");
                List<Integer> vals = new ArrayList<>();
                for (String v : values) {
                    vals.add(Integer.parseInt(v));
                }
                if (dt.size() > 20)
                    for (int i = initial; i < end; i++) {
                        if (vals.contains(1))
                            System.out.println("Number: " + dt.get(i).get_Number());
                        if (vals.contains(2))
                            System.out.println("Item_ID: " + dt.get(i).get_Item_ID());
                        if (vals.contains(3))
                            System.out.println("Name: " + dt.get(i).get_Name());
                        if (vals.contains(4))
                            System.out.println("Category: " + dt.get(i).get_Category());
                        if (vals.contains(5))
                            System.out.println("Price: " + dt.get(i).get_Price());
                        if (vals.contains(6))
                            System.out.println("Old_Price: " + dt.get(i).get_Old_Price());
                        if (vals.contains(7))
                            System.out.println("Sellable_Online: " + dt.get(i).get_Sellable_Online());
                        if (vals.contains(8))
                            System.out.println("Link: " + dt.get(i).get_Link());
                        if (vals.contains(9))
                            System.out.println("Other_Colors: " + dt.get(i).get_Other_Colors());
                        if (vals.contains(10))
                            System.out.println("Short Description: " + dt.get(i).get_short_Desciption().trim());
                        if (vals.contains(11))
                            System.out.println("Designer: " + dt.get(i).get_designer());
                        if (vals.contains(12))
                            System.out.println("Depth: " + dt.get(i).get_Depth());
                        if (vals.contains(13))
                            System.out.println("Height: " + dt.get(i).get_Height());
                        if (vals.contains(14))
                            System.out.println("Width: " + dt.get(i).get_Width());
                        System.out.println();
                    }
                else {
                    for (int i = initial; i < initial + dt.size(); i++) {
                        if (vals.contains(1))
                            System.out.println("Number: " + dt.get(i).get_Number());
                        if (vals.contains(2))
                            System.out.println("Item_ID: " + dt.get(i).get_Item_ID());
                        if (vals.contains(3))
                            System.out.println("Name: " + dt.get(i).get_Name());
                        if (vals.contains(4))
                            System.out.println("Category: " + dt.get(i).get_Category());
                        if (vals.contains(5))
                            System.out.println("Price: " + dt.get(i).get_Price());
                        if (vals.contains(6))
                            System.out.println("Old_Price: " + dt.get(i).get_Old_Price());
                        if (vals.contains(7))
                            System.out.println("Sellable_Online: " + dt.get(i).get_Sellable_Online());
                        if (vals.contains(8))
                            System.out.println("Link: " + dt.get(i).get_Link());
                        if (vals.contains(9))
                            System.out.println("Other_Colors: " + dt.get(i).get_Other_Colors());
                        if (vals.contains(10))
                            System.out.println("Short Description: " + dt.get(i).get_short_Desciption().trim());
                        if (vals.contains(11))
                            System.out.println("Designer: " + dt.get(i).get_designer());
                        if (vals.contains(12))
                            System.out.println("Depth: " + dt.get(i).get_Depth());
                        if (vals.contains(13))
                            System.out.println("Height: " + dt.get(i).get_Height());
                        if (vals.contains(14))
                            System.out.println("Width: " + dt.get(i).get_Width());
                        System.out.println();
                    }
                }
                System.out.println("Size is " + dt.size());

                break;

            default:
                break;
        }
    }

    static String columnname() {
        return "Number,item_id,name,category,price,old_price,sellable_online,link,other_colors,short_description,designer,depth,height,width";
    }

    static String columnname1() {
        return "Number,item_id,name,price,old_price,sellable_online,link,other_colors,short_description,designer,depth,height,width";
    }

    static void PrinttoCSV(List<ikeas> dt, String name) {
        File toCSV = new File(name + ".csv");
        try (PrintWriter print = new PrintWriter(toCSV)) {
            print.printf("%s\n", columnname());
            for (ikeas ikeas : dt)
                print.printf("%s\n", ikeas.toLineofContent());
        } catch (Exception e) {
            System.out.println("An Error occured: " + e);
        }
    }

    static void PrinttoCSV_Categories(List<ikeas> dt, String name) {
        name = name.replaceAll("\"", "");
        File toCSV = new File(name + ".csv");
        try (PrintWriter print = new PrintWriter(toCSV)) {
            print.printf("%s\n", columnname1());
            for (ikeas ikeas : dt)
                print.printf("%s\n", ikeas.toLineofContent_no_Category());
        } catch (Exception e) {
            System.out.println("An Error occured: " + e);
        }

    }

    static void PrintBonus_Categoires(List<ikeas> dt, Set<String> categories) {
        for (String string : categories) {
            List<ikeas> toBeFilled = new ArrayList<>();
            for (ikeas ikeas : dt) {
                if (ikeas.get_Category().contains(string)) {
                    toBeFilled.add(ikeas);
                }
            }
            PrinttoCSV_Categories(toBeFilled, string);
            toBeFilled.clear();
        }
    }

    static Set<String> generate_designers(List<ikeas> dt) {
        Set<String> designers = new HashSet<>();
        for (ikeas ikeas : dt) {
            ikeas.get_designer().trim();
            if (!Character.isDigit(ikeas.get_designer().charAt(0)) && !ikeas.get_designer().startsWith("\"")) {
                String[] line = ikeas.get_designer().replaceAll("/", ",").split(",");
                for (String st : line) {
                    designers.add(st.trim());
                }
            }
        }
        return designers;
    }

    static Set<String> generate_categories_report(List<ikeas> dt) {
        Set<String> categories = new HashSet<>();
        for (ikeas ikeas : dt) {
            ikeas.get_Category().trim();
            if (!ikeas.get_Category().isEmpty())
                categories.add(ikeas.get_Category());
        }
        return categories;
    }

    static List<ikeas> FilterFields(List<ikeas> dt, int integer, int option) {
        switch (integer) {
            case 1:
                switch (option) {
                    case 1:
                        System.out.println("Which Number you want to look for? ");
                        int nmb1 = sc.nextInt();
                        dt = dt.stream().filter(ikeas -> {
                            return ikeas.get_Number() == nmb1;
                        }).collect(Collectors.toList());
                        break;
                    case 2:
                        System.out.println("Which Number you want to be greater than? ");
                        int nmb2 = sc.nextInt();
                        dt = dt.stream().filter(ikeas -> {
                            return ikeas.get_Number() > nmb2;
                        }).collect(Collectors.toList());
                        break;
                    case 3:
                        System.out.println("Which Number you want to be Less than? ");
                        int nmb3 = sc.nextInt();
                        dt = dt.stream().filter(ikeas -> {
                            return ikeas.get_Number() < nmb3;
                        }).collect(Collectors.toList());
                        break;
                    case 4:
                        System.out.println("Which Number you want to be greater than and equal to? ");
                        int nmb4 = sc.nextInt();
                        dt = dt.stream().filter(ikeas -> {
                            return ikeas.get_Number() >= nmb4;
                        }).collect(Collectors.toList());
                        break;
                    case 5:
                        System.out.println("Which Number you want to be Less than and equal to? ");
                        int nmb5 = sc.nextInt();
                        dt = dt.stream().filter(ikeas -> {
                            return ikeas.get_Number() <= nmb5;
                        }).collect(Collectors.toList());
                        break;
                    case 6:
                        System.out.println("Enter Left Boudnary");
                        int nmb6left = sc.nextInt();
                        System.out.println("Enter Right Boudnary");
                        int nmb6right = sc.nextInt();
                        dt = dt.stream().filter(ikeas -> {
                            return ikeas.get_Number() > nmb6left && ikeas.get_Number() < nmb6right;
                        }).collect(Collectors.toList());
                        break;
                    case 7:
                        dt = dt.stream().filter(ikeas -> {
                            return Integer.toString(ikeas.get_Number()) == null;
                        }).collect(Collectors.toList());
                        break;
                }
                break;
            case 2:
                switch (option) {
                    case 1:
                        System.out.println("Which Item_ID you want to look for? ");
                        int nmb1 = sc.nextInt();
                        dt = dt.stream().filter(ikeas -> {
                            return ikeas.get_Item_ID() == nmb1;
                        }).collect(Collectors.toList());
                        break;
                    case 2:
                        System.out.println("Which Item_ID you want to be greater than? ");
                        int nmb2 = sc.nextInt();
                        dt = dt.stream().filter(ikeas -> {
                            return ikeas.get_Item_ID() > nmb2;
                        }).collect(Collectors.toList());
                        break;
                    case 3:
                        System.out.println("Which Item_ID you want to be Less than? ");
                        int nmb3 = sc.nextInt();
                        dt = dt.stream().filter(ikeas -> {
                            return ikeas.get_Item_ID() < nmb3;
                        }).collect(Collectors.toList());
                        break;
                    case 4:
                        System.out.println("Which Item_ID you want to be greater than and equal to? ");
                        int nmb4 = sc.nextInt();
                        dt = dt.stream().filter(ikeas -> {
                            return ikeas.get_Item_ID() >= nmb4;
                        }).collect(Collectors.toList());
                        break;
                    case 5:
                        System.out.println("Which Item_ID you want to be Less than and equal to? ");
                        int nmb5 = sc.nextInt();
                        dt = dt.stream().filter(ikeas -> {
                            return ikeas.get_Item_ID() <= nmb5;
                        }).collect(Collectors.toList());
                        break;
                    case 6:
                        System.out.println("Enter Left Boudnary");
                        int nmb6left = sc.nextInt();
                        System.out.println("Enter Right Boudnary");
                        int nmb6right = sc.nextInt();
                        dt = dt.stream().filter(ikeas -> {
                            return ikeas.get_Item_ID() > nmb6left && ikeas.get_Item_ID() < nmb6right;
                        }).collect(Collectors.toList());
                        break;
                    case 7:
                        dt = dt.stream().filter(ikeas -> {
                            return Integer.toString(ikeas.get_Item_ID()) == null;
                        }).collect(Collectors.toList());
                        break;
                }
                break;
            case 5:
                switch (option) {
                    case 1:
                        System.out.println(
                                "Which Price you want to Look for? Enter with the Decimal Point!(comma sign)");
                        Double dp1 = sc.nextDouble();
                        dt = dt.stream().filter(ikeas -> {
                            return ikeas.get_Price() != 0 && ikeas.get_Price() == (dp1);
                            // return !ikeas.get_Price().isEmpty() && ikeas.get_Price() == (dp1);
                        }).collect(Collectors.toList());
                        break;
                    case 2:
                        System.out.println(
                                "Which Price you want to be greater than ? Enter with the Decimal Point!(comma sign)");
                        Double dp2 = sc.nextDouble();
                        dt = dt.stream().filter(ikeas -> {
                            return ikeas.get_Price() != 0 && ikeas.get_Price() > (dp2);
                        }).collect(Collectors.toList());
                        break;
                    case 3:
                        System.out.println(
                                "Which Price you want to be Less than ? Enter with the Decimal Point!(comma sign)");
                        Double dp3 = sc.nextDouble();
                        dt = dt.stream().filter(ikeas -> {
                            return ikeas.get_Price() != 0 && ikeas.get_Price() < (dp3);
                        }).collect(Collectors.toList());
                        break;
                    case 4:
                        System.out.println(
                                "Which Price you want to be Greater than and equal to ? Enter with the Decimal Point!(comma sign)");
                        Double dp4 = sc.nextDouble();
                        dt = dt.stream().filter(ikeas -> {
                            return ikeas.get_Price() != 0 && ikeas.get_Price() >= (dp4);
                        }).collect(Collectors.toList());
                        break;
                    case 5:
                        System.out.println(
                                "Which Price you want to be Less than and equal to ? Enter with the Decimal Point!(comma sign)");
                        Double dp5 = sc.nextDouble();
                        dt = dt.stream().filter(ikeas -> {
                            return ikeas.get_Price() != 0 && ikeas.get_Price() <= (dp5);
                        }).collect(Collectors.toList());
                        break;
                    case 6:
                        System.out.println(
                                "Enter Price for Left Boundary ? Enter with the Decimal Point!(comma sign)");
                        Double dp6left = sc.nextDouble();
                        System.out.println(
                                "Enter Price for Right Boundary ? Enter with the Decimal Point!(comma sign)");
                        Double dp6right = sc.nextDouble();
                        dt = dt.stream().filter(ikeas -> {
                            return ikeas.get_Price() != 0 && ikeas.get_Price() > (dp6left)
                                    && ikeas.get_Price() < (dp6right);
                        }).collect(Collectors.toList());
                        break;
                    case 7:
                        dt = dt.stream().filter(ikeas -> {
                            return ikeas.get_Price() == 0;
                        }).collect(Collectors.toList());
                        break;
                }
                break;
            case 6:
                switch (option) {
                    case 1:
                        System.out.println(
                                "Which Old_Price you want to Look for? Enter with the Decimal Point!(comma sign)");
                        Double dp1 = sc.nextDouble();
                        dt = dt.stream().filter(ikeas -> {
                            return !ikeas.get_Old_Price().isEmpty()
                                    && Double.parseDouble(ikeas.get_Old_Price()) == (dp1);
                        }).collect(Collectors.toList());
                        break;
                    case 2:
                        System.out.println(
                                "Which Old_Price you want to be greater than ? Enter with the Decimal Point!(comma sign)");
                        Double dp2 = sc.nextDouble();
                        dt = dt.stream().filter(ikeas -> {
                            return !ikeas.get_Old_Price().isEmpty()
                                    && Double.parseDouble(ikeas.get_Old_Price()) > (dp2);
                        }).collect(Collectors.toList());
                        break;
                    case 3:
                        System.out.println(
                                "Which Old_Price you want to be Less than ? Enter with the Decimal Point!(comma sign)");
                        Double dp3 = sc.nextDouble();
                        dt = dt.stream().filter(ikeas -> {
                            return !ikeas.get_Old_Price().isEmpty()
                                    && Double.parseDouble(ikeas.get_Old_Price()) < (dp3);
                        }).collect(Collectors.toList());
                        break;
                    case 4:
                        System.out.println(
                                "Which Old_Price you want to be Greater than and equal to ? Enter with the Decimal Point!(comma sign)");
                        Double dp4 = sc.nextDouble();
                        dt = dt.stream().filter(ikeas -> {
                            return !ikeas.get_Old_Price().isEmpty()
                                    && Double.parseDouble(ikeas.get_Old_Price()) >= (dp4);
                        }).collect(Collectors.toList());
                        break;
                    case 5:
                        System.out.println(
                                "Which Old_Price you want to be Less than and equal to ? Enter with the Decimal Point!(comma sign)");
                        Double dp5 = sc.nextDouble();
                        dt = dt.stream().filter(ikeas -> {
                            return !ikeas.get_Old_Price().isEmpty()
                                    && Double.parseDouble(ikeas.get_Old_Price()) <= (dp5);
                        }).collect(Collectors.toList());
                        break;
                    case 6:
                        System.out.println(
                                "Enter Price for Left Boundary ? Enter with the Decimal Point!(comma sign)");
                        Double dp6left = sc.nextDouble();
                        System.out.println(
                                "Enter Price for Right Boundary ? Enter with the Decimal Point!(comma sign)");
                        Double dp6right = sc.nextDouble();
                        dt = dt.stream().filter(ikeas -> {
                            return !ikeas.get_Old_Price().isEmpty()
                                    && Double.parseDouble(ikeas.get_Old_Price()) > (dp6left)
                                    && Double.parseDouble(ikeas.get_Old_Price()) < (dp6right);
                        }).collect(Collectors.toList());
                        break;
                    case 7:
                        dt = dt.stream().filter(ikeas -> {
                            return ikeas.get_Old_Price().isEmpty();
                        }).collect(Collectors.toList());
                        break;
                }
                break;
            case 12:
                switch (option) {
                    case 1:
                        System.out.println("Which Depth you want to look for? ");
                        int dpt1 = sc.nextInt();
                        dt = dt.stream().filter(ikeas -> {
                            return ikeas.get_Depth() != null && ikeas.get_Depth() == dpt1;
                        }).collect(Collectors.toList());
                        break;
                    case 2:
                        System.out.println("Which Depth you want to be greater than? ");
                        int nmb2 = sc.nextInt();
                        dt = dt.stream().filter(ikeas -> {
                            return ikeas.get_Depth() != null && ikeas.get_Depth() > nmb2;
                        }).collect(Collectors.toList());
                        break;
                    case 3:
                        System.out.println("Which Depth you want to be Less than? ");
                        int nmb3 = sc.nextInt();
                        dt = dt.stream().filter(ikeas -> {
                            return ikeas.get_Depth() != null && ikeas.get_Depth() < nmb3;
                        }).collect(Collectors.toList());
                        break;
                    case 4:
                        System.out.println("Which Depth you want to be greater than and equal to? ");
                        int nmb4 = sc.nextInt();
                        dt = dt.stream().filter(ikeas -> {
                            return ikeas.get_Depth() != null && ikeas.get_Depth() >= nmb4;
                        }).collect(Collectors.toList());
                        break;
                    case 5:
                        System.out.println("Which Depth you want to be Less than and equal to? ");
                        int nmb5 = sc.nextInt();
                        dt = dt.stream().filter(ikeas -> {
                            return ikeas.get_Depth() != null && ikeas.get_Depth() <= nmb5;
                        }).collect(Collectors.toList());
                        break;
                    case 6:
                        System.out.println("Enter Left Boudnary");
                        int nmb6left = sc.nextInt();
                        System.out.println("Enter Right Boudnary");
                        int nmb6right = sc.nextInt();
                        dt = dt.stream().filter(ikeas -> {
                            return ikeas.get_Depth() != null && ikeas.get_Depth() > nmb6left
                                    && ikeas.get_Depth() < nmb6right;
                        }).collect(Collectors.toList());
                        break;
                    case 7:
                        dt = dt.stream().filter(ikeas -> {
                            return ikeas.get_Depth() == null;
                        }).collect(Collectors.toList());
                        break;
                }
                break;
            case 13:
                switch (option) {
                    case 1:
                        System.out.println("Which Height you want to look for? ");
                        int dpt1 = sc.nextInt();
                        dt = dt.stream().filter(ikeas -> {
                            return ikeas.get_Height() != null && ikeas.get_Height() == dpt1;
                        }).collect(Collectors.toList());
                        break;
                    case 2:
                        System.out.println("Which Height you want to be greater than? ");
                        int nmb2 = sc.nextInt();
                        dt = dt.stream().filter(ikeas -> {
                            return ikeas.get_Height() != null && ikeas.get_Height() > nmb2;
                        }).collect(Collectors.toList());
                        break;
                    case 3:
                        System.out.println("Which Height you want to be Less than? ");
                        int nmb3 = sc.nextInt();
                        dt = dt.stream().filter(ikeas -> {
                            return ikeas.get_Height() != null && ikeas.get_Height() < nmb3;
                        }).collect(Collectors.toList());
                        break;
                    case 4:
                        System.out.println("Which Height you want to be greater than and equal to? ");
                        int nmb4 = sc.nextInt();
                        dt = dt.stream().filter(ikeas -> {
                            return ikeas.get_Height() != null && ikeas.get_Height() >= nmb4;
                        }).collect(Collectors.toList());
                        break;
                    case 5:
                        System.out.println("Which Height you want to be Less than and equal to? ");
                        int nmb5 = sc.nextInt();
                        dt = dt.stream().filter(ikeas -> {
                            return ikeas.get_Height() != null && ikeas.get_Height() <= nmb5;
                        }).collect(Collectors.toList());
                        break;
                    case 6:
                        System.out.println("Enter Left Boudnary");
                        int nmb6left = sc.nextInt();
                        System.out.println("Enter Right Boudnary");
                        int nmb6right = sc.nextInt();
                        dt = dt.stream().filter(ikeas -> {
                            return ikeas.get_Height() != null && ikeas.get_Height() > nmb6left
                                    && ikeas.get_Height() < nmb6right;
                        }).collect(Collectors.toList());
                        break;
                    case 7:
                        dt = dt.stream().filter(ikeas -> {
                            return ikeas.get_Height() == null;
                        }).collect(Collectors.toList());
                        break;
                }
                break;
            case 14:
                switch (option) {
                    case 1:
                        System.out.println("Which Width you want to look for? ");
                        int dpt1 = sc.nextInt();
                        dt = dt.stream().filter(ikeas -> {
                            return ikeas.get_Width() != null && ikeas.get_Width() == dpt1;
                        }).collect(Collectors.toList());
                        break;
                    case 2:
                        System.out.println("Which Width you want to be greater than? ");
                        int nmb2 = sc.nextInt();
                        dt = dt.stream().filter(ikeas -> {
                            return ikeas.get_Width() != null && ikeas.get_Width() > nmb2;
                        }).collect(Collectors.toList());
                        break;
                    case 3:
                        System.out.println("Which Width you want to be Less than? ");
                        int nmb3 = sc.nextInt();
                        dt = dt.stream().filter(ikeas -> {
                            return ikeas.get_Width() != null && ikeas.get_Width() < nmb3;
                        }).collect(Collectors.toList());
                        break;
                    case 4:
                        System.out.println("Which Width you want to be greater than or equal to? ");
                        int nmb4 = sc.nextInt();
                        dt = dt.stream().filter(ikeas -> {
                            return ikeas.get_Width() != null && ikeas.get_Width() >= nmb4;
                        }).collect(Collectors.toList());
                        break;
                    case 5:
                        System.out.println("Which Width you want to be Less than or equal to? ");
                        int nmb5 = sc.nextInt();
                        dt = dt.stream().filter(ikeas -> {
                            return ikeas.get_Width() != null && ikeas.get_Width() <= nmb5;
                        }).collect(Collectors.toList());
                        break;
                    case 6:
                        System.out.println("Enter Left Boudnary");
                        int nmb6left = sc.nextInt();
                        System.out.println("Enter Right Boudnary");
                        int nmb6right = sc.nextInt();
                        dt = dt.stream().filter(ikeas -> {
                            return ikeas.get_Width() != null && ikeas.get_Width() > nmb6left
                                    && ikeas.get_Width() < nmb6right;
                        }).collect(Collectors.toList());
                        break;
                    case 7:
                        dt = dt.stream().filter(ikeas -> {
                            return ikeas.get_Width() == null;
                        }).collect(Collectors.toList());
                        break;
                }
                break;
            case 3:
                switch (option) {
                    case 1:
                        System.out.println("Which Name you want to look for? ");
                        sc.nextLine();
                        String nm = sc.nextLine();
                        dt = dt.stream().filter(ikeas -> {
                            return !ikeas.get_Name().isEmpty() && ikeas.get_Name().contains(nm);
                        }).collect(Collectors.toList());
                        break;
                    case 2:
                        dt = dt.stream().filter(ikeas -> {
                            return ikeas.get_Name().isEmpty();
                        }).collect(Collectors.toList());
                        break;

                }
                break;
            case 4:
                switch (option) {
                    case 1:
                        System.out.println("Which Category you want to look for? ");
                        sc.nextLine();
                        String nm = sc.nextLine();
                        dt = dt.stream().filter(ikeas -> {
                            return !ikeas.get_Category().isEmpty() && ikeas.get_Category().contains(nm);
                        }).collect(Collectors.toList());
                        break;
                    case 2:
                        dt = dt.stream().filter(ikeas -> {
                            return ikeas.get_Category().isEmpty();
                        }).collect(Collectors.toList());
                        break;
                }
                break;
            case 8:
                switch (option) {
                    case 1:
                        System.out.println("Which Link you want to look for? ");
                        sc.nextLine();
                        String nm = sc.nextLine();
                        dt = dt.stream().filter(ikeas -> {
                            return !ikeas.get_Link().isEmpty() && ikeas.get_Link().contains(nm);
                        }).collect(Collectors.toList());
                        break;
                    case 2:
                        dt = dt.stream().filter(ikeas -> {
                            return ikeas.get_Link().isEmpty();
                        }).collect(Collectors.toList());
                        break;

                }
                break;
            case 10:
                switch (option) {
                    case 1:
                        System.out.println("Which Short Descrition you want to look for? ");
                        sc.nextLine();
                        String nm = sc.nextLine();
                        dt = dt.stream().filter(ikeas -> {
                            return !ikeas.get_short_Desciption().isEmpty() && ikeas.get_short_Desciption().contains(nm);
                        }).collect(Collectors.toList());
                        break;
                    case 2:
                        dt = dt.stream().filter(ikeas -> {
                            return ikeas.get_short_Desciption().isEmpty();
                        }).collect(Collectors.toList());
                        break;
                }
                break;
            case 11:
                switch (option) {
                    case 1:
                        System.out.println("Which Designer you want to look for? ");
                        sc.nextLine();
                        String nm = sc.nextLine();
                        dt = dt.stream().filter(ikeas -> {
                            return !ikeas.get_designer().isEmpty() && ikeas.get_designer().contains(nm);
                        }).collect(Collectors.toList());
                        break;
                    case 2:
                        dt = dt.stream().filter(ikeas -> {
                            return ikeas.get_designer().isEmpty();
                        }).collect(Collectors.toList());
                        break;
                }
            case 7:
                switch (option) {
                    default:
                        System.out.println(
                                "Do you wanna look for sellable online or not?\n1->Sellable\n2->Not Sellable\n(Enter Corresponding Numeric Value\n");
                        int so = sc.nextInt();
                        boolean so1;
                        try {
                            if (so == 1)
                                so1 = true;
                            else if (so == 2)
                                so1 = false;
                            else
                                throw new Exception();
                            dt = dt.stream().filter(ikeas -> {
                                return Boolean.toString(ikeas.get_Sellable_Online()) != null
                                        && ikeas.get_Sellable_Online().equals(so1);
                            }).collect(Collectors.toList());
                        } catch (Exception e) {
                            System.out.println("Wrong Input");
                        }
                        break;
                }
                break;
            case 9:
                switch (option) {
                    default:
                        System.out.println(
                                "Do you wanna look for products with other colors?\n1->Sellable\n2->Not Sellable\n(Enter Corresponding Numeric Value\n");
                        int oc = sc.nextInt();
                        boolean oc1;
                        try {
                            if (oc == 1)
                                oc1 = true;
                            else if (oc == 2)
                                oc1 = false;
                            else
                                throw new Exception();
                            dt = dt.stream().filter(ikeas -> {
                                return !ikeas.get_Other_Colors().isEmpty() && Boolean.parseBoolean(
                                        ikeas.get_Other_Colors().contains("yes") ? "true" : "false") == oc1;
                            }).collect(Collectors.toList());
                        } catch (Exception e) {
                            System.out.println("Wrong Input");
                        }
                        break;
                }
        }
        return dt;
    }
}