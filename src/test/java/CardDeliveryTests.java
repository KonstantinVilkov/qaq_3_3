import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CardDeliveryTests {
    private String baseUrl = "http://localhost:9999";

    @Test
    void shouldPreventSendRequestMultipleTimes() {
        open(baseUrl);

        InfoUser validInfoUser = DataGenerate.createValidInfoUser();
        $("[data-test-id=city] input").setValue(validInfoUser.getCity());
        $("[data-test-id=name] input").setValue(validInfoUser.getFullName());
        $("[data-test-id=phone] input").setValue(validInfoUser.getPhone());
        $("[data-test-id=agreement]").click();
        $$(".button").findBy(Condition.exactText("Запланировать")).click();
        $("[data-test-id=success-notification].").waitUntil(Condition.visible, 3000);
        //("[data-test-id=success-notification]").should(Condition.exactText("Встреча успешно забронирована на "));

    }

}
