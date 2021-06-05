package com.karnilaev.investment.e2e

import com.codeborne.selenide.CollectionCondition.size
import com.codeborne.selenide.Condition.text
import com.codeborne.selenide.Selenide.`$$`
import com.codeborne.selenide.Selenide.open
import org.junit.jupiter.api.Test


class IndexPageTest {

    @Test
    internal fun headerTest() {
        open("/")
        `$$`("#app").shouldHave(size(1))
        `$$`("#app")[0].shouldHave(text("Войти в систему"))
        `$$`("#app")[0].shouldHave(text("E-mail"))
        `$$`("#app")[0].shouldHave(text("Пароль"))
    }
}