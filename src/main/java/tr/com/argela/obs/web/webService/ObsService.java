/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.com.argela.obs.web.webService;

import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import tr.com.argela.obs.core.app.Application;
import tr.com.argela.obs.core.models.Student;
import tr.com.argela.obs.core.remote.MainService;

/**
 *
 * @author enesrecep
 */
@WebService(serviceName = "ObsService")
public class ObsService {

    public List<Student> getStudents() throws Exception {
        MainService mainService = Application.getApp().getMainService();

        return mainService.getStudents();
    }

}
