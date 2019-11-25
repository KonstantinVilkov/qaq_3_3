
import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CardDeliveryTests {
    private String baseUrl = "http://localhost:9999/";

    @Test
    @DisplayName("Отображается нотификация 'Успешно' при вводе валидных данных")
    void shouldSuccessIfDataIsValid() {
        open(baseUrl);
        InfoUser validUser = DataGenerator.createValidUserInfo();
        $("[data-test-id=city] input").setValue(validUser.getCity());
        $("[data-test-id=name] input").setValue(validUser.getFullName());
        $("[data-test-id=phone] input").setValue(validUser.getPhone());
        $("[data-test-id=agreement]").click();
        $$(".button").findBy(Condition.exactText("Запланировать")).click();
        $("[data-test-id=success-notification] .notification__title").waitUntil(Condition.visible, 3000).shouldHave(Condition.exactText("Успешно!"));
    }

    @Test
    @DisplayName("Заполнение формы данными с невалидным городом")
    void invalidCityInDate() {
        open(baseUrl);
        InfoUser validUser = DataGenerator.createInvalidCity();
        $("[data-test-id=city] input").setValue(validUser.getCity());
        $("[data-test-id=name] input").setValue(validUser.getFullName());
        $("[data-test-id=phone] input").setValue(validUser.getPhone());
        $("[data-test-id=agreement]").click();
        $$(".button").findBy(Condition.exactText("Запланировать")).click();
        $("[data-test-id=city]").should(Condition.exactText("Доставка в выбранный город недоступна"));
    }

    @Test
    @DisplayName("Заполнение формы данными с невалидным ФИО")
    void invalidFullnameInDate() {
        open(baseUrl);
        InfoUser validUser = DataGenerator.createUserInvalidFullName();
        $("[data-test-id=city] input").setValue(validUser.getCity());
        $("[data-test-id=name] input").setValue(validUser.getFullName());
        $("[data-test-id=phone] input").setValue(validUser.getPhone());
        $("[data-test-id=agreement]").click();
        $$(".button").findBy(Condition.exactText("Запланировать")).click();
        $("[data-test-id=name] .input__sub").should(Condition.exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    // Тест падает так как нет проверки на правильность введенного номера и подсказк при неправильно введенном номере.
    // Появляется информационное сообщение о том что встреча запланирована даже с неправильным номером
    @Test
    @DisplayName("Заполнение формы данными с невалидным телефоном")
    void invalidNumberPhoneInDate() {
        open(baseUrl);
        InfoUser validUser = DataGenerator.createUserInvalidPhone();
        $("[data-test-id=city] input").setValue(validUser.getCity());
        $("[data-test-id=name] input").setValue(validUser.getFullName());
        $("[data-test-id=phone] input").setValue(validUser.getPhone());
        $("[data-test-id=agreement]").click();
        $$(".button").findBy(Condition.exactText("Запланировать")).click();
        $("[data-test-id=phone] .input__sub").should(Condition.exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
        $("[data-test-id=success-notification] .notification__title").waitUntil(Condition.not(Condition.visible), 3000);
    }

    @Test
    @DisplayName("Отображается нотификация 'Перепланировать', при повторном планировании с теми же валидными данными но с измененной датой")
    void replanIfDateIsChangedInData() {
        open(baseUrl);
        InfoUser validUser = DataGenerator.createValidUserInfo();
        $("[data-test-id=city] input").setValue(validUser.getCity());
        $("[data-test-id=name] input").setValue(validUser.getFullName());
        $("[data-test-id=phone] input").setValue(validUser.getPhone());
        $("[data-test-id=agreement]").click();
        $$(".button").findBy(Condition.exactText("Запланировать")).click();
        $("[data-test-id=success-notification] .notification__title").waitUntil(Condition.visible, 3000).shouldHave(Condition.exactText("Успешно!"));
        $("button .icon_name_close").click();
        $("button .icon_name_calendar").click();
        $(".calendar__layout").waitUntil(Condition.visible, 2000);
        long newDate = DataGenerator.getTimeStampString(DataGenerator.getEarliestValidDate().plusDays(2));
        $(String.format("[data-day='%d000']", newDate)).waitUntil(Condition.visible,10000).click();
        $$(".button").findBy(Condition.exactText("Запланировать")).click();
        $("[data-test-id=replan-notification] .notification__title").waitUntil(Condition.visible, 3000).shouldHave(Condition.exactText("Необходимо подтверждение"));
    }


    @Test
    @DisplayName("Отображается нотификация 'Перепланировать', при повторном планировании с теми же валидными данными")
    void replanWithoutChangesInData() {
        open(baseUrl);
        InfoUser validUser = DataGenerator.createValidUserInfo();
        $("[data-test-id=city] input").setValue(validUser.getCity());
        $("[data-test-id=name] input").setValue(validUser.getFullName());
        $("[data-test-id=phone] input").setValue(validUser.getPhone());
        $("[data-test-id=agreement]").click();
        $$(".button").findBy(Condition.exactText("Запланировать")).click();
        $("[data-test-id=success-notification] .notification__title").waitUntil(Condition.visible, 3000).shouldHave(Condition.exactText("Успешно!"));
        $("button .icon_name_close").click();
        $$(".button").findBy(Condition.exactText("Запланировать")).click();
        $("[data-test-id=replan-notification] .notification__title").waitUntil(Condition.visible, 3000).shouldHave(Condition.exactText("Необходимо подтверждение"));
    }

}

