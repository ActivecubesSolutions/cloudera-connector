//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated by jni4net. See http://jni4net.sourceforge.net/ 
//     Runtime Version:2.0.50727.4952
//
//     Changes to this file may cause incorrect behavior and will be lost if
//     the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

namespace org.drools.runtime.rule {
    
    
    #region Component Designer generated code 
    [global::net.sf.jni4net.attributes.JavaInterfaceAttribute()]
    public partial interface LiveQuery {
        
        [global::net.sf.jni4net.attributes.JavaMethodAttribute("()V")]
        void close();
    }
    #endregion
    
    #region Component Designer generated code 
    public partial class LiveQuery_ {
        
        public static global::java.lang.Class _class {
            get {
                return global::org.drools.runtime.rule.@__LiveQuery.staticClass;
            }
        }
    }
    #endregion
    
    #region Component Designer generated code 
    [global::net.sf.jni4net.attributes.JavaProxyAttribute(typeof(global::org.drools.runtime.rule.LiveQuery), typeof(global::org.drools.runtime.rule.LiveQuery_))]
    [global::net.sf.jni4net.attributes.ClrWrapperAttribute(typeof(global::org.drools.runtime.rule.LiveQuery), typeof(global::org.drools.runtime.rule.LiveQuery_))]
    internal sealed partial class @__LiveQuery : global::java.lang.Object, global::org.drools.runtime.rule.LiveQuery {
        
        internal new static global::java.lang.Class staticClass;
        
        internal static global::net.sf.jni4net.jni.MethodId _close0;
        
        private @__LiveQuery(global::net.sf.jni4net.jni.JNIEnv @__env) : 
                base(@__env) {
        }
        
        private static void InitJNI(global::net.sf.jni4net.jni.JNIEnv @__env, java.lang.Class @__class) {
            global::org.drools.runtime.rule.@__LiveQuery.staticClass = @__class;
            global::org.drools.runtime.rule.@__LiveQuery._close0 = @__env.GetMethodID(global::org.drools.runtime.rule.@__LiveQuery.staticClass, "close", "()V");
        }
        
        public void close() {
            global::net.sf.jni4net.jni.JNIEnv @__env = this.Env;
            using(new global::net.sf.jni4net.jni.LocalFrame(@__env, 10)){
            @__env.CallVoidMethod(this, global::org.drools.runtime.rule.@__LiveQuery._close0);
            }
        }
        
        private static global::System.Collections.Generic.List<global::net.sf.jni4net.jni.JNINativeMethod> @__Init(global::net.sf.jni4net.jni.JNIEnv @__env, global::java.lang.Class @__class) {
            global::System.Type @__type = typeof(__LiveQuery);
            global::System.Collections.Generic.List<global::net.sf.jni4net.jni.JNINativeMethod> methods = new global::System.Collections.Generic.List<global::net.sf.jni4net.jni.JNINativeMethod>();
            methods.Add(global::net.sf.jni4net.jni.JNINativeMethod.Create(@__type, "close", "close0", "()V"));
            return methods;
        }
        
        private static void close0(global::System.IntPtr @__envp, global::net.sf.jni4net.utils.JniLocalHandle @__obj) {
            // ()V
            // ()V
            global::net.sf.jni4net.jni.JNIEnv @__env = global::net.sf.jni4net.jni.JNIEnv.Wrap(@__envp);
            try {
            global::org.drools.runtime.rule.LiveQuery @__real = global::net.sf.jni4net.utils.Convertor.FullJ2C<global::org.drools.runtime.rule.LiveQuery>(@__env, @__obj);
            @__real.close();
            }catch (global::System.Exception __ex){@__env.ThrowExisting(__ex);}
        }
        
        new internal sealed class ContructionHelper : global::net.sf.jni4net.utils.IConstructionHelper {
            
            public global::net.sf.jni4net.jni.IJvmProxy CreateProxy(global::net.sf.jni4net.jni.JNIEnv @__env) {
                return new global::org.drools.runtime.rule.@__LiveQuery(@__env);
            }
        }
    }
    #endregion
}
