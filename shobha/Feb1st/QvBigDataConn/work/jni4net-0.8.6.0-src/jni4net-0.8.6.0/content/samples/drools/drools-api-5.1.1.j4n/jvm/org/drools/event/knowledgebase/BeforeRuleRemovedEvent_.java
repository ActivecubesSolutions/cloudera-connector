// ------------------------------------------------------------------------------
//  <autogenerated>
//      This code was generated by jni4net. See http://jni4net.sourceforge.net/ 
// 
//      Changes to this file may cause incorrect behavior and will be lost if 
//      the code is regenerated.
//  </autogenerated>
// ------------------------------------------------------------------------------

package org.drools.event.knowledgebase;

@net.sf.jni4net.attributes.ClrTypeInfo
public final class BeforeRuleRemovedEvent_ {
    
    //<generated-static>
    private static system.Type staticType;
    
    public static system.Type typeof() {
        return org.drools.event.knowledgebase.BeforeRuleRemovedEvent_.staticType;
    }
    
    private static void InitJNI(net.sf.jni4net.inj.INJEnv env, system.Type staticType) {
        org.drools.event.knowledgebase.BeforeRuleRemovedEvent_.staticType = staticType;
    }
    //</generated-static>
}

//<generated-proxy>
@net.sf.jni4net.attributes.ClrProxy
class __BeforeRuleRemovedEvent extends system.Object implements org.drools.event.knowledgebase.BeforeRuleRemovedEvent {
    
    protected __BeforeRuleRemovedEvent(net.sf.jni4net.inj.INJEnv __env, long __handle) {
            super(__env, __handle);
    }
    
    @net.sf.jni4net.attributes.ClrMethod("()Lorg/drools/KnowledgeBase;")
    public native org.drools.KnowledgeBase getKnowledgeBase();
    
    @net.sf.jni4net.attributes.ClrMethod("()Lorg/drools/definition/rule/Rule;")
    public native org.drools.definition.rule.Rule getRule();
}
//</generated-proxy>
