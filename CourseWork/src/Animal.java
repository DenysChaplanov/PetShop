import java.util.ArrayList;

public class Animal {
    private String name;
    private String breed;
    private int age;
    private ArrayList<String> diet;
    private String appetizing;
    private int price;


    public Animal(String name, String breed, int age, ArrayList<String> diet, String appetizing, int price) {
        this.name = name;
        this.age = age;
        this.breed = breed;
        this.diet = diet;
        this.appetizing = appetizing;
        this.price=price;
    }

    public String toCsvString() {
        StringBuilder str= new StringBuilder(name + ',' + breed + ',' + age + ',' + diet.size());
        for(String s: diet)
            str.append(',').append(s);
        str.append(',').append(appetizing).append(',').append(price);
        return str.toString();
    }

    public void addProduct(String product){
        diet.add(product);
    }


    public void setName(String name) {
        this.name = name;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public void setBreed(String breed) {
        this.breed = breed;
    }
    public void setAppetizing(String appetizing) {
        this.appetizing = appetizing;
    }


    public int getAge(){
        return age;
    }
    public String getName() {
        return name;
    }
    public String getBreed() {
        return breed;
    }
    public ArrayList<String> getDiet() {
        return diet;
    }
    public String getAppetizing() {
        return appetizing;
    }
    public int getPrice() { return price; }


    @Override
    public String toString() {
        return "Имя: \"" + name + '\"' + ", Возраст: " + age + ", Порода: \"" + breed + '\"' + ", Рацион: " + diet + ", Деликатес: " + appetizing + ", Цена: "+price;
    }
}
