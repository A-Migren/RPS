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
}