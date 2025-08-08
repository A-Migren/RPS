import java.util.*;


public class attestation01 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        List<Person> persons = new ArrayList<>();

        // Ввод покупателей
        while (true) {
            System.out.println("Введите имя покупателя или 'end' для завершения:");
            String name = scanner.nextLine().trim();
            if (name.equalsIgnoreCase("end")) {
                break;
            }

            System.out.println("Введите сумму денег у " + name + ":");
            String moneyInput = scanner.nextLine().trim();
            double money;
            try {
                money = Double.parseDouble(moneyInput);
            } catch (NumberFormatException e) {
                System.out.println("Некорректный ввод суммы. Попробуйте снова.");
                continue; // возвращаемся к следующему имени
            }

            Person person = new Person(name, money);
            persons.add(person);
        }




// Ввод продуктов:
        Map<String, Product> productsCatalog = new HashMap<>();
// Можно заранее задать или вводить динамически. Для примера:
        productsCatalog.put("Хлеб", new Product("Хлеб", 40));
        productsCatalog.put("Молоко", new Product("Молоко", 60));
        productsCatalog.put("Торт", new Product("Торт", 1000));
        productsCatalog.put("Кофе растворимый", new Product("Кофе растворимый", 879));
        productsCatalog.put("Масло", new Product("Масло", 150));
        System.out.println("Доступные продукты:");
        for (Map.Entry<String, Product> entry : productsCatalog.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue().getCost() + " рублей");
        }

// Цикл покупки:
        while (true) {
            for (Person person : persons) {
                System.out.println(person.getName() + ", начинаем покупки. Введите название продукта или END:");
                while (true) {
                    String input = scanner.nextLine().trim();
                    if (input.equalsIgnoreCase("END")) break;

                    Product product = productsCatalog.get(input);
                    if (product == null) {
                        System.out.println("Продукт не найден");
                        continue;
                    }

                    boolean bought = person.addProduct(product);
                    if (bought) {
                        System.out.println(person.getName() + " купил " + product.getName());
                    } else {
                        System.out.println(person.getName() + " не может позволить себе " + product.getName());
                    }
                }
            }

            // Спрашиваем, хотим ли мы продолжить покупки или завершить
            System.out.println("Хотите продолжить покупки? Введите 'да' или 'нет':");
            String answer = scanner.nextLine().trim();
            if (!answer.equalsIgnoreCase("да")) {
                break; // Выходим из внешнего цикла
            }
        }

// Вывод итогов:
        for (Person p : persons) {
            System.out.println(p.toString());
        }
    }
    }

class Person {
    private String name;
    private double money;
    private List<Product> productPackage;

    public Person(String name, double money) {
        setName(name);
        setMoney(money);
        this.productPackage = new ArrayList<>();
    }

    public String getName() { return name; }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Имя не может быть пустым");
        }
        if (name.length() < 3) {
            throw new IllegalArgumentException("Имя не может быть короче 3 символов");
        }
        this.name = name;
    }

    public double getMoney() { return money; }

    public void setMoney(double money) {
        if (money < 0) {
            throw new IllegalArgumentException("Деньги не могут быть отрицательными");
        }
        this.money = money;
    }

    public List<Product> getProductPackage() { return productPackage; }

    // Метод для добавления продукта в пакет
    public boolean addProduct(Product product) {
        if (product.getCost() <= this.money) {
            this.productPackage.add(product);
            this.money -= product.getCost();
            return true;
        } else {
            return false; // Не хватает денег
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Покупателя зовут ").append(name)
                .append(", в кошельке ").append(money)
                .append(", купленные продукты: ");

        if (productPackage.isEmpty()) {
            sb.append("ничего");
        } else {
            for (Product p : productPackage) {
                sb.append(p.getName()).append(", ");
            }
            // Удаляем последнюю запятую и пробел
            sb.setLength(sb.length() - 2);
        }

        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person Person = (Person) o;
        return Double.compare(Person.money, money) == 0 && Objects.equals(name, Person.name) && Objects.equals(productPackage, Person.productPackage);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(name, money);
        result = 31 * result + Objects.hashCode(productPackage);
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