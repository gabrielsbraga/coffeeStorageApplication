package com.example.demo.beans;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Coffee {
	long id;
	String name;
	String origin;
	String coffeeType;
	String roast;
	long quantity;
}
