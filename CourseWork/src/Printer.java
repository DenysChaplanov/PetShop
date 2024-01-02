import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.PatternSyntaxException;

public class Printer {
    private final Magazine shop;
    private final Scanner scanner;

    public static void main(String[] args) {
        Printer Interface =new Printer();
        Interface.mainMenu();

    }
    public Printer() {
        this.shop = new Magazine();
        this.scanner = new Scanner(System.in);
    }

    public void animalsMenu(){
        int menu=-1;
        boolean exit=false;
        while (!exit){
            System.out.println("\nМЕНЮ ЖИВОТНЫХ");
            System.out.println("1. Добавить");
            System.out.println("2. Удалить");
            System.out.println("3. Показать всех");
            System.out.println("4. Найти");
            System.out.println("0. Выйти");
            try{
                menu = scanner.nextInt();
            }catch (InputMismatchException e){
                menu=-1;
            }
            scanner.nextLine();
            switch (menu) {
                case 1 -> addAnimal();
                case 2 -> removeAnimal();
                case 3 -> showAllAnimals();
                case 4 -> findAnimals();
                case 0 -> exit = true;
                default -> System.out.println("Введены неверные данные, попробуйте ещё раз");
            }
        }

    }

    public void addAnimal(){
        System.out.println("\nДОБАВИТЬ ЖИВОТНОЕ");
        String name;
        int age;
        String breed;
        ArrayList<String> diet;
        String appetizing;
        int price;

        System.out.println("Введите имя: ");
        while (true){
            name=scanner.nextLine();
            if(getShop().isValidName(name)){
                break;
            }else System.out.println("Некоректное имя, попробуйте ещё раз");
        }

        System.out.println("Введите возраст: ");
        while (true){
            try {
                age=scanner.nextInt();
                if(age<=0) throw new InputMismatchException();
                break;
            }catch (InputMismatchException e){
                System.out.println("Некоректный возраст, попробуйте ещё раз");
            }
            scanner.nextLine();
        }
        breed=getBreed();
        System.out.println("Введите рацион питания: ");
        diet=getDietArray();
        System.out.println("Введите любимый деликатес: ");
        while (true){
            appetizing =scanner.nextLine();
            if(shop.isValidName(appetizing)){
                break;
            }else System.out.println("Некоректный введён деликатес, попробуйте ещё раз");
        }

        System.out.println("Введите стоимость: ");
        while (true){
            try {
                price=scanner.nextInt();
                if(price<=0) throw new InputMismatchException();
                break;
            }catch (InputMismatchException e){
                System.out.println("Некоректная стоимость, попробуйте ещё раз");
            }
            scanner.nextLine();
        }

        shop.addAnimal(new Animal(name,breed, age, diet, appetizing, price));
    }

    private Magazine getShop() {
        return shop;
    }

    private String getBreed(){
        String str;
        System.out.println("Введите породу животного:");
        while (true){
            str=scanner.nextLine();
            if(shop.isValidName(str)){
                return str;
            }else{
                System.out.println("Некоректно введёна порода, попробуйте ещё раз");
            }
        }
    }

    private ArrayList<String> getDietArray(){
        ArrayList<String> result=new ArrayList<String>();
        String str;
        System.out.println("Введите название продукта, чтобы добавить его в рацион (0 для выхода)");
        while (true) {
            str = scanner.nextLine();
            if(str.equals("0")){
                return result;
            }else if(str.equalsIgnoreCase("")) {
                System.out.println("Некоректо введёно название продукта, попробуйте ещё раз");
            }else{
                result.add(str);
                System.out.println("Продукт добавлен! Введите следующий продукт (0 для выхода)");
            }
        }
    }

    public void removeAnimal(){
        String str;
        int num=-1;
        boolean exit=false;
        System.out.println("\nУДАЛИТЬ ЖИВОТНОЕ");
        System.out.println("Введите имя или номер животного для удаления(0 для выхода)");

        while (!exit) {
            str = scanner.nextLine();
            try {
                num = Integer.parseInt(str);

                if (num == 0) return;

                if (shop.deleteAnimal(num-1)) {
                    System.out.println("Успешно удалён");
                    exit=true;
                }else {
                    System.out.println("Ошибка при удалении; проверьте индекс и попробуйте ещё раз (0 для выхода)");
                    exit=false;
                }
            } catch (NumberFormatException e) {
                if (shop.deleteAnimal(str)) {
                    System.out.println("Успешно удалён");
                    exit=true;
                }else {
                    System.out.println("Ошибка при удалении; проверьте имя и попробуйте ещё раз (0 для выхода)");
                    exit=false;
                }
            }
        }
    }

    public void showAllAnimals(){
        System.out.println("\nСПИСОК ВСЕХ ЖИВОТНЫХ:");
        ArrayList<Animal> result=shop.getAnimals();
        if(result.size()!=0){
            for(int i=0;i<result.size();i++){
                System.out.println("#"+(i+1));
                System.out.println(result.get(i).toString());
            }
        }else {
            System.out.println("Животные не найдены");
        }
    }

    public void findAnimals(){
        boolean exit=false;
        int num=0;
        System.out.println("Поиск по породе (0 для выхода)");
        System.out.println("Введите название породы");
        while (!exit) {
            String str=scanner.nextLine();
            try {
                num = Integer.parseInt(str);
                scanner.nextLine();
                if (num == 0) return;//0 for exit
                else System.out.println("Не верная порода, попробуйте ещё раз (0 для выхода)");

            } catch (NumberFormatException e) {
                try {
                    ArrayList<Animal> result = shop.findAnimals(str);
                    if(result.size()!=0){
                        System.out.println("Результат поиска:");
                        for (int i = 0; i < result.size(); i++) {
                            System.out.println("Животное #" + (i + 1));
                            System.out.println(result.get(i).toString());
                        }
                    }else{
                        System.out.println("Не найдено");
                    }

                    exit=true;
                } catch (PatternSyntaxException ex) {
                    System.out.println("Не верные данные, попробуйте ещё раз (0 для выхода)");
                    exit=false;
                }
            }
        }
    }

    public void customersMenu(){
        int menu=-1;
        boolean exit=false;
        while (!exit){
            System.out.println("\nМЕНЮ ПОКУПАТЕЛЯ");
            System.out.println("1. Добавить покупателя");
            System.out.println("2. Удалить покупателя");
            System.out.println("3. Список всех покупателей");
            System.out.println("0. Выйти");
            try{
                menu = scanner.nextInt();
            }catch (InputMismatchException e){
                menu=-1;
            }
            scanner.nextLine();
            switch (menu) {
                case 1 -> addCustomer();
                case 2 -> removeCustomer();
                case 3 -> showAllCustomer();
                case 0 -> exit = true;
                default -> System.out.println("Введены неверные данные, попробуйте ещё раз");
            }
        }
    }

    private void addCustomer(){
        System.out.println("\nДОБАВИТЬ ПОКУПАТЕЛЯ");
        String name;
        String surname;
        Date dateBirth;
        String passport;
        String str;

        System.out.println("Введите имя: ");
        while (true){
            name=scanner.nextLine();
            if(shop.isValidName(name)){
                break;
            }else System.out.println("Некоректное имя, попробуйте ещё раз");
        }

        System.out.println("Введите фамилию: ");
        while (true){
            surname=scanner.nextLine();
            if(shop.isValidName(surname)){
                break;
            }else System.out.println("Некоректная фамилия, попробуйте ещё раз");
        }

        System.out.println("Введите дату рождения (день-месяц-год): ");
        while (true){
            str=scanner.nextLine();
            try {
                if(!shop.isValidDate(str)) throw new ParseException("Некоректный ввод",0);
                dateBirth=(new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH)).parse(str);
                break;
            } catch (ParseException e) {
                System.out.println("Некоректный формат, попробуйте ещё раз");
            }
        }
        System.out.println("Введите № паспорта: ");
        while (true){
            passport=scanner.nextLine();
            if(!passport.equalsIgnoreCase("")){
                break;
            }else System.out.println("Некоректный № паспорта, попробуйте ещё раз");
        }


        shop.addCustomer(new Customer(name,surname, dateBirth, passport));
    }

    private void removeCustomer(){
        String str;
        int num=-1;
        boolean exit=false;
        System.out.println("\nУДАЛИТЬ ПОКУПАТЕЛЯ");
        System.out.println("Введите имя или индекс покупателя для удаления (0 для выхода)");

        while (!exit) {
            str = scanner.nextLine();
            try {
                num = Integer.parseInt(str);

                if (num == 0) return;

                if (shop.deleteCustomer(num-1)) {
                    System.out.println("Удачно удалён");
                    exit=true;
                }else {
                    System.out.println("Ошибка при удалении; проверьте индекс и попробуйте ещё раз (0 для выхода)");
                    exit=false;
                }
            } catch (NumberFormatException e) {
                if (shop.deleteCustomer(str)) {
                    System.out.println("Удачно удалён");
                    exit=true;
                }else {
                    System.out.println("Ошибка при удалении; проверьте имя и попробуйте ещё раз (0 для выхода)");
                    exit=false;
                }
            }
        }
    }

    private void showAllCustomer(){
        System.out.println("\nСПИСОК ВСЕХ ПОКУПАТЕЛЕЙ:");
        ArrayList<Customer> result=shop.getCustomers();
        if(result.size()!=0){
            for(int i=0;i<result.size();i++){
                System.out.println("#"+(i+1)+" Покупатель:");
                System.out.println(result.get(i).toString());
            }
        }else{
            System.out.println("Покупатели не найдены");
        }
    }


    public void sellAnimal(){
        boolean exit=false;
        int indexAnimal=-1;
        int indexCustomer=-1;
        String str;
        System.out.println("\nПРОДАТЬ ЖИВОТНОЕ");
        System.out.println("Введите индекс животного, которого хотите продать(0 для выхода)");

        while (!exit) {
            str = scanner.nextLine();
            try {
                indexAnimal = Integer.parseInt(str);

                if (indexAnimal == 0) return;
                indexAnimal--;
                if(indexAnimal<0||indexAnimal>=shop.getAnimals().size()){
                    throw new IndexOutOfBoundsException(indexAnimal);
                }else{
                    exit=true;
                }
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                System.out.println("Ошибка при продаже; проверьте индекс и попробуйте ещё раз (0 для выхода)");
                exit=false;
            }
        }
        System.out.println("Введите индекс покупателя(0 для выхода)");
        exit=false;
        while (!exit) {
            str = scanner.nextLine();
            try {
                indexCustomer = Integer.parseInt(str);

                if (indexCustomer == 0) return;
                indexCustomer--;
                if(indexCustomer<0||indexCustomer>=shop.getCustomers().size()){
                    throw new IndexOutOfBoundsException(indexCustomer);
                }else{
                    exit=true;
                }
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                System.out.println("Ошибка при продаже; проверьте индекс и попробуйте ещё раз (0 для выхода)");
                exit=false;
            }
        }
        shop.sellAnimal(indexAnimal,indexCustomer);
        System.out.println("Успешно продано");
    }

    public void mainMenu(){
        boolean exit=false;
        int menu = -1;
        while (!exit) {
            menu = -1;
            System.out.println("\nГЛАВНОЕ МЕНЮ:");
            System.out.println("1. Меню животных");
            System.out.println("2. Меню клиектов");
            System.out.println("3. Продать животное");
            System.out.println("4. Ежедневная сумма продаж");
            System.out.println("5. Сохранить данные в файл");
            System.out.println("6. Загрузить данные из файла");
            System.out.println("0. Выйти");
            try{
                menu = scanner.nextInt();

            }catch (InputMismatchException e){
                menu=-1;
            }
            scanner.nextLine();
            switch (menu) {
                case 1:
                    animalsMenu();
                    break;
                case 2:
                    customersMenu();
                    break;
                case 3:
                    sellAnimal();
                    break;
                case 4:
                    getDailySales();
                    break;
                case 5:
                    try {
                        shop.saveRegistryToFile();
                        System.out.println("Имя файла: data.csv; Файл сохранён в папке приложения");
                    } catch (IOException e) {
                        System.out.println("Неверный файл");
                    }
                    break;
                case 6:
                    System.out.println("Имя файла: data.csv;");
                    try {
                        shop.loadRegistryFromFile();
                        System.out.println("Успешно загружено");
                    } catch (IOException e) {
                        System.out.println("Неверный файл; проверьте файл и попробуйте еще раз");
                    }
                    break;
                case 0:
                    exit=true;
                    break;
                default:
                    System.out.println("Введены неверные данные, попробуйте ещё раз");
                    break;
            }
        }
    }

    private void getDailySales() {
        Date d=new Date();
        System.out.println("\nЕЖЕДНЕВНАЯ СУММА ПРОДАЖ");
        System.out.println(shop.getDailySales(d));
    }

}
