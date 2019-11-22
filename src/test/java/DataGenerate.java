import com.github.javafaker.Faker;
import java.util.List;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

public class DataGenerate {
    private DataGenerate() {}

    public static InfoUser createValidInfoUser () {
        Faker faker = new Faker(new Locale("ru"));
        return new InfoUser(
                getAdministrativeCenter(),
                faker.name().fullName(),
                faker.phoneNumber().phoneNumber()
        );
    }

    public static InfoUser createUserInvalidPhone () {
        Faker faker = new Faker(new Locale("ru"));
        return new InfoUser(
                getAdministrativeCenter(),
                faker.name().fullName(),
                getInvalidPhoneNumber()
        );
    }

    public static String getAdministrativeCenter() {
        List<String> administrativeCenters = new ArrayList<>();
        administrativeCenters.add("Санкт-Петербург");
        administrativeCenters.add("Москва");
        administrativeCenters.add("Кемерово");
        administrativeCenters.add("Екатеринбург");
        administrativeCenters.add("Челябинск");
        administrativeCenters.add("Владивосток");
        administrativeCenters.add("Новосибирск");

        Random random = new Random();
        int index = random.nextInt(7);

        return administrativeCenters.get(index);
    }

    public static String getInvalidAdministrativeCenter() {
        List<String> invalidAdministrativeCenters = new ArrayList<>();
        invalidAdministrativeCenters.add("Новокузнецк");
        invalidAdministrativeCenters.add("Moscow");
        invalidAdministrativeCenters.add("Кемерово1");
        invalidAdministrativeCenters.add("Санкт-Петербург\\");

        Random random = new Random();
        int index = random.nextInt(4);

        return invalidAdministrativeCenters.get(index);
    }


    public static String getInvalidPhoneNumber() {
        List<String> invalidPhones = new ArrayList<>();
        invalidPhones.add("+01234567890");
        invalidPhones.add("+7999999999");
        invalidPhones.add("+799999999999");

        Random random = new Random();
        int index = random.nextInt(3);

        return invalidPhones.get(index);
    }

    public  static int getMinDaysToDelivery(){
        return 3;
    }

}
