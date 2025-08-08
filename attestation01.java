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

        // Ввод продуктов в магазин
        Map<String, Product> productsCatalog = new HashMap<>();
        System.out.println("Введите количество продуктов для добавления в магазин:");
        int productCount;
        while (true) {
            String countInput = scanner.nextLine().trim();
            try {
                productCount = Integer.parseInt(countInput);
                if (productCount < 0) {
                    System.out.println("Количество не может быть отрицательным. Попробуйте снова:");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Некорректный ввод. Введите целое число:");
            }
        }

        for (int i = 0; i < productCount; i++) {
            System.out.println("Введите название продукта:");
            String productName = scanner.nextLine().trim();

            System.out.println("Введите стоимость продукта " + productName + ":");
            String costInput = scanner.nextLine().trim();
            double cost;
            try {
                cost = Double.parseDouble(costInput);
                if (cost < 0) {
                    System.out.println("Стоимость не может быть отрицательной. Попробуйте заново для этого продукта.");
                    i--;
                    continue;
                }
            } catch (NumberFormatException e) {
                System.out.println("Некорректный ввод стоимости. Попробуйте заново для этого продукта.");
                i--;
                continue;
            }

            Product product = new Product(productName, cost);
            productsCatalog.put(productName, product);
        }

        // Вывод доступных продуктов
        System.out.println("\nДоступные продукты:");
        for (Map.Entry<String, Product> entry : productsCatalog.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue().getCost() + " рублей");
        }

        // Цикл покупок с возможностью повторения
        while (true) {
            for (Person person : persons) {
                System.out.println("\n" + person.getName() + ", начинаем покупки. Введите название продукта или END:");
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

            // Спрашиваем о продолжении
            System.out.println("\nХотите продолжить покупки? Введите 'да' или 'нет':");
            String answer = scanner.nextLine().trim();
            if (!answer.equalsIgnoreCase("да")) {
                break; // Выходим из цикла покупок
            }
        }

        // Итоговые результаты
        System.out.println("\nИтоги покупок:");
        for (Person p : persons) {
            System.out.println(p.toString());
        }
    }
}

// Класс Person
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
            sb.setLength(sb.length() - 2); // Удаляем последнюю запятую и пробел
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) { /* оставляем как есть */
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return Double.compare(person.money, money) == 0 &&
                Objects.equals(name, person.name) &&
                Objects.equals(productPackage, person.productPackage);
    }

    @Override
    public int hashCode() { /* оставляем как есть */
        return Objects.hash(name, money, productPackage);
    }
}

// Класс Product
class Product{
    private String name;
    private double cost;

    public Product(String name, double cost) {

        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("Название продукта не может быть пустым");

        if (cost < 0)
            throw new IllegalArgumentException("Продукт не может стоить меньше 0");

        this.name = name;
        this.cost = cost;
    }

    public String getName() { return name; }

    public void setName(String name){
        if(name == null || name.trim().isEmpty()){
            throw new IllegalArgumentException("Название продукта не может быть пустым!");
        }
        this.name= name;
    }

    public double getCost(){ return cost; }

    public void setCost(double cost){
        if(cost<0){
            throw new IllegalArgumentException("Стоимость не может быть отрицательной!");
        }
        this.cost=cost;
    }

    @Override
    public String toString() { return name + "(" + cost + ")";}

    @Override
    public boolean equals(Object i){
        if(this==i)return true;
        if(!(i instanceof Product))return false;
        Product p= (Product)i;
        return Double.compare(p.cost,cost)==0 && Objects.equals(name,p.name);
    }

    @Override
    public int hashCode(){return Objects.hash(name,cost);}
}