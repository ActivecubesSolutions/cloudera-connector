// ------------------------------------------------------------------------------
//  <autogenerated>
//      This code was generated by jni4net. See http://jni4net.sourceforge.net/ 
// 
//      Changes to this file may cause incorrect behavior and will be lost if 
//      the code is regenerated.
//  </autogenerated>
// ------------------------------------------------------------------------------

package org.drools;

@net.sf.jni4net.attributes.ClrTypeInfo
public final class KnowledgeBaseFactoryService_ {
    
    //<generated-static>
    private static system.Type staticType;
    
    public static system.Type typeof() {
        return org.drools.KnowledgeBaseFactoryService_.staticType;
    }
    
    private static void InitJNI(net.sf.jni4net.inj.INJEnv env, system.Type staticType) {
        org.drools.KnowledgeBaseFactoryService_.staticType = staticType;
    }
    //</generated-static>
}

//<generated-proxy>
@net.sf.jni4net.attributes.ClrProxy
class __KnowledgeBaseFactoryService extends system.Object implements org.drools.KnowledgeBaseFactoryService {
    
    protected __KnowledgeBaseFactoryService(net.sf.jni4net.inj.INJEnv __env, long __handle) {
            super(__env, __handle);
    }
    
    @net.sf.jni4net.attributes.ClrMethod("(Ljava/util/Properties;[Ljava/lang/ClassLoader;)Lorg/drools/KnowledgeBaseConfiguration;")
    public native org.drools.KnowledgeBaseConfiguration newKnowledgeBaseConfiguration(java.util.Properties par0, java.lang.ClassLoader[] par1);
    
    @net.sf.jni4net.attributes.ClrMethod("()Lorg/drools/KnowledgeBaseConfiguration;")
    public native org.drools.KnowledgeBaseConfiguration newKnowledgeBaseConfiguration();
    
    @net.sf.jni4net.attributes.ClrMethod("(Ljava/util/Properties;)Lorg/drools/runtime/KnowledgeSessionConfiguration;")
    public native org.drools.runtime.KnowledgeSessionConfiguration newKnowledgeSessionConfiguration(java.util.Properties par0);
    
    @net.sf.jni4net.attributes.ClrMethod("()Lorg/drools/runtime/KnowledgeSessionConfiguration;")
    public native org.drools.runtime.KnowledgeSessionConfiguration newKnowledgeSessionConfiguration();
    
    @net.sf.jni4net.attributes.ClrMethod("()Lorg/drools/KnowledgeBase;")
    public native org.drools.KnowledgeBase newKnowledgeBase();
    
    @net.sf.jni4net.attributes.ClrMethod("(Ljava/lang/String;)Lorg/drools/KnowledgeBase;")
    public native org.drools.KnowledgeBase newKnowledgeBase(java.lang.String par0);
    
    @net.sf.jni4net.attributes.ClrMethod("(Lorg/drools/KnowledgeBaseConfiguration;)Lorg/drools/KnowledgeBase;")
    public native org.drools.KnowledgeBase newKnowledgeBase(org.drools.KnowledgeBaseConfiguration par0);
    
    @net.sf.jni4net.attributes.ClrMethod("(Ljava/lang/String;Lorg/drools/KnowledgeBaseConfiguration;)Lorg/drools/KnowledgeBase;")
    public native org.drools.KnowledgeBase newKnowledgeBase(java.lang.String par0, org.drools.KnowledgeBaseConfiguration par1);
    
    @net.sf.jni4net.attributes.ClrMethod("()Lorg/drools/runtime/Environment;")
    public native org.drools.runtime.Environment newEnvironment();
}
//</generated-proxy>
