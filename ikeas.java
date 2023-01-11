
public class ikeas {
    private int Number; //1
    private int Item_ID; //2 
    private String Name;   //3 
    private String Category;  //4
    private Double Price;   //5
    private String Old_Price;  //6
    private Boolean Sellable_Online;   //7
    private String Link;    //8
    private String Other_Colors;   //9
    private String Short_Description;   //10 
    private String Designer;   //11
    private Double Depth;   //12
    private Double Height;   //13
    private Double Width;    //14


    public ikeas(int Number, int Item_ID, String Name, String Category, Double Price,
            String Old_Price, Boolean Sellable_Online, String Link, String Other_Colors, String Short_Description, 
            String Designer, Double Depth, Double Height, Double Width) {
        this.Number = Number;
        this.Item_ID = Item_ID;
        this.Name = Name;
        this.Category = Category;
        this.Price = Price;
        this.Old_Price = Old_Price;
        this.Sellable_Online = Sellable_Online;
        this.Link = Link;
        this.Other_Colors = Other_Colors;
        this.Short_Description = Short_Description;
        this.Designer = Designer;
        this.Depth = Depth;
        this.Height = Height;
        this.Width = Width;
    }
    public int get_Number(){
        return this.Number;
    }
    public int get_Item_ID(){
        return this.Item_ID;
    }
    public String get_Name(){
        return this.Name;
    }
    public String get_Category(){
        return this.Category;
    }
    public Double get_Price(){
        return this.Price;
    }
    public String get_Old_Price(){
        return this.Old_Price;
    }
    public Boolean get_Sellable_Online(){
        return this.Sellable_Online;
    }
    public String get_Link(){
        return this.Link;
    }
    public String get_Other_Colors(){
        return this.Other_Colors;
    }
    public String get_short_Desciption(){
        return this.Short_Description;
    }
    public String get_designer(){
        return this.Designer;
    }
    public Double get_Depth(){
        return this.Depth;
    }
    public Double get_Height(){
        return this.Height;
    }
    public Double get_Width(){
        return this.Width;
    }

    public void printobj(){
        System.out.println("Number: "+this.Number);
        System.out.println("Item_ID: "+this.Item_ID);
        System.out.println("Name: "+this.Name);
        System.out.println("Category: "+this.Category);
        System.out.println("Price: "+this.Price);
        System.out.println("Old_Price: "+this.Old_Price);
        System.out.println("Sellable_Online: "+this.Sellable_Online);
        System.out.println("Link: "+this.Link);
        System.out.println("Other_Colors: "+this.Other_Colors);
        System.out.println("Short_Description: "+this.Short_Description);
        System.out.println("Designer: "+this.Designer);
        System.out.println("Depth: "+this.Depth);
        System.out.println("Height: "+this.Height);
        System.out.println("Width: "+this.Width);
        System.out.println();
    }
    public String toLineofContent(){
        return this.Number+ "," +this.Item_ID+ "," +this.Name+ "," +this.Category+ "," +this.Price+ "," +this.Old_Price+ "," +this.Sellable_Online+ "," +this.Link
        + "," +this.Other_Colors + ","+ this.Short_Description+","+ this.Designer+","+this.Depth+","+this.Height+","+ this.Width;
    }
    public String toLineofContent_no_Category(){
        return this.Number+ "," +this.Item_ID+ "," +this.Name+"," +this.Price+ "," +this.Old_Price+ "," +this.Sellable_Online+ "," +this.Link
        + "," +this.Other_Colors + ","+ this.Short_Description+","+ this.Designer+","+this.Depth+","+this.Height+","+ this.Width;
    }
}
