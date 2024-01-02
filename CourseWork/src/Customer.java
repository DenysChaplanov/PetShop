import java.text.SimpleDateFormat;
import java.util.Date;

public class Customer {
    private String name;
    private String surname;
    private Date dateOfBirth;
    private final String passport;

    public Customer(String name, String surname, Date dateOfBirth, String passport){
        this.name=name;
        this.surname=surname;
        this.dateOfBirth = dateOfBirth;
        this.passport=passport;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "Имя: \"" + name + '\"' +
                ", Фамили: \"" + surname + '\"' +
                ", Дата рождения: " + new SimpleDateFormat("dd.MM.yyyy").format(dateOfBirth) +
                ", № Паспорта:\"" + passport + '\"';
    }
}
