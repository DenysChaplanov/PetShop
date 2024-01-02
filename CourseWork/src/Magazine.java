import java.io.*;
import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class Magazine {
    private String name;
    private String address;
    private ArrayList<Animal> animals;
    private ArrayList<Customer> customers;
    private ArrayList<Triplet<Animal,Customer,Date>> sales;

    public Magazine() {
        this.name="GoodPets";
        this.address ="USA";
        this.animals=new ArrayList<Animal>();
        this.sales=new ArrayList();
        this.customers=new ArrayList<>();
    }


    public void addAnimal(Animal animal){
        animals.add(animal);
    }

    public boolean deleteAnimal(String name){
        return animals.removeIf(n -> (name.equalsIgnoreCase(n.getName())));
    }

    public boolean deleteAnimal(int index){
        try {
            animals.remove(index);
            return true;
        }catch (IndexOutOfBoundsException e){
            return false;
        }
    }

    public ArrayList<Animal> findAnimals(String reg) throws PatternSyntaxException {
        reg=reg.toLowerCase();
        ArrayList<Animal> result=new ArrayList<>();
        for(Animal a: animals){
            if(Pattern.matches(reg,a.getBreed().toLowerCase()))
                result.add(a);
        }
        return result;
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }
    
    public void addCustomer(Customer customer){
        customers.add(customer);
    }

    public boolean deleteCustomer(String name){
        return customers.removeIf(n -> (name.equalsIgnoreCase(n.getName())));
    }

    public boolean deleteCustomer(int index){
        try {
            customers.remove(index);
            return true;
        }catch (IndexOutOfBoundsException e){
            return false;
        }
    }

    public boolean isValidName(String name){
        return Pattern.matches("[\\p{L}| ]+", name);
    }

    public boolean isValidDate(String date){
        //in breed only letters and whitespace
        return Pattern.matches("[0-9]{1,2}-[0-9]{1,2}-[0-9]{1,4}",date);
    }


    public void sellAnimal(int animal, int customer){
        sales.add(new Triplet<>(animals.remove(animal),customers.get(customer),new Date()));
    }

    public void saveRegistryToFile() throws IOException {
        FileWriter csvWriter = new FileWriter("data.csv");

        for (Animal a : animals) {
            csvWriter.append(a.toCsvString());
            csvWriter.append("\n");
        }
        csvWriter.flush();
        csvWriter.close();
    }

    public void loadRegistryFromFile() throws IOException {
        String pathToCsv="data.csv";
        BufferedReader csvReader = new BufferedReader(new FileReader(pathToCsv));
        String row;
        ArrayList<Animal> registry=new ArrayList<>();
        int index=0;

        String name;
        String breed;
        int age;
        int countProducts=0;
        ArrayList<String> diet;
        String appetizing;
        int price;

        while ((row = csvReader.readLine()) != null) {
            index=0;
            String[] data = row.split(",");
            name=data[index++];
            breed=data[index++];
            age = Integer.parseInt(data[index++]);
            countProducts=Integer.parseInt(data[index++]);

            diet = new ArrayList<>(Arrays.asList(data).subList(index, index + countProducts));
            index+=countProducts;
            appetizing =data[index++];
            price= Integer.parseInt(data[index]);
            registry.add(new Animal(name,breed, age,diet, appetizing,price));
        }
        animals=registry;

        csvReader.close();
    }

    public int getDailySales(Date date){
        int amount=0;
        GregorianCalendar currDate=new GregorianCalendar();
        currDate.setTime(date);
        currDate.set(Calendar.HOUR_OF_DAY, 0);
        currDate.set(Calendar.MINUTE, 0);
        currDate.set(Calendar.SECOND, 0);
        currDate.set(Calendar.MILLISECOND, 0);

        GregorianCalendar tmpDate=new GregorianCalendar();
        for(Triplet<Animal, Customer, Date> t: sales){
            tmpDate.setTime(t.getRight());
            tmpDate.set(Calendar.HOUR_OF_DAY, 0);
            tmpDate.set(Calendar.MINUTE, 0);
            tmpDate.set(Calendar.SECOND, 0);
            tmpDate.set(Calendar.MILLISECOND, 0);

            if(currDate.equals(tmpDate)){
                amount+=t.getLeft().getPrice();
            }
        }
        return amount;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public ArrayList<Animal> getAnimals() {
        return animals;
    }

}
