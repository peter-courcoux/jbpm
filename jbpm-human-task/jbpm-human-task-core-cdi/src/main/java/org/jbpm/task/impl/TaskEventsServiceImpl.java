/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jbpm.task.impl;

import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.jboss.seam.transaction.Transactional;
import org.jbpm.task.TaskEvent;
import org.jbpm.task.annotations.Local;
import org.jbpm.task.annotations.Persistent;
import org.jbpm.task.api.TaskEventsService;


/**
 *
 */
@Persistent 
@Transactional
public class TaskEventsServiceImpl implements TaskEventsService{
    @PersistenceContext
    private EntityManager em;
    
    public List<TaskEvent> getTaskEventsById(long taskId) {
        return em.createQuery("select te from TaskEvent te where te.taskId =:taskId ").setParameter("taskId", taskId).getResultList();
    }

    public void removeTaskEventsById(long taskId) {
        List<TaskEvent> taskEventsById = getTaskEventsById(taskId);
        for(TaskEvent e : taskEventsById){
            em.remove(e);
        }
    }
    
}
