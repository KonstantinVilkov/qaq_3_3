
import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class DataGenerator {
    private DataGenerator() {}

    // пользователь с валидными данными
    public static InfoUser createValidUserInfo () {
        Faker faker = new Faker(new Locale("ru"));
        return new InfoUser(getAdministrativeCenter(),
                faker.name().fullName(),
                faker.phoneNumber().phoneNumber()
        );
    }

    // пользователь с невалидным телефоном
    public static InfoUser createUserInvalidPhone () {
        Faker faker = new Faker(new Locale("ru"));
        return new InfoUser(
                getAdministrativeCenter(),
                faker.name().fullName(),
                getInvalidPhoneNumber()
        );
    }

    // пользователь с невалидным именем
    public static InfoUser createUserInvalidFullName () {
        Faker faker = new Faker(new Locale("ru"));
        return new InfoUser(
                getAdministrativeCenter(),
                getInvalidFullName(),
                faker.phoneNumber().phoneNumber()
        );
    }

    // пользователь с невалидным городом
    public static InfoUser createInvalidCity () {
        Faker faker = new Faker(new Locale("ru"));
        return new InfoUser(getInvalidCity(),
                faker.name().fullName(),
                faker.phoneNumber().phoneNumber()
        );
    }

    // валидные города - административные центры
    public static String getAdministrativeCenter() {
        List<String> administrativeCenters = new ArrayList<>();
        administrativeCenters.add("Москва");
        administrativeCenters.add("Волгоград");
        administrativeCenters.add("Санкт-Петербург");
        administrativeCenters.add("Ростов-на-Дону");
        administrativeCenters.add("Краснодар");
        administrativeCenters.add("Владивосток");
        administrativeCenters.add("Новосибирск");

        Random random = new Random();
        int index = random.nextInt(7);

        return administrativeCenters.get(index);
    }

    // невалидные номера телефона
    public static String getInvalidPhoneNumber() {
        List<String> invalidPhones = new ArrayList<>();
        invalidPhones.add("+41112566891");
        invalidPhones.add("+790256688");

        Random random = new Random();
        int index = random.nextInt(2);

        return invalidPhones.get(index);
    }

    //невалидное ФИО
    public static String getInvalidFullName() {
        List<String> invalidFullName = new ArrayList<>();
        invalidFullName.add("Ivan Ivanov");
        invalidFullName.add("Иван Ivanov");
        invalidFullName.add("Иван Иванов1");

        Random random = new Random();
        int index = random.nextInt(3);

        return invalidFullName.get(index);
    }

    // невалидный город - не административный центр
    public static String getInvalidCity() {
        List<String> administrativeCenters = new ArrayList<>();
        administrativeCenters.add("Новокузнецк");
        administrativeCenters.add("Moscow");
        administrativeCenters.add("Краснодар/");
        administrativeCenters.add("Владивосток1");
        administrativeCenters.add("Ростов-na-Дону");

        Random random = new Random();
        int index = random.nextInt(5);

        return administrativeCenters.get(index);
    }

    // минимальное количество дней до доставки
    public static int getMinimumDaysToDelivery() {
        return 3;
    }

    // самая близкая подходящая дата
    public static LocalDate getEarliestValidDate() {
        return LocalDate.now().plusDays(getMinimumDaysToDelivery());
    }

    //строка с датой
    public static long getTimeStampString(LocalDate date) {
        ZoneId zoneId = ZoneId.systemDefault();
        return date.atStartOfDay(zoneId).toEpochSecond();
    }
}
