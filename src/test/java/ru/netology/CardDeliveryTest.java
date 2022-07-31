package ru.netology;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CardDeliveryTest {

    @BeforeEach
    void setupTest() {
        open("http://localhost:9999/");
    }

    @Test
    void successDelivery() {
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        SelenideElement form = $x("//form");

        form.$("[data-test-id='city'] input").setValue("Москва");
        form.$("[data-test-id='date'] input").clear();
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Томас Джефферсон");
        form.$("[data-test-id='phone'] input").setValue("+79876543223");
        form.$("[data-test-id='agreement']").click();
        $x("//button[contains(@class,'button_view_extra')]").click();
        String popupText = $("[data-test-id='notification']").shouldBe(visible, Duration.ofSeconds(15))
                .getText();
        $x("//button[contains(@class,'notification__closer')]").click();

        assertEquals("Успешно!" + "\n" + "Встреча успешно забронирована на " + date, popupText.trim());
    }

    @Test
    void emptyCity() {
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        SelenideElement form = $x("//form");

        form.$("[data-test-id='date'] input").clear();
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Томас Джефферсон");
        form.$("[data-test-id='phone'] input").setValue("+79876543223");
        form.$("[data-test-id='agreement']").click();
        $x("//button[contains(@class,'button_view_extra')]").click();
        String subText = $("[data-test-id='city'].input_invalid .input__sub").getText();

        assertEquals("Поле обязательно для заполнения", subText.trim());
    }

    @Test
    void validCityLowerCase() {
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        SelenideElement form = $x("//form");

        form.$("[data-test-id='city'] input").setValue("москва");
        form.$("[data-test-id='date'] input").clear();
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Томас Джефферсон");
        form.$("[data-test-id='phone'] input").setValue("+79876543223");
        form.$("[data-test-id='agreement']").click();
        $x("//button[contains(@class,'button_view_extra')]").click();
        String popupText = $("[data-test-id='notification']").shouldBe(visible, Duration.ofSeconds(15))
                .getText();
        $x("//button[contains(@class,'notification__closer')]").click();

        assertEquals("Успешно!" + "\n" + "Встреча успешно забронирована на " + date, popupText.trim());
    }

    @Test
    void validCityUpperCase() {
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        SelenideElement form = $x("//form");

        form.$("[data-test-id='city'] input").setValue("МОСКВА");
        form.$("[data-test-id='date'] input").clear();
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Томас Джефферсон");
        form.$("[data-test-id='phone'] input").setValue("+79876543223");
        form.$("[data-test-id='agreement']").click();
        $x("//button[contains(@class,'button_view_extra')]").click();
        String popupText = $("[data-test-id='notification']").shouldBe(visible, Duration.ofSeconds(15))
                .getText();
        $x("//button[contains(@class,'notification__closer')]").click();

        assertEquals("Успешно!" + "\n" + "Встреча успешно забронирована на " + date, popupText.trim());
    }

    @Test
    void validCompositeCityLowerCase() {
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        SelenideElement form = $x("//form");

        form.$("[data-test-id='city'] input").setValue("южно-сахалинск");
        form.$("[data-test-id='date'] input").clear();
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Томас Джефферсон");
        form.$("[data-test-id='phone'] input").setValue("+79876543223");
        form.$("[data-test-id='agreement']").click();
        $x("//button[contains(@class,'button_view_extra')]").click();
        String popupText = $("[data-test-id='notification']").shouldBe(visible, Duration.ofSeconds(15))
                .getText();
        $x("//button[contains(@class,'notification__closer')]").click();

        assertEquals("Успешно!" + "\n" + "Встреча успешно забронирована на " + date, popupText.trim());
    }

    @Test
    void validCompositeCityUpperCase() {
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        SelenideElement form = $x("//form");

        form.$("[data-test-id='city'] input").setValue("ЮЖНО-САХАЛИНСК");
        form.$("[data-test-id='date'] input").clear();
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Томас Джефферсон");
        form.$("[data-test-id='phone'] input").setValue("+79876543223");
        form.$("[data-test-id='agreement']").click();
        $x("//button[contains(@class,'button_view_extra')]").click();
        String popupText = $("[data-test-id='notification']").shouldBe(visible, Duration.ofSeconds(15))
                .getText();
        $x("//button[contains(@class,'notification__closer')]").click();

        assertEquals("Успешно!" + "\n" + "Встреча успешно забронирована на " + date, popupText.trim());
    }

    @Test
    void validCompositeCityFirstSymbolFirstWordSmall() {
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        SelenideElement form = $x("//form");

        form.$("[data-test-id='city'] input").setValue("южно-Сахалинск");
        form.$("[data-test-id='date'] input").clear();
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Томас Джефферсон");
        form.$("[data-test-id='phone'] input").setValue("+79876543223");
        form.$("[data-test-id='agreement']").click();
        $x("//button[contains(@class,'button_view_extra')]").click();
        String popupText = $("[data-test-id='notification']").shouldBe(visible, Duration.ofSeconds(15))
                .getText();
        $x("//button[contains(@class,'notification__closer')]").click();

        assertEquals("Успешно!" + "\n" + "Встреча успешно забронирована на " + date, popupText.trim());
    }

    @Test
    void validCompositeCityFirstSymbolSecondWordSmall() {
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        SelenideElement form = $x("//form");

        form.$("[data-test-id='city'] input").setValue("Южно-сахалинск");
        form.$("[data-test-id='date'] input").clear();
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Томас Джефферсон");
        form.$("[data-test-id='phone'] input").setValue("+79876543223");
        form.$("[data-test-id='agreement']").click();
        $x("//button[contains(@class,'button_view_extra')]").click();
        String popupText = $("[data-test-id='notification']").shouldBe(visible, Duration.ofSeconds(15))
                .getText();
        $x("//button[contains(@class,'notification__closer')]").click();

        assertEquals("Успешно!" + "\n" + "Встреча успешно забронирована на " + date, popupText.trim());
    }

    @Test
    void cityWithoutDelivery() {
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        SelenideElement form = $x("//form");

        form.$("[data-test-id='city'] input").setValue("Кукумбер");
        form.$("[data-test-id='date'] input").clear();
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Томас Джефферсон");
        form.$("[data-test-id='phone'] input").setValue("+79876543223");
        form.$("[data-test-id='agreement']").click();
        $x("//button[contains(@class,'button_view_extra')]").click();
        String subText = $("[data-test-id='city'].input_invalid .input__sub").getText();

        assertEquals("Доставка в выбранный город недоступна", subText.trim());
    }

    @Test
    void invalidCityNameFromTwoValidCityNamesWithSpace() {
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        SelenideElement form = $x("//form");

        form.$("[data-test-id='city'] input").setValue("Москва Якутск");
        form.$("[data-test-id='date'] input").clear();
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Томас Джефферсон");
        form.$("[data-test-id='phone'] input").setValue("+79876543223");
        form.$("[data-test-id='agreement']").click();
        $x("//button[contains(@class,'button_view_extra')]").click();
        String subText = $("[data-test-id='city'].input_invalid .input__sub").getText();

        assertEquals("Доставка в выбранный город недоступна", subText.trim());
    }

    @Test
    void invalidCityNameFromTwoValidCityNamesWithoutSpace() {
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        SelenideElement form = $x("//form");

        form.$("[data-test-id='city'] input").setValue("МоскваЯкутск");
        form.$("[data-test-id='date'] input").clear();
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Томас Джефферсон");
        form.$("[data-test-id='phone'] input").setValue("+79876543223");
        form.$("[data-test-id='agreement']").click();
        $x("//button[contains(@class,'button_view_extra')]").click();
        String subText = $("[data-test-id='city'].input_invalid .input__sub").getText();

        assertEquals("Доставка в выбранный город недоступна", subText.trim());
    }

    @Test
    void invalidCityNameFromTwoValidCityNamesWithHyphen() {
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        SelenideElement form = $x("//form");

        form.$("[data-test-id='city'] input").setValue("Москва-Якутск");
        form.$("[data-test-id='date'] input").clear();
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Томас Джефферсон");
        form.$("[data-test-id='phone'] input").setValue("+79876543223");
        form.$("[data-test-id='agreement']").click();
        $x("//button[contains(@class,'button_view_extra')]").click();
        String subText = $("[data-test-id='city'].input_invalid .input__sub").getText();

        assertEquals("Доставка в выбранный город недоступна", subText.trim());
    }

    @Test
    void invalidCityNameFromValidNameWithoutHyphen() {
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        SelenideElement form = $x("//form");

        form.$("[data-test-id='city'] input").setValue("Южно Сахалинск");
        form.$("[data-test-id='date'] input").clear();
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Томас Джефферсон");
        form.$("[data-test-id='phone'] input").setValue("+79876543223");
        form.$("[data-test-id='agreement']").click();
        $x("//button[contains(@class,'button_view_extra')]").click();
        String subText = $("[data-test-id='city'].input_invalid .input__sub").getText();

        assertEquals("Доставка в выбранный город недоступна", subText.trim());
    }

    @Test
    void validCityWithSpaceAtFirst() {
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        SelenideElement form = $x("//form");

        form.$("[data-test-id='city'] input").setValue(" Москва");
        form.$("[data-test-id='date'] input").clear();
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Томас Джефферсон");
        form.$("[data-test-id='phone'] input").setValue("+79876543223");
        form.$("[data-test-id='agreement']").click();
        $x("//button[contains(@class,'button_view_extra')]").click();
        String subText = $("[data-test-id='city'].input_invalid .input__sub").getText();

        assertEquals("Доставка в выбранный город недоступна", subText.trim());
    }

    @Test
    void validCityWithHyphenAtFirst() {
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        SelenideElement form = $x("//form");

        form.$("[data-test-id='city'] input").setValue("-Москва");
        form.$("[data-test-id='date'] input").clear();
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Томас Джефферсон");
        form.$("[data-test-id='phone'] input").setValue("+79876543223");
        form.$("[data-test-id='agreement']").click();
        $x("//button[contains(@class,'button_view_extra')]").click();
        String subText = $("[data-test-id='city'].input_invalid .input__sub").getText();

        assertEquals("Доставка в выбранный город недоступна", subText.trim());
    }

    @Test
    void validCityLatin() {
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        SelenideElement form = $x("//form");

        form.$("[data-test-id='city'] input").setValue("Moscow");
        form.$("[data-test-id='date'] input").clear();
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Томас Джефферсон");
        form.$("[data-test-id='phone'] input").setValue("+79876543223");
        form.$("[data-test-id='agreement']").click();
        $x("//button[contains(@class,'button_view_extra')]").click();
        String subText = $("[data-test-id='city'].input_invalid .input__sub").getText();

        assertEquals("Доставка в выбранный город недоступна", subText.trim());
    }

    @Test
    void invalidCityDigits() {
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        SelenideElement form = $x("//form");

        form.$("[data-test-id='city'] input").setValue("80401");
        form.$("[data-test-id='date'] input").clear();
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Томас Джефферсон");
        form.$("[data-test-id='phone'] input").setValue("+79876543223");
        form.$("[data-test-id='agreement']").click();
        $x("//button[contains(@class,'button_view_extra')]").click();
        String subText = $("[data-test-id='city'].input_invalid .input__sub").getText();

        assertEquals("Доставка в выбранный город недоступна", subText.trim());
    }

    @Test
    void invalidCitySpecialSymbols() {
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        SelenideElement form = $x("//form");

        form.$("[data-test-id='city'] input").setValue("<>:{}");
        form.$("[data-test-id='date'] input").clear();
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Томас Джефферсон");
        form.$("[data-test-id='phone'] input").setValue("+79876543223");
        form.$("[data-test-id='agreement']").click();
        $x("//button[contains(@class,'button_view_extra')]").click();
        String subText = $("[data-test-id='city'].input_invalid .input__sub").getText();

        assertEquals("Доставка в выбранный город недоступна", subText.trim());
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
        String subText = $("[data-test-id='date'] .input_invalid .input__sub").getText();

        assertEquals("Неверно введена дата", subText.trim());
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
        String subText = $("[data-test-id='date'] .input_invalid .input__sub").getText();

        assertEquals("Неверно введена дата", subText.trim());
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
        String subText = $("[data-test-id='date'] .input_invalid .input__sub").getText();

        assertEquals("Неверно введена дата", subText.trim());
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
        String subText = $("[data-test-id='date'] .input_invalid .input__sub").getText();

        assertEquals("Неверно введена дата", subText.trim());
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
        String subText = $("[data-test-id='date'] .input_invalid .input__sub").getText();

        assertEquals("Заказ на выбранную дату невозможен", subText.trim());
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
        String subText = $("[data-test-id='date'] .input_invalid .input__sub").getText();

        assertEquals("Заказ на выбранную дату невозможен", subText.trim());
    }

    @Test
    void invalidEarlyDate() {
        String date = LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        SelenideElement form = $x("//form");

        form.$("[data-test-id='city'] input").setValue("Москва");
        form.$("[data-test-id='date'] input").clear();
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Томас Джефферсон");
        form.$("[data-test-id='phone'] input").setValue("+79876543223");
        form.$("[data-test-id='agreement']").click();
        $x("//button[contains(@class,'button_view_extra')]").click();
        String subText = $("[data-test-id='date'] .input_invalid .input__sub").getText();

        assertEquals("Заказ на выбранную дату невозможен", subText.trim());
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
        String popupText = $("[data-test-id='notification']").shouldBe(visible, Duration.ofSeconds(15))
                .getText();
        $x("//button[contains(@class,'notification__closer')]").click();

        assertEquals("Успешно!" + "\n" + "Встреча успешно забронирована на " + date, popupText.trim());
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
        String popupText = $("[data-test-id='notification']").shouldBe(visible, Duration.ofSeconds(15))
                .getText();
        $x("//button[contains(@class,'notification__closer')]").click();

        assertEquals("Успешно!" + "\n" + "Встреча успешно забронирована на " + date, popupText.trim());
    }

    @Test
    void emptyName() {
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        SelenideElement form = $x("//form");

        form.$("[data-test-id='city'] input").setValue("Москва");
        form.$("[data-test-id='date'] input").clear();
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='phone'] input").setValue("+79876543223");
        form.$("[data-test-id='agreement']").click();
        $x("//button[contains(@class,'button_view_extra')]").click();
        String subText = $("[data-test-id='name'].input_invalid .input__sub").getText();

        assertEquals("Поле обязательно для заполнения", subText.trim());
    }

    @Test
    void invalidNameSpaceOnly() {
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        SelenideElement form = $x("//form");

        form.$("[data-test-id='city'] input").setValue("Москва");
        form.$("[data-test-id='date'] input").clear();
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue(" ");
        form.$("[data-test-id='phone'] input").setValue("+79876543223");
        form.$("[data-test-id='agreement']").click();
        $x("//button[contains(@class,'button_view_extra')]").click();
        String subText = $("[data-test-id='name'].input_invalid .input__sub").getText();

        assertEquals("Поле обязательно для заполнения", subText.trim());
    }

    @Disabled
    @Test
    void invalidNameHyphenOnly() {
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        SelenideElement form = $x("//form");

        form.$("[data-test-id='city'] input").setValue("Москва");
        form.$("[data-test-id='date'] input").clear();
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("-");
        form.$("[data-test-id='phone'] input").setValue("+79876543223");
        form.$("[data-test-id='agreement']").click();
        $x("//button[contains(@class,'button_view_extra')]").click();
        boolean invalidInput = $("[data-test-id='name'].input_invalid .input__sub").isDisplayed();

        assertTrue(invalidInput);
    }

    @Test
    void invalidNameLatin() {
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        SelenideElement form = $x("//form");

        form.$("[data-test-id='city'] input").setValue("Москва");
        form.$("[data-test-id='date'] input").clear();
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Thomas Jefferson");
        form.$("[data-test-id='phone'] input").setValue("+79876543223");
        form.$("[data-test-id='agreement']").click();
        $x("//button[contains(@class,'button_view_extra')]").click();
        String subText = $("[data-test-id='name'].input_invalid .input__sub").getText();

        assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.",
                subText.trim());
    }

    @Test
    void validNameCompositeName() {
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        SelenideElement form = $x("//form");

        form.$("[data-test-id='city'] input").setValue("Москва");
        form.$("[data-test-id='date'] input").clear();
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Томас-Лукас Джефферсон");
        form.$("[data-test-id='phone'] input").setValue("+79876543223");
        form.$("[data-test-id='agreement']").click();
        $x("//button[contains(@class,'button_view_extra')]").click();
        String popupText = $("[data-test-id='notification']").shouldBe(visible, Duration.ofSeconds(15))
                .getText();
        $x("//button[contains(@class,'notification__closer')]").click();

        assertEquals("Успешно!" + "\n" + "Встреча успешно забронирована на " + date, popupText.trim());
    }

    @Test
    void validNameCompositeFamily() {
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        SelenideElement form = $x("//form");

        form.$("[data-test-id='city'] input").setValue("Москва");
        form.$("[data-test-id='date'] input").clear();
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Томас Джефферсон-Сваровски");
        form.$("[data-test-id='phone'] input").setValue("+79876543223");
        form.$("[data-test-id='agreement']").click();
        $x("//button[contains(@class,'button_view_extra')]").click();
        String popupText = $("[data-test-id='notification']").shouldBe(visible, Duration.ofSeconds(15))
                .getText();
        $x("//button[contains(@class,'notification__closer')]").click();

        assertEquals("Успешно!" + "\n" + "Встреча успешно забронирована на " + date, popupText.trim());
    }

    @Test
    void validNameCompositeNameAndFamily() {
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        SelenideElement form = $x("//form");

        form.$("[data-test-id='city'] input").setValue("Москва");
        form.$("[data-test-id='date'] input").clear();
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Томас-Лукас Джефферсон-Сваровски");
        form.$("[data-test-id='phone'] input").setValue("+79876543223");
        form.$("[data-test-id='agreement']").click();
        $x("//button[contains(@class,'button_view_extra')]").click();
        String popupText = $("[data-test-id='notification']").shouldBe(visible, Duration.ofSeconds(15))
                .getText();
        $x("//button[contains(@class,'notification__closer')]").click();

        assertEquals("Успешно!" + "\n" + "Встреча успешно забронирована на " + date, popupText.trim());
    }

    @Disabled
    @Test
    void validNameWithSymbolE() {
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        SelenideElement form = $x("//form");

        form.$("[data-test-id='city'] input").setValue("Москва");
        form.$("[data-test-id='date'] input").clear();
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Сергеев Пётр");
        form.$("[data-test-id='phone'] input").setValue("+79876543223");
        form.$("[data-test-id='agreement']").click();
        $x("//button[contains(@class,'button_view_extra')]").click();
        String popupText = $("[data-test-id='notification']").shouldBe(visible, Duration.ofSeconds(15))
                .getText();
        $x("//button[contains(@class,'notification__closer')]").click();

        assertEquals("Успешно!" + "\n" + "Встреча успешно забронирована на " + date, popupText.trim());
    }

    @Test
    void invalidNameOneSymbolInLatin() {
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        SelenideElement form = $x("//form");

        form.$("[data-test-id='city'] input").setValue("Москва");
        form.$("[data-test-id='date'] input").clear();
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Cергеев Алексей");
        form.$("[data-test-id='phone'] input").setValue("+79876543223");
        form.$("[data-test-id='agreement']").click();
        $x("//button[contains(@class,'button_view_extra')]").click();
        String subText = $("[data-test-id='name'].input_invalid .input__sub").getText();

        assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.",
                subText.trim());
    }

    @Test
    void invalidNameSpecialSymbol() {
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        SelenideElement form = $x("//form");

        form.$("[data-test-id='city'] input").setValue("Москва");
        form.$("[data-test-id='date'] input").clear();
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Сергеев Алексей:)");
        form.$("[data-test-id='phone'] input").setValue("+79876543223");
        form.$("[data-test-id='agreement']").click();
        $x("//button[contains(@class,'button_view_extra')]").click();
        String subText = $("[data-test-id='name'].input_invalid .input__sub").getText();

        assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.",
                subText.trim());
    }

    @Test
    void invalidNameSpecialSymbolOnly() {
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        SelenideElement form = $x("//form");

        form.$("[data-test-id='city'] input").setValue("Москва");
        form.$("[data-test-id='date'] input").clear();
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("[!@;%:?()]");
        form.$("[data-test-id='phone'] input").setValue("+79876543223");
        form.$("[data-test-id='agreement']").click();
        $x("//button[contains(@class,'button_view_extra')]").click();
        String subText = $("[data-test-id='name'].input_invalid .input__sub").getText();

        assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.",
                subText.trim());
    }

    @Test
    void emptyPhone() {
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        SelenideElement form = $x("//form");

        form.$("[data-test-id='city'] input").setValue("Москва");
        form.$("[data-test-id='date'] input").clear();
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Томас Джефферсон");
        form.$("[data-test-id='agreement']").click();
        $x("//button[contains(@class,'button_view_extra')]").click();
        String subText = $("[data-test-id='phone'].input_invalid .input__sub").getText();

        assertEquals("Поле обязательно для заполнения", subText.trim());
    }

    @Test
    void invalidFormatPhone8InsteadOfPlus7() {
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        SelenideElement form = $x("//form");

        form.$("[data-test-id='city'] input").setValue("Москва");
        form.$("[data-test-id='date'] input").clear();
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Томас Джефферсон");
        form.$("[data-test-id='phone'] input").setValue("89876543223");
        form.$("[data-test-id='agreement']").click();
        $x("//button[contains(@class,'button_view_extra')]").click();
        String subText = $("[data-test-id='phone'].input_invalid .input__sub").getText();

        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", subText.trim());
    }

    @Test
    void invalidFormatPhoneWithBrackets() {
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        SelenideElement form = $x("//form");

        form.$("[data-test-id='city'] input").setValue("Москва");
        form.$("[data-test-id='date'] input").clear();
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Томас Джефферсон");
        form.$("[data-test-id='phone'] input").setValue("+7(987)6543223");
        form.$("[data-test-id='agreement']").click();
        $x("//button[contains(@class,'button_view_extra')]").click();
        String subText = $("[data-test-id='phone'].input_invalid .input__sub").getText();

        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", subText.trim());
    }

    @Test
    void invalidFormatPhoneWithHyphens() {
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        SelenideElement form = $x("//form");

        form.$("[data-test-id='city'] input").setValue("Москва");
        form.$("[data-test-id='date'] input").clear();
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Томас Джефферсон");
        form.$("[data-test-id='phone'] input").setValue("+7987-654-32-23");
        form.$("[data-test-id='agreement']").click();
        $x("//button[contains(@class,'button_view_extra')]").click();
        String subText = $("[data-test-id='phone'].input_invalid .input__sub").getText();

        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", subText.trim());
    }

    @Test
    void invalidFormatPhoneWithSpaces() {
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        SelenideElement form = $x("//form");

        form.$("[data-test-id='city'] input").setValue("Москва");
        form.$("[data-test-id='date'] input").clear();
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Томас Джефферсон");
        form.$("[data-test-id='phone'] input").setValue("+7987 654 32 23");
        form.$("[data-test-id='agreement']").click();
        $x("//button[contains(@class,'button_view_extra')]").click();
        String subText = $("[data-test-id='phone'].input_invalid .input__sub").getText();

        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", subText.trim());
    }

    @Test
    void invalidPhoneWithoutPlus() {
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        SelenideElement form = $x("//form");

        form.$("[data-test-id='city'] input").setValue("Москва");
        form.$("[data-test-id='date'] input").clear();
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Томас Джефферсон");
        form.$("[data-test-id='phone'] input").setValue("79876543223");
        form.$("[data-test-id='agreement']").click();
        $x("//button[contains(@class,'button_view_extra')]").click();
        String subText = $("[data-test-id='phone'].input_invalid .input__sub").getText();

        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", subText.trim());
    }

    @Test
    void invalidPhoneLatin() {
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        SelenideElement form = $x("//form");

        form.$("[data-test-id='city'] input").setValue("Москва");
        form.$("[data-test-id='date'] input").clear();
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Томас Джефферсон");
        form.$("[data-test-id='phone'] input").setValue("+79876J43223");
        form.$("[data-test-id='agreement']").click();
        $x("//button[contains(@class,'button_view_extra')]").click();
        String subText = $("[data-test-id='phone'].input_invalid .input__sub").getText();

        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", subText.trim());
    }

    @Test
    void invalidPhoneSpecialSymbols() {
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        SelenideElement form = $x("//form");

        form.$("[data-test-id='city'] input").setValue("Москва");
        form.$("[data-test-id='date'] input").clear();
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Томас Джефферсон");
        form.$("[data-test-id='phone'] input").setValue("+7987.644322");
        form.$("[data-test-id='agreement']").click();
        $x("//button[contains(@class,'button_view_extra')]").click();
        String subText = $("[data-test-id='phone'].input_invalid .input__sub").getText();

        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", subText.trim());
    }

    @Test
    void invalidPhoneCyrillic() {
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        SelenideElement form = $x("//form");

        form.$("[data-test-id='city'] input").setValue("Москва");
        form.$("[data-test-id='date'] input").clear();
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Томас Джефферсон");
        form.$("[data-test-id='phone'] input").setValue("+79877644ш22");
        form.$("[data-test-id='agreement']").click();
        $x("//button[contains(@class,'button_view_extra')]").click();
        String subText = $("[data-test-id='phone'].input_invalid .input__sub").getText();

        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", subText.trim());
    }

    @Test
    void checkboxNotPressed() {
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        SelenideElement form = $x("//form");

        form.$("[data-test-id='city'] input").setValue("Москва");
        form.$("[data-test-id='date'] input").clear();
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Томас Джефферсон");
        form.$("[data-test-id='phone'] input").setValue("+79876543223");
        $x("//button[contains(@class,'button_view_extra')]").click();
        boolean checkbox = form.$("[data-test-id='agreement'].input_invalid").isDisplayed();

        assertTrue(checkbox);
    }

    @Test
    void emptyAll() {
        SelenideElement form = $x("//form");

        form.$("[data-test-id='date'] input").clear();
        $x("//button[contains(@class,'button_view_extra')]").click();
        String subText = $("[data-test-id='city'].input_invalid .input__sub").getText();

        assertEquals("Поле обязательно для заполнения", subText.trim());
    }
}
