import java.util.Arrays;
import java.util.Objects;

public class attestation01 {
public static void main (String[] args){
}
}

class Person {
    private String name;
    private double money;
    private String[] productPackage;

    Person(String name, double money, String[] productPackage) {
        this.name = name;

        if (money < 0) {
            throw new
                    IllegalArgumentException("Сумма денег не может быть отрицательной!");
        }
        this.money = money;

        if (productPackage != null) { //Делаем эту проверку только если массив не пуст.

            for (String product : productPackage) {

                if (product == null || product.trim().length() < 3) {
                    throw new
                            IllegalArgumentException("Название продукта не должно быть пустым или содержать меньше трех символов!");
                }
            }


            this.productPackage = productPackage.clone();
        } else {
            this.productPackage = null;
        }

    }

    @Override
    public String toString() {
        return "Покупателя зовут " + name + '\'' + "В его кошельке лежит " + money + '\'' + "У него есть " + productPackage; //Делаем так, чтобы при обращении к классу Person выводилась нормальная читаемая информация, а не хэш.
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person Person = (Person) o;
        return Double.compare(Person.money, money) == 0 && Objects.equals(name, Person.name) && Arrays.equals(productPackage, Person.productPackage);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(name, money);
        result = 31 * result + Arrays.hashCode(productPackage);
        return result;
    }
}