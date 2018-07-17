/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.com.argela.obs.core.app;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import tr.com.argela.obs.core.gui.GuiManager;
import tr.com.argela.obs.core.gui.commandLine.CommandLineManager;
import tr.com.argela.obs.core.remote.MainService;

/**
 *
 * @author aybuke
 */
public class Application {

    EntityManagerFactory entityManagerFactory;
    static Application app;
    MainService mainService = new MainService();
    GuiManager guiManager;

    public static void main(String[] args) {
        app = new Application();
        app.init();
    }

    public void init() {
        initDb();
        initApp();
    }

    public void destroy() {
        entityManagerFactory.close();
    }

    private void initDb() {
        entityManagerFactory = Persistence.createEntityManagerFactory("test1");
    }

    private void initApp() {
        try {
            //guiManager = new CommandLineManager();
            //guiManager.start();
        } catch (Exception ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Application getApp() {
        if (app == null) {
            app = new Application();
        }
        return app;
    }

    public EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    public GuiManager getGuiManager() {
        return guiManager;
    }

    public MainService getMainService() {
        return mainService;
    }

}
