import java.util.*;

public class attestation01 {
    public static void main (String[] args){
    }
}

class Person {
    private String name;
    private double money;
    private list<Product> productPackage;

    public Person(String name, double money) {
        setName(name);
        setMonet(money);
        this.productPackage = new ArratList<>();
    }
 public String getName() { return name; }

    public void setName(String name) {
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

class Product{
   private String name;
   private double cost;

   public Product(String name, double cost) {

       if (name == null || name.trim().isEmpty())
           throw new IllegalArgumentException("Название продукта не может быть пустым");

       if (cost < 0)
           throw new IllegalArgumentException("Продукт не может стоит меньше 0");

       this.name = name;
       this.cost = cost;
   }

    public String getName()  {return name;}


    public void setName(String name) {
       if (name == null || name.trim().isEmpty()) {
           throw new IllegalArgumentException("Название продукта не может может быть пустым!");
        }
       this.name = name;

    }

    public double getCost(){
       return cost;
    }
    public void setCost(double cost){
       if(cost < 0){
           throw new IllegalArgumentException("Стоимость не может быть отрицательной!");
       }
       this.cost = cost;
    }

    @Override
    public String toString() {
       return name + "(" + cost + ")";
    }

    @Override
    public boolean equals(Object i) {
       if(this == i) return true;
       if(!(i instanceof Product)) return false;
       Product product = (Product) i;
       return Double.compare(product.cost, cost) == 00 && Objects.equals(name, product.name);
    }
    @Override
    public int hashCode(){return Objects.hash(name, cost);}

}