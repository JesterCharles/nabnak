package com.revature.nabnak;

/* Nabnak is a kanband board for professional developers aimed to be the next best virtual board on the internet.
    The goal is to allow members to join the application, be assigned part of a team and shard their kanban cards.
*/

import com.revature.nabnak.util.ServletContext;

import java.io.File;
import java.net.MalformedURLException;

public class MainDriver {


    public static void main(String[] args) {


        ServletContext servletContext = new ServletContext();
        servletContext.run();

    }



}
