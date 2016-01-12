package com.eason.coding.life.el;

import java.util.Calendar;

import org.junit.Test;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.SpelParserConfiguration;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import com.eason.coding.life.entity.Person;

public class SpringElTest {
	private final ExpressionParser parser = new SpelExpressionParser(new SpelParserConfiguration(true, true));
	@Test
	public void test(){
		Person person= new Person("Eason", Calendar.getInstance());
		Expression expression = parser.parseExpression("name=='Eason'");
		StandardEvaluationContext context = new StandardEvaluationContext();
		context.setRootObject(person);
		Boolean meetConditoin = expression.getValue(context, Boolean.class);
		System.out.println(meetConditoin);
	}
}
