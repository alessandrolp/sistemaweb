package br.com.sistema.util;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.hibernate.Session;

public class PhaseListenerSistemaweb implements PhaseListener{

	// antes da fase
	@Override
	public void beforePhase(PhaseEvent fase) {
		if(fase.getPhaseId().equals(PhaseId.RESTORE_VIEW)){
			System.out.println("antes da fase: " + getPhaseId().toString());
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			FacesContextUtil.setRequestSession(session);
		}
	}
	
	// depois da fase
	@Override
	public void afterPhase(PhaseEvent fase) {
		System.out.println("depois da fase: " + getPhaseId().toString());
		if(fase.getPhaseId().equals(PhaseId.RENDER_RESPONSE)){
			Session session = FacesContextUtil.getRequestSession();
			try {
				session.getTransaction().commit();
			} catch (Exception e) {
				if(session.getTransaction().isActive()){
					session.getTransaction().rollback();
				}
			} finally{
				session.close();
			}
		}
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.ANY_PHASE;
	}
	
}


