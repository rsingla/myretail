package com.target.api.call.integration;

import java.util.Currency;

public class CheckCurrencySymbol {
  
  public static void main(String[] args) {
    Currency currency = Currency.getInstance("INR");
    System.out.println(currency.getDisplayName());
  }

}
