// ------------------------------------------------------------------------------
//  <autogenerated>
//      This code was generated by jni4net. See http://jni4net.sourceforge.net/ 
// 
//      Changes to this file may cause incorrect behavior and will be lost if 
//      the code is regenerated.
//  </autogenerated>
// ------------------------------------------------------------------------------

package system;

@net.sf.jni4net.attributes.ClrType
public class NullReferenceException extends system.SystemException {
    
    //<generated-proxy>
    private static system.Type staticType;
    
    protected NullReferenceException(net.sf.jni4net.inj.INJEnv __env, long __handle) {
            super(__env, __handle);
    }
    
    @net.sf.jni4net.attributes.ClrConstructor("()V")
    public NullReferenceException() {
            super(((net.sf.jni4net.inj.INJEnv)(null)), 0);
        system.NullReferenceException.__ctorNullReferenceException0(this);
    }
    
    @net.sf.jni4net.attributes.ClrConstructor("(LSystem/String;)V")
    public NullReferenceException(java.lang.String message) {
            super(((net.sf.jni4net.inj.INJEnv)(null)), 0);
        system.NullReferenceException.__ctorNullReferenceException1(this, message);
    }
    
    @net.sf.jni4net.attributes.ClrConstructor("(LSystem/String;LSystem/Exception;)V")
    public NullReferenceException(java.lang.String message, system.Exception innerException) {
            super(((net.sf.jni4net.inj.INJEnv)(null)), 0);
        system.NullReferenceException.__ctorNullReferenceException2(this, message, innerException);
    }
    
    @net.sf.jni4net.attributes.ClrMethod("()V")
    private native static void __ctorNullReferenceException0(net.sf.jni4net.inj.IClrProxy thiz);
    
    @net.sf.jni4net.attributes.ClrMethod("(Ljava/lang/String;)V")
    private native static void __ctorNullReferenceException1(net.sf.jni4net.inj.IClrProxy thiz, java.lang.String message);
    
    @net.sf.jni4net.attributes.ClrMethod("(Ljava/lang/String;Lsystem/Exception;)V")
    private native static void __ctorNullReferenceException2(net.sf.jni4net.inj.IClrProxy thiz, java.lang.String message, system.Exception innerException);
    
    public static system.Type typeof() {
        return system.NullReferenceException.staticType;
    }
    
    private static void InitJNI(net.sf.jni4net.inj.INJEnv env, system.Type staticType) {
        system.NullReferenceException.staticType = staticType;
    }
    //</generated-proxy>
}
