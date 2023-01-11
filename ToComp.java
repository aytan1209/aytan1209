import java.util.*;
public class ToComp implements Comparator<ikeas>{
    public int fld;  //1
    public ToComp(int fld){
        this.fld= fld;
    }

    @Override
    public int compare(ikeas a, ikeas b){
        if(fld==1) return (int)(a.get_Number()- b.get_Number());
        if(fld==2) return (int)(a.get_Item_ID()- b.get_Item_ID());
        if(fld==3) return a.get_Name().compareTo(b.get_Name());
        if(fld==4) return a.get_Category().compareTo(b.get_Category());
        if(fld==5) return (int)(Double.compare(a.get_Price(), b.get_Price()));

        if(fld==6) {
            if(a.get_Old_Price().isEmpty() && b.get_Old_Price().isEmpty()) return 0;
            if(a.get_Old_Price().isEmpty()| a.get_Old_Price().contains("No")) return 1;
            if(b.get_Old_Price().isEmpty()| b.get_Old_Price().contains("No")) return -1;
            System.out.println(a.get_Item_ID()+ "    "+b.get_Item_ID());
            String S1= a.get_Old_Price().replaceAll("\"", "");
            String S2= b.get_Old_Price().replaceAll("\"", "");
            
            System.out.println(S1+"    "+S2);
            S1= S1.replaceAll("[^\\d,/]", "");
            S2= S2.replaceAll("[^\\d,/]", "");
            System.out.println(S1+"    "+S2);
            
            S1= S1.replaceAll(",", "");
            S2= S2.replaceAll(",", "");

            S1= S1.replaceAll("/", "");
            S2= S2.replaceAll("/", "");
            System.out.println(S1+"    "+S2);

            Double a1= Double.parseDouble(S1);
            Double a2= Double.parseDouble(S2);

            return (int)(Double.compare(a1,a2));
        }
        if(fld==7) return a.get_Sellable_Online().compareTo(b.get_Sellable_Online());
        if(fld==8) return a.get_Link().compareTo(b.get_Link());
        if(fld==9) return a.get_Other_Colors().compareTo(b.get_Other_Colors());
        if(fld==10) return a.get_short_Desciption().compareTo(b.get_short_Desciption());
        if(fld==11) return a.get_designer().compareTo(b.get_designer());
        if(fld==12) {
            if(a.get_Depth() == null && b.get_Depth() == null) return 0;
            if(a.get_Depth() == null) return 1;
            if(b.get_Depth() == null) return -1;
            else 
            return (int)(Double.compare(a.get_Depth(), b.get_Depth()));
        }
        if(fld==13) {
        if(a.get_Height() == null && b.get_Height() == null) return 0;
            if(a.get_Height() == null) return 1;
            if(b.get_Height() == null) return -1;
            else 
            return (int)(Double.compare(a.get_Height(), b.get_Height()));
    }
        if(fld==14) {
            if(a.get_Width() == null && b.get_Width() == null) return 0;
            if(a.get_Width() == null) return 1;
            if(b.get_Width() == null) return -1;
            else 
            return (int)(Double.compare(a.get_Width(), b.get_Width()));
        }

        else return 0;
    }
}
