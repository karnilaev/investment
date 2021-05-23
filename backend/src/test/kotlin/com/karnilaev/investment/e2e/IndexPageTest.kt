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
        `$$`("header.navbar ul li").shouldHave(size(2))
        `$$`("header.navbar ul li")[0].shouldHave(text("Главная"))
        `$$`("header.navbar ul li")[1].shouldHave(text("Новый портфель"))
    }
}