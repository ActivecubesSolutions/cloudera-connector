//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated by jni4net. See http://jni4net.sourceforge.net/ 
//     Runtime Version:2.0.50727.4952
//
//     Changes to this file may cause incorrect behavior and will be lost if
//     the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

namespace org.drools.conf {
    
    
    #region Component Designer generated code 
    [global::net.sf.jni4net.attributes.JavaClassAttribute()]
    public partial class RemoveIdentitiesOption : global::java.lang.Object, global::org.drools.conf.SingleValueKnowledgeBaseOption {
        
        internal new static global::java.lang.Class staticClass;
        
        internal static global::net.sf.jni4net.jni.MethodId _getPropertyName0;
        
        internal static global::net.sf.jni4net.jni.MethodId _valueOf1;
        
        internal static global::net.sf.jni4net.jni.MethodId _values2;
        
        internal static global::net.sf.jni4net.jni.MethodId _isRemoveIdentities3;
        
        internal static global::net.sf.jni4net.jni.FieldId _YES4;
        
        internal static global::net.sf.jni4net.jni.FieldId _NO5;
        
        internal static global::net.sf.jni4net.jni.FieldId _PROPERTY_NAME6;
        
        protected RemoveIdentitiesOption(global::net.sf.jni4net.jni.JNIEnv @__env) : 
                base(@__env) {
        }
        
        public static global::java.lang.Class _class {
            get {
                return global::org.drools.conf.RemoveIdentitiesOption.staticClass;
            }
        }
        
        [global::net.sf.jni4net.attributes.JavaMethodAttribute("Lorg/drools/conf/RemoveIdentitiesOption;")]
        public static global::org.drools.conf.RemoveIdentitiesOption YES {
            get {
                global::net.sf.jni4net.jni.JNIEnv @__env = global::net.sf.jni4net.jni.JNIEnv.ThreadEnv;
            using(new global::net.sf.jni4net.jni.LocalFrame(@__env, 10)){
                return global::net.sf.jni4net.utils.Convertor.StrongJ2Cp<global::org.drools.conf.RemoveIdentitiesOption>(@__env, @__env.GetStaticObjectFieldPtr(global::org.drools.conf.RemoveIdentitiesOption.staticClass, global::org.drools.conf.RemoveIdentitiesOption._YES4));
            }
            }
        }
        
        [global::net.sf.jni4net.attributes.JavaMethodAttribute("Lorg/drools/conf/RemoveIdentitiesOption;")]
        public static global::org.drools.conf.RemoveIdentitiesOption NO {
            get {
                global::net.sf.jni4net.jni.JNIEnv @__env = global::net.sf.jni4net.jni.JNIEnv.ThreadEnv;
            using(new global::net.sf.jni4net.jni.LocalFrame(@__env, 10)){
                return global::net.sf.jni4net.utils.Convertor.StrongJ2Cp<global::org.drools.conf.RemoveIdentitiesOption>(@__env, @__env.GetStaticObjectFieldPtr(global::org.drools.conf.RemoveIdentitiesOption.staticClass, global::org.drools.conf.RemoveIdentitiesOption._NO5));
            }
            }
        }
        
        [global::net.sf.jni4net.attributes.JavaMethodAttribute("Ljava/lang/String;")]
        public static global::java.lang.String PROPERTY_NAME {
            get {
                global::net.sf.jni4net.jni.JNIEnv @__env = global::net.sf.jni4net.jni.JNIEnv.ThreadEnv;
            using(new global::net.sf.jni4net.jni.LocalFrame(@__env, 10)){
                return global::net.sf.jni4net.utils.Convertor.StrongJ2CpString(@__env, @__env.GetStaticObjectFieldPtr(global::org.drools.conf.RemoveIdentitiesOption.staticClass, global::org.drools.conf.RemoveIdentitiesOption._PROPERTY_NAME6));
            }
            }
        }
        
        private static void InitJNI(global::net.sf.jni4net.jni.JNIEnv @__env, java.lang.Class @__class) {
            global::org.drools.conf.RemoveIdentitiesOption.staticClass = @__class;
            global::org.drools.conf.RemoveIdentitiesOption._getPropertyName0 = @__env.GetMethodID(global::org.drools.conf.RemoveIdentitiesOption.staticClass, "getPropertyName", "()Ljava/lang/String;");
            global::org.drools.conf.RemoveIdentitiesOption._valueOf1 = @__env.GetStaticMethodID(global::org.drools.conf.RemoveIdentitiesOption.staticClass, "valueOf", "(Ljava/lang/String;)Lorg/drools/conf/RemoveIdentitiesOption;");
            global::org.drools.conf.RemoveIdentitiesOption._values2 = @__env.GetStaticMethodID(global::org.drools.conf.RemoveIdentitiesOption.staticClass, "values", "()[Lorg/drools/conf/RemoveIdentitiesOption;");
            global::org.drools.conf.RemoveIdentitiesOption._isRemoveIdentities3 = @__env.GetMethodID(global::org.drools.conf.RemoveIdentitiesOption.staticClass, "isRemoveIdentities", "()Z");
            global::org.drools.conf.RemoveIdentitiesOption._YES4 = @__env.GetStaticFieldID(global::org.drools.conf.RemoveIdentitiesOption.staticClass, "YES", "Lorg/drools/conf/RemoveIdentitiesOption;");
            global::org.drools.conf.RemoveIdentitiesOption._NO5 = @__env.GetStaticFieldID(global::org.drools.conf.RemoveIdentitiesOption.staticClass, "NO", "Lorg/drools/conf/RemoveIdentitiesOption;");
            global::org.drools.conf.RemoveIdentitiesOption._PROPERTY_NAME6 = @__env.GetStaticFieldID(global::org.drools.conf.RemoveIdentitiesOption.staticClass, "PROPERTY_NAME", "Ljava/lang/String;");
        }
        
        [global::net.sf.jni4net.attributes.JavaMethodAttribute("()Ljava/lang/String;")]
        public virtual global::java.lang.String getPropertyName() {
            global::net.sf.jni4net.jni.JNIEnv @__env = this.Env;
            using(new global::net.sf.jni4net.jni.LocalFrame(@__env, 10)){
            return global::net.sf.jni4net.utils.Convertor.StrongJ2CpString(@__env, @__env.CallObjectMethodPtr(this, global::org.drools.conf.RemoveIdentitiesOption._getPropertyName0));
            }
        }
        
        [global::net.sf.jni4net.attributes.JavaMethodAttribute("(Ljava/lang/String;)Lorg/drools/conf/RemoveIdentitiesOption;")]
        public static global::org.drools.conf.RemoveIdentitiesOption valueOf(global::java.lang.String par0) {
            global::net.sf.jni4net.jni.JNIEnv @__env = global::net.sf.jni4net.jni.JNIEnv.ThreadEnv;
            using(new global::net.sf.jni4net.jni.LocalFrame(@__env, 12)){
            return global::net.sf.jni4net.utils.Convertor.StrongJ2Cp<global::org.drools.conf.RemoveIdentitiesOption>(@__env, @__env.CallStaticObjectMethodPtr(global::org.drools.conf.RemoveIdentitiesOption.staticClass, global::org.drools.conf.RemoveIdentitiesOption._valueOf1, global::net.sf.jni4net.utils.Convertor.ParStrongCp2J(par0)));
            }
        }
        
        [global::net.sf.jni4net.attributes.JavaMethodAttribute("()[Lorg/drools/conf/RemoveIdentitiesOption;")]
        public static org.drools.conf.RemoveIdentitiesOption[] values() {
            global::net.sf.jni4net.jni.JNIEnv @__env = global::net.sf.jni4net.jni.JNIEnv.ThreadEnv;
            using(new global::net.sf.jni4net.jni.LocalFrame(@__env, 10)){
            return global::net.sf.jni4net.utils.Convertor.ArrayStrongJ2Cp<org.drools.conf.RemoveIdentitiesOption[], global::org.drools.conf.RemoveIdentitiesOption>(@__env, @__env.CallStaticObjectMethodPtr(global::org.drools.conf.RemoveIdentitiesOption.staticClass, global::org.drools.conf.RemoveIdentitiesOption._values2));
            }
        }
        
        [global::net.sf.jni4net.attributes.JavaMethodAttribute("()Z")]
        public virtual bool isRemoveIdentities() {
            global::net.sf.jni4net.jni.JNIEnv @__env = this.Env;
            using(new global::net.sf.jni4net.jni.LocalFrame(@__env, 10)){
            return ((bool)(@__env.CallBooleanMethod(this, global::org.drools.conf.RemoveIdentitiesOption._isRemoveIdentities3)));
            }
        }
        
        new internal sealed class ContructionHelper : global::net.sf.jni4net.utils.IConstructionHelper {
            
            public global::net.sf.jni4net.jni.IJvmProxy CreateProxy(global::net.sf.jni4net.jni.JNIEnv @__env) {
                return new global::org.drools.conf.RemoveIdentitiesOption(@__env);
            }
        }
    }
    #endregion
}
