public class App1 {
    public static void main(String[] args) {
        int years = 4;
        int monthDate = 29;

        if (monthDate != 29) {
            System.out.println("Ошибка monthDate должна быть 29");
            return;
        }

        if (years < 4) {
            System.out.println("Ошибка years должен быть не меньше 4");
        }

        switch (years) {
            case 4, 7 -> System.out.println("Количество юбилеев: " + 1);

            case 8 -> System.out.println("Количество юбилеев: " + 2);

            default -> System.out.println("Количество юбилеев: 0");
        }
    }
}
