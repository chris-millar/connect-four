package com.schrismillar.connect4;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class HelloWorldTest {
    @Test
    public void sayHelloReturnsHelloWorld() {
        HelloWorld helloWorld = new HelloWorld();
        assertEquals("Hello World!", helloWorld.sayHello());
    }
}
