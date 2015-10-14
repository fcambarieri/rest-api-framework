/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ml.project.api.datastore;

import java.util.logging.Logger;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import org.hibernate.HibernateException;
/**
 *
 * @author fcambarieri
 */
@Interceptor
@Transactional
public class TransactionInterceptor {

   @Inject
   private EntityManagerStoreImpl entityManagerStore;

   private Logger logger = Logger.getLogger(TransactionInterceptor.class.getName());

   @AroundInvoke
   public Object runInTransaction(InvocationContext invocationContext) throws Exception {

      EntityManager em = entityManagerStore.createAndRegister();

      Object result = null;
      try {
         em.getTransaction().begin();

         result = invocationContext.proceed();

         em.getTransaction().commit();

      } catch (Exception e) {
         try {
            if (em.getTransaction().isActive()) {
               em.getTransaction().rollback();
               logger.severe("Rolled back transaction");
            }
         } catch (HibernateException e1) {
            logger.severe("Rollback of transaction failed -> " + e1.getMessage());
         }
         throw e;
      } finally {
         if (em != null) {
            entityManagerStore.unregister(em);
            em.close();
         }
      }


      return result;
   }
}
