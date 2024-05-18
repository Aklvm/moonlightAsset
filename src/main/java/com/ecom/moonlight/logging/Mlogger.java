package com.ecom.moonlight.logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ecom.moonlight.dto.EmailUserForLoginAndSignUp;

public class Mlogger  {

      private Logger logger;
      private static Boolean isMethodLoggingEnabled=null;

      public static Mlogger getlogger(Class className){
        return new Mlogger(className);
      }
      public Mlogger(Class className){
        this.logger=LogManager.getLogger(className);
      }

      public void log(String logStr, String level){
        switch (level) {
                case "INFO":
                this.logger.info(logStr);
                break;
                case "DEBUG":
                this.logger.debug(logStr);
                break;
                case "WARN":
                this.logger.warn(logStr);
                break;
                case "ERROR":
                this.logger.debug(logStr);
                break;
                case "TRACE":
                this.logger.trace(level);
                break;
                default:
                break;
        }
    }
        public void debug(String logStr){
                 this.logger.debug(logStr);
        }
        public void error(String str,Throwable t) {
             this.logger.error(str,t);
        }
        public void error(String str){
          this.logger.error(str);
        }
    
        public void debug(String string,  Object ... obj) {
           this.logger.debug(string, obj);
        }

      }

