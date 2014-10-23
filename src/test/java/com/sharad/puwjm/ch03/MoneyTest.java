package com.sharad.puwjm.ch03;

import static org.junit.Assert.assertEquals;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import static junitparams.JUnitParamsRunner.$;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.sharad.puwjm.ch03.money.Money;

@RunWith(JUnitParamsRunner.class)
public class MoneyTest {
    private static final Object[] getMoney() {
	// return new Object[] { new Object[] { 10, "USD" }, new Object[] { 20,
	// "EUR" }, new Object[] { 60, "INR" } };
	return $($(10, "USD"), $(10, "EUR"), $(60, "INR"));
    }

    @Test
    public void constructorShouldSetAmountAndCurrency() {
	Money money = new Money(10, "USD");
	assertEquals(10, money.getAmount());
	assertEquals("USD", money.getCurrency());
    }

    @Test
    @Parameters(method = "getMoney")
    public void constructorShouldSetAmountAndCurrencyParameterized(int amount, String currency) {
	Money money = new Money(amount, currency);
	assertEquals(amount, money.getAmount());
	assertEquals(currency, money.getCurrency());
    }

}
