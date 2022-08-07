package ru.netology;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CardDeliveryTest {

    @BeforeEach
    void setupTest() {
        open("http://localhost:9999/");
    }

    public String dateGenerate(int days) {
        String date = LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        return date;
    }

    @Test
    void successDelivery() {
        String date = dateGenerate(3);
        SelenideElement form = $x("//form");

        form.$("[data-test-id='city'] input").setValue("Москва");
        form.$("[data-test-id='date'] input").clear();
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Томас Джефферсон");
        form.$("[data-test-id='phone'] input").setValue("+79876543223");
        form.$("[data-test-id='agreement']").click();
        $x("//button[contains(@class,'button_view_extra')]").click();
        $("[data-test-id='notification']").shouldBe(visible, Duration.ofSeconds(15)).
                shouldHave(exactText("Успешно!\nВстреча успешно забронирована на " + date));
        $x("//button[contains(@class,'notification__closer')]").click();
    }

    @Test
    void emptyCity() {
        String date = dateGenerate(3);
        SelenideElement form = $x("//form");

        form.$("[data-test-id='date'] input").clear();
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Томас Джефферсон");
        form.$("[data-test-id='phone'] input").setValue("+79876543223");
        form.$("[data-test-id='agreement']").click();
        $x("//button[contains(@class,'button_view_extra')]").click();
        $("[data-test-id='city'].input_invalid .input__sub").shouldBe(visible)
                .shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void validCityLowerCase() {
        String date = dateGenerate(3);
        SelenideElement form = $x("//form");

        form.$("[data-test-id='city'] input").setValue("москва");
        form.$("[data-test-id='date'] input").clear();
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Томас Джефферсон");
        form.$("[data-test-id='phone'] input").setValue("+79876543223");
        form.$("[data-test-id='agreement']").click();
        $x("//button[contains(@class,'button_view_extra')]").click();
        $("[data-test-id='notification']").shouldBe(visible, Duration.ofSeconds(15))
                .shouldHave(exactText("Успешно!\nВстреча успешно забронирована на " + date));
        $x("//button[contains(@class,'notification__closer')]").click();
    }

    @Test
    void validCityUpperCase() {
        String date = dateGenerate(3);
        SelenideElement form = $x("//form");

        form.$("[data-test-id='city'] input").setValue("МОСКВА");
        form.$("[data-test-id='date'] input").clear();
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Томас Джефферсон");
        form.$("[data-test-id='phone'] input").setValue("+79876543223");
        form.$("[data-test-id='agreement']").click();
        $x("//button[contains(@class,'button_view_extra')]").click();
        $("[data-test-id='notification']").shouldBe(visible, Duration.ofSeconds(15))
                .shouldHave(exactText("Успешно!\nВстреча успешно забронирована на " + date));
        $x("//button[contains(@class,'notification__closer')]").click();
    }

    @Test
    void validCompositeCityLowerCase() {
        String date = dateGenerate(3);
        SelenideElement form = $x("//form");

        form.$("[data-test-id='city'] input").setValue("южно-сахалинск");
        form.$("[data-test-id='date'] input").clear();
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Томас Джефферсон");
        form.$("[data-test-id='phone'] input").setValue("+79876543223");
        form.$("[data-test-id='agreement']").click();
        $x("//button[contains(@class,'button_view_extra')]").click();
        $("[data-test-id='notification']").shouldBe(visible, Duration.ofSeconds(15))
                .shouldHave(exactText("Успешно!\nВстреча успешно забронирована на " + date));
        $x("//button[contains(@class,'notification__closer')]").click();
    }

    @Test
    void validCompositeCityUpperCase() {
        String date = dateGenerate(3);
        SelenideElement form = $x("//form");

        form.$("[data-test-id='city'] input").setValue("ЮЖНО-САХАЛИНСК");
        form.$("[data-test-id='date'] input").clear();
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Томас Джефферсон");
        form.$("[data-test-id='phone'] input").setValue("+79876543223");
        form.$("[data-test-id='agreement']").click();
        $x("//button[contains(@class,'button_view_extra')]").click();
        $("[data-test-id='notification']").shouldBe(visible, Duration.ofSeconds(15))
                .shouldHave(exactText("Успешно!\nВстреча успешно забронирована на " + date));
        $x("//button[contains(@class,'notification__closer')]").click();
    }

    @Test
    void validCompositeCityFirstSymbolFirstWordSmall() {
        String date = dateGenerate(3);
        SelenideElement form = $x("//form");

        form.$("[data-test-id='city'] input").setValue("южно-Сахалинск");
        form.$("[data-test-id='date'] input").clear();
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Томас Джефферсон");
        form.$("[data-test-id='phone'] input").setValue("+79876543223");
        form.$("[data-test-id='agreement']").click();
        $x("//button[contains(@class,'button_view_extra')]").click();
        $("[data-test-id='notification']").shouldBe(visible, Duration.ofSeconds(15))
                .shouldHave(exactText("Успешно!\nВстреча успешно забронирована на " + date));
        $x("//button[contains(@class,'notification__closer')]").click();
    }

    @Test
    void validCompositeCityFirstSymbolSecondWordSmall() {
        String date = dateGenerate(3);
        SelenideElement form = $x("//form");

        form.$("[data-test-id='city'] input").setValue("Южно-сахалинск");
        form.$("[data-test-id='date'] input").clear();
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Томас Джефферсон");
        form.$("[data-test-id='phone'] input").setValue("+79876543223");
        form.$("[data-test-id='agreement']").click();
        $x("//button[contains(@class,'button_view_extra')]").click();
        $("[data-test-id='notification']").shouldBe(visible, Duration.ofSeconds(15))
                .shouldHave(exactText("Успешно!\nВстреча успешно забронирована на " + date));
        $x("//button[contains(@class,'notification__closer')]").click();
    }

    @Test
    void cityWithoutDelivery() {
        String date = dateGenerate(3);
        SelenideElement form = $x("//form");

        form.$("[data-test-id='city'] input").setValue("Кукумбер");
        form.$("[data-test-id='date'] input").clear();
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Томас Джефферсон");
        form.$("[data-test-id='phone'] input").setValue("+79876543223");
        form.$("[data-test-id='agreement']").click();
        $x("//button[contains(@class,'button_view_extra')]").click();
        $("[data-test-id='city'].input_invalid .input__sub").shouldBe(visible)
                .shouldHave(exactText("Доставка в выбранный город недоступна"));
    }

    @Test
    void invalidCityNameFromTwoValidCityNamesWithSpace() {
        String date = dateGenerate(3);
        SelenideElement form = $x("//form");

        form.$("[data-test-id='city'] input").setValue("Москва Якутск");
        form.$("[data-test-id='date'] input").clear();
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Томас Джефферсон");
        form.$("[data-test-id='phone'] input").setValue("+79876543223");
        form.$("[data-test-id='agreement']").click();
        $x("//button[contains(@class,'button_view_extra')]").click();
        $("[data-test-id='city'].input_invalid .input__sub").shouldBe(visible)
                .shouldHave(exactText("Доставка в выбранный город недоступна"));
    }

    @Test
    void invalidCityNameFromTwoValidCityNamesWithoutSpace() {
        String date = dateGenerate(3);
        SelenideElement form = $x("//form");

        form.$("[data-test-id='city'] input").setValue("МоскваЯкутск");
        form.$("[data-test-id='date'] input").clear();
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Томас Джефферсон");
        form.$("[data-test-id='phone'] input").setValue("+79876543223");
        form.$("[data-test-id='agreement']").click();
        $x("//button[contains(@class,'button_view_extra')]").click();
        $("[data-test-id='city'].input_invalid .input__sub").shouldBe(visible)
                .shouldHave(exactText("Доставка в выбранный город недоступна"));
    }

    @Test
    void invalidCityNameFromTwoValidCityNamesWithHyphen() {
        String date = dateGenerate(3);
        SelenideElement form = $x("//form");

        form.$("[data-test-id='city'] input").setValue("Москва-Якутск");
        form.$("[data-test-id='date'] input").clear();
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Томас Джефферсон");
        form.$("[data-test-id='phone'] input").setValue("+79876543223");
        form.$("[data-test-id='agreement']").click();
        $x("//button[contains(@class,'button_view_extra')]").click();
        $("[data-test-id='city'].input_invalid .input__sub").shouldBe(visible)
                .shouldHave(exactText("Доставка в выбранный город недоступна"));
    }

    @Test
    void invalidCityNameFromValidNameWithoutHyphen() {
        String date = dateGenerate(3);
        SelenideElement form = $x("//form");

        form.$("[data-test-id='city'] input").setValue("Южно Сахалинск");
        form.$("[data-test-id='date'] input").clear();
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Томас Джефферсон");
        form.$("[data-test-id='phone'] input").setValue("+79876543223");
        form.$("[data-test-id='agreement']").click();
        $x("//button[contains(@class,'button_view_extra')]").click();
        $("[data-test-id='city'].input_invalid .input__sub").shouldBe(visible)
                .shouldHave(exactText("Доставка в выбранный город недоступна"));
    }

    @Test
    void validCityWithSpaceAtFirst() {
        String date = dateGenerate(3);
        SelenideElement form = $x("//form");

        form.$("[data-test-id='city'] input").setValue(" Москва");
        form.$("[data-test-id='date'] input").clear();
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Томас Джефферсон");
        form.$("[data-test-id='phone'] input").setValue("+79876543223");
        form.$("[data-test-id='agreement']").click();
        $x("//button[contains(@class,'button_view_extra')]").click();
        $("[data-test-id='city'].input_invalid .input__sub").shouldBe(visible)
                .shouldHave(exactText("Доставка в выбранный город недоступна"));
    }

    @Test
    void validCityWithHyphenAtFirst() {
        String date = dateGenerate(3);
        SelenideElement form = $x("//form");

        form.$("[data-test-id='city'] input").setValue("-Москва");
        form.$("[data-test-id='date'] input").clear();
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Томас Джефферсон");
        form.$("[data-test-id='phone'] input").setValue("+79876543223");
        form.$("[data-test-id='agreement']").click();
        $x("//button[contains(@class,'button_view_extra')]").click();
        $("[data-test-id='city'].input_invalid .input__sub").shouldBe(visible)
                .shouldHave(exactText("Доставка в выбранный город недоступна"));
    }

    @Test
    void validCityLatin() {
        String date = dateGenerate(3);
        SelenideElement form = $x("//form");

        form.$("[data-test-id='city'] input").setValue("Moscow");
        form.$("[data-test-id='date'] input").clear();
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Томас Джефферсон");
        form.$("[data-test-id='phone'] input").setValue("+79876543223");
        form.$("[data-test-id='agreement']").click();
        $x("//button[contains(@class,'button_view_extra')]").click();
        $("[data-test-id='city'].input_invalid .input__sub").shouldBe(visible)
                .shouldHave(exactText("Доставка в выбранный город недоступна"));
    }

    @Test
    void invalidCityDigits() {
        String date = dateGenerate(3);
        SelenideElement form = $x("//form");

        form.$("[data-test-id='city'] input").setValue("80401");
        form.$("[data-test-id='date'] input").clear();
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Томас Джефферсон");
        form.$("[data-test-id='phone'] input").setValue("+79876543223");
        form.$("[data-test-id='agreement']").click();
        $x("//button[contains(@class,'button_view_extra')]").click();
        $("[data-test-id='city'].input_invalid .input__sub").shouldBe(visible)
                .shouldHave(exactText("Доставка в выбранный город недоступна"));
    }

    @Test
    void invalidCitySpecialSymbols() {
        String date = dateGenerate(3);
        SelenideElement form = $x("//form");

        form.$("[data-test-id='city'] input").setValue("<>:{}");
        form.$("[data-test-id='date'] input").clear();
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Томас Джефферсон");
        form.$("[data-test-id='phone'] input").setValue("+79876543223");
        form.$("[data-test-id='agreement']").click();
        $x("//button[contains(@class,'button_view_extra')]").click();
        $("[data-test-id='city'].input_invalid .input__sub").shouldBe(visible)
                .shouldHave(exactText("Доставка в выбранный город недоступна"));
    }


    @Test
    void emptyDate() {
        SelenideElement form = $x("//form");

        form.$("[data-test-id='city'] input").setValue("Москва");
        form.$("[data-test-id='date'] input").clear();
        form.$("[data-test-id='name'] input").setValue("Томас Джефферсон");
        form.$("[data-test-id='phone'] input").setValue("+79876543223");
        form.$("[data-test-id='agreement']").click();
        $x("//button[contains(@class,'button_view_extra')]").click();
        $("[data-test-id='date'] .input_invalid .input__sub").shouldBe(visible)
                .shouldHave(exactText("Неверно введена дата"));
    }

    @Test
    void invalidDateZeroInDateFormat() {
        SelenideElement form = $x("//form");

        form.$("[data-test-id='city'] input").setValue("Москва");
        form.$("[data-test-id='date'] input").clear();
        form.$("[data-test-id='date'] input").setValue("00.00.0000");
        form.$("[data-test-id='name'] input").setValue("Томас Джефферсон");
        form.$("[data-test-id='phone'] input").setValue("+79876543223");
        form.$("[data-test-id='agreement']").click();
        $x("//button[contains(@class,'button_view_extra')]").click();
        $("[data-test-id='date'] .input_invalid .input__sub").shouldBe(visible)
                .shouldHave(exactText("Неверно введена дата"));
    }

    @Test
    void invalidDateZero() {
        SelenideElement form = $x("//form");

        form.$("[data-test-id='city'] input").setValue("Москва");
        form.$("[data-test-id='date'] input").clear();
        form.$("[data-test-id='date'] input").setValue("0");
        form.$("[data-test-id='name'] input").setValue("Томас Джефферсон");
        form.$("[data-test-id='phone'] input").setValue("+79876543223");
        form.$("[data-test-id='agreement']").click();
        $x("//button[contains(@class,'button_view_extra')]").click();
        $("[data-test-id='date'] .input_invalid .input__sub").shouldBe(visible)
                .shouldHave(exactText("Неверно введена дата"));
    }

    @Disabled
    @Test
    void invalidDateZeroYear() {
        SelenideElement form = $x("//form");

        form.$("[data-test-id='city'] input").setValue("Москва");
        form.$("[data-test-id='date'] input").clear();
        form.$("[data-test-id='date'] input").setValue("01.01.0000");
        form.$("[data-test-id='name'] input").setValue("Томас Джефферсон");
        form.$("[data-test-id='phone'] input").setValue("+79876543223");
        form.$("[data-test-id='agreement']").click();
        $x("//button[contains(@class,'button_view_extra')]").click();
        $("[data-test-id='date'] .input_invalid .input__sub").shouldBe(visible)
                .shouldHave(exactText("Неверно введена дата"));
    }

    @Test
    void invalidPastDate() {
        String date = LocalDate.now().plusDays(5).minusMonths(1).format(DateTimeFormatter.
                ofPattern("dd.MM.yyyy"));
        SelenideElement form = $x("//form");

        form.$("[data-test-id='city'] input").setValue("Москва");
        form.$("[data-test-id='date'] input").clear();
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Томас Джефферсон");
        form.$("[data-test-id='phone'] input").setValue("+79876543223");
        form.$("[data-test-id='agreement']").click();
        $x("//button[contains(@class,'button_view_extra')]").click();
        $("[data-test-id='date'] .input_invalid .input__sub").shouldBe(visible)
                .shouldHave(exactText("Заказ на выбранную дату невозможен"));
    }

    @Test
    void invalidCurrentDate() {
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        SelenideElement form = $x("//form");

        form.$("[data-test-id='city'] input").setValue("Москва");
        form.$("[data-test-id='date'] input").clear();
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Томас Джефферсон");
        form.$("[data-test-id='phone'] input").setValue("+79876543223");
        form.$("[data-test-id='agreement']").click();
        $x("//button[contains(@class,'button_view_extra')]").click();
        $("[data-test-id='date'] .input_invalid .input__sub").shouldBe(visible)
                .shouldHave(exactText("Заказ на выбранную дату невозможен"));
    }

    @Test
    void invalidEarlyDate() {
        String date = dateGenerate(1);
        SelenideElement form = $x("//form");

        form.$("[data-test-id='city'] input").setValue("Москва");
        form.$("[data-test-id='date'] input").clear();
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Томас Джефферсон");
        form.$("[data-test-id='phone'] input").setValue("+79876543223");
        form.$("[data-test-id='agreement']").click();
        $x("//button[contains(@class,'button_view_extra')]").click();
        $("[data-test-id='date'] .input_invalid .input__sub").shouldBe(visible)
                .shouldHave(exactText("Заказ на выбранную дату невозможен"));
    }

    @Test
    void validDateInMonth() {
        String date = LocalDate.now().plusMonths(1).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        SelenideElement form = $x("//form");

        form.$("[data-test-id='city'] input").setValue("Москва");
        form.$("[data-test-id='date'] input").clear();
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Томас Джефферсон");
        form.$("[data-test-id='phone'] input").setValue("+79876543223");
        form.$("[data-test-id='agreement']").click();
        $x("//button[contains(@class,'button_view_extra')]").click();
        $("[data-test-id='notification']").shouldBe(visible, Duration.ofSeconds(15))
                .shouldHave(exactText("Успешно!\nВстреча успешно забронирована на " + date));
        $x("//button[contains(@class,'notification__closer')]").click();
    }

    @Test
    void validDateInYear() {
        String date = LocalDate.now().plusYears(1).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        SelenideElement form = $x("//form");

        form.$("[data-test-id='city'] input").setValue("Москва");
        form.$("[data-test-id='date'] input").clear();
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Томас Джефферсон");
        form.$("[data-test-id='phone'] input").setValue("+79876543223");
        form.$("[data-test-id='agreement']").click();
        $x("//button[contains(@class,'button_view_extra')]").click();
        $("[data-test-id='notification']").shouldBe(visible, Duration.ofSeconds(15))
                .shouldHave(exactText("Успешно!\nВстреча успешно забронирована на " + date));
        $x("//button[contains(@class,'notification__closer')]").click();
    }

    @Test
    void emptyName() {
        String date = dateGenerate(3);
        SelenideElement form = $x("//form");

        form.$("[data-test-id='city'] input").setValue("Москва");
        form.$("[data-test-id='date'] input").clear();
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='phone'] input").setValue("+79876543223");
        form.$("[data-test-id='agreement']").click();
        $x("//button[contains(@class,'button_view_extra')]").click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldBe(visible)
                .shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void invalidNameSpaceOnly() {
        String date = dateGenerate(3);
        SelenideElement form = $x("//form");

        form.$("[data-test-id='city'] input").setValue("Москва");
        form.$("[data-test-id='date'] input").clear();
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue(" ");
        form.$("[data-test-id='phone'] input").setValue("+79876543223");
        form.$("[data-test-id='agreement']").click();
        $x("//button[contains(@class,'button_view_extra')]").click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldBe(visible)
                .shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Disabled
    @Test
    void invalidNameHyphenOnly() {
        String date = dateGenerate(3);
        SelenideElement form = $x("//form");

        form.$("[data-test-id='city'] input").setValue("Москва");
        form.$("[data-test-id='date'] input").clear();
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("-");
        form.$("[data-test-id='phone'] input").setValue("+79876543223");
        form.$("[data-test-id='agreement']").click();
        $x("//button[contains(@class,'button_view_extra')]").click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldBe(visible);
    }

    @Test
    void invalidNameLatin() {
        String date = dateGenerate(3);
        SelenideElement form = $x("//form");

        form.$("[data-test-id='city'] input").setValue("Москва");
        form.$("[data-test-id='date'] input").clear();
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Thomas Jefferson");
        form.$("[data-test-id='phone'] input").setValue("+79876543223");
        form.$("[data-test-id='agreement']").click();
        $x("//button[contains(@class,'button_view_extra')]").click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldBe(visible)
                .shouldHave(exactText("Имя и Фамилия указаные неверно. " +
                        "Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void validNameCompositeName() {
        String date = dateGenerate(3);
        SelenideElement form = $x("//form");

        form.$("[data-test-id='city'] input").setValue("Москва");
        form.$("[data-test-id='date'] input").clear();
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Томас-Лукас Джефферсон");
        form.$("[data-test-id='phone'] input").setValue("+79876543223");
        form.$("[data-test-id='agreement']").click();
        $x("//button[contains(@class,'button_view_extra')]").click();
        $("[data-test-id='notification']").shouldBe(visible, Duration.ofSeconds(15))
                .shouldHave(exactText("Успешно!\nВстреча успешно забронирована на " + date));
        $x("//button[contains(@class,'notification__closer')]").click();
    }

    @Test
    void validNameCompositeFamily() {
        String date = dateGenerate(3);
        SelenideElement form = $x("//form");

        form.$("[data-test-id='city'] input").setValue("Москва");
        form.$("[data-test-id='date'] input").clear();
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Томас Джефферсон-Сваровски");
        form.$("[data-test-id='phone'] input").setValue("+79876543223");
        form.$("[data-test-id='agreement']").click();
        $x("//button[contains(@class,'button_view_extra')]").click();
        $("[data-test-id='notification']").shouldBe(visible, Duration.ofSeconds(15))
                .shouldHave(exactText("Успешно!\nВстреча успешно забронирована на " + date));
        $x("//button[contains(@class,'notification__closer')]").click();
    }

    @Test
    void validNameCompositeNameAndFamily() {
        String date = dateGenerate(3);
        SelenideElement form = $x("//form");

        form.$("[data-test-id='city'] input").setValue("Москва");
        form.$("[data-test-id='date'] input").clear();
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Томас-Лукас Джефферсон-Сваровски");
        form.$("[data-test-id='phone'] input").setValue("+79876543223");
        form.$("[data-test-id='agreement']").click();
        $x("//button[contains(@class,'button_view_extra')]").click();
        $("[data-test-id='notification']").shouldBe(visible, Duration.ofSeconds(15))
                .shouldHave(exactText("Успешно!\nВстреча успешно забронирована на " + date));
        $x("//button[contains(@class,'notification__closer')]").click();
    }

    @Disabled
    @Test
    void validNameWithSymbolE() {
        String date = dateGenerate(3);
        SelenideElement form = $x("//form");

        form.$("[data-test-id='city'] input").setValue("Москва");
        form.$("[data-test-id='date'] input").clear();
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Сергеев Пётр");
        form.$("[data-test-id='phone'] input").setValue("+79876543223");
        form.$("[data-test-id='agreement']").click();
        $x("//button[contains(@class,'button_view_extra')]").click();
        $("[data-test-id='notification']").shouldBe(visible, Duration.ofSeconds(15))
                .shouldHave(exactText("Успешно!\nВстреча успешно забронирована на " + date));
        $x("//button[contains(@class,'notification__closer')]").click();
    }

    @Test
    void invalidNameOneSymbolInLatin() {
        String date = dateGenerate(3);
        SelenideElement form = $x("//form");

        form.$("[data-test-id='city'] input").setValue("Москва");
        form.$("[data-test-id='date'] input").clear();
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Cергеев Алексей");
        form.$("[data-test-id='phone'] input").setValue("+79876543223");
        form.$("[data-test-id='agreement']").click();
        $x("//button[contains(@class,'button_view_extra')]").click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldBe(visible)
                .shouldHave(exactText("Имя и Фамилия указаные неверно. " +
                        "Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void invalidNameSpecialSymbol() {
        String date = dateGenerate(3);
        SelenideElement form = $x("//form");

        form.$("[data-test-id='city'] input").setValue("Москва");
        form.$("[data-test-id='date'] input").clear();
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Сергеев Алексей:)");
        form.$("[data-test-id='phone'] input").setValue("+79876543223");
        form.$("[data-test-id='agreement']").click();
        $x("//button[contains(@class,'button_view_extra')]").click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldBe(visible)
                .shouldHave(exactText("Имя и Фамилия указаные неверно. " +
                        "Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void invalidNameSpecialSymbolOnly() {
        String date = dateGenerate(3);
        SelenideElement form = $x("//form");

        form.$("[data-test-id='city'] input").setValue("Москва");
        form.$("[data-test-id='date'] input").clear();
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("[!@;%:?()]");
        form.$("[data-test-id='phone'] input").setValue("+79876543223");
        form.$("[data-test-id='agreement']").click();
        $x("//button[contains(@class,'button_view_extra')]").click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldBe(visible)
                .shouldHave(exactText("Имя и Фамилия указаные неверно. " +
                        "Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void emptyPhone() {
        String date = dateGenerate(3);
        SelenideElement form = $x("//form");

        form.$("[data-test-id='city'] input").setValue("Москва");
        form.$("[data-test-id='date'] input").clear();
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Томас Джефферсон");
        form.$("[data-test-id='agreement']").click();
        $x("//button[contains(@class,'button_view_extra')]").click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldBe(visible)
                .shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void invalidFormatPhone8InsteadOfPlus7() {
        String date = dateGenerate(3);
        SelenideElement form = $x("//form");

        form.$("[data-test-id='city'] input").setValue("Москва");
        form.$("[data-test-id='date'] input").clear();
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Томас Джефферсон");
        form.$("[data-test-id='phone'] input").setValue("89876543223");
        form.$("[data-test-id='agreement']").click();
        $x("//button[contains(@class,'button_view_extra')]").click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldBe(visible)
                .shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void invalidFormatPhoneWithBrackets() {
        String date = dateGenerate(3);
        SelenideElement form = $x("//form");

        form.$("[data-test-id='city'] input").setValue("Москва");
        form.$("[data-test-id='date'] input").clear();
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Томас Джефферсон");
        form.$("[data-test-id='phone'] input").setValue("+7(987)6543223");
        form.$("[data-test-id='agreement']").click();
        $x("//button[contains(@class,'button_view_extra')]").click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldBe(visible)
                .shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void invalidFormatPhoneWithHyphens() {
        String date = dateGenerate(3);
        SelenideElement form = $x("//form");

        form.$("[data-test-id='city'] input").setValue("Москва");
        form.$("[data-test-id='date'] input").clear();
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Томас Джефферсон");
        form.$("[data-test-id='phone'] input").setValue("+7987-654-32-23");
        form.$("[data-test-id='agreement']").click();
        $x("//button[contains(@class,'button_view_extra')]").click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldBe(visible)
                .shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void invalidFormatPhoneWithSpaces() {
        String date = dateGenerate(3);
        SelenideElement form = $x("//form");

        form.$("[data-test-id='city'] input").setValue("Москва");
        form.$("[data-test-id='date'] input").clear();
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Томас Джефферсон");
        form.$("[data-test-id='phone'] input").setValue("+7987 654 32 23");
        form.$("[data-test-id='agreement']").click();
        $x("//button[contains(@class,'button_view_extra')]").click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldBe(visible)
                .shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void invalidPhoneWithoutPlus() {
        String date = dateGenerate(3);
        SelenideElement form = $x("//form");

        form.$("[data-test-id='city'] input").setValue("Москва");
        form.$("[data-test-id='date'] input").clear();
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Томас Джефферсон");
        form.$("[data-test-id='phone'] input").setValue("79876543223");
        form.$("[data-test-id='agreement']").click();
        $x("//button[contains(@class,'button_view_extra')]").click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldBe(visible)
                .shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void invalidPhoneLatin() {
        String date = dateGenerate(3);
        SelenideElement form = $x("//form");

        form.$("[data-test-id='city'] input").setValue("Москва");
        form.$("[data-test-id='date'] input").clear();
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Томас Джефферсон");
        form.$("[data-test-id='phone'] input").setValue("+79876J43223");
        form.$("[data-test-id='agreement']").click();
        $x("//button[contains(@class,'button_view_extra')]").click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldBe(visible)
                .shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void invalidPhoneSpecialSymbols() {
        String date = dateGenerate(3);
        SelenideElement form = $x("//form");

        form.$("[data-test-id='city'] input").setValue("Москва");
        form.$("[data-test-id='date'] input").clear();
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Томас Джефферсон");
        form.$("[data-test-id='phone'] input").setValue("+7987.644322");
        form.$("[data-test-id='agreement']").click();
        $x("//button[contains(@class,'button_view_extra')]").click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldBe(visible)
                .shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void invalidPhoneCyrillic() {
        String date = dateGenerate(3);
        SelenideElement form = $x("//form");

        form.$("[data-test-id='city'] input").setValue("Москва");
        form.$("[data-test-id='date'] input").clear();
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Томас Джефферсон");
        form.$("[data-test-id='phone'] input").setValue("+79877644ш22");
        form.$("[data-test-id='agreement']").click();
        $x("//button[contains(@class,'button_view_extra')]").click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldBe(visible)
                .shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void checkboxNotPressed() {
        String date = dateGenerate(3);
        SelenideElement form = $x("//form");

        form.$("[data-test-id='city'] input").setValue("Москва");
        form.$("[data-test-id='date'] input").clear();
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Томас Джефферсон");
        form.$("[data-test-id='phone'] input").setValue("+79876543223");
        $x("//button[contains(@class,'button_view_extra')]").click();
        form.$("[data-test-id='agreement'].input_invalid").shouldBe(visible);
    }

    @Test
    void emptyAll() {
        SelenideElement form = $x("//form");

        form.$("[data-test-id='date'] input").clear();
        $x("//button[contains(@class,'button_view_extra')]").click();
        $("[data-test-id='city'].input_invalid .input__sub").shouldBe(visible)
                .shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void successDeliveryWithPopupElements() {
        SelenideElement form = $x("//form");

        String city = "Москва";
        String firstTwoSymbols = city.substring(0, 2);
        form.$("[data-test-id='city'] input").setValue(firstTwoSymbols);
        $$x("//*[@class='popup__container']//*[@class='menu-item__control']").findBy(text(city)).click();
        form.$("[data-test-id='city'] [class='input__control']").shouldHave(value(city));
        LocalDate currentDate = LocalDate.now().plusDays(3);
        LocalDate deliveryDate = LocalDate.now().plusWeeks(1);
        String dayOfDeliveryDate = String.valueOf(deliveryDate.getDayOfMonth());
        String date = deliveryDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        form.$("[data-test-id='date'] button").click();
        if (deliveryDate.getMonthValue() > currentDate.getMonthValue() ||
                deliveryDate.getYear() > currentDate.getYear()) {
            $("[data-step='1']").shouldBe(visible).click();
        }
        $$(".calendar__day").findBy(text(dayOfDeliveryDate)).click();
        form.$("[data-test-id='name'] input").setValue("Томас Джефферсон");
        form.$("[data-test-id='phone'] input").setValue("+79876543223");
        form.$("[data-test-id='agreement']").click();
        $x("//button[contains(@class,'button_view_extra')]").click();
        $("[data-test-id='notification']").shouldBe(visible, Duration.ofSeconds(15))
                .shouldHave(exactText("Успешно!\nВстреча успешно забронирована на " + date));
        $x("//button[contains(@class,'notification__closer')]").click();
    }
}
