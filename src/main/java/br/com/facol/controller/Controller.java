package br.com.facol.controller;

import br.com.facol.ioc.ContainerIoc;

public class Controller {
    private final ContainerIoc containerIoc;
    private String pathSpec;

    public Controller(ContainerIoc containerIoc) {
        this.containerIoc = containerIoc;
    }

    public void UserRote(){
        UserServlet userServlet = new UserServlet();

    }

}
