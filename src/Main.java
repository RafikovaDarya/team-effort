import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] products = {"Молоко", "Хлеб", "Гречневая крупа", "Сыр", "Бекон"};
        int[] prices = {50, 14, 80, 35, 155};
        int foodBasket[] = new int[products.length];
        int sumNumb[] = new int[products.length];
        String[] sale3for2 = {null, null, "Гречневая крупа", null, "Бекон"};


        System.out.println("Список возможных товаров для покупки:");
        for (int i = 0; i < products.length; i++) {
            System.out.println((i + 1) + " " + products[i] +
                    " " + prices[i] + " руб/шт");
        }
        System.out.println("*****");
        System.out.println("Товары по акции  \"3 по цене 2х\": ");
        for (int i = 0; i < products.length; i++) {
            if (sale3for2[i] != null) {
                System.out.println(sale3for2[i]);
            }
        }


        int sumProducts = 0;

        while (true) {

            System.out.println("Выберите товар и количество или введите end");
            String input = scanner.nextLine();

            if ("end".equals(input)) {
                break;
            }
            String[] parts = input.split(" ");
            if (parts.length != 2) {
                System.out.println("Укажите 2 значения: 1 - продукт, 2 - количество.");
                continue;
            }

            int productNumber;
            int productCount;

            try {
                productNumber = Integer.parseInt(parts[0]) - 1;
                productCount = Integer.parseInt(parts[1]);
            } catch (NumberFormatException n) {
                System.out.println("Введены некорректные символы: " + n);
                continue;
            }

            if (productNumber > products.length || productNumber < 0) {
                System.out.println("Некорректно введен номер продукта");
                continue;
            }
            if (productCount < 0) {
                System.out.println("Некорректно введено количество продукта");
                continue;
            }

            foodBasket[productNumber] += productCount;
        }

        System.out.println("Ваша корзина:");
        for (int i = 0; i < products.length; i++) {

            if (foodBasket[i] >= 3 && sale3for2[i] != null) {
                int goodsSale = foodBasket[i] / 3;
                sumNumb[i] = (foodBasket[i] - goodsSale) * prices[i];
            } else if (foodBasket[i] > 0) {
                sumNumb[i] = foodBasket[i] * prices[i];
            }
            sumProducts += sumNumb[i];
        }
        for (int i = 0; i < products.length; i++) {
            if (foodBasket[i] > 0 && sale3for2[i] != null) {
                System.out.println("Цена по акции: ");
            }
            if (foodBasket[i] > 0) {
                System.out.println(products[i] + " " + foodBasket[i] + " шт " +
                        prices[i] + " руб/шт " + sumNumb[i] + " в сумме");
            }
        }

        System.out.println("Итого: " + sumProducts + " руб");
    }
}