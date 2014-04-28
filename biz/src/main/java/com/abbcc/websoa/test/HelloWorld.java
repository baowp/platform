package com.abbcc.websoa.test;

import java.io.IOException;
import javax.jws.WebService;

@WebService
public abstract interface HelloWorld
{
  public abstract String sayHello(String paramString)
    throws IOException;
}

/* Location:           G:\software\abbcc_websoa.jar
 * Qualified Name:     com.abbcc.websoa.test.HelloWorld
 * JD-Core Version:    0.6.2
 */