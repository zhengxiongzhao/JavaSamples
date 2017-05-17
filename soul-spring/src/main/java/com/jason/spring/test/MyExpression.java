package com.jason.spring.test;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

public class MyExpression {
//	@Test
	public void show() {
		ExpressionParser parser = new SpelExpressionParser();
		Expression expression = parser.parseExpression("('Hello' + ' World').concat(#end)");
		EvaluationContext context = new StandardEvaluationContext();
		context.setVariable("end", "!");
		Assert.assertEquals("Hello World!", expression.getValue(context));
	}
	
	@Test  
	public void testParserContext() {  
	    ExpressionParser parser = new SpelExpressionParser();  
	    ParserContext parserContext = new ParserContext() {  
	        @Override  
	         public boolean isTemplate() {  
	            return true;  
	        }  
	        @Override  
	        public String getExpressionPrefix() {  
	            return "#{";  
	        }  
	        @Override  
	        public String getExpressionSuffix() {  
	            return "}";  
	        }  
	    };  
	    String template = "#{'Hello '}#{'World!'}";  
	    Expression expression = parser.parseExpression(template, parserContext);  
	    Assert.assertEquals("Hello World!", expression.getValue());  
	}  
}
