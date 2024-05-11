package com.ecom.moonlight.exception;


public class MoonLightException  extends RuntimeException{
    
    private MoonlightError moonlightError;

    public MoonLightException(MoonlightError moonlightError){
        super(moonlightError.getErrorMessage());
        this.moonlightError=moonlightError;
    }
    public MoonLightException(MoonlightError moonLightError, Throwable exc){
      super(moonLightError.getErrorMessage(),exc);
      this.moonlightError=moonLightError;
    }
    public MoonLightException(Throwable excep){
        super(excep);
    }
    public MoonlightError getError(){
        return this.moonlightError;
    }
    
}
